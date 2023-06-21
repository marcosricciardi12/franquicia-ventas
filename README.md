# Proyecto `franquicia-ventas`
Proyecto franquicia de comidas rápidas utilizando Spring
Servicio de ventas de una franquicia. El servicio hace consultas recurrentes cada un tiempo determinado a un servicio externo, del cual recibe como respuesta la acción a realizar.
Las acciones a realizar pueden ser: nada, actualizar el menú y crear un reporte.

## Actualizar menú
El servicio principal envía la lista de los menús, su disponibilidad e indica si se agregaron nuevos menús. Todo esto se debe guardar en la base de datos del servicio de ventas.
## Crear reporte
El servicio principal puede solicitar crear un reporte de las ventas realizadas, donde el reporte puede ser histórico, incluyendo todas las ventas realizadas. puede ser un reporte de tipo recurrente, con una fecha de inicio y una fecha de fin de ejecución, y cada cuanto tiempo se debe enviar dicho reporte.
Para crear dichos reporte, el servicio de ventas se comunica con el servidor de reportes.

## Nueva venta
El servicio realiza nuevas ventas utilizando la información de los menús recibidos desde el servidor principal. Una vez procesada la información de la nueva venta, esta es almacenada en la base datos incluyendo detalles como fecha, precio total, menús consumidos y la cantidad de cada uno.
