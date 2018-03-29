---
layout: page
title: Assignment 3 Part B
description: Spark Data Frame API
---

## Nijmegen - Spark Data Frame API

The goal of assignment 3 part B is to gain hands-on experience with Spark SQL and DataFrames
by carrying out an analysis on structured open data.

The repository for the assignment contains the course's notebooks.

### Big Data Spark Nijmegen notebook

Copy the course's notebook from your assignment 3 repo, or from the course repository
([`BigData-open-data-Nijmegen.snb`](https://raw.githubusercontent.com/rubigdata/course/gh-pages/assignments/BigData-open-data-Nijmegen.snb))
into the docker container in directory `/opt/docker/notebooks/BigData`
(just like you did in assignment 3 Part A).

Open [localhost:9001/tree/BigData](http://localhost:9001/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

_You can [view the course notebook](https://github.com/rubigdata/spark-2018/blob/master/BigData-open-data-Nijmegen.snb.ipynb)
in your browser as (imperfectly) rendered by Github UI._

Follow the instructions in the notebook, and do not just click through every single cell - experiment with
alternatives, you will need the experience when you write your blog post!
_You may have to create the directory `mkdir -p /data/bigdata/` using `docker exec` or a `:sh` directive._

**When you get stuck, open an issue in the
[Forum](https://github.com/rubigdata/forum-2017)
to find help from your fellow students and/or me!**

Useful background information:
[Spark SQL and DataFrame documentation](http://spark.apache.org/docs/latest/sql-programming-guide.html).


Back to [Assignment 3 overview](A3-spark.html).
