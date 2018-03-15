---
layout: page
title: Map Reduce
tagline: Hands-on session with Hadoop and Map-Reduce
description: Learn how the abstract concepts from the lectures so far work out in practice.
---

Lectures 2 to 4 discuss distributed filesystems and Map-Reduce, that in practice today are almost a synonym to "Hadoop".

In this assignment, we install Hadoop on our own "pseudo-cluster", and use Map-Reduce to do some basic count operations.

You accept the assignment via Github for Education, using 
[__this invitation link__](https://classroom.github.com/a/H-Hs6uhw)

In the first week of assignment two, you will use a container we created for the course, where you setup a
pseudo-distributed cluster. In the subsequent two weeks, you run your own map-reduce jobs.
The HDFS filesystem that you create and use in this tutorial, can be re-used in the later lab sessions on Spark.

### Setup

Setup distributed filesystem `HDFS` and the Map-Reduce tools using [__our Hadoop instructions__](../background/hadoop.html).

Useful HDFS background information:

* [User Guide](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-hdfs/HdfsUserGuide.html)
* [Commands Reference](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-hdfs/HDFSCommands.html)

Make sure that you understand conceptually what is going on, and you know how to use the filesystem:
create files, read them, delete files and directories, _etc_.
Run the example with a different pattern on a different set of files.

If you did not fully comprehend the Java code for the example,
don't worry - that will become clear in class this Monday.
But do take a look at it!

### Blog post

The assignment is to write a blog post about your experience with HDFS and Map-Reduce.
Assume the reader knows what a distributed filesystem is, and why you would use it.

These instructions assume your HDFS has been set up successfully and is running.
Download the *Complete Shakespeare* directly from the github website and save it to the HDFS:

```
cd /opt/docker/hadoop-2.7.3
wget https://raw.githubusercontent.com/rubigdata/hadoop-dockerfile/master/100.txt
bin/hdfs dfs -put 100.txt input
```
Next we need to set up the environment to compile and run the WordCount code.
Make sure WordCount.java is in the `/opt/docker/hadoop-2.7.3` directory.
```
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
bin/hadoop com.sun.tools.javac.Main WordCount.java
jar cf wc.jar WordCount*.class
```
Next run your code with
```
bin/hadoop jar wc.jar WordCount input output
```
This output is now located in the file output in the HDFS and can be inspected like you did in the Hadoop instructions.


Walk your readers through a simple Map-Reduce example to count the number of lines, words or characters 
(or something more interesting, it is really up to you).

Use the Map-Reduce documentation to get started:
[tutorial WordCount v1.0](https://hadoop.apache.org/docs/r2.7.3/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Example:_WordCount_v1.0)

If you clone your assignment repository, you find the example `WordCount.java` as starting point. 

Address at least the following questions:
+ What happens when you run the Hadoop commands (`hdfs dfs` _etc._) in the first week's part of the tutorial?
+ How do you use mapreduce to count the number of lines/words/characters/... in the *Complete Shakespeare*?
+ Does Romeo or Juliet appear more often in the plays?
  Can you answer this question making only one pass over the corpus?

If things go smooth, try to compute the average number of words or characters per line.

If things go _really_ smoothly, you can try to use a combiner and discuss the improvement achieved.

### Done

When you completed the assignment, push your blog post to the first assignment's repository
and include a link to the published blog post in the README of the assignment repository.
Commit the README as well as your code to the assignment repository.
In other words:

**Instructions to submit your completed work** (replace USERNAME by your github account):

+ Write your blog in the blogpost repository you made for assignment 1. 
  This repostory is located at `https://github.com/rubigdata/bigdata-blog-2018-USERNAME` 
+ Make sure your blog is published and the post is accessible from https://rubigdata.github.io/bigdata-blog-2018-USERNAME
+ Place a link to the published blogpost (for example https://rubigdata.github.io/bigdata-blog-2018-USERNAME/assignment2) in the README.md of your assignment 2 repository, 
  which is located at https://github.com/rubigdata/hello-hadoop-2018-USERNAME

### Help?!

Feel free to ask for help, but please do that by using the github issue tracker on [the forum](https://github.com/rubigdata/forum-2018/); 
see the [first issue](https://github.com/rubigdata/forum-2018/issues/1) as an example of how to proceed.
Every student may help out, please contribute and share your knowledge! 

[Back to assignments overview](../index.html)


