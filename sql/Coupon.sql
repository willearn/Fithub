use Fithub;

create table Coupon(
	couponid int not null primary key identity(1,1),
	couponname nvarchar(50) not null,
	couponstate int not null,
	coupongeneratedate date not null,
	couponenddate date not null,
	couponcatagoriesid int not null references CouponCatagories(couponcatagoriesid),
	couponcode varchar(20) not null,
	couponamount int not null default 0,
	coupondiscount int not null default 0,
	couponceil int not null,
	couponused int not null,
	couponthreshold int not null
);