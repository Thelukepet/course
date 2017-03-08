---
layout: page
title: Hadoop
tagline: Running Hadoop in your Docker container
description: Explanations to install and run Hadoop
---

## Map Reduce on HDFS

Lectures 3 to 5 discussed distributed filesystems and Map-Reduce, that in practice today are almost equivalent to "Hadoop".

In this assignment, we install Hadoop on our own "pseudo-cluster", and use Map-Reduce to do some basic count operations.

The tutorial was tested inside the Docker container for Spark-Notebook that we setup in the previous lab sessions;
you would use the `docker exec` command to start a shell inside the image first.
The HDFS filesystem that you create and use in this tutorial, will be re-used in the later lab sessions on Spark.

### Setup

First, we need to setup distributed filesystem `HDFS` and the Map-Reduce tools.

Execute the following commands to install missing tools, setup the environment for `ssh localhost` 
and ensure the `JAVA_HOME` variable is set upon login:

```
## Missing software
apt-get install wget rsync ssh
service ssh start

## Prepare ssh localhost
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys

## Add JAVA_HOME to .bashrc
echo export JAVA_HOME=${JAVA_HOME} >> ${HOME}/.bashrc
```

Install the necessary basic tools, download Hadoop version 2.7.3, and unpack the code:

```
wget http://ftp.nluug.nl/internet/apache/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz
tar xzvfp hadoop-2.7.3.tar.gz
cd hadoop-2.7.3
```

### Standalone

Try out if everything works by using the _standalone_ version first.

Instructions:
[Standalone](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#Standalone_Operation)


### Pseudo Distributed

The exercise is more interesting if we setup a "real" cluster, even though we will only emulate it on our machine 
(to be precise, _inside_ the Docker container that runs _inside_ the virtual machine managed by `vagrant`).

```
sbin/start-dfs.sh

bin/hdfs dfs -mkdir /user
bin/hdfs dfs -mkdir /user/root

bin/hdfs dfs -put etc/hadoop input
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar grep input output 'dfs[a-z.]+'
bin/hdfs dfs -get output output

bin/hdfs dfs -ls hdfs://localhost:9000/user/root/input

sbin/stop-dfs.sh
```

See also the Hadoop documentation for running a
[Pseudo-Distributed Cluster](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#Pseudo-Distributed_Operation)

### See also

Real clusters use a cluster management system like _Yarn_ to start and stop services and manage map-reduce jobs.

If you are interested to see how that works (voluntarily, not required for the course), 
you could try the additional steps from the Hadoop documentation to
[run Yarn on a single node](https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#YARN_on_a_Single_Node)



