---
layout: page
title: Assignment 2 Part B
description: Big Data Execution Model
---

## Spark Execution Model

The goal of assignment 2B is to get a deeper understanding of how
query plans are executed. You may want to inspect the slides
of Lecture 5 for background information.

Start the docker container (refer to [assignment A1.b](A1b-docker.html)
and the [Spark Notebook background](../background/spark-notebook.html)
if necessary).

### Big Data Execution Model Notebook

Download the course's notebook 
[Big Data Execution Model](http://rubigdata.github.io/course/assignments/BigData-big-data-execution-model.snb)
copy it into `notebooks/BigData`.

Simply open it using the notebook UI in the browser (where it states *or click here*), 
or use `wget` from inside the docker engine:

```
cd /opt/docker/notebooks/BigData
wget http://rubigdata.github.io/course/assignments/BigData-big-data-execution-model.snb
```

Open [localhost:9000/tree/BigData](http://localhost:9000/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

### Blog post (part B)

Imagine the reader who has just learned the basics of Spark,
but is curious to know how jobs are actually executed.

Use the information in the Execution Model notebook for inspiration.
Then complete the blog post for Assignment 2.

The final blog post should introduce the reader briefly to
spark, and, if you like, the way you carry out the assignment:
in the terminal room or at home, deviations from the default suggested 
commands that you needed to get things running conveniently, *etc.*.

Briefly explain what you learned about going through the notebook.
Copy the most relevant commands (modified where you thought interesting),
and add a brief explanation of what the commands do.
(View as report can be a handy feature!)

Do not forget to include what you learn from inspecting the Spark UI after 
issuing commands from the notebook! 
