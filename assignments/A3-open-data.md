---
layout: page
title: Assignment 3
description: RDDs and DataFrames
---

## RDDs and DataFrames

The goal of assignment 3 is to analyze some open data provided by the
city of Nijmegen using Spark.

Start the docker container (refer to [assignment A1.b](A1b-docker.html)
and the [Spark Notebook background](../background/spark-notebook.html)
if necessary).

### Prepare container

Copy the *Nijmegen* data provided at 
[`/vol/practica/BigData/TODO`](file:///vol/practica/BigData/TODO)
into directory `/data` in the docker container.


### Big Data Spark 101 notebook

Download the course's notebook 
[Big Data Spark 101](http://rubigdata.github.io/course/assignments/big-data-TODO) and copy
the `.snb` file into the `notebooks/Bigdata` folder.

```
mkdir -p /opt/docker/notebooks/BigData
cd /opt/docker/notebooks/BigData
wget http://rubigdata.github.io/course/assignments/big-data-TODO.snb
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





Open [localhost:9000/tree/BigData](http://localhost:9000/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

Take a look at the fragments of example code in the notebook.

Then analyze the data and try to answer the question
TODO

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

### Blog post

Imagine a reader who has heard about Spark and open data, and wants to read along
with your exploration of the open data provided by the city of Nijmegen.

Implement your analyses both using basic RDDs and using the DataFrames API.

Try to explain by looking at the query plans what types of optimizations the DataFrames API provides
when compared to creating your own code on basic RDDs.
