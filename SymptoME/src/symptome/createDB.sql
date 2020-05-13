-- drop index ReportsIndex;
-- drop table Reports;
-- drop index UsersIndex;
-- drop table Users;

-- create table Users (
--     username varchar(50) not null,
--     passhash varchar(50) not null,
--     zipCode  integer,
--     dob      date,
--     primary key(username)
-- );

--create index UsersIndex on Users(username);

-- create table Reports (
--     reportDate      date not null,
--     username        varchar(50) not null,
--     feelingRating   integer,
--     cough           integer,
--     diffBreathing   integer,
--     fever           integer,
--     musclePain      integer,
--     soreThroat      integer,
--     lostTasteSmell  integer,
--     goneOut         integer,
--     beenTested      integer,
--     testResult      integer,
--     primary key(reportDate, username),
--     foreign key(username) references Users(username)
--     on delete cascade
-- );

--create index ReportsIndex on Reports(reportDate);

select * from Reports;
--SELECT * FROM Users WHERE reportDate = TO_DATE('2020-05-12','YYYY-MM-DD')