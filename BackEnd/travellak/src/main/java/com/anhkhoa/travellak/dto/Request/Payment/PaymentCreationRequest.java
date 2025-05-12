package com.anhkhoa.travellak.dto.Request.Payment;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class PaymentCreationRequest {
    private String totalPrice;
    private UUID bookingId;
    private UUID tourId;
    private String returnUrl;
}
