| Nombre  | ruta |
| ------ | ------ |
|All clases |[ Ver Todas las Clases](../../../../../../../class.md)|
|Portada |[ Volver a la portada ](../../../../../../../README.md)|



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


```JAVA

// ===================================================//
//  =   Class  Race_standar
//  =   you can create a race with  a duration limit 
//  =   win the car who get more distance in 3 hour
// ===================================================//

public class Race_Standar extends Race {

    private int duration;

    //  = Constructor ALL Garages
    public Race_Standar(
            String name, int type,
            HashMap<String, Garage> mapGCar,
            boolean isAllCarPasrticipe
    ) {
        super(name, type, mapGCar, isAllCarPasrticipe);
        this.lap = 3 * 60;
        this.type = 0;
        this.duration = 3 * 60 * 60;
    }

    //  = Constructor ONE  Garages
    public Race_Standar(
            String name, int type,
            Garage garage,
            // all Cars on the garage participate true
            // one Car on the garage participate false
            boolean isAllCarPasrticipe
    ) {
        super(name, type, garage, isAllCarPasrticipe);
        this.lap = 3 * 60;
        this.type = 0;
        this.duration = 3 * 60 * 60;
    }
    


```

----

* METHOD

----


```JAVA

// generate a standar race cars
    @Override
    public boolean doRace() {
        // update distance and speed  and take results in mapGParticipe
        // loop , eliminate in mapRacer
        int lap = 0;
        int lapEliminacion = 0;
        HashMap<String, Car> mapRacer;
        mapRacer = mapGParticipe;
        // race duration 3*60*60*
        for (int time = 1; duration == time; time++) {
            // first run car
            for (Car c : mapRacer.values()) {
                if ((Math.random() > 0.5f)
                        && (mapGParticipe.get(c.matricula).speed
                        > mapGParticipe.get(c.matricula).maxSpeed)) {
                    mapGParticipe.get(c.matricula).speed++;
                } else {
                    if (mapGParticipe.get(c.matricula).speed != 0) {
                        mapGParticipe.get(c.matricula).speed--;
                    }
                }
            }
            // 2 take results
            resultRace();
            // 3 analice results
            // 3. 1 time = 60 -->  1 lap
            if (time == 60) {
                this.distaceLap[lap] = result.get(0).distance;
                lap++;
            }
        }
        return true;
    }




```