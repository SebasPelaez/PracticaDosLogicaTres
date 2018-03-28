# **WordTransform**

Un juego muy interesante que se encuentra en algunas revistas y periódicos es el transformador de palabras. Al tomar una palabra de inicio
y sucesivamente alterar una sola letra para formar una nueva palabra, se puede construir una secuencia de palabras que cambie la palabra
original a una palabra final dada. Por ejemplo, la palabra “cama” puede transformarse en tres pasos a la palabra “cero” de acuerdo con la
siguiente secuencia: cama, cara, caro, cero. 
Cada palabra se diferencia de la anterior en una única letra en una posición específica.

El proyecto aquí desarrollado, usando un diccionario de palabras, además de las palabras inicial y final, permite determinar la menor
cantidad de pasos posibles para realizar la transformación.

Para esto, el programa
* Lee un archivo de texto (.txt) que contiene un diccionario de palabras separadas por saltos de línea (_una palabra por renglón_).
* Construye un grafo que represente el diccionario de palabras.
* Muestra todas las secuencias posibles para llegar de la palabra inicial a la palabra final.
* Determina la secuencia con la menor cantidad de pasos posibles para realizar la transformación.
