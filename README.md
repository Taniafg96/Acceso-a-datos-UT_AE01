# Acceso-a-datos-UT_AE01
Actividad tema 4 de acceso a datos.

# HERRAMIENTAS:

  · IDE -> Netbeans
  
  · DATABASE -> PostgresSQL


# ENUNCIADO:

Una agencia de viajes necesita una base de datos objeto-relacional, que implementaremos con PostGreSQL. El director de la agencia nos indica que hay que almacenar la siguiente información:

  · Del cliente,necesitamos el nombre, el DNI, los teléfonos y la dirección, así como el paquete vacacional que haya contratado.
  
  · Del paquete vacacional necesitamos conocer las fechas de inicio y de fin, qué alojamiento incluye y qué vuelos de ida y vuelta incluye (aunque podría no incluir vuelos).
  
  · Del alojamiento necesitamos el nombre del establecimiento, su dirección, qué tipo de establecimiento es (hotel, hostal, apartamento) y qué categoría tiene (1, 2, 3, 4 o 5).
  
  · Por último, de los vuelos necesitamos saber su aeropuerto de origen, su aeropuerto de destino, los aeropuertos en los que hace escala, la compañía y el número de vuelo.


Deberás realizar los siguientes apartados:

  1.- Crear a través de una aplicación java con jdbc la base de datos OR en postgressql  y que incluya como mínimo un tipo estructurado, un tipo array, un tipo enumerado y una función.
  
  2.- La aplicación instará como mínimo 5 registros de las tabas/estructuras creadas.
  
  3.- La función tendrá como mínimo un parámetro de tipo estructurado y devolverá un "varchar" con la información compuesta de la combinación de varios de sus campos. p.e. "Manolo viajó el 01/02/2022 y se alojó en el hotel ventura".
  
  4.- La aplicación ejecutará una consulta que presente el resultado de la función.


Indicaciones:

  1.- Elaborar el esquema E-R como si fuéramos a diseñar una base de datos relacional (a fin de cuentas el esquema E-R vale para cualquier BD). Una vez estén todos los elementos incorporados pasamos al siguiente paso.
 
  2.- Identificar qué elementos han de ser tratados como una tabla, por ser necesario su adecuado mantenimiento,  y cuáles pueden permanecer como columna compuesta de un tipo estructurado. Una vez estemos seguros, procedemos a crear la base de datos.

