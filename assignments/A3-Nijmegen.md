---
layout: page
title: Assignment 3 
description: Spark Data Frame API
---

## Nijmegen - Spark Data Frame API

The goal of assignment 3 is to carry out an analysis on structured open data.

Start the docker container (refer to [assignment A1.b](A1b-docker.html)
and the [Spark Notebook background](../background/spark-notebook.html)
if necessary).

### Big Data Spark Nijmegen notebook

Download the course's notebook 
[Big Data Nijmegen](http://rubigdata.github.io/course/assignments/BigData-Nijmegen.snb).

Open [localhost:9000/tree/BigData](http://localhost:9000/tree/BigData) in your browser, 
and open the notebook you just installed inside your Spark Notebook container.

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

### Blog post 

The city of Nijmegen provides a variety of resources as open data at [nijmegen.nl/opendata](http://www.nijmegen.nl/opendata).

Our goal is to analyze these together - to investigate if we can say anything about the relation between population statistics in areas of the city and the activities that are organized there.

Specifically, we will integrate the following three data sets:

* `vBoaEvents_all.csv` and `vBoaEventsLoc_all.csv` for events and their locations
* `BAG_ADRES.csv` for streetnames and their quarters
* `opendata_stadsgetallen.accdb` for a variety of statistics about the population

Imagine writing about an open data project, where you want to explain how your
findings are backed up.

