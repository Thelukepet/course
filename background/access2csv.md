---
layout: page
title: CBS data
description: Access to CSV
---

The city distributes most data as CSV (Comma Seperated Values), but the statistics data only
in the Microsoft Access format. You can install open source tool `access2csv` for converting 
the Microsoft Access data.
Building the `jar` requires installing `ant` if your container does not have it yet:

```
apt-get install ant
git clone https://github.com/AccelerationNet/access2csv.git
cd access2csv
ant
```

How to use the conversion tool:

```
java -jar /opt/docker/notebooks/BigData/access2csv/access2csv.jar --help
```

E.g., to inspect the schema:

```
java -jar /opt/docker/notebooks/BigData/access2csv/access2csv.jar /data/opendata_stadsgetallen.accdb --schema
```

This gives a result like:
```
CREATE TABLE tbl_OPENDATA (
  WaardeId LONG,
  Waarde FLOAT,
  WaardetypeNaam TEXT,
  ThemaNaam TEXT,
  OnderwerpNaam TEXT,
  Labelgroepnaam TEXT,
  LabelNaam TEXT,
  GeografieType TEXT,
  GeografieOmschrijving TEXT,
  TijdOmschrijving TEXT,
  TijdType TEXT,
  BronOrganisatie TEXT,
  BronNaam TEXT,
)
```

Now convert the data to CSV to enable further analysis.

**When you get stuck, use Piazza to find help from your fellow students and/or me!**

