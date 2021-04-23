create schema if not exists hospital_inventory;
use hospital_inventory;

create table if not exists equipment_location (
    id int not null auto_increment primary key,
    description varchar(40) not null,
    location varchar(40) not null
);