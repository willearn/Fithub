use Fithub;

create table ClassesSet(
	classesSetId int not null primary key identity(1,1),
	couponId int not null references Coupon(couponId),
	classesSetName nvarchar(50) not null,
	classesDescription nvarchar(250)
);