package solution_07;

public class Program {
	
	public static void main(String[] args) {
	
		WeatherInformation weatherInformation = new OpenWeatherMapWeatherInformation();
		double temperature = weatherInformation.getTemperatureFor("Zurich City, CH");
		System.out.println("Temperature in Zurich: " + temperature);
		
		int humidity = weatherInformation.getHumidityFor("Zurich City, CH");
		System.out.println("Humidity in Zurich: " + humidity);
	}
	
}
