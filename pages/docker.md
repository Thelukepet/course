---
layout: page
title: Using the Coursera Docker Image
description: How to use the Coursera Docker Image on the machines in the Huygens terminal rooms
---

# Docker

In the course, we will use docker to ensure that we work on the same configuration of the development environment.

## Setup Docker

Docker is already setup in the HG terminal rooms.

You should be part of group `docker`, which you can check by issuing the `groups` command.
If you do not see group `docker` listed in the output, you may check the following:
    [[ -n "`grep $USER /etc/group | grep docker`" ]] && echo yes

If you do not see "yes", you still have to be added to the group - please send mail to `arjen@cs.ru.nl` with your username ($USER).
If you do, then the proper solution is to logout and login again; 
a workaround is to issue the following command in the terminal you want to run docker from:
    exec su -l $USER

## Using Docker

A minimal test:

    docker run hello-world

## Start an image

When running the image (i.e., starting a container), we will request the web interface to be exposed (port 80) so you can follow the tutorial using your local browser.

    docker run --hostname=quickstart.cloudera --privileged=true --name=CDH -t -i -p 80 docker.io/cloudera/quickstart /usr/bin/docker-quickstart

Using the `-i` option opens an interactive shell, `-t` creates a pseudo-TTY. 
You can detach the terminal with key sequence `Ctrl-p``Ctrl-q`.

You can assign a name to a container with, e.g., `--name=CDH`; then subsequent commands can use the given name instead of its hash, e.g., `docker port CDH`.

**TODO:** 
more on attach detach
more on exec

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

