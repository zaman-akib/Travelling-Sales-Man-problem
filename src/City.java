
public class City {
    private double x;
    private double y;
    private int cityNumber;            

	public City(int cityNumber, double x, double y){
    	this.cityNumber = cityNumber;
        this.x = x;
        this.y = y;
    }            

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public int getCityNumber() {
		return cityNumber;
	}	
}
