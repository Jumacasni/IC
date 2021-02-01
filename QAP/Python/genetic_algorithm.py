import os
import random as random
from individual import Individual
from population import Population

class GeneticAlgorithm:
	def __init__(self, t, size, fm, dm):
		self.TYPE = t
		self.FLOW_MATRIX = fm
		self.DISTANCE_MATRIX = dm

		self.POPULATION_SIZE = 20
		self.N_ITERATIONS = 100
		self.TOURNAMENT_SIZE = 5

		self.QAP_SIZE = size
		self.MATRIX_SIZE = range(len(self.FLOW_MATRIX))
		self.GENES_SIZE = len(self.FLOW_MATRIX[0])

		self.MUTATION_PROBABILITY = 0.05

	def start(self):
		""" Este método contiene todos los pasos a seguir en un algoritmo evolutivo """

		current_population = self.create_population()

		for i in range(self.N_ITERATIONS):
			print("Iteración número "+str(i)+"/"+str(self.N_ITERATIONS))
			new_population = Population()
			for j in range(self.POPULATION_SIZE):

				parent1 = self.tournament_selection(current_population)
				parent2 = self.tournament_selection(current_population)
				while parent1 == parent2:
					parent2 = self.tournament_selection(current_population)

				child1, child2 = self.uniform_like_crossover(parent1, parent2)

				self.swap_mutation(child1)
				self.swap_mutation(child2)

				new_population.individuals.append(child1)
				new_population.individuals.append(child2)

			self.calculate_all_fitness(new_population)

			worst_individual, index_worst = new_population.get_worst()
			best_individual = current_population.get_best()
			new_population.individuals[index_worst] = best_individual

			current_population = new_population

		best = new_population.get_best()
		return best

	def create_population(self):
		""" Crea una población """
		population = Population()

		for j in range(self.POPULATION_SIZE):
			population.individuals.append(Individual(self.QAP_SIZE))
			population.individuals[j].calculate_fitness(self.FLOW_MATRIX, self.DISTANCE_MATRIX)

		return population

	def tournament_selection(self, population):
		""" Selección por torneo: se escogen al azar n individuos de la población
			(sin que ninguno se repita) y se devuelve el que tenga mejor fitness """
		tournament = Population()
		for i in range(self.TOURNAMENT_SIZE):
			r = int(random.random() * population.size())
			while population.individuals[r] in tournament.individuals:
				r = int(random.random() * population.size())

			tournament.individuals.append(population.individuals[r])

		return tournament.get_best()

	def uniform_like_crossover(self, parent1, parent2):
		""" Crea dos hijos a partir de dos padres """

		child1 = Individual(parent1.size,[-1]*parent1.size)
		child2 = Individual(parent1.size,[-1]*parent1.size)

		self.generate_child(child1, parent1, parent2)
		self.generate_child(child2, parent1, parent2)

		return child1, child2

	def generate_child(self, child, parent1, parent2):
		""" Crea un hijo a partir de dos padres usando el algoritmo ULX """

		unassigned_chromosomes = []
		unassigned_index = []
		for i in range(parent1.size):
			if(parent1.chromosomes[i] == parent2.chromosomes[i]):
				child.chromosomes[i] = parent1.chromosomes[i]

			elif (parent1.chromosomes[i] not in child.chromosomes) and (parent2.chromosomes[i] not in child.chromosomes):
				chromosome = random.choice([parent1.chromosomes[i],parent2.chromosomes[i]])
				child.chromosomes[i] = chromosome

			else:
				unassigned_index.append(i)

		for i in range(parent1.size):
			if i not in child.chromosomes:
				unassigned_chromosomes.append(i)

		# Asegura que el número de índices no asignados es igual al número de cromosomas no asignados
		assert(len(unassigned_chromosomes) == len(unassigned_index))

		random.shuffle(unassigned_chromosomes)
		random.shuffle(unassigned_index)

		for i in range(len(unassigned_chromosomes)):
			child.chromosomes[unassigned_index[i]] = unassigned_chromosomes[i]

		# Asegura que no hay elementos repetidos en el hijo
		assert(any(child.chromosomes.count(element) > 1 for element in child.chromosomes) == False)

	def swap_mutation(self, child):
		""" Intercambia dos cromosomas en un individuo """

		if random.random() < self.MUTATION_PROBABILITY:
			chromosome1 = int(random.random() * child.size)
			chromosome2 = int(random.random() * child.size)
			while chromosome1 == chromosome2:
				chromosome2 = int(random.random() * child.size)

			child.swap(chromosome1, chromosome2)

	def calculate_all_fitness(self, population):
		""" Calcula el fitness de todos los individuos de una población
			teniendo en cuenta la versión que se ha elegido """

		if self.TYPE == "Baldwiniana":
			for i in range(len(population.individuals)):
				population.individuals[i].calculate_fitness_baldwiniana(self.FLOW_MATRIX, self.DISTANCE_MATRIX)

		elif self.TYPE == "Lamarckiana":
			for i in range(len(population.individuals)):
				population.individuals[i].calculate_fitness_lamarckiana(self.FLOW_MATRIX, self.DISTANCE_MATRIX)

		elif self.TYPE == "Standard":
			for i in range(len(population.individuals)):
				population.individuals[i].calculate_fitness(self.FLOW_MATRIX, self.DISTANCE_MATRIX)

		else:
			print("No existe la versión: "+self.TYPE)
			exit()
