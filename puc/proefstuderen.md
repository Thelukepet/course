---
layout: page
title: Proefstuderen
tagline: Radboud Proefstuderen Big Data 
description: College Big Data, Radboud.
---

Welkom bij het proefstuderen college over Big Data.

### Introductie

Een (eigenlijk te) eenvoudige poll voor de stemming in het land, kijkt naar de voorkomens van verschillende smileys 
in Tweets.

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

### Streaming Tweets

Hoe kun je eigenlijk de _trending topics_ bepalen over een groot volume aan Tweets?

De uitdaging is om de tweets niet eerst allemaal op te slaan, maar "on the fly" te analyseren:
terwijl ze binnenkomen.

#### Wie wint in social media?

We passen het programma `scraper.py` aan om de data niet op te slaan, maar de stemming op Twitter
te peilen terwijl de data langskomt - dus zonder die in een database op te slaan!

In informatica-termen is de achterliggende vraag als volgt:
_How would you find the quantiles of a stream of numbers in O(N) with limited memory?_.

##### Majority

Bekijk de code van functie `MJRTY` in [`streamer.py`](https://raw.githubusercontent.com/rubigdata/puc/master/streamer.py).

##### Mediaan

Wat als we nu de mediaan van een reeks getallen zouden willen weten?

```
median_est = 0
for val in stream:
    if val > median_est:
        median_est += 1
    elif val < median_est:
        median_est -= 1
```

#### Uitbreiding voor precentielen

Als je het 75% percentiel wilt weten van een reeks getallen, dan is de basis als volgt:

```
quantile_75 = 0
for val in stream:
    r = random()
    if val > quantile_75 and r > 1 - 0.75:
        quantile_75 += 1
    elif val < quantile_75 and r > 0.75:
        quantile_75 -= 1
```

De details kun je nalezen in de (Engelstalige en best ingewikkelde) blog post
[Sketch of the Day: Frugal Streaming](https://research.neustar.biz/2013/09/16/sketch-of-the-day-frugal-streaming/).
Als je een paar jaar informatica studeert, dan kun je de theorie achter die post tot in detail begrijpen.
De truc is dat je door de _randomisatie_ van het algorithme (het gooien van een dobbelsteen) de "mediaan" als het ware 
verschuift.

Zonder studeren kunnen we nu alvast spelen met de bijbehorende demo:

+ [Frugal Sketching demo](http://content.research.neustar.biz/blog/frugal.html)

Probeer maar eens verschillende distributies, en bedenk wat je nu eigenlijk gevisualiseerd ziet worden.,

#### Van woorden naar getallen

Met woorden is het lastig werken in een computer.
Soms vertalen we woorden dan ook naar getallen - vervolgens kunnen we veel sneller met de data omgaan.
In technische termen _representeren_ we de woorden als getallen.
De keuze van de juiste representatie is misschien wel het belangrijkste onderdeel van informatica.

7 minute video about [arrays and hash tables](https://www.youtube.com/watch?v=h2d9b_nEzoA).

[Bloom Filters](https://www.jasondavies.com/bloomfilter/).

Meer weten?

+ Nog een voorbeeld: [Bloom Filters by Example](https://llimllib.github.io/bloomfilter-tutorial/)

+ Bloom Filters bij [Medium](https://blog.medium.com/what-are-bloom-filters-1ec2a50c68ff#.i6jjy9yh9), een "dinner conversation"

+ Implementation: [A Toy Bloom Filter](http://glowingpython.blogspot.nl/2013/01/bloom-filter.html)

+ Een serieuze implementatie met zeer veel low level optimalisaties: [PyBloomFilter](https://axiak.github.io/pybloomfiltermmap/)

##### Counting

We kunnen met een Bloom Filter dus snel checken of we een woord eerder hebben gezien - maar dat maakt het
woord nog geen geschikt trending topic. Ofwel, we moeten kunnen tellen welke woorden het vaakst voorkomen!

We weten al hoe we het 99.99% percentiel van de aantallen voorkomens met weinig geheugen kunnen bepalen. 
Kunnen we ook met beperkt geheugen tellen hoe vaak elk woord genoemd wordt?
Hiervoor is de count-min sketch ontwikkeld.

+ [Count Min Sketch](http://lkozma.net/blog/sketching-data-structures/)

In principe weten we nu genoeg om Twitter's trending topics functionaliteit na te bouwen.
Er zitten alleen nog wel wat haken en ogen aan...

+ Wat zijn de termen die we moeten tellen?
+ Hoe bepalen we trending topics per regio?

De eerste vraag is er een in het vakgebied van de "information retrieval" en "natuurlijke taalverwerking",
de laatste een vraag op het gebied van "databases" en "data mining".

#### Meer weten? 

+ Count-Min Sketch in python:
  [blog post](https://tech.shareaholic.com/2012/12/03/the-count-min-sketch-how-to-count-over-large-keyspaces-when-about-right-is-good-enough/)

+ Een uitbreiding op Bloom Filters om te tellen, en ook deletions toe te laten:
  [The Invertible Bloom Filter](http://www.i-programmer.info/programming/theory/4641-the-invertible-bloom-filter.html)

+ Een nieuwere techniek, iets lastiger maar met mooie visualisatie:
  [Cuckoo Hashing](http://www.lkozma.net/cuckoo_hashing_visualization/)

+ [Cuckoo Hashing vs. Counting Bloom Filters](http://blog.fastforwardlabs.com/2016/11/23/probabilistic-data-structure-showdown-cuckoo.html)

+ Zeer snelle hash functie: [xxHash](https://cyan4973.github.io/xxHash/)

