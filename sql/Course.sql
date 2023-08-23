use Fithub;

create table Course(
	courseid int not null primary key identity(1,1),
	coursename nvarchar(30) not null,
	categoryid int not null references CourseCategories(categoryid),
	courseimgpath nvarchar(100) ,
	coursedescription nvarchar(250) 
);