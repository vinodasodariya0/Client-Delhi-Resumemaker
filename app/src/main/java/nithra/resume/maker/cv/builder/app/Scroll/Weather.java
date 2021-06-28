package nithra.resume.maker.cv.builder.app.Scroll;

public enum Weather {
    PERIODIC_CLOUDS("Periodic Clouds"),
    CLOUDY("Cloudy"),
    MOSTLY_CLOUDY("Mostly Cloudy"),
    PARTLY_CLOUDY("Partly Cloudy"),
    CLEAR("Clear");
    
    private String displayName;

    private Weather(String str) {
        this.displayName = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
