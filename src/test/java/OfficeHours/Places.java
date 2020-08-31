package OfficeHours;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Places {

    @JsonProperty("place name")
     private String place;
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    private String longitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    private String state;
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @JsonProperty("state abbreviation ")
    private String  abbreviation;
    private String latitude;


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String toString() {
        return "Place{" +
                "placeName='" + place + '\'' +
                ", longitude='" + longitude + '\'' +
                ", state='" + state + '\'' +
                ", stateAbbreviation='" + abbreviation + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }


}
