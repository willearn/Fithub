use Fithub;

create table Cart(
	cartid int not null primary key identity(1,1),
	memberid int not null references Member(memberid),
	classid int not null references Classes(classid)
);

ALTER TABLE Cart
ADD CONSTRAINT FK__Cart__Classes FOREIGN KEY (classid) REFERENCES Classes(classid);
