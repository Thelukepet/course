---
layout: page
title: User namespaces
description: How to get docker to run in user namespace
---

# Docker

## Problem

Mounting the filesystem inside a docker image should not be allowed:

    docker run -v /:/mnt -it --rm busybox

This can be fixed by remapping the user namespace, a pretty default option on modern kernels.

See also:

+ https://docs.docker.com/engine/reference/commandline/dockerd/#/starting-the-daemon-with-user-namespaces-enabled
+ https://blog.yadutaf.fr/2016/04/14/docker-for-your-users-introducing-user-namespace/

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

See also:

+ https://github.com/docker/docker/issues/25929
+ https://github.com/procszoo/procszoo/wiki/How-to-enable-%22user%22-namespace-in-RHEL7-and-CentOS7%3F
