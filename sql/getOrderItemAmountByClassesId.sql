USE FithubTest
GO

SELECT oi.classid, cl.applicantsceil , count(oi.orderid) orderAmount
  FROM OrderItem oi JOIN Classes cl ON oi.classid=cl.classid
  
  WHERE oi.classid=22
  GROUP BY oi.classid , cl.applicantsceil;

GO


