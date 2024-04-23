# Group 12 Movie Database

## Index:
- [Jäsenet](#jasenet)
- [Esittely](esittely)
- [Teknologia](teknologia)
	- [Backend](#backend)
	- [Frontend](#frontend)
	- [Tietokanta](#tietokanta) 
	- [TMDB API](#tmdb_api) 
- [Sivusto](#sivusto)
- [Lähteet](#lähteet)

## <a name ="jasenet"></a> Ryhmän jäsenet:
- Pasi Honka - [Rapuska](https://www.github.com/Rapuska)

- Henri Laukka - [MacStache](https://www.github.com/MacStache)

- Lari Lepistö - [LariLepisto](https://www.github.com/LariLepisto)

- Sanna Mäki - [Rahukettu](https://www.github.com/rahukettu)

## <a name ="esittely"></a> Esittely
Group 12 Movie Database on websovellus, jonka välityksellä elokuvista pitävät voivat keskustella keskenään ja arvioida sekä listata näkemiään elokuvia. 

Sivusto toimii sosiaalisen median sivuston kaltaisella periaatteella, eli käyttäjät voivat seurata toisiaan, lähettää toisilleen viestejä sekä liittyä erilaisiin ryhmiin joissa voi keskustella esimerkiksi samankaltaisista elokuvista pitävien käyttäjien kanssa. Ryhmien muodolla ja tarkoituksella ei ole rajoitetta, joten käyttäjä voi itse perustaa minkälaisen ryhmän tahansa. 

Käyttäjät voivat rekisteröityä palveluun, kirjautua omalle tililleen ja kirjautua tililtä ulos. Julkiset (ei-autentikoidut) käyttäjät pääsevät projektin julkisille sivuille.
Autentikoidut käyttäjät pääsevät kaikille sivuille ja voivat lisäksi muodostaa uusia ryhmiä, julkaista arvosteluja, muokata kirjoittamiaan arvosteluja ja poistaa kirjoittamansa arvostelut ja luomansa ryhmät.

Seuraavissa kappaleissa käydään läpi sivuston teknologisempaa puolta, alkaen [backendistä](#backend), jatkuen [frontendiin](#frontend) ja [tietokantaan](#tietokanta) ja päättyen [TMDB:n ohjelmistorajapintaan](#tmdb_api).

## <a name ="teknologia"></a>Teknologia

### <a name ="backend"></a> Backend 
Sivuston backend-toiminnot on rakennettu käyttämällä Java-kielellä toimivaa [Spring Boot](https://spring.io/projects/spring-boot) -sovelluskehystä. Spring Boot on avoimen lähdekoodin Java-projektikirjasto, joka on tarkoitettu esimerkiksi tämän projektin tyylisten mikropalveluiden kehittämiseen. Se on hyvin mukautuvainen ja helppokäyttöinen kehys, jonka avulla on helppo luoda itsenäisiä, tuotantotasoisia Springiin perustuvia sovelluksia. Spring Boot tarjoaa monenlaisia mahdollisuuksia. Yksi sen ydinkonsepteista on riippuvuusinjektio, mikä tarkoittaa, että niin kauan kuin koodissa käytetään oikeanlaisia riippuvuuskuvauksia, Spring huolehtii sovelluksen eri komponenttien alustamisesta.

#### Tämän projektin backendin käyttämien riippuvuuksien versiot:
- **Java:** *17*
- **Spring Boot:** *3.2.4*
- **okhttp3:** *4.12.0*

Käyttäjän autentikointi
Käyttäjän autentikointi tehdään Spring securityllä. 

Sivujen rakenne

- Model-Controller -malli
- Repositoriot

API





### <a name ="frontend"></a> Frontend
Sivuston frontend -- eli selainpuolen -- koodina käytetään [React JavaScript](https://react.dev/) -kirjastoa. Kirjasto on Metan (Facebook) alunperin julkaisema avoimen lähdekoodin kirjasto, joka on tarkoitettu verkkosivustojen käyttöliittymien kehittämiseen, mutta sitä voi käyttää nykyisin myös mobiili- sekä työpöytäsovellusten kehittämiseen.

#### Tämän projektin frontend käyttämien riippuvuuksien versiot:
- **React:** *18.2.0*
- **Axios:** *1.6.8*

### <a name ="tietokanta"></a> Tietokanta
Tietokantana toimii avoimeen lähdekoodiin perustuva [PostgreSQL](https://www.postgresql.org/) -tietokantapalvelin. PostgreSQL perustuu relaatiomallille, ja se on vaihtoehto muille vapaan lähdekoodin tietokantajärjestelmille, kuten MySQL, sekä myös kaupallisille järjestelmille, kuten Oracle sekä Sybase.

Projektin tietokanta toimii [Render](https://render.com/) -palvelussa.

#### Tässä projektissa käytetyn tietokantasovelluksen versio:
- **PostgreSQL**: *16*

### <a name ="tmdb_api"></a> TMDB API
Sivusto hyödyntää The Movie Databasen *(TMDB)* palveluita ohjelmistorajapinnan *(application programming interface, API)* avulla. TMDB tarjoaa sovelluskehittäjille ja muille käyttäjille API-avaimen, joka voidaan integroida omaan sovellukseen tietyin TMDB:n määrittelemin ehdoin.

TMDB on elokuvien ja tv-sarjojen tietokanta, jota on rakennettu sen käyttäjien toimesta jo vuodesta 2008 alkaen. TMDB:n tietosisältö on hyvin laaja ja kansainvälisesti kohdistettu, mikä tekee siitä lähes verrattoman muihin vastaaviin palveluihin nähden. Palvelua käytetään päivittäin **jopa yli 180** maassa. TMDB:n vahva yhteisöllisyys tekee siitä erilaisen verrattaessa muihin vastaaviin palveluihin. (The Movie Database, 2024.)

API-rajapinta mahdollistaa eri ohjelmistojen tai palveluiden välistä kommunikaatiota ja yhteistoimintaa. Se määrittelee, miten eri ohjelmistokomponentit voivat kommunikoida keskenään ja millaisia toimintoja ne voivat suorittaa. API:n avulla kehittäjät voivat käyttää valmiita toimintoja tai palveluita osana omaa ohjelmointityötään. Ohjelmistorajapintojen juuret juontavat 1940-1950-luvulle, kun *Maurice Wilkes*, *David Wheeler* ja *Stanley Gill* kehittivät ohjelmistokirjastoja ja algoritmisia ratkaisuja ensimmäisten tietokoneiden yhteyteen. Näitä varhaisia kirjastoja voidaan pitää ensimmäisinä API:n kaltaisina käytänteinä. (Kristopher Sandoval, 2018.) 

TMDB:n rajapinnasta haetaan sivustolle muun muassa elokuvien ja tv-sarjojen tietoja, joita käyttäjät voivat hyödyntää sivuston eri osioissa esimerkiksi lisäämällä niitä suosikkilistalleen sekä katsottuihin ja katsottaviin. Esimerkiksi sovelluksen haku-sivu hakee rajapinnasta elokuvan ja tv-sarjan nimen, julisteen, tyylilajin, kielen, julkaisuajan sekä arvostelun. Osaa tiedoista käytetään hakusuodattimina ja osa näytetään hakutuloksissa.

## <a name ="sivusto"></a> Sivusto
Sivusto koostuu kotisivusta sekä useista sen alasivuista. Sivuston eri osiot ovat näkyvissä riippuen siitä, että onko käyttäjä kirjautunut vai ei. Lisäksi sivusto on responsiivinen, eli skaalautuu tietokoneen näytöltä erikokoisille kädessä pidettäville laitteille.

#### Julkiset sivut:

#### Tähän alle otsikot eri sivuista ja lyhyt selitys niiden sisällöstä sekä tarvittaessa ruutukaappaus

#### Kirjautumisen takana olevat sivut:

#### Ruutukaappaukset (jos tarvetta erilliselle osiolle) 

## <a name ="lähteet"></a> Lähteet
The Movie Database (TMDB) 2024. About. Hakupäivä 23.4.2024. https://www.themoviedb.org/about.

Sandoval, Kristopher 2018. Who Invented the API?. Hakupäivä 23.4.2024. https://nordicapis.com/who-invented-the-api/.
