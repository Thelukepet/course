---
layout: page
title: Using the Spark Notebook Docker Image
description: How to use the Spark Notebook Docker Image on the machines in the Huygens terminal rooms
---

# Spark Notebook

## Install at the university

Docker images can still be kind-a large, therefore I prepared a saved image on the shared volumes, for more efficient distribution.

First check if by any chance, somebody already used the docker client on your machine, and took these steps already;
do so by issuing `docker images`.  If no image named `andypetrella/spark-notebook` (with a long tag) is listed, 
then issue the following command to initialize a container that runs Spark Notebook:

```
cat /vol/practica/BigData/cloudera-docker-image.tar | docker load
```

Do not worry if processing this command takes a while, as about one gigabyte is transferred over the network;
not dramatic, but not instant either.

## Install at home

At home, you can pull the same image directly from [Spark Notebook](http://spark-notebook.io), e.g.
```
docker pull andypetrella/spark-notebook:0.6.2-scala-2.11.7-spark-1.6.0-hadoop-2.7.1-with-hive-with-parquet
```

Alternatively, you may also use the copy of the image provided in `/vol/practica/BigData`, using a command like
```
ssh lilo cat /vol/practica/BigData/cloudera-docker-image.tar | pv | docker load
```

(You can ignore `pv` if you do not have it installed; I find it incredibly useful though.)

## Run the container

We can now run a container using this image.

    docker run -p 9000:9000 -p 4040-4045:4040-4045 \ 
      andypetrella/spark-notebook:0.6.2-scala-2.11.7-spark-1.6.0-hadoop-2.7.1-with-hive-with-parquet

(You could also use the image's hash, which you copy from `docker images`.)

The port options tell docker to expose ports 4040-4045 and port 9000.

Now open [localhost:9000](http://localhost:9000/) in your browser to access the Spark Notebook.

You can execute a shell inside the running container by looking up its hash using `docker ps`
(different from the images' hash!), followed by

    docker exec -it HASH /bin/bash

If you are lucky, you reached this point without any problems. Do not worry to ask questions if you encounter
problems that you cannot resolve on your own, but then **please please** do that on 
[Piazza](https://piazza.com/ru.nl/spring2016/nwiibc036/home); then every one in class can help out, and it is
not my overflowing email box that will form the bottleneck for your progress.

## See also:

After the first trials in the terminal rooms, I discoverd that the images should be relatively small for things 
to work on the hardware provided in the Huygens building. 

The reference docker image for the first part of class is the top one of the list below. 
If you prefer Python over Scala, the second image is a good alternative; though slightly less complete 
than the Spark Notebook. A very complete Spark Notebook (that will however take too many resources on the PCs
in HG 00.023) is the third link below - feel free to try it out at home!

* [Spark Notebook](http://spark-notebook.io) for Scala, the source of the image used in class
* [Pyspark Notebook](http://blog.prabeeshk.com/blog/2015/06/19/pyspark-notebook-with-docker/) for Python
* A more complete Spark Notebook, including Python and R, provided by [Jupyter](https://hub.docker.com/r/jupyter/all-spark-notebook/) (however, ~4GB, will not run well on the computers in the terminal room)

[Back to Assignment A1b](../assignments/A1b-docker.html)

