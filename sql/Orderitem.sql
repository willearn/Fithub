use Fithub;

create table Orderitem(
	orderid int not null references Orders(orderid),
	itemid int not null primary key identity(1,1),
	classid int not null references Classes(classid)
);

ALTER TABLE orderitem
ADD CONSTRAINT fk_Coupon_Id FOREIGN KEY (couponid) REFERENCES coupon(couponid);

ALTER TABLE orderitem
ADD CONSTRAINT FK_Orderitem_classes FOREIGN KEY (classid) REFERENCES Classes(classid);
