use Fithub;

create table VerificationCode(
	email nvarchar(50) not null primary key,
	verificationcode nchar(6) not null,
	codegeneratedate datetime2 not null
);