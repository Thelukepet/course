---
layout: page
title: PUC 
tagline: Radboud Pre-University College Big Data 
description: Pre-University College, Radboud.
---

Welkom bij de pre-university college (PUC) les over Big Data.

### Getting started

#### PUC Repository

Een snelle start dankzij versiebeheer met `git`; doe achtereenvolgens:

    git clone git@github.com:rubigdata/puc.git
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

   Bewaar deze tokens nu in een tekstfile `private.py`, hou ze wel geheim;
   save en edit deze file, of pas het template aan:


```   
# Application Settings:
TWITTER_APP_KEY    = "YOUR Consumer Key (API Key)"
TWITTER_APP_SECRET = "YOUR Consumer Secret (API Secret)"

# Access Tokens:
TWITTER_KEY        = "YOUR Access Token"
TWITTER_SECRET     = "YOUR Access Token Secret"
```

#### Verzamel Tweets

[Tweepy](http://docs.tweepy.org/en/v3.5.0/streaming_how_to.html) is een veelgebruikte `python` library om
met de Twitter Streaming API te werken, zonder veel te hoeven programmeren.

Eerst gaan we een klassieke oplossing volgen: sla de tweets op in een (eenvoudige) database.





















