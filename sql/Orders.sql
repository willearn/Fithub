use Fithub;

create table Orders(
	orderid int not null primary key identity(1,1),
	orderfate date not null,
	ordercondition nvarchar(15) not null,
	memberid int not null references Member(memberid),
	ordertotalamount int not null,
	orderpaymentmethod nvarchar(15) not null,
	orderstate int not null
);