USE FithubTest
GO

SELECT cr.classroomid
      ,cr.classroomname
	  ,cl.classid
	  ,cl.classdate
	  ,cl.classtime
	  ,r.rentorderid
	  ,r.rentdate
	  ,r.renttime

  FROM Classroom cr JOIN Classes cl ON (cr.classroomid=cl.classroomid)
                   JOIN RentOrder r ON (cr.classroomid= r.classroomid)
  WHERE r.classroomid=1 AND (cl.classdate=r.rentdate) AND (cl.classtime=r.renttime);
 
 
SELECT cl.classid
	  ,cl.classdate
	  ,cl.classtime
  FROM Classroom cr JOIN Classes cl ON (cr.classroomid=cl.classroomid)
                  
  WHERE cr.classroomid=1 AND (cl.classdate='2023-10-25') AND (cl.classtime='早上');
 

 SELECT r.rentorderid
	  ,r.rentdate
	  ,r.renttime

  FROM Classroom cr   JOIN RentOrder r ON (cr.classroomid= r.classroomid)
  WHERE r.classroomid=1 AND (r.rentdate='2023-10-20') AND (r.renttime='晚上') AND r.rentstatus='已付款' ;


GO


