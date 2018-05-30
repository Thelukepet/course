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
first install the Kerberos client software. You can skip this on Huygens; on a self-managed Redhat machine, you would issue
`sudo dnf install krb5-workstation krb5-libs krb5-auth-dialog`.

Now, copy the config file from the Docker image, or, alternatively, download [`surfsara.krb5.conf`](surfsara.krb5.conf),
put it in your home directory and set the `KRB5_CONFIG` environment variable to point to this file:

```
cp /path/to/surfsara.krb5.conf $HOME/.surfsara.krb5.conf
export KRB5_CONFIG=$HOME/.surfsara.krb5.conf
```

If these steps have been successful, you authenticate to the cluster using the following command:

    kinit rubd##X@CUA.SURFSARA.NL

Common pitfalls are (1) that the PCs in the Huygens terminal rooms already use Kerberos for authentication in the 
RU domain, so you must use your username (at the cluster) with the SurfSara realm when using `kinit`
(as in the example above), (2) that the *capitals* in `CUA.SURFSARA.NL` are an important detail, 
and (3) that the `KRB5_CONFIG` variable needs to be set in the terminal where you try to issue these commands.

Now, proceed to configure Firefox according to the instructions in the final assignment's [FAQ](P-faq.html).
Do not forget that you have to start the browser from a terminal in which the `KRB5_CONFIG` variable points
to the `krb5.conf` that defines the SurfSara Kerberos environment; clicking on the icon on the desktop will
not work correctly.

On your own hardware, using Chrome is an alternative (it is not installed in Huygens however). 
Start Chrome from the command-line with a special flag to whitelist Hathi for Kerberos authentication:

``` 
KRB5_CONFIG=/home/arjen/.surfsara.krb5.conf google-chrome --auth-server-whitelist=".hathi.surfsara.nl"
```
You should now be able to access the [ResourceManager](http://head05.hathi.surfsara.nl/cluster) in the configured browser.

### Windows

__Not yet tested in 2018 if this still works!__

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
If you figure out how to modify the Linux instructions to work on OS/X, please share on the Forum and I will add those here.

