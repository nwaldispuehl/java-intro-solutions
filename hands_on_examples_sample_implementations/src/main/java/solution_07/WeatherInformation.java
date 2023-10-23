package solution_07;

import java.util.Optional;

public interface WeatherInformation {

	/**
	 * Returns the temperature in celsius for the city provided as argument.
	 */
	Optional<WeatherData> getTemperatureFor(String city);


}
