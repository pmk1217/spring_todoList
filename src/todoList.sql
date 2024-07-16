drop table todo;
drop table todouser;

create table todouser (
    userid varchar2(20) primary key,
    userpwd varchar2(20) not null,
    username varchar(20) not null
);

insert into todouser (userid, userpwd, username) values ('admin', '1111', 'min');
insert into todouser (userid, userpwd, username) values ('test', '1111', 'ddd');
insert into todouser (userid, userpwd, username) values ('min', '1111', '민');

CREATE SEQUENCE todo_seq
  START WITH 1
  INCREMENT BY 1
  NOMAXVALUE;

CREATE TABLE todo (
    todoseq NUMBER PRIMARY KEY,
    userid VARCHAR2(20) NOT NULL,
    title VARCHAR2(30) NOT NULL,
    content VARCHAR2(30) NOT NULL,
    regdate DATE DEFAULT SYSDATE NOT NULL,
    deadline DATE,
    result VARCHAR2(10) DEFAULT 'pend' CHECK (result IN ('complete', 'faulure', 'pend')),
    CONSTRAINT fk_userid FOREIGN KEY (userid) REFERENCES todouser(userid) ON DELETE CASCADE
);

INSERT INTO todo (todoseq, userid, title, content, deadline, result)
VALUES (todo_seq.nextval, 'min', '잠자기', '반드시 한다', TO_DATE('2099-05-01', 'YYYY-MM-DD'), 'complete');

commit;