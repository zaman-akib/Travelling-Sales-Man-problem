import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class TSP {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String dataSet[] = { "att48.tsp", "burma14.tsp", "st70.tsp", "ulysses16.tsp", "ulysses22.tsp" };
		FileWriter fw = new FileWriter("output.txt", true);
		PrintWriter writer = new PrintWriter(fw);
		final int T = 1000;
		final double eta = 0.5;
		final double k = 1.3806e-23;
		DecimalFormat df = new DecimalFormat("#.####");

		for (int i = 0; i < 5; i++) {
			String filename = dataSet[i];
			ReadDataSet inputs = new ReadDataSet(filename);

			System.out.println((i + 1) + ". " + filename);

			double sum = 0;
			double min_cost = Integer.MAX_VALUE;
			SimulatedAnnealing sa = new SimulatedAnnealing(T, eta, k);
			Route r = new Route();
			Route min_route = new Route();

			for (int t = 1; t <= 10; t++) {
				r = sa.start_simulatedAnnealing();
				sum += r.getTotalDistance();

				if (r.getTotalDistance() < min_cost) {
					min_cost = r.getTotalDistance();
					min_route = new Route(r.getRoute());
				}					
			}

			double avrg_cost = sum / 10.0;
			double gap = Math.abs(avrg_cost - min_cost);

			System.out.println("Average cost: " + df.format(avrg_cost));
			System.out.println("Minimum cost: " + df.format(min_cost));
			System.out.println("Gap: " + df.format(gap));
			System.out.println("Route: " + min_route);
			System.out.println();

			writer.append(filename + "\t\t" + T + "\t\t" + eta + "\t\t" + k + "\t\t" + df.format(avrg_cost) + "\t\t"
					+ df.format(min_cost) + "\t\t" + df.format(gap) + "\n");
			
			if(dataSet[i].equals("st70.tsp")) {
				Gui g = new Gui();
				g.setVisible(true);
				g.drawRoute(min_route);
			}
		}
		writer.append("\n");
		writer.close();
	}
}
