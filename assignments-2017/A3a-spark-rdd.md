---
layout: page
title: Assignment 3 Part A
tagline: Hands-on session with Spark RDDs
description: Learn how the abstract concepts from the lectures so far work out in practice.
---

## Spark RDDs

The goal of assignment 3A is to get hands-on experience in using the 
Spark Notebook to write your spark programs.

Start the docker container (refer to [assignment A1.b](A1b-docker.html)
and the [Spark Notebook background](../background/spark-notebook.html)
if necessary).

Note: if you need to restart from scratch, the easiest is to follow 
[the detailed instructions from assignment 2](../background/exact.html),
where you can skip the middle part (the instructions that setup Hadoop).

### Using Notebook

Access Spark Notebook in your browser by navigating to [localhost:9001](http://localhost:9001/).

You may navigate a notebook with the keyboard by pressing shift-enter 
to execute a cell, and enter to add lines to a cell.

To get files onto the notebook docker container that you use, the easiest
approach is to simply start a shell inside the container, and download the
files there:

```
docker exec -it HASH /bin/bash
```

Inside the shell, you can simply `cd` to move to the directory you need,
and use `scp`, `wget` or `git clone` commands to copy the files needed.

### Prepare container

Create the following directory and copy the __course notebook__ 
[BigData-big-data-spark-rdd.snb](http://rubigdata.github.io/course/assignments/BigData-big-data-spark-rdd.snb) into that directory:

    mkdir -p /opt/docker/notebooks/BigData
    cd /opt/docker/notebooks/BigData
    wget http://rubigdata.github.io/course/assignments/BigData-big-data-spark-rdd.snb

Navigate to [localhost:9001/tree/BigData](http://localhost:9001/tree/BigData) in your browser 
to open the notebook you just installed inside your Spark Notebook container.

Follow the steps in the course notebook to get at ease with using spark, scala;
and make sure that you understand what you find in the Spark UI, 
available at [localhost:4040](http://localhost:4040).

**When you get stuck, open an issue in the 
[Forum](https://github.com/rubigdata/forum-2017)
to find help from your fellow students and/or me!**

Back to [Assignment 3 overview](A3-spark.html).
