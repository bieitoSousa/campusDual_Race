| Nombre  | ruta |
| ------ | ------ |
|All clases |[ Ver Todas las Clases](../../../../../../../class.md)|
|Portada |[ Volver a la portada ](../../../../../../../README.md)|


## CAR.CLASS


|  CLASS| DESCRIPTION |
|-----------------|-----------------|
|Car| Define un coche|


|  ATR | TYPE     | DESCRIPTION |
|--------------------|----------|-----------------|
|maxSpeed|final int|velocidad maxima del coche|
|id|Static int|contador para generar una matricula aleatoria|
|mark|String|marca de coche|
|model|String|modelo de coche|
|speed|int|velocidad de coche|
|distance|int| distancia recorrida|


----
* ATRIBUTES
----

```JAVA

// ===================================================//
//  =   Class to make cars
//  =   you can define the properties of a car
//  =   mark, model and autogenerate a id of them
// ===================================================//


    // max sped is 5 
    private final int MAXSPEED = 5;
    // String to identificate a car
    static int id;
    // Company make the car
    private String mark;
    // name of the model of a car
    private String model;
    // quantity of units the car move 
    private int speed;
    // units the car move
    private int distance;


```
----
* BUILDER
----

```JAVA
    Car(String mark, String model) {
          this.mark = mark;
          this.model = model;
          this.id = id++;
        }

```