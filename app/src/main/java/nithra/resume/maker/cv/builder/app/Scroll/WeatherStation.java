package nithra.resume.maker.cv.builder.app.Scroll;

import java.util.Arrays;
import java.util.List;
import nithra.resume.maker.cv.builder.app.R;

public class WeatherStation {
    private WeatherStation() {
    }

    public static WeatherStation get() {
        return new WeatherStation();
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(new Forecast("Personal Info", R.drawable.profile, "16", Weather.PARTLY_CLOUDY), new Forecast("Academic Details", R.drawable.academic, "14", Weather.CLEAR), new Forecast("Work Experience", R.drawable.work_ic, "9", Weather.MOSTLY_CLOUDY), new Forecast("Project Details", R.drawable.project_ic, "18", Weather.PARTLY_CLOUDY), new Forecast("Skills", R.drawable.skills_ic, "6", Weather.PERIODIC_CLOUDS), new Forecast("Achievements", R.drawable.achive_ic, "20", Weather.CLEAR), new Forecast("Reference", R.drawable.share_ic, "20", Weather.CLEAR), new Forecast("Objective", R.drawable.objective_ic, "20", Weather.CLEAR), new Forecast("Cover Letter", R.drawable.cover_ic, "20", Weather.CLEAR), new Forecast("Photo,Sign", R.drawable.photo_ic, "20", Weather.CLEAR), new Forecast("Preview", R.drawable.resume_ic, "20", Weather.CLEAR), new Forecast("View", R.drawable.preview_ic, "20", Weather.CLEAR));
    }
}
