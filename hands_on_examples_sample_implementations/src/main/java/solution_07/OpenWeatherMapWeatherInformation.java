package solution_07;

import org.json.JSONObject;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class OpenWeatherMapWeatherInformation implements WeatherInformation {
	
	/* Note that this is just a generated API key for the free subscription in the URL. It might or might not work. 
	 * If the latter happens you could go to their website to register for an own API key. */
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?appid=cf3772c618c3b3309a15427de0c799f2&q=";
	private static final BigDecimal KELVIN_TRIPLE_POINT = BigDecimal.valueOf(273.15);

	@Override
	public Optional<WeatherData> getTemperatureFor(String city) {
		String rawData = getDataFrom(API_URL + encode(city));

		if (rawData != null) {
			JSONObject weather = new JSONObject(rawData);
			JSONObject main = (JSONObject) weather.get("main");
			BigDecimal temperatureKelvin = BigDecimal.valueOf(main.getDouble("temp"));
			double temperature = temperatureKelvin.subtract(KELVIN_TRIPLE_POINT).doubleValue();
			int humidity = main.getInt("humidity");

			return Optional.of(new WeatherData(temperature, humidity));
		}
		else {
			return Optional.empty();
		}
	}
	
	private String encode(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

	/* These are helper functions. */
	
	private String getDataFrom(String address) {
		try (InputStream inputStream = URI.create(address).toURL().openStream()) {
			return convertStreamToString(inputStream);	
		} 
		catch (Exception e) {
			return null;
		}
	}
	
	private String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is);
	    s.useDelimiter("\\A");
	    String result = s.hasNext() ? s.next() : "";
	    s.close();
	    return result;
	}

}
