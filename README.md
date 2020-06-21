# Prueba Técnica Android

Versión: 1.0

La prueba consistirá en la finalización de las tareas pendientes de una app para almacenar el historial de puntuaciones de una partida de bolos. Para ello se proporciona una documentación funcional de la aplicación y un listado de tareas pendientes. En el código también se podrán encontrar *TODOs* para facilitar éstas tareas.

NOTA: puedes existir errores intencionados que no estén especificados como tareas o marcados con *TODOs*.

# Funcional

### Launcher
Al inicio de la app se deberá realizar una sincronización de datos con los servidores externos para obtener los datos iniciales. Si ya tenemos esas partidas en local no se deberán modificar, descartaremos los cambios de remoto.

### Home
Pantalla principal de la app. Tiene 3 navegaciones:
- Continuar: Solo aparece si la última partida aún no ha terminado, al pulsar nos llevará a esa partida para continuar introduciendo puntuaciones.
- Nueva Partida: Crea una nueva partida. Si existe una partida sin terminar deberemos mostrar un dialogo indicando que la creación de una nueva partida elimina la otra partida existente sin terminar.
- Puntuaciones: Navegará a un listado con el historial de puntuaciones por partida.

### Puntuaciones
Listado con todo el historíal de puntuaciones de todas las partidas completadas.

[Puntuación en los bolos](http://www.fryes4fun.com/Bowling/scoring.htm)

### Partida
Pantalla para introducir y ver las tiradas de una partida. Cuando acabe la partida se ocultará el input y el botón.

# Ejercicios

1. Parsear el servicio de sincronización usando Retrofit + Gson. URL: [https://raw.githubusercontent.com/SDOSLabs/AndroidTestJson/master/db.json](https://raw.githubusercontent.com/SDOSLabs/AndroidTestJson/master/db.json)
2. Modelar la base de datos usando Room.
3. Home: Petición de datos en el viewmodel. NOTA: Es posible que requiera una transformación o cambiar código.
4. Home: Mostrar dialogo si al pulsar en nueva partida existe ya una sin finalizar advirtiendo que se va a borrar
5. Home: Navigación
6. Partida: Ajustar los layouts con las constraints correspondientes según diseño ( [Imagen](game.png) )
7. Partida: Funcionalidad completa para añadir una tirada.
8. Puntuaciones: El equipo de QA ha detectado algunos errores pero no nos han dicho nada más, revisar que todo vaya bien.
9. TEST: Verificar el correcto funcionamiento de RoundBoExtensions.addShot con los test necesarios.

# Puntuación
Como requisito para realizar la evaluación el proyecto deberá compilar. Cualquier error (Los TODO no cuentan) que provoque un cierre inesperado restará puntos.

| Sección  | Puntos |
|----------|--------|
| Datos remotos WS | 1.5 |
| Datos locales BD | 2 |
| Repositorio | 1 |
| Home Feature | 4 |
| Tests | 1.5 |
