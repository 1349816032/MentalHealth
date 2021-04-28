# create table if not exists manager(
# 	id varchar(11) primary key,
# 	password varchar (11)
#  );    #//管理员
#
#
# create table if not exists doctor(
#     dusername varchar(11) primary key,
# 	did varchar(18) not null,
# 	dpassword varchar(11) not null,
#     dname varchar(11) not null,
# 	dage int not null ,
# 	dgender varchar(8) not null,
# 	research_direction varchar(50) not null,
#     dtel varchar(13) ,
#     filed varchar(16),
#     title varchar(16)
# );     #//心理咨询师
#
insert into patient(pid,pname,page,pgender,diagnosis,ptel,time,occupation) values('430302199003304541','zz'
,22,'male','','18810637630','2021-3-23','学生');
# create table if not exists patient(
#     id int(10) primary key auto_increment,
#     pid varchar(18) not null unique ,
#     pname varchar(11) not null,
#     page int not null ,
# 	pgender varchar(8) not null,
# 	diagnosis varchar(256),
# 	ptel varchar(16),
# 	time date,
# 	occupation varchar(16)
# );   # //来访病人
#

#
#
# create table if not exists medical_record(
#     patient varchar(10) not null ,
#     doctor varchar(10) not null,
#     diagnose varchar(256) not null ,
#     record varchar(256) not null ,
#     date date not null ,
#     pid varchar(18)not null ,
#     did varchar(18) not null ,
#     id int primary key auto_increment
# );    # //就诊记录
#
#



select * from patient;

