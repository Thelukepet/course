---
layout: page
title: Assignment 1B
tagline: Docker
description: First steps using Docker
---

# Docker Environment

We will use [Docker](https://docs.docker.com/linux/) to remove the burden on unifying working environments.

## Introduction

First, go over the [basic tutorial](https://docs.docker.com/engine/getstarted/) to gain familiarity with Docker usage.

The easiest solution is to use Docker on the computers in the terminal room; the only drawback that you
have to be in the terminal room to boot the machine in Linux (note that you can use `ssh` once it is booted, but,
anyone can decide to reboot the machine, and all machines are shutdown at the end of the day).

Alternatively, install Docker on your own laptop or home computer.
While this route is **not officially supported**, I will provide a few pointers to get you started, see below.

## Setting up Docker and Vagrant

Unfortunately, Docker is not supported in the HG00.137 machines -
this may or may not change during the semester (security concerns).

As a workaround, for now, we work inside a virtual machine that we manage using `vagrant`.

### Vagrant

Vagrant has been installed and is ready for use in the terminal room HG00.137. 

You will first want to do the [getting started](https://www.vagrantup.com/docs/getting-started/)
if you never worked with vagrant before.

We use vagrant with the `virtualbox` provisioner, and a configuration that stores your virtual
machines in directory `/var/tmp/${USER}`. 

_Leaving a single virtual machine in the terminal rooms is okay!_
But, we do request that you:

+ `vagrant destroy` unused virtual machines;
+ use the same computer every week (whenever possible).

Virtual machines consume considerable disk and that volume has only 16G available (`df --si /var/tmp`).

### Vagrant setup

Create the project directory and `cd` into it:

    mkdir bigdata
    cd bigdata

Download the [`Vagrantfile`](https://raw.githubusercontent.com/rubigdata/course/gh-pages/assignments/Vagrantfile) that I prepared, and save it to the project directory you just created, or issue:

    wget https://raw.githubusercontent.com/rubigdata/course/gh-pages/assignments/Vagrantfile

Start the virtual machine and `ssh` into it:

    vagrant up
    vagrant ssh

When you are finished, you exit the shell, and may want to `vagrant suspend` the virtual machine - you can always restart it with `vagrant up`.

## Using Docker

A minimal test shows that the docker client can start so-called containers:

    docker run hello-world

If Docker is new to you, it is highly recommended to learn more about its architecture and usage on the Docker
site; specifically, the rest of the course material will assume that you followed steps **two** and **three** 
from the excellent documentation.

Follow the tutorial starting from [**step two**](https://docs.docker.com/engine/getstarted/step_two/).

In the course, we will only use docker with images provided by others, so it is not necessary to continue to 
step four, although I will not stop you if you are starting to get the hang of it!

_Continue only after having seen the ASCII art using your own setup!_

### DIY

If you opt to install docker on your own hardware (and work independently from the availability of
computers in the Huygens building), start below at the link corresponding to your operating system of choice,
and start with **step one** to get docker up and running on your own hardware:

- [Windows](https://docs.docker.com/windows/)
- [OS X](https://docs.docker.com/mac)
- [Linux](https://docs.docker.com/linux/)

Students who install Docker on their own machine will need command `docker-machine ip`
and are recommended to read the [docker machine docs](https://docs.docker.com/machine/get-started/).

BTW, `vagrant` is available on all platforms as well, so you could just do what we do on the terminal room machines;
but if your host is a Linux machine, I recommend to see if you cannot just run Docker directly.

## Spark Notebook

In the assignments, we get hands-on experience with [Spark Notebook](http://spark-notebook.io).

### Setup (first time only)

If this is the first time that you will start Spark Notebook, you need to use its image and initialize a container:
follow the instructions given in [Spark Notebook for the big data course](../background/spark-notebook.html).

### Starting the Spark Notebook container

Start up a container with `docker run` (only if it is not running of course);
and simply open [localhost:9000](http://localhost:9000/) in your browser.

If you successfully started the Spark Notebook container, then opening [localhost:9000](http://localhost:9000/) will
show you the Spark Notebook UI in the browser. 

_Why don't you try out some of the scala things you worked with in the first week!_

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
