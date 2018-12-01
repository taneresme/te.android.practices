
package tr.com.mskr.sunshine22.weather;

import com.squareup.moshi.Json;

public class Temp {

    @Json(name = "day")
    private Double day;
    @Json(name = "min")
    private Double min;
    @Json(name = "max")
    private Double max;
    @Json(name = "night")
    private Double night;
    @Json(name = "eve")
    private Double eve;
    @Json(name = "morn")
    private Double morn;

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
    }

}
