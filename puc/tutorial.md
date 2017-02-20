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
bv. `/scratch/${USERNAME}/`.

```
DATA_DIR           = "/YOUR DATA DIRECTORY/"
```

Copieer nu deze file alvast naar directory `queries/`:

```
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

Je kunt de database ook dumpen als CSV, een "comma-separated file":
```
python queries/dump.py
```

Als je wilt weten hoe de `scraper.py` precies werkt, de code is gebaseerd op de uitleg van blog post
[Working with streaming data: Using the Twitter API to capture tweets](https://www.dataquest.io/blog/streaming-data-python/).

