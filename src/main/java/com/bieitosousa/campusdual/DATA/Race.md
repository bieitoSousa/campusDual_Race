| Nombre  | ruta |
| ------ | ------ |
|All clases |[ Ver Todas las Clases](../../../../../../../class.md)|
|Portada |[ Volver a la portada ](../../../../../../../README.md)|


## [RACE.CLASS](./src/main/java/com/bieitosousa/campusdual/DATA/Race.md)

[ver codigo](./src/main/java/com/bieitosousa/campusdual/DATA/Race.md)

|  CLASS    |MODIFICADORES| DESCRIPTION |
|------|-----------|-----------------|
|Race| ABSTRACT|Define una carrera|


|  ATR  | TYPE     | DEFAULT |DESCRIPTION |
|---------|----------|------------|-----------------|
|name |String | ... | Nombre del premio que se sortea |
|type|int|...|0 en carreras estandar 1 en eliminatorias |
|result|Car[]|)|almacena los resultados de la carrera|
|partic| Car[] ||almancena los participantes de la carrera|



|  Metodo | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|makeRace||| realiza la carrera|
|printArray||imprime la carrera |
|exportRace||exporta la carrera a un fichero| 
|OrderCarAsPosition|Car[]|Car[]|ordena los coches por posicion | 


----
* ATRIBUTES
----


```JAVA

// ===================================================//
//  =   Class to Create Race
//  =   you can add Garages , makeRace and printResult
// ===================================================//
// name of the race
    protected String name;
    // type = 0 Estandar ; type = 1 Eliminarorias
    protected int type;
    // true --> is on the run if  result != null 
    // positions ,0--> 1ºCar ,1-->2ºCar ,2-->3ºCar 
    // store of the race result 
    protected Car[] result = null;
        // store of the race participants 
    protected Car[] partic = null;


```
----
* BUILDER
----

```JAVA
 public Race(
            String name, int type,
            ArrayList<Garage> listGarage,
            // all Cars on the garage participate true
            // one Car on the garage participate false
            boolean isAllCarPasrticipe
    ) {
        this.name = name;
        this.type = type;
        // Definimos la lista de participantes
        ArrayList<Car> listCarParticipe = null;
        if (isAllCarPasrticipe) {
            // Loop the gararges and take one the cart to participate
            for (Garage g : listGarage) {
                listCarParticipe = listGarage.get(listGarage.indexOf(g)).getOneCar();
            }
        } else { // Loop the gararges and take all the cart to participate
            for (Garage g : listGarage) {
                listCarParticipe = listGarage.get(listGarage.indexOf(g)).getAllCar();
            }
        }
        listCarParticipe.toArray(this.partic);
    }
```

----

* METHOD

----


```JAVA

       protected void makeRace() throws Exception {
        //  ================================================//
        //  =   =   =   =   Variables locales =   =   =   =   
        //  ================================================//
        // variables auxiliares
        boolean printInLap = false;
        boolean printInDelete = false;

        // lap , MaxLap , duracion
        int lap = 0; // inicio en vuelta 0
        int maxLap = -1; // inicio en -1 :: si la uso reinicio en 0
        int duration = -1; // inicio en -1 :: si la uso reinicio en 0
        //Carrera Standar : duracion
        if (type == 0) {
            duration = 3 * 60 * 60;
            printInLap = true; // seguimiento de la carrera
            //Carrera Eliminatoria    : nº vueltas
        } else if (type == 1) {
            maxLap = partic.length - 1;
            printInDelete = true; // seguimiento de la carrera
        } else if (maxLap == 0) {
            throw new Exception("Numero maximo de vueltas mal calculado");
        }
        //  = > arryRace :  array para tratar la carrera
        Car[] arryRace = this.partic;  // tratar los participantes en la carrera
        if (!(this.partic.length > 0 && arryRace.length > 0)) {
            throw new Exception("NO se han cargado la lista de coches adecuadamente");
        }
        // = > arryResult :  array vacio  para tratar los resultados
        Car[] arryResult = new Car[arryRace.length];

        //  ================================================//
        //  =   =   =   =   Pre carrera =   =   =   =   =   =
        //  ================================================//
        // antes de empezar la carrera todos los participantes 
        // speed =0
        // distance = 0
        for (Car c : arryRace) {
            c.setSpeed(0);
            c.setDistance(0);
        }
        //  ================================================//
        //  =   =   =   =   Carrera =   =   =   =   =   =
        //  ================================================//  
        int[] lapDistance = null; // registro la distancia de cada vuelta.
        int lapTime = 60; // cada vuelta dura 60 time
        int lapActive = 0; // numero de vuelta hasta que pase el ultimo coche por meta
        //  =   =   = FILTROS =   =   =   = 
        // Carrera Eliminancion duration = -1 , maxLap = 0 ; type =1
        if (duration != -1 && maxLap != 0 && type != 1) {
            throw new Exception("");
        }
        // Carrera Estandar duration = 0 , maxLap = -1 ; type =0
        if (duration != 0 && maxLap != -1 && type != 0) {
            throw new Exception("");
        }
        // la Carrea type 1 finaliza por vueltas , Carrera type 0 por duracion
        for (int time = 1;
                (type == 1 && maxLap == lap) || (type == 0 && duration == time);
                time++) {
            // =======  Cada Time abanzan los coches entre 1 y 5 =======//
            for (Car cc : arryRace) {
                if (Math.random() > 0.5f
                        && cc.getSpeed() > cc.getMAXSPEED()) {
                    int a = cc.getSpeed();
                    cc.setSpeed(a++);
                } else {
                    if (cc.getSpeed() != 0) {
                        int b = cc.getSpeed();
                        cc.setSpeed(b--);
                    }
                }
                int v = cc.getSpeed() + cc.getDistance();
                cc.setDistance(v);
            }
            // === ANALIS ====
            // una vez abanzados los coches analizamos posiciones
            // Ordenamos los coches esgun posicion
            arryRace = OrderCarAsPosition(arryRace);

            // = Vueltas ; cada vuelta dura --> lap = 60 t
            if (time == lapTime) {
                lapTime = +lapTime;
                // calculo cuanta distancia recorrio el 1 al terminar la vuelta
                // esa va ser la distancia de dicha vuelta.
                lapDistance[lap] = arryRace[0].getDistance();
                lap++;
                // = Si son cararreras de Elimnacion  

                if (this.type == 0) {
                    System.out.println("Tabla de Claificacion :");
                    printArray(arryResult);
                    System.out.println("Tabla de Carrera :");
                    printArray(arryRace);
                }

                if (this.type == 1) {
                    //Eliminar coche
                    // Elimino el coche cuando el ultimo coche tenga mas distancia
                    //  que la distancia de la vuelta
                    if (lapDistance[lapActive] == arryRace[arryRace.length - 1].getDistance()) {
                        // añadimos el coche eliminado en la ultima posicion activa 
                        //      del array resultados
                        arryResult[arryRace.length - 1] = arryRace[arryRace.length - 1];
                        // org.apache.commons.lang.ArrayUtils;
                        // Elimino el coche
                        arryRace = (Car[]) ArrayUtils.remove(arryRace, arryRace.length - 1);
                        // paso el ultimo coche se focaliza la siguente vuelta
                        lapActive++;
                        if (printInDelete) {
                            System.out.println("Tabla de Claificacion :");
                            printArray(arryResult);
                            System.out.println("Tabla de Carrera :");
                            printArray(arryRace);
                        }
                    }

                }

            }
        }// Fin de carrera 
        //  ================================================//
        //  =   =   =   TRAS   Carrera =   =   =   =   =   =
        //  ================================================//  

        // == Trato Los resultados.
        if (type == 1) {// carrera Eliminacion registro el 1 coche
            // pongo la primera posicione en el arrayResult
            arryResult[arryRace.length - 1] = arryRace[arryRace.length - 1];
        } else if (type == 0) {// carrera Estandar registro toda la clasificacion
            arryResult = arryRace;
        }
        this.result = arryResult;
        // finalizo la carrera 
        // == Exporto los datos
        exportRace();
    }//FinmakeRace

    private Car[] OrderCarAsPosition(Car[] listCar) {
        int cuentaintercambios = 0;
        //Usamos un bucle anidado, saldra cuando este ordenado el array
        for (boolean ordenado = false; !ordenado;) {
            for (int i = 0; i < listCar.length - 1; i++) {
                if (listCar[i].getDistance() > listCar[i + 1].getDistance()) {
                    //Intercambiamos valores
                    int vAux = listCar[i].getDistance();
                    listCar[i].setDistance(listCar[i + 1].getDistance());
                    listCar[i + 1].setDistance(vAux);
                    //indicamos que hay un cambio
                    cuentaintercambios++;
                }
            }
            //Si no hay intercambios, es que esta ordenado.
            if (cuentaintercambios == 0) {
                ordenado = true;
            }
            //Inicializamos la variable de nuevo para que empiece a contar de nuevo
            cuentaintercambios = 0;
        }
        return listCar;
    }

    public void printArray(Object[] oa) {
        for (Object o : oa) {
            System.out.println(o);
        }
    }

    public boolean exportRace() {
        File f = new File(".//export//race//");
        if (!f.exists()) {
            f.mkdirs();
        }
        Date date = new Date();
        name = "race_" + this.name + date.getTime();
        File fname = new File(".//export//race//" + name);
        return JSON.WriteObjJsonInFile(fname, this);
    }


```

