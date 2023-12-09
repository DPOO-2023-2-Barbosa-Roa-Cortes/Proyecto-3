
Reflexión sobre el Proceso de Diseño en los Proyectos 1, 2 y 3

Introducción:

En los tres proyectos realizados, nuestro equipo se enfrentó a desafíos significativos y experimentó un proceso evolutivo en términos de diseño y desarrollo de software. Esta reflexión busca analizar los aspectos positivos, las decisiones acertadas, los problemas encontrados y las lecciones aprendidas durante el ciclo de los proyectos.

Proyecto 1 - Sistema de Cuentas y Tarjetas de Crédito:

Aspectos Positivos:

Utilización de estructuras de datos eficientes: La implementación de clases anidadas y estructuras de datos bien organizadas facilitó la manipulación de cuentas y tarjetas de crédito de manera clara y eficiente.
Uso de bibliotecas de manejo de fechas: La inclusión de clases como LocalDate
facilitó la gestión de fechas y tiempos, mejorando la precisión y legibilidad del código.

Decisiones Acertadas:

Separación de responsabilidades: La creación de clases específicas para diferentes tipos de cuentas y tarjetas de crédito permitió una gestión más modular y extensible del sistema.
Implementación de métodos parse: La inclusión de métodos estáticos parse en las clases facilitó la conversión de datos desde y hacia cadenas, mejorando la interoperabilidad del sistema.
Problemas Encontrados:

Problemas con la gestión de contraseñas: La validación de contraseñas mediante la comparación directa con el hash podría ser mejorable en términos de seguridad.
Complejidad en la creación de subclases dinámicamente: La generación de subclases de CreditCard mediante reflexión podría llevar a problemas de mantenimiento y complicar la comprensión del código.
Proyecto 2 - Base de Datos de la Compañía:

Aspectos Positivos:

Uso de Singleton para la base de datos: La implementación de un patrón Singleton permitió la creación de una única instancia de la base de datos, garantizando la consistencia de los datos en toda la aplicación.
Carga eficiente de datos: La carga de datos desde archivos CSV y su conversión a objetos Java demostró ser una estrategia efectiva para inicializar la base de datos.
Decisiones Acertadas:

Validación de contraseñas en el registro: La incorporación de la función checkPassword ayudó a mejorar la seguridad de las contraseñas de los usuarios.
Separación de responsabilidades: La división de la base de datos en métodos especializados facilitó la carga y actualización de datos, mejorando la mantenibilidad del sistema.
Problemas Encontrados:

Problemas con la actualización de datos en tiempo real: La actualización manual de datos al realizar operaciones podría llevar a inconsistencias si no se actualizan todas las instancias necesarias.
Proyecto 3 - Sistema de Alquiler de Vehículos:

Aspectos Positivos:

Uso de Enums para categorías: La introducción de Enums para las categorías de vehículos simplificó la gestión y ampliación del sistema de alquiler.
Utilización de Optional en la clase CreditCard: El uso de Optional en la clase CreditCard mejora la manejo de casos donde el parsing puede fallar.
Decisiones Acertadas:

Modularidad en la búsqueda de vehículos: La separación de la lógica de búsqueda de vehículos facilitó la extensión del sistema para diferentes tipos de vehículos y categorías.
Integración de lógica de pagos: La inclusión de la lógica de pagos dentro de la clase CreditCard simplificó las operaciones de reserva y pago.
Problemas Encontrados:

Desafíos en la gestión de fechas y tiempos: La manipulación de fechas para la disponibilidad de vehículos y cálculos de precios podría ser susceptible a errores.
Problemas potenciales con la serialización de clases: La persistencia de datos mediante la serialización podría enfrentar problemas en futuras actualizaciones del sistema.
Conclusiones:

En retrospectiva, cada proyecto proporcionó valiosas lecciones sobre diseño de software, modularidad y gestión de datos. Aprendimos la importancia de la planificación cuidadosa, la validación de datos y la necesidad de ajustar el diseño en función de las necesidades cambiantes del proyecto. La reflexión continua sobre nuestro propio trabajo nos permitirá mejorar constantemente y aplicar las lecciones aprendidas en futuros proyectos.