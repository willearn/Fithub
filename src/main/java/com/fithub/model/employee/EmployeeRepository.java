package com.fithub.model.employee;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// XiaoQing
	@Query("from Employee where jobtitleid = :jobtitleid")
	List<Employee> findManagersByJobTitleId(@Param("jobtitleid") String jobtitleid);

	// william 自定查詢，返回全部 employeename 和 employeeid
	@Query("SELECT e.employeename, e.employeeid FROM Employee e")
	List<Object[]> findAllemployeenameAndemployeeid();

	// XiaoQing
	@Query("select count(*) from Employee where :name is null or employeename like %:name%")
	long count(@Param("name") String name);

	// XiaoQing
	@Query("select e from Employee e where e.employeename like CONCAT('%', :name, '%')")
	Page<Employee> searchByName(Pageable pageable, @Param("name") String name);

	// XiaoQing
	@Query("select count(*) from Employee where jobtitleid = :jobtitleid")
	long countByJobTitleId(@Param("jobtitleid") Integer jobtitleid);

	// XiaoQing
	@Query("select count(*) from Employee where jobtitleid = :jobtitleid AND (:name is null or employeename like %:name%)")
	long countByJobTitleIdAndName(@Param("jobtitleid") Integer jobtitleid, @Param("name") String name);

	// XiaoQing
	@Query("from Employee where jobtitleid = :jobtitleid AND (:name is null or employeename like %:name%)")
	Page<Employee> findManagersByJobTitleIdAndName(Pageable pageable, @Param("jobtitleid") Integer jobtitleid,
			@Param("name") String name);

	// XiaoQing
	@Query("from Employee where jobtitleid = :jobtitleid")
	Page<Employee> findManagersByJobTitleId(Pageable pageable, @Param("jobtitleid") Integer jobtitleid);

	// XiaoQing
//	@Query("SELECT e.employeename as employeenamne, e.employeeemail as employeeemail, e.employeephone as emploueephone from Employee as e where e.jobtitleid = :jobtitleid")
//	List<Map<String,Object>> findEmpNameEmailPhoneByJobTitleId(@Param("jobtitleid") Integer jobtitleid);

	// XiaoQing
	@Query("SELECT e.employeename as employeenamne, e.employeeemail as employeeemail, e.employeephone as emploueephone,  s.specialtyname as specialtyname , cp.cpicfile as cpicfile\r\n"
			+ "FROM Employee AS e\r\n" + "LEFT JOIN CoachPic AS cp ON e.employeeid = cp.employeeid\r\n"
			+ "LEFT JOIN CoachSpecialty AS cs ON e.employeeid = cs.employeeid\r\n"
			+ "LEFT JOIN Specialty AS s ON cs.specialtyid = s.specialtyid\r\n"
			+ "WHERE e.jobtitleid = (SELECT jobtitleid FROM JobTitle WHERE jobtitlename = '教練')")
	List<Map<String, Object>> findCoachDataPicSpecialty();

	// XiaoQing
	@Query("SELECT e.employeeid as employeeid ,  e.employeename as employeenamne, e.employeeemail as employeeemail, e.employeephone as emploueephone,  s.specialtyname as specialtyname \r\n"
			+ "FROM Employee AS e\r\n" + "LEFT JOIN CoachSpecialty AS cs ON e.employeeid = cs.employeeid\r\n"
			+ "LEFT JOIN Specialty AS s ON cs.specialtyid = s.specialtyid\r\n"
			+ "WHERE e.jobtitleid = (SELECT jobtitleid FROM JobTitle WHERE jobtitlename = '教練')")
	List<Map<String, Object>> findCoachDataAndSpecialty();

	// Chrislafolia 驗證教練是否衝堂
	@Query("SELECT e.employeeid employeeid ,  e.employeename employeenamne, cl.classId classId "
			+ "FROM Employee e JOIN e.classes cl "
			+ "WHERE e.employeeid = :employeeid AND cl.classDate = :classDate AND cl.classTime = :classTime  ")
	List<Map<String, Object>> findClassesInDateRangeAndEmployeeid(@Param("employeeid") Integer employeeid,
			@Param("classDate") String classDate, @Param("classTime") String classTime);

}
