package com.sobu.parkinglot.data.repository;

import com.sobu.parkinglot.data.model.StudentTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentTrackingRepository extends JpaRepository<StudentTracking, Long> {
    @Query("select str from StudentTracking str order by str.createAt desc ")
    Page<StudentTracking> findAllOrderByCreateAtDesc(Pageable pageable);

    @Query("select str from StudentTracking str where (:searchKey <> '' and lower(str.message) like concat('%', :searchKey, '%')) or str.status = :status order by str.createAt desc ")
    Page<StudentTracking> doFilter(@Param("searchKey") String searchKey, @Param("status") int status, Pageable pageable);
}
