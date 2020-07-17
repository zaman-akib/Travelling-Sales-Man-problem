import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Gui extends JFrame {
	Route route;

	public Gui() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		setTitle("Best route");
	}

	public void paint(Graphics g) {
		double scale = 7;
		super.paint(g);

		Graphics2D g2 = (Graphics2D) g;
		if (route == null)
			return;
		
		City currentCity;
		City prevCity;

		int last = 0;
		for (int i = 1; i < route.routeSize(); i++) {
			if (route.getCity(i) == null)
				break;
			last = i;
			currentCity = route.getCity(i);
			prevCity = route.getCity(i-1);
			drawLine(g2, currentCity, prevCity, scale);
		}

		currentCity = route.getCity(0);
		prevCity = route.getCity(last);

		drawLine(g2, currentCity, prevCity, scale);
	}

	private void drawLine(Graphics2D g, City currentCity, City prevCity,
			double scale) {

		Ellipse2D p1 = new Ellipse2D.Double(
				10 + scale * currentCity.getX() - 7, 10 + scale
						* currentCity.getY() - 7, 20, 20);
		g.drawString(currentCity.getCityNumber() + "", (float) (10 + scale
				* currentCity.getX() - 3),
				(float) (10 + scale * currentCity.getY() + 3));

		Line2D lin = new Line2D.Double(10 + scale * currentCity.getX(), 10
				+ scale * currentCity.getY(), 10 + scale * prevCity.getX(), 10
				+ scale * prevCity.getY());
		g.draw(p1);
		g.draw(lin);
	}

	public void drawRoute(Route r) {
		route = new Route(r.getRoute());
		repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}