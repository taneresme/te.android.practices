
package tr.com.mskr.sunshine22.weather;

import com.squareup.moshi.Json;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class List {

    @Json(name = "dt")
    private Integer dt;
    @Json(name = "temp")
    private Temp temp;
    @Json(name = "pressure")
    private Double pressure;
    @Json(name = "humidity")
    private Integer humidity;
    @Json(name = "weather")
    private java.util.List<Weather> weather = null;
    @Json(name = "speed")
    private Double speed;
    @Json(name = "deg")
    private Integer deg;
    @Json(name = "clouds")
    private Integer clouds;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public String getReadableDateString(){
        return getReadableDateString(getDt());
    }

    private String getReadableDateString(long time){
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time * 1000);
    }
}
