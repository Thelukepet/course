---
layout: page
title: Installing Scala
description: Scala in the Spark Notebook Docker Image on the machines in the Huygens terminal rooms
---

# Scala

## Scala Docker container

The first option is to install a different Docker container that comes with Scala pre-installed:

```
docker run -it --rm williamyeh/scala
```

Of course, you can first start a shell in that container, by appending `/bin/bash` to the command above;
then start a Scala interpreter using `scala` from the commandline.

## Spark-notebook container

You can _also_ install Scala in the Spark-Notebook container.
Start the container and open a shell (replace HASH by the right value):

```
docker exec -it $HASH /bin/bash
```

## Install Scala

Issue the following commands:

```
apt-get install wget
wget www.scala-lang.org/files/archive/scala-2.11.8.deb
dpkg -i scala-2.11.8.deb
```

Maybe you also need the following commands:

```
apt-get update
apt-get install scala

```

## Install Scala build-tool

Advanced Scala uses the build-tool, `sbt`.

You can install it using the following sequence of commands:

```
echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823
apt-get install apt-transport-https
apt-get update
apt-get install sbt
```

[Back to Assignment A1b](../assignments/A1b-docker.html)

