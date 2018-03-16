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
* Levert av:   *Preben Bucher-Johannessen* (*pjo047*)
* Del A: [X] helt ferdig, [ ] delvis ferdig
* Del B: [X] helt ferdig, [X] delvis ferdig
* Del C: [X] helt ferdig, [ ] delvis ferdig
* [X] hele semesteroppgaven er ferdig og klar til retting!

# Del A
## Svar på spørsmål

   **Deloppg. A1**

**a) Tilstander**
 
* IGame - Et IGameobjekt virker som den må ha en tilstand som vet om/er koblet til et IMapView objekt og har dermed tilgang til alt som er på kartet. Den virker også å *utgjøre/være koblet til det grafiske grensesnittet. 

* IMapView - Et IMapView objekt virker som den utgjør kartet i spillet og har tilgang til alle items på kartet, kartets størrelse etc. 

* IGameMap - tilstanden til et IGameMap vil ha de samme som IMapView og i tillegg ha en liste over items som kan endres.

* IItem - Tilstanden til et IItemobjekt virker som, utifra UML, at det må inneholde alle egenskapene til et item, som navn, helse, maks hitpoints til objektet, defence, størrelse og informasjon om symbolet. Objektet må også vite hvordan de håndterer et event, og dermed endrer tilstand, og reagere ift hva som skjer i IGame.

* ILocation - se oppgavetekst.
* IActor - Tilstanden til IActor vil utgjøre det samme som IItem pluss at en Actor vil ha informasjon om Attack og Damage.
* INonPlayer - Virker som den vil ha samme tilstand som IActor må sikkert kunne endre tilstand ift spillets gang.
* IPlayer - Ser ut til å være samme tilstand som IActor må kunne reagere på tastetrykk og da endre tilstand.

**b) Sammenhenger**r
* IGame - Mottar sannsynligvis objekter fra IMapView og IGameMap men også sikkert IItems og IActor objekter for å kunne sjekke om spillet oppfører seg ihht gitte regler i spillet, samt utføre eventuelle hendelser i spillet.
* IMapView - Mottar sannsynligvis ILocation objekter fra IGame for å kunne returnere ILocation Objekter med hvor ting befinner seg ift IArea objektet el.l.
* IGameMap - Er nok utvidet fra ImapView for å kunne utføre handlinger på objektet som ellers i andre sammenhenger ikke er ønskelig.
* IItem - Er basis for et Item i spillet og må sannsynligvis motta objekter fra IGame objektet for å kunne endre tilstanden til et IItem objekt.
* ILocation - Mottar sannsynligvis objekter fra IMapView for å kunne returnere en ny ILocation. Evtl si hvor noe befinner seg ift IArea.
* IActor - Er utvidet fra IItem og vil anta at den mottar og returnerer samme objekter som IItem men vil også kunne returnere noen utvidede tilstander.
* INonPlayer - Utvidet fra IActor og burde kunne endre tilstand ved spillets gang.
* IPlayer - Utvidet fra IActor og burde i tillegg kunne reagere på et tastetrykk
**c)** 
Dette er separert sannsynligvis fordi IGameMap har visse metoder som IMapView ikke har. IMapView kan sannsynligvis bare returnere info om objektet mens IGameMap kanskje i tillegg kan endre på objektet el. l. info om kartet og dets tilstand. Det er sannsynligvis gjort slik for å kunne bruke grensesnittet IMapView i andre sammenhenger enn i spill sammenheng.

**d)** 
Disse er forskjellig sannsynligvis da en non-player kun skal reagere på spillets gang mens en player må kunne reagere på input fra brukeren (tastaturet). Etter *min mening gir det mening å separere disse slik at man kun må bestemme reaksjonene på spillet/tastatur der hvor det er nødvendig og ikke for alle objekter som blir opprettet.

   **Deloppg. A2**
   

