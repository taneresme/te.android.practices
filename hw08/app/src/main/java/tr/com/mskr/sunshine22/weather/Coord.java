
package tr.com.mskr.sunshine22.weather;

import com.squareup.moshi.Json;

public class Coord {

    @Json(name = "lon")
    private Double lon;
    @Json(name = "lat")
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

}
