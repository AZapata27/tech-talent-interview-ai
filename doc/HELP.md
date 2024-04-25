# Proyecto Backend Arqueotipo Implementando Clean Architecture

## Arquitectura

<img alt="Clean Architecture" height="100%" src="https://miro.medium.com/v2/resize:fit:720/format:webp/0*pUhK2tTX-OSKSKtN.jpg" width="100%"/>

## Diagrama de paquetes

<img alt="Clean Architecture Packages" height="100%" src="https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png" width="100%"/>

## Domain
Este capa se debe mantener lo más limpio que se pueda, no depender de librerias externas, al ingresar a esta capa se debe dar prioridad a las conversiones de objetos en las implementaciones de los puertos de entradas
y puertos de salida, ya que el paquete domain solo conoce informacion referente al negocio, es decir, modelos o entidades (objetos) de negocio, puertos, casos de usos, excepciones o errores de acuerdo con el flujo que 
puede surgir al cumplir con las abstracciones de las tareas o historias de usuario en el código.

### Model
Es el modulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la logica y reglas del negocio mediante clases abstractas (interfaces, llamados puertos), modelos o entidades de dominio.
No confundir el termino entidad, con las entidades en JPA, la cual representa una tabla o el acceso a datos en una bd.

Al ser el núcleo de la arquitectura, este módulo no depende nada más que de sí mismo. Al tener que relacionarse con su entorno, se ve obligado a definir el comportamiento esperado de la infraestructura mediante 
interfaces y depender únicamente de estas. A esta abstracción le conoceremos como gateways (puertos) del dominio y son fundamentales para desacoplar la tecnología del dominio;

### Usecases

Este modulo perteneciente a la capa del dominio, implementa los casos de uso del sistema, define logica de la aplicacion y reacciona a las invocaciones desde el modulo de entry points por medio del puerto de los casos de usos (Interface), 
cumpliendo uno de los principios SOLID el cual es no depender de implementaciones si no de clases abstractas y además es el encargado de orquestar los flujos hacia el modulo del Model (modulo descrito anteriormente).

## Infrastructure
Es la capa donde encontraremos las implementacion de nuestros puertos en el cual encontraos 2 capas, la capa de entrada a nuestra aplicacion y la capa e salida de nuestra aplica, 
pero cada capa se define como un paquete y cada capa tiene sus modulos referente a si son de entrada o de salida, separando responsabilidades, esto nos permite conetarnos con sistemas externos 
y estas implementacios pueden usar dependencias correspondiente a las tecnologias requeridas, ejemplo jpa, spring web, entre otras.

### Driven Adapters

Los driven adapter perteneciente a la capa de infraestructura, representa la capa de salida, donde contraremos modulos que nos permiten enviar datos a sistemas externos, como lo son conexiones a bases de datos, 
lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos interactuar.

Si se requiere trabajar con nuevos modulos para alguna tecnologia diferente a jpa, considere usar el modulo new-module (cambiar el nombre de acuerdo a su uso o tecnologia) o creé uno copiando el empaquetado de new-module.
para configurar el modulo se debe modificar el pom principal (pom padre) del proyecto y agregar el nuevo modulo en el apartado de <module>, recuerde cambiar el nombre de su nuevo modulo 

            <module>infrastructure/driven-adapters/new-module</module>

en el pom del modulo application agrege la dependencia del nuevo modulo, el nombre del paquete o carpeta principal hace referencia al modulo, en el ejemplo es new-module

                            <dependency>
                                <groupId>co.com.flypass</groupId>
                                <artifactId>new-module</artifactId>
                                <version>1.0.0</version>
                            </dependency>

el new modulo debe apuntar al pom padre, observar como se encuentra la distribucion de paquetes en infreastructure, driven-adapter-new-module esta a niveles más interno al del pom padre 
debeido a eso se requiere agregar el relativePath y apuntar al pom del proyecto o pom padre donde se encuentra todos lo modulos de nuestra aplicacion 

                            <parent>
                                <groupId>co.com.flypass</groupId>
                                <artifactId>backend-arquetipo</artifactId>
                                <version>0.0.1-SNAPSHOT</version>
                                <relativePath>../../../pom.xml</relativePath>
                            </parent>

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio como lo son conexiones a servicios rest,
soap, consumidores de eventos, entre otras.

Si se requiere trabajar con nuevos modulos para alguna tecnologia diferente a spring-web, considere usar el modulo new-module (cambiar el nombre de acuerdo a su uso o tecnologia) o creé uno copiando el empaquetado de new-module.
para configurar el modulo se debe modificar el pom principal (pom padre) del proyecto y agregar el nuevo modulo en el apartado de <module>, recuerde cambiar el nombre de su nuevo modulo

            <module>infrastructure/entry-points/new-module</module>

