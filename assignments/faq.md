---
layout: page
title: Frequently Asked Spark/Scala Questions
description: Random bits of useful Spark/Scala tips & tricks
---

## Tips & Tricks

### Package to Notebook

Here's how to get all the dependencies for working with WARC files resolved in the spark-notebook container.

In the `surfsara/hathi` docker container do:

```
cd rubigdata
sbt pack
cd target/pack
make archive
scp target/RUBigDataApp-1.0.tar.gz ${USERNAME}@lilo
```

In the `spark-notebook` docker container do:

```
scp ${USERNAME}@lilo.science.ru.nl:RUBig* .
tar xzvfp RUBigDataApp-1.0.tar.gz ./lib
rm ./lib/rubigdataapp_2.10-1.0.jar
```

Now the `RUBigDataApp.scala` code will run in your Spark notebook.

### Configuration

Start `spark-shell`.

```
sc.version
sc.getConf.toDebugString
```

The scala version (and the specific JVM) are printed right under the logo when starting `spark-shell`.

### See also:

* Additional info on [`spark-shell`](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/content/spark-shell.html)


