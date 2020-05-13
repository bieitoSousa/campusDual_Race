## [CAR.CLASS](./src/main/java/com/bieitosousa/campusdual/DATA/Car.md)

[ver codigo](./src/main/java/com/bieitosousa/campusdual/DATA/Car.md)


|  CLASS| DESCRIPTION |
|-----------------|-----------------|
|Car| Define un coche|


|  ATR | TYPE     | DESCRIPTION |
|--------------------|----------|-----------------|
|matriculaCont|Static int|contador pàra generar una matricula aleatoria|
|mark|String|marca de coche|
|model|String|modelo de coche|
|speed|int|velocidad de coche|
|maxSpeed|int|velocidad maxima del coche|
|distance|int| distancia recorrida|


----

----


## [GARAGE.CLASS](./src/main/java/com/bieitosousa/campusdual/DATA/Garage.md)

[ver codigo](./src/main/java/com/bieitosousa/campusdual/DATA/Garage.md)


|  CLASS    | DESCRIPTION |
|-----------------|-----------------|
|Car| Define un coche|

|  ATR | TYPE     | DESCRIPTION |
|--------------------|----------|-----------------|
|name |String |nombre de la escuderia |
|mapGcar |HashMap<String, Car>|lista de coches del garaje |



|  Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|addCar(Car)|Car.class||Añade un coche al garaje|
|deleteCar(Car)|Car.class||Elimina un coche del garaje|
|selecctRandomCar()||Car.class|selecciona un coche dentro del garaje|
|selectparticipant()|int| HashMap<String, Car>|0 = selecciona todos los coches del garaje|
|"|||1 = selecciona un coche aleatorio del garaje|


----

## [RACE.CLASS](./src/main/java/com/bieitosousa/campusdual/DATA/Race.md)

[ver codigo](./src/main/java/com/bieitosousa/campusdual/DATA/Race.md)

|  CLASS    |MODIFICADORES| DESCRIPTION |
|------|-----------|-----------------|
|Race| ABSTRACT|Define una carrera|


|  ATR  | TYPE     | DEFAULT |DESCRIPTION |
|---------|----------|------------|-----------------|
|name |String | ... | Nombre del premio que se sortea |
|mapGCar|HashMap<String, Garage>| ...  |Lista de garajes participantes en la carrera |
|mapGParticipe|HashMap<String, Car>| ...  |Lista de los coches que participan en la carrera |
|type|int|...|0 en carreras estandar 1 en eliminatorias |
|state| boolean |false| stado de la carrera true en curso false finalizada / no iniciada|
|result|ArrayList< Car >|)| dependera de eliminatoria o estandar|
|lap| int |??| numero de vueltas dependera de eliminatoria o estandar|
|distaceLap| int[] |??| distancia de cada vuelta eliminatoria o estandar|

|  Metodo | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|makeRace||boolean| diseño los pasos que conforman la carrera|
|startRace||String|pone la carrera como activa e incia el contador de ditancia de los coches |
|abstract startRace||boolean| genera las vultas , el recorrido , che acelera frena ..| 
|resultRace||boolean|genera el resultado de la carrera, en que posicion va cada coche | 
|endRace||String| cambia el estado de la carrera a finalizada |
|printResult||| imprime el resultado de la carrera |
|endRace|||cambia el estado de la carrera a false|
|exporRace|File|boolean|exporta los resultados de la carrera a un fichero|

----

## [ RACE_STANDAR.CLASS ](./src/main/java/com/bieitosousa/campusdual/DATA/Race_Standar.md)
[ ver codigo ](./src/main/java/com/bieitosousa/campusdual/DATA/Race_Standar.md)

|  CLASS    |HERENCIA| DESCRIPTION |
|-----------------|-----------------|------------|
|Race_Standar| Race.Class | Define una carrera "ESTANDAR"|


|  ATR | TYPE     | DEFAULT |DESCRIPTION |
|----------|----------|----------|-----------------|
|duration |int |3 * 60 *  60 | Tiempo que dura la carrera 3h x60 m x60 s | 
|lap  |int | 3 * 60   |  nº de vueltas| cada vuelta dura 1 minuto|
|type|int|0|tipo 0 carreras estandar |


|  Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|doRace||boolean|recorro los coches hasta que se termine la duraccion de la prueba|
|"|||coche velocidad :  por cada 1 t , cada coche aleatoriamente aunemtara o decrementa su velocidad en 1 entre los valores 0 y 5 |
|"|||coche añadir distancia :  por cada 1 t , cada coche aumenta la distancia segun su velocidad  |
|"|||vuelta :  por cada  vuelta = 60 t , por cada  60 unidad de tiempo (t)  numero de vuelta + 1 |
|"|||ditancia cada vuelta : es igual a la distancia que recorrio el 1 º al cabo de cada 60(t)|



----

## [ RACE_ELIMINATION.CLASS ](./src/main/java/com/bieitosousa/campusdual/DATA/Race_Elimination.md)
[ ver codigo ](./src/main/java/com/bieitosousa/campusdual/DATA/Race_Elimination.md)

|  CLASS    | HERENCIA | DESCRIPTION |
|-----------------|-----------------|------------|
|Race_Elimination | Race.Class | Define una carrera "ELIMINACION"|


|  ATR | TYPE     | DEFAULT |DESCRIPTION |
|--------------------|----------|---------|--------|
|lap|int|this.mapGParticipe.size() - 1 | nº de vueltas |
|type|int|1|tipo 1 casrreras eliminatorias |


|Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|doRace||boolean|recorro los coches hasta que se termine el numero de vueltas |
|"|||coche velocidad :  por cada 1 t , cada coche aleatoriamente aunemtara o decrementa su velocidad en 1 entre los valores 0 y 5 |
|"|||coche añadir distancia :  por cada 1 t , cada coche aumenta la distancia segun su velocidad  |
|"|||vuelta :  por cada  vuelta = 60 t , por cada  60 unidad de tiempo (t)  numero de vuelta + 1 |
|"|||ditancia cada vuelta : es igual a la distancia que recorrio el 1 º al cabo de cada 60(t)|
|"|||Eliminacion : Cuando el penultimo halla recorrido la distancia de la vuelta se elimina al ultimo coche|




## TORNAMENT.CLASS


|  CLASS Name   | HERENCIA | DESCRIPTION |
|-----------------|-----------------|------------|
|Tornament.class | ... | Torneos |


|ATR | TYPE     | DEFAULT |DESCRIPTION |
|--------|------------|----------|-----------------|
|name |String  | ... | nombre del torneo| 
|type |int  | -1 | tipo 0 carreras estandar ; tipo 1 carreras eliminatorias| 
|listTornRace|Array(Race.class)| new Array(10)| lista de carreras del torneo|
|winer| Car.class| ...| ganador del torneo|
|state|boolean|false| True el torneo esta activo | false el torneo esta inactivo|


|Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|getWiner|...|Car.class|recorrer listTornRace.result() sumando puntos, en caso de empate se divide el premio|
|getState|..|boolean|recorre listTornRace().state, devuelve true si alguna carrera del torneo esta activa ; false si todas estan desactivadas|
|addRace|Race|boolean|si hay espacios en el torneo y si el estado del torneo no etsa definido o es igual a el de la carrera añade dicha carrera |
|"|||si hay espacios en el torneo y si (this.state = -1  o this.state= race.state)|
|"|||añade a race.setInTorneo(true),race.setGrupTorn(this.name) |
|exporResult|File|boolean| tru operacion correcta , false fallo. recorre listTornRace().export(File) exporta los resultados a un fichero|




## CONTROL.CLASS


|  CLASS Name   | HERENCIA | DESCRIPTION |
|-----------------|-----------------|------------|
|Control.class | ... | panel de control |


|ATR | TYPE     | DEFAULT |DESCRIPTION |
|--------|------------|----------|-----------------|
|listTornAndRace|ArrayList()< Race.class >|| lista de carreras totales|
|listGarage|ArrayList()< Garage.class >|| lista de garajes totales|
|selectedAllGarajes|boolean|false|todos los garajes estan seleccionados|
|selectedOneGaraje|Garaje|...|seleccionado un garaje|
|isCreateOpen|boolean|false| se puede iniciar carreras o torneos|
|TornSelected|boolean|false| se puede iniciar carreras o torneos|
|raceSelected|boolean|false| se puede iniciar carreras o torneos|




NOTA : los metodos seleccted.. y create... devuelve true operacion exito , false operacion fallida.


|Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|getisCreateOpen||boolean|recorre listTornAndRace().state() si todas estan en false = true si hay alguna en true = false |
|getListGarageReadFile|File|ListGarage|lee un fichero y crea los agrages y los guarda en el grupo garajes |
|getState|..|boolean|recorre listTornRace().state, devuelve true si alguna carrera del torneo esta activa ; false si todas estan desactivadas|
|createRaceAll|..|boolean|recorre listTornRace().state, devuelve true si alguna carrera del torneo esta activa ; false si todas estan desactivadas|
|createRaceOne|..|boolean|recorre listTornRace().state, devuelve true si alguna carrera del torneo esta activa ; false si todas estan desactivadas|
|createTornAll|int {0,1}|boolean|se creara un torneo recorriendo listGarage().selecctRandomCar() y el tipo 0 estandar , 1 eliminatoria|
|createTornOne|int {0,1}|boolean|se creara un torneo con los datos del garaje seleccionado y el tipo 0 estandar , 1 eliminatoria|
|createRacenAll|int {0,1}|boolean|se creara un carrera recorriendo listGarage().selecctRandomCar()y el tipo 0 estandar , 1 eliminatoria |
|createRaceOne|int {0,1}|boolean|se creara un carrera con los datos del garaje seleccionado y el tipo 0 estandar , 1 eliminatoria|
|startTornSelected||boolean|selectedTorn.start() iniscia el torneo seleccionado|
|startRaceSelected||boolean| selectedTorn.start() inicia la carrera seleccionada|
|choseTornSelected||boolean|selectedTorn.start() iniscia el torneo seleccionado|
|choseRaceSelected||boolean| selectedTorn.start() inicia la carrera seleccionada|
|viewTornList||boolean|muestra la lista listTornAndRace().grupTorn() filtrando de que no se repoita ningun nombre de torneo|
|viewRaceList||boolean|muestra la lista listTornAndRace|


