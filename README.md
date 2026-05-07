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
schemafamilj som används via `typeId`.

## Payload-format (JSON-LD)

Payload följer JSON-LD `@graph`-format:

```json
{
  "@context": {
    "@vocab":  "http://data.fk.se/vocab/",
    "meta":    "http://data.fk.se/meta/",
    "common":  "http://data.fk.se/common/",
    "fk":      "http://data.fk.se/fk/"
  },
  "@graph": [{
    "@id":           "meta:<uuid>",
    "@type":         "fk:Yrkande",
    "meta:typeId":   "urn:mimer:typ:Yrkande:1.0",
    "meta:version": 1,
    "common:from": "2025-04-22T14:26:00+02:00",
    "common:tom":  "2025-04-23T14:26:00+02:00"
  }],
  "rawData": { }
}
```

### Prefix

| Prefix  | Ansvar                                        | Exempel                 |
|---------|-----------------------------------------------|-------------------------|
| meta:   | Teknisk metadata om noden                     | meta:version            |
| common: | Generiska återanvändbara datatyper            | common:from, common:tom |
| fk:      | Verksamhetsdata — domänspecifika fält och typer | fk:yrkan                |

### Graf-noder

Objekt med `id`-fält och som är registrerade i `TypeRegistry` taggas med:
- `@id` → `meta:<uuid>`
- `@type` → `fk:KlassNamn`
- `meta:typeId` → `urn:mimer:typ:KlassNamn:schemafamilj`

Värde-objekt utan `id`-fält, prefix-mappas men får inga JSON-LD-nyckelord.

### rawData

`rawData` innehåller producentens originaldata orörd — inga prefix, inga JSON-LD-nyckelord.

### Placeholders
Idag används placeholder-URL:er i `@context` exemplevis som: `http://data.fk.se/fk/`, de är inte
kopplade till en riktig server och tjänar bara som platshållare under utveckling just nu.


## Metadata

Metadata för varje leverans lagras separat i **Cassandra**.

Metadata innehåller exempelvis:

```json
{
  "producentId": "SJP",
  "korrelationsId": "xyz-123",
  "transportVersion": "1.0",
  "createdAt": "2025-04-22T14:26:00+02:00"
}
```
Fullständig metadatastruktur definieras av `Datalverans`-DTO:n.

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

## Ändringar i codec vid modellförändringar

### Ny klass läggs till
- Registrera i `CodecRegistries.createTypeRegistry()`
- Om polymorfisk subklass: registrera i `CodecRegistries.createVariantRegistry()`
- Om polymorfisk basklass: lägg till i `VariantInjectingSerializerModifier`

### Befintlig klass tas bort
- Ta bort från `CodecRegistries.createTypeRegistry()`
- Om polymorfisk subklass: ta bort från `CodecRegistries.createVariantRegistry()`
- Om polymorfisk basklass: ta bort från `VariantInjectingSerializerModifier`

### Fält läggs till/tas bort
- `FieldToClassMapBuilder` hanterar detta automatiskt via reflection
- Om fältet är ett objekt/array, verifiera att klassen är registrerad i TypeRegistry

---

# Schemafamiljer

Datamodeller organiseras i **schemafamiljer** i.e. **models-v1**, **models-v2** ...

En schemafamilj representerar en **version av datamodellerna**.

Payload identifierar schemafamiljen via `typeId`


## Identifiering av typer `(@type)` och `(typeId)`

Varje graf-nod identifieras med två fält som har olika syften.

### `@type` - semantisk typ för JSON-LD-konsumenter

Format: `domän:KlassNamn` (t.ex. `fk:Yrkande`)

`@type` är ett standard JSON-LD-fält som expanderas via `@context` till en
fullständig IRI. Det används av JSON-LD-verktyg och semantiska konsumenter
för att förstå vad noden representerar.

#### Framtida målformat

Det långsiktiga målet är att `@type` expanderas till ett realiserat IRI-format:
`https://data.fk.se/typ/Yrkande`.

Detta gör det möjligt att:
- ge typer en stabil semantisk identifierare
- publicera dokumentation eller metadata för typer
- använda identifierarna i semantiska sammanhang

### `meta:typeId` - versionerad typidentifierare för codec

Format: `urn:fk:typ:<TypNamn>:<SchemaFamilj>`
(t.ex. `urn:fk:typ:Yrkande:1.0`)

`meta:typeId` är ett internt fält som codec använder för att:
- avgöra vilken schemafamilj payloaden tillhör (1.0, 2.0 etc.)
- välja rätt modellversion vid deserialisering
- möjliggöra replay av historisk data oavsett aktuell modellversion


### Varför två fält?

`@type` är stabilt och beskriver **vad** noden är semantiskt, det ändras inte
mellan schemafamiljer. `meta:typeId` är versionerat och beskriver **hur** noden
ska tolkas tekniskt. Detta gör att samma typ kan utvecklas över tid utan att
bryta semantiken: ett `Yrkande` är alltid ett `Yrkande` (`@type`), men dess
struktur kan skilja sig mellan version 1.0 och 2.0 (`meta:typeId`).

---

# Vad schemafamiljen styr

Schemafamiljen beskriver **strukturen för den kanoniska datan** i payload.

Det innebär främst:

- strukturen i `@graph`
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

Modellversionen identifieras i payload via `typeId`.

Alla artifact-versioner inom samma schemafamilj implementerar samma
kontrakt. Detta betyder att om vi har ex: `models-v1: 1.0.0` och `models-v1: 1.6.10`
så implementerar båda schemafamilj **1.0**.

Breaking changes i modellen kräver ny schemafamilj.


## 3) Transportversion

Transportversionen anger versionen av transportwrappern (`Dataleverans`).

ex: `transportVersion=1.0`

Transportversion styr hur wrappern ska tolkas, inte hur payloadens modeller
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
- payloadens modell styrs alltid av `@type` och `typeId`


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
- `typeId.../1.0`

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
- `typeId.../2.0`

Flera schemafamiljer kan då existera parallelt.

---

# Varför flera modellmoduler

Java kan normalt bara ha **en version av samma artifact** på classpath.

Om vi bara hade en modul `models` skulle en server inte kunna stödja
flera schemafamiljer samtidigt.

Genom separata moduler kan systemet:
- ha `models-v1` och `models-v2` på classpath samtidigt.
- välja rätt modellfamilj baserat på payload `typeId`
- köra migrering och replay i samma process
- stödja producenter som uppgraderar i olika takt

---

# Replay och långsiktig kompabilitiet

Payload lagras i rådatalagret i sin urpsrungliga form.

Vid replay:
1. bytes läses från S3
2. payload deserialiseras
3. `typeId` identifierar schemafamilj
4. rätt modellfamilj används (`models-v1`, `models-v2`)

Detta gör att historiska payloads alltid kan återprocessas
även efter att nya schemafamiljer införts.

---

# Sammanfattning

Arkitekturen bygger på följande principer
- payload lagras som JSON-LD i rådatalagret
- schemafamiljer identifieras via `typeId`
- transportversion styr wrapperkontrakt
- artifact-version styr biblioteksversion
- breaking changes i modeller skapar ny schemafamilj
- transport kan utvecklas bakåtkompatibelt
- historisk data måste alltid kunna replayas






