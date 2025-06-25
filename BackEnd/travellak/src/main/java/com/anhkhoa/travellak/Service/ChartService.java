package com.anhkhoa.travellak.Service;

import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Repository.BookingRepository;
import com.anhkhoa.travellak.Repository.TourRepository;
import com.anhkhoa.travellak.Repository.UsersRepository;
import com.anhkhoa.travellak.dto.Response.ChartResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor // tạo constructor chứa tất cả các biến được khai báo final và tự động inject các dependency
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // khai báo phạm vi là private và final
public class ChartService {
    TourRepository tourRepository;
    UsersRepository usersRepository;
    BookingRepository bookingRepository;

    public ChartResponse getChart() {
        return ChartResponse.builder()
                .totalBooking(bookingRepository.count())
                .totalTour(tourRepository.count())
                .totalUser(usersRepository.count())
                .totalRevenue(bookingRepository.getTotalRevenue("Đã thanh toán"))
                .build();
    }
}
