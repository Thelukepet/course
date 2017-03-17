# Exact instructions for doing the wordcount
Ensure you are logged in on ubuntu on a Huygens terminal
## Setting up the virtual machine
```Bash
mkdir bigdata
cd bigdata
wget https://raw.githubusercontent.com/rubigdata/course/gh-pages/assignments/Vagrantfile
vagrant up
```
This sets up a [virtual machine](https://en.wikipedia.org/wiki/Virtual_machine) and starts it.

## Setting up docker in the VM
First you [ssh](https://en.wikipedia.org/wiki/Secure_Shell) into your virtual machine so you can issue commands to your virtual machine.
Then you download(pull) a docker image, then create a docker container based on that (Note the difference between images and containers).
The `-p 9001:9001` sets up for the port 9001 to map to the port 9001 on the virtual machine, and in the Vagrantfile we specified that the port 9001 on the virtual machine maps to the port 9001 on the host computer.
Effectively this means that an application that listens on port 9001 inside docker can be reached from a browser on your host machine.

Similiarly there is a shared folder between the virtual machine and the host machine, namely `/vagrant`.
Anything you put inside your `bigdata` folder will show up in `/vagrant` and vice-versa.
The same thing happens for `/mnt/vagrant` inside your docker container, so any files in your `bigdata` folder are exposes to the virtual machine and your docker container.
```Bash
vagrant ssh
docker pull andypetrella/spark-notebook:0.7.0-scala-2.11.8-spark-2.1.0-hadoop-2.7.3-with-hive
CHASH=$(docker create -p 9001:9001 -p 4040-4045:4040-4045 -v /vagrant:/mnt/bigdata andypetrella/spark-notebook:0.7.0-scala-2.11.8-spark-2.1.0-hadoop-2.7.3-with-hive)
```
The `docker create` command creates a container and returns its container id, that is assigned to variable `CHASH`.
```Bash
docker start $CHASH
docker logs $CHASH
```
You now have a running docker container, and you should be able to access the spark notebook at [localhost:9001](http://localhost:9001).

## Setting up inside the container
First you need to open an interactive bash shell inside your docker container.
Next some missing software needs to be installed and the ssh server needs to be
started and configured.
Next we download the hadoop distributed file system and set it up.
```Bash
docker exec -it $CHASH /bin/bash
apt-get install wget rsync ssh nano
export TERM=xterm
service ssh start
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys

wget http://ftp.nluug.nl/internet/apache/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz
tar xzvfp hadoop-2.7.3.tar.gz
cd hadoop-2.7.3
```
`nano etc/hadoop/core-site.xml`
Make sure this file looks like
```XML
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```
`nano etc/hadoop/hdfs-site.xml`
Make sure this file looks like
```XML
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```
## Running Hadoop 
Format a Hadoop File System(hdfs), start it up and create a directory for your
user on the hdfs. Keep in mind the hdfs is completely seperate from your container
filesystem.
```Bash
bin/hdfs namenode -format
sbin/start-dfs.sh
bin/hdfs dfs -mkdir -p /user/root
```
## Running the WordCount
Next we need to do some setup for the wordcount.
Download the shakespeare collection and WordCount program, then set up some
environment variables to make sure you can compile and run Java programs
on hadoop.
Using the `bin/hdfs` program we can interact with our Hadoop filesystem, here we put
a file into the filesystem.
```Bash
wget http://www.gutenberg.org/ebooks/100.txt.utf-8
wget https://gist.githubusercontent.com/WKuipers/87a1439b09d5477d21119abefdb84db0/raw/c327b9f74d30684b1ad2a0087a6de805503379d3/WordCount.java
bin/hdfs dfs -put 100.txt.utf-8 input
echo export JAVA_HOME=${JAVA_HOME} >> ${HOME}/.bashrc
export PATH=${JAVA_HOME}/bin:${PATH}
export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
```
Next we need to compile `WordCount.java` to `.class` files, and then pack those
`.class` files into a `wc.jar`.
Next we can run the program using `bin/hadoop` and give it an input file and a 
output directory.
The output directory must not exist or the program will refuse to generate output.
To ensure we can easily run the program again we use `bin/hdfs` to retrieve our
results and then delete the output directory.
You can then use nano or another program to inspect the output.
```Bash
bin/hadoop com.sun.tools.javac.Main WordCount.java
jar cf wc.jar WordCount*.class
bin/hadoop jar wc.jar WordCount input output
bin/hdfs dfs -get output/part-r-00000
bin/hdfs dfs -rm -r output
nano part-r-00000
```

## Gracefully shutting down your setup
*Inside your virtual machine*
```Bash
docker stop container_hash
```
Inside your bigdata folder on the host machine
```Bash
vagrant suspend
```
