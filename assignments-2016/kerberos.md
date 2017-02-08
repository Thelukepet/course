---
layout: page
title: Kerberos
description: Getting Kerberos to work
---

## Kerberos

We work on the national [Hadoop cluster](https://userinfo.surfsara.nl/systems/hadoop/description) called `hathi`.

The basics of using the cluster are discussed briefly on the 
[Hadoop cluster usage page](https://userinfo.surfsara.nl/systems/hadoop/usage).

For access to the cluster, I recommend working with the provided (beta) Docker image:

```
docker run -it surfsara/hathi-client
```

Using the Docker image gets you automatically the right settings **inside** the container - however, if you want to inspect 
the status of a job, you will want to adapt your normal desktop environment to work with Kerberos authentication.

Below, I share how I have made this work on Linux and Windows (hoping OS/X is the same as Linux).

### Linux

To authenticate through Kerberos _on a Linux machine_ outside the Docker image (necessary to use the ResourceManager from firefox),
first install the Kerberos client software. On a Redhat machine, you would issue the following command:

```
sudo dnf install krb5-workstation krb5-libs krb5-auth-dialog
```

Copy the config file from the Docker image, or, alternatively, download [`surfsara.krb5.conf`](surfsara.krb5.conf),
put it in your home directory and set the `KRB5_CONFIG` environment variable:

```
cp /path/to/surfsara.krb5.conf $HOME/.surfsara.krb5.conf
export KRB5_CONFIG=$HOME/.surfsara.krb5.conf
```

Configure Firefox by setting its settings according to the instructions given by SurfSara on the
[usage page](https://userinfo.surfsara.nl/systems/hadoop/usage).

You should now be able to access the [ResourceManager](http://head05.hathi.surfsara.nl/cluster) in the configured Firefox browser.
_Note: this may involve a restart of Firefox from a terminal in which the `KRB5_CONFIG` variable is set as given above._

### Windows

On Windows, it takes a few more steps, but I managed to make it work on my own laptop using the instructions provided by
the Edinburgh University systems people: [Kerberos on Windows](http://computing.help.inf.ed.ac.uk/kerberos-windows).

First, install _Heimdal Kerberos for Windows_ and the _Network Identity Manager_ following the Edinburgh instructions.

Then, replace the default `krb5.conf` file in `C:\ProgramData\Kerberos` by the file provided by SurfSara in the Docker image;
or, alternatively, by downloading [`surfsara.krb5.conf`](surfsara.krb5.conf); make sure to rename the file to `krb5.conf`.

Next, add your credentials to the Network Identity Manager, and obtain a Kerberos ticket.

Finally, configure Firefox using the settings given on the [hathi usage page](https://userinfo.surfsara.nl/systems/hadoop/usage).
Additionally, for Windows, you should also switch the following variable from `true` to `false` (again, from `about:setup`):

```
network.auth.use-sspi false
```

You should now be able to view the [ResourceManager](http://head05.hathi.surfsara.nl/cluster) _(works in configured Firefox only)_.

### OS/X

Sorry, you are on your own!
If you figure out how to modify the Linux instructions to work on OS/X, please share on Piazza and I will add those here.

