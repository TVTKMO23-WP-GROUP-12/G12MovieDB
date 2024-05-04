# Group 12 Movie Database

## Sisällysluettelo
- [Jäsenet](#jasenet)
- [Esittely](esittely)
- [Teknologia](teknologia)
	- [Backend](#backend)
	- [Frontend](#frontend)
	- [Tietokanta](#tietokanta) 
	- [TMDB API](#tmdb_api) 
- [Sovelluksen rakenne](#sovellus)
- [Lisenssi](#lisenssi)
- [Lähteet](#lähteet)

## <a name ="jasenet"></a> Ryhmän jäsenet
- Pasi Honka - [Rapuska](https://www.github.com/Rapuska)
- Henri Laukka - [MacStache](https://www.github.com/MacStache)
- Lari Lepistö - [LariLepisto](https://www.github.com/LariLepisto)
- Sanna Mäki - [Rahukettu](https://www.github.com/rahukettu)

Ryhmän jäsenet ovat OAMKin toisen vuoden opiskelijoita.

## <a name ="esittely"></a> Esittely
Group 12 Movie Database on Oulun ammattikorkeakoulun toisen vuosikurssin opiskelijoiden projektina tehty web-sovellus, jonka välityksellä elokuvista pitävät voivat keskustella keskenään, arvioida elokuvia sekä tehdä listoja elokuvista eri kategorioiden perusteella.

Web-sovellus toimii sosiaalisen median sivustojen kaltaisella periaatteella, eli käyttäjät voivat seurata toisiaan, lähettää toisilleen viestejä sekä liittyä erilaisiin ryhmiin, joissa voi keskustella esimerkiksi samankaltaisista elokuvista pitävien käyttäjien kanssa. Ryhmien muodolla ja tarkoituksella ei ole rajoitetta, joten käyttäjä voi itse perustaa minkälaisen ryhmän tahansa.

Käyttäjät voivat rekisteröityä palveluun, kirjautua omalle tililleen ja kirjautua tililtä ulos. Julkiset (ei-autentikoidut) käyttäjät pääsevät projektin julkisille sivuille. Autentikoidut käyttäjät pääsevät kaikille sivuille ja voivat lisäksi muodostaa uusia ryhmiä, julkaista arvosteluja, muokata kirjoittamiaan arvosteluja sekä poistaa kirjoittamansa arvostelut ja luomansa ryhmät.

## <a name ="teknologia"></a>Teknologia

Projektin teknologia pitää sisällään useita eri osa-alueita. Teknologia rakentuu backendin ja frontendin ympärille ja ne muodostavat yhdessä toimivan kokonaisuuden.

### <a name ="backend"></a> Backend 
Backend sisältää sovelluksen kaikki palvelimella suoritettavat osa-alueet, kuten yhteydet tietokantaan, tiedostojen, lomakkeiden ja hakujen käsittelyt, sovellukseen kirjautuminen sekä käyttäjän autentikointi. Backend vastaa käyttöliittymästä tuleviin pyyntöihin ja käsittelee, siirtää ja vastaanottaa sovelluksen tietoja.

Sovelluksen backend-toiminnot on rakennettu käyttämällä Java-kielellä toimivaa [Spring Boot](https://spring.io/projects/spring-boot) -sovelluskehystä. Spring Boot on avoimen lähdekoodin Java-projektikirjasto, joka on tarkoitettu esimerkiksi tämän projektin tyylisten mikropalveluiden kehittämiseen. Se on hyvin mukautuvainen ja helppokäyttöinen kehys, jonka avulla on helppo luoda itsenäisiä, tuotantotasoisia Springiin perustuvia sovelluksia. Spring Boot tarjoaa monenlaisia mahdollisuuksia. Yksi sen ydinkonsepteista on riippuvuusinjektio, mikä tarkoittaa, että niin kauan kuin koodissa käytetään oikeanlaisia riippuvuuskuvauksia, Spring huolehtii sovelluksen eri komponenttien alustamisesta.

#### Tämän projektin backendin käyttämien riippuvuuksien versiot:
- **Java:** *17*
- **Spring Boot:** *3.2.4*
- **okhttp3:** *4.12.0*
- **Spring Security:** *6.2.4*

### Backendin rakenne
Backend pohjautuu model-controller-repositorio-malliin, joka on yleinen Spring Boot -sovelluksissa. Ohjauskerros (controller) on yksin vastuussa toiminnallisuuksien ylläpidosta ja muut kerrokset käyttävät sitä toimiakseen yhdessä. Tietovarastokerros (repository) on vastuussa tietojen tallentamisesta ja hakemisesta. Tietotaso (model) pitää sisällään yksityiskohtaiset tiedot. Jos toimintalogiikka vaatii tietojen hakemista tai tallentamista, se kytkeytyy tietovarastoon. 

Näiden peruselementtien lisäksi backendissä on palvelun konfiguraatiolle sekä palvelussa käytössä olevalle APIlle omat osiot. 

### Käyttäjän autentikointi
Käyttäjän autentikoinnissa käytetään Spring Bootin autentikointiin ja kulunvalvontaan tarkoitettua Spring Securityä, jota on kustomoitu sovelluksen tarpeisiin. Autentikoinnissa käytetään ns. tilatonta istuntoa, eli käyttäjälle tehdään autentikoinnin yhteydessä ainutkertainen, yksilöllinen istuntotunnus, joka kryptataan ja hävitetään uloskirjautumisen yhteydessä. Istuntotunnukselle on myös määrätty maksimivoimassaoloaika. Käyttäjätiedot on keskitetty palvelinpuolelle ja palvelun jokaisen osan, joka vaatii jonkinlaista tietoa käyttäjästä, täytyy ottaa erikseen yhteyttä palvelimeen. Tämä tekee palvelusta tietoturvallisemman. Käyttäjätietoja ei voi väärentää, ja yksilöllisestä, läpinäkymättömästä istuntotunnuksesta ei voi saada tietoja, toisin kuin esimerkiksi jatkuvasti toiminnassa olevasta salausavaimesta.

### <a name ="frontend"></a> Frontend
Sivuston frontendin - eli selainpuolen - koodina käytetään [React JavaScript](https://react.dev/) -kirjastoa. Kirjasto on Metan (Facebook) alunperin julkaisema avoimen lähdekoodin kirjasto, joka on tarkoitettu verkkosivustojen käyttöliittymien kehittämiseen, mutta sitä voi käyttää nykyisin myös mobiili- sekä työpöytäsovellusten kehittämiseen.

#### Tämän projektin frontend käyttämien riippuvuuksien versiot:
- **React:** *18.2.0*
- **Axios:** *1.6.8*

### <a name ="tietokanta"></a> Tietokanta

Tietokantana toimii avoimen lähdekoodin [PostgreSQL](https://www.postgresql.org/) -tietokantapalvelin. Yhteisölähtöisiä, avoimen lähdekoodin tietokantapalvelimia ovat myös esimerkiksi MariaDB ja Percona, jotka ovat MySQL:n haaroja. Suurimpana erona Postgresin ja MySQL:n välillä on se, että Postgres on objektirelaatiotietokanta, kun taas MySQL on puhtaasti relaatiotietokanta. Tämä tarkoittaa, että Postgresissa olioita, luokkia ja periytymistä tuetaan suoraan tietokantaskeemoissa ja kyselykielessä.

Postgres on myös erittäin laajennettavissa, sillä se tukee useita kehittyneitä tietotyyppejä, joita ei ole saatavilla MySQL:ssä. Näitä ovat esimerkiksi geometrinen/GIS, verkko-osoitetyypit, JSONB, joka voidaan indeksoida, natiivi UUID sekä aikavyöhyketietoiset aikaleimat. Postgresiin voi lisätä myös omia tietotyyppejä, operaattoreita ja indeksityyppejä. Postgresia pidetään joissakin tapauksissa hitaana sen kompleksisuuden vuoksi.

Projektin tietokantaa ajetaan [Render](https://render.com/) -palvelussa. Kuvassa 1 on esitetty tietokannan ER-kaavio.

![KUVA 1. Tietokannan ER-kaavio (entity-relationship model).](https://github.com/TVTKMO23-WP-GROUP-12/G12MovieDB/blob/main/documents/G12MovieDB%20ER%20Model.png?raw=true)
*KUVA 1. Tietokannan ER-kaavio (entity-relationship model).*

#### Tässä projektissa käytetyn tietokantasovelluksen versio:
- **PostgreSQL**: *16*

### <a name ="tmdb_api"></a> TMDB API
Sovellus hyödyntää The Movie Databasen (TMDB) palveluita ohjelmistorajapinnan (application programming interface, API) avulla. TMDB tarjoaa sovelluskehittäjille ja muille käyttäjille API-avaimen, joka voidaan integroida omaan sovellukseen tietyin TMDB:n määrittelemin ehdoin.

TMDB on elokuvien ja tv-sarjojen tietokanta, jota on rakennettu sen käyttäjien toimesta jo vuodesta 2008 alkaen. TMDB:n tietosisältö on hyvin laaja ja kansainvälisesti kohdistettu, mikä tekee siitä lähes verrattoman muihin vastaaviin palveluihin nähden. Palvelua käytetään päivittäin jopa yli 180 maassa. TMDB:n vahva yhteisöllisyys tekee siitä erilaisen verrattaessa muihin vastaaviin palveluihin. (The Movie Database, 2024.)

API-rajapinta mahdollistaa eri ohjelmistojen tai palveluiden välistä kommunikaatiota ja yhteistoimintaa. Se määrittelee, miten eri ohjelmistokomponentit voivat kommunikoida keskenään ja millaisia toimintoja ne voivat suorittaa. API:n avulla kehittäjät voivat käyttää valmiita toimintoja tai palveluita osana omaa ohjelmointityötään. Ohjelmistorajapintojen juuret juontavat 1940-1950-luvulle, kun Maurice Wilkes, David Wheeler ja Stanley Gill kehittivät ohjelmistokirjastoja ja algoritmisia ratkaisuja ensimmäisten tietokoneiden yhteyteen. Näitä varhaisia kirjastoja voidaan pitää ensimmäisinä API:n kaltaisina käytänteinä. (Kristopher Sandoval, 2018.)

TMDB:n rajapinnasta haetaan sovellukseen muun muassa elokuvien ja tv-sarjojen tietoja, joita käyttäjät voivat hyödyntää sovelluksen eri osioissa esimerkiksi lisäämällä niitä suosikkilistalleen sekä katsottuihin ja katsottaviin. Esimerkiksi sovelluksen hakuportaali-sivu hakee rajapinnasta elokuvan ja tv-sarjan nimen, julisteen, tyylilajin, kielen, julkaisuajan sekä arvostelun. Osaa tiedoista käytetään hakusuodattimina ja osa näytetään hakutuloksissa.

## <a name ="sovellus"></a> Sovelluksen rakenne

Web-sovellus koostuu kotisivusta (kuva 2) sekä useista sen alasivuista. Sovelluksen eri osiot ovat näkyvissä riippuen siitä, että onko käyttäjä kirjautunut vai ei. Lisäksi sovellus on responsiivinen, eli se skaalautuu tietokoneen näytöltä erikokoisille kädessä pidettäville laitteille.

![KUVA 2. Kotisivu.](https://github.com/TVTKMO23-WP-GROUP-12/G12MovieDB/blob/main/documents/Homepage.png?raw=true)
*KUVA 2. Kotisivu.*


### Julkiset sivut:

Julkiset sivut ovat avoimia kaikille sovelluksen käyttäjille. Osa julkisista sivuista pitävät kuitenkin sisällään ominaisuuksia, jotka ovat käytettävissä ainoastaan kirjautuneille käyttäjille.

#### Näytösajat

Näytösajat-sivu (kuva 3) tarjoaa käyttäjille mahdollisuuden tutustua Finnkinon elokuvaohjelmistoon ja näytösaikoihin. Sivulla voi selata elokuvia, näytösaikoja ja teattereita eri paikkakunnilla. Lisäksi käyttäjä voi halutessaan selailla elokuvia valitsemansa tyylilajin perusteella. Projektissamme olemme hyödyntäneet Finnkinon XML-pohjaista rajapintaa, joka mahdollistaa reaaliaikaisten tietojen hakemisen Finnkinon palvelusta. Käytämme tähän JavaScriptiä yhdessä React-kirjaston kanssa.

XML-tietojen käsittelyyn ja parsimiseen käytämme selaimen `DOMParser`-objektia, joka mahdollistaa XML-dokumenttien tehokkaan käsittelyn ja datan uuttamisen tarpeidemme mukaisesti. Tämä toiminnallisuus tarjoaa käyttäjillemme pääsyn ajankohtaisiin tietoihin elokuvanäytöksistä, teatterien sijainneista ja elokuvien genreistä suoraan Finnkinon tietokannoista (Finnkino Oy, 2024). Tämä parantaa merkittävästi sovelluksemme käyttäjäkokemusta.

![KUVA 3. Näytösajat-sivu.](https://github.com/TVTKMO23-WP-GROUP-12/G12MovieDB/blob/main/documents/Showtimes%20page.png?raw=true)
*KUVA 3. Näytösajat-sivu.*

#### Hakuportaali

Hakuportaali-sivulla (kuva 4) käyttäjät voivat etsiä eri elokuvia ja TV-sarjoja. Käyttäjät voivat suodattaa hakutuloksia arvosanan, genrejen tai julkaisuvuoden perusteella löytääkseen kiinnostavaa sisältöä.

![KUVA 4. Hakuportaali-sivu.](https://github.com/TVTKMO23-WP-GROUP-12/G12MovieDB/blob/main/documents/Searchportal%20page.png?raw=true)
*KUVA 4. Hakuportaali-sivu.*

#### Elokuva-sivu (uloskirjautunut käyttäjä)

Kaikki käyttäjät voivat selata elokuvien ja TV-sarjojen tietoja, kuten lajityyppiä, julkaisuaikaa, kuvausta sekä TMDB:n käyttäjien antamia arvosanoja. Myös sovelluksemme käyttäjien antamat arvostelut sekä elokuvien ja TV-sarjojen näyttelijätiedot ovat kaikille julkisia.

#### Sisään kirjautuminen

Kirjautumissivulla käyttäjät voivat kirjautua sisään antamalla käyttäjätunnuksensa ja salasanansa. Tämä sivu on tarkoitettu rekisteröityneille käyttäjille, joiden avulla he pääsevät käsiksi laajempiin palveluihin.

#### Rekisteröityminen

Rekisteröitymissivulla uudet käyttäjät voivat luoda käyttäjätilin täyttämällä vaadittavat tiedot, kuten käyttäjänimi, sähköpostiosoite ja salasana. Rekisteröityminen mahdollistaa täyden pääsyn sovelluksen toiminnallisuuksiin.

#### Ryhmäsivu (uloskirjautunut käyttäjä)

Käyttäjät näkevät sovelluksen kotisivulla listan ryhmistä, jossa on lueteltuna ryhmien nimet ja kuvaukset. Rekisteröitymätön ja uloskirjautunut käyttäjä ei kuitenkaan näe ryhmän toimintaa, eikä muuta sivulle lisättyä sisältöä. Kirjautunut käyttäjä voi liittyä ryhmään ”liity ryhmään” painikkeesta.


### Kirjautumisen takana olevat sivut:

Rekisteröityminen ja kirjautuminen sovellukseen avaa käyttäjälle tiettyjä lisäominaisuuksia. Kirjautuneelle käyttäjälle avautuu myös erilaisia sosiaalisia kanavia ja muokkausmahdollisuuksia.

#### Elokuvasivu (kirjautunut käyttäjä)

Kaikille avointen ominaisuuksien lisäksi kirjautuneelle käyttäjälle avautuu muutamia lisäominaisuuksia elokuvasivulle. Kirjautunut käyttäjä voi lisätä elokuvia ja TV-sarjoja omiksi suosikeikseen sekä katsottujen listalle ja katsottavien listalle. Kirjautunut käyttäjä voi myös kirjoittaa omia arvosteluitaan elokuvan ja TV-sarjan yhteyteen. Kuvassa 5 näkyy kaikki kirjautuneelle käyttäjälle avoimet ominaisuudet.

![KUVA 5. Elokuvasivu (kirjautunut käyttäjä).](https://github.com/TVTKMO23-WP-GROUP-12/G12MovieDB/blob/main/documents/Movie%20page.png?raw=true)
*KUVA 5. Elokuvasivu (kirjautunut käyttäjä)*.

#### Ryhmäsivu (kirjautunut käyttäjä)

Ryhmäsivulla (kuva 6) kirjautunut käyttäjä näkee koko ryhmän oman näkymän. Käyttöliittymässä on helppokäyttöiset välilehdet, joiden avulla voi selata ryhmän uutisia, jäsenlistoja, jaettua sisältöä, elokuvasuosituksia, ryhmäläisten elokuva-arvosteluja sekä tietoja Finnkinon elokuvanäytöksistä. Käyttäjä voi jakaa oman suosikkilistansa ryhmäsivulle, jolloin se tulee näkyviin ryhmän jaetut-osioon.

![KUVA 6. Ryhmäsivu (kirjautunut käyttäjä).](https://github.com/TVTKMO23-WP-GROUP-12/G12MovieDB/blob/main/documents/Group%20page.png?raw=true)
*KUVA 6. Ryhmäsivu (kirjautunut käyttäjä).*

#### Oma sivu

Rekisteröitynyt käyttäjä saa oman sivun, jonne hän voi lisätä eri sisältöä. Oman sivun välilehdillä käyttäjälle näkyy uutiset, omat ryhmät ja omat arvostelut. Näiden lisäksi käyttäjälle näkyy myös omat suosikit sekä omat katsotut ja katsottavat elokuvat ja TV-sarjat. Käyttäjä voi muokata omaa profiiliaan haluamakseen vaihtamalla profiilikuvan ja luomalla kuvauksen.

## <a name ="lisenssi"></a> Lisenssi

Lisenssinä käytämme MIT-lisenssiä, jolla käyttäjä saa vapaat oikeudet muokata, kopioida ja käyttää teosta omassa projektissaan. Ehtona käytölle on, että lisenssin teksti säilyy lähdekoodissa. Lisenssi on kirjoitettu englanniksi, jotta kansainvälisetkään käyttäjät eivät tee virhetulkintoja sen sisällössä.

```
Copyright (c) 2024 Pasi Honka, Henri Laukka, Lari Lepistö & Sanna Mäki

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:
 
The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.
 
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

## <a name ="lähteet"></a> Lähteet
Finnkino Oy 2024. XML Services. Hakupäivä 27.4.2024. https://www.finnkino.fi/xml.

Sandoval, Kristopher 2018. Who Invented the API?. Hakupäivä 23.4.2024. https://nordicapis.com/who-invented-the-api/.

The Movie Database (TMDB) 2024. About. Hakupäivä 23.4.2024. https://www.themoviedb.org/about.
