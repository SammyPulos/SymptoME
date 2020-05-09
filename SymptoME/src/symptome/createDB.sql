-- drop index ReportsIndex;
--drop table Reports;
-- drop index UsersIndex;
--drop table Users;

-- create table Users (
--     username varchar(50) not null,
--     passhash varchar(50) not null,
--     zipCode  integer,
--     dob      varchar(50),
--     primary key(username)
-- );

--create index UsersIndex on Users(username);

-- create table Reports (
--     reportDate      varchar(50) not null,
--     username        varchar(50) not null,
--     cough           integer,
--     diffBreathing   integer,
--     fever           integer,
--     musclePain      integer,
--     soreThroat      integer,
--     lostTasteSmell  integer,
--     goneOut         integer,
--     feelingRating   integer,
--     beenTested      integer,
--     positiveResult  integer,
--     primary key(reportDate, username),
--     foreign key(username) references Users(username)
--     on delete cascade
-- );

--create index ReportsIndex on Reports(reportDate);

-- NOTE: inserted one user manually because I started with login flow rather than register flow
--insert into Users (username, passhash, zipCode, dob) values ('Deb', 'pass123', 95070, '12/20/80');