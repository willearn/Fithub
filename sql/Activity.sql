use Fithub;

create table Activity(
	activityid int not null primary key identity(1,1),
	activityname nvarchar(40) not null,
	activitydescription nvarchar(500) not null,
	activitydate date not null,
	activityurl varchar(255) not null,
	activitydisplay nvarchar(1) not null,
	employeeid int not null references Employee(employeeid),
	activityon date,
	activityoff date,
	activitysort int
);