## Archivos base

- El archivo `Solution.java` contiene el código solución al problema. 
- El archivo `input.in` contiene los 100 casos de entrada al problema. 
- El archivo `solution.out` contiene la respuesta a los 100 casos en `input.in`.
- El archivo `output.out` contiene la salida después de ejecutar el código solución con los casos de `input.in` (nótese que `input.in` no cambia, mientras que `output.out` dependerá de la solución que se está evaluando). 

## Archivos de evaluación
- El archivo `Generator.java` contiene el código encargado de generar los casos y sus respectivas soluciones asegurándose que estos cumplan con los parámetros del problema ($$n < 10^4$$). 
- El archivo `Checker.java` se encarga de comparar las respuestas en `output.out` con las soluciones de `solution.out`. Si el código es correcto, el puntaje impreso debería ser 100/100.
- El archivo `Runner.java` se encarga de hacer *benchmarking* al código solución que acertó a los 100 casos e imprimir los resultados al archivo `times.out`. El método se apoya en la librería de Apache Commons Lang 3.9 para obtener mediciones precisas (en lo posible) de la ejecución. El código solución es ejecutado 100 veces y el tiempo final será el promedio de los 100 tiempos de ejecución para evitar tiempos posiblemente atípicos. Se hace registro también del tiempo mínimo y máximo. 