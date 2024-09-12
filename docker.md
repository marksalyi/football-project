A Docker lehetővé teszi, hogy az alkalmazásokat és minden szükséges összetevőt, például könyvtárakat és konfigurációkat,
egy csomagba, úgynevezett konténerbe zárjuk, így biztosítva, hogy az alkalmazás ugyanúgy működjön minden környezetben,
függetlenül attól, hogy hol futtatjuk, például a fejlesztői gépen, a tesztkörnyezetben vagy a termelési szerveren.

Container:

Fogalom: A Docker konténerek az alkalmazások és azok összes szükséges függőségeinek izolált, hordozható egységei.
Egy konténer tartalmazza az alkalmazást és annak futtatásához szükséges könyvtárakat, konfigurációkat és egyéb fájlokat.

Előnyök: Könnyűszerkezetűek, gyorsan indíthatók, és biztosítják, hogy az alkalmazás minden környezetben ugyanúgy működjön.


Images:
Fogalom: A Docker imagek az alkalmazások és azok függőségeinek előre definiált csomagjai, amelyeket containerek létrehozásához használnak.
Az imagek „olvasóként” működnek, míg a konténerek „íróként”.
Előnyök: Könnyen megoszthatók, verziózhatók és újra felhasználhatók


Dockerfile:
Fogalom: A Dockerfile egy szöveges fájl, amely tartalmazza az összes utasítást, amely szükséges egy Docker image létrehozásához.
Előnyök: Automatizálja az imagek építését és biztosítja a repeatálható környezetet.

Docker Compose:
Fogalom: Eszköz a több containerből álló alkalmazások egyszerű kezelésére. A docker-compose.yml fájl segítségével definiálhatók a containerek, hálózatok és volumenek.
Előnyök: Egyszerűsíti a komplex alkalmazások telepítését és kezelését.

Docker Hub:
Fogalom: Nyilvános Docker image registry, ahol imageket tárolhatunk, oszthatunk meg és kereshetünk.
Előnyök: Központi hely az imagek tárolására és megosztására.


Működési Elvek:
Isoláció: A Docker containerek izolálják az alkalmazásokat és azok függőségeit egymástól, biztosítva, hogy a különböző containerek ne befolyásolják egymást.

Hordozhatóság: Az alkalmazások ugyanúgy működnek bárhol, ahol Docker fut, így csökkenti a „de ez nálam működött” problémákat.

Könnyűszerkezetűség: A containerek gyorsan indulnak és leállnak, így könnyen skálázhatók és kezelhetők.


Felhasználási Esetek:
Fejlesztés és Tesztelés: Egységes fejlesztési környezet biztosítása a csapat számára.
CI/CD: Folyamatos integráció és szállítás támogatása konténerek használatával.
Skálázás: Alkalmazások könnyű skálázása és menedzselése containerek segítségével.


Gyakorlati Parancsok:
docker run: Új container indítása egy imageből.
docker ps: Futó containerek listázása.
docker stop: Container leállítása.
docker rm: Container eltávolítása.
docker build: Image építése Dockerfile-ból.
docker-compose up: Több containerből álló alkalmazás indítása Docker Compose segítségével
