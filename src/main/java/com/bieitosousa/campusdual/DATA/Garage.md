| Nombre  | ruta |
| ------ | ------ |
|All clases |[ Ver Todas las Clases](../../../../../../../class.md)|
|Portada |[ Volver a la portada ](../../../../../../../README.md)|


## GARAGE.CLASS


|  CLASS    | DESCRIPTION |
|-----------------|-----------------|
|Car| Define un coche|

|  ATR | TYPE     | DESCRIPTION |
|--------------------|----------|-----------------|
|name |String |nombre de la escuderia |
|listGCar |ArrayList< Car >|lista de coches del garaje |



|  Metodo  | INPUT | OUPUT | DESCRIPTION |
|------------------|---------|----------|----------------|
|addCar(Car)|Car.class||Añade un coche al garaje|
|deleteCar(Car)|Car.class||Elimina un coche del garaje|
|getOneCar()||ArrayList<Car>|devuelve un coche aleatorio dentro del garaje|
|getAllCar()||ArrayList<Car>| devuelve todos los coches del garaje|
|HelpsRandomNumber|int , int|int| devuelve un numero aleatorio entre un nº incial y nº final|
|ImportCars|||Importa los coches de un fichero|

----
* ATRIBUTES
----


```JAVA

// ===================================================//
//  =   Class to house cars 
//  =   you can add, delete and selecct a car of him
// ===================================================//

    String name; //name of the garage
    // List of cars
    ArrayList<Car> listGCar = new ArrayList<>();
```
----
* BUILDER
----

```JAVA
    
    public Garage(String name) {
        this.name = name;
    }

```

----

* METHOD

----


```JAVA

  // add a car to the garage
    public void addCar(Car c) {
        try {
            listGCar.add(c);
        } catch (Exception e) {
            System.err.println(" not add the car" + c.id);
        }
    }

    // delete a car on the garage
    public void deleteCar(Car c) {
        try {
            listGCar.remove(c);
        } catch (Exception e) {
            System.err.println(" not remove the car" + c.id);
        }

    }
    
    // return the list of cars
    public ArrayList<Car> selectAllCar() {
        return listGCar;

    }

    // return one random car on this garage
    public ArrayList<Car> selecctOneCar() {
        ArrayList<Car> auxArry = new ArrayList<>();
        auxArry.add(listGCar.get(HelpsRandomNumber(0, listGCar.size())));
        return auxArry;
    }

    // take a ramdom number betwin two numbers
    public int HelpsRandomNumber(int inicio, int fin) {
        return (int) (Math.random() * fin + inicio);
    }

    //import a list of cars in a file
    public void ImportCars() {
        List<Car> importlistCar = CargarFileCar(new File(".//cars.json"));
        for (Car c : importlistCar) {
            listGCar.add(c);
        }
    }

    @Override
    public String toString() {
        return "Garage_"+name+ " : {" + listGCar + "}";
    }

```