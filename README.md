# sistema-de-valoracion-del-adulto-mayor
El sistema propuesto es una aplicación web institucional para el Departamento de Enfermería de la UAA que digitalice completamente el proceso de valoración clínica del adulto mayor.

Guía de navegacion:

- Carpeta prototipo: Aqui se encuentra el proyecto completo hecho en java, html, css, javascript, maven y Apache Tomcat. Para encontrar las pantallas dentro de esta carpeta tendrá que seguir el siguiente camino: /prototipo/mavenproject1/target/mavenproject1-1.0-SNAPSHOT. Estando dentro de esa carpeta deberá ver la siguiente estructura: 

    - META-INF
    - WEB-INF (Aqui se encuentra todo el backend de java y del servidor)
    - pages (Aqui se encuentran las pantallas importantes: PNT-02, PNT-03, PNT-04, etc)
        - admin (carpeta donde se encuentran todas las vistas del administrador)
            -usuarios.html (PNT-06) ABCC para los usuarios del sistema.
        - avisoConfidencialidad.html (PNT-03) 
        - dashboard-estudiante.html (PNT-02) vista de estudiante de sus valoraciones.
        - dashboard-maestro.html (PNT-05) vista de maestro de todas las valoraciones.
        - formularioValoracion.html (PNT-04) Formulario de valoración del adulto mayor completo dividido por secciones.
    - scripts (javascript utilizado de intermediario entre el backend y el frontend asi como herramientas de navegacion, firma, etc).
    - styles (estilos que utiliza toda la pagina).
    - index.html (PNT-01 Login)

- Carpeta minutas: Minutas de entrevistas de identificacion de requerimientos y de validación.

- Carpeta documentación: Matriz de requerimientos, trazabilidad 

- Carpeta diagramas: Diagrama AS-IS, Diagrama TO-BE y Modelo Entidad Relación

