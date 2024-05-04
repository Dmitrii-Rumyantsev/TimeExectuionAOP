CREATE TABLE method
(
    method_id   serial  primary key,
    class_name  varchar(255) not null,
    method_name varchar(255) not null
);
CREATE TABLE time_exectuion
(
    time_id    serial primary key,
    start_time timestamp not null,
    end_time   timestamp not null,
    execution bigint not null,
    is_complete boolean not null,
    method_id  bigint           not null,
    foreign key (method_id) references method (method_id)
);

CREATE TABLE account(
    id serial primary key ,
    full_name varchar(255) not null ,
    data_of_birth DATE not null ,
    phone varchar(255) not null,
    email varchar(255) not null
);
insert into method (method_id,class_name, method_name) values (1,'dsd','dsd');

select * from method;
select * from time_exectuion;
select * from account;

SELECT m.class_name, m.method_name, te.start_time, te.end_time, te.execution
FROM method m
 INNER JOIN time_exectuion te ON m.method_id = te.method_id;

drop table method cascade ;
drop table account cascade ;
drop table time_exectuion cascade ;
