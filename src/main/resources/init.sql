create table user
(
  id int auto_increment,
  account_id varchar(100),
  name varchar(50),
  token varchar(36),
  gmt_create bigint,
  gmt_modify bigint,
  constraint user_pk
    primary key (id)
  );
