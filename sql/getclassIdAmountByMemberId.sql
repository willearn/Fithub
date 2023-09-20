USE FithubTest

GO

SELECT * 
FROM Orders o JOIN OrderItem oi ON (o.orderid=oi.orderid)
WHERE o.memberid=46 AND o.orderstate IN (1,3) AND oi.classid=11 ;

GO

SELECT o.memberid, oi.classid , COUNT(oi.classid) alreadyBuyAmount
FROM Orders o JOIN OrderItem oi ON (o.orderid=oi.orderid)
WHERE o.memberid=46 AND o.orderstate IN (1,3) AND oi.classid=11 
GROUP BY oi.classid ,o.memberid;

GO