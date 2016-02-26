---
layout:page

History
```
  734  git clone git://github.com/kbroman/simple_site
  735  mv simple_site course
  736  cd course
  737  rm -rf .git
```

Follow Instructions.

```
  761  git init
  762  git add .
  763  git commit -m "first commit"
  764  git branch -m master gh-pages
  765  git remote add origin git@github.com:rubigdata/course.git
  766  git push -u origin gh-pages
```
