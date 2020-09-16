package com.chazlakinger.lightingcontroller;

class Lights {

    private boolean lightsStatus;
    private boolean sunsetStatus;
    private boolean lockStatus;

    String turnLightsOn() {
        lightsStatus = true;
        lockStatus = false;
        sunsetStatus = UtilLighting.isAfterSunset();
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
        return UtilLighting.morningTask();
    }

    void lockLights() {
        lockStatus = true;
        System.out.println("lights locked");
    }

    void unLockLights() {
        lockStatus = false;
        System.out.println("lights unlocked");
    }

    String turnRoomLightsOn(Boolean lockLights) {
        lightsStatus = true;
        sunsetStatus = false;
        lockStatus = lockLights;
        return UtilLighting.turnRoomLightsOn();
    }

    String turnRoomLightsOff(Boolean lockLights) {
        lightsStatus = false;
        lockStatus = lockLights;
        return UtilLighting.turnRoomLightsOff();
    }

    String lowLightDaytime() {
        lightsStatus = true;
        sunsetStatus = false;
        lockStatus = true;
        return UtilLighting.lowLightDaytime();
    }

    String turnHallLampOn() {
        return UtilLighting.turnHallLampOn();
    }

    String turnHallLampOff() {
        return UtilLighting.turnHallLampOff();
    }

    String nightTask() {
        lightsStatus = false;
        lockStatus = false;
        return UtilLighting.nightTask();
    }

    String turnChazBthFanOn() {
        return UtilLighting.turnChazBthFanOn();
    }

    String turnChazBthFanOff() {
        return UtilLighting.turnChazBthFanOff();
    }

    String turnChazBthFanOn5Min() {
        return UtilLighting.turnChazBthFanOn5Min();
    }
}