**e)** 
Det virker som mine antakelser av IItem var noenlunde riktig selv om de fleste feltvariablene for Carrot er definert inni metodene, (f.eks. navn returnerer alltid "carrot" og er ikke definert som en egen felvariabel navn). hp er definert som helsen til en carrot som privat feltvariabel da denne endres ift. handleDamage metoden som vi også antok. Rabbit er en NonPlayer som har veldig lik tilstand som vi nevnte over, navn, hp, osv. I tillegg har Rabbit en feltvariabel for food som har en inflytelse på helse feltvariabelen over tid. Det finnes ingen metode for getHunger da dette ikke er del av interface over og man kan selvsagt innføre mange flere feltvariabler som påvirker tilstanden for hver IItem/IActor/INonPlayer som thirst, tiredness etc. her er det bare fantasien som begrenser.  
**f)** 
Slik som jeg forstår det finner ikke Rabbit ut selv hvor den er, det er det Game som gjør med currentLocation når doTurn blir kalt på. Game gjennomgår for hver Actor i spillet og henter da ut fra map (et GameMAp object) location til en actor og kaller deretter på getLocation for Rabbit og lagrer denne i sin currentlocation feltvariabel. Senere kaller den på doTurn for en Actor (f.eks. Rabbit) og da utfører rabbit sin doTurn handling. Rabbit kaller da på getLocalItems fra Game for å finne ut hvilke Items som er i nærheten. getLocalItems fra Game kaller på getItems med currentLocation fra GameMap som returnerer en liste med objekter først til Game som returnerer det videre til Rabbit. Deretter gjennomgår Rabbit alle Items for å sjekke om det er en carrot å spise. Hvis ikke går den i en tilfeldig retning ved å kalle på canGo fra Game for hver mulige retning (North south east west). Game kaller videre på canGo fra GameMap ved hjelp av currentlocation og direction som den fikk av Rabbit og returnerer en boolsk verdi som den får fra GameMap ihht om det er et lovlig trekk eller ikke. Rabbit velger deretter en tilfeldig lovlig retning og beveger seg dit ved å kalle på move fra Game. 

**g)** 
Se utfyllende svar over. Game har en liste over Actors som har noe igjen å gjøre denne "turnen" og utfører doTurn og getLocation for hver av disse med feltvariabelen currentActor. Dermed er det Game som utfører getLocation og ikke Rabbit. getLocation blir utført av Game for enhver Actor som har noe igjen å gjøre denne turnen. Rabbit spør aldri om sin location fra Game da Rabbit ikke trenger sin location til noe i sine metoder derimot spør Game GameMap on location til currentActor. (etter min mening er dette spørsmålet litt feil formulert ift objektene men kanskje jeg missforstår??)

   **Deloppg A3**
   

**Smart kanin** 

**a)** 
Første kjøring: ikke alle gulrøtter ble spist opp. Endret helsen til kaninene til 50 og carrot til 15. Denne gangen ble alle carrots borte (spist) og kaninene hoppet rundt en del lengre..

**b)** 
Endret først helsen til kaninene og gulrøttene tilbake til normal. Deretter..
Endret koden under TODO til følgende:  if(game.canGo(GridDirection.NORTH))
												game.move(GridDirection.NORTH)
Kaninene gikk da nord helt til de møtte på en vegg eller en annen kanin og spiste opp alle gulerøttene på veien. Resultatet ble omtrent det samme som hvis de beveget seg tilfeldig altså ikke alle ble spist opp.

**c)** 
Kommenterte ut koden "Collections.shuffle(possibleMoves);" og det endte med at de fleste kaninene gikk EAST helt til de møtte en vegg og deretter NORTH. Resultatet ble omtrent det samme som under b) og med tilfeldig bevegelser. Altså Ikke alle gulerøttene ble spist opp. 
**d)** 
Denne gangen ble alle glurøttene spist opp. Implementerte at hvis kaninen ser en gulrot i en av retningenen skal den bevege seg dit. hvis ikke skal den gå i en tilfeldig retning slik som tidligere. 

**e)** 
Implementerte getPossibleMoves i Game omtrent slik som det var løst i Rabbit. Denne kan man endre slik at possiblemoves også inkluderer diagonale bevegelser hvis man ønsker. getPossibleMoves kan nå også brukes av andre Actors. 

**Bedre Gulrøtter**

**a)** 
Endret gulrøtter sin hp til å bli 0 istedet for -1 når de blir skadet. Dette gjør fortsatt ikke at food øker for kaninene. handleDamage returnerer nå 0 og dermed blir ikke food økt med eat lenger. Hvis vi hadde satt hp til 1 ville kaninene holdt ut lengre da de kunen i teorien spist en gulrot for alltid. Kaninene er desuten skjult av gulrøttene mens gulrøttene blir spist og når gulrøttene ikke forsvinner vil kaninene for alltid være skjult av gulrøttene på kartet.

**b)** 
Det ser ikke ut til at helsen blir økt for hver runde. Dette er nok fordi Carrot er en IItem og ikke en IActor. Game kjører doTurn kun for hver INonPlayer. Hvis vi endrer grensesnittet til Carrot til INonPlayer burde det fungere. Evtl kan vi endre metoden til Game til å gjennomgå do Turn for alle IItems men dette vil kanskje få større implikasjoner senere hvis vi ønsker å skille mellom Actors, IItems og INonplayers. Jeg har valgt å ikke gjøre dette her men heller gjøre oppg. c) for å generere nye gulrøtter. 

**c)** Jeg satte gulrøttene tilbake til -1 slik at de blir borte og dermed at kaninene får en sjanse til å bevege seg igjen og kanskje finne en ny generert gulrot. Legger nå til en ny carrot med 20% sannsynlighet inni doTurn i Game, før man begynner gjennomgangen av hvert IItem. Se kildekode for kommentarer/forklaring.

# Del B
## Svar på spørsmål

   **Deloppg. B1**
   
**a)** 
Metoden som lager nye objekter heter createItem.

**b)-d)** 
Lagde en klasse Apple.java som er noenlunde lik carrot. endret fargen til rød og stilk fargen til brun. Det tegnes nå et rødt eple i tillegg til carrots. La til et par A'er i kartet slik at det nå blir tegnet noen (litt avlange) røde epler i kartet. Disse blir foreløpig ikke spist av kaninene desverre.

Kommentar til avansert del: vil prøve meg på denne når jeg er ferdig med hele* oppgaven hvis jeg får tid til det.

   **Deloppg. B2**
   
**a)-e)** 
Implementerte Player i en ny pakke "int101.v18.rogue101.player" med symbolet @ slik at spilleren kan bevege seg rundt på kartet og mister 1 hit point hvis den treffer en vegg. Hver gang man trykker en tast blir også "Player hit points: X" vist som status i spillskjermen. Se kode for implementasjon med kommentarer. 

**f)** 
Implementerte tester i klassen PlayerTest for å bevege seg i forskjellige retninger og for å bevege seg i retning en vegg. Alle testene blir godkjent.

Kommentar til oppg: Jeg vil utvide/gjøre nødvendige endringer når jeg kommer videre i utviklingen av spillet...

   **Deloppg. B3**

a) La til return new Dust() i createItem metoden

b) La til en for-løkke som gjennomgår indeksene i listen av items i en location og setter inn item i riktig rekkefølge etter størrelse. Hvis man kommer til enden av løkken setter man item bare inn i listen på siste posisjon.

c) Fullførte testSortedAdd ved å generere tilfeldige items og legge til 5 stk på en location og sjekke om hvert element har size som er mindre enn det etter i listen av items.

Kommentar til avansert: Vil komme tilbake til implementasjonen av sortedList hvis jeg får tid til dette.

   **Deloppg. B4**
   
a) La til keys P og D i keypressed.

b) implementerte metodene pickup og drop i Player for å håndtere dette spilleren kan foreløpig bare plukke opp et item av gangen. man plukker også opp det største elementet på hver location og må da droppe et item i en annen location for å plukke opp items som befinner seg under dette. implementerte også at spilleren ikke kan plukke opp flere ting samtidig.

c) implementerte drop med passende beskjed hvis spilleren ikke holder noe item. 

d) la til Item i status bar

   **Deloppg. B5**
   
a) implementerte først for dist = 1.

b) deretter la jeg til mulighet for å hente ut naboer for dist > 1 Dette løste jeg med en while loop som gjennomgår for alle naboer til hver nabo i listen og legger de til listen hvis de ikke allerede eksisterer.

