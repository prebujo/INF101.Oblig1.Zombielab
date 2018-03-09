# [Semesteroppgave 1: “Rogue One oh one”](https://retting.ii.uib.no/inf101.v18.sem1/blob/master/SEM-1.md)


* **README**
* [Oversikt](SEM-1.md) – [Praktisk informasjon 5%](SEM-1.md#praktisk-informasjon)
* [Del A: Bakgrunn, modellering og utforskning 15%](SEM-1_DEL-A.md)
* [Del B: Fullfør basisimplementasjonen 40%](SEM-1_DEL-B.md)
* [Del C: Videreutvikling 40%](SEM-1_DEL-C.md)

Dette prosjektet inneholder [Semesteroppgave 1](SEM-1.md). Du kan også [lese oppgaven online](https://retting.ii.uib.no/inf101.v18.oppgaver/inf101.v18.sem1/blob/master/SEM-1.md) (kan evt. ha små oppdateringer i oppgaveteksten som ikke er med i din private kopi).

**Innleveringsfrist:**
* Del A + minst to deloppgaver av Del B skal være ferdig til **fredag 9. mars kl. 2359**. 
* Hele oppgaven skal være ferdig til **onsdag 14. mars kl. 2359**

(Kryss av under her, i README.md, så kan vi følge med på om du anser deg som ferdig med ting eller ikke. Hvis du er helt ferdig til den første fristen, eller før den andre fristen, kan du si fra til gruppeleder slik at de kan begynne å rette.)

**Utsettelse:** Hvis du trenger forlenget frist er det mulig å be om det (spør gruppeleder – evt. foreleser/assistenter hvis det er en spesiell situasjon). Hvis du ber om utsettelse bør du helst være i gang (ha gjort litt ting, og pushet) innen den første fristen.
   * Noen dagers utsettelse går helt fint uten begrunnelse, siden oppgaven er litt forsinket.
   * Hvis du jobber med labbene fremdeles, si ifra om det, og så kan du få litt ekstra tid til å gjøre ferdig labbene før du går i gang med semesteroppgaven. Det er veldig greit om du er ferdig med Lab 4 først.
   * Om det er spesielle grunner til at du vil trenge lengre tid, så er det bare å ta kontakt, så kan vi avtale noe. Ta også kontakt om du [trenger annen tilrettelegging](http://www.uib.no/student/49241/trenger-du-tilrettelegging-av-ditt-studiel%C3%B8p). 
   

# Fyll inn egne svar/beskrivelse/kommentarer til prosjektet under
* Levert av:   *NAVN* (*BRUKERNAVN*)
* Del A: [ ] helt ferdig, [ ] delvis ferdig
* Del B: [ ] helt ferdig, [ ] delvis ferdig
* Del C: [ ] helt ferdig, [ ] delvis ferdig
* [ ] hele semesteroppgaven er ferdig og klar til retting!

# Del A
## Svar på spørsmål
* ...
*Deloppg. A1
*a) Tilstander 
*IGame - Et IGameobjekt virker som den må ha en tilstand som vet om/er koblet til et IMapView objekt og har dermed tilgang til alt som er på kartet. Den virker også å *utgjøre/være koblet til det grafiske grensesnittet. 
*IMapView - Et IMapView objekt virker som den utgjør kartet i spillet og har tilgang til alle items på kartet, kartets størrelse etc. 
*IGameMap - tilstanden til et IGameMap vil ha de samme som IMapView og i tillegg ha en liste over items som kan endres.
*IItem - Tilstanden til et IItemobjekt virker som, utifra UML, at det må inneholde alle egenskapene til et item, som navn, helse, maks hitpoints til objektet, defence, størrelse og informasjon om symbolet. Objektet må også vite hvordan de håndterer et event, og dermed endrer tilstand, og reagere ift hva som skjer i IGame..
*ILocation - se oppgavetekst.
*IActor - Tilstanden til IActor vil utgjøre det samme som IItem pluss at en Actor vil ha informasjon om Attack og Damage.
*INonPlayer - Virker som den vil ha samme tilstand som IActor må sikkert kunne endre tilstand ift spillets gang.
*IPlayer - Ser ut til å være samme tilstand som IActor må kunne reagere på tastetrykk og da endre tilstand.

*b) Sammenhenger
*IGame - Mottar sannsynligvis objekter fra IMapView og IGameMap men også sikkert IItems og IActor objekter for å kunne sjekke om spillet oppfører seg ihht gitte regler i spillet, samt utføre eventuelle hendelser i spillet.
*IMapView - Mottar sannsynligvis ILocation objekter fra IGame for å kunne returnere ILocation Objekter med hvor ting befinner seg ift IArea objektet el.l.
*IGameMap - Er nok utvidet fra ImapView for å kunne utføre handlinger på objektet som ellers i andre sammenhenger ikke er ønskelig.
*IItem - Er basis for et Item i spillet og må sannsynligvis motta objekter fra IGame objektet for å kunne endre tilstanden til et IItem objekt.
*ILocation - Mottar sannsynligvis objekter fra IMapView for å kunne returnere en ny ILocation. Evtl si hvor noe befinner seg ift IArea.
*IActor - Er utvidet fra IItem og vil anta at den mottar og returnerer samme objekter som IItem men vil også kunne returnere noen utvidede tilstander.
*INonPlayer - Utvidet fra IActor og burde kunne endre tilstand ved spillets gang.
*IPlayer - Utvidet fra IActor og burde i tillegg kunne reagere på et tastetrykk.

*c) Dette er separert sannsynligvis fordi IGameMap har visse metoder som IMapView ikke har. IMapView kan sannsynligvis bare returnere info om objektet mens IGameMap kanskje i tillegg kan endre på objektet el. l. info om kartet og dets tilstand. Det er sannsynligvis gjort slik for å kunne bruke grensesnittet IMapView i andre sammenhenger enn i spill sammenheng. 

*d) Disse er forskjellig sannsynligvis da en non-player kun skal reagere på spillets gang mens en player må kunne reagere på input fra brukeren (tastaturet). Etter *min mening gir det mening å separere disse slik at man kun må bestemme reaksjonene på spillet/tastatur der hvor det er nødvendig og ikke for alle objekter som blir opprettet.

*Deloppg. A2
*e) Det virker som mine antakelser av IItem var noenlunde riktig selv om de fleste feltvariablene for Carrot er definert inni metodene, (f.eks. navn returnerer alltid "carrot" og er ikke definert som en egen felvariabel navn). hp er definert som helsen til en carrot som privat feltvariabel da denne endres ift. handleDamage metoden som vi også antok. Rabbit er en NonPlayer som har veldig lik tilstand som vi nevnte over, navn, hp, osv. I tillegg har Rabbit en feltvariabel for food som har en inflytelse på helse feltvariabelen over tid. Det finnes ingen metode for getHunger da dette ikke er del av interface over og man kan selvsagt innføre mange flere feltvariabler som påvirker tilstanden for hver IItem/IActor/INonPlayer som thirst, tiredness etc. her er det bare fantasien som begrenser.  
*f) Slik som jeg forstår det finner ikke Rabbit ut selv hvor den er, det er det Game som gjør med currentlocation doTurn blir kalt på av Game   


# Del B
## Svar på spørsmål
* ...

# Del C
## Oversikt over designvalg og hva du har gjort
* ... blah, blah, er implementert i klassen [KurtMario](src/inf101/v18/rogue101/player/KurtMario.java), blah, blah `ITurtleShell` ...
 
