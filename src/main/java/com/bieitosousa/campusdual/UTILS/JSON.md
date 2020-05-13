| Nombre  | ruta |
| ------ | ------ |
|All clases |[ Ver Todas las Clases](../../../../../../../class.md)|
|Portada |[ Volver a la portada ](../../../../../../../README.md)|

----
* WRITE A OBJECT IN A FILE
----

```JAVA
public static boolean WriteObjJsonInFile(File objectFile, Object obj) {
        Gson gson = new Gson();

        // Java objects to String
        // String json = gson.toJson(staff);
        // Java objects to File
        try (FileWriter writer = new FileWriter(objectFile)) {
            gson.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
        // System.out.println(gson.toJson(obj));
    }



```
----
* READ A OBJECT IN A FILE
----

```JAVA
 public static Object ReadObjJsonInFile(File objectFile) {
        Gson gson = new Gson();
        Object obj = null;
        try (Reader reader = new FileReader(objectFile)) {
            // Convert JSON File to Java Object
            obj = gson.fromJson(reader, Object.class);
            // print staff object
            System.out.println(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

```
----
* READ A ARRAY IN A FILE 
----

```JAVA

 public static  List<Car> CargarFileCar(File f)  {
        Gson gson = new Gson();
       
        List<Car> carList = null;
        BufferedReader in;
         CarsList objList= null;
        try (Reader reader = (in = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8")))) {
            objList = gson.fromJson(reader, CarsList.class);
            if (objList.carList != null  || objList.carList.size() > 17) {
          throw new Exception("No se han cargado correctamente "+f.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objList.carList ;
    }
    public class CarsList {
    private List<Car> carList = null;
}
}
```
----
* EJEMPLO DE ARRAYLIST  
----

```JAVA

data class Player(val name : String, val surname: String)

val json = [
  {
    "name": "name 1",
    "surname": "surname 1"
  },
  {
    "name": "name 2",
    "surname": "surname 2"
  },
  {
    "name": "name 3",
    "surname": "surname 3"
  }
]

val typeToken = object : TypeToken<List<Player>>() {}.type
val playerArray = Gson().fromJson<List<Player>>(json, typeToken)
OR

val playerArray = Gson().fromJson(json, Array<Player>::class.java)


```



----
* WRITE A ARRAY IN A FILE 
----

```JAVA


```