c) La til tester for å teste at gridDistance alltid er mindre enn dist og i tillegg metoder som sjekker størrelsen på listen for en location midt i et kart, på kanten og i hjørnet. 

d) la også til en metode som sjekker rekkefølgen på location. gridDistance må da alltid være mindre eller lik den neste location i listen.

   **Deloppg. B6**

a) implementerte ferdig koden, se Game.attack. 

b) La til attack for rabbit slik at en rabbit sjekker alle retninger og angriper en evtl IActor eller går til en evtl carrot. Hvis det ikke finnes noen carrot eller actor går Rabbit i en tilfeldig retning slik som tidligere.

c) La til angrep for spilleren. Sjekker nå først om retningen spilleren vil gå i er ledig (altså ikke ulovlig og ikke IActor eller Wall) hvis det er mulig beveger spilleren seg dit.
Hvis ikke vet jeg at enten er cellen opptatt (av Wall eller IActor) så da sjekker jeg om retningen er lovlig (altså ikke utenfor brettet) og angriper i så fall det som befinner seg på første plass av getAll i denne retningen. 
Hvis ikke skriver jeg ut en beskjed til brukeren om at det ikke er mulig å gå videre og spilleren miste et helsepoeng for å ha prøvd (tough-luck;)

   **Deloppg. B7: SPØRSMÅL**
a) I Labyrint Laben brukte vi grid og hjelpemetoden isValidPosition med koordinater for x og y. Dette er relativt oversiktelig men har ulempen med at du hele tiden må bruke playerX og playerY og sjekke posisjonene ift. denne. I spillet bruker jeg allNeighbours fra ILocation som returnerer alle naboer i alle 8 retninger gitt at de ikke er utenfor spillområdet. Dette gjør det mer anvendbart når man skal sjekke alle naboer ettersom "ulovlige" naboer ikke blir tatt med automatisk og jeg slipper å få programmet til å sjekke om f.eks. NORTH er en gyldig retning. Metoden i Game er dermed mer generell og bedre for en programmerer å bruke men mindre "oversiktlig" da det ikke alltid er så lett å skjønne hvor de "gyldige retningene" kommer fra.

b) Fordeler ved å la ting gå igjennom Game:
Game håndterer unntak/regler som GameMap f.eks. ikke holder styr på. Ved å la trekkene gå igjennom game vil man også endre på game-objekt som trekkene blir kalt på direkte istedet for å måtte ha to objekter (game og gameMap). Man gjør det også lettere å håndtere hvem sin tur det er. Eks: Hvis en spiller skal flytte seg til et felt sjekker Game først om det er et lovlig trekk ved å kalle på de riktige metodene istedet for at forflyttingen skjer uten sjekk og game oppdaterer da alle objektene inneholdt i det nåværende spillet.
Ulemper: 
Kan bli veldig mye for en klasse å håndtere ved veldig mange trekk i tillegg til alle reglene osv. Man kunne vurdert å splitte opp Game i flere klasser for å gjøre det mer oversiktelig som GameMove,GameRules osv men foreløpig er det enda oversiktelig nok. 

c) Jeg har prøvd å gjøre noen slike forbedringer underveis. Man kunne f.eks. ved første implementasjon plukke opp flere ting etterhverandre og dermed ville de items du hadde plukket opp "forsvinne". Dette kommer jeg til å endre på senere slik at jeg kan plukke opp flere Items i en liste.
Også GameMap.hasNeighbour antar at input ILocation "from" er en gyldig location og gjør ingen tester på det før den kaller på canGo.
Display Status sjekker heller ikke om Strengen som skal skrives ut er for lang. Dette kunne med fordel vært gjort for å passe på at ikke strengen blir avkortet.

d) Tilstand:
Ser at flere av implementasjonene har tilstander (feltvariabler) som jeg ikke så når før jeg kikket på koden og jobbet med det. 
Sammenheng:
Sammenhengene ble også mye klarere når jeg begynte å bruke og sette meg ordentlig inn i koden. F.eks. at det er sammenhenger mellom Game og nesten alle de andre klassene. At en IActor nå gjennom metodene sine "endrer" på game objektet som blir sendt som argument i metodene attack og move. At GameMap har et Multigrid som feltvariabel og en hashcode liste av items for å holde styr på navigering og hvor ting er på kartet. osv.

