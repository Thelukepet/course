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

### Testing

Prepare on the host:

```
arjen$ echo Hello to the container > /export/data/arjen/my-bigdata/hi-container.txt
```

Run a container with shared mount:

```
arjen$ docker run -it -v /export/data/arjen/my-bigdata/:/mnt/my-bigdata busybox sh
/ # cd /mnt/my-bigdata/
/mnt/my-bigdata # ls
hi-container.txt
/mnt/my-bigdata # cat hi-container.txt 
Hello to the container
/mnt/my-bigdata # echo Hi from the container > container-hi.txt
```

Ét voilà, the results back on the host:

```
[arjen@apc my-bigdata]$ ls -al
total 16K
drwxrwx---.  2 dockerfiles dockerfiles 4.0K Feb 23 17:29 ./
drwxr-xr-x. 11 arjen       arjen       4.0K Feb 23 17:10 ../
-rw-r--r--.  1 dockerfiles dockerfiles   23 Feb 23 17:29 container-hi.txt
-rw-rw-r--.  1 arjen       arjen         24 Feb 23 17:29 hi-container.txt
arjen$ cat container-hi.txt 
Hi from the container!
```

## See also

+ https://github.com/docker/docker/issues/25929
+ https://github.com/procszoo/procszoo/wiki/How-to-enable-%22user%22-namespace-in-RHEL7-and-CentOS7%3F

## The story continues

Docker added an option to overrule the namespace control per container: `--userns=host`,
see also this [stackoverflow discussion](https://stackoverflow.com/questions/40468739/disable-certain-docker-run-options).

A solution is to start the daemon with an [_authorization plugin_](https://docs.docker.com/engine/extend/plugins_authorization/).

Two options that we may follow:

+ A crude option that simply [disables dangerous options including --userns](https://github.com/ad-freiburg/docker-no-trivial-root);
+ A more sophisiticated approach that [distinguishes users and their access rights](https://sergeyyakubov.github.io/hpc/docker/2017/03/13/docker-noroot.html);
+ An even more advanced solution called [TwistLock](https://github.com/twistlock/authz).


