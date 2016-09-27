package solution_08;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

import org.json.JSONObject;

public class OpenWeatherMapWeatherInformation implements WeatherInformation {
	
	/* Note that this is just a generated API key for the free subscription in the URL. It might or might not work. 
	 * If the latter happens you could go the their web site to register for an own API key. */
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?appid=cf3772c618c3b3309a15427de0c799f2&q=";
	private static final BigDecimal KELVIN_TRIPLE_POINT = BigDecimal.valueOf(273.15);

	@Override
	public double getTemperatureFor(String city) {
		
		String rawData = getDataFrom(API_URL + city);
		
		JSONObject weather = new JSONObject(rawData);
		JSONObject main = (JSONObject) weather.get("main");
		BigDecimal temperatureKelvin = BigDecimal.valueOf(main.getDouble("temp"));
		
		return temperatureKelvin.subtract(KELVIN_TRIPLE_POINT).doubleValue();
	}
	

	@Override
	public int getHumidityFor(String city) {
		String rawData = getDataFrom(API_URL + city);
		JSONObject weather = new JSONObject(rawData);
		JSONObject main = (JSONObject) weather.get("main");
		int humidity = main.getInt("humidity");
		return humidity;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* These are helper functions. */
	
	private String getDataFrom(String address) {
		InputStream inputStream = null;
		try {
			URL url = new URL(address);
			inputStream = url.openStream();
			return convertStreamToString(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
		
		return null;
	}
	
	private String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is);
	    s.useDelimiter("\\A");
	    String result = s.hasNext() ? s.next() : "";
	    s.close();
	    return result;
	}

}
