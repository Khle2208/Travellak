package com.anhkhoa.travellak.dto.Request.Payment;

import java.util.UUID;

import lombok.Data;

@Data
public class PaymentCreationRequest {
    private String totalPrice;
    private UUID bookingId;
    private UUID tourId;
    private String returnUrl;
}
