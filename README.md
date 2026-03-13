# Mimer-Datamodels

Detta repository innehåller datamodeller och codec för transport,
lagring och återläsning av dataleveranser inom Mimer.

Arkitekturen är designad för att:

- möjliggöra stabil versionshantering av datamodeller
- stödja parallella schemafamiljer
- möjliggöra replay och reprocess av historisk data
- kunna utveckla transportformatet utan att ändra domänmodeller
- separera transportkontrakt, modellkontrakt och artifact-versioner

Datamodellerna och codecen används både när data levereras till Mimer och när data hämtas
från rådatalagret.

---

# Rådatalager

Dataleveranser lagras i två delar.

## Payload

Payload lagras i **S3** som:
> UTF-8-kodade bytes som representerar ett JSON-LD dokument.

Payload innehåller själva domändatan och identifierar vilken 
schemafamilj som används via `@type`.

## Metadata

Metadata för varje leverans lagras separat i **Cassandra**.

Metadata innehåller exempelvis:

- TODO


---

# Repositorystruktur

Repositoryt innehåller tre typer av moduler:

- **Legacy-modeller**
- **Versionerade modellfamiljer**
- **Versionerade codecs**


## Legacy-modeller

Denna modul innehåller äldre datamodeller från tidigare implementation.

Dessa modeller saknar formell schemafamilj-versionering och betraktas
som legacy.


## Modellfamiljer

Varje modul representerar en **schemafamilj**.

Modeller inom samma schemafamilj delar samma semantiska kontrakt.


## Codec

Codec implementerar serialisering och deserialisering för respektive modellfamilj.

Codec ansvarar för:

- serialisering till JSON-LD
- deserialisering från JSON-LD
- polymorfism via `variant`
- konstruktion av JSON-LD payloadstruktur
- encoding av payload
- tolkning av transportwrappern (`Dataleverans`)

Codec används när data skickas, tas emot eller när payload läses tillbaka från
rådatalagret.

---

# Schemafamiljer

Datamodeller organiseras i **schemafamiljer** i.e. **models-v1**, **models-v2** ...

En schemafamilj representerar en **version av datamodellerna**.

Payload identifierar schemafamiljen via `@type`


## Identifiering av typer (`@type`)

### Framtida målformat

Det långsiktiga målett är att använda ett IRI-format:
`https://data.fk.se/typ/Yrkande/1.0`.

Detta gör det möjligt att:

- ge typer en stabil semantisk identifierare
- publicera dokumentation eller metadata för typer
- använda identifierarna i semantiska sammanhang

### Nuvarande format (övergånsperiod)

- Under en övergånsperiod används ett **URN-format**: `urn:mimer:typ:Yrkande:1.0`.
- Strukturen är: `urn:mimer:typ:<TypNamn>:<SchemaFamilj>`.

Båda formaten representerar samma koncept:
**typ + schemafamilj**.

---

# Vad schemafamiljen styr

Schemafamiljen beskriver **strukturen för den kanoniska datan** i payload.

Det innebär främst:

- strukturen i `data`
- vilka typer som är giltiga
- hur modellen ska tolkas vid deserialisering

`rawData` är producentens originalstruktur och kan innehålla ytterligare
fält som inte ingår i basmodellen.

---

# Versionslager

Systemet använder tre typer av versioner.


## 1) Artifact-version (SemVer)

Versionen på biblioteken.
ex: `models-v1: 1.6.10`, `codec-v1: 1.6.10`.
Artifact-versionen används för dependecy management.

I nuläget publiceras `models-v1` och `codec-v1` med samma artifact-version
för att det ska vara tydligt vilka som hör ihop.


## 2) Modellversion (schemafamilj)

Modellversionen identifieras i payload via `@type`.

Alla artifact-versioner inom samma schemafamilj implementerar samma
kontrakt. Detta betyder att om vi har ex: `models-v1: 1.0.0` och `models-v1: 1.6.10`
så implementerar båda schemafamilj **1.0**.

