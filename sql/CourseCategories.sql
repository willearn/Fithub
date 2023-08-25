use Fithub;

create table CourseCategories(
	categoryid int not null primary key identity(1,1),
	categoryname nvarchar(30) not null
);