# [Semesteroppgave 1: “Rogue One oh one”](https://retting.ii.uib.no/inf101.v18.sem1/blob/master/SEM-1_DEL-B.md) Del B: Implementasjonsarbeid

**OBS: koden / oppgaveteksen er ikke helt ferdig ennå – du kan lese den og se på ting online men ikke laste den ned**

* [Oversikt](SEM-1.md)
* [Praktisk informasjon](SEM-1.md#praktisk-informasjon)
* [Del A: Bakgrunn, modellering og utforskning](SEM-1_DEL-A.md)
* [Del B: Gjør ferdig nødvendige komponenter](SEM-1_DEL-B.md)
* [Del C: Selvvalgt del](SEM-1_DEL-C.md)

I denne delen av semesteroppgaven skal du implementere en del konkrete metoder.

### Deloppgave B1: Objekt-fabrikk
* a) Se på konstruktøren i Game-klassen, og se hvordan kartet blir fylt inn. Finn metoden som lager nye objekter basert på strenger ('#', '.', 'R', osv) dette er en såkalt [factory/fabrikk-metode](https://en.wikipedia.org/wiki/Factory_method_pattern). Det er vanlig å bruke når vi har mange klasser som implementerer det samme grensesnittet, og vi vil gjøre det lett for andre deler av programmet å opprette objekter uten å kjenne til klassene.
* **b)** Lag deg en ny klasse som implementerer IItem. Du kan ta utgangspunkt i en av klassene du har fra før (f.eks. `Carrot`) og du kan velge navn selv (f.eks. `CarrotCake`). Funksjonaliteten kan (foreløpig) være helt lik.
* **c)** Legg klassen til i fabrikk-metoden. Du må velge et tegn som skal representere objekter av klassen (f.eks. `"c"`). Bruk dette tegnet både i fabrikk-metoden i `Game` og i `getSymbol()`-metoden i den nye klassen din (det er ingen direkte sammenheng mellom disse, men greit om de stemmer overens).
* **d)** Legg til det nye tegnet ditt i standard-kartet (ligger i [src/inf101/v18/rogue101/map/maps/level1.txt](src/inf101/v18/rogue101/map/maps/level1.txt) – eventuelt ligger det et innebygget kart i `Main`-klassen hvis vi av en eller annen grunn ikke finner kartfilen), og se at gulrotkaken (eller det du har laget) dukker opp på skjermen.

Symbolene på skjermen blir hentet fra `getSymbol()` (evt. `getPrintSymbol()` om du har definert den). I tillegg til å kunne tegne tekst-tegn kaller programmet `draw()`-metoden hvor du kan legge inn egen grafikk; hvis denne returnerer `true` blir tegnet fra `getSymbol()` ikke tegnet på skjermen. Så hvis du bare ser ekstra `X`-er, så har du kopiert `ExampleItem` uten å endre `getSymbol()`, og hvis du bare ser ekstra gulrøtter, så har du kopiert `Carrot` uten å oppdatere grafikken (du kan bare slette draw-metoden og returnere false).

*(Avansert mulighet (ikke del av oppgaven): i stedet for å lage en stor fabrikk med `switch` går det an å bygge en oversikt over fabrikkene mens programmet kjører. Du kan registrere symbolet for den nye klassen + en kodesnutt som lager objekt i `itemFactories` i `Game`: f.eks. `itemFactories.put("R", () -> new Rabbit())`. `default`-tilfellet i `switch`en vil prøve å slå opp i `itemFactories` og kalle kodesnutten hvis den finner noe. Registreringen kan gjøres i konstruktøren til `Game`, eller du kan lage en ekstra `addFactory`-metode.)*

### Deloppgave B2: Minimal Player
Du er sikkert lei av at det bare er kaninene som får lov til å ha det gøy.
* **a)** Lag en klasse `Player implements IPlayer`. Du kan følge mønsteret fra de andre IItem-klassene, men du trenger litt andre metoder. Der hvor `Rabbit` har `doTurn()` skal `Player` ha `keyPressed()`. Du kan la denne være tom til å begynne med.
* **b)** Du må oppdatere fabrikken i `Game` så den også lager `Player`-objekter; de har tradisjonelt symboled `@` (i utlevert kode gir det deg en `ExampleItem`).
* **c)** Metoden `keyPressed()` i `IPlayer` tar et `KeyCode`-object, og kalles hver gang spilleren
trykker på en tast. Knappen indikeres med KeyCode-en (en for hver tast på tastaturet + at man kan sjekke f.eks. om *Ctrl*-tasten er trykket inn). F.eks. vil følgende kode sjekke om 
venstre-tasten ble trykket, og kalle `tryToMove()` (denne metoden er ikke implementert ennå):


    public void keyPressed(IGame game, KeyCode key) {
		if (key == KeyCode.LEFT) {
			tryToMove(game, GridDirection.WEST);
		}
	}

