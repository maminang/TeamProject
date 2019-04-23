select * from tbl_member

update tbl_member set grade=0 where id='dingding'

update tbl_member set 
if(point>1)
point=point+1 where id=?

select * from tbl_comment_final
select * from tbl_yadong
select * from tbl_final2
Select * from tbl_ipcount2
Select * from tbl_ipcount

alter table tbl_

CREATE TABLE tbl_comment_final (
	cnum number(5) primary key,
	content varchar2(3000),
	writer varchar2(30) not null,
	writedate date default sysdate,
	reproot number(4),
	repstep number(4),
	repindent number(4),
	good number,
	bad number,
	num number(5),
	constraint tbl_comment_final_num_fk 
	foreign key(num) references tbl_final(num)
)

create table tbl_final(
num number(5) primary key, 
title varchar2(50) not null,
content varchar2(3000),
writer varchar2(30) not null,
writedate date default sysdate,
readcnt number default 0,
RepRoot number(4),
RepStep number(4),
RepIndent number(4),
good number default 0,
bad number default 0,
grade number(1),
up_num number(1) default 0,
up_adress varchar2(100)
);

alter table tbl_final add ( youtube varchar2(50) );

CREATE TABLE tbl_comment_board1 (
	cnum number(5) primary key,
	content varchar2(3000),
	writer varchar2(30) not null,
	writedate date default sysdate,
	reproot number(4),
	repstep number(4),
	repindent number(4),
	good number,
	bad number,
	num number(5),
	constraint tbl_comment_board1_num_fk 
	foreign key(num) references tbl_board1(num)
)


ALTER TABLE tbl_ipcount4 RENAME COLUMN contentnum TO ipcontentnum;
commit							
select * from tbl_final2
select * from tbl_ipcount2
select * from tbl_comment_final2




SELECT * FROM 
(SELECT rownum rnum,num,title,content,writer,to_char(writedate, 'yyyy/mm/dd') writedate,readcnt,reproot,repstep,repindent,good,bad,grade,up_num,up_adress,count FROM 
(SELECT A.*, 
(SELECT COUNT(*) FROM tbl_comment_board1 C WHERE C.num = A.num) count FROM tbl_board1 A ORDER BY reproot DESC, repStep ASC)) 
WHERE rnum BETWEEN ? AND ?

ALTER TABLE tbl_board1 MODIFY up_adress varchar2(200)\

CREATE TABLE tbl_member (
id varchar2(15) primary key,
pw varchar2(15) not null,
name varchar2(21) not null,
nick varchar2(30) not null,
birth date not null,
email varchar2(320),
phone number(20),
grade number(1) default 1,
point number(10) default 0,
pfa varchar2(200)
)

select * from tbl_member

update tbl_member set grade = 9 where id = 'NamepeN'

select * from view_all

select count(num) from view_all where to_char(writedate , 'yyyy-mm') like '2019-03'
