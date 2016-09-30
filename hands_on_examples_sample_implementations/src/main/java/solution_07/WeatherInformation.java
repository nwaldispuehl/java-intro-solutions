package solution_07;

public interface WeatherInformation {

	/**
	 * Returns the temperature in celsius for the city provided as argument.
	 */
	public double getTemperatureFor(String city);
	
	public int getHumidityFor(String city);
}
