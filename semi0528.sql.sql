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
constraint ajoin_groupnum_fk foreign key(groupnum) references agroup(groupnum),
constraint ajoin_membernum_fk foreign key(membernum) references amember(membernum)
);

create table hobby(
hobbynum number primary key,
hname varchar2(30)
);

create table board (
boardnum number primary key,
membernum number ,
subject varchar2(50) ,
content varchar2(200) ,
groupnum number ,
bdate date,
wlist number,
constraint board_groupnum_fk foreign key(groupnum) references ajoin(groupnum),
constraint board_membernum_fk foreign key(membernum) references ajoin(membernum)
);

create table boardcomm(
commnum number primary key,
boardnum number,
membernum number,
content varchar2(200),
cdate date,
constraint BOARDCOMM_MEMBERNUM_FK foreign key(membernum) references ajoin(membernum),
constraint BOARDCOMM_BOARDNUM__FK foreign key(boardnum) references board(boardnum)
);

create table chat (
cnum number primary key,
groupnum number ,
cdate date,
constraint chat_groupnum_fk foreign key(groupnum) references ajoin(groupnum)
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

/*1	�ƿ�����/����
2	�/������
3	����/����
4	����/����
5	�丮/����
*/