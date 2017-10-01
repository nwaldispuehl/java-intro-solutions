package solution_07;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

public class OpenWeatherMapWeatherInformation implements WeatherInformation {
	
	/* Note that this is just a generated API key for the free subscription in the URL. It might or might not work. 
	 * If the latter happens you could go the their web site to register for an own API key. */
	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?appid=cf3772c618c3b3309a15427de0c799f2&q=";
	private static final BigDecimal KELVIN_TRIPLE_POINT = BigDecimal.valueOf(273.15);

	@Override
	public double getTemperatureFor(String city) {
		String rawData = getDataFrom(API_URL + encode(city));
//		System.out.println("Fetched data: " + rawData);
		
		JSONObject weather = new JSONObject(rawData);
		JSONObject main = (JSONObject) weather.get("main");
		BigDecimal temperatureKelvin = BigDecimal.valueOf(main.getDouble("temp"));
		
		return temperatureKelvin.subtract(KELVIN_TRIPLE_POINT).doubleValue();
	}
	

	@Override
	public int getHumidityFor(String city) {
		String rawData = getDataFrom(API_URL + encode(city));
		JSONObject weather = new JSONObject(rawData);
		JSONObject main = (JSONObject) weather.get("main");
		int humidity = main.getInt("humidity");
		return humidity;
	}
	
	private String encode(String s) {
		try {
			return URLEncoder.encode(s, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* These are helper functions. */
	
	private String getDataFrom(String address) {
		try (InputStream inputStream = new URL(address).openStream()) {
			return convertStreamToString(inputStream);	
		} 
		catch (Exception e) {
			e.printStackTrace();
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
