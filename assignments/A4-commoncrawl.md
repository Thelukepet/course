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

#### Initial setup

Let us work through the basics discussed on the
[Hadoop cluster usage page](https://userinfo.surfsara.nl/systems/hadoop/usage).

Start by using the provided docker image.

```
docker pull surfsara/hathi-client

docker run -it surfsara/hathi-client
```

#### Kerberos authentication

Because the national cluster serves many users that not all have access to the same data, authentication is kind-a strict, provided
through MIT's Kerberos.

Inside a Docker container, this works smoothly: 
use your credentials (obtained from me) to get a Kerberos ticket, 
by following the [SurfSara instructions](https://userinfo.surfsara.nl/systems/hadoop/usage).

To authenticate through Kerberos _on a Linux or Windows machine_ **outside** the Docker image,
which will be necessary to use the ResourceManager from your web-browser, 
please [follow these steps](kerberos.html) (provided without warranty, by me).

#### First steps on Hathi

Try a simple directory listing of the most recent crawl:

```
hdfs dfs -ls /data/public/common-crawl/crawl-data/CC-MAIN-2016-07
hdfs dfs -ls -h /data/public/common-crawl/crawl-data/CC-MAIN-2016-07/segments/1454702039825.90/warc
```

#### Spark

Spark Notebook is not supported on the national hadoop cluster, but Spark is.

Next, let us go through the basics of running a Spark job on the cluster.
Quickly scan the [Surfsara specific instructions](https://userinfo.surfsara.nl/systems/hadoop/software/spark);
to install Spark on the docker image, proceed however with the specific instructions given here:

```
cd hathi-client
perl -e 's/version=1.6.0/version=1.6.1/g' -npi bin/get.sh
perl -e 's/spark-assembly-1.6.1/spark-assembly-1.6.1/g' -npi conf/spark/spark-defaults.conf
bin/get.sh spark
```

From now on, initialize the right environment for working with Spark by issuing the following command:

```
bin/env.sh
```

Try out the standard example to compute Pi through a random process. 
(The SurfSara cluster requires jobs to be submitted through `yarn`.)

```
cd spark
MASTER=yarn-cluster bin/run-example SparkPi
```

If this succeeded, we are ready for using the cluster for real!

Start to work your way through the initial steps of the official 
[Spark documentation](http://spark.apache.org/docs/1.6.1/quick-start.html#self-contained-applications)
to learn how to create a standalone Spark application using `spark-submit`.
For running an application, you need the instructions to use
[`spark-submit` for a yarn cluster](http://spark.apache.org/docs/1.6.1/running-on-yarn.html#launching-spark-on-yarn).

**Do not yet run a program on the Commoncrawl at this point!**

I suggest to extract a few spark exercises from the notebooks we used in [assignment 2a](A2a-spark-101.md) 
and [assignment 2b](A2b-execution-model.md) before continuing to the next step.
Create your own version of the notebook, and access the code by saving it as scala code (_save as_ in the file menu).

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
