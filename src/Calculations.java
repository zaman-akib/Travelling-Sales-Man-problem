import java.util.Random;

public class Calculations {

	public static double cost(City city1, City city2) {
		double d1 = city1.getX() - city2.getX();
		double d2 = city1.getY() - city2.getY();
		double dist = Math.sqrt((d1 * d1) + (d2 * d2));

		return dist;
	}

	public static double randomDouble() {
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}

	public static int randomInt(int min, int max) {
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int) d;
	}
}
