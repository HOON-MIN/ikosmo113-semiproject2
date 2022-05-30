select * from user_tables;

create table hobby(
hobbynum number primary key,
hname varchar2(30)
);

create table amember (
membernum number primary key,
mid varchar2(30) unique ,
mpwd varchar2(30) not null,
mname varchar2(30) not null,
mloc varchar2(20) not null,
mhobby number not null,
mjumin varchar2(30) not null,
mdate date ,
gender varchar2(30) not null,
constraint amember_gender_ck check(gender = '��' or gender ='��') ,
constraint amember_mhobby_fk foreign key(mhobby) references hobby(hobbynum)
);

select * from amember;

create table agroup (
groupnum number primary key,
gname varchar2(20) not null,
ghobby number not null,
gloc varchar2(20) not null,
ginfo varchar2(200) not null,
gdate date ,
CONSTRAINT agroup_ghobby_fk foreign key(ghobby) references hobby(hobbynum)
);

create table ajoin (
membernum number,
groupnum number,
jdate date,
joinmember number,
constraint ajoin_pk_dual primary key(membernum, groupnum),
constraint ajoin_groupnum_fk foreign key(membernum, groupnum) references ajoin(membernum,groupnum)
);

drop table ajoin;

create table board (
boardnum number primary key,
membernum number ,
subject varchar2(50) ,
content varchar2(200) ,
groupnum number ,
bdate date,
wlist number,
constraint board_groupnum_fk foreign key(groupnum) references agroup(groupnum),
constraint board_membernum_fk foreign key(membernum) references amember(membernum)
);

create table boardcomm(
commnum number primary key,
boardnum number,
membernum number,
content varchar2(200),
cdate date,
constraint BOARDCOMM_MEMBERNUM_FK foreign key(membernum) references amember(membernum),
constraint BOARDCOMM_BOARDNUM__FK foreign key(boardnum) references board(boardnum)
);

create table chat (
cnum number primary key,
groupnum number ,
talk varchar2(200),
cdate date,
constraint chat_groupnum_fk foreign key(groupnum) references agroup(groupnum)
);

create sequence membernum_seq
increment by 1 start with 1;

create sequence groupnum_seq
increment by 1 start with 1;

create sequence chat_seq
increment by 1 start with 1;

create sequence board_seq
increment by 1 start with 1;

create sequence boardcomm_seq
increment by 1 start with 1;

--�׷캰 �Խñ��� ����
--gnum = groupnum

create or replace function getWlist(gnum number) return number is 
an number ;
begin 
select  count(*) into an from board where groupnum = gnum;
if an = null then an:=1;
end if;
return an +1;
end;
/
--�׷캰 ���Թ�ȣ
create or replace function gnum(gnum number) return number is 
an number ;
begin 
select  count(*) into an from ajoin where groupnum = gnum;
if an = null then an:=1;
end if;
return an +1;
end;
/
select * from hobby;

--�ֹι�ȣ�� ���� ��ȯ -function
create or replace function usergender(userjumin  varchar2) return varchar2 is
gender varchar2(10);
begin
select  decode(substr(userjumin,instr(userjumin,'-',1)+1,1),1,'��',3,'��','��') ���� into gender
 from dual;
 return gender;
end;
/
-- �׷쿡 ���Ե� ����� ���� ��ü ����
create or replace procedure p1(num in number, cur out SYS_REFCURsor)
is
begin
open cur for
select j.groupnum,j.jdate,m.mname,g.gname,m.mid,j.membernum,usergender(mjumin) gender  FROM amember m
JOIN ajoin j
ON m.membernum=j.membernum
JOIN agroup g
ON g.groupnum=j.groupnum where j.groupnum = num;
end;
/

-- ��ȣ��̼� ���ķ� �׷츮��Ʈ �޾ƿ���
create or replace procedure glist(hnum in number,cur out sys_refcursor) is
begin 
open cur for
select g.*,h.hname from agroup g,hobby h where g.ghobby = h.hobbynum order by decode(ghobby,hnum,1);
end;
/

-- ������ ���Ӹ�� Ȯ��
create or replace procedure ckgjlist(gnum in number,cur out sys_refcursor) is

begin 
open cur for
select membernum from ajoin where groupnum = gnum;
end;
/

create table amember_log(
lnum number primary key,
tablename varchar2(10),
dml_type varchar2(10),
membernum number,
mid varchar2(50),
ldate date
);

create sequence amember_log_seq;

create or replace trigger trg_amember_log
after
insert or update or delete on amember
for each row
begin
if inserting then 
insert into amember_log values(amember_log_seq.nextVal,'amember','insert',:new.membernum,:new.mid,sysdate);
elsif updating then
insert into amember_log values(amember_log_seq.nextVal,'amember','update',:old.membernum,:old.mid,sysdate);
elsif deleting then
insert into amember_log values(amember_log_seq.nextVal,'amember','update',:old.membernum,:old.mid,sysdate);
end if;
end;
/

select * from amember;
select * from hobby;
select * from agroup;
-- ��̰�(hobby) �Է�
insert into hobby values(1,'�ƿ�����/����');
insert into hobby values(2,'�/������');
insert into hobby values(3,'����/����');
insert into hobby values(4,'����/����');
insert into hobby values(5,'�丮/����');


-- ���Դ�� ���̺�
create table ajoin_delay(
gdnum number primary key,
membernum number,
groupnum number,
ddate date,
constraint ajoin_delay_fk1 foreign key(membernum) references amember(membernum),
constraint ajoin_delay_fk2 foreign key(groupnum) references agroup(groupnum)
);


--���� ��û,����,���� ��Ű��
create or replace package join_pkg is
procedure joindelay(mnum in number, grnum in number);
procedure accetjoin(mnum in number, grnum in number);
procedure denyjoin(mnum in number, grnum in number);
end;
/

-- ���Ӱ��Ժκ� ��Ű���� ����
create or replace package body join_pkg is
procedure joindelay(mnum in number,grnum in number) is
begin 
insert into ajoin_delay values(gdnum.nextVal,mnum,grnum,sysdate);
end;
procedure accetjoin(mnum in number, grnum in number) is
begin
insert into ajoin values(mnum,grnum,sysdate,gnum(grnum));
delete ajoin_delay where membernum = mnum and groupnum = grnum;
end;
procedure denyjoin(mnum in number, grnum in number)is
begin
delete ajoin_delay where membernum = mnum and groupnum = grnum;
end;
end;
/

--������� �ֹι�ȣ �־� ���� ��� �Լ�
create or replace function getage(jumin amember.mjumin%type) return number is
mage varchar2(100);
begin
select to_char(sysdate,'yyyy')-(decode(substr(jumin,8,1),'1','19','2','19','20')||substr(jumin,1,2)+1) into mage
from dual;
return to_number(mage);
end;
select * from ajoin;
select * from amember;




