# [Semesteroppgave 1: “Rogue One oh one”](https://retting.ii.uib.no/inf101.v18.sem1/blob/master/SEM-1_DEL-C.md) Del A: Fri utfoldelse

* [Oversikt](SEM-1.md)
* [Praktisk informasjon](SEM-1.md#praktisk-informasjon)
* [Del A: Bakgrunn, modellering og utforskning](SEM-1_DEL-A.md)
* [Del B: Gjør ferdig nødvendige komponenter](SEM-1_DEL-B.md)
* [Del C: Selvvalgt del](SEM-1_DEL-C.md)

## Videreutvikling av Rogue-101

Vi overlater nå ansvaret for utviklingen til deg – du finner noen forslag under, men du må selv bestemme hva mer som skal legges til av funksjonalitetet, og hva du evt. vil gjøre for å gi spillet litt mer “flavour” (grafikk, kanskje?).

Som nevnt tidligere kan du velge “setting” og “storyline” som du vil – du trenger ikke å lage huleutforskning med magi, sverd, orker og hobbiter – eller med kaniner og gulrøtter. Skriv ned forklaring til det du gjør i README.md – både funksjonalitet som du legger til, og kreative ting du finner på – vi legger ikke nødvendigvis merke til alt når vi prøvekjører ting, så det er greit å vite hva vi skal se etter.


### Styling

Du kan kommet et godt stykke på vei med litt kreativ bruk av tekst-symbolene. For enkel blokk-grafikk (til vegger, f.eks.) så finnes det en del forskjellige tegn du kan bruke i [BlocksAndBoxes](inf101/v18/gfx/textmode/BlocksAndBoxes.java). 

F.eks. bruker `Wall`-objektene `BlocksAndBoxes.BLOCK_FULL` som symbol. Det kan være litt tricky å få `Wall` til å variere `getSymbol()` avhengig av hvor den har andre vegger som naboer, men du kan i prinsippet lage flere varianter av wall:
   * Her er det mest praktisk å bruk *arv*, f.eks.:
   
```
public class DiagonalWall extends Wall {
    // Java vil bruke denne i stedet for getSymbol() fra Wall – men alle andre metoder følger
    // med fra Wall.
	@Override
	public String getSymbol() {  
		return BlocksAndBoxes.DIAG_FORWARD;
	}
}
```

   * Du trenger også å legge til alle vegg-variantene i `createItem`-metoden.
   * Du trenger at alle vegger har `Wall`-typen, fordi kartet bruker det til å se om et felt er opptatt (bla gjennom items på en lokasjon, sjekk om `item instanceof Wall`). Når du sier at `DiagonalWall extends Wall`, så vi alle diagonale vegger også telle som vanlige vekker.
   * Alternativet ville vært å enten legge til en metode `isWall()` i `IItem`; eller si at en kartcelle er opptatt hvis det er en veldig stor ting der (`getSize()` større enn 100 eller 1000, f.eks.); eller lage et ekstra grensesnitt `IWall` (og både `Wall` og `DiagonalWall` `implements IWall`) og bruke det istedenfor der hvor man trenger å sjekke etter vegger. 
   

* lage meldings"vindu"

# Diverse

## Åpne kilder til grafikk / lyd / media

* Om du ikke er flink til å tegne selv, kan du finne glimrende grafikk på [OpenGameArt](http://opengameart.org/) – **husk å skrive i oversiktsdokumentet hvor du har fått grafikken fra** (webside, opphavsperson, copyright-lisens – om du bruker OpenGameArt, finner du opplysningene i *License(s)* og *Copyright/Attribution Notice*).
* [Wikimedia Commons](https://commons.wikimedia.org/wiki/Main_Page) har en god del bilder og andre mediafiler tilgjengelig – du får til og med en “You need to attribute this author – show me how” instruks når du laster ned ting.
* [Kevin MacLeod](https://incompetech.com/music/) er komponist og har masse musikk tilgjengelig som egner seg for spill og småfilmer; du trenger bare å kreditere ham (han bruker vanlig [CC-BY-3.0](http://creativecommons.org/licenses/by/3.0/) lisens).
* Tidligere INF101-student [Øyvind Aandalen](https://soundcloud.com/user-616269685) har litt musikk han har laget på [SoundCloud](https://soundcloud.com/user-616269685) som han sier dere kan bruke som dere vil.



