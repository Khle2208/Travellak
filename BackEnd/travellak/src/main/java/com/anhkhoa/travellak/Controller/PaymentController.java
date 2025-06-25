package com.anhkhoa.travellak.Controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.anhkhoa.travellak.Configuration.VNPayConfig;
import com.anhkhoa.travellak.dto.Request.Payment.PaymentCreationRequest;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {
    @PostMapping("/create_payment")
    public Map<String, String> creatPayment(@RequestBody PaymentCreationRequest request)
            throws UnsupportedEncodingException {
        BigDecimal totalPrice = new BigDecimal(request.getTotalPrice());
        BigDecimal amountBigDecimal = totalPrice.multiply(new BigDecimal(100));
        String orderType = "other";
        long amount = amountBigDecimal.longValue();
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);

        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_PaymentMethod", "");
        System.out.println(vnp_TxnRef);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        System.out.println(request.getBookingId());
        vnp_Params.put("vnp_OrderInfo", "Thanh toán hoá đơn : " + request.getBookingId());
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_IpAddr", "127.0.0.1");
        //        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl + "?tourId=" + request.getTourId());
        System.out.println(request.getReturnUrl());
        vnp_Params.put("vnp_ReturnUrl", request.getReturnUrl());

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        System.out.println(vnp_CreateDate);
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 10);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        System.out.println(vnp_ExpireDate);
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        System.out.println(paymentUrl);
        //        return paymentUrl;
        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", paymentUrl);
        response.put("returnUrl", request.getReturnUrl()); // Trả về returnUrl
        return response;
    }
}
