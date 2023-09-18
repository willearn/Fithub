GO

SELECT e.employeeid
      ,e.employeename  
      ,j.jobtitleid
	  ,j.jobtitlename
	  ,cl.classid
	  ,cl.classdate
	  ,cl.classtime

  FROM Employee e JOIN JobTitle j ON (e.jobtitleid=j.jobtitleid)
                  JOIN Classes cl ON (e.employeeid=cl.employeeid)
  WHERE jobtitlename='教練' AND cl.classdate BETWEEN '2023-09-01' AND '2023-10-31';

  GO

  SELECT e.employeeid
      ,e.employeename  
      ,j.jobtitleid
	  ,j.jobtitlename
	  ,cl.classid
	  ,cl.classdate
	  ,cl.classtime

  FROM Employee e JOIN JobTitle j ON (e.jobtitleid=j.jobtitleid)
                  JOIN Classes cl ON (e.employeeid=cl.employeeid)
  WHERE jobtitlename='教練' AND cl.classdate = '2023-09-01' AND cl.classtime='早上' ;

  GO

  SELECT e.employeeid
      ,e.employeename  
	  ,cl.classid

  FROM Employee e JOIN Classes cl ON (e.employeeid=cl.employeeid)
  WHERE e.employeeid=4 AND  cl.classdate = '2023-09-01' AND cl.classtime='早上' ;

   GO

