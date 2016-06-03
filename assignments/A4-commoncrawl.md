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

Next, go through the basics of running a Spark job on the cluster.
Read the [Surfsara specific instructions](https://userinfo.surfsara.nl/systems/hadoop/software/spark);
to install Spark on the docker image, proceed however as follows:

```
cd hathi-client
perl -e 's/version=1.6.0/version=1.6.1/g' -npi bin/get.sh
bin/get.sh spark
```



Finally, work your way through the steps in the official 
[Spark documentation](http://spark.apache.org/docs/1.6.1/running-on-yarn.html#launching-spark-on-yarn)
to get a bit more experience on running a program on the cluster.

**Do not yet run a program on the Commoncrawl at this point!**

I suggest to extract a few spark exercises from the notebooks we used in [assignment 2a](A2a-spark-101.md) 
and [assignment 2b](A2b-execution-model.md) before continuing to the next step.

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
