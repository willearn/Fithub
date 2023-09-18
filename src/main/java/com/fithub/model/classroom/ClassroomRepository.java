package com.fithub.model.classroom;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    // william 自定查詢，返回 classroomName,classroomId
    @Query("SELECT c.classroomName classroomName, c.classroomId classroomId FROM Classroom c WHERE c.classroomStatus != '維修中'")
    List<Map<String,Object>> findAllClassroomNamesAndIds();

    // Author: Chrislafolia
    // findAll 不 return description 和 pic
    @Query("SELECT c.classroomId, c.classroomName, c.classroomCapacity, c.classroomPrice,c.classroomStatus FROM Classroom c")
    List<Object[]> findAllClassroomsWithoutDescriptionsAndPics();

    // william 返回指定教室的全部
    // classroomId,classroomName,classroomstatus,classroomPrice,classroomPic
    @Query("SELECT new com.fithub.model.classroom.ClassroomDTO(c.classroomId, c.classroomName, c.classroomStatus ,c.classroomPrice,c.classroomPic) FROM Classroom c")
    List<ClassroomDTO> getClassroomInfo();
    
    // chrislafolia 查詢特定時間 classes
    @Query("SELECT cl.classId classId, cl.classDate classDate, cl.classTime classTime "
    		+ "FROM Classroom cr JOIN classes cl "
    		+ "WHERE cr.classroomId= :classroomId AND (cl.classDate= :classDate) AND (cl.classTime= :classTime)")
    List<Map<String,Object>> findClassesByDateAndTime(@Param("classroomId") int classroomId,
    		@Param("classDate") String classDate,@Param("classTime") String classTime);
    
    // chrislafolia 查詢特定時間 rentOrder
    @Query("SELECT  r.rentorderid rentorderid, r.rentdate rentdate, r.renttime renttime "
    		+ "FROM Classroom cr JOIN rentOrder r "
    		+ "WHERE cr.classroomId= :classroomId AND (r.rentdate= :rentdate) "
    		+ "AND (r.renttime= :renttime) AND (r.rentstatus=  :rentstatus)")
    List<Map<String,Object>> findRentOrdersByDateAndTimeAndStatus(@Param("classroomId") int classroomId,
    		@Param("rentdate") String rentdate,@Param("renttime") String renttime,@Param("rentstatus") String rentstatus);
}
