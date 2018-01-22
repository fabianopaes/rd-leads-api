# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table CONTACT (
  id                        bigint not null,
  name                      varchar(255),
  Version                   integer not null,
  constraint pk_CONTACT primary key (id))
;

create sequence CONTACT_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists CONTACT;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists CONTACT_seq;

