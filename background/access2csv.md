---
layout: page
title: CBS data
description: Access to CSV
---

The city distributes most data as CSV (Comma Seperated Values), but the statistics data only
in the Microsoft Access format. You can install open source tool `access2csv` for converting 
the Microsoft Access data.

_Install git using `apt-get install git` if you do not have it yet. Building the `jar` would require installing `mvn` if your container does not have it yet._

```
git clone https://github.com/AccelerationNet/access2csv.git
cd access2csv
```

How to use the conversion tool:

```
./access2csv --help
```

E.g., to convert the data to CSV:

```
./access2csv --input /data/bigdata/opendata_stadsgetallen.accdb --output /tmp
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

