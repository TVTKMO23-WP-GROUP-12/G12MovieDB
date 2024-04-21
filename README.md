# Group 12 Movie Database

## Index:
- [Jäsenet](#jasenet)
- [Esittely](esittely)
- [Teknologia](teknologia)
	- [Backend](#backend)
	- [Frontend](#frontend)
	- [Tietokanta](#tietokanta) 
- [Sivusto](#sivusto)


## <a name ="jasenet"></a> Ryhmän jäsenet:
- Pasi Honka - [Rapuska](https://www.github.com/Rapuska)

- Henri Laukka - [MacStache](https://www.github.com/MacStache)

- Lari Lepistö - [LariLepisto](https://www.github.com/LariLepisto)

- Sanna Mäki - [Rahukettu](https://www.github.com/rahukettu)

## <a name ="esittely"></a> Esittely
Group 12 Movie Database on websovellus jonka välityksellä elokuvista pitävät voivat keskustella keskenään ja arvioida sekä listata näkemiään elokuvia. 

Sivusto toimii sosiaalisen median sivuston kaltaisella periaatteella, eli käyttäjät voivat seurata toisiaan, lähettää toisilleen viestejä sekä liittyä erilaisiin ryhmiin joissa voi keskustella esimerkiksi samankaltaisista elokuvista pitävien käyttäjien kanssa. Ryhmien muodolla ja tarkoituksella ei ole rajoitetta, joten käyttäjä voi itse perustaa minkälaisen ryhmän tahansa. 

Seuraavissa kappaleissa käydään läpi sivuston teknologisempaa puolta, alkaen [backendistä](#backend), jatkuen [frontendiin](#frontend) ja päättyen [tietokantaan](#tietokanta).

## <a name ="teknologia"></a>Teknologia

### <a name ="backend"></a> Backend 
Sivuston backend taustatoiminnot on rakennettu käyttämällä Java-kielellä toimivaa [Spring Boot](https://spring.io/projects/spring-boot) -kehystä. Spring Boot on avoimen lähdekoodin Java -kehys, joka on tarkoitettu tämän projektin tyylisten mikropalveluiden kehittämiseen. Se on  hyvin mukautuvainen ja helppokäyttöinen kehys, jonka avulla on helppo luoda itsenäisiä, tuotantotasoisia Springiin perustuvia sovelluksia.

#### Tämän projektin backendin käyttämien riippuvuuksien versiot:
- **Java:** *17*
- **Spring Boot:** *3.2.4*
- **okhttp3:** *4.12.0*

### <a name ="frontend"></a> Frontend
Sivuston frontend -- eli selainpuolen -- koodina käytetään [React JavaScript](https://react.dev/) -kirjastoa. Kirjasto on Metan (Facebook) alunperin julkaisema avoimen lähdekoodin kirjasto, joka on tarkoitettu verkkosivustojen käyttöliittymien kehittämiseen, mutta sitä voi käyttää nykyisin myös mobiili- sekä työpöytäsovellusten kehittämiseen.

#### Tämän projektin frontend käyttämien riippuvuuksien versiot:
- **React:** *18.2.0*
- **Axios:** *1.6.8*

### <a name ="tietokanta"></a> Tietokanta
Tietokantana toimii avoimeen lähdekoodiin perustuva PostgreSQL-tietokantajärjestelmä. PostgreSQL perustuu relaatiomallille, ja se on vaihtoehto muille vapaan lähdekoodin tietokantajärjestelmille, kuten MySQL, sekä myös kaupallisille järjestelmille, kuten Oracle sekä Sybase.

Projektin tietokanta pyörii [Render](https://render.com/) -palvelussa.

#### Tässä projektissa käytetyn tietokantasovelluksen versio:
- **PostgreSQL**: *16*

## <a name ="sivusto"></a> Sivusto
Sivusto koostuu kotisivusta sekä useista sen alasivuista. Sivuston eri osiot ovat näkyvissä riippuen siitä, että onko käyttäjä kirjautunut vai ei. Lisäksi sivusto on responsiivinen, eli skaalautuu tietokoneen näytöltä erikokoisille kädessä pidettäville laitteille.

#### Julkiset sivut:

#### Tähän alle otsikot eri sivuista ja lyhyt selitys niiden sisällöstä sekä tarvittaessa ruutukaappaus

#### Kirjautumisen takana olevat sivut:

#### Ruutukaappaukset (jos tarvetta erilliselle osiolle) 

