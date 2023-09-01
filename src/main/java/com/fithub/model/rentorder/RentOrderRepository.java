package com.fithub.model.rentorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentOrderRepository extends JpaRepository<RentOrder, Integer> {
	 
    // william ，返回指定教室的全部 rentdate renttime rentstatus
    @Query("SELECT r.rentdate, r.renttime ,r.rentstatus FROM RentOrder r WHERE r.classroomid = :classroomid")
    List<Object[]> findAllrentDateAndrentTimeAndrentStatusByClassroomId(@Param("classroomid") Integer classroomId);

}
