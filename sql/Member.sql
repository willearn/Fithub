use Fithub;

create table Member(
	memberid int not null primary key identity(1,1),
	memberphoneno varchar(25) not null,
	memberpassword varchar(50) not null,
	membername nvarchar(50) not null,
	membergender varchar(1) not null,
	memberemail varchar(50) not null,
	membercity nvarchar(20) not null,
	memberzone nvarchar(20) not null,
	memberaddress nvarchar(100) not null,
	memberbirthday date not null,
	memberaccountsince date not null
);