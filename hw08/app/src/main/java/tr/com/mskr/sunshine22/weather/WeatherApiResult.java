
package tr.com.mskr.sunshine22.weather;

import com.squareup.moshi.Json;

public class WeatherApiResult {

    @Json(name = "city")
    private City city;
    @Json(name = "cod")
    private String cod;
    @Json(name = "message")
    private Double message;
    @Json(name = "cnt")
    private Integer cnt;
    @Json(name = "list")
    private java.util.List<List> list = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

}
