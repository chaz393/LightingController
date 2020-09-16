package com.chazlakinger.lightingcontroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightingController {

    private Lights lights = new Lights();

    @PostMapping(path = "/turnLightsOn", produces = "text/plain")
    public String turnLightsOn() {
        return lights.turnLightsOn();
    }

    @PostMapping(path = "/turnLightsOff", produces = "text/plain")
    public String turnLightsOff() {
        return lights.turnLightsOff();
    }

    @PostMapping(path = "/turnToSunset", produces = "text/plain")
    public String turnToSunset() {
        return lights.turnToSunset(false);
    }

    @PostMapping(path = "/turnToDaytime", produces = "text/plain")
    public String turnToDaytime() {
        return lights.turnToDaytime(false);
    }

    @PostMapping(path = "/updateLightsIfOn", produces = "text/plain")
    public String updateLightsIfOn() {
        return lights.updateLightsIfOn();
    }

    @PostMapping(path = "/morningTask", produces = "text/plain")
    public String morningTask() {
        return lights.morningTask();
    }

    @PostMapping(path = "/lockLights")
    public void  lockLights() {
        lights.lockLights();
    }

    @PostMapping(path = "/unLockLights")
    public void  unLockLights() {
        lights.unLockLights();
    }

    @PostMapping(path = "/turnToSunsetAndLockLights", produces = "text/plain")
    public String turnToSunsetAndLockLights() {
        return lights.turnToSunset(true);
    }

    @PostMapping(path = "/turnToDaytimeAndLockLights", produces = "text/plain")
    public String turnToDaytimeAndLockLights() {
        return lights.turnToDaytime(true);
    }

    @PostMapping(path = "/turnRoomLightsOn", produces = "text/plain")
    public String turnRoomLightsOn() {
        return lights.turnRoomLightsOn(false);
    }

    @PostMapping(path = "/turnRoomLightsOff", produces = "text/plain")
    public String turnRoomLightsOff() {
        return lights.turnRoomLightsOff(false);
    }

    @PostMapping(path = "/turnRoomLightsOnAndLock", produces = "text/plain")
    public String turnRoomLightsOnAndLock() {
        return lights.turnRoomLightsOn(true);
    }

    @PostMapping(path = "/turnRoomLightsOffAndLock", produces = "text/plain")
    public String turnRoomLightsOffAndLock() {
        return lights.turnRoomLightsOff(true);
    }

    @PostMapping(path = "/lowLightDaytime", produces = "text/plain")
    public String lowLightDaytime() {
        return lights.lowLightDaytime();
    }

    @PostMapping(path = "/turnHallLampOn", produces = "text/plain")
    public String turnHallLampOn() {
        return lights.turnHallLampOn();
    }

    @PostMapping(path = "/turnHallLampOff", produces = "text/plain")
    public String turnHallLampOff() {
        return lights.turnHallLampOff();
    }

    @PostMapping(path = "/nightTask", produces = "text/plain")
    public String nightTask() {
        return lights.nightTask();
    }

    @PostMapping(path = "/turnChazBthFanOn", produces = "text/plain")
    public String turnChazBthFanOn() {
        return lights.turnChazBthFanOn();
    }

    @PostMapping(path = "/turnChazBthFanOff", produces = "text/plain")
    public String turnChazBthFanOff() {
        return lights.turnChazBthFanOff();
    }

    @PostMapping(path = "/turnChazBthFanOn5Min", produces = "text/plain")
    public String turnChazBthFanOn5Min() {
        return lights.turnChazBthFanOn5Min();
    }
}
