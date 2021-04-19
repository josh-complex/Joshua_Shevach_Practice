create schema if not exists tasker_test;
use tasker_test;

create table if not exists task (
	task_id int not null auto_increment primary key,
    task_description varchar(255) not null,
    create_date date not null,
    due_date date not null,
    category varchar(50)
);