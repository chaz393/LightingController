package com.chazlakinger.lightingcontroller;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.lang.System.currentTimeMillis;

class UtilLighting {

    static boolean isAfterSunset() {
        final String uri = "https://api.openweathermap.org/data/2.5/weather?zip=63385&appid=" + ApiKeys.open_weather_api;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, null, String.class);
        JsonParser springParser = JsonParserFactory.getJsonParser();
        Map<String, Object> parsedResult = springParser.parseMap(result);
        Map<String, Object> sys = (Map<String, Object>) parsedResult.get("sys");
        Integer sunsetTime = (Integer) sys.get("sunset");
        Integer sunriseTime = (Integer) sys.get("sunrise");
        Long currentTime = currentTimeMillis()/1000;

        System.out.println("current time: " + currentTime + ", sunset time: " + sunsetTime + ", sunrise time: " + sunriseTime);

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
        System.out.println("lights turning off");
        if (switch1Off() && switch2Off() && iftttOff()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnToSunset() {
        System.out.println("lights turning to sunset");
        if (switch1Off() && switch2Off() && iftttSunset()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnToDaytime() {
        System.out.println("lights turning to daytime");
        if (switch1On() && switch2On() && iftttDaytime()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnRoomLightsOn() {
        if (switch1On() && switch2On()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnRoomLightsOff() {
        if (switch1Off() && switch2Off()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String lowLightDaytime() {
        if (switch1On() && switch2On() && iftttSunset()) {
            return "success";
        } else {
            return "error";
        }
    }



    private static Boolean iftttOff() {
        final String uri = "https://maker.ifttt.com/trigger/all_off/with/key/" + ApiKeys.ifttt;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.equals("Congratulations! You've fired the all_off event");
    }

    private static Boolean iftttSunset() {
        final String uriIfttt = "https://maker.ifttt.com/trigger/sunset/with/key/" + ApiKeys.ifttt;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uriIfttt, null, String.class);
        return response != null && response.equals("Congratulations! You've fired the sunset event");
    }

    private static Boolean iftttDaytime() {
        final String uri = "https://maker.ifttt.com/trigger/daytime/with/key/" + ApiKeys.ifttt;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.equals("Congratulations! You've fired the daytime event");
    }



    private static Boolean switch1On() {
        final String uri = "http://192.168.70.24/cm?cmnd=Power on";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean switch1Off() {
        final String uri = "http://192.168.70.24/cm?cmnd=Power off";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

    private static Boolean switch2On() {
        final String uri = "http://192.168.70.25/cm?cmnd=Power on";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean switch2Off() {
        final String uri = "http://192.168.70.25/cm?cmnd=Power off";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }
}
