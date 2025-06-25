package com.anhkhoa.travellak.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Entity.Booking;
import com.anhkhoa.travellak.Entity.Tour;
import com.anhkhoa.travellak.Entity.Users;
import com.anhkhoa.travellak.Mapper.BookingMapper;
import com.anhkhoa.travellak.Repository.BookingRepository;
import com.anhkhoa.travellak.Repository.TourRepository;
import com.anhkhoa.travellak.Repository.UsersRepository;
import com.anhkhoa.travellak.dto.Request.Booking.BookingCreationRequest;
import com.anhkhoa.travellak.dto.Request.Booking.BookingUpdateRequest;
import com.anhkhoa.travellak.dto.Response.BookingResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor // tạo constructor chứa tất cả các biến được khai báo final và tự động inject các dependency
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // khai báo phạm vi là private và final
@Service
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    UsersRepository usersRepository;
    TourRepository tourRepository;

    private static final String PENDING = "Chờ thanh toán";

    @Transactional
    public List<BookingResponse> getMyBookings(UUID userId) {
        List<Booking> list = bookingRepository.findByUser_UserId(userId);
        LocalDate today = LocalDate.now();
        boolean changed = false;

        for (Booking b : list) {
            if (PENDING.equals(b.getStatus()) && b.getStartDate().isBefore(today)) {
                b.setStatus("Quá hạn");
                changed = true;
            }
        }
        if (changed) {
            bookingRepository.saveAll(list);
        }
        return bookingMapper.toListBookingResponse(list);
    }

    public BookingResponse getBookingById(UUID bookingId) {
        Booking booking =
                bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Không tìm thấy booking"));
        return bookingMapper.toBookingResponse(booking);
    }

    public List<BookingResponse> getAll() {
        return bookingMapper.toListBookingResponse(bookingRepository.findAll());
    }

    public List<BookingResponse> getBookingByUserId(UUID userId) {
        return bookingMapper.toListBookingResponse(bookingRepository.findByUser_UserId(userId));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    public BookingResponse createBooking(BookingCreationRequest request) {
        Users user = usersRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User"));
        Tour tour = tourRepository
                .findById(request.getTourId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Tour"));
        Booking booking = bookingMapper.toBooking(request);
        booking.setTour(tour);
        booking.setUser(user);
        booking.setBookingDate(LocalDate.now());
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public BookingResponse updateBooking(UUID bookingId, BookingUpdateRequest request) {
        Booking booking =
                bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Không tìm thấy Booking"));
        Users user = usersRepository
                .findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User"));
        Tour tour = tourRepository
                .findById(request.getTourId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Tour"));
        bookingMapper.updateBooking(booking, request);
        booking.setTour(tour);
        booking.setUser(user);
        booking.setBookingDate(LocalDate.now());
        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

    public String deleteBooking(UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Không tìm thấy booking");
        }
        bookingRepository.deleteById(bookingId);
        return "Xoá thành công";
    }
}
