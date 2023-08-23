use Fithub;

create table ActivityPic(
 apicid int not null primary key,
 activityid int not null references Activity(activityid),
 apicsort int not null,
 apicpath nvarchar(100) not null,
);
