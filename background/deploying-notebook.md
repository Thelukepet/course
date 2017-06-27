---
layout: page
title: Deploying
tagline: Deploying your notebook to the cluster
description: Explanations to install and run on Surfsara
---

# Deploying your notebook to the cluster

### Converting notebook to Scala source
Download as -> Scala

Wrap in main function:
```
Object cells {
    def main(args: Array[String]) {
        // all code here (even better: refactor!)
    }
}
```

Remove all the lines that start with `:` as these SparkNotebook directives can not be used in Scala. If you use these to import a library, it should be added to the `.sbt` file instead. You should also remove the line that start with `reset( lastChanges=...)`.

In a Spark notebook, you get the SparkContext (`sc`) for free. You have to do this yourself:
```
val conf = new SparkConf().setAppName("RUBigDataApp")
val sc = new SparkContext(conf)
```

In Scala, you can't reassign to a variable declared with `val`. In the WARC for Spark notebook, `warcc` is declared and assigned to twice. Change the first declaration to `var warcc = ...` and remove `val` from the second.

### Compiling to a jar
Make sure your program follows the directory structure in the assignment repository for the commoncrawl (i.e. `.sbt` file in the root, source file in `src/main/scala/org/rubigdata/`. Use `sbt assembly` to create a jar with all dependencies included. The jar will be located in `target/scala-2.x/`.


### Deploying to the cluster
If you want to use your own data, you should put it in hdfs first. This can be done with:
```
hdfs dfs -put /path/to/file
```

You can first try your application locally by running `spark-submit myprogram.jar`. If this works, you can then deploy it to the cluster.
