---
layout: page
title: Using the Spark Notebook Docker Image
description: How to use the Spark Notebook Docker Image on the machines in the Huygens terminal rooms
---

# Spark Notebook

## Install

Pull an image directly from [Spark Notebook](http://spark-notebook.io):
```
docker pull andypetrella/spark-notebook:0.7.0-scala-2.11.8-spark-2.1.0-hadoop-2.7.3-with-hive
```

## Run the container

We can now run a container using this image.

    docker run -p 9001:9001 -p 4040-4045:4040-4045 \ 
      andypetrella/spark-notebook:0.7.0-scala-2.11.8-spark-2.1.0-hadoop-2.7.3-with-hive

(You could also use the image's hash, which you would have to copy-paste from `docker images`.)

The port options tell docker to expose ports 4040-4045 and port 9001.

Now open [localhost:9001](http://localhost:9001/) in your browser to access the Spark Notebook.

You can execute a shell inside the running container by looking up its hash using `docker ps`
(different from the images' hash!), followed by

    docker exec -it HASH /bin/bash

## Done

If you are lucky, you reached this point without any problems. Do not worry to ask questions if you encounter
problems that you cannot resolve on your own, but then **please please** do that using
the github issue tracker on [the forum](https://github.com/rubigdata/forum/); then every one in class can help out, 
and it is not my overflowing email box that will form the bottleneck for your progress.

[Back to Assignment A1b](../assignments/A1b-docker.html)

