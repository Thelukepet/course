---
layout: page
title: Spark
tagline: Hands-on session with Spark RDDs, SQL and DataFrames
description: Use the different Spark APIs in practice and understand their inner workings.
---

Assignment 3 consists of two parts that focus on query processing and data analysis with Spark,
using the core RDD API as well as the newer and more high-level DataFrames.

<!--
The lecture on Monday March 19th gives more detail on Spark's internals;
the initial goal this week is to observe how you can carry out a few simple tasks in Spark,
and contrast this with using the older Map Reduce framework.

You should stop at heading _Deeper Understanding of Spark_.
And, it is not a problem if you do not understand everything this first week,
however, _it will be a problem if you still do not understand it next week!_
-->

You accept the assignment via Github for Education, using 
[__this invitation link__](https://classroom.github.com/a/hqXs_57o)

You need to download the Spark Notebook for assignment 3, and copy it to your Docker container.
Follow the instructions:

+ [Assignment 3A](A3a-spark-rdd.html);
+ [Assignment 3B](A3b-spark-df.html).

### Blog Post

The assignment is to write a blog post about your experience with Spark.

While assignment 3A looks into RDDs, the data representation and query processing internals of using Spark,
assignment 3B is focused on the use of Dataframes and Spark SQL. It is _up to you_ to choose the preferred angle
for your blog post: you may focus on the Spark internals where the emphasis is assignment 3A (looking into the
details of query processing in Spark, perhaps using some of the queries in the 3B notebook to illustrate your
point) or assignment 3B (focusing on the analytical side, e.g. by analyzing a different dataset from the one
we use in the notebook).

Whatever you choose, include in your blog post the insights you gain from inspecting the Spark UI after
issuing commands from the notebooks.

#### Option A

If you choose the query processing angle, I suggest you use data we already worked with in the notebooks
for assignments 3A and 3B.

Interesting aspects of Spark you might want to address include the lazy evaluation, the effect of caching RDDs,
partitioning of the RDDs, etc. Ideally, you would experiment with a few alternative solutions for the same
high-level objective, and/or different choices in the setup of the dataset (like number of partitions, how
to avoid shuffles, relationship between number of shuffles and overall processing cost, etc.).

#### Option B

If you choose the data analysis angle, I suggest to choose one idea from the two directions discussed next,
and reflect in the blog post on the process that you followed to analyze the data.

The most basic version for a blog post from the angle of assignment 3B would be to continue the analysis of the
data we already used in the notebook. Can you provide a more complete list of dated quarters or improve the estimated
dates? Include different data, or answer a different question about this data?

Alternatively, you could decide to carry out a small analysis of a different open dataset, of your own choice;
and present to your readers some basic properties of that data. _You will notice that it is harder than following
instructions, and you run the risk of getting stuck because the data does not parse well, etc._

### Done

When you completed the assignment, push your blog post to the first assignment's repository.
Include a link to the published blog post in the README of the third assignment repository.
Push the updated README as well as your own code or notebook to the third assignment repository.

### Help?!

Feel free to ask for help, but please do that by using the github issue tracker on [the forum](https://github.com/rubigdata/forum-2018/); 
see the [already closed issues](https://github.com/rubigdata/forum-2018/issues?q=is%3Aissue+is%3Aclosed) for reference.
Every student may help out, please contribute and share your knowledge! 

[Back to assignments overview](../index.html)
