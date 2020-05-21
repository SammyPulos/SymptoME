-- drop index ReportsIndex;
-- drop table Reports;
-- drop index UsersIndex;
-- drop table Users;
-- 
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
--  INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
--                 VALUES (TO_DATE('2020-05-13','YYYY-MM-DD'), 'Deb', 2, 0, 1, 1, 1, 1, 0, 1, 1, 0);



SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND (username = 'Zac' OR username = 'Zan' OR username = 'Gab' OR username = 'John' OR username = 'izzy' OR username = 'K' OR username = 'BB' OR username = 'h' OR username = 'Jan' OR username = 'Liz')
