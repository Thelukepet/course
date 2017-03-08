---
layout: page
title: Map Reduce
tagline: Hands-on session with Hadoop and Map-Reduce
description: Learn how the abstract concepts from the lectures so far work out in practice.
---

## Map Reduce on HDFS

Lectures 3 to 5 discussed distributed filesystems and Map-Reduce, that in practice today are almost equivalent to "Hadoop".

In this assignment, we install Hadoop on our own "pseudo-cluster", and use Map-Reduce to do some basic count operations.

The tutorial was tested inside the Docker container for Spark-Notebook that we setup in the previous lab sessions;
you would use the `docker exec` command to start a shell inside the image first.
The HDFS filesystem that you create and use in this tutorial, can be re-used in the later lab sessions on Spark.

### Setup

Setup distributed filesystem `HDFS` and the Map-Reduce tools using [these Hadoop instructions](../background/hadoop.html).

### Blog post

The assignment is to write a blog post about your experience with HDFS and Map-Reduce.

Assume the reader knows what a distributed filesystem is, and why you would use it.
Walk your readers through a simple Map-Reduce example like counting the words in a file.

[Back to assignments overview](index.html)


