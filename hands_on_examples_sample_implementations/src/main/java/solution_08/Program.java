package solution_08;

public class Program {
	
	public static void main(String[] args) {
	
		WeatherInformation weatherInformation = new OpenWeatherMapWeatherInformation();
		double temperature = weatherInformation.getTemperatureFor("Zurich");
		System.out.println("Temperature in Zurich: " + temperature);
		
		int humidity = weatherInformation.getHumidityFor("Zurich");
		System.out.println("Humidity in Zurich: " + humidity);
	}
	
}
