import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadDataSet {
	int cityIndex;
	double x;
	double y;
	static int countCity=0;
	
	ReadDataSet(String filename) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(filename));
        String s;
        
        for(int i=0; i<6; i++) {
        	s = sc.nextLine();
        	
        	if(i==3) {
        		String st[];
        		st = s.split(" ");
        		countCity = Integer.parseInt(st[1]);
        		//System.out.println(countCity);
        	}
        }
        
        TravelManager tm = new TravelManager();
        
        for(int i=0; i<countCity; i++) {
            cityIndex = sc.nextInt();
            x = sc.nextDouble();
            y = sc.nextDouble();
            
            City city = new City(cityIndex, x, y);
            tm.addNewCity(city);
        }
    }
}
