---
layout: page
title: Assignment 1B
tagline: Docker
description: First steps using Docker
---

# Docker Environment

We will use [Docker](https://docs.docker.com/linux/) to remove the burden on unifying working environments.

## Introduction

First, go over the [quickstart](https://docs.docker.com/engine/quickstart/) to gain familiarity with Docker usage.

The easiest solution is to use Docker on the computers in the terminal room; the only drawback that you
have to be in the terminal room to boot the machine in Linux (note that you can use `ssh` once it is booted, but,
anyone can decide to reboot the machine, and all machines are shutdown at the end of the day).

Alternatively, install Docker on your own laptop or home computer.
While this route is **not officially supported**, I will provide a few pointers to get you started, see below.

## Docker in HG00.023

Docker has been installed and is ready for use in terminal room HG00.023.

Using the client will however *only* work correctly if your account is part of group `docker`; 
check this by issuing the `groups` command.
If you do not see group `docker` listed in the output, you may check the following:

    [[ -n "`grep $USER /etc/group | grep docker`" ]] && echo yes

If you do not see "yes", you still have to be added to the group - please send mail to `arjen@cs.ru.nl` with your username ($USER).
If you do see a "yes", the proper solution is to logout and login again; 
a workaround is to issue the following command in the terminal you want to run docker from:
    exec su -l $USER

## Using Docker

A minimal test shows that the docker client can start so-called containers:

    docker run hello-world

If Docker is new to you, it is highly recommended to learn more about its architecture and usage on the Docker
site; specifically, the rest of the course material will assume that you followed steps **two** and **three** 
from the excellent documentation.
Follow the tutorial on any computer booted to Ubuntu in terminal room HG 00.023, 
starting from [**step two**](https://docs.docker.com/linux/step_two/).

Alternatively, if you opt to install docker on your own hardware (and work independently from the availability of
computers in the Huygens building), start below at the link corresponding to your operating system of choice,
and start with **step one** to get docker up and running on your own hardware:

- [Windows](https://docs.docker.com/windows/)
- [OS X](https://docs.docker.com/mac)
- [Linux](https://docs.docker.com/linux/)

In the course, we will only use docker with images provided by others, so it is not necessary to continue to 
step four, although I will not stop you if you are starting to get the hang of it!

Students who install Docker on their own machine will need command `docker-machine ip`
and are recommended to read the [docker machine docs](https://docs.docker.com/machine/get-started/).

## Starting a container for Spark Notebook

In the assignments, we get hands-on experience with [Spark Notebook](http://spark-notebook.io).

If this is the first time that you will start Spark Notebook, you need to use its image and initialize a container:
follow the instructions given in [Spark Notebook for the big data course](../background/spark-notebook.html).
Otherwise, start up a container with `docker run` (only if it is not running of course);
and simply open [localhost:9000](http://localhost:9000/) in your browser.

(Use `docker images` and `docker ps` to find out if another student has taken these steps before on the same machine
you choose to use; in that case, just skip ahead to start the container without loading the image, 
or simply open the browser as above.)

If you successfully started the Spark Notebook container, then opening [localhost:9000](http://localhost:9000/) will
show you the Spark Notebook UI in the browser. 

(It is possible to run the docker container remotely, and open the Spark Notebook in a browser on your laptop, provided
that you know how to tunnel ports 4040 and 9000 to the laptop; for example using `ssh -L` or the right tunneling 
settings to `Putty`.)

## Clean up

Once you are fluent in using the docker client, it is easy to forget that every container and image used takes up 
disk space on the local machine. Please clean up regularly!

The following command lists the running containers:

    docker ps -f status=running

Any running containers you do not use, can be stopped using `docker stop HASH`.
Next, you may remove all inactive, exited containers that you do not plan to restart by issuing one shell command:

    docker ps -f status=exited -q | \
      xargs docker rm

When we complete the first phase of the course, we will not use these docker images any more.
You may for example remove an unused cloudera image from your machine:

    docker images -q docker.io/cloudera/quickstart | \
      xargs docker rmi

## See also

Optional extra reading (not required for the course):

* Advanced Docker with the [Docker book](http://www.dockerbook.com/) (not free, ~EUR 10).
