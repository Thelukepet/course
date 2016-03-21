---
layout: page
title: Test A
tagline: RU Big Data NWI-IBC036-2015
description: Detailed information of the material examined in Test A
---

## Test A

### Objectives

The objective of Test A is a summative assessment of your understanding of the course material for the _first 5 lectures_ of the course.

### Introduction

Lecures 1 and 2 gave a broad introduction to big data, and the notion of programming the data center. You should understand the trends in hardware that motivate the rise of shared-nothing architectures for processing big data. I expect basic knowledge of the architecture of the compute infrastructure in a data center. You comprehend the difference between latency and throughput.

### Map Reduce

Lectures 2 and 3 gave a more detailed introduction to Map Reduce and the key features of the Google distributed file system (and HDFS). We discussed design patterns that to achieve more control over the execution (i.e., tools for synchronization). Focus on the basic notions of mappers, reducers, combiners and partitioners, and make sure you understand the design patterns discussed in class. Background reading is Chapter 3 from Lin and Dyer (see below) and the HDFS paper discussed plenary.

### Spark

The remaining lectures discussed Spark, focusing on the notion of RDD (lecture 4) and its execution model (lecture 5). You are expected to understand why Spark can deliver higher performance than Map Reduce, especially on interactive and iterative algorithms. Background reading is Chapter 2 of Zaharia's PhD thesis (see below).

Lectures 6 and 7 introduced the more recent abstractions of Data Frame (lecture 6) and the machine learning library MLLib (lecture 7). These last two lectures are not part of examination in Test A.

### Course Information

The main source to study is the series of slides for the lectures.

Additional information:
- The background paper [on HDFS](https://blackboard.ru.nl/bbcswebdav/pid-2407975-dt-content-rid-6666363_4/xid-6666363_4).
- Chapter 3 "MapReduce algorithm design" in Data-Intensive Text Processing with MapReduce, by Jimmy Lin and Chris Dyer 
([online version](https://lintool.github.io/MapReduceAlgorithms/ed1n.html)).
- Chapter 2 [Resilient Distributed Datasets](https://blackboard.ru.nl/bbcswebdav/pid-2422582-dt-content-rid-6743516_4/xid-6743516_4), from Matei Zaharia's PhD thesis.

