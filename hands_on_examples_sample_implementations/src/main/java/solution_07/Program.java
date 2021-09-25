package solution_07;

public class Program {
	
	public static void main(String[] args) {
		
		String location;
		if (args.length == 1) {
			location = args[0];
		}
		else {
			location = "Zurich, CH";
		}
	
		WeatherInformation weatherInformation = new OpenWeatherMapWeatherInformation();
		double temperature = weatherInformation.getTemperatureFor(location);
		System.out.println("Temperature in '" + location + "': " + temperature + " °C");
		
		int humidity = weatherInformation.getHumidityFor(location);
		System.out.println("Humidity in '" + location + "': " + humidity + " %");
	}
	
}
