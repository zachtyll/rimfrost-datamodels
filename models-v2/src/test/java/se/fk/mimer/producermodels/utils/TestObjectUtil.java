package se.fk.mimer.producermodels.utils;

import com.fasterxml.uuid.Generators;
import lombok.Getter;
import se.fk.mimer.producermodels.v2.model.Land;
import se.fk.mimer.producermodels.v2.model.Period;
import se.fk.mimer.producermodels.v2.model.beslut.Beslut;
import se.fk.mimer.producermodels.v2.model.beslut.BeslutandeOrganisation;
import se.fk.mimer.producermodels.v2.model.beslut.Beslutstyp;
import se.fk.mimer.producermodels.v2.model.beslut.Beslutsutfall;
import se.fk.mimer.producermodels.v2.model.beslut.delgivning.Delgivning;
import se.fk.mimer.producermodels.v2.model.beslut.delgivning.Delgivningstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.EInkomsttyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.Ersattning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.EBeloppstypKategori;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.beloppstyp.BeloppstypBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.EBerakningsgrund;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.EBerakningsgrundRegel;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.berakningsgrund.BerakningsgrundBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.EErsattningstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.ersattningstyp.ELagrumForErsattningstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.ersattning.omfattning.OmfattningBaseratPaErsattningstypEnligtLagrum;
import se.fk.mimer.producermodels.v2.model.produceratresultat.krav.Krav;
import se.fk.mimer.producermodels.v2.model.produceratresultat.krav.Kravtyp;
import se.fk.mimer.producermodels.v2.model.kundbehov.Avsikt;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehov;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsflode;
import se.fk.mimer.producermodels.v2.model.kundbehov.Kundbehovsstatus;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollIKundbehov;
import se.fk.mimer.producermodels.v2.model.kundbehov.RollerIKundbehov;
import se.fk.mimer.producermodels.v2.model.lagrum.Lagrum;
import se.fk.mimer.producermodels.v2.model.person.EnskildNaringsidkare;
import se.fk.mimer.producermodels.v2.model.person.FysiskPerson;
import se.fk.mimer.producermodels.v2.model.person.JuridiskPerson;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Foretradartyp;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Funktionar;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Funktionarstyp;
import se.fk.mimer.producermodels.v2.model.person.foretradare.Ombud;
import se.fk.mimer.producermodels.v2.model.produceratresultat.Bedomdarbetsformaga;
import se.fk.mimer.producermodels.v2.model.produceratresultat.KarenstidEnskildNaringsidkare;
import se.fk.mimer.producermodels.v2.model.produceratresultat.Periodisering;
import se.fk.mimer.producermodels.v2.model.produceratresultat.RattenTillPeriod;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.BedomdInkomst;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.EInkomsttypKategori;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdkostnad.BedomdKostnad;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdkostnad.Kostnadstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.AnledningIngenBidragssparr;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.BedomdMedvetenhet;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.Bidragssparr;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.BidragssparrStatus;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.GrundForBeslut;
import se.fk.mimer.producermodels.v2.model.produceratresultat.bidragssparr.GrundForUtredning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.EUKort;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.Forhandstillstand;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.IVIntyg;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.IVIntygstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.Intyg;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.IntygOmTillampligLagstiftning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.Intygstyp;
import se.fk.mimer.producermodels.v2.model.produceratresultat.intyg.UtlandsktForetag;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.EArtikel;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.EForordning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.periodtillampliglagstiftning.PeriodTillampligLagstiftning;
import se.fk.mimer.producermodels.v2.model.produceratresultat.svensksocialforsakringsperiod.Socialforsakringsgrund;
import se.fk.mimer.producermodels.v2.model.produceratresultat.svensksocialforsakringsperiod.SvenskSocialforsakringsperiod;
import se.fk.mimer.producermodels.v2.model.produkt.EErbjudande;
import se.fk.mimer.producermodels.v2.model.produkt.Erbjudande;
import se.fk.mimer.producermodels.v2.model.produkt.Produkt;
import se.fk.mimer.producermodels.v2.model.produkt.EProduktnamn;
import se.fk.mimer.producermodels.v2.model.produkt.Produktroller;
import se.fk.mimer.producermodels.v2.model.produkt.RollIProdukt;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestObjectUtil
{
    // #### UUID ####
    private static final UUID lagrumid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID fysiskpersonid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID juridiskpersonid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID ombudid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID funktionarid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID foretraderid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID kundbehovid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID kundbehovsflodeid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID naringsidkareid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID produktid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID rolliproduktid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID foretraderrolliproduktid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID erbjudandeid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID beslutid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID beslutsfattareid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID delgivningid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID beloppstypbaseratpaersattningstypenligtlagrumid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID omfattningbaseratpaersattningstypenligtlagrumid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID berakningsgrundbaseratpaersattningstypenligtlagrumid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID ersattningstypenligtlagrumid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID ersattningid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID kravid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID arendeid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID bedomdinkomstid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID bedomdkostnadid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID periodtillampliglagstiftningid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID svensksocialforsakringsperiodid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID eukortid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID rattentillperiodid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID forhandstillstandid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID intygomtillampliglagstiftningid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID ivintygid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID utlandsktforetagintygid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID bedomdarbetsformagaid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID bidragssparrid = Generators.timeBasedEpochGenerator().generate();
    private static final UUID karenstidenskildnaringsidkareid = Generators.timeBasedEpochGenerator().generate();

    // #### TIMESTAMPS/PERIODS ####
    @Getter
    private static final Period period = new Period(
            ZonedDateTime.of(2025,1,1,10,0,0,0, ZoneId.systemDefault()),
            ZonedDateTime.of(2026,1,1,10,0,0,0, ZoneId.systemDefault())
    );
    @Getter
    private static final ZonedDateTime date = ZonedDateTime.of(2024, 1, 1, 10, 0, 0, 0, ZoneId.systemDefault());

    // #### PERSONAL INFO ####
    @Getter
    private static final String PERSONID = "1234567811234";
    @Getter
    private static final String FORETRADERPERSONID = "9876543229876";
    @Getter
    private static final String PERSONNUMMER = "12345678-1234";
    @Getter
    private static final String FORETRADERPERSONNUMMER = "98765432-9876";
    @Getter
    private static final String FORETAGSNAMN = "Testföretag OY";
    @Getter
    private static final String ORGANISATIONSNUMMER = "11223344";
    @Getter
    private static final String EUKORTNUMMER = "1212123344";

    // #### LAGRUM ####
    @Getter
    private static final Lagrum lagrum = new Lagrum(lagrumid, 1, "Testförfattning", "Kapitel 1", "§1", "Stycke 1", "Punkt 1", period);

    // #### PRODUKT ####
        // #### ROLL I PRODUKT ####
    @Getter
    private static final RollIProdukt rollIProdukt = new RollIProdukt( rolliproduktid, 1, PERSONID, foretraderid, Produktroller.PRODUKTAGARE);
    @Getter
    private static final RollIProdukt foretraderRollIProdukt = new RollIProdukt( foretraderrolliproduktid, 1, FORETRADERPERSONID, foretraderid, Produktroller.PRODUKTAGARE);
    @Getter
    private static final Erbjudande erbjudande = new Erbjudande(erbjudandeid, 0, "testerbjudande", EProduktnamn.UNDERHALLSSTOD, EErbjudande.DIREKTAVDRAG);

    @Getter
    private static final RollIProdukt[] rollIProduktArray = {rollIProdukt};
    @Getter
    private static final Map<UUID, Erbjudande> erbjudandeMap = Map.of(kundbehovid, erbjudande);
    @Getter
    private static final Produkt produkt = new Produkt(produktid, 1, EProduktnamn.UNDERHALLSSTOD, rollIProduktArray, erbjudandeMap);

    // #### KUNDBEHOV ####
    @Getter
    private static final RollIKundbehov rollIKundbehov = new RollIKundbehov(PERSONID, RollerIKundbehov.BOFORALDER, true);
    @Getter
    private static final Map<UUID, RollIKundbehov> rollIKundbehovMap = Map.of( kundbehovid, rollIKundbehov );
    @Getter
    private static final List<UUID> hanterarKundbehov = List.of(kundbehovid);
    @Getter
    private static final Kundbehov kundbehov = new Kundbehov( kundbehovid, 1, Kundbehovsstatus.PLANERAT, Avsikt.ANDRING, "testanledning",date, period, erbjudande ,List.of( rollIKundbehov ));
    @Getter
    private static final Kundbehovsflode kundbehovsflode = new Kundbehovsflode(kundbehovsflodeid, 1, null, date, hanterarKundbehov, arendeid.toString());

    // #### PERSON ####
    @Getter
    private static final EnskildNaringsidkare naringsidkare = new EnskildNaringsidkare( naringsidkareid, 1);
    @Getter
    private static final JuridiskPerson juridiskPerson = new JuridiskPerson(juridiskpersonid, PERSONID, 1, "JuridiskPerson", ORGANISATIONSNUMMER, rollIKundbehovMap);
    @Getter
    private static final FysiskPerson fysiskPerson = new FysiskPerson(fysiskpersonid, PERSONID, 0, "FysiskPerson", PERSONNUMMER, rollIProdukt, naringsidkare, rollIKundbehovMap );
    @Getter
    private static final FysiskPerson foretraderPerson = new FysiskPerson(foretraderid, FORETRADERPERSONID, 0, "ForetraderPerson", FORETRADERPERSONNUMMER, foretraderRollIProdukt, naringsidkare, rollIKundbehovMap );
            // #### FYSISK PERSON-SUBKLASSER ####
    @Getter
    private static final Ombud ombud = new Ombud(fysiskpersonid, PERSONID, 0, "Ombud", rollIKundbehovMap, "12345678-1234", rollIProdukt, naringsidkare, "1234567811234", ombudid, Foretradartyp.FULLMAKT, period, foretraderPerson );
    @Getter
    private static final Funktionar funktionar = new Funktionar(ombudid, PERSONID, 0, "Funktionar", rollIKundbehovMap, "12345678-1234", rollIProdukt, naringsidkare, "1234567811234", funktionarid, Foretradartyp.STALLFORETRADARE, period, foretraderPerson, Funktionarstyp.VD);

    // #### PRODUCERAT RESULTAT ####
    @Getter
    private static final RattenTillPeriod basresultat = new RattenTillPeriod(rattentillperiodid, kundbehovid, 1, "RattenTillPeriod", fysiskPerson, period, "Testtyp", "Teststatus", "Testomfattning", "Testersättning");

    @Getter
    private static final BedomdInkomst bgiBedomdInkomst = new BedomdInkomst(bedomdinkomstid, basresultat.getFaststallsForKundbehov(), 1, basresultat.getVariant(), basresultat.getAvserPerson(),
            basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), 0.0, EInkomsttyp.A, EInkomsttypKategori.BGI, Periodisering.MANAD, se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.Beloppstyp.STUDIEBIDRAG );
    @Getter
    private static final BedomdInkomst sgiBedomdInkomst = new BedomdInkomst(bedomdinkomstid, basresultat.getFaststallsForKundbehov(), 1, basresultat.getVariant(), basresultat.getAvserPerson(),
            basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), 0.0, EInkomsttyp.SKYDDAD, EInkomsttypKategori.SGI, Periodisering.MANAD, se.fk.mimer.producermodels.v2.model.produceratresultat.bedomdinkomst.Beloppstyp.STUDIEBIDRAG );
    @Getter
    private static final BedomdKostnad bedomdKostnad = new BedomdKostnad(bedomdkostnadid, basresultat.getFaststallsForKundbehov(), 1, basresultat.getVariant(),
            basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), 0.0, Kostnadstyp.BOENDEKOSTNAD, Periodisering.MANAD);
    @Getter
    private static final SvenskSocialforsakringsperiod svenskSocialforsakringsperiod = new SvenskSocialforsakringsperiod(svensksocialforsakringsperiodid, basresultat.getFaststallsForKundbehov(),
            1, basresultat.getVariant(), basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), Socialforsakringsgrund.ARBETE, lagrum, Kundbehovsstatus.UNDERUTREDNING);
    @Getter
    private static final Bedomdarbetsformaga bedomdarbetsformaga = new Bedomdarbetsformaga( bedomdarbetsformagaid, basresultat.getFaststallsForKundbehov(), 1, "BedomdArbetsformaga",
            basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), "Testomfattning"
    );
    @Getter
    private static final Bidragssparr bidragssparr = new Bidragssparr( bidragssparrid, basresultat.getFaststallsForKundbehov(), 1, basresultat.getVariant(), basresultat.getAvserPerson(),
            basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), AnledningIngenBidragssparr.OSKALIGT_PGA_BARNETS_BASTA, BedomdMedvetenhet.INTE_AGERAT_MEDVETET_ELLER_GROVT_VARDSLOST,
            false, BidragssparrStatus.BORTTAGEN, GrundForBeslut.VARKEN_ORIKTIGA_UPPGIFTER_ELLER_UNDERLATIT_SIG_ANMALNINGSSKYLDIGHET, GrundForUtredning.BROTTSMISSTANKE
    );
    @Getter
    private static final EnskildNaringsidkare[] array = new EnskildNaringsidkare[0];
    @Getter
    private static final KarenstidEnskildNaringsidkare karenstidEnskildNaringsidkare = new KarenstidEnskildNaringsidkare(karenstidenskildnaringsidkareid, 1, basresultat.getVariant(),
            basresultat.getFaststallsForKundbehov(), basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), 1, array
            );
    @Getter
    private static final RattenTillPeriod rattenTillPeriod = new RattenTillPeriod(rattentillperiodid, basresultat.getFaststallsForKundbehov(), 1, basresultat.getVariant(), basresultat.getAvserPerson(),
            basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), basresultat.getOmfattningstyp(), basresultat.getErsattningstyp()
    );

        // #### PERIOD TILLÄMPLIG LAGSTIFTNING ####
    @Getter
    private static final PeriodTillampligLagstiftning periodTillampligLagstiftningEeg140871 = new PeriodTillampligLagstiftning(periodtillampliglagstiftningid, basresultat.getFaststallsForKundbehov(),
                1, basresultat.getVariant(), basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), EForordning.EEG140871, EArtikel.ARTIKEL_13_2a,
                Land.SVERIGE, "Sverige", Kundbehovsstatus.FASTSTALLT
    );
    @Getter
    private static final PeriodTillampligLagstiftning periodTillampligLagstiftningEeg8592003 = new PeriodTillampligLagstiftning(periodtillampliglagstiftningid, basresultat.getFaststallsForKundbehov(),
            1, basresultat.getVariant(), basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), EForordning.EEG8592003, EArtikel.ARTIKEL13_2a,
            Land.SVERIGE, "Sverige", Kundbehovsstatus.FASTSTALLT
    );
    @Getter
    private static final PeriodTillampligLagstiftning periodTillampligLagstiftningEeg8832004 = new PeriodTillampligLagstiftning(periodtillampliglagstiftningid, basresultat.getFaststallsForKundbehov(),
            1, basresultat.getVariant(), basresultat.getAvserPerson(), basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), EForordning.EEG8832004, EArtikel.ARTIKEL11_3A,
            Land.SVERIGE, "Sverige", Kundbehovsstatus.FASTSTALLT
    );

        // #### INTYG ####
    @Getter
    private static final Intyg intyg = new Intyg(eukortid, basresultat.getFaststallsForKundbehov(), 1, basresultat.getVariant(), basresultat.getAvserPerson(),
                basresultat.getPeriod(), basresultat.getTyp(), basresultat.getStatus(), "Testinstitution", "Testbeskrivning", date
    );
    @Getter
    private static final EUKort eukort = new EUKort(eukortid, intyg.getFaststallsForKundbehov(), 1, intyg.getVariant(), intyg.getAvserPerson(), intyg.getPeriod(), intyg.getTyp(),
                intyg.getStatus(), intyg.getInstitution(), intyg.getBeskrivning(), date, EUKORTNUMMER
    );
    @Getter
    private static final Forhandstillstand forhandstillstand = new Forhandstillstand(forhandstillstandid, intyg.getFaststallsForKundbehov(), 1, intyg.getVariant(), intyg.getAvserPerson(),
            intyg.getPeriod(), intyg.getTyp(), intyg.getStatus(), intyg.getInstitution(), intyg.getBeskrivning(), date, period,
            "Testspecifik beskrivning av tillstånd", "Testbehandling", "Testvårdgivare", "Testläkare"
    );
    @Getter
    private static final UtlandsktForetag utlandsktForetag = new UtlandsktForetag(utlandsktforetagintygid, 1, "UtlandsktForetag", FORETAGSNAMN, ORGANISATIONSNUMMER);
    @Getter
    private static final IntygOmTillampligLagstiftning intygOmTillampligLagstiftning = new IntygOmTillampligLagstiftning( intygomtillampliglagstiftningid, intyg.getFaststallsForKundbehov(), 1,
            intyg.getVariant(), intyg.getAvserPerson(), intyg.getPeriod(), intyg.getTyp(), intyg.getStatus(), intyg.getInstitution(), intyg.getBeskrivning(), date, utlandsktForetag,
            Intygstyp.E101, true, true, true, true
    );
    @Getter
    private static final IVIntyg ivintyg = new IVIntyg(ivintygid, intyg.getFaststallsForKundbehov(), 1, intyg.getVariant(), intyg.getAvserPerson(),
            intyg.getPeriod(), intyg.getTyp(), intyg.getStatus(), intyg.getInstitution(), intyg.getBeskrivning(), date, IVIntygstyp.E106);


    // #### BESLUT ####
    @Getter
    private static final Beslut beslut = new Beslut(beslutid, 0 ,kundbehovid, date, Beslutstyp.SLUTGILTIG, Beslutsutfall.BEVILJAT, beslutsfattareid.toString(), lagrum, BeslutandeOrganisation.FORSAKRINGSKASSAN, "");

    // #### DELGIVNING ####
    @Getter
    private static final Delgivning delgivning = new Delgivning(delgivningid, 1, date, Delgivningstyp.VANLIG, beslutsfattareid);

    // #### ERSÄTTNING ####
        // #### ERSÄTTNINGSTYP ENLIGT LAGRUM ####
    @Getter
    private static final ErsattningstypEnligtLagrum ersattningstypEnligtLagrum = new ErsattningstypEnligtLagrum(
            ersattningstypenligtlagrumid, 1, "ErsattningstypEnligtLagrum", EErsattningstyp.AKTIVITETSSTOD, ELagrumForErsattningstyp.FEA
    );
        // #### OMFATTNING BASERAT PÅ ERSÄTTNINGSGRUND ENLIGT LAGRUM ####
    @Getter
    private static final OmfattningBaseratPaErsattningstypEnligtLagrum omfattningBaseratPaErsattningstypEnligtLagrum = new OmfattningBaseratPaErsattningstypEnligtLagrum(
            omfattningbaseratpaersattningstypenligtlagrumid, 1, "OmfattningBaseratPaErsattningstypEnligtLagrum", 100.0,ersattningstypEnligtLagrum
    );
        // #### BELOPPSTYP BASERAT PÅ ERSÄTTNINGSTYP ENLIGT LAGRUM ####
    @Getter
    private static final BeloppstypBaseratPaErsattningstypEnligtLagrum beloppstypBaseratPaErsattningstypEnligtLagrum = new BeloppstypBaseratPaErsattningstypEnligtLagrum(
            beloppstypbaseratpaersattningstypenligtlagrumid, 1, "BeloppstypBaseratPaErsattningsgrundEnligtLagrum", EBeloppstypKategori.AS, EBeloppstyp.AKASSA, ersattningstypEnligtLagrum
    );
        // #### BERÄKNINGSGRUND BASERAT PÅ ERSATTNINGSTYP ENLIGT LAGRUM ####
    @Getter
    private static final BerakningsgrundBaseratPaErsattningstypEnligtLagrum berakningsgrundBaseratPaErsattningstypEnligtLagrum = new BerakningsgrundBaseratPaErsattningstypEnligtLagrum(
            berakningsgrundbaseratpaersattningstypenligtlagrumid, 1, "BerakningsgrundBaseratPaErsattningstypEnligtLagrum", EBerakningsgrundRegel.AS, EBerakningsgrund.ARBETSBASERAD_AKASSA, ersattningstypEnligtLagrum
    );
        // #### ERSÄTTNING ####
    @Getter
    private static final Ersattning[] samordnasMed = new Ersattning[0];
    @Getter
    private static final Ersattning asersattning = new Ersattning(
            ersattningid, kundbehovid, 1, "Ersattning", fysiskPerson, period, "Testtyp", "Teststatus", 0.0, EBerakningsgrundRegel.AS,  EBerakningsgrund.ARBETSBASERAD_AKASSA, EBeloppstypKategori.AS, EBeloppstyp.AKASSA,
            omfattningBaseratPaErsattningstypEnligtLagrum, ersattningstypEnligtLagrum, Periodisering.MANAD, samordnasMed, ""
    );
    @Getter
    private static final Ersattning sjpersattning = new Ersattning(
            ersattningid, kundbehovid, 1, "Ersattning", fysiskPerson, period, "Testtyp", "Teststatus", 0.0, EBerakningsgrundRegel.SJP, EBerakningsgrund.SGI_A, EBeloppstypKategori.FP, EBeloppstyp.SGI_GRUNDAD,
            omfattningBaseratPaErsattningstypEnligtLagrum, ersattningstypEnligtLagrum, Periodisering.MANAD, samordnasMed, ""
    );
    @Getter
    private static final Ersattning sjpersattning2 = new Ersattning(
            ersattningid, kundbehovid, 1, "Ersattning", fysiskPerson, period, "Testtyp", "Teststatus", 0.0, EBerakningsgrundRegel.SJP, EBerakningsgrund.SGI_A, EBeloppstypKategori.UE, EBeloppstyp.UMFN,
            omfattningBaseratPaErsattningstypEnligtLagrum, ersattningstypEnligtLagrum, Periodisering.MANAD, samordnasMed, ""
    );

    // #### KRAV ####
    @Getter
    private static final Krav krav = new Krav(
            kravid, kundbehovid, 1, "Krav", fysiskPerson, period, "Testtyp", "Teststatus", EBeloppstypKategori.AS, EBeloppstyp.AKASSA,
            Kravtyp.BETALNINGSBELOPP_FOR_UNDERHALLSSTOD, Periodisering.MANAD, omfattningBaseratPaErsattningstypEnligtLagrum
    );
}
