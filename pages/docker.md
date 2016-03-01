---
layout: page
title: Using the Coursera Docker Image
description: How to use the Coursera Docker Image on the machines in the Huygens terminal rooms
---

# Docker

In the course, we will use docker to ensure that we work on the same configuration of the development environment.

## Setup Docker

Docker has been installed and is **ready for use** in terminal room HG00.023.

Using the client will *only* work correctly if your account is part of group `docker`; check this by issuing the `groups` command.
If you do not see group `docker` listed in the output, you may check the following:
    [[ -n "`grep $USER /etc/group | grep docker`" ]] && echo yes

If you do not see "yes", you still have to be added to the group - please send mail to `arjen@cs.ru.nl` with your username ($USER).
If you do see a "yes", the proper solution is to logout and login again; 
a workaround is to issue the following command in the terminal you want to run docker from:
    exec su -l $USER

## Using Docker

A minimal test:

    docker run hello-world

Now, learn more about docker and its usage:
please follow **step two** and **step three** from the excellent documentation.

If you want to install docker on your own hardware, start at the link corresponding to your operating system,
and first go through **step one** to get docker up and running:

- [Windows](https://docs.docker.com/windows/)
- [OS X](https://docs.docker.com/mac)
- [Linux](https://docs.docker.com/linux/)

Otherwise, follow the Linux tutorial on a RU computer booted to Ubuntu in the terminal room, starting from [**step two**](https://docs.docker.com/linux/step_two/)
of the docker tutorial.

In the course, we will only use docker with images provided by others, so it is not necessary to continue to step four, although I will
not stop you if you are starting to get the hang of it!

## Starting a container for Spark Notebook

In the assignments, we get hands-on experience with [Spark Notebook](http://spark-notebook.io).

If this is the first time to start Spark Notebook, you need to initialize a container:
follow the instructions given in [Spark Notebook for the big data course](spark-notebook.html).
Otherwise, start up the container if it is not running yet, or simply open [localhost:9000](http://localhost:9000/) in your browser.

(You can check `docker images` and `docker ps` to find out if another student has taken these steps before on the same machine;
in that case, just skip ahead to start the container without loading the image, or simply open the browser.)

*Etc. Etc.*

## Clean up

While using docker, it is easy to forget that every container and image takes up disk space on the local machine. Please clean up regularly!

Find out the running containers:

    docker ps -f status=running

Any running containers not in use, you may stop using `docker stop HASH`.

Remove the inactive, exited containers that you do not use as follows:

    docker ps -f status=exited -q | \
      xargs docker rm

When we complete the first phase of the course, we will not use the cloudera image any more.
Remove these cloudera images as follows:

    docker images -q docker.io/cloudera/quickstart | \
      xargs docker rmi

