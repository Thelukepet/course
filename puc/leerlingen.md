---
layout: page
title: PUC 
tagline: Radboud Pre-University College Big Data 
description: Pre-University College, Radboud.
---

Welkom bij de pre-university college (PUC) les over Big Data.

### Introductie

We bekijken een stream van Tweets rond de verkiezingen.

Hiervoor gebruik ik programma `scraper.py`
(je kunt het [thuis namaken](tutorial.html) als je de online instructies volgt!).

We laten de `scraper` een tijdje draaien, en drukken op `Ctrl-C` om te stoppen:

```
python scraper.py
```

Bekijk de verzamelde data door de database te dumpen als CSV, of er enkele SQL queries op uit te voeren:

```
python queries/usercounts.py
python queries/query.py
```

Je kunt de database ook dumpen als CSV, een "comma-separated file", en vervolgens (bijvoorbeeld)
als spreadsheet importeren in Google Docs:

```
python dump.py
```

#### Streaming Tweets

De uitdaging is nu om de tweets niet eerst op te slaan, maar "on the fly" te analyseren terwijl ze
binnenkomen.

We passen het programma `scraper.py` aan om de data niet op te slaan, maar de stemming op Twitter
te peilen terwijl de data langskomt - dus zonder die in een database op te slaan!

In informatica-termen is de achterliggende vraag als volgt:
_How would you find the quantiles of a stream of numbers in O(N) with limited memory?_.

##### Majority

Bekijk de code van functie `MJRTY` in [`streamer.py`](https://raw.githubusercontent.com/rubigdata/puc/master/streamer.py).

##### Mediaan

Wat als we nu de mediaan willen vinden?

```
median_est = 0
for val in stream:
    if val > median_est:
        median_est += 1
    elif val < median_est:
        median_est -= 1
```

En als je het 75th percentiel wilt weten is de basis als volgt:

```
quantile_75 = 0
for val in stream:
    r = random()
    if val > quantile_75 and r > 1 - 0.75:
        quantile_75 += 1
    elif val < quantile_75 and r > 0.75:
        quantile_75 -= 1
```

De details kun je nalezen in de (Engelstalige en best moeilijke) blog post
[Sketch of the Day: Frugal Streaming](https://research.neustar.biz/2013/09/16/sketch-of-the-day-frugal-streaming/).

Als je een paar jaar informatica studeert, dan kun je de theorie achter die post tot in detail begrijpen.
Zonder studeren kunnen we nu alvast spelen met de bijbehorende demo:

+ [Frugal Sketching demo](http://content.research.neustar.biz/blog/frugal.html)

