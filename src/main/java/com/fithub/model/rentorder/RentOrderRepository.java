package com.fithub.model.rentorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentOrderRepository extends JpaRepository<RentOrder, Integer> {
	 
    // william ，返回指定教室的全部 rentdate renttime rentstatus
    @Query("SELECT r.rentdate, r.renttime ,r.rentstatus FROM RentOrder r WHERE r.classroomid = :classroomid")
    List<Object[]> findAllrentDateAndrentTimeAndrentStatusByClassroomId(@Param("classroomid") Integer classroomId);

    @Query("SELECT r FROM RentOrder r WHERE r.classroomid = :classroomid AND r.rentdate = :rentdate AND r.renttime = :renttime")
    RentOrder checkRentOrder(
        @Param("classroomid") Integer classroomId,
        @Param("rentdate") String rentdate,
        @Param("renttime") String renttime
    );

}
