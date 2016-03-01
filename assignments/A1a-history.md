---
layout: page
title: Assignments
description: How to complete and turn in assignments
---

A trace of git commands issued to construct the [Course site](http://rubigdata.github.io/course), for reference.

Initialize the repository as follows:

```
git clone git://github.com/kbroman/simple_site
mv simple_site course
cd course
rm -rf .git
```

Follow the detailed [instructions](http://kbroman.org/simple_site/pages/independent_site.html) to setup the site
and write its initial contents (using Markdown).

Then, we publish the site by pushing it to the repository:

```
git init
git add .
git commit -m "first commit"
git branch -m master gh-pages
git remote add origin git@github.com:rubigdata/course.git
git push -u origin gh-pages
```

In the `git remote add` command, use the details of your `github` repository to store the completed practical assignments.

Feel free to inspect the Github Repository for the [Course Site](https://github.com/rubigdata/course) and/or its
history, but keep in mind that only the documents published online comprise the *official* course information.