Gjør ferdig denne metoden så den kaller `tryToMove` med `GridDirection.EAST`, `GridDirection.NORTH`, eller `GridDirection.SOUTH` når spilleren trykker `KeyCode.RIGHT`, `KeyCode.UP`, eller `KeyCode.DOWN`.

(Dette er typisk enkel *event-drevet programmering* hvor metoder i programmet vårt blir kalt når
ting skjer utenfor programmet. Dette er vanlig for spill, nettverkstjenere og programmer med grafiske brukergrensesnitt – man har en metode som blir kalt for hvert tidssteg, og metoder som blir kalt når tastatur eller mus brukes eller det kommer inn en ny forespørsel over nettet.)

* **d)** Du trenger også metoden `tryToMove()` (du kan kalle den hva du vil, men det er greit å ha den som en egen metode, siden du gjør nesten det samme for alle retningene).
    * Den må spørre `game` om det er lov å gå i den aktuelle retningen, og i såfall kalle `game.move()` for å flytte i den gitte retningen. Du kan se hvordan `Rabbit` løser dette – hos Player slipper du heldigvis å tenke på hvor det er *lurt* å flytte, siden brukeren alt har bestemt det.
    * Hvis det ikke er lov å flytte (det er en vegg der – eller en kanin), kan du f.eks. gi en tilbakemelding til brukeren med `game.displayMessage("Ouch!")`. Hvis du er ekstra streng, kan du også la spilleren tape litt helse på å stange hodet i veggen.

* **e)** Brukeren trenger sikkert også litt statusinformasjon, f.eks. om antall helsepoeng. Lag en metode `showStatus(game)` i `Player`-klassen din. Den kan f.eks. bruke `game.displayStatus("...")` eller `game.formatStatus("... %d ...", verdi)` (hvis du er komfortabel med `printf`-liknende strengformattering). Begge disse printer en linje med tekst på skjermen (Main-klassen holder rede på de passende linjene, status havner på linjen `Main.LINE_STATUS` som er 21 (rett under kartet hvis kartet er 20 linjer høyt)). Kall `showStatus()` på slutten av `keyPressed()` (hvis du senere lager andre metoder som kan endre tilstanden til spilleren, bør du kalle `showStatus()` på slutten av disse også).

### Deloppgave B3: Sortering av items

Vi er ikke så veldig lure med hvordan vi samler en mengde IItems i en List i kart-cellene. F.eks. blir grafikken antakelig veldig rotete om vi prøver å tegne opp mer én ting i hver rute – så vi trenger gjerne en bedre måte å bestemme rekkefølgen de ligger i listen på (eller evt. hvordan vi velger hvilken ting vi skal tegne). I den utleverte koden tegner vi alltid bare den første tingen i hver celle, uavhengig av om den er interessant eller ikke. Du har kanskje lagt merke til at kaninene “forsvinner” når de står på et felt med en gulrot (hvis ikke, kan du prøve å flytte spilleren til en gulrot og se hva som skjer).

En grei løsning er å si at vi vil se den *største* tingen – alle IItems har en `getSize()` metode som forteller hvor “stor” den er (uten at vi har brukt dette til noe hittil). IItems er også `Comparable` – det følger med en `default` `compareTo()`-metode i `IItem` som sammenlikner størrelse (`getSize()`).

For å fikse dette vil vi at kartet skal ha tingene liggende i sortert rekkefølge i listene for hver celle. Vi kunne sortert listen hver gang vi hentet den (f.eks. hver gang kaninen kaller `game.getLocalItems()`, slik at den største gulroten kommer først), men det er både raskere og ca. like lettvint å passe på at vi legger ting inn i sortert rekkefølge: Hvis vi skal legge et element *e* inn i listen *l*, så vil vi ha det på den første posisjonen *i* slik at *e.compareTo(l.get(i)) >= 0* (altså, sett inn *e* foran det første elementet *e* er større enn).

* **a)** Legg til alternativet for `"."` i `Game.createItem()` – den skal produsere et `Dust`-objekt.
    * Hvis du kjører programmet nå, vil du antakelig se at mange av kaninenen “gjemmer seg” – de ligger altså under støvet (Dust-objektene; sees som litt mørkere felter).
* **b)** Endre `add`-metoden i `GameMap` slik at nye items blir lagt til i sortert rekkefølge (største først).
    * Husk at `a.compareTo(b)` er `< 0` hvis `a` er mindre `b`, `== 0` hvis `a` er lik `b` og `> 0` hvis `a` er større `b`.
    * Du kan legge element foran det nåværende elementet på posisjon `i` med `list.add(i, e)` (det er også OK om `i == list.size()`, da legger du til et nytt element på slutten av listen)
    * Det holder å kalle `add` på listen, siden `list` refererer til samme objektet som ligger inne i kart-cellen.
    * `Dust`-objektene er ganske små (størrelse 1), så hvis du har gjort det riktig, vil kaninene (og spilleren) bli tegnet i stedet for støvet.
