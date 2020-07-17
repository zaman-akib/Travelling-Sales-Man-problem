
import java.util.ArrayList;

public class TravelManager {
	
    private static ArrayList<City> dest_Cities;
    
    TravelManager(){
    	dest_Cities = new ArrayList<City>();
    }

	public static void addNewCity(City city) {
		dest_Cities.add(city);
	}

	public static City getCity(int index){
		return (City)dest_Cities.get(index);
	}

	public static int numberOfCities(){
		return dest_Cities.size();
	}
    
}
