---
layout: page
title: Frequently Asked Spark/Scala Questions
description: Random bits of useful Spark/Scala tips & tricks
---

## Tips & Tricks

### Configuration

Start `spark-shell`.

```
sc.version
sc.getConf.toDebugString
```

The scala version (and the specific JVM) are printed right under the logo when starting `spark-shell`.

### See also:

* Additional info on [`spark-shell`](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/content/spark-shell.html)