Endelig svar: Ja jeg tenker nok veldig annerledes nå som jeg har sett på koden og ser sammenhengene/tilstandene implementert.
# Del C
## Oversikt over designvalg og hva du har gjort
*   
   **ZombieLab**
Et zombie survival spill hvor en ukjent helt våkner uten å huske noe og må finne veien ut fra en rar laboratorie-bygning fyllt med, merkelig nok rabiate kaniner og zombier. Overlevelse blir det viktigste og han må bruke tingene rundt seg for å overleve..

**Overordnet:** 
Jeg har endret på spillet og dermed laget mine egne klasser av både main og Player bl.a. Under forklarer jeg overordnet hva jeg har gjort i hver klasse. Dette samt kommentarer rundt i koden tilsvarer dermed mitt svar på Deloppg. C. Spillet er ikke 100% ferdig implementert men jeg har prøvd meg på samtlige ting nevnt i DelC for å vise at jeg kan bruke mest mulig aspekter av det vi har lært til nå i kurset. Spillet er dermed ikke perfekt men spillet samt koden burde vise at jeg kan stoffet hittil godt. Ellers så er spillet nå ganske vanskelig og det er lett for å bli tatt av zombier spesielt hvis ikke du husker på å wielde våpen eller bruke first aid kits.. Zombiapokalypse er en tøff verden..

   **MainDELC.java**
Lagde en egen main slik at man fortsatt kan kjøre den gamle main metoden for å teste arbeidet fra Oppg. A&B. I denne main metoden har jeg bl.a. endret slik at jeg bruker GameDELC
	
	STYLING C3-C5
Lagt til Symbola som font og innført en del emojis som figurer for de forskjellige klassene. Har også endret farge for å gjøre spillet mer fargerikt. Kjør spillet evtl se de forskjellige objektene for symboler. Jeg har ikke lagt fokuset på styling i spillutviklingen og heller fokusert på spillets funksjoner. Etter min mening er funksjoner viktigere for Rouge like spill. :)

   **GameDELC.java**
implementerte et eget Game for ikke å ødelegge arbeidet fra oppg A&B. Her har jeg for det første brukt et annet kart "zombies3.txt" under maps. I dette kartet har jeg også en rekke nye objekter som jeg bruker i mitt spill, bl.a. Zombie, Door, Flesh, Knife, Torch, Shadow, Sword, osv (se under). Bruker også PlayerDELC istedet for Player. 
	
	SKYGGE C7
Har endret opprettelsen av spillbrettet slik at det alltid legges en "Shadow" over hvert item i spillet for å skjule det. Alle ruter spilleren går på (pluss alle naboruter innenfor getVisible ny metode implementert i iActor), får skyggen fjernet. Dette gjør at spilleren ikke ser hva som skjer på spillbrettet og gjør spillet mer mystisk..

	Inventory C2
Laget en metode displayInventory som spilleren bruker for å skrive ut under Status linjen hva spilleren har med seg.

	KeyMap C9
Laget en metode for å skrive en beskjed ut på linje 3 som blir brukt av spilleren for å skrive ut en Key-Map altså oversikt over hvilke taster man kan trykke på og hva de gjør.

	Attack C10
Endret oppsettet til Attack til at hvis spilleren blir drept kjører jeg metoden gameOver som skriver ut en avsluttende melding og klarerer alt annet. Endret også at hvis en kanin dør blir det lagt ut flesh som Zombiene vil spise.

	hasDoor og openDoor C11
implementerte to metoder for å avgjøre om en retning inneholder en door og en metode for å fjerne døren.

	gameOver og win C6
Har lagt til to metoder som skriver ut en melding om at spilleren har vunnet/tapt og spilleren blir i begge tilfeller borte. Fant ikke helt ut hvordan jeg skulle få spillet til å stoppe i det tilfellet (utenom å avslutte alt med System.exit) så det er et forbedringspotensiale.
	
	

