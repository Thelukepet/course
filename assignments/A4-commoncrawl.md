---
layout: page
title: Assignment 4 
description: Commoncrawl
---

## Commoncrawl

Assignment 4 is an open assignment, where we will work with data on the national supercomputer infrastructure managed by SurfSara.

We have to see how far we get - the main objective is not so much to prepare a winning submission for the next Norvig Award, but 
merely to get some handson experience in running _and debugging_ jobs on a shared managed cluster, instead of our own laptops or
desktops emulating a cluster.

### Preparations

We work on the national [Hadoop cluster](https://userinfo.surfsara.nl/systems/hadoop/description).
Instead of following the _Obtaining an account_ instructions, you have to receive your account info from me.

The next step is to work through the basics as discussed on their
[Hadoop cluster usage page](https://userinfo.surfsara.nl/systems/hadoop/usage).

...

Spark Notebook is not supported on the national hadoop cluster, but Spark is.

Let us start by using the provided docker image.
```
docker pull surfsara/hathi-client
docker run -it surfsara/hathi-client
```

Then use your credentials to get a Kerberos ticket, following the [SurfSara instructions](https://userinfo.surfsara.nl/systems/hadoop/usage).

_Note that this did not work well when (by mistake) multiple `surfsara/hathi-client` docker containers were running on my machine._

Try a simple directory listing of the most recent crawl:
```
hdfs dfs -ls /data/public/common-crawl/crawl-data/CC-MAIN-2016-07
```


### Assignment

The assignment is to "do something with the Commoncrawl data" and write-up your experience in
a final blog post.

More details to follow; this page will be updated Monday June 6th.


**When you get stuck, use Piazza to find help from your fellow students and/or me!**

### Blog post 

Imagine a reader that has followed your previous experiences with Spark.
The goal of this post is to share your work on the Commoncrawl data with them,
emphasizing the differences between running locally a prepared notebook versus
a real project on a more unfamiliar remote environment.
