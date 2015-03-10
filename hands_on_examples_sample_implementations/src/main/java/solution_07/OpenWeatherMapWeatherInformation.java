package solution_07;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.JSONObject;

public class OpenWeatherMapWeatherInformation implements WeatherInformation {
	
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final double KELVIN_TRIPLE_POINT = 273.15;

	@Override
	public double getTemperatureFor(String city) {
		
		String rawData = getDataFrom(API_URL + city);
		
		JSONObject weather = new JSONObject(rawData);
		JSONObject main = (JSONObject) weather.get("main");
		Double temperatureKelvin = main.getDouble("temp");
		
		return temperatureKelvin - KELVIN_TRIPLE_POINT;
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
