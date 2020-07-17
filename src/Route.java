import java.util.ArrayList;
import java.util.Collections;

public class Route {
	private ArrayList<City> route;
	private double distance;

	public Route() {
		route = new ArrayList<City>();
		distance = 0;

		for (int i = 0; i < TravelManager.numberOfCities(); i++) {
			route.add(null);
		}
	}

	@SuppressWarnings("unchecked")
	public Route(ArrayList<City> r) {
		this.route = (ArrayList<City>) r.clone();
	}

	public ArrayList<City> getRoute() {
		return route;
	}

	public void generateIndividual() {
		for (int i = 0; i < TravelManager.numberOfCities(); i++) {
			setCity(i, TravelManager.getCity(i));
		}

		Collections.shuffle(route);
	}

	public City getCity(int index) {
		return route.get(index);
	}

	public void setCity(int index, City city) {
		route.set(index, city);
		distance = 0;
	}

	public double getTotalDistance() {
		if (distance == 0.0) {
			double tourDistance = 0.0;
			for (int i = 0; i < routeSize(); i++) {
				City fromCity = getCity(i);
				City toCity;

				if (i + 1 < routeSize()) {
					toCity = getCity(i + 1);
				} else {
					toCity = getCity(0);
				}
				tourDistance += Calculations.cost(fromCity, toCity);
			}
			distance = tourDistance;
		}
		return distance;
	}

	public int routeSize() {
		return route.size();
	}

	public String toString() {
		int a = getCity(0).getCityNumber();
		String s = "" + a;

		for (int i = 1; i < routeSize(); i++) {
			s += " -> " + getCity(i).getCityNumber();
		}

		return s;
	}
}
