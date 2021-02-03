# Inteligencia Computacional (IC)

Asignatura de Inteligencia Computacional (IC) - Máster Profesional en Ingeniería Informática 2020/2021

<details open="open">
  <summary>Tabla de contenidos</summary>
  <ol>
    <li>
      <a href="#teoria">Teoría</a>
    </li>
    <li>
      <a href="#practicas">Prácticas</a>
      <ul>
        <li><a href="#practica1">Redes neuronales: Reconocimiento óptico de caracteres (MNIST)</a></li>
          <ul>
            <li><a href="#practica1d">Documentación</a></li>
            <li><a href="#practica1c">Código</a></li>
            <li><a href="#practica1m">Mejor resultado</a></li>
          </ul>
        <li><a href="#practica2">Algoritmos evolutivos: Problema de la asignación cuadrática (QAP)</a></li>
          <ul>
            <li><a href="#practica2d">Documentación</a></li>
            <li><a href="#practica2c">Código</a></li>
            <li><a href="#practica2m">Mejor resultado</a></li>
          </ul>
      </ul>
    </li>
  </ol>
</details>

<a name="teoria"></a>
## 1. Teoría

Sistema Experto basado en lógica difusa para el diagnóstico de diabetes
* [Documentación](https://github.com/Jumacasni/IC/blob/main/Trabajo/SE_diabetes.pdf)

<a name="practicas"></a>
## 2. Prácticas

<a name="practica1"></a>
### 2.1 **Redes neuronales: Reconocimiento óptico de caracteres (MNIST)**
<a name="practica1d"></a>
#### Documentación

  * [Documentación](MNIST/documentacion.pdf)

<a name="practica1c"></a>
#### Código
  * [Versión 1 - Perceptrón](MNIST/src/version1_perceptron.ipynb): 12.32% de error
  * [Versión 2](MNIST/src/version2.ipynb): 7.46% de error
  * [Versión 3](MNIST/src/version3.ipynb): 1.9% de error
  * [Versión 4](MNIST/src/version4.ipynb): 1.26% de error
  * [Versión 5](MNIST/src/version5.ipynb): 0.81% de error
  * [Versión 6](MNIST/src/version6.ipynb): 0.47% de error

<a name="practica1m"></a>
#### Mejor resultado

* **0.47% de error** sobre el conjunto de entrenamiento
* Red neuronal usada:

<img src="https://github.com/Jumacasni/IC/blob/main/MNIST/img/version6_plot.png" width="30%" height="">

* Training loss vs. Validation loss:

<img src="https://github.com/Jumacasni/IC/blob/main/MNIST/img/version6_grafica1.png" width="30%" height="">

* Training accuracy vs. Validation accuracy:

<img src="https://github.com/Jumacasni/IC/blob/main/MNIST/img/version6_grafica2.png" width="30%" height="">

<a name="practica2"></a>
### 2.2 Algoritmos evolutivos: Problema de la asignación cuadrática (QAP)
<a name="practica2d"></a>
#### Documentación
  * [Documentación](QAP/documentacion.pdf)
  
<a name="practica2c"></a>
#### Código
  * [Versión en Python](QAP/Python)
  * [Versión en Java](QAP/Java)

<a name="practica2m"></a>
#### Mejor resultado

* **Versión**: Lamarckiana
* **Tamaño de la población**: 50
* **Número de iteraciones**: 20
* **Tamaño del torneo**: 20
* **Operador de cruce**: Uniform Like Crossover (ULX)
* **Operador de mutación**: Swap mutation
* **Probabilidad de mutación**: 0.05
* **Coste de la solución**: 44842840
* **Tiempo de ejecucion**: 4026.60 segundos
* **Vector solución**:
```
[106, 9, 195, 6, 213, 209, 98, 154, 141, 159, 128, 2, 180, 87, 68, 189, 43, 165, 224, 26, 41, 245, 234, 184, 145, 126, 102, 198, 226, 38, 172, 204, 80, 36, 76, 63, 162, 192, 174, 17, 117, 137, 115, 232, 177, 215, 191, 169, 55, 50, 24, 120, 187, 78, 132, 89, 206, 221, 135, 21, 243, 65, 72, 202, 236, 85, 150, 46, 19, 32, 217, 58, 83, 53, 147, 109, 12, 139, 0, 61, 29, 247, 238, 91, 228, 113, 167, 251, 255, 14, 111, 124, 201, 73, 231, 37, 210, 193, 49, 248, 56, 75, 214, 185, 242, 246, 112, 200, 95, 54, 39, 101, 108, 222, 94, 166, 99, 134, 28, 227, 23, 156, 114, 11, 170, 103, 179, 16, 116, 93, 199, 31, 1, 151, 104, 239, 125, 158, 88, 235, 22, 148, 203, 218, 69, 97, 30, 237, 252, 66, 160, 146, 133, 127, 244, 67, 40, 149, 110, 42, 51, 196, 155, 74, 130, 59, 25, 64, 92, 205, 122, 81, 100, 13, 223, 254, 140, 15, 152, 119, 188, 86, 52, 33, 182, 207, 47, 190, 153, 79, 163, 175, 219, 143, 90, 225, 129, 71, 138, 157, 173, 183, 70, 136, 10, 62, 3, 181, 176, 249, 27, 77, 48, 240, 164, 60, 250, 45, 211, 168, 178, 161, 18, 35, 107, 123, 8, 5, 57, 20, 142, 171, 208, 44, 105, 7, 241, 131, 4, 220, 197, 121, 118, 230, 186, 229, 84, 144, 96, 216, 212, 34, 82, 253, 194, 233]
```
## Licencia 📄

Este repositorio está bajo la licencia [GPLv3](LICENSE)
