CREATE TABLE method(
   method_id bigint  primary key ,
    class_name varchar(255) not null ,
    method_name varchar(255) not null
);
CREATE TABLE time_exectuion(
    time_id bigint primary key ,
    start_time double precision not null ,
    end_time double precision not null ,
    date date not null ,
    method_id bigint not null,
    foreign key (method_id) references method(method_id)
);