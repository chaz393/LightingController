package com.chazlakinger.lightingcontroller;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.client.RestTemplate;
import sun.nio.ch.Net;

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
        if (switchMainOff() && switchSecondaryOff() && plugNightstandOff() && plugBedLampOff() && plugDeskLampOff() && plugSideLampOff()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnToSunset() {
        System.out.println("lights turning to sunset");
        if (switchMainOff() && switchSecondaryOff() && plugNightstandOn() && plugBedLampOff() && plugDeskLampOff() && plugSideLampOff()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnToDaytime() {
        System.out.println("lights turning to daytime");
        if (switchMainOn() && switchSecondaryOn() && plugNightstandOff() && plugBedLampOn() && plugDeskLampOn() && plugSideLampOn()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnRoomLightsOn() {
        if (switchMainOn() && switchSecondaryOn()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String turnRoomLightsOff() {
        if (switchMainOff() && switchSecondaryOff()) {
            return "success";
        } else {
            return "error";
        }
    }

    static String lowLightDaytime() {
        if (switchMainOn() && switchSecondaryOn() && plugNightstandOn() && plugBedLampOff() && plugDeskLampOff() && plugSideLampOff()) {
            return "success";
        } else {
            return "error";
        }
    }



    private static Boolean switchMainOn() {
        final String uri = Networking.baseUri + Networking.switchMainIp + Networking.onUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean switchMainOff() {
        final String uri = Networking.baseUri + Networking.switchMainIp + Networking.offUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

    private static Boolean switchSecondaryOn() {
        final String uri = Networking.baseUri + Networking.switchSecondaryIp + Networking.onUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean switchSecondaryOff() {
        final String uri = Networking.baseUri + Networking.switchSecondaryIp + Networking.offUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

    private static Boolean plugNightstandOn() {
        final String uri = Networking.baseUri + Networking.plugNightstandIp + Networking.onUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean plugNightstandOff() {
        final String uri = Networking.baseUri + Networking.plugNightstandIp + Networking.offUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

    private static Boolean plugBedLampOn() {
        final String uri = Networking.baseUri + Networking.plugBedLampIp + Networking.onUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean plugBedLampOff() {
        final String uri = Networking.baseUri + Networking.plugBedLampIp + Networking.offUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

    private static Boolean plugDeskLampOn() {
        final String uri = Networking.baseUri + Networking.plugDeskLampIp + Networking.onUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean plugDeskLampOff() {
        final String uri = Networking.baseUri + Networking.plugDeskLampIp + Networking.offUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

    private static Boolean plugSideLampOn() {
        final String uri = Networking.baseUri + Networking.plugSideLampIp + Networking.onUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("ON");
    }

    private static Boolean plugSideLampOff() {
        final String uri = Networking.baseUri + Networking.plugSideLampIp + Networking.offUri;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, null, String.class);
        return response != null && response.contains("POWER") && response.contains("OFF");
    }

}
