---
layout: page
title: Self Contained Apps 
description: Run your own Spark applications written in Scala on Hathi 
---

## Running Self Contained Apps on Hathi

So far, we have only used Spark in an interactive setting, making best use of the Spark Notebook.

**You should definitely develop your project like that:
locally on your own machine using small test data, taken as samples from the real Commoncrawl.**

However, eventually, we will want to run a program on the real cluster, using the actual Web data.
Unfortunately however, using Spark interactively from Spark Notebook does not
work properly (yet?) on `hathi`, the national [Hadoop cluster](https://userinfo.surfsara.nl/systems/hadoop/description).

This instruction will get you up and running with developing _standalone_ Spark solutions that can be
executed on the cluster, assuming that you have claimed success on running the `SparkPi` example from
the Docker image provided by SurfSara.

### Preparation

First install the _Simple Build Tool_ `sbt` on SurfSara's Docker image.
Hereto, follow the instructions from [`scala-sbt.org`](http://www.scala-sbt.org/0.13/docs/Installing-sbt-on-Linux.html);
you are root in the docker container, so do not need to `sudo` these commands:

```
apt-get update
apt-get install apt-transport-https
```

Myself, I issue `apt-get install vim mlocate` as well, but feel free to skip this of you do not use
these tools.

Continue to install the Scala Build Tool (SBT) from a non-standard location.

_Note: the default `sbt` package for Ubuntu is a newer version that does not work together with JDK 1.7._

```
echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
apt-get update
wget -c https://bintray.com/artifact/download/sbt/debian/sbt-0.13.16.deb
dpkg -i sbt-0.13.16.deb
```

This last command takes a few minutes to execute; go treat yourself on a cup of coffee or tea!

You should use the rest of the waiting time to glance over [this blog post on `sbt`](http://xerial.org/blog/2014/03/24/sbt/).

### OpenJDK security policy fix

SurfSara started to use [jitpack.io](https://jitpack.io/) to distribute libraries like the `warcutils`, 
which causes however errors in combination with SBT and the OpenJDK 1.7 JVM installed on their machines.
To resolve these errors (`java.security.InvalidKeyException: EC parameters error`) you have to issue:

```
apt-get install libbcprov-java
```

Followed by this long command to configure the JDK to use the just installed `BouncyCastle` security provider:

```
ln -s /usr/share/java/bcprov.jar /usr/lib/jvm/java-7-openjdk-amd64/jre/lib/ext/bcprov.jar \
        && awk -F . -v OFS=. 'BEGIN{n=2}/^security\.provider/ {split($3, posAndEquals, "=");$3=n++"="posAndEquals[2];print;next} 1' \
             /etc/java-7-openjdk/security/java.security > /tmp/java.security \
        && echo "security.provider.1=org.bouncycastle.jce.provider.BouncyCastleProvider" >> /tmp/java.security \
        && mv /tmp/java.security /etc/java-7-openjdk/security/java.security
```

Source for this magic: [Fix by Travis project](https://github.com/travis-ci/travis-ci/issues/8503). 
_Thank you Travis!_

### `RUBigDataApp`

Now we can start to actually develop the Standalone Spark App.

Glance over the Spark project's documentation on creating 
[self-contained Spark apps](http://spark.apache.org/docs/2.1.2/quick-start.html#self-contained-applications)
to find out how to create a standalone Spark application and subsequently run the application using 
[`spark-submit` for a yarn cluster](http://spark.apache.org/docs/2.1.2/running-on-yarn.html#launching-spark-on-yarn).
Spark documentation on [launching jobs from scala](http://spark.apache.org/docs/2.1.2/programming-guide.html#launching-spark-jobs-from-java--scala)
and [submitting applications](http://spark.apache.org/docs/2.1.2/submitting-applications.html) are also useful background reads.

The official documentation remains rather concise, so let us walk through the process with an actual (but _overly simple_) 
example of a Spark application written in Scala.

<!--
You can find the sample application in your assignment repository (after accepting it in the classroom).
-->

Download a compressed archive [`rubigdata.tgz`](rubigdata.tgz) with the same code from the 
course website into your Docker container, and unpack the archive.

```
cd /hathi-client/spark
wget http://rubigdata.github.io/course/background/rubigdata.tgz
tar xzvfp rubigdata.tgz
cd rubigdata
```

Copy the included (very small) [text file](rubigdata/rubigdata-test.txt) into your home directory 
on the cluster; feel free to first modify the sample textfile before copying it to HDFS.

```
hdfs dfs -put rubigdata-test.txt
```

The two inputs to building trivial sample app `RUBigDataApp` are the 
[`rubigdata.sbt`](rubigdata/rubigdata.sbt.txt) build file and the actual code, 
provided in [`rubigdata.scala`](rubigdata/src/main/scala/org/rubigdata/RUBigDataApp.scala.txt).
The directory structure provided through the compressed archive is important; 
for the details, look into the `sbt` documentation.

Build the stand-alone application: 

```
sbt package
```

The result is a `jar` file that we can execute on `hathi` using Spark's `spark-submit` command:

```
spark-submit --master yarn --deploy-mode cluster /hathi-client/spark/rubigdata/target/scala-2.11/rubigdataapp_2.11-1.0.jar
```

_First inspect the 
[`RUBigDataApp.scala` code](rubigdata/src/main/scala/org/rubigdata/RUBigDataApp.scala.txt), 
so you know what to expect as output!_

Follow the Spark application's output by opening the URL that is given on `stdout` 
(this URL looks like `http://head05.hathi.surfsara.nl:8088/proxy/application_1486393309284_11065/`).

While the application runs, you will see the already familiar Spark UI (visible in standalone mode at `localhost:4040`); 
once the application is finished, you get a summary page, from which you can inspect the logs.
In our simple example program, these logs contain the output: character counts in the textfile we stored in our home directory on HDFS.

### Packaging and Including External Libraries

It makes no sense to write code for subtasks like parsing WARC files from scratch.
To including external libraries, two [`sbt` plugins](http://www.scala-sbt.org/release/docs/Community-Plugins.html) 
are very useful to simplify the workflow: 
[`sbt-assembly`](https://github.com/sbt/sbt-assembly)
and 
[`sbt-pack`](https://github.com/xerial/sbt-pack) (follow the links for their github repositories).

With `sbt-assembly`, you easily create a single large `jar` that includes all dependencies, without having to worry about setting
the right `CLASSPATH` and/or adding all the correct parameters for including libraries when issuing `spark-submit`.
The `sbt-pack` plugin gathers all dependent libraries in a single location under `target/pack`, to ease copying these jars 
into the Spark Notebook docker container, for example.

### Next steps

Before trying to analyze Commoncrawl data, I suggest to run a few spark exercises from [assignment 3](../assignments/A3-spark.html).
You can save any notebook as just its Scala code by using the _save as_ option from the file menu.

_Include a brief discussion, perhaps using a concrete example, in the introductory part of your blog post, 
taking your readers along on the road from interactive small scale prepatory experiments to actual use of a large cluster._
