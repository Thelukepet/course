---
layout: page
title: Using the Coursera Docker Image
description: How to use the Coursera Docker Image on the machines in the Huygens terminal rooms
---

# Coursera Docker Image

## Setup

Make sure to have first read [Docker instructions](docker.html).

Then initialize the Cloudera docker image, as follows:
 
## Setup Cloudera docker image in terminal rooms

    docker import - cloudera/quickstart:latest < /vol/practica/BigData/cloudera-docker-image.tar

## Setup Cloudera docker image at home

    docker pull cloudera/quickstart:latest

## Start the Cloudera docker image

When running the image (i.e., starting a container), we will request the web interface to be exposed (port 80) so you can follow the tutorial using your local browser.

    docker run --hostname=quickstart.cloudera --privileged=true --name=CDH -t -i -p 80 docker.io/cloudera/quickstart /usr/bin/docker-quickstart

Get the exposed port using `docker ps` and a subsequent `docker port HASH`.
 
## Using Cloudera docker image

Follow the tutorial on http://localhost:9000/

The original tutorial data is stored in a relational database that you can access as follows:

    mysql --user=retail_dba --password=cloudera  retail_db

*Etc.*


