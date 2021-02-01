import math
import random

class Individual:

	def __init__(self, size, individual=None):
		self.size = size
		self.fitness = math.inf
		if individual:
			self.copy_individual(individual)
		else:
			self.generate_random_solution()

	def generate_random_solution(self):
		""" Genera una solución aleatoria en el vector de cromosomas del individuo """

		self.chromosomes = list(range(self.size))
		random.shuffle(self.chromosomes)

	def copy_individual(self, individual):
		""" Constructor de copia de un individuo """

		self.chromosomes = list(range(self.size))
		for i in range(self.size):
			self.chromosomes[i] = individual[i]

	def calculate_fitness(self, flow_matrix, distance_matrix):
		""" Calcula el fitness de un individuo """

		total_fitness = 0
		matrix_size = range(len(flow_matrix))
		for i in matrix_size:
			for j in matrix_size:
				chromosomes_i = self.chromosomes[i]
				chromosomes_j = self.chromosomes[j]
				total_fitness += flow_matrix[i][j] * distance_matrix[chromosomes_i][chromosomes_j]

		# Asegura que el fitness no supera el record mundial actual
		assert(total_fitness >= 44759294)

		self.fitness = total_fitness

	def calculate_fitness_baldwiniana(self, flow_matrix, distance_matrix):
		""" Calcula el fitness de un individuo en la versión baldwiniana """

		best = Individual(self.size, self.chromosomes)
		
		for i in range(self.size):
			for j in range(i+1, self.size):
				T = Individual(best.size, best.chromosomes)
				T.chromosomes[i] = best.chromosomes[j]
				T.chromosomes[j] = best.chromosomes[i]
				T.calculate_fitness(flow_matrix, distance_matrix)

				if T.fitness < best.fitness:
					best = T
					self.fitness = best.fitness

	def calculate_fitness_lamarckiana(self, flow_matrix, distance_matrix):
		""" Calcula el fitness de un individuo en la versión lamarckiana"""

		best = Individual(self.size, self.chromosomes)
		
		for i in range(self.size):
			for j in range(i+1, self.size):
				T = Individual(best.size, best.chromosomes)
				T.chromosomes[i] = best.chromosomes[j]
				T.chromosomes[j] = best.chromosomes[i]
				T.calculate_fitness(flow_matrix, distance_matrix)

				if T.fitness < best.fitness:
					best = T
					self.fitness = best.fitness
					this.chromosomes = best.chromosomes
					
	def swap(self, index1, index2):
		""" Intercambia dos cromosomas de un individuo """

		aux = self.chromosomes[index1]
		self.chromosomes[index1] = self.chromosomes[index2]
		self.chromosomes[index2] = aux