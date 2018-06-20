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
scp target/RUBigDataApp-1.0.tar.gz ${USERNAME}@lilo.science.ru.nl
```

In the `spark-notebook` docker container do:

```
scp ${USERNAME}@lilo.science.ru.nl:RUBigDataApp-1.0.tar.gz .
tar xzvfp RUBigDataApp-1.0.tar.gz ./lib
rm ./lib/rubigdataapp_2.11-1.0.jar
```

Now the `RUBigDataApp.scala` code will run in your Spark notebook.

### Configuration

Start `spark-shell`.

```
sc.version
sc.getConf.toDebugString
```

The scala version (and the specific JVM) are printed right under the logo when starting `spark-shell`.

### Firefox config

Myself, I had quite some trouble getting Kerberos and Firefox to work together correctly.

What helped is to create a clean profile, and set the variable there;
starting Firefox in a terminal where `KRB5_CONFIG` is set to the config file in your homedir.
For example:

    KRB5_CONFIG=${HOME}/.surfsara.krb5.conf firefox -P bigdata

You can set the configuration option for Kerberos manually, or edit the `prefs.js` file for this profile
(`${HOME}/.mozilla/firefox/somehash.bigdata/prefs.js`) to include

    user_pref("network.negotiate-auth.trusted-uris", "https://, hathi.surfsara.nl");

If you need to debug, enable logging of authorization related events as follows:

    export NSPR_LOG_MODULES=negotiateauth:5
    export NSPR_LOG_FILE=/tmp/test.log

### See also:

* Additional info on [`spark-shell`](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/content/spark-shell.html)


