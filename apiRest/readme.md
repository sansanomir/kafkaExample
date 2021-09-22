La info de las llamadas se almacena en la colección api_call de MongoDB

La base de datos se puede inicializar así en sistemas Linux
> $mongod

Yo le he tenido que dar permiso al directorio desde Ubuntu
> sudo chown -R $USER /data/db


En los servicios se devuelve un jSON con la información de: fecha y hora, datos, código de respuesta y estado.

Para las llamadas de los servicios en general, si la ruta no existe o tenemos alguna excepción, se devuelve una respuesta con la información de que la ruta no existe(404) o un error 500(error interno)

Si en un servicio no se encuentran datos en la base de datos también se devuelve un 404

Llamada a coche que existe en determinada fecha -> Respuesta con código 200
>http://localhost:8080/coches/2/fecha/2020-06-01


Llamada de un ítem que no existe -> Respuesta con código 404
>http://localhost:8080/coches/filter?id=12


Llamada a un ítem que sí que existe -> Respuesta con código 200
>http://localhost:8080/coches/filter?nombre_modelo=Corolla
>Respuesta con código 200

Llamada a un servicio que no existe -> Respuesta con código 404
>http://localhost:8080/coches/feee


Llamada a la exportación en excel -> Respuesta con código 200
>http://localhost:8080/excel

El excel se genera en el directorio del proyecto

Los tests unitarios no he conseguido que funcionen. la idea era llamar al api, y hacer mocks de los objetos de Servicio

