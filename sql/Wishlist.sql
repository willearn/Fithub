use Fithub;

create table Wishlist(
	listid int not null primary key identity(1,1),
	memberid int not null references Member(memberid),
	classid int not null references Classes(classid),
	wishaddsince date not null,
	wishremovedate date
);