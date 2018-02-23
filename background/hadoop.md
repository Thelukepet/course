---
layout: page
title: Hadoop
tagline: Running Hadoop in your Docker container
description: Explanations to install and run Hadoop
---

## Map Reduce on HDFS

We have prepared an extended Spark-Notebook Docker container, prepared to run Hadoop on our own "pseudo-cluster".

You will use the `docker exec` command to start a shell inside the Docker container, and work from that shell.
The HDFS filesystem that you create and use in this tutorial, can be re-used in the later lab sessions on Spark.

### Setup

Run the course's Docker container and execute a shell, in which we still have to start the `ssh` service 
(a shortcoming of our current Docker image).

```
## Run the container, in the background
DID=`docker run -d rubigdata/hadoop`

## Execute a shell in the running container
docker exec -it $DID /bin/bash

## Start missing service
service ssh start
```

### Intermezzo

If you are exploring the source of the Hadoop examples later on in the lab session, I recommend doing these steps 
on your host machine, and not inside the docker container; much easier for working with your favourite GUI, editors, 
copy-paste support, _etc. etc._

You can exchange files in three different ways:

1. Use `docker cp` to copy files into the container.

2. Use `scp` to copy files via `lilo` (the FNWI LInux LOgin server); your homedir in the terminal room
is also mounted through NFS on `lilo`. 

3. __Does not work in the Huygens terminal room (yet?):__ 
Create a directory `${HOME}/bigdata` in your homedir that you share with the Docker container using the `-v` flag.

### Pseudo Distributed

```
cd hadoop-2.7.3
```

We will now setup a "real" cluster, even though we will only emulate it on our machine (_inside_ the Docker container, actually).

You find the configuration files prepared as `etc/hadoop/core-site.xml` and `etc/hadoop/hdfs-site.xml`;
inspect them and you notice that replication has been set to one instead of the default _(why does that make sense?)_.

#### HDFS

Prepare (format) the distributed filesystem:

```
bin/hdfs namenode -format
```

Start HDFS and create the user directory; here, I assume you simply work as user `root` in the Docker container.

```
sbin/start-dfs.sh

bin/hdfs dfs -mkdir /user
bin/hdfs dfs -mkdir /user/root

```

To run map-reduce jobs on a cluster, you first have to copy the data to the HDFS filesystem.

```
bin/hdfs dfs -put etc/hadoop input
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar grep input output 'dfs[a-z.]+'
bin/hdfs dfs -get output output

bin/hdfs dfs -ls hdfs://localhost:9000/user/root/input
```

Try to understand exactly what happened when you ran these commands - what files are you doing what operation on?
When trying to understand what happens when you follow along, it is good to know that the source of the example programs
is included in the release, e.g.,

```
jar tvf share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.7.3-sources.jar
```

(You can unpack `.jar` files (for "java archive") using `jar xf`.)

Stop the filesystem gracefully when you are done:

```
sbin/stop-dfs.sh
```

See also the Hadoop documentation for running a
[Pseudo-Distributed Cluster](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#Pseudo-Distributed_Operation)

### See also

Real clusters use a cluster management system like _Yarn_ to start and stop services and manage map-reduce jobs.

If you are interested to see how that works (voluntarily, not required for the course), 
you could try the additional steps from the Hadoop documentation to
[run Yarn on a single node](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#YARN_on_a_Single_Node)


[Back to Map-Reduce assignment](../assignments/A2-mapreduce.html)
