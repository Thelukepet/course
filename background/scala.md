---
layout: page
title: Installing Scala
description: Scala in the Spark Notebook Docker Image on the machines in the Huygens terminal rooms
---

# Scala

## Spark-notebook container

Start a spark-notebook container, and open a shell inside the container (replace HASH by the right value):

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

