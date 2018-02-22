---
layout: page
title: Hadoop
tagline: Running Hadoop in your Docker container
description: Explanations to install and run Hadoop
---

## Map Reduce on HDFS

We install an extended Spark-Notebook Docker container that has Hadoop installed, ready to run on our own "pseudo-cluster".
We then run a few default Map-Reduce operations.

You will use the `docker exec` command to start a shell inside the Docker container, and work from that shell.
The HDFS filesystem that you create and use in this tutorial, can be re-used in the later lab sessions on Spark.

### Setup

Install the Docker image and run a shell, in which we still have to start the `ssh` service 
(a shortcoming of our current Docker image).


```
## Run a container
docker exec -it rubigdata/hadoop /bin/bash

## Start missing service
service ssh start

```

### Intermezzo

If you are not yet (!) fluent in a UNIX environment, view and edit files inside the Docker container using `nano` 
(can be installed via `apt-get install nano`).

Maybe you need to set the `TERM` variable for `nano` before this works; e.g.

```
export TERM=xterm
```

_If that is the case, now use `nano` to add this command as the last line of `$HOME/.bashrc`._ 

If you are exploring the source of the Hadoop examples later on in the lab session, I recommend doing these steps 
on your host machine, and not inside the docker container; much easier for working with your favourite GUI, editors, 
copy-paste support, _etc. etc._

You can exchange files in three different ways:

1. You could create a directory `${HOME}/bigdata` in your homedir that you can access in the Docker container
by starting it using the `-v` flag, by adding `-v ${HOME}/bigdata:/mnt/bigdata`. 

2. Use `docker cp` to copy files into the container.

3. Use `scp` to copy files via `lilo` (the FNWI LInux LOgin server); your homedir in the terminal room
is also mounted through NFS on `lilo`. 


```
docker run -p 9001:9001 -p 4040-4045:4040-4045 -p 50070:50070 -p 8088:8088 -v /vagrant:/mnt/bigdata HASH
```

Both examples assume that we use directory `${HOME}/bigdata`, which needs group read rights to user `dockeremap`.
CHECK
 

### Installing HDFS

Install the necessary basic tools, download Hadoop version 2.7.3, and unpack the code:

```
wget http://ftp.nluug.nl/internet/apache/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz
tar xzvfp hadoop-2.7.3.tar.gz
cd hadoop-2.7.3
```

### Standalone

Try out if everything works by using the _standalone_ version first.

Follow the
[standalone instructions](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#Standalone_Operation) and try to understand what happens when you run the example job.

Note: the sources are included in the release, e.g.,

```
jar tvf share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.7.3-sources.jar
```

(You unpack the `.jar` file (for "java archive") using `jar xf`.)

### Pseudo Distributed

Let us now continue the exercise and setup a "real" cluster, even though we will only emulate it on our machine 
(to be precise, _inside_ the Docker container that runs _inside_ the virtual machine managed by `vagrant`).

#### Config

Edit the configuration files for the pseudo-distributed cluster version (e.g., using `nano`).

`etc/hadoop/core-site.xml:`

```
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```

`etc/hadoop/hdfs-site.xml:`

```
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```

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
