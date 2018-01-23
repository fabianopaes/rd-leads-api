# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table CONTACT (
  id                        bigint not null,
  CREATED                   timestamp,
  UPDATED                   timestamp,
  email                     varchar(255) not null,
  Version                   integer not null,
  constraint pk_CONTACT primary key (id))
;

create table PAGE (
  id                        bigint not null,
  CREATED                   timestamp,
  UPDATED                   timestamp,
  URL                       varchar(255),
  CONTACT_ID                bigint,
  ACCESS_TIMESTAMP          timestamp,
  Version                   integer not null,
  constraint pk_PAGE primary key (id))
;

create sequence CONTACT_seq;

create sequence PAGE_seq;

alter table PAGE add constraint fk_PAGE_contact_1 foreign key (CONTACT_ID) references CONTACT (id) on delete restrict on update restrict;
create index ix_PAGE_contact_1 on PAGE (CONTACT_ID);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists CONTACT;

drop table if exists PAGE;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists CONTACT_seq;

drop sequence if exists PAGE_seq;

