package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.anhkhoa.travellak.Service.BookingService;
import com.anhkhoa.travellak.dto.Request.Booking.BookingCreationRequest;
import com.anhkhoa.travellak.dto.Request.Booking.BookingUpdateRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.BookingResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("Booking")
@RequiredArgsConstructor // tạo constructor chứa tất cả các biến được khai báo final và tự động inject các dependency
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // khai báo phạm vi là private và final
public class BookingController {
    BookingService bookingService;

    @GetMapping("/MyBooking/{userId}")
    public ApiResponse<List<BookingResponse>> getBookingByUserId(@PathVariable("userId") UUID userId) {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getMyBookings(userId))
                .build();
    }

    @GetMapping("/{bookingId}")
    public ApiResponse<BookingResponse> getBookingById(@PathVariable("bookingId") UUID bookingId) {
        System.out.println(bookingId);
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBookingById(bookingId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookingResponse>> getBookingByUserId() {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getAll())
                .build();
    }

    @PostMapping
    public ApiResponse<BookingResponse> createBooking(@RequestBody BookingCreationRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }

    @PutMapping("/{bookingId}")
    public ApiResponse<BookingResponse> updateBooing(
            @PathVariable("bookingId") UUID bookingId, @RequestBody BookingUpdateRequest request) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(bookingId, request))
                .build();
    }

    @DeleteMapping("/{bookingId}")
    public ApiResponse<String> deleteBooking(@PathVariable("bookingId") UUID bookingId) {
        return ApiResponse.<String>builder()
                .result(bookingService.deleteBooking(bookingId))
                .build();
    }
}
