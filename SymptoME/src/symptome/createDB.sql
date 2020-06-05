-- drop index ReportsIndex;
-- drop table Reports;
-- drop index UsersIndex;
-- drop table Users;

create table Users (
    username varchar(50) not null,
    passhash varchar(50) not null,
    zipCode  integer,
    dob      date,
    primary key(username)
);

--create index UsersIndex on Users(username);
 
create table Reports (
    reportDate      date not null,
    username        varchar(50) not null,
    feelingRating   integer,
    cough           integer,
    diffBreathing   integer,
    fever           integer,
    musclePain      integer,
    soreThroat      integer,
    lostTasteSmell  integer,
    goneOut         integer,
    beenTested      integer,
    testResult      integer,
    primary key(reportDate, username),
    foreign key(username) references Users(username)
    on delete cascade
);

--create index ReportsIndex on Reports(reportDate);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Dave', 'passola345', 93405, TO_DATE('1999-06-07','YYYY-MM-DD'));
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Dave';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult) 
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Dave', 4, 1, 1, 1, 0, 0, 0, 0, 1, 1);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Sandra', 'ballislife', 93405, TO_DATE('1970-06-07','YYYY-MM-DD')); 
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Sandra';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Sandra', 7, 1, 1, 1, 0, 0, 0, 0, 1, 1);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Liz', 'mypass123!', 95070, TO_DATE('1989-06-07','YYYY-MM-DD'));
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Liz';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult) 
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Liz', 9, 1, 0, 0, 0, 0, 0, 0, 1, 0);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Foad', 'testpass', 93405, TO_DATE('1960-06-07','YYYY-MM-DD')); 
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Foad';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Foad', 6, 1, 1, 1, 0, 0, 0, 0, 1, 1);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Linda', 'mypass123!', 95070, TO_DATE('1975-10-07','YYYY-MM-DD'));
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Linda';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult) 
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Linda', 9, 1, 0, 0, 0, 0, 0, 0, 0, 0);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Sally', 'mypass123!', 95070, TO_DATE('1975-10-07','YYYY-MM-DD'));
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Sally';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult) 
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Sally', 9, 1, 0, 0, 0, 0, 0, 0, 1, 1);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Sarah', 'testpass', 95070, TO_DATE('1960-06-07','YYYY-MM-DD')); 
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Sarah';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Sarah', 8, 1, 0, 1, 0, 0, 0, 0, 1, 1);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Bob', 'testpass', 95070, TO_DATE('1960-06-07','YYYY-MM-DD')); 
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Bob';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-05','YYYY-MM-DD'), 'Bob', 8, 1, 0, 1, 0, 0, 0, 0, 0, 0);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Sammy', 'abcdefg', 95070, TO_DATE('1995-09-10','YYYY-MM-DD')); 
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-05','YYYY-MM-DD') AND username ='Sammy';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-01','YYYY-MM-DD'), 'Sammy', 6, 1, 1, 1, 0, 0, 0, 0, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-04','YYYY-MM-DD'), 'Sammy', 2, 1, 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-03','YYYY-MM-DD'), 'Sammy', 3, 0, 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-02','YYYY-MM-DD'), 'Sammy', 3, 0, 1, 1, 1, 1, 1, 1, 1, 1);

INSERT INTO Users (username, passhash, zipCode, dob) VALUES ('Meg', 'testpass', 95070, TO_DATE('1994-12-19','YYYY-MM-DD')); 
DELETE FROM Reports WHERE reportDate = TO_DATE('2020-06-04','YYYY-MM-DD') AND username ='Meg';
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-04','YYYY-MM-DD'), 'Meg', 6, 1, 1, 1, 0, 0, 0, 0, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-03','YYYY-MM-DD'), 'Meg', 5, 1, 1, 1, 0, 0, 0, 0, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-02','YYYY-MM-DD'), 'Meg', 7, 1, 1, 1, 0, 0, 0, 0, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-06-01','YYYY-MM-DD'), 'Meg', 6, 1, 1, 1, 0, 0, 0, 0, 1, 1);
INSERT INTO Reports (reportDate, username, feelingRating, cough, diffBreathing, fever, musclePain, soreThroat, lostTasteSmell, goneOut, beenTested, testResult)
VALUES (TO_DATE('2020-05-19','YYYY-MM-DD'), 'Meg', 6, 1, 1, 1, 0, 0, 0, 0, 1, 1);

select * from Users;