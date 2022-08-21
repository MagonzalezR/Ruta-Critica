# Ruta-Critica
##Programa que calcula la ruta critica de un grafo
El programa se hizo utilizando Apache Netbeans
##Funcionamiento
El programa requiere dibujar un grafo, para ello se deben marcar sus nodos en el canvas, estos aparecerán con un indicador único (del 1 al 19)

![imagen 1](https://github.com/MagonzalezR/Ruta-Critica/blob/main/img/img%20programa%201.png)

Una vez puestos los nodos en el grafo, se procede a generar la matriz de adyacencia de los nodos, dando click en el botón "Crear Matriz"
Aparecerá una tabla en la que las filas nos indicarán los nodos de partida y cada columna será un nodo de llegada; cada nodo puede tener hasta
4 nodos adyacentes. El formato que se usa para reconocer el nodo de llegada es en primer lugar el nodo destino, en segúndo el numero de la actividad
y en tercero el peso de dicha actividad (tener en cuenta que una vez terminada de llenar la matriz, se debe quitar el foco de la tabla para que esta
guarde el ultimo cambio realizado, si no, no se tomará el ultimo nodo ingresado)

![imagen 1](https://github.com/MagonzalezR/Ruta-Critica/blob/main/img/img%20programa%202.png)

Por último, se debe dar click en el botón "IniciarRuta" y este nos dará toda la información de la ruta crítica

![imagen 1](https://github.com/MagonzalezR/Ruta-Critica/blob/main/img/img%20programa%203.png)