**PlayerDELC.java**
Også her implementerte jeg en egen spiller. Dette er fordi jeg har gjort om på en del av implementasjonene fra Del A-B og i tillegg lagt til ganske mange nye funksjoner. bl.a. har en spiller nå et inventory eller Backpack som implementerer metoden IContainer() (se under). Spilleren kan også plukke opp en ny backpack å sette denne på ryggen for å utvide bærekapasiteten.
	
	BackPack C2
Spilleren har nå et inventory som implementeres av IContainer (se under).Han kan i tillegg ha en ryggsekk på ryggen og legge fra seg denne.

	Visibility C7
Spilleren (og alle Actors) har også fått en ny feltvariabel "vis" som blir brukt av GameDELC (se over) for å fjerne skygge. Spilleren kan øke denne variabelen ved å plukke opp Torch (lommelykt). 

	Weapon wielding C1
Spilleren må equipt eller wielde et våpen for å kunne angripe bedre. Dette er avgjørende for overlevelse. Man må altså da plukke opp noe av typen IWeapon (se under) og deretter trykke på w for å bruke våpenet. Metoden changeWeap bytte mellom våpnene spilleren har med seg. Ved å wielde et våpen endrer også spilleren sin totale Attack og Damage. Hvis spilleren bare bærer med seg et våpen skjer det ingenting.

	DoTurn C11
Endret oppsettet til DoTurn slik at spilleren bare går i en retning hvis canGo blir godkjent. Endret også canGo slik at den overser en Door i tillegg til Wall og IActor. Hvis man ikke kan gå i den retningen må jeg først avgjøre om det er en dør og dermed prøve å åpne den døren med en Key (se under). Hvis man ikke har en key kommer man forbi døren. Hvis det ikke er en dør i retningen angriper spilleren det som er den veien. Spilleren kan dermed også angripe en vegg selv om veggene har ganske mye helse.

	Pickup
spilleren kan plukke opp et objekt på spillbrettet. Dette påvirker spilleren på forskjellige måter og hvis spilleren plukker opp et Exit Item blir spillet avsluttet og spilleren borte. Andre Items påvirker spilleren også som beskrevet under

**ZombieLab.txt**
Nytt kart som blir brukt av GameDELC tilpasset ZombieLab..

**IContainer.java C2**
Har laget en Interface IContainer med T som utvider Item IContainer selv er også utvidet av IItem. Altså kan jeg plassere IContainere rundt påkartet og plukke dem opp/slippe de ned.

**IWeapon.java C1**
Lagde en egen interface for Weapon som utvider IItem. Et IWeapon må kunne returnere Attack og Damage slik som en spiller.

**Backpack.java C2**
En implementasjon av IContainer som blir brukt av spiller som inventory eller backpack. Denne gjør at spilleren kan bære med seg flere ting. Jeg har implementert HashMap for å holde styr på alle Items i Backpacken og hvor mange det er av hvert Item. Merk at Item må være det samme Objektet og jeg har derfor ikke lenger opprettet nye Objekter for hver gang av de Objektene spilleren kan plukke opp flere av. Dette kan man sikkert løse bedre ved å override HashMap etc.

**FirstAidKit.java C1**
Et Iitem som kan konsumeres av spilleren for å øke helse.

**Door.java C11**
Implementerte et kosept med en Dør som ikke kan angripes av spilleren

**Key.java C11**
Har implementert nøkler som kan brukes til å åpne dører.

**Knife.java C1**
Er implementert av IWeapon og er dermed et IItem som også har Attack og Damage som. Knife øker Damage og Attack til spilleren litt når den wieldes.

**Sword.java C1**
Er implementert av IWeapon og er dermed et IItem som også har Attack og Damage som. Sword øker Damage og Attack til spilleren mer når den wieldes.

**Flesh.java C12**
Er et Item som Zombier liker å spise.. Blir sluppet hver gang en Kanin eller andre levende ting dør.

**Torch.java C7**
Item som øker spilleren visibility

**Shadow.java C7**
Skygge som ligger over alt på kartet og blir fjerner for hver tur spilleren har. avhenger sv spillerens Visibility..

**Exit.java**
Item som er utgangen i spillet. Spilleren blir borte når har plukker opp dette og spiller kjører won() metoden som skriver ut beskjed om at spilleren fant veien ut TBC osv..



q

	



 
