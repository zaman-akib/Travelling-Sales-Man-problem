
public class SimulatedAnnealing {

	private final int T_not;
	private final double coolingRate;
	private final double k;

	SimulatedAnnealing(int T, double coolingRate, double k) {
		this.T_not = T;
		this.coolingRate = coolingRate;
		this.k = k;
	}

	Route start_simulatedAnnealing() {
		int T;

		Route currentRoute = new Route();
		currentRoute.generateIndividual();

		Route bestRoute = new Route(currentRoute.getRoute());
		Route nextRoute = new Route();

		for (int t = 1;; t++) {
			T = schedule(t);

			if (T == 0) {
				return bestRoute;
			}

			double random = Calculations.randomDouble();

			if (random < 0.5) {
				nextRoute = oneOneExchange(currentRoute); // heuristic_1 is called here
			} else {
				nextRoute = twoOpt(currentRoute); // heuristic_2 is called here
			}

			double currentRouteCost = currentRoute.getTotalDistance();
			double nextRouteCost = nextRoute.getTotalDistance();

			double delE = nextRouteCost - currentRouteCost;

			if (delE < 0) {
				currentRoute = nextRoute;
			} else {
				double rand = Calculations.randomDouble();
				double x = Math.exp(-delE / (k * T));

				if (x > rand) {
					currentRoute = nextRoute;
				}
			}

			if (currentRoute.getTotalDistance() < bestRoute.getTotalDistance()) {
				bestRoute = currentRoute;
			}
		}
	}

	int schedule(int t) {
		int T;
		T = (int) (T_not - coolingRate * t);

		return T;
	}

	Route oneOneExchange(Route route) {
		Route newRoute = new Route(route.getRoute());
		int n = newRoute.routeSize();

		int random1 = Calculations.randomInt(1, n);
		int random2 = Calculations.randomInt(1, n);

		while (random1 == random2) {
			random2 = Calculations.randomInt(1, n);
		}

		City city1 = newRoute.getCity(random1);
		City city2 = newRoute.getCity(random2);

		newRoute.setCity(random2, city1);
		newRoute.setCity(random1, city2);

		return newRoute;
	}

	Route twoOpt(Route route) {
		Route newRoute = new Route();
		int size = route.routeSize();
		
		for (int k = 0; k < size; k++) {
			newRoute.setCity(k, route.getCity(k));
		}

		double best_distance = route.getTotalDistance();

		for (int i = 1; i < (size - 2); i++) {

			for (int j = i + 1; j < (size -1); j++) {
				
				for (int k = 0; k <= (i-1); k++) {
					newRoute.setCity(k, route.getCity(k));
				}
				
				int count = 0;
				for (int k = i; k <= j; k++) {
					newRoute.setCity(k, route.getCity(j - count));
					count++;
				}

				for (int k = j + 1; k < size; k++) {
					newRoute.setCity(k, route.getCity(k));
				}

				double new_distance = newRoute.getTotalDistance();

				if (new_distance < best_distance) {
					best_distance = new_distance;
					for (int k = 0; k < size; k++) {
						route.setCity(k, newRoute.getCity(k));
					}
				}
			}
		}

		return route;
	}
}