* **c)** Test `add`-metoden.
   * Finn testklassen [GameMapTest](TODO) og testmetoden vi har begynt på (`testSortedAdd`).
   * Du må sette opp et test-scenario:
      * du trenger et nytt GameMap (det trenger ikke være fylt med noe)
      * du trenger også en ILocation, siden de fleste kart-metodene bruker det (kall `getLocation(x,y)` med en kjent, lovlig posisjon)
      * så må du opprette noen IItems og legge dem til på kartposisjonen ved å kalle `GameMap.add()`
   * Til slutt kan du teste at tingene ligger i den rekkefølgen du forventer (`GameMap.getAll()`, og sjekk listeelementene med `get()`)
   * *(Litt mer avansert:)* Hvis du vil teste med litt større mengder data er det gjerne upraktisk å sjekke nøyaktig hvordan elementene er plassert – da kan du i stedet gå gjennom listen og sjekke at `list.get(i).compareTo(list.get(i+1)) >= 0` for `0 <= i < list.size()-1`. Et godt utvalg data kan du få ved å lage deg en metode som lager tilfeldige IItems – den kan uansett være nyttig for å lage tilfeldige kart og slikt. (Du skal lære dette mer grundig i neste lab-oppgave.)
   
### Deloppgave B4: Plukk opp / dropp ting

Vi vil gjerne la brukeren kunne plukke opp og legge fra seg ting på kartet – halvspiste gulrøtter, for eksempel. 

* **a)** For å få til dette må du legge til flere tastetrykk til `keyPressed`-metoden i `Player` – du kan f.eks. bruke `KeyCode.P` og `KeyCode.D`. Lag gjerne egne metoder for å plukke opp/droppe ting i spilleren.
   * *Legg merke til:* andre metoder i `Player` er laget slik at de tar et `IGame`-argument. Det trenger du nok her også fordi du må snakke med spill-objektet for å interagere med kartet og andre ting. Du *kan* lagre `game` som en feltvariabel, så du slipper å sende den rundt som argument. Vi har latt være å gjøre det fordi: a) det er lettere å opprette og teste Player (og andre IItems/IActors) hvis man ikke trenger å lage gi dem et game-objekt (f.eks. kunne du teste GameMap/sortert add uten å bruke `Game`/`IGame` i det hele tatt); og b) det kan være litt forvirrende med sykliske avhengigheter mellom objekter (ikke minst når forholdet endrer seg – f.eks. når ting fjernes fra spillet).
* **b)** `Game` har tilsvarende `pickUp()` og `drop()` metoder du kan bruke for å plukke et objekt fra kartet og for å etterlate et objekt på kartet. Metoden `pickUp()` returnerer `null` om du av en eller annen grunn ikke kunne plukke opp tingen. Implementer “plukke opp”-funksjonaliteten. Hint: 
    * `game.pickUp()` plukker opp et spesifikt objekt som ligger i kartruten du står i, så du er nødt til å finne ut *hvilket objekt* du vil prøve å plukke opp hvis det er flere mulighetre (foreløpig har brukeren ingen måte å be om et spesifikt IItem på); 
    * du finner alle tingene som ligger i kartruten med `game.getLocalItems()`, og du kan f.eks. prøve å ta den første. Du kan også finne tingene ved å få tak i kartet (game.getMap()) og undersøke det direkte – bare pass på at spilleren ikke ender opp med å plukke opp seg selv! (getLocalItems() gir deg bare items som ikke er IActor, så den er “trygg”)
    * husk at ruten kan være tom, dvs at `game.getLocalItems()` gir tom liste – da kan du f.eks. gi bruken beskjed om at det ikke er noe å plukke opp.
* **c)** For å kunne legge fra deg ting må du ha lagret objektet du plukket opp, f.eks. i en feltvariabel i `Player`. Foreløpig går det greit å tenke seg at du bare kan holde på én ting, så da er det lett å vite hva dus skal legge fra deg. Implementer “dropp / legg fra deg”-funksjonaliteten. Hint:
   * Du kan gi en passende melding til brukeren om du ikke har noe å legge fra deg.
   * Pass på at når du har lagt fra deg objektet (med `game.drop()`) så sletter du det fra `Player`-objektet – ellers kan du massekopiere ting ved å plukke opp én ting og så legge den fra deg mange ganger.
   * `game.drop()` kan i prinsippet feile (fullt på bakken, kanskje?), i såfall returnerer den `false` og du bør *ikke* slette tingen fra `Player`-objektet
* **d)** Det er praktisk for brukeren å vite hva man bærer på, så legg inn navnet på objektet i status-meldingen (fra B2.e)  

###
###
