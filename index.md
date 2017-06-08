---
layout: page
title: Course Information
tagline: RU Big Data 2017 (NWI-IBC036-2016)
description: RU Big Data course information and overall structure
---

## Related Course Information

The course on Blackboard:
[Blackboard](http://bit.ly/RUBigDataBB-2017)

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

### Development environment

Development environment customization: more next week.

### Assignments Schedule

#### February 23rd: Setup blogs

Completing the first assignment is not evaluated. However, I expect that every student will be able 
to create their assignment reports in Markdown and have these converted to HTML at their github page,
and know how to start Spark Notebook on their hardware of choice (be it a laptop, their home computer or
a PC in HG 00.137).

Instructions (assignment A1a):

* [Assignment 1a](assignments/A1a-blogging.html)

#### March 8th: Setup Spark-notebook

The rest of the course depends on a working Spark-notebook setup.
Follow the instructions and get yourself acquainted with Docker and Vagrant.

Once you have the Spark-notebook in your browser, you can practice your Scala by issuing
commands in a newly started notebook.

Instructions (assignment A1b):

* [Assignment 1b](assignments/A1b-docker.html)
* [Scala environment](background/scala.html) __(optional)__

#### March 23rd: Map-Reduce

The assignment is to write a blog post reflecting on using HDFS and running simple Map-Reduce programs.
Hands-on experience with using Hadoop Map-Reduce will help you appreciate the advantages of Spark for the
subsequent lab sessions :-)

_This assignment will be evaluated using peer review._

Instructions (assignment 2):

* [Assignment 2](assignments/A2-mapreduce.html)
* History of [command-line instructions](background/exact.html) for assignment 2 __(Thanks Wietse)__

#### April 10th: Peer Review assignment 2

Every student reviews the assignment 2 blog for two of their peers;
follow the [instructions for the peer review](peer-review.html).

#### May 8th: Spark

Instructions:

* [Assignment 3](assignments/A3-spark.html)
* [Assignment 3A](assignments/A3a-spark-rdd.html)
* [Assignment 3B](assignments/A3b-spark-df.html)

#### June 29th / July 2nd: CommonCrawl

The 4th assignment is the final project for the course. The assignment is to write a blog post about running
an analysis over the full CommonCrawl on the SurfSara cluster.

The assignment is open-ended, you can decide for yourself what you want to do. My recommendation is however 
to take it one step at a time - start with something that seems incredibly simple, as the data is __REALLY__ huge 
and it is likely that you will run into many small and perhaps even some serious issues in carrying out your 
ideas. So, again, start easy!

I scheduled a slot for presentating your progress on __June 29th__ starting __12.30__, in __HG 00.616__.

The official deadline for the blogpost is July 2nd, but provided that you have 1. a _clear plan_ and 2. initial 
results on small amounts of data, yet you run into problems completing your project on the cluster, 
I promise to be flexible with granting an extension so you can discuss results on the full crawl.

* [Assignment 4](assignments/A4-commoncrawl.html)

