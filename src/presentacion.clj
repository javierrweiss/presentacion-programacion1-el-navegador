(ns presentacion
  {:nextjournal.clerk/visibility {:code :hide}}
  (:require [nextjournal.clerk :as clerk]
            [nextjournal.clerk-slideshow :as slideshow]
            [clojure.java.io :as io])
  (:import (javax.imageio ImageIO)))

^{:nextjournal.clerk/visibility {:result :hide}}
(clerk/add-viewers! [slideshow/viewer])


;; # El internet y la Web 🌐
;; ## ¿Qué es el internet? 📶
;; El internet es una infraestructura compleja que involucra los siguientes componentes:
;; 1. **Redes**: un conjunto de computadores o dispositivos electrónicos conectados a un mismo *router* (enrutador)
;; 2. **Redes de redes**: un conjunto de computadores o dispositivos electrónicos puede conectarse con otros conectando sus enrutadores.
;; 3. **Infraestructura de telecomunicaciones**: satélites, fibra óptica, redes inalámbricas (WiFi, 4G, 5G) y en algunos casos redes telefónicas. 
;; 4. **Protocolos y estándares**: La comunicación a través del internet está regida por protocolos como el TCP/IP 
;;      (*Transfer Control Protocol/ Internet Protocol*).
;; 5. **ISP (Internet Service Providers)**: las redes de enrutadores están a su vez conectadas a otra
;;      red de redes, a saber, los ISP. Los ISP son enrutadores especiales propiedad de compañías
;;      proveedoras de servicios de internet. Todos ellos están interconectados, haciendo posible
;;      recuperar información de cualquier parte del mundo.

;; Existen distintos servicios que se basan en el internet, entre ellos, la Web es el más conocido.
;; Pero también existen: 
;; * El servicio de correo electrónico a través del protocolo SMTP (*Simple Mail 
;; Transfer Protocol*). 
;; * El IRC (*Internet Relay Chat*) para intercambiar mensajes de texto 
;; * El VoIP (*Voice over IP*) el cual utilizan muchas plataformas de videollamadas (e.g. Zoom, Discord, Google
;;  Meet, entre otras) 
;; * El IoT (*Internet of Things*) 
;; * y la Computación en la nube (*Cloud Computing*).


;;---
(ImageIO/read (io/input-stream "resources/w3c-logo-small.png"))
;; ## ¿Qué es la Web? 
;; La Web es un servicio que corre sobre el internet y consiste en una gigantesca red de información
;; conformada por computadores intercambiando información a través de ciertos protocolos. A los computadores 
;; que proveen información les llamamos *servidores*, mientras que aquellos que la consumen
;; los llamamos *clientes* (por supuesto, nada descarta que un computador pueda ser ora servidor y ora
;; cliente).

;; Cada computador o dispositivo electrónico en esta gran red debe tener una identidad para poder ubicarla y reconocerla. Esta es la dirección IP
;; o *Internet Protocol Address*. Y los recursos que se comparten en la Web también deben tener una identidad
;; única para poder encontrarlos; y acá tenemos varios conceptos relacionados, como por ejemplo, URL (*Universal Resource Locator*), URI (*Universal Resource Identifier*) y IRI (*Internationalized Resource Identifier*), este último un elemento clave en la llamada *Web Semántica*.   


;; La Web se basa sobre estándares y protocolos que son definidos por la W3C (*World Wide Web Consortium*).
;; Entre esos protocolos, uno de los más prominentes cuando usamos la Web es el HTTP (*Hypertext Transfer Protocol*) o
;; HTTPS (*Hypertext Transfer Protocol Secure*) para mayor seguridad.

;; Pero antes de hablar de ello, hagámonos la siguiente pregunta  

^{:nextjournal.clerk/visibility {:result :hide}}
(def mermaid-viewer
  {:transform-fn clerk/mark-presented
   :render-fn '(fn [value]
                 (when value
                   [nextjournal.clerk.render/with-d3-require {:package ["mermaid@8.14/dist/mermaid.js"]}
                    (fn [mermaid]
                      [:div.grid.grid-col-2.place-content-center
                       {:ref (fn [el] (when el
                                        (.render mermaid (str (gensym)) value #(set! (.-innerHTML el) %))))}])]))})

;;---
;; ## ¿Cómo funciona el navegador? 🔎
;; Un navegador es una herramienta ubicua en nuestro día a día y es una de las piezas de ingeniería más extraordinarias.
;; Su complejidad es equiparable a la de un sistema operativo; un navegador como Google Chrome, por ejemplo, puede llegar
;; a tener más de 2 millones de líneas de código. 

;; Un navegador tiene al menos 4 grandes componentes:
;; 1. Interfaz gráfica
;; 2. Motor del navegador
;; 3. Capa de datos
;; 4. Red

(clerk/with-viewer mermaid-viewer
  "graph
    ui[Interfaz gráfica] --> motor[Motor del navegador]
    motor --> data[Persistencia de datos]
    motor --> re[Motor de renderizado]
    re --> red[Red]
    re --> js[Intérprete/compilador de JavaScript]")


;;---
;; ## La interfaz gráfica 🗔
;; Esta es la parte del navegador que se encarga de mostrar la información al usuario. Es la parte del navegador con la que 
;; interactuas a diario. 

;; Acá vemos los controles para avanzar o retroceder, ir al home, recargar, favoritos, configuración, barra de direcciones, etc.
;; Y, por supuesto, el contenido de la página que has solicitado.
(ImageIO/read (io/input-stream "resources/navegador.png"))

;; ---
;; ## La red o networking 🖧
;; Todo empieza con el usuario introduciendo la URL (Universal Resource Locator).
;; Una URL está dividida en tres partes:
;; 1. El protocolo *https://* ó *http://*
;; 2. El nombre del dominio (e.g. www.google.com)
;; 3. Y la ruta */documento/cualquiera/de-ejemplo.html*

;; Con esta información **el browser debe identificar con qué computador ha de comunicarse para obtener la información de la página**. 
;; Básicamente, para ello debe traducir el nombre de dominio en una dirección IP (*Internet Protocol Address*).

;; Luego hace una solicitud al Sistema Operativo a través de un mecanismo conocido como IPC (*InterProcess Communication*), que busca en el 
;; sistema de archivos si ya se encuentra registrada esa dirección (*/etc/hosts* en Linux) y, si no es así, envía una solicitud a un servidor de DNS 
;; (*Domain Name Service*), el cual en realidad es una red de servidores, que regresarán la IP asociada con ese nombre de dominio. 

;; **Una vez que tiene la dirección IP debe, establecer una conexión con ese computador**. Esto se realiza mediante el protocolo TCP (*Transfer Control Protocol*),
;; el cual se encarga de gestionar el envío y transmisión de la información a través de paquetes. 
;; Este protocolo involucra un three-way handshake: *sync*, *sync-ack*, *ack* ó synchronize, synchronize-acknowledge, acknowledge. 

;; **Una vez establecida esa conexión se envía una solicitud a través del protocolo HTTP (Hyper Text Transfer Protocol)**.
;; Hay que tener en cuenta que es muy común que una página tenga referencias a muchas otras. Todo este proceso se repite por cada URL que encuentra el navegador.

;; De esta forma a través de solicitud HTTP hecha con el método **GET** (ya hablaremos sobre esto), nuestro navegador obtiene los paquetes necesarios que le van a permitir mostrarnos la 
;; información que hemos solicitado. 


;;---
;; ## Motores de navegación 🚢
;; El motor de navegación, como su nombre lo sugiere, es el corazón del navegador; es el que hace parte esencial del trabajo.

;; Se encarga de gestionar las interacciones con el usuario (e.g. hacer que el click del usuario gatille las acciones apropiadas),
;; coordina los flujos de información entre la interfaz del usuario, el motor de renderización y otros componentes.

;; Los más utilizados son Gecko (Mozilla), Blink (Chrome) y Webkit.

;;---
;; ## Motores de renderización 🎨

;; El motor de renderización es el encargado de mostrar el contenido de las páginas web. 
;; Su labor es una de las más complejas. Pero antes de profundizar sobre su labor, debemos
;; familiarizarnos con algunos conceptos. 

;; Las páginas web están escritas en una mezcla de HTML, CSS y JavaScript.

;; ### HTML y CSS

;; HTML (*HyperText Markup Language*) es un **lenguaje de marcado** estandarizado por la W3C. En otras palabras, se trata de un
;; conjunto de símbolos y convenciones basados en la noción de una **etiqueta** que permiten determinar, 
;; no sólo el texto a presentarse en pantalla, sino también el tipo,
;; tamaño y color de la fuente, su ubicación dentro del conjunto
;; (título, subtítulo, lista, párrafo, etc.); permite además insertar imágenes, videos, animaciones. 

;; Aunque el HTML admite etiquetas de estilo, éstas, por lo general, se ubican en un archivo que sigue
;; otra notación diferente. Se trata del CSS (*Cascading Style Sheet*). En este lenguaje se manejan exclusivamente
;; los elementos estéticos de la página, mientras que el HTML se encarga de la diagramación y formato. 


;; ---
;; ### JavaScript
;; JavaScript es un **lenguaje de programación** diseñado especialmente para la Web. 
;; Un lenguaje de programación es un conjunto limitado de símbolos regidos por un
;; conjunto de reglas que prescriben cómo pueden formarse sentencias válidas (sintaxis)
;; y cómo pueden utilizarse esos símbolos para generar nuevos elementos en ese lenguaje.
;; Sin entrar en detalles en este momento, un lenguaje de programación es capaz de comunicarse
;; con el computador y hacer que éste ejecute las instrucciones expresadas en el mismo.

;; #### ¿Por qué JavaScript es un lenguaje diseñado para la Web? 

;; Pues, básicamente porque se pensó en 
;; crear un lenguaje más asequible y fácil de aprender, que fuese más flexible en sus reglas que los lenguajes corrientes del momento y que
;; fuese dinámico.

;; #### ¿Qué le aporta JavaScript al HTML y CSS?

;; Con JavaScript puedes añadir, sustraer ó modificar dinámicamente cualquier elemento de la página web 
;; (texto, estilo, ubicación, lo que quieras).

;; ---
;; ## ¿Qué hace entonces un motor de renderización?
;; El motor de renderización toma los datos de la página web (es decir, todo el HTML, CSS y JavaScript)
;; como bytes, esto es, como unos y ceros y luego: 
;; 1. Paralelamente, toma por un lado el HTML y por otro el CSS y el JavaScript. 
;; 2. Decodifica el HTML según una codificación de caracteres (UTF-8, es la más común), pero esto depende 
;; si el navegador está configurado para desplegar caracteres en cirílico, chino mandarín, árabe, coreano,
;; etc. Por eso es importante determinar correctamente el **charset** o codificación de caracteres. 
;;     * Se aplica un tokenizador que extrae las palabras clave del texto (e.g. h1, html, br, etc.). Este
;; proceso se denomina *parsear*.
;;     * Cada token se convierte en un objeto con metainformación sobre el mismo (tag, title, data, etc.)
;; y se crea un *parse tree*. Y es que, como vemos, estamos tratando con estructuras arbóreas, un tipo de grafo muy común.
;;     * Todos estos elementos conforma el *Document Object Model* (DOM). El DOM, por cierto, es también un estándar de la W3C.
;; 3. De igual modo que con el HTML, también en el caso del CSS se leen los bytes crudos y se decodifican.
;;     * Se aplica un tokenizador que extrae las palabras clave del texto (width, height, rem, background, etc.) incluyendo las de HTML (h1, html).
;;     * Todos estos objetos se encuentran en una relación de jerarquía y se crea así el *CCS Object Model* (CSSOM).
;; 4. Compila el código JavaScript que encuentra. Para esto llama al componente encargado de esta tarea.
;; 5. Se crea un *Render Tree* o *Frame Tree*
;;     * Básicamente, se toma el DOM y el CSSOM y se los transmite al motor de navegación, el cual decide cómo mostrarlos en pantalla.
;;     Un proceso que se llama *paiting*. 

(clerk/with-viewer mermaid-viewer
  "flowchart LR
    html>HTML] --> p1[Parseador de HTML]
    css>Hojas de estilo] --> p2[Parseador de CSS]
   p1 --> dom[Árbol DOM]
   p2 --> cssd[Reglas de Estilo]
   dom --> c[Se unifican HTML y CSS]
   cssd --> c
   c --> rt[Render Tree]
   rt --> pt[Pintar ó Painting]
   pt --> cnt[Mostrar contenido]")

;; ---
;; ## Comprendiendo el DOM
;; Con la finalidad de que un lenguaje de programación pueda interactuar con los contenidos representados por el HTML y con los eventos producto de la interacción del usuario 
;; con el mismo, la W3C creó una especificación denominada *Document Object Model*. Se trata una representación agnóstica
;; (es decir que pretende ser neutral frente a los distintos lenguajes y plataformas) del documento HTML como un árbol de objetos.

;; Concretamente, el DOM HTML define:
;; 1. a los elementos de HTML como **objetos**
;; 2. las **propiedades** de los elementos HTML
;; 3. los **métodos** para acceder a los elementos HTML
;; 4. los **eventos** para todos los elementos HTML. 

;; ---
;; ## Fuentes
;; 1. https://web.dev/articles/howbrowserswork?hl=es-419#preface
;; 2. https://www.dailyfrontend.com/articles/browsers-and-how-they-work
;; 3. https://www.browserstack.com/guide/what-is-browser
;; 4. https://developer.mozilla.org/en-US/docs/Web/Performance/How_browsers_work
;; 5. https://www.youtube.com/watch?v=OFIvyc1y1ws&list=PLNYkxOF6rcICgS7eFJrGDhMBwWtdTgzpx&index=5
;; 6. https://dom.spec.whatwg.org/
;; 7. https://www.w3schools.com/js/js_htmldom.asp
