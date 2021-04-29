create table tribbles(
	tribble_id serial primary key,
	tribble_name text,
	lab_id integer references labs (lab_id)
);

create table labs(
	lab_id serial primary key,
	lab_name text
);

select * from labs order by lab_id

select * from tribbles

drop table labs

select * from tribbles join labs on tribbles.lab_id = labs.lab_id where tribbles.lab_id = 2

select labs.lab_id, labs.lab_name, tribbles.tribble_id, tribbles.tribble_name from labs left join tribbles on tribbles.lab_id = labs.lab_id

insert into tribbles values (default, 'Test', 2)

delete from labs where lab_id = 2