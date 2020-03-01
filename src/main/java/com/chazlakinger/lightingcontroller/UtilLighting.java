package com.chazlakinger.lightingcontroller;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

class UtilLighting {

    static boolean isAfterSunset() {
        final String uri = "https://api.openweathermap.org/data/2.5/weather?zip=63385&appid=" + ApiKeys.open_weather_api;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, null, String.class);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> parsedResult = springParser.parseMap(result);
        Map<String, Object> sys = (Map<String, Object>) parsedResult.get("sys");
        Integer currentTime = (Integer) parsedResult.get("dt");
        Integer sunsetTime = (Integer) sys.get("sunset");
        Integer sunriseTime = (Integer) sys.get("sunrise");

        if (currentTime > sunsetTime) {
            System.out.println("currently after sunset but before midnight");
            return true;
        } else if (currentTime < sunriseTime) {
            System.out.println("currently after midnight but before sunrise");
            return true;
        } else {
            System.out.println("currently day time");
            return false;
        }
    }

    static String turnLightsOn() {
        if (UtilLighting.isAfterSunset()) {
            return turnToSunset();
        } else {
            return turnToDaytime();
        }
    }

    static String turnLightsOff() {
        System.out.println("lights turned off");
        final String uri = "https://maker.ifttt.com/trigger/all_off/with/key/" + ApiKeys.ifttt;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, null, String.class);
    }

    static String turnToSunset() {
        System.out.println("lights turned to sunset");
        final String uri = "https://maker.ifttt.com/trigger/sunset/with/key/" + ApiKeys.ifttt;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, null, String.class);
    }

    static String turnToDaytime() {
        System.out.println("lights turned to daytime");
        final String uri = "https://maker.ifttt.com/trigger/daytime/with/key/" + ApiKeys.ifttt;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(uri, null, String.class);
    }
}
