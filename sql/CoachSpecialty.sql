use Fithub;

create table CoachSpecialty(
	coachspecialtyid int not null primary key identity(1,1),
	employeeid int not null references Employee(employeeid),
	specialtyid int not null references Specialty(specialtyid)
);