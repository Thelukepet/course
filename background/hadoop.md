apt-get install wget rsync ssh
service ssh start

wget http://ftp.nluug.nl/internet/apache/hadoop/common/hadoop-2.7.3/hadoop-2.7.3.tar.gz
tar xzvfp hadoop-2.7.3.tar.gz
cd hadoop-2.7.3

## NOW TRY:
## https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#Standalone_Operation



## TODO:
## CHECK IF NECESSARY:
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys


## NOW TRY:
## https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#Pseudo-Distributed_Operation

echo export JAVA_HOME=$JAVA_HOME >> ~/.bashrc



## NOW TRY:
## https://hadoop.apache.org/docs/r2.7.3/hadoop-project-dist/hadoop-common/SingleCluster.html#YARN_on_a_Single_Node

sbin/start-dfs.sh

bin/hdfs dfs -mkdir /user
bin/hdfs dfs -mkdir /user/root

bin/hdfs dfs -put etc/hadoop input
bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar grep input output 'dfs[a-z.]+'
bin/hdfs dfs -get output output

bin/hdfs dfs -ls hdfs://localhost:9000/user/root/input

sbin/stop-dfs.sh


