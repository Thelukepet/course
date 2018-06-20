---
layout: page
title: Course Information
tagline: RU Big Data 2018 (NWI-IBC036)
description: RU Big Data course information and overall structure
---

## Related Course Information

The course on
[Blackboard](http://bit.ly/RUBigDataBB-2018)
and in the
[course catalog](https://sis.ru.nl/osiris-student/OnderwijsCatalogusSelect.do?selectie=cursus&cursus=NWI-IBC036&collegejaar=2017)
or [here](http://www.ru.nl/studiegids/science/vm/osirislinks/ibc/nwi-ibc036/).

## Practical Assignments

### Objectives

The practical work for the course serves two main objectives:

1. Get to know the emerging Big Data platforms in more detail by using them, with a focus on data analysis using Spark.
2. Gain hands-on experience using modern development tools and services, including `git`, `github`, `docker`, `Jupyter notebooks`, *etc.*.

### Completing and Evaluating Assignments

In this course, we use *Github for education* to hand in **brief** reports (like a blog post) about the results of the 
practical work. Standard git functionality serves perfectly for carrying out peer review, and keeping track of 
modifications in response to feedback.

Refer to [assignment 1a](assignments/A1a-blogging.html) for more details about the blogging framework.

Doublecheck with [Blackboard announcement](https://blackboard.ru.nl/webapps/blackboard/execute/announcement?method=search&context=mybb&searchSelect=_115038_1) 
to avoid common mistakes.

### Getting help
Check out the [forum](https://github.com/rubigdata/forum-2018) to get help, or even help out your peers.
If you feel the forum is not the right place for your question you can contact one of the student assistents at
`wietse.kuipers AT student.ru.nl` or `J.martens AT student.ru.nl`

### Assignments Schedule

#### February 21st: Setup and first test blog

Completing the first assignment is not evaluated. However, I expect that every student will be able 
to create their assignment reports in Markdown and have these converted to HTML at their github page,
and know how to use Docker on their hardware of choice (be it a laptop, their home computer or
a PC in HG 00.075).

Usually, working with big data requires accessing a large cluster through a remote shell (`ssh`).
If you are not that familiar with using a computer from the commandline in a terminal, or struggle 
with UNIX commands, I recommend this [UNIX Tutorial for Beginners](http://www.ee.surrey.ac.uk/Teaching/Unix/);
nicely organised by topic. 

Once you have your template blogpost published and Docker installed, explore Scala using the links
in assignment 1B. Write up your experience in markdown, following 1A to publish those as a first blog post.

Instructions (assignments A1a and A1b):

* [Assignment 1a](assignments/A1a-blogging.html)
* [Assignment 1b](assignments/A1b-docker.html)

__Final words:__ _Practice makes perfect_.
 
Take your time to improve your CLI (CommandLine Interface) skills! 
Mastering UNIX is a steep learning curve, but the time invested pays back bigtime in the long run.

#### March 14th: Hadoop and Map-Reduce

_Extended deadline, in case you missed this one: March 21st. Only and **final** extension for this assignment._

The objective of assignment 2 is to gain some hands-on experience with HDFS.

The first week, we install HDFS in pseudo-distributed mode, and explore the default examples.
The subsequent weeks, you write your blog-post discussing your own Map Reduce jobs on your own cluster.

* Assignment 2 [description](assignments/A2-mapreduce.html) and [invite](https://classroom.github.com/a/H-Hs6uhw) (please accept only once).

_Enjoy the Elephant in the Room!_

#### April 25th: Spark

_I recommend to complete assignment 3A before the Mid-Term test._

The objective of assignment 3 is to gain hands-on experience with Spark.

Assignment 3A is designed to enhance your understanding of RDDs and how they are executed.
Assignment 3B helps you carry out a basic data analysis task using Spark Dataframes and/or Spark SQL.

* Assignment 3 [description](assignments/A3-spark.html) and [invite](https://classroom.github.com/a/hqXs_57o) (please accept only once).

#### May 23rd: Spark Streaming

The objective of assignment 4 is to gain hands-on experience with Spark Streaming.
* [Assignment 4](assignments/A4-streaming.html)

### May 28th - May 31st: BDR Challenge

This week starts with a guest lecture by Big Data Republic, which is accompanied by a mini-Hackaton that we complete May 31st.

More info:
+ [Blackboard announcment](https://blackboard.ru.nl/webapps/blackboard/execute/announcement?method=search&context=mybb&searchSelect=_115038_1)
+ BDR Assignment [repository](https://github.com/rubigdata/bdr-assignment)
+ BDR Assignment [FAQ](https://github.com/rubigdata/forum-2018/issues/17)

__Ideally, you complete the assignment at the end of the Thursday morning meeting. If you cannot attend or are too competitive to give up the
quest for the highest Recall@15, then you may complete the assignment on **Sunday, June 3rd, midnight** the latest.__

### _July 3rd_: Final project

The final project brings together everything we learned in the previous exercises: you will carry out a big data analysis on a large
Web crawl distributed by the CommonCrawl, on the national cluster `Hathi` (managed by SurfSara). 

A successful project faces a few differences from the way of work so far:

* Run Spark code standalone, outside the notebook interface;
* Working remote, using Kerberos for authentication, on a managed cluster with many different users;
* Scaling up your workload in multiple steps, to tackle the issues that you encounter one by one.

Instructions:

* [Final Project](assignments/P-commoncrawl.html)


