---
layout: page
title: Using the Spark Notebook Docker Image
description: How to use the Spark Notebook Docker Image on the machines in the Huygens terminal rooms
---

# Spark Notebook

<!--
## Install

Pull an image directly from [Spark Notebook](http://spark-notebook.io):

```
docker pull andypetrella/spark-notebook:0.7.0-scala-2.11.8-spark-2.1.0-hadoop-2.7.3-with-hive
```
-->

## Run the container

We run a container using the course's `rubigdata/hadoop` image. 

    docker run -p 9001:9001 -p 4040-4045:4040-4045 rubigdata/hadoop

(You could also use the image's hash, which you would have to copy-paste from `docker images`.)

The port options tell docker to expose ports 4040-4045 and port 9001.

Now open [localhost:9001](http://localhost:9001/) in your browser to access the Spark Notebook.

You can execute a shell inside the running container by looking up its hash using `docker ps`
(different from the images' hash!), followed by

    docker exec -it HASH /bin/bash

## Need help?

Use the github issue tracker on [the forum](https://github.com/rubigdata/forum-2018/) so every one 
in class can help out and my overflowing email box is not a bottleneck for _your_ progress.
See also: [First welcome issue](https://github.com/rubigdata/forum-2018/issues/1)

[Back to Assignment A1b](../assignments/A1b-docker.html)

