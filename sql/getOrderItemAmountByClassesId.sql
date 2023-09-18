USE FithubTest
GO

SELECT oi.classid, count(oi.orderid) orderAmount
  FROM OrderItem oi JOIN Orders o ON oi.orderid = o.orderid 
  WHERE oi.classid=22
  GROUP BY oi.classid;

GO


