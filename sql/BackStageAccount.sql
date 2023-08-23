use Fithub;

create table BackStageAccount(
	employeeid int not null references Employee(employeeid),
	employeeaccount varchar(50) not null primary key,
	employeepassword varchar(50) not null,
	loa int not null

);