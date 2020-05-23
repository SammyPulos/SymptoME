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
--                 VALUES (TO_DATE('2020-05-05','YYYY-MM-DD'), 'Deb', 10, 0, 1, 1, 1, 1, 0, 1, 1, 0);


--SELECT reportDate, feelingRating FROM Reports WHERE username = 'Deb' AND reportDate <= TO_DATE('2020-05-23','YYYY-MM-DD') AND reportDate >= TO_DATE('2020-05-01','YYYY-MM-DD')