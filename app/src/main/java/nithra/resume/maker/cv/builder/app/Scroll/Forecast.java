package nithra.resume.maker.cv.builder.app.Scroll;

public class Forecast {
    int cityIcon;
    String cityName;
    String temperature;
    Weather weather;

    public Forecast(String str, int i, String str2, Weather weather2) {
        this.cityName = str;
        this.cityIcon = i;
        this.temperature = str2;
        this.weather = weather2;
    }

    public Forecast() {
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public int getCityIcon() {
        return this.cityIcon;
    }

    public void setCityIcon(int i) {
        this.cityIcon = i;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public Weather getWeather() {
        return this.weather;
    }
}
