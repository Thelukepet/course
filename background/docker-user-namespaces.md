---
layout: page
title: User namespaces
tagline: Securing Docker by enabling User Namespaces
description: How to get Docker to run in User Namespaces
---

# Docker

## Problem

Docker is a very convenient instrument to overcome configuration problems and speed up development, 
and I consider it a standard tool for modern development.
Mounting the filesystem inside a docker image should however not give unauthorized access to all the files in the filesystem...
i.e., a user issuing

    docker run -v /:/mnt -it --rm busybox

should not be able to read or write files that would require root rights otherwise.

The problem can be fixed by configuring the Docker daemon to _remap the user namespace_, a feature considered stable on 
modern Linux kernels (4.3 and newer).

See also:

+ The Docker documentation on 
  [enabling user namespaces](https://docs.docker.com/engine/reference/commandline/dockerd/#/starting-the-daemon-with-user-namespaces-enabled);
+ An [insightful blogpost](https://blog.yadutaf.fr/2016/04/14/docker-for-your-users-introducing-user-namespace/) about this feature.

## Validation

Checked the feature using Docker on my Redhat FC25 machine:

    [arjen@apc ~]$ sudo ls -al /etc/help.txt
    -rw-------. 1 root root 8 Feb 13 11:48 /etc/help.txt

    [arjen@apc ~]$  docker run -v /:/mnt -it --rm busybox
    / # cat /mnt/etc/help.txt
    cat: can't open '/mnt/etc/help.txt': Permission denied
    / #

I.e., because the root inside the image is mapped on a normal user (`dockremap`) that cannot access the file.

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

## Kernel

In Redhat FC25, I had to adapt the default kernel setting as follows:

    sudo grubby --args="user_namespace.enable=1" --update-kernel="$(grubby --default-kernel)"

(Check if that is necessary by issuing `cat /proc/cmdline`.)

Then reboot the machine.

## Sharing files

Tricky: I cannot get this to work with a directory in ${HOME}.
However... I can in a different path.

Create user and group `dockerfiles` with uid and gid equal to the dockremap user/group-id in `/etc/subuid` and `/etc/subgid`, respectively.

A folder `/export/data/arjen/my-data` can now be shared in a container after `sudo chown dockerfiles:dockerfiles /export/data/arjen/my-data`:

    docker run -it -v /export/data/arjen/my-bigdata/:/mnt/my-bigdata busybox sh

_TODO: check if this also works when simply using the numeric value of the uid/gid._

## See also

+ https://github.com/docker/docker/issues/25929
+ https://github.com/procszoo/procszoo/wiki/How-to-enable-%22user%22-namespace-in-RHEL7-and-CentOS7%3F


