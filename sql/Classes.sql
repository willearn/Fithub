use Fithub;

create table Classes(
	classid int not null primary key identity(1,1),
	courseid int not null references Course(courseid),
	classdate date not null,
	classtimesince nvarchar(20) not null,
	classtimeend nvarchar(20) not null,
	employeeid int not null references Employee(employeeid),
	applicantsceil int not null,
	applicantsfloor int not null,
	price int not null,
	classroomid int not null references Classroom(classroomid)
);