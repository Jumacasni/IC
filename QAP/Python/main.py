import os
import time
from genetic_algorithm import GeneticAlgorithm

if __name__ == "__main__":

	# Leo el archivo
	filepath = "tai256c.dat"
	if os.path.exists(filepath):
		lines = []
		with open(filepath, "r") as f:
			try:
				for index, line in enumerate(f.readlines()):
					if line.strip():
						line_int = map(int, line.split())
						lines.append(list(line_int))
			except:
				print("No se puede leer el fichero {}".format(filepath))
	else:
		raise FileNotFoundError("No existe el fichero {}".format(filepath))
	
	size = lines[0][0]

	# La matriz de flujo son los 256 primeros vectores
	flow_matrix = lines[1:size+1]

	# La matriz de distancia son los 256 últimos vectores
	distance_matrix = lines[-size:]

	version = "Baldwiniana"
	algorithm = GeneticAlgorithm(version, size, flow_matrix, distance_matrix)
	print("PARÁMETROS:")
	print("\tVersión: "+version)
	print("\tTamaño de la población: "+str(algorithm.POPULATION_SIZE))
	print("\tNúmero de iteraciones: "+str(algorithm.N_ITERATIONS))
	print("\tTamaño del torneo: "+str(algorithm.TOURNAMENT_SIZE))
	print("\tProbabilidad de mutación: "+str(algorithm.MUTATION_PROBABILITY))

	start = time.time()
	solution = algorithm.start()
	end = time.time()

	# Salida a consola
	print("SOLUCIÓN:")
	print("\tVector solución: "+str(solution.chromosomes))
	solution.calculate_fitness(flow_matrix, distance_matrix)
	print("\tFitness: "+str(solution.fitness))
	print("\tNota obtenida: "+str(5-100*((solution.fitness-44759294)/44759294)))
	print("\tTiempo de ejecución: "+str(end-start))