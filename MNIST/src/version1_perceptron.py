import matplotlib.pyplot as plt
import tensorflow as tf
import numpy as np
import time

# Se cargan las imágenes del dataset MNIST
(training_images, training_labels), (test_images, test_labels) = tf.keras.datasets.mnist.load_data(path="mnist.npz")

# Función que visualiza un ejemplo
def visualize(sample):
  image = training_images[sample]
  fig = plt.figure
  plt.imshow(image, cmap='gray')
  plt.show()

"""# VERSIÓN 1: ALGORITMO DEL PERCEPTRÓN

## PREPARACIÓN DE LOS DATOS
"""

# Se cambia el tipo de los datos a float32
training_images = training_images.astype("float32")
test_images = test_images.astype("float32")

# Las imágenes pasan de matriz 28x28 a vector 784x1
training_images = training_images.reshape(60000, 784)
test_images = test_images.reshape(10000, 784)

# Normalización: los datos pasan a tener un valor entre [0, 1]
training_images = training_images / 255
test_images = test_images / 255

"""## CLASE NEURON:

Representa una neurona. Hay 10 neuronas en total (una para cada dígito)
"""

class Neuron:
  SIZE = 28*28+1 # +1 porque se incluye la entrada x0 con el peso w0 (bias)

  def __init__(self, number, bias):
    self.weights = self.init_weights()
    self.number = number
    self.x0 = 1
    self.weights[0] = bias

  # Para inicializar los pesos a 0
  def init_weights(self):
    return np.zeros(self.SIZE)
  
  # Se calcula la suma de los pesos
  # Devuelve True si es mayor o igual que 0
  def predict(self, image_number):
    # Se inserta añade un 0 en la primera posición (x0)
    image_number = np.insert(image_number,0,self.x0)
    sum = np.dot(image_number,self.weights)

    if sum >= 0:
      return True
    else:
      return False
  
  # Actualiza los pesos
  def update_weights(self, training_image, update_politic):
    training_image = np.insert(training_image,0,self.weights[0])
    
    # Si la salida es 1 pero debería haber sido un 0
    if update_politic:
      self.weights = np.subtract(self.weights,training_image)
    # Si la salida es 0 pero debería haber sido un 1
    else:
      self.weights = np.add(self.weights,training_image)

# Se crea una neurona para cada dígito
neurons = []
for digit in range(10):
  neuron = Neuron(digit, 10)
  neurons.append(neuron)

"""## ENTRENAMIENTO"""

# Para cada neurona se le pasa todo el conjunto de entrenamiento
# Para cada conjunto de entrenamiento, se calcula si la salida es 1 o 0
# Se actualizan los pesos de acuerdo a si acierta o falla
start = time.time()

for neuron in neurons:
  for x in range(len(training_images)):
      activated = neuron.predict(training_images[x])
      if activated:
        if training_labels[x] != neuron.number:
          neuron.update_weights(training_images[x], True)
      else:
        if training_labels[x] == neuron.number:
          neuron.update_weights(training_images[x], False)
          
end = time.time()

"""## PORCENTAJE DE ERROR"""

n_errors = np.zeros(10)

for neuron in neurons:
  for x in range(len(test_images)):
      activated = neuron.predict(test_images[x])
      if activated:
        if test_labels[x] != neuron.number:
          n_errors[neuron.number] += 1

total_percentage = 0
for neuron in neurons:
  percentage = n_errors[neuron.number]/len(test_images)*100
  total_percentage += percentage
  print("Clase "+str(neuron.number)+": "+str(percentage)+"% de error")
  
print("\nPorcentaje de error total: "+str(total_percentage))
print("Tiempo transcurrido: "+str(round(end - start,2)))