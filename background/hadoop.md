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
The HDFS filesystem that you create and use in this tutorial, can be re-used in the later lab sessions on Spark.

### Setup

First, we need to setup distributed filesystem `HDFS` and the Map-Reduce tools.

Execute the following commands to install missing tools, setup the environment for `ssh localhost` 
and ensure the `JAVA_HOME` variable is set upon login:

```
## Missing software
apt-get install wget rsync ssh nano
service ssh start

## Prepare ssh localhost
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys

## Add JAVA_HOME to .bashrc
echo export JAVA_HOME=${JAVA_HOME} >> ${HOME}/.bashrc
```

### Intermezzo

If you are not yet (!) fluent in a UNIX environment, view and edit files inside the Docker container using `nano` 
(that we installed in the first command in this tutorial).

Maybe you need to set the `TERM` variable for `nano` before this works; e.g.

```
export TERM=xterm
```

_If that is the case, now use `nano` to add this command as the last line of `$HOME/.bashrc`._ 

If you are exploring the source of the Hadoop examples later on in the lab session, I recommend doing these steps 
on your host machine, and not inside the docker image (or even the vagrant virtual machine); much easier for working 
with your favourite GUI, editors, copy-paste support, _etc. etc._

You can use `scp` to secure copy files into and from your `$HOME/bigdata` directory on the machine you work at.
In Huygens, you can rely on NFS (the Network File System), and copy the file to Linux login server `lilo` 
or `lilo.science.ru.nl` (the file used in this example does however not yet exist):

```
export USERNAME=<your-science-account>
scp share/hadoop/mapreduce/sources/hadoop-mapreduce-examples-2.7.3-sources.jar ${USERNAME}@lilo.science.ru.nl:bigdata
```

A perhaps better alternative is to run the _docker container_ using Docker options to mount a directory from the 
host filesystem inside the container; passing the desired location using `-v /vagrant:/mnt/bigdata`.
If you decide to go this route, then you need to start a new container and redo the above commands (my apologies).
In that case, I recommend to open two more ports, and use the following command 
(you need to take the _HASH_ from `docker images`):

```
docker run -p 9001:9001 -p 4040-4045:4040-4045 -p 50070:50070 -p 8088:8088 -v /vagrant:/mnt/bigdata HASH
```

Both examples assume that we use directory `$HOME/bigdata`, the location where I suggested to put your `Vagrantfile`; 
this directory is automatically mounted by `vagrant` under `/vagrant`, as described in 
[vagrant's _getting started_ documentation](https://www.vagrantup.com/docs/getting-started/synced_folders.html).


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

You unpack the `.jar` file (for "java archive") using `jar xvf` instead.

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



