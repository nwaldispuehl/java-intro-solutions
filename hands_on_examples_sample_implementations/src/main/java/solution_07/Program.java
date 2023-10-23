package solution_07;

import java.util.Optional;

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
		Optional<WeatherData> weatherData = weatherInformation.getTemperatureFor(location);
		if (weatherData.isPresent()) {
			System.out.println("Temperature in '" + location + "': " + weatherData.get().getTemperature() + " °C");
			System.out.println("Humidity in '" + location + "': " + weatherData.get().getHumidity() + " %");
		}
		else {
			System.out.println("Place not found");
		}

	}
	
}
