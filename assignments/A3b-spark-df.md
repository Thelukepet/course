---
layout: page
title: Assignment 3 Part B
description: Spark Data Frame API
---

## Nijmegen - Spark Data Frame API

The goal of assignment 3 part B is to gain hands-on experience with Spark SQL and DataFrames
by carrying out an analysis on structured open data.

Follow this link to the *Classroom for Github*
[**spark-2017** assignment](https://classroom.github.com/assignment-invitations/d74d229a25740c5743af699c18a1d93f),
login with your github account, and accept the assignment.

The repository for the assignment contains the course's notebook and two extra data files.

Before you start, I recommend to go briefly over the
[Spark SQL and DataFrame documentation](http://spark.apache.org/docs/latest/sql-programming-guide.html).


### Big Data Spark Nijmegen notebook

Copy the course's notebook

into the docker container in directory `/opt/docker/notebooks/BigData`
(just like you did in assignment 3 Part A).

Download the course's notebook 
[Big Data Nijmegen](http://rubigdata.github.io/course/assignments/BigData-Nijmegen.snb).

Open [localhost:9000/tree/BigData](http://localhost:9000/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

### Blog post 

The city of Nijmegen provides a variety of resources as open data at [nijmegen.nl/opendata](http://www.nijmegen.nl/opendata).

Our goal is to analyze these together - to investigate if we can say anything about the relation between population statistics in areas of the city and the activities that are organized there.

Specifically, we will integrate the following three data sets:

* _Evenementen Kalender_ `vBoaEvents_all.csv` and _Evenementen Locaties_ `vBoaEventsLoc_all.csv` for events and their locations
* _Adressen (BAG)_ `BAG_ADRES.csv` for streetnames and their quarters
* _Statistische data_ `opendata_stadsgetallen.accdb` for a variety of statistics about the population

_Note:_ For your convenience, I have put a copy of these data in the shared volume of the university computers, `/vol/practica/BigData`.

Analyze the data and try to answer questions about the different quarters in the city;
e.g., ask yourself which quarter has the highest income, which quarter has the largest number of young people,
where do the families live, etc. Can you determine the average age in the quarter where the highest number of
events take place?

Hints:
* BAG is the Dutch national "basic registration" of addresses and buildings
* You can match addresses in the different data sets by string or by their location (x,y)-coordinates, 
see the notebook examples for inspiration

Imagine writing about an open data project, where you want to explain how your
findings are backed up.

### Additional Instructions

#### Prepare container

Copy the *Nijmegen* data into directory `/data` in the docker container.

Download the course's notebook 
[Big Data Nijmegen](http://rubigdata.github.io/course/assignments/BigData-Nijmegen.snb) and copy
the `.snb` file into the `notebooks/Bigdata` folder.

```
mkdir -p /opt/docker/notebooks/BigData
cd /opt/docker/notebooks/BigData
wget http://rubigdata.github.io/course/assignments/BigData-Nijmegen.snb
```

The city distributes most data as CSV (Comma Seperated Values), but the statistics data only
in the Microsoft Access format. Let us install helper classes to deal with both formats,
by using open source tool `access2csv` for converting the Microsoft Access data.
Building the `jar` requires installing `ant` if your container does not have it yet:

```
apt-get install ant
git clone https://github.com/AccelerationNet/access2csv.git
cd access2csv
ant
```

How to use the conversion tool:

```
java -jar /opt/docker/notebooks/BigData/access2csv/access2csv.jar --help
```

E.g., to inspect the schema:

```
java -jar /opt/docker/notebooks/BigData/access2csv/access2csv.jar /data/opendata_stadsgetallen.accdb --schema
```

This gives a result like:
```
CREATE TABLE tbl_OPENDATA (
  WaardeId LONG,
  Waarde FLOAT,
  WaardetypeNaam TEXT,
  ThemaNaam TEXT,
  OnderwerpNaam TEXT,
  Labelgroepnaam TEXT,
  LabelNaam TEXT,
  GeografieType TEXT,
  GeografieOmschrijving TEXT,
  TijdOmschrijving TEXT,
  TijdType TEXT,
  BronOrganisatie TEXT,
  BronNaam TEXT,
)
```

Now convert the data to CSV to enable further analysis.

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

