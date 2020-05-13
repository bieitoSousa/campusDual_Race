| Nombre  | ruta |
| ------ | ------ |
|All clases |[ Ver Todas las Clases](../../../../../../../class.md)|
|Portada |[ Volver a la portada ](../../../../../../../README.md)|


|  CLASS    | HERENCIA | DESCRIPTION |
|-----------------|-----------------|------------|
|Race_Elimination | Race.Class | Define una carrera "ELIMINACION"|


|  ATR | TYPE     | DEFAULT |DESCRIPTION |
|--------------------|----------|---------|--------|
|vueltas|int|this.mapGParticipe.size() - 1 | nº de vueltas |
|type|int|1|tipo 1 casrreras eliminatorias |


|Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|doRace||boolean|recorro los coches hasta que se termine el numero de vueltas |
||||coche velocidad :  por cada 1 t , cada coche aleatoriamente aunemtara o decrementa su velocidad en 1 entre los valores 0 y 5 |
||||coche añadir distancia :  por cada 1 t , cada coche aumenta la distancia segun su velocidad  |
||||vuelta :  por cada  vuelta = 60 t , por cada  60 unidad de tiempo (t)  numero de vuelta + 1 |
||||ditancia cada vuelta : es igual a la distancia que recorrio el 1 º al cabo de cada 60(t)|
||||Eliminacion : Cuando el penultimo halla recorrido la distancia de la vuelta se elimina al ultimo coche|


----


```JAVA

// ===================================================//
//  =   Class  Race_eliminate
//  =   you can create race to eliminate car
// ===================================================//

public class Race_Elimination extends Race {

    //  = Constructor ALL Garages
    public Race_Elimination(
            String name, int type,
            HashMap<String, Garage> mapGCar,
            boolean isAllCarPasrticipe
    ) {
        super(name, type, mapGCar, isAllCarPasrticipe);
        this.lap = this.mapGParticipe.size() - 1;
        this.type = 1;
    }

    //  = Constructor ONE  Garages
    public Race_Elimination(
            String name, int type,
            Garage garage,
            // all Cars on the garage participate true
            // one Car on the garage participate false
            boolean isAllCarPasrticipe
    ) {
        super(name, type, garage, isAllCarPasrticipe);
        this.lap = this.mapGParticipe.size() - 1;
        this.type = 1;
    }



```

----

* METHOD

----


```JAVA

 // generate a eliminate race cars
    @Override
    public boolean doRace() {
        // update distance and speed  and take results in mapGParticipe
        // loop , eliminate in mapRacer
        int vuelta = 0;
        int vueltaEliminacion = 0;
        HashMap<String, Car> mapRacer;
        mapRacer = mapGParticipe;
        for (int time = 1; lap >= vuelta; time++) {
            // first run car
            for (Car c : mapRacer.values()) {
              // random increametate speed betwen 0 to 5  
                if ((Math.random() > 0.5f)
                        && (mapGParticipe.get(c.matricula).speed
                        > mapGParticipe.get(c.matricula).maxSpeed)) {
                    mapGParticipe.get(c.matricula).speed++;
                } else {
                    if (mapGParticipe.get(c.matricula).speed != 0) {
                        mapGParticipe.get(c.matricula).speed--;
                    }
                }
              // distance = distance +speed  
              mapGParticipe.get(c.matricula).distance
                      +=  mapGParticipe.get(c.matricula).speed;
            }
            // 2 take results
            resultRace();
            // 3 analice results
            // 3. 1 time = 60 -->  1 lap
            if (time == 60) {
                this.distaceLap[vuelta] = result.get(0).distance;
                vuelta++;
            }
            // 3.2 when the "penultimo" end the lap , eliminate the last car 
            if (result.get(result.size() - 2).distance
                    == this.distaceLap[vueltaEliminacion]) {
                // delete car of the map Race
                mapRacer.remove(result.get(result.size() - 1).matricula);
            }

        }
        return true;
    }


```