---
layout: page
title: Assignment 1a
tagline: Blogging environment
description: Frequently asked questions
---

## FAQ

1. Where do I find my Github page?

   The markdown files are processed and rendered as HTML at `https://rubigdata.github.io/{AssignmentName}-{UserName}/`.

2. Why is the stylefile ignored?

   Did you use the correct url at `production_url` and `BASE_PATH` in the `_config.yml` file?
   See the previous question, and pay attention to the trailing `/`.  
   _Note:_ If those urls start with `http` instead of `https`, only the `http` version will be rendered (so use `https`!).

3. Why are updates to my repository not processed?

   Are you sending your updates to the `gh-pages` branch?  
   Try `git status` to check.


