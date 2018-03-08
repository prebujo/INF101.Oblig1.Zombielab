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
*IMapView - Et IMapView objekt virker som den utgjør kartet i spillet og har tilgang til om alle items på kartet, kartets størrelse etc. 
*IGameMap - tilstanden til et IGameMap vil ha de samme som IMapView og i tillegg ha en liste over items som kan endres.
*IItem - Tilstanden til et IItemobjekt virker som det utgjør alle egenskaper til et item, navn, hit points, maks hitpoints til objektet, defence, størrelse og informasjon om symbolet. De må også vite hvordan de håndterer et event siden de virker som de er koblet til Event. 
*ILocation - se oppgavetekst.
*IActor - Tilstanden til IActor vil utgjøre det samme som IItem pluss at en Actor vil ha informasjon om Attack og Damage.
*INonPlayer - Samme tilstand som IActor men kan også interagere med IGame.
*IPlayer - Samme som IActor men vil også kunne håndtere et tastetrykk og er dermed koblet til IGame.

*b) Sammenhenger
*IGame - Mottar objekter fra IMapView og IGameMap
*IMapView - Henger sammen med IGrid 
*IGameMap - Utvidet fra IMapView og har tar imot Items fra 
*IItem - 
*ILocation - 
*IActor - Utvidet fra IItem og har dermed samme grensesnitt som IItem med noen tillegg.
*INonPlayer - Utvidet fra IActor og kan i tillegg utføre en turn fra Game.
*IPlayer - Utvidet fra IActor og kan i tillegg reagere på et tastetrykk.

*c) Dette er separert sannsynligvis fordi IGameMap kan gjøre endringer på kartet mens IMapView kun kan returnere info om kartet og dets tilstand. Det gir mening *å holde dette separat slik at man kan bruke grensesnittet IMapView i andre sammenhenger enn spill. 

*d) Disse er forskjellig sannsynligvis da en non-player kun skal reagere på en turn mens en player må kunne reagere på input fra brukeren (tastaturet). Etter *min mening gir det mening å separere disse. Evtl kunne man sagt at alle IActors kan reagere på en turn. Da kunne man også implementere endringer for en Player *for hver turn (f.eks. at han får mer hit points etter hver turn eller lignende) og dermed ikke ha noen NonPlayer grensesnitt. 


# Del B
## Svar på spørsmål
* ...

# Del C
## Oversikt over designvalg og hva du har gjort
* ... blah, blah, er implementert i klassen [KurtMario](src/inf101/v18/rogue101/player/KurtMario.java), blah, blah `ITurtleShell` ...
 
