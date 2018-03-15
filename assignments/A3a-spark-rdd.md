---
layout: page
title: Assignment 3 Part A
tagline: Hands-on session with Spark RDDs
description: Learn how the abstract concepts from the lectures so far work out in practice.
---

## Spark RDDs

The goal of assignment 3A is to get hands-on experience in using the 
Spark Notebook to write your spark programs.

Start a new docker container following the [Spark Notebook background](../background/spark-notebook.html)
instructions.

_Unfortunately, a new container is necessary to open up the ports for the Spark Notebook and its debug console._

You can access the Spark Notebook environment in your browser by navigating to [localhost:9001](http://localhost:9001/).
Feel free to take a quick peek, but do not spend too much time before following the instructions below.

### Prepare container

For the assignment, we have to copy the __course notebook__ 
[BigData-big-data-spark-rdd.snb](http://rubigdata.github.io/course/assignments/BigData-big-data-spark-rdd.snb) into 
a directory on the Spark Notebook container.

To get files onto the notebook docker container that you use, the recommended approach is to download the files
to your normal desktop environment and copy them into the container using `docker cp`
(replace CONTAINER by the hash or the name of your container):

    wget http://rubigdata.github.io/course/assignments/BigData-big-data-spark-rdd.snb
    docker exec CONTAINER mkdir /opt/docker/notebooks/BigData
    docker cp BigData-big-data-spark-rdd.snb CONTAINER:/opt/docker/notebooks/BigData

An alternative approach is to start a shell inside the container (using the `docker exec` command), 
and download the files there. (From the shell, you can simply `cd` to move to the directory you need,
and use `scp`, `wget` or `git clone` commands to copy the files needed.)
Create a directory for the course notebooks and download the file there: 

    mkdir -p /opt/docker/notebooks/BigData
    cd /opt/docker/notebooks/BigData
    wget http://rubigdata.github.io/course/assignments/BigData-big-data-spark-rdd.snb

### Using Notebook

Navigate to [localhost:9001/tree/BigData](http://localhost:9001/tree/BigData) in your browser 
to open the notebook you just installed inside your Spark Notebook container.

Navigate the notebook using the keyboard, pressing shift-enter to execute a cell, and enter to add lines to a cell.
_You can try out any scala command - create a new cell, enter some scala code in the cell,
and execute it, just to get familiar with the notebook environment._

You will have to copy the Shakespeare file into the notebook, as last week, and/or modify its path
in the notebook. _I did the following:_

    docker exec CONTAINER mkdir /mnt/bigdata
    docker cp 100.txt CONTAINER:/mnt/bigdata

Follow the steps in the course notebook to get at ease with using spark, scala;
and make sure that you understand what you find in the Spark UI, 
available at [localhost:4040](http://localhost:4040).

### Need help?

**When you get stuck, open an issue in the 
[Forum](https://github.com/rubigdata/forum-2018)
to find help from your fellow students and/or me!**

Back to [Assignment 3 overview](A3-spark.html).
