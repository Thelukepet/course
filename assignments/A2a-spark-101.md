---
layout: page
title: Assignment 2 Part A
description: Big Data Spark 101
---

## Spark 101

The goal of assignment 2A is to get hands-on experience in using the 
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

Copy the *Complete Shakespeare* in text provided at 
[`/vol/practica/BigData/100.txt.utf-8`](file:///vol/practica/BigData/100.txt.utf-8)
into directory `/data` in the docker container.

E.g.:

```
mkdir -p /data
scp USERNAME@lilo.science.ru.nl:/vol/practica/BigData/100.txt.utf-8 /data
```

You have to provide your username (in the Docker container, you are `root`);
in the terminal rooms, use the local machine name: 
`hg023pcXX` instead of `lilo.science.ru.nl`.

Alternatively, download the data yourself from the Project Gutenberg website:

```
cd /data ; wget http://www.gutenberg.org/ebooks/100.txt.utf-8
```

### Big Data Spark 101 notebook

Download the course's notebook 
[Big Data Spark 101](http://rubigdata.github.io/course/assignments/big-data-spark-101.snb).

Create a directory `notebooks/BigData` and copy the `.snb` file into that directory.

```
mkdir -p /opt/docker/notebooks/BigData
cd /opt/docker/notebooks/BigData
wget http://rubigdata.github.io/course/assignments/big-data-spark-101.snb

```

Open [localhost:9000/tree/BigData](http://localhost:9000/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

Follow the steps in the course notebook to get at ease with using spark, scala;
and try to understand what you find in the Spark UI, that is available 
at [localhost:4040](http://localhost:4040).

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

### Blog post (part A)

Imagine a reader who is interested in learning the very basics of Spark, 
for whom you write a blog to get started (and enthousiastic about learning 
more).

Write the introduction of the blog post for Assignment 2.

Introduce the reader briefly to
spark, and, if you like, the way you carry out the assignment:
in the terminal room or at home, deviations from the default suggested 
commands that you needed to get things running conveniently, *etc.*.

Briefly explain what you learned about going through the notebook.
Copy the most relevant commands (modified where you thought interesting),
and add a brief explanation of what the commands do.
(View as report can be a handy feature!)

Do not forget to include what you learn from inspecting the Spark UI after 
issuing commands from the notebook! 
(Hint: comment on lazy evaluation and/or the effect of caching RDDs.)

Next week, in part B, we will write about more detailed analysis of the data 


