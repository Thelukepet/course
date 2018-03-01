---
layout: page
title: Map Reduce
tagline: Hands-on session with Hadoop and Map-Reduce
description: Learn how the abstract concepts from the lectures so far work out in practice.
---

Lectures 2 to 4 discuss distributed filesystems and Map-Reduce, that in practice today are almost a synonym to "Hadoop".

In this assignment, we install Hadoop on our own "pseudo-cluster", and use Map-Reduce to do some basic count operations.

You accept the assignment via Github for Education, using 
[__this invitation link__](https://classroom.github.com/assignment-invitations/TBD)

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

Copy the *Complete Shakespeare* in text provided at 
[`/vol/practica/BigData/100.txt.utf-8`](file:///vol/practica/BigData/100.txt.utf-8)
into directory `/mnt/bigdata` in the docker container.
Alternatively, download it directly from the _Project Gutenburg_ website:

```
wget http://www.gutenberg.org/ebooks/100.txt.utf-8
```

Walk your readers through a simple Map-Reduce example to count the number of lines, words or characters 
(or something more interesting, it is really up to you).

Use the Map-Reduce documentation to get started:
[tutorial WordCount v1.0](https://hadoop.apache.org/docs/r2.7.3/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Example:_WordCount_v1.0)

If you clone your assignment repository, you find the example `WordCount.java` as starting point. 

Address at least the following questions:
+ What happens when you run the commands in the first week's part of the tutorial?
+ How do you use mapreduce to count the number of lines/words/characters/... in the *Complete Shakespeare*?
+ Does Romeo or Juliet appear more often in the plays?
  Can you answer this question making only one pass over the corpus?

If things go smooth, try to compute the average number of words or characters per line.

If things go _really_ smoothly, you can try to use a combiner and discuss the improvement achieved.

### Done

When you completed the assignment, push your blog post to the first assignment's repository
and include a link to the published blog post in the README of the assignment repository.
Commit the README as well as your code to the assignment repository.

### Help?!

Feel free to ask for help, but please do that by using the github issue tracker on [the forum](https://github.com/rubigdata/forum-2018/); 
see the [first issue](https://github.com/rubigdata/forum-2018/issues/1) as an example of how to proceed.
Every student may help out, please contribute and share your knowledge! 

[Back to assignments overview](../index.html)


