USE FithubTest

select cl.classid, cl.courseid,co.coursename,coc.categoryname ,e.employeename  , cl.classdate,  cl.classtime,
       cl.price , r.classroomname ,co.coursedescription , co.courseimgpath
from Classes cl JOIN Course co ON cl.courseid = co.courseid
                JOIN CourseCategories coc ON co.categoryid = coc.categoryid 
				JOIN Employee e ON cl.employeeid = e.employeeid 
				JOIN Classroom  r ON cl.classroomid = r.classroomid 
where cl.courseid=13 AND cl.classdate>'2023-08-01' AND cl.classdate<'2023-11-30'
order by cl.classdate;

select cl.classid, cl.courseid,co.coursename,coc.categoryname ,e.employeename  , cl.classdate,  cl.classtime, cl.coachSubstitute,
       cl.applicantsceil, cl.applicantsfloor ,cl.price , r.classroomname 
from Classes cl JOIN Course co ON cl.courseid = co.courseid
                JOIN CourseCategories coc ON co.categoryid = coc.categoryid 
				JOIN Employee e ON cl.employeeid = e.employeeid 
				JOIN Classroom  r ON cl.classroomid = r.classroomid 
where cl.courseid=13 AND cl.classdate>'2023-08-01' AND cl.classdate<'2023-11-30'
order by cl.classdate;

select cl.classid, co.coursename,coc.categoryname ,e.employeename  , cl.classdate,  cl.classtime, cl.coachSubstitute,
       cl.applicantsceil, cl.applicantsfloor ,cl.price , r.classroomname, w.listid,w.wishaddsince 
from Classes cl JOIN Course co ON cl.courseid = co.courseid
                JOIN CourseCategories coc ON co.categoryid = coc.categoryid 
				JOIN Employee e ON cl.employeeid = e.employeeid 
				JOIN Classroom  r ON cl.classroomid = r.classroomid 
				JOIN Wishlist w ON cl.classid=w.classid

where w.memberid= 48 AND w.wishremovedate IS NULL 
order by cl.classdate;