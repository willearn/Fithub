use Fithub;

create table Classes(
	classid int not null primary key identity(1,1),
	courseid int not null references Course(courseid),
	classdate date not null,
	classtime nvarchar(20) not null,
	coachsubstitute int not null default 0,
	employeeid int not null references Employee(employeeid),
	applicantsceil int not null,
	applicantsfloor int not null,
	price int not null,
	classroomid int not null references Classroom(classroomid)
);