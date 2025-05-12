package com.anhkhoa.travellak.Repository;

import com.anhkhoa.travellak.Entity.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {
    List<Booking> findByUser_UserId(UUID userId);

    List<Booking> findByTour_TourId(UUID tourId);

    @Modifying
    @Transactional
    @Query("""
            UPDATE Booking b
            SET b.status = 'Đã huỷ'
            WHERE b.startDate < :today
              AND b.status = :pendingStatus
            """)
    int cancelExpiredBookings(
            @Param("today") LocalDate today,
            @Param("pendingStatus") String pendingStatus
    );


    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM Booking b WHERE b.status = :status")
    Double getTotalRevenue(@Param("status") String status);

    @Query("SELECT COALESCE(SUM(b.totalPrice), 0) FROM Booking b WHERE b.status = :status AND MONTH(bookingDate) = :month")
    Double getTotalRevenueByMonth(@Param("status") String status, int month);
}
