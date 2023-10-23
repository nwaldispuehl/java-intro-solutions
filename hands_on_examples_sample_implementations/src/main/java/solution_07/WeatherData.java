package solution_07;

public class WeatherData {

    private final double temperature;
    private final int humidity;

    WeatherData(double temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }
}
