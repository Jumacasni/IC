import numpy as np

class Population:
	def __init__(self):
		self.individuals = []

	def size(self):
		""" Crea una población """

		return len(self.individuals)

	def get_fittest(self):
		""" Devuelve el mejor individuo de la población """
		best = self.individuals[0]
		for i in range(len(self.individuals)):
			if(self.individuals[i].fitness < best.fitness):
				best = self.individuals[i]

		return best

	def get_worst(self):
		""" Devuelve el peor individuo de la población """

		worst = self.individuals[0]
		index = 0
		for i in range(len(self.individuals)):
			if(self.individuals[i].fitness > worst.fitness):
				worst = self.individuals[i]
				index = self.individuals.index(worst)

		return worst, index

