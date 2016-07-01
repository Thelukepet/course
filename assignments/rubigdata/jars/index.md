---
layout: page
title: Jars
tagline: Dependencies for standalone example app
description: Jars the resolve the dependencies for the standalone example app
---

## Dependencies for standalone example app

Now that `beehive.nl` is not working properly, here is a backup solution.

- [jwat-common-1.0.0.jar](jwat-common-1.0.0.jar)
- [jwat-gzip-1.0.0.jar](jwat-gzip-1.0.0.jar)
- [jwat-warc-1.0.0.jar](jwat-warc-1.0.0.jar)
- [warcutils-1.3.jar](warcutils-1.3.jar)

You may copy these jars into `/opt/docker/rubigdata/lib` on the spark-notebook docker image
when working with the _WARC for Spark_ notebook.
You will have to add the fourth jar to the `:cp` command at the start of the notebook.
