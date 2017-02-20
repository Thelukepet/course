---
layout: page
title: PUC 
tagline: Radboud Pre-University College Big Data 
description: Pre-University College, Radboud.
---

Welkom bij de pre-university college (PUC) les over Big Data.

### Getting started

#### Python settings

Voordat we beginnen, moeten we (eenmalig) de `python` omgeving upgraden
naar een recentere versie. Start een terminal met Ctrl-t en voer de volgende
commando's uit:

    wget https://bootstrap.pypa.io/get-pip.py
    python get-pip.py --user
    pip install --user --upgrade urllib3[secure]

#### PUC Repository

Een snelle start dankzij versiebeheer met `git`; doe achtereenvolgens:

    git clone https://github.com/rubigdata/puc.git
    cd puc
    pip install --user -r requirements.txt
    cp private-template.py private.py

#### Twitter API

We gaan met de Twitter Streaming API aan de slag.

1. Maak [een nieuw Twitter account](https://twitter.com/signup), of login met je eigen account.

2. Maak [een app](https://apps.twitter.com/) aan.

   Laat de callback url leeg, en gebruik als url `https://rubigdata.github.io/course/puc/`.

   Zet de access rights, voor de zekerheid, op "Read only", en druk op "Regenerate Consumer Key and Secret". 
   Genereer ook een "Access Token".

   Copieer deze tokens nu in tekstfile `private.py`; hou ze wel goed geheim,
   save en edit de file maar plaats deze niet onder versiebeheer.

```   
# Application Settings:
TWITTER_APP_KEY    = "YOUR Consumer Key (API Key)"
TWITTER_APP_SECRET = "YOUR Consumer Secret (API Secret)"

# Access Tokens:
TWITTER_KEY        = "YOUR Access Token"
TWITTER_SECRET     = "YOUR Access Token Secret"
```

#### Tweepy

[Tweepy](http://docs.tweepy.org/en/v3.5.0/streaming_how_to.html) is een veelgebruikte `python` library om
met de Twitter Streaming API te werken, zonder veel te hoeven programmeren.

We beginnen met een "klassieke" aanpak: sla de verzamelde tweets op in een (eenvoudige) database,
en voer analyses uit met behulp van database queries.

Voeg eerst nog aan `private.py` het pad toe naar jouw user directory op een lokale drive,
bv. `/tmp/${USER}/`.

```
DATA_DIR           = "/YOUR DATA DIRECTORY/"
```

Maak deze directory aan en copieer nu deze file alvast naar directory `queries/`:

```
mkdir -p /tmp/${USER}
cp private.py queries/
```

#### Verzamel Tweets

Nu kun je programma `scraper.py` uitvoeren en achtereenvolgens de resultaten analyseren. 
Laat het eerst een tijdje draaien, en druk op `Ctrl-C` om het scrapen te stoppen:

```
python scraper.py
```

Bekijk de verzamelde data door de database te dumpen als CSV, of er enkele SQL queries op uit te voeren:

```
python queries/usercounts.py
python queries/query.py
```

Je kunt de database ook dumpen als CSV, een "comma-separated file", en vervolgens (bijvoorbeeld) als spreadsheet 
importeren in Google Docs.
```
python dump.py
```

Als je wilt weten hoe de `scraper.py` precies werkt, de [code](https://raw.githubusercontent.com/rubigdata/puc/master/scraper.py)
is gebaseerd op de uitleg in blog post 
[Working with streaming data: Using the Twitter API to capture tweets](https://www.dataquest.io/blog/streaming-data-python/).

#### Stream Tweets

De uitdaging is nu om de tweets niet eerst op te slaan, maar al streaming te analyseren.

In informatica-termen: _How would you find the quantiles of a stream of numbers in O(N) with limited memory?_.

##### Majority

Bekijk de code van functie `MJRTY` in [`streamer.py`](https://raw.githubusercontent.com/rubigdata/puc/master/streamer.py).

Wat als we nu de median willen vinden?

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

Meer details: 

+ [Sketch of the Day: Frugal Streaming](https://research.neustar.biz/2013/09/16/sketch-of-the-day-frugal-streaming/)

+ Demo bij deze blog: [Frugal Sketching demo](http://content.research.neustar.biz/blog/frugal.html)

##### Query

[Bloom Filters](https://www.jasondavies.com/bloomfilter/)

Meer weten?

+ Nog een voorbeeld: [Bloom Filters by Example](https://llimllib.github.io/bloomfilter-tutorial/)

+ Bloom Filters bij [Medium](https://blog.medium.com/what-are-bloom-filters-1ec2a50c68ff#.i6jjy9yh9), een "dinner conversation"

+ Implementation: [A Toy Bloom Filter](http://glowingpython.blogspot.nl/2013/01/bloom-filter.html)

+ Een serieuze implementatie met zeer veel low level optimalisaties: [PyBloomFilter](https://axiak.github.io/pybloomfiltermmap/)

##### Count

[Count Min Sketch]()

Meer weten? 

+ [Sketching data structures](http://lkozma.net/blog/sketching-data-structures/) is een aanrader!

+ Count-Min Sketch in python:
  [blog post](https://tech.shareaholic.com/2012/12/03/the-count-min-sketch-how-to-count-over-large-keyspaces-when-about-right-is-good-enough/)

##### Verder lezen

+ Een uitbreiding op Bloom Filters om te tellen, en ook deletions toe te laten:
  [The Invertible Bloom Filter](http://www.i-programmer.info/programming/theory/4641-the-invertible-bloom-filter.html)

+ Een nieuwere techniek, iets lastiger maar met mooie visualisatie:
  [Cuckoo Hashing](http://www.lkozma.net/cuckoo_hashing_visualization/)

+ [Cuckoo Hashing vs. Counting Bloom Filters](http://blog.fastforwardlabs.com/2016/11/23/probabilistic-data-structure-showdown-cuckoo.html)

+ Zeer snelle hash functie: [xxHash](https://cyan4973.github.io/xxHash/)

