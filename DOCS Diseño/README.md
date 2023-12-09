## Decisiones de diseño
- Se ha definido la mayoría de los atributos como protegidos para permitir su edición dentro del mismo paquete. Los métodos se han declarado principalmente como públicos o, en casos especiales, como privados para facilitar la comunicación entre paquetes.
- Se ha separado la gestión de cuentas de clientes de las tarjetas de crédito para simplificar la implementación de los pagos.
- La información sensible se maneja y valida utilizando hash mediante la función integrada de la clase Objects. En caso de corrupción de hashes, se recomienda utilizar la clase HashedDataFixer para resolver estos problemas.
- Los precios se han definido como estáticos independientemente del número de días para simplificar la implementación.

## Comentarios sobre el uso de la aplicación
- El flujo de inicio de sesión y registro de cuentas sigue un enfoque estándar, donde el programa emite alertas en caso de errores para detener la ejecución y permitir reintentos sin perder progreso.
- Existen cuentas de clientes predefinidas en el archivo de cuentas sin hash, por lo que se puede utilizar cualquiera de estas. Las tarjetas de crédito también están predefinidas en el mismo archivo para su uso.

## Comentarios sobre las pruebas unitarias
- Se han realizado pruebas sobre los casos más comunes de error, centrándose en reservas y carga de datos.
- En las pruebas "JUnitTest", se observa que dos métodos fallan constantemente. Esto se debe a que cuando un carro es reservado, se actualiza inmediatamente, lo que provoca que estas pruebas siempre fallen.

## Información adicional
- Se han proporcionado dos imágenes, "CORE UML" y "GUI UML", que muestran la estructura tanto del núcleo como de la interfaz gráfica del proyecto.
