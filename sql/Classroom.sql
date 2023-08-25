use Fithub;

create table Classroom(
	classroomid int not null primary key identity(1,1),
	classroomlocation nvarchar(50) not null,
	classroomcapacity int not null
);