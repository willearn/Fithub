use Fithub;

create table Announcement(
	annid int not null primary key identity(1,1),
	annname nvarchar(40) not null,
	anndescroption nvarchar(250) not null,
	annimg image,
	anndate date not null,
	anndisplay nvarchar(1) not null,
	employeeid int not null references Employee(employeeid),
	annon date,
	annoff date,
	annsort int
);