en el pom del modulo application agrege la dependencia del nuevo modulo, el nombre del paquete o carpeta principal hace referencia al modulo, en el ejemplo es new-module

                            <dependency>
                                <groupId>co.com.flypass</groupId>
                                <artifactId>new-module</artifactId>
                                <version>1.0.0</version>
                            </dependency>

el new modulo debe apuntar al pom padre, observar como se encuentra la distribucion de paquetes en infreastructure, entry-points - new-module esta a niveles más interno al del pom padre
debeido a eso se requiere agregar el relativePath y apuntar al pom del proyecto o pom padre donde se encuentra todos lo modulos de nuestra aplicacion

                            <parent>
                                <groupId>co.com.flypass</groupId>
                                <artifactId>backend-arquetipo</artifactId>
                                <version>0.0.1-SNAPSHOT</version>
                                <relativePath>../../../pom.xml</relativePath>
                            </parent>

## Application

Este modulo es el capa más externa de la arquitectura, es el encargado de ensamblar los distintos modulos, resolver las dependencias y 
crear los beans de los casos de use (UseCases) o configuraciones adicionales. 
Además inicia la aplicación (es el unico modulo del proyecto donde encontraremos la función public static void main(String[] args).

Ya que es la capa para ensamblar los distintos modulos, se debe agregar las dependencias de todos los modulos para el funcionamiento del proyecto.


# Configurar nuevo proyecto con base a este arquetipo.

## Ajustar la modularizacion de acuerdo al nombre de tu proyecto.
### Cambia el nombre de la carpeta principal
Cambia el nombre de la carpeta principal del proyecto de acuerdo al nombre que le quieres dar a tu nuevo proyecto
recomendado desde el explorador de archivos.
usar guiones para separar palabrar al aplicar un nombre al proyecto

### cambiar el nombre del modulo principal
usar guiones para separar palabrar al aplicar un nombre al proyecto

Intellij permite realizar un refactor del modulo dando click derecho en la carpeta o modulo llamado backend-arquetipo, esa opcion 
te permite cambiar el nombre de tu modulo padre o princpal, esto se reflejara en todos los modulos hijos, en caso de inconvenientes, 
tendras que cambiar manualmente cada pom con referencia al artefactId principal. 
ejemplo
en el pom principal existe un apartado

                        <groupId>co.com.flypass</groupId>
                        <artifactId>backend-arquetipo</artifactId>
                        <version>0.0.1-SNAPSHOT</version>
                        <name>backend-arquetipo</name>
                        <description>backend-arquetipo</description>

Cambialo a de acuerdo a lo siguiente 

                        <groupId>co.com.flypass</groupId>
                        <artifactId>nuevo-nombre</artifactId>
                        <version>0.0.1-SNAPSHOT</version>
                        <name>nuevo-nombre</name>
                        <description>una breve descripcion del proyecto</description>

los modulos hijos tienen un apartado de acuerdo a lo siguiente

                        <parent>
                            <groupId>co.com.flypass</groupId>
                            <artifactId>backend-arquetipo</artifactId>
                            <version>0.0.1-SNAPSHOT</version>
                        </parent>

con el refactor de intellij debe cambiar a lo siguiente o si no debes hacerlo manual, para cada modulo
el artifactId debe ser el mismo nombre del artifactId que se especifico en el pom principal.

                        <parent>
                            <groupId>co.com.flypass</groupId>
                            <artifactId>nuevo-nombre</artifactId>
                            <version>0.0.1-SNAPSHOT</version>
                        </parent>

## propiedades o configuracion a tener en cuenta
En la capa de aplicacion están los recursos y las propiedades para el buen funcionamiento de nuestra aplicacion, como algunas configuraciones a nivel de bd
en la capa de infraestructure - jpa-repository hay una clase configuracion de jpa, 
para configurar tu conexion a la BD deseada, usa las variables de entorno definidas en el properties o archivo yml


recuerda cambiar los paquetes de acuerdo a las tecnologias que uses.

Cada modulo debe tener las dependencias necesarias para su funcionamiento, definiendo cada libreria o dependencia
en su pom correspondiente, para mantener las capas limpias de dependencias innecesarias.

el pom principal permite compartir dependencias entre todas las capas, por si se necesita una dependencia en comun para todo el proyecto.

Si considera Util, crear un modulo o usar el modulo transversal, para manejar operaciones o metodos reutilizables en diferentes modulos.
