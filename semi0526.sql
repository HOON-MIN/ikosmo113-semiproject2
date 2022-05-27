create table  amember(
membernum number primary key,
mid varchar2(100) not null,
mpwd varchar2(100) not null,
mname varchar2(100) not null,
mloc varchar2(100) not null,
mhobby number not null,
mjumin varchar2(100) not null,
mdate date,
constraint member_mid_uk unique(mid)
);

select * from amember;
drop table amember;
drop table agroup;
drop table ajoin;


--시퀀스
create sequence membernum_seq
increment by 1 start with 1;

create sequence groupnum_seq
increment by 1 start with 1;
drop sequence membernum_seq;
drop sequence groupnum_seq;
--agroup
create table agroup(
groupnum number primary key,
gname varchar2(100) not null,
ghobby number not null,
gloc varchar2(50) not null,
ginfo varchar2(100),
gdate date
);

-- 모임 가입 테이블
create table ajoin(
membernum number,
groupnum number,
jdate date,
CONSTRAINT ajoin_PK_dual 
PRIMARY KEY (membernum, groupnum)
);
alter table ajoin add constraint ajoin_groupnum_fk foreign key(groupnum) references agroup(groupnum);
alter table ajoin add constraint ajoin_membernum_fk foreign key(membernum) references amember(membernum);
-------------------------------

--group더미
insert into agroup values(groupnum_seq.nextVal,'소모임1',1,'서울','안녕하세요1',sysdate);
insert into agroup values(groupnum_seq.nextVal,'소모임2',2,'서울','안녕하세요2',sysdate);
insert into agroup values(groupnum_seq.nextVal,'소모임3',3,'서울','안녕하세요3',sysdate);
insert into agroup values(groupnum_seq.nextVal,'소모임4',4,'서울','안녕하세요4',sysdate);
insert into agroup values(groupnum_seq.nextVal,'소모임5',5,'서울','안녕하세요5',sysdate);
commit;
--join 더미
insert into ajoin values((select membernum from amember where membernum = 1),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 1),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 3),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 4),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 8),(select groupnum from agroup where groupnum =2),sysdate);
insert into ajoin values((select membernum from amember where membernum = 7),(select groupnum from agroup where groupnum =2),sysdate);
commit;
select * from amember;
--모임 가입원 보기
SELECT m.membernum,m.mid,m.mname,g.gname FROM amember m
JOIN ajoin j
ON m.membernum=j.membernum
JOIN agroup g
ON g.groupnum=j.groupnum where g.groupnum = 2;
-----------------------------------------------------------------------------
--모임리스트 
select * from agroup;
create or replace procedure glist(hobby in number,cur out sys_refcursor) is
begin 
open cur for
select *from agroup order by decode(ghobby,hobby,1);
end;
/

create function f1(jumin in amember.mjumin%type)
is
return number
begin
return
end;
/
SELECT EXTRACT(YEAR FROM SYSDATE) -
            (DECODE(SUBSTR(mjumin,8,1),'1', '19','2','19','20') || SUBSTR(mjumin,1,2)) +1  as "현재나이"
FROM  (SELECT mjumin FROM amember);

create or replace procedure p1(num in number, cur out SYS_REFCURsor)
is
begin
open cur for
select j.groupnum,m.mname,g.gname  FROM amember m
JOIN ajoin j
ON m.membernum=j.membernum
JOIN agroup g
ON g.groupnum=j.groupnum where j.groupnum = num;
end;
/

union all 
select m.membernum,m.mid,m.mname,g.gname , j.groupnum FROM amember m
JOIN ajoin j
ON m.membernum=j.membernum
JOIN agroup g
ON g.groupnum=j.groupnum where not j.groupnum = 1;


select * from amember;
delete from amember;
delete from agroup;
commit;

select m.mname , m.mhobby  from amember m , agroup g where m.mhobby = g.ghobby and m.mhobby = 1 
union all
select m.mname , m.mhobby  from amember m , agroup g where not m.mhobby = 1;

alter table ajoin add joinmembernum number;

select * from ajoin;


update ajoin set joinmembernum = 1 where groupnum =3 and membernum=2 ;

insert into agroup values(groupnum_seq.nextVal,'소모임',1,'서울','안녕하세요',sysdate);
insert into agroup values(groupnum_seq.nextVal,'코모임2',3,'서울','안녕하세요','2022-05-10');
insert into agroup values(groupnum_seq.nextVal,'동모임3',2,'부천','반갑습니다','2020-07-02');
insert into agroup values(groupnum_seq.nextVal,'우모임4',1,'인천','방가방가','2021-06-23');
insert into agroup values(groupnum_seq.nextVal,'조모임5',4,'경기','오하요','2022-02-12');
insert into agroup values(groupnum_seq.nextVal,'찐모임6',4,'부천','곰방와','2020-02-01');
commit;
select*from agroup;
select*from amember;


drop sequence membernum_seq;
drop sequence groupnum_seq;


insert into ajoin values((select membernum from amember where membernum = 1),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 5),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 3),(select groupnum from agroup where groupnum =1),sysdate);
insert into ajoin values((select membernum from amember where membernum = 4),(select groupnum from agroup where groupnum =2),'2022-04-20');
insert into ajoin values((select membernum from amember where membernum = 10),(select groupnum from agroup where groupnum =2),sysdate);
insert into ajoin values((select membernum from amember where membernum = 2),(select groupnum from agroup where groupnum =3),'2022-05-01');
commit;

select * from amember;
select * from agroup;

alter table amember add gender varchar2(50);
select * from ajoin;

create table board(
boardnum number,
membernum number not null,
subject varchar2(100) not null,
content varchar2(100) not null,
groupnum number not null ,
bdate date,
constraint board_boardnum_pk primary key(boardnum),
constraint board_groupnum_fk foreign key(groupnum) references agroup(groupnum),
constraint board_membernum_fk foreign key(membernum) references amember(membernum)
);
select * from boardcomm;

select b.boardnum,b.subject,a.mname,b.bdate from board b, amember a where b.membernum = a.membernum and groupnum = 1;

select b.boardnum,b.subject,a.mname,b.content from board b, amember a where b.membernum = a.membernum and b.boardnum = 1;
commit;
drop table board;
drop table boardcomm;
drop sequence board_seq;
drop sequence boardcomm_seq;
desc board;
desc boardcomm;


create table boardcomm(
commnum number, 
boardnum number not null, 
membernum number not null,
content varchar2(100) not null,
cdate date,
constraint boardcomm_commnum_pk primary key(commnum),
constraint boardcomm_membernum_fk foreign key(membernum) references amember(membernum),
constraint boardnum__fk foreign key(boardnum) references board(boardnum) on delete cascade
);

작성자 랑 내용 

select a.mname,bc.content ,bc.cdate 
    from amember a , boardcomm bc 
    where bc.membernum = a.membernum 
    and bc.boardnum = 1;

select * from boardcomm;

select a.mname,bc.content ,bc.cdate from amember a , boardcomm bc where bc.membernum = a.membernum and bc.boardnum = 1;

insert into boardcomm values(boardcomm_seq.nextVal,1,12,'내용컨텐츠','21-05-10');
delete from board where boardnum=1;
select * from boardcomm;
drop sequence board_seq;
desc boardcomm;

select a.mname,bc.content ,bc.cdate 
    from amember a , boardcomm bc 
    where bc.membernum = a.membernum 
    and bc.boardnum = 1
    order by cdate asc;


create sequence board_seq
increment by 1
start with 1;

create sequence boardcomm_seq
increment by 1
start with 1;
select *from amember;
insert into amember values(membernum_seq.nexVal,ksw,123,'박태진','서울',3,'940206-1122321',sysdate,userInfo('940206-1122321'));
select * from boardcomm;

select *  FROM amember m
JOIN ajoin j
ON m.membernum=j.membernum
JOIN agroup g
ON g.groupnum=j.groupnum ;

--amember 더미
insert into amember values(membernum_seq.nextVal,'tae1','1234','박태진','서울',1,'940206-1055555',sysdate,usergender('940206-1055555'));
insert into amember values(membernum_seq.nextVal,'tae2','1234','이태진','서울',1,'941231-2055555',sysdate,usergender('941231-2055555'));
insert into amember values(membernum_seq.nextVal,'tae3','1234','김태진','서울',2,'970523-1255555',sysdate,usergender('970523-1255555'));
insert into amember values(membernum_seq.nextVal,'tae4','1234','최태진','서울',3,'921231-1055555',sysdate,usergender('921231-1055555'));
insert into amember values(membernum_seq.nextVal,'tae5','1234','임태진','서울',4,'021231-3055555',sysdate,usergender('021231-3055555'));
insert into amember values(membernum_seq.nextVal,'tae6','1234','조태진','서울',3,'041231-4055555',sysdate,usergender('041231-4055555'));
insert into amember values(membernum_seq.nextVal,'tae7','1234','구태진','서울',3,'941231-2055555',sysdate,usergender('941231-2055555'));
insert into amember values(membernum_seq.nextVal,'tae8','1234','왕태진','서울',1,'901105-3055555',sysdate,usergender('901105-3055555'));
insert into amember values(membernum_seq.nextVal,'tae9','1234','윤태진','서울',2,'910127-2055555',sysdate,usergender('910127-2055555'));
insert into amember values(membernum_seq.nextVal,'tae10','1234','길태진','서울',4,'991231-1055555',sysdate,usergender('991231-1055555'));
commit;