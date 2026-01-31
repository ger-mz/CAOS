## Decisiones tomadas
Decidi realizar la opcion 1 Proveedores y facturacion, para manejar estados utilice el patron de diseño de maquina de estados para manejar los diferentes estados del flujo del proceso
donde considere los estados, CREADO, EN_ESPERA, REVISION, PAGO, FINALIZADO, DEVOLUCION y CANCELACION  

Para permitir la visualizacion del avance del flujo implemente una pagina web que se puede encontrar en la ruta localhost:8080/index.html al ejecutar el programa.

Se utilizo java 17 para la ejecucion del programa

## Que deje fuera y porque
Se dejo fuera validaciones para evitar que una solicitud pueda ser modificada al mismo tiempo desde dos seciones por falta de tiempo

No se configuro variables de entorno y validacion de autenticacion y autorizacion para facilitar la visualizacion del programa ejecutandose

## Que haria diferente o ampliaria con mas tiempo
Agregaria docker para poder ejecutar facilmente el proyecto y asegurar la compativilidad con otros dispositivos

para ejecutar el programa recomiendo utilizar IntelliJ IDEA