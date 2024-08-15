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
;; 1. **Redes**: un conjunto de computadores conectados a un mismo *router* (enrutador)
;; 2. **Redes de redes**: un conjunto de computadores puede conectarse con otros conjuntos conectando sus enrutadores.
;; 3. **Módems**: los enrutadores, del mismo modo, están conectados a módems, ya que el internet
;;      depende de la infraestructura ya desarrollada por las redes telefónicas.
;; 4. **ISP (Internet Service Providers)**: las redes de enrutadores están a su vez conectadas a otra
;;      red de redes, a saber, los ISP. Los ISP son enrutadores especiales propiedad de compañías
;;      proveedoras de servicios de internet. Todos ellos están interconectados, haciendo posible
;;      recuperar información de cualquier parte del mundo.

;; Existen distintos servicios que se basan en el internet, entre ellos, la Web es el más conocido.
;; Pero también existe el servicio de correo electrónico a través del protocolo SMTP (*Simple Mail 
;; Transfer Protocol*), el IRC (*Internet Relay Chat*) para intercambiar mensajes de texto y el VoIP
;; (*Voice over IP*) el cual utilizan muchas plataformas de videollamadas (e.g. Zoom, Discord, Google
;;  Meet, entre otras).

 
;;---
(ImageIO/read (io/input-stream "resources/w3c-logo-small.png"))
;; ## ¿Qué es la Web? 
;; La Web es un servicio que corre sobre el internet y consiste en una gigantesca red de información
;; conformada por computadores intercambiando información a través de ciertos protocolos. A los computadores 
;; que proveen información les llamamos *servidores*, mientras que aquellos que la consumen
;; los llamamos *clientes* (por supuesto, nada descarta que un computador pueda ser ora servidor y ora
;; cliente).

;; Cada computador en esta gran red debe tener una identidad para poder ubicarla y reconocerla. Esta es la dirección IP
;; o *Internet Protocol Address*. Y los recursos que se comparten en la Web también deben tener una identidad
;; única para poder encontrarlos y acá tenemos varios conceptos relacionados, como por ejemplo, URL (*Universal Resource Locator*), URI (*Universal Resource Identifier*) y IRI (*Internationalized Resource Identifier*).  

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
;; ## ¿Cómo funciona el navegador (browser)? 🔎
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

;; HTML (*HyperText Markup Language*) es un lenguaje de marcado, en otras palabras, se trata de un
;; conjunto de símbolos y convenciones basados en la noción de una **etiqueta** que permiten determinar 
;; no sólo el texto a presentarse en pantalla sino también el tipo,
;; tamaño y color de la fuente, su ubicación dentro del conjunto
;; (título, subtítulo, lista, párrafo, etc.); permite además insertar imágenes, videos, animaciones. 
;; Aunque el HTML admite etiquetas de estilo, éstas, por lo general, se ubican en un archivo que sigue
;; otra notación diferente. Se trata del CSS (*Cascading Style Sheet*). En este lenguaje se manejan exclusivamente
;; los elementos estéticos de la página, mientras que el HTML se encarga de la diagramación y formato. 

;; ### JavaScript
;; JavaScript es un **lenguaje de programación** diseñado especialmente para la Web. 
;; Un lenguaje de programación es un conjunto limitado de símbolos regidos por un
;; conjunto de reglas que prescriben cómo pueden formarse sentencias válidas (sintaxis)
;; y cómo pueden utilizarse esos símbolos para generar nuevos elementos en ese lenguaje.
;; Sin entrar en detalles en este momento, un lenguaje de programación es capaz de comunicarse
;; con el computador y hacer que éste ejecute las instrucciones expresadas en el mismo.

;; #### ¿Por qué JavaScript es un lenguaje diseñado para la Web? 

;; Pues, básicamente porque se pensó en 
;; crear un lenguaje más asequible y fácil de aprender, que fuese más flexible en sus reglas y que
;; fuese dinámico.

;; #### ¿Qué le aporta JavaScript al HTML y CSS?

;; Con JavaScript puedes añadir, sustraero o modificar dinámicamente cualquier elemento de la página web 
;; (texto, estilo, ubicación, lo que quieras).
