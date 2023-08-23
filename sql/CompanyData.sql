use Fithub;

create table CompanyData(
	companyname nvarchar(20) not null,
	logoimg image not null,
	companymap varchar(255) not null,
	companyphoneno varchar(15) not null,
	companycity nvarchar(10) not null,
	companyzone nvarchar(10) not null,
	companyaddress nvarchar(100) not null,
	companyemail varchar(50) not null,
	officerhour varchar(11) not null,
	vatnumber char(8) not null,
	companysince date not null,
	companyabout nvarchar(300) not null,
	companyintroduction nvarchar(100) not null
);