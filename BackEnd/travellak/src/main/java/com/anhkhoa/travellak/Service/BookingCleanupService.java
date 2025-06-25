package com.anhkhoa.travellak.Service;

import java.time.LocalDate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingCleanupService {
    private final BookingRepository bookingRepository;
    private static final String PENDING = "Chờ thanh toán";

    // Cron: giây phút giờ ngày-tháng-thứ = 0 5 0 * * * → 00:05 mỗi ngày
    @Scheduled(cron = "0 5 0 * * *")
    public void cancelOverdueBookings() {
        LocalDate today = LocalDate.now();
        int count = bookingRepository.cancelExpiredBookings(today, PENDING);
        System.out.println("[Scheduler] Đã huỷ " + count + " booking quá hạn.");
    }
}