Breaking changes i modellen kräver ny schemafamilj.


## 3) Transportversion

Transportversionen anger versionen av transportwrappern (`Dataleverans`).

ex: `transportVersion=1.0`

Transportversion styr hur wrappern ska tolkas, int hur payloadens modeller
ska tolkas.

---

# Transportwrapper (`Dataleverans`)

`Dataleverans` är transportmeddelandet som innehåller:

- metadata
- payload
- transportVersion
- eventuella transportrelaterade fält

Payloaden innehåller JSON-LD.

---

# Utveckling av transportformatet

Transportversion gör det möjligt att utveckla wrappern utan att ändra
modellerna.

Transporversion är **separerad från schemafamiljen**.

Det innebär att:

- wrappern kan utvecklas självständigt
- payloadens modell styrs alltid av `@type`


## Bakåtkompatibla ändringar

Inom samma transport-major tillåts endast bakåtkompatibla förändringar.

ex: `1.0` -> `1.1`

Det kan till exempel vara:
- nya optional fält
- nya metadatafält
- signering som optional funktion

### Exempel: signering

Signering kan införas genom att lägga till nya optional fält i
transportwrappern.

- `payloadSha256B64`
- `signature`
- `keyId`

Äldre producenter kan fortsätta skicka osignerade meddelanden.

Detta är en **minor-uppdatering** av transportVersion.

## Breaking change i transportwrappern

Breaking changes i wrappern kräver en ny transport-major.

ex: `1.x` -> `2.0`

Det kan tillexempel vara:
- signering görs obligatorisk
- fält tas bort
- fält ändrar betydelse

I sådana fall introduceras en ny DTO. Dvs. om vi har `Dataleverans` skapas
`DataleveransV2`

---

# Pre-production strategi

Under utveckling före produktionsstart används:

- Artifact-versioner: `0.x.y`
- Schemafamilj: draft
- Transportversion = 0.1

Breaking changes är tillåtna eftersom ingen produktionsdata lagras permanent.

---

# Produktionsstart

När systemet börjar lagra verklig data:
- `models-v1` -> `1.0.0`,
- `codec-v1` -> `1.0.0`

Schemafamilj:
- `@type.../1.0`

TransportVersion:
- 1.0

Från denna punkt måste historiska payloads kunna läsas och replayas.

---

# Breaking changes i modeller

Breaking changes i modeller kräver en ny schemafamilj.

ex:
- `models-v2`
- `codec-v2`

Payload använder då:
- `@type.../2.0`

Flera schemafamiljer kan då existera parallelt.

---

# Varför flera modellmoduler

Java kan normalt bara ha **en version av samma artifact** på classpath.

Om vi bara hade en modul `models` skulle en server inte kunna stödja
flera schemafamiljer samtidigt.

Genom separata moduler kan systemet:
- ha `models-v1` och `models-v2` på classpath samtidigt.
- välja rätt modellfamilj baserat på payload `@type`
- köra migrering och replay i samma process
- stödja producenter som uppgraderar i olika takt

---

# Replay och långsiktig kompabilitiet

Payload lagras i rådatalagret i sin urpsrungliga form.

Vid replay:
1. bytes läses från S3
2. payload deserialiseras
3. `@type` identifierar schemafamilj
4. rätt modellfamilj används (`models-v1`, `models-v2`)

Detta gör att historiska payloads alltid kan återprocessas
även efter att nya schemafamiljer införts.

---

# Sammanfattning

Arkitekturen bygger på följande principer
- payload lagras som JSON-LD i rådatalagret
- schemafamiljer identifieras via `@type`
- transportversion styr wrapperkontrakt
- artifact-version styr biblioteksversion
- breaking changes i modeller skapar ny schemafamilj
- transport kan utvecklas bakåtkompatibelt
- historisk data måste alltid kunna replayas






