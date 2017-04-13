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

### Using Notebook

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

Alternatively, you can run commands from inside a running notebook; 
making use of the functionality provided by the `sys.process` package and/or 
shell escapes; experiment yourself using:

```
:sh ls /tmp
```

or

```
val tmpfiles = "ls /tmp" !!
```

*Note: Myself, I did not succeed in using wildcards in commands issued.*

### Prepare container

Build on the setup we created for the Map Reduce assignment.
If you need to restart from scratch, the easiest is to follow 
[these detailed instructions](../background/exact.html).

Create the following directory:

    mkdir -p /opt/docker/notebooks/BigData

When you accept assignment 3, you find the course notebook in the github repository for the assignment.
Either checkout the repository from inside the container, or copy the file into the container.

    cd /opt/docker/notebooks/BigData
    wget http://rubigdata.github.io/course/assignments/BigData-big-data-spark-rdd.snb

Open [localhost:9000/tree/BigData](http://localhost:9000/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

Follow the steps in the course notebook to get at ease with using spark, scala;
and try to understand what you find in the Spark UI, that is available 
at [localhost:4040](http://localhost:4040).

**When you get stuck, open an issue in the 
[Forum](https://github.com/rubigdata/forum-2017)
to find help from your fellow students and/or me!**

### Blog post assignment 3

Write the blog post after carrying out assignment 3B (to be released next week).

While assignment 3A looks into RDDs, the data representation and query processing
internals of using Spark, assignment 3B will be focused on the use of Dataframes and Spark SQL.
You will be free to choose your preferred angle for the blog post: 
whether you focus on the information from assignment 3A (looking into the details of 
query processing in Spark) or 3B (focusing more on carrying out a data analysis).

Whatever you choose, include in your blog post the insights you gain from inspecting 
the Spark UI after issuing commands from the notebook.
Interesting aspects you might want to address include the lazy evaluation, the effect 
of caching RDDs, partitioning of the RDDs, etc.

