package com.chazlakinger.lightingcontroller;

class Lights {

    private boolean lightsStatus;
    private boolean sunsetStatus;
    private boolean lockStatus;

    String turnLightsOn() {
        lightsStatus = true;
        lockStatus = false;
        return UtilLighting.turnLightsOn();
    }

    String turnLightsOff() {
        lightsStatus = false;
        lockStatus = false;
        return UtilLighting.turnLightsOff();
    }

    String turnToSunset(Boolean lockLights) {
        lightsStatus = true;
        sunsetStatus = true;
        lockStatus = lockLights;
        return UtilLighting.turnToSunset();
    }

    String turnToDaytime(Boolean lockLights) {
        lightsStatus = true;
        sunsetStatus = false;
        lockStatus = lockLights;
        return UtilLighting.turnToDaytime();
    }

    String updateLightsIfOn() {
        if(lightsStatus && !lockStatus) {
            if (!sunsetStatus && UtilLighting.isAfterSunset()) {
                sunsetStatus = true;
                return UtilLighting.turnToSunset();
            } else if (sunsetStatus && !UtilLighting.isAfterSunset()) {
                sunsetStatus = false;
                return UtilLighting.turnToDaytime();
            } else {
                return "no update needed";
            }
        } else {
            return "lights not on or are locked";
        }
    }

    String morningTask() {
        lightsStatus = true;
        lockStatus = true;
        return UtilLighting.turnToSunset();
    }

    void lockLights() {
        lockStatus = true;
        System.out.println("lights locked");
    }

    void unLockLights() {
        lockStatus = false;
        System.out.println("lights unlocked");
    }
}
