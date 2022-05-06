package com.study.city.entity.weather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class WeatherResult {

    private Weather yesterday;
    private String city;
    private List<Weather> forecast;
    private String ganmao;
    private long wendu;

    @Override
    public String toString() {
        return "{" +
                "yesterday=" + yesterday +
                ", city='" + city + '\'' +
                ", forecast=" + forecast +
                ", ganmao='" + ganmao + '\'' +
                ", wendu=" + wendu +
                '}';
    }
}
