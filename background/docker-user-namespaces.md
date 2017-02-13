---
layout: page
title: User namespaces
description: How to get docker to run in user namespace
---

# Docker

## Problem

Mounting the filesystem inside a docker image should not be allowed:

    docker run -v /:/mnt -it --rm busybox

This can be fixed by remapping the user namespace, a feature considered stable on modern Linux kernels (4.3 and newer).

See also:

+ The Docker documentation on 
  [enabling user namespaces](https://docs.docker.com/engine/reference/commandline/dockerd/#/starting-the-daemon-with-user-namespaces-enabled);
+ An [insightful blogpost](https://blog.yadutaf.fr/2016/04/14/docker-for-your-users-introducing-user-namespace/) about this feature.

## Setup

Make sure that `/etc/docker/daemon.json` at least contains:

    {
    	"userns-remap": "default"
    }

Maybe necessary:

    sudo touch /etc/subgid
    sudo touch /etc/subuid

Then restart the docker daemon:

    sudo systemctl restart docker

## Validation

Checked the feature using Docker on my Redhat FC25 machine:

    [arjen@apc ~]$ sudo ls -al /etc/help.txt
    -rw-------. 1 root root 8 Feb 13 11:48 /etc/help.txt

    [arjen@apc ~]$  docker run -v /:/mnt -it --rm busybox
    / # cat /mnt/etc/help.txt
    cat: can't open '/mnt/etc/help.txt': Permission denied
    / #

I.e., because the root inside the image is mapped on a normal user (`dockremap`) that cannot access the file.

## Kernel

In Redhat FC25, I had to adapt the default kernel setting as follows:

    sudo grubby --args="user_namespace.enable=1" --update-kernel="$(grubby --default-kernel)"

(Check if that is necessary by issuing `cat /proc/cmdline`.)

Then reboot the machine.

See also:

+ https://github.com/docker/docker/issues/25929
+ https://github.com/procszoo/procszoo/wiki/How-to-enable-%22user%22-namespace-in-RHEL7-and-CentOS7%3F
