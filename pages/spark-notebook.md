---
layout: page
title: Using the Spark Notebook Docker Image
description: How to use the Spark Notebook Docker Image on the machines in the Huygens terminal rooms
---

# Spark Notebook

## Install at the university

Docker images can still be kind-a large, therefore I prepared a saved image on the shared volumes, for more efficient distribution.

Login to the terminal machine, and issue the following command to initialize the engine:

```
cat /vol/practica/BigData/cloudera-docker-image.tar | docker load
```

## Install at home

At home, you can pull the same image directly from [Spark Notebook](http://spark-notebook.io), e.g.
```
docker pull andypetrella/spark-notebook:0.6.2-scala-2.11.7-spark-1.6.0-hadoop-2.7.1-with-hive-with-parquet
```

Alternatively, you may also use the copy of the image provided here, using a command like
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
