use Fithub;

create table Notification(
	notificationid int not null primary key,
	subject nvarchar(50) not null,
	content nvarchar(100) not null
);