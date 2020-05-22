
Control RACE
--------------------
##### Breve explicación

----

Los archivos de configuración están en la carpeta prívate_data el único indispensable es :

​	".\private_data\private_conf\cars.json"	--> 	para cargar los coches desde el archivo.

Las importaciones y exportaciones se gurdan en  --> 	".\private_data"

Salida de resultado por defecto : Imprimir resultados en ficheros    --> 	".\private_public" 

Se puede cambia a  "imprimir resultados  por consola" en el menú principal o editando  Utilss.pConsole

Se puede importar y exportar directamente todos los datos desde el menu principal

------------------------------------------------

###### Observaciones 

---------------------------

Por falta de  tiempo no he empleado la encasulacion en muchos casos y accedo directamente a variables en ved a sus métodos get/set

**Race.class Tornament.class**

 En el uso de las variables Race.particG , Tornament.tornResultC

No se debería hacer un ".clear()" en diversas ocasiones, esta "parcheado". Lo ideal es que no tuvieran 	datos sin ser forzadas a ello, al menos si no se ejecuta el mismo Torneo o carrera por segunda vez.

**Json**

La manera de construir Race_Elimination y Race_Standar creo que me lie y la hice demasiado tediosa.

**Controler** 

En los métodos para el menú, la construcción de  métodos [update * add *] creo que no lo hice de manera clara y sencilla , resulta sucio y tediosa de entender y provoca errores.

**.Clone()**

El uso de clone para el diseño, dudo si es necesario por como diseñe la aplicación para que no de errores, o es una manera de "parchear" para que el programa haga lo que quiero que haga y no lo que esta haciendo.

-----------------------------

**Opinión**

-------------------------------

Un poco de mejor o peor marea las decisiones que he tomado a parte de , una arquitectura demasiado fijada en crear Race_Estandar y Race_Elimination y no resolverlo con Race es lo comentado en las Observaciones que un poco creo que son malas interpretaciones o malas ideas que no limpie lo sufiente y acaban provocando "parcheados" o malas praxis. 

De todas maneras, tengo que aparcar la app Race y meterme estos días a ver Videos y ponerme al dia.

soy consciente que dedicando mas tiempo mejoraría la parte de menús y la funcionalidad, pero no quiero atrasarme demasiado con el contenido visual. Si eso pruébala y me comentas si aparte de los errores que e detectado yo mismo hay algún tema que debería ser consciente y evitar fallar con el proyecto al que me voy a enfrentar en breves .