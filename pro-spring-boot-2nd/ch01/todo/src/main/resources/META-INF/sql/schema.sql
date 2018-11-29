create table todo (
  id varchar(36) not null,
  description varchar(255) not null,
  created timestamp,
  modified timestamp,
  completed boolean,
  primary key (id)
);


