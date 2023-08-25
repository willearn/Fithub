use Fithub;

create table ClassesSetItem(
	
	ClassesSeItemtID int not null primary key identity(1,1),
	classesSetId int not null references classesSet(classesSetId),
	classid int not null references Classes(classid)
);