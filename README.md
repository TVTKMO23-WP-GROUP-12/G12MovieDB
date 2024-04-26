# Group 12 Movie Database

## Sisällysluettelo
- [Jäsenet](#jasenet)
- [Esittely](esittely)
- [Teknologia](teknologia)
	- [Backend](#backend)
	- [Frontend](#frontend)
	- [Tietokanta](#tietokanta) 
	- [TMDB API](#tmdb_api) 
- [Sivusto](#sivusto)
- [Lähteet](#lähteet)

## <a name ="jasenet"></a> Ryhmän jäsenet
- Pasi Honka - [Rapuska](https://www.github.com/Rapuska)
- Henri Laukka - [MacStache](https://www.github.com/MacStache)
- Lari Lepistö - [LariLepisto](https://www.github.com/LariLepisto)
- Sanna Mäki - [Rahukettu](https://www.github.com/rahukettu)

Ryhmän jäsenet ovat OAMKin toisen vuoden opiskelijoita.

## <a name ="esittely"></a> Esittely
Group 12 Movie Database on Oulun Ammattikorkeakoulun toisen vuosikurssin opiskelijoiden projektina tehty websovellus, jonka välityksellä elokuvista pitävät voivat keskustella keskenään, arvioida elokuvia sekä tehdä listoja elokuvista eri kategorioiden perusteella. 

Sivusto toimii sosiaalisen median sivustojen kaltaisella periaatteella, eli käyttäjät voivat seurata toisiaan, lähettää toisilleen viestejä sekä liittyä erilaisiin ryhmiin, joissa voi keskustella esimerkiksi samankaltaisista elokuvista pitävien käyttäjien kanssa. Ryhmien muodolla ja tarkoituksella ei ole rajoitetta, joten käyttäjä voi itse perustaa minkälaisen ryhmän tahansa. 

Käyttäjät voivat rekisteröityä palveluun, kirjautua omalle tililleen ja kirjautua tililtä ulos. Julkiset (ei-autentikoidut) käyttäjät pääsevät projektin julkisille sivuille. Autentikoidut käyttäjät pääsevät kaikille sivuille ja voivat lisäksi muodostaa uusia ryhmiä, julkaista arvosteluja, muokata kirjoittamiaan arvosteluja sekä poistaa kirjoittamansa arvostelut ja luomansa ryhmät.

Seuraavissa kappaleissa käydään läpi sivuston teknologisempaa puolta [backendistä](#backend), [frontendiin](#frontend), [tietokantaan](#tietokanta) ja [TMDB:n ohjelmistorajapintaan](#tmdb_api).

## <a name ="teknologia"></a>Teknologia

### <a name ="backend"></a> Backend 
Backend sisältää sovelluksen kaikki palvelimella suoritettavat osa-alueet, kuten yhteydet tietokantaan, tiedostojen, lomakkeiden ja hakujen käsittelyt, sovellukseen kirjautuminen sekä käyttäjän autentikointi. Backend vastaa käyttöliittymästä tuleviin pyyntöihin ja käsittelee, siirtää ja vastaanottaa sovelluksen tietoja. 

Sovelluksen backend-toiminnot on rakennettu käyttämällä Java-kielellä toimivaa [Spring Boot](https://spring.io/projects/spring-boot) -sovelluskehystä. Spring Boot on avoimen lähdekoodin Java-projektikirjasto, joka on tarkoitettu esimerkiksi tämän projektin tyylisten mikropalveluiden kehittämiseen. Se on hyvin mukautuvainen ja helppokäyttöinen kehys, jonka avulla on helppo luoda itsenäisiä, tuotantotasoisia Springiin perustuvia sovelluksia. Spring Boot tarjoaa monenlaisia mahdollisuuksia. Yksi sen ydinkonsepteista on riippuvuusinjektio, mikä tarkoittaa, että niin kauan kuin koodissa käytetään oikeanlaisia riippuvuuskuvauksia, Spring huolehtii sovelluksen eri komponenttien alustamisesta.

#### Tämän projektin backendin käyttämien riippuvuuksien versiot:
- **Java:** *17*
- **Spring Boot:** *3.2.4*
- **okhttp3:** *4.12.0*
- **Spring Security:** *6.2.4*

### Käyttäjän autentikointi
Käyttäjän autentikoinnissa käytetään Spring Bootin autentikointiin ja kulunvalvontaan tarkoitettua Spring Securityä, jota on kustomoitu sovelluksen tarpeisiin. Autentikoinnissa käytetään ns. tilatonta istuntoa, eli käyttäjälle tehdään autentikoinnin yhteydessä ainutkertainen, yksilöllinen istuntotunnus, joka kryptataan ja hävitetään uloskirjautumisen yhteydessä. Istuntotunnukselle on myös määrätty maksimivoimassaoloaika. Käyttäjätiedot on keskitetty palvelinpuolelle ja palvelun jokaisen osan, joka vaatii jonkinlaista tietoa käyttäjästä, täytyy ottaa erikseen yhteyttä palvelimeen. Tämä tekee palvelusta tietoturvallisemman. Käyttäjätietoja ei voi väärentää, ja yksilöllisestä, läpinäkymättömästä istuntotunnuksesta ei voi saada tietoja, toisin kuin esimerkiksi jatkuvasti toiminnassa olevasta salausavaimesta.

### Backendin rakenne
Backend pohjautuu model-controller-repositorio-malliin, joka on yleinen Spring Boot -sovelluksissa. Ohjauskerros (controller) on yksin vastuussa toiminnallisuuksien ylläpidosta ja muut kerrokset käyttävät sitä toimiakseen yhdessä. Tietovarastokerros (repository) on vastuussa tietojen tallentamisesta ja hakemisesta. Tietotaso (model) pitää sisällään yksityiskohtaiset tiedot. Jos toimintalogiikka vaatii tietojen hakemista tai tallentamista, se kytkeytyy tietovarastoon. 

Näiden peruselementtien lisäksi backendissä on palvelun konfiguraatiolle sekä palvelussa käytössä olevalle APIlle omat osiot. 

### <a name ="frontend"></a> Frontend
Sivuston frontendin - eli selainpuolen - koodina käytetään [React JavaScript](https://react.dev/) -kirjastoa. Kirjasto on Metan (Facebook) alunperin julkaisema avoimen lähdekoodin kirjasto, joka on tarkoitettu verkkosivustojen käyttöliittymien kehittämiseen, mutta sitä voi käyttää nykyisin myös mobiili- sekä työpöytäsovellusten kehittämiseen.

#### Tämän projektin frontend käyttämien riippuvuuksien versiot:
- **React:** *18.2.0*
- **Axios:** *1.6.8*

### <a name ="tietokanta"></a> Tietokanta

Tietokantana toimii avoimen lähdekoodin [PostgreSQL](https://www.postgresql.org/) -tietokantapalvelin. Yhteisölähtöisiä, avoimen lähdekoodin tietokantapalvelimia ovat myös esimerkiksi MariDB ja Percona, jotka ovat MySQL:n haaroja. Suurimpana erona Postgresin ja MySQL:n välillä on se, että Postgres on objektirelaatiotietokanta, kun taas MySQL on puhtaasti relaatiotietokanta. Tämä tarkoittaa, että Postgresissa olioita, luokkia ja periytymistä tuetaan suoraan tietokantaskeemoissa ja kyselykielessä. 

Postgres on myös erittäin laajennettavissa, sillä se tukee useita kehittyneitä tietotyyppejä, joita ei ole saatavilla MySQL:ssä. Näitä ovat esimerkiksi geometrinen/GIS, verkko-osoitetyypit, JSONB, joka voidaan indeksoida, natiivi UUID sekä aikavyöhyketietoiset aikaleimat. Postgresiin voi lisätä myös omia tietotyyppejä, operaattoreita ja indeksityyppejä. Postgresia pidetään joissakin tapauksissa hitaana sen kompleksisuuden vuoksi.

Projektin tietokantaa ajetaan [Render](https://render.com/) -palvelussa.

#### Tässä projektissa käytetyn tietokantasovelluksen versio:
- **PostgreSQL**: *16*

### <a name ="tmdb_api"></a> TMDB API
Sivusto hyödyntää The Movie Databasen (TMDB) palveluita ohjelmistorajapinnan (application programming interface, API) avulla. TMDB tarjoaa sovelluskehittäjille ja muille käyttäjille API-avaimen, joka voidaan integroida omaan sovellukseen tietyin TMDB:n määrittelemin ehdoin.

TMDB on elokuvien ja tv-sarjojen tietokanta, jota on rakennettu sen käyttäjien toimesta jo vuodesta 2008 alkaen. TMDB:n tietosisältö on hyvin laaja ja kansainvälisesti kohdistettu, mikä tekee siitä lähes verrattoman muihin vastaaviin palveluihin nähden. Palvelua käytetään päivittäin jopa yli 180 maassa. TMDB:n vahva yhteisöllisyys tekee siitä erilaisen verrattaessa muihin vastaaviin palveluihin. (The Movie Database, 2024.)

API-rajapinta mahdollistaa eri ohjelmistojen tai palveluiden välistä kommunikaatiota ja yhteistoimintaa. Se määrittelee, miten eri ohjelmistokomponentit voivat kommunikoida keskenään ja millaisia toimintoja ne voivat suorittaa. API:n avulla kehittäjät voivat käyttää valmiita toimintoja tai palveluita osana omaa ohjelmointityötään. Ohjelmistorajapintojen juuret juontavat 1940-1950-luvulle, kun Maurice Wilkes, David Wheeler ja Stanley Gill kehittivät ohjelmistokirjastoja ja algoritmisia ratkaisuja ensimmäisten tietokoneiden yhteyteen. Näitä varhaisia kirjastoja voidaan pitää ensimmäisinä API:n kaltaisina käytänteinä. (Kristopher Sandoval, 2018.) 

TMDB:n rajapinnasta haetaan sivustolle muun muassa elokuvien ja tv-sarjojen tietoja, joita käyttäjät voivat hyödyntää sivuston eri osioissa esimerkiksi lisäämällä niitä suosikkilistalleen sekä katsottuihin ja katsottaviin. Esimerkiksi sovelluksen haku-sivu hakee rajapinnasta elokuvan ja tv-sarjan nimen, julisteen, tyylilajin, kielen, julkaisuajan sekä arvostelun. Osaa tiedoista käytetään hakusuodattimina ja osa näytetään hakutuloksissa.

## <a name ="sivusto"></a> Sivusto
Sivusto koostuu kotisivusta sekä useista sen alasivuista. Sivuston eri osiot ovat näkyvissä riippuen siitä, että onko käyttäjä kirjautunut vai ei. Lisäksi sivusto on responsiivinen, eli skaalautuu tietokoneen näytöltä erikokoisille kädessä pidettäville laitteille.

### Julkiset sivut:

#### Näytösajat (Showtimes)

Näytösajat-sivusto tarjoaa käyttäjille mahdollisuuden tutustua Finnkinon elokuvaohjelmistoon ja näytösaikoihin. Sivustolla voi selata elokuvia, näytösaikoja ja teattereita eri paikkakunnilla. Lisäksi käyttäjä voi halutessaan selailla elokuvia valitsemansa tyylilajin perusteella. Projektissamme olemme hyödyntäneet Finnkinon XML-pohjaista rajapintaa, joka mahdollistaa reaaliaikaisten tietojen hakemisen Finnkinon palvelusta. Käytämme tähän JavaScriptiä yhdessä React-kirjaston kanssa. 

XML-tietojen käsittelyyn ja parsimiseen käytämme selaimen `DOMParser`-objektia, joka mahdollistaa XML-dokumenttien tehokkaan käsittelyn ja datan uuttamisen tarpeidemme mukaisesti. Tämä toiminnallisuus tarjoaa käyttäjillemme pääsyn ajankohtaisiin tietoihin elokuvanäytöksistä, teatterien sijainneista ja elokuvien genreistä suoraan Finnkinon tietokannoista, mikä parantaa merkittävästi käyttäjäkokemusta sivustollamme.

#### Haku (Search)

Hakusivulla käyttäjät voivat etsiä tietoa eri elokuvista ja TV-sarjoista. Käyttäjät voivat käyttää hakukenttää arvosanan, genrejen tai julkaisuvuoden perusteella löytääkseen kiinnostavaa sisältöä.

#### Kirjautuminen (Login)

Kirjautumissivulla käyttäjät voivat kirjautua sisään antamalla käyttäjätunnuksensa ja salasanansa. Tämä sivu on tarkoitettu rekisteröityneille käyttäjille, joiden avulla he pääsevät käsiksi laajempiin palveluihin.

#### Rekisteröityminen (Signup)

Rekisteröitymissivulla uudet käyttäjät voivat luoda käyttäjätilin täyttämällä vaadittavat tiedot, kuten käyttäjänimi, sähköpostiosoite ja salasana. Rekisteröityminen mahdollistaa täyden pääsyn sivuston toiminnallisuuksiin.

### Kirjautumisen takana olevat sivut:

#### Ryhmäsivut

#### Käyttäjäsivut

#### Ruutukaappaukset (jos tarvetta erilliselle osiolle) 

## <a name ="lähteet"></a> Lähteet
The Movie Database (TMDB) 2024. About. Hakupäivä 23.4.2024. https://www.themoviedb.org/about.

Sandoval, Kristopher 2018. Who Invented the API?. Hakupäivä 23.4.2024. https://nordicapis.com/who-invented-the-api/.
