use Fithub;

create table Employee(
	employeeid int not null primary key identity(1,1),
	employeename nvarchar(50) not null,
	employeeemail varchar(50) not null,
	employeephone varchar(25) not null,
	employeegender nvarchar(1) not null,
	employeecity nvarchar(20) not null,
	employeezone nvarchar(20) not null,
	employeeaddress nvarchar(100) not null,
	deptid int not null references Department(deptid),
	employeetitle nvarchar(20) not null,
	manager int,
	hiredate date not null,
	resigndate date,
	salary int not null,
	employeebirthday date not null,
	employeeintroducetion nvarchar(150)
);