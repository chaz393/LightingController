package com.chazlakinger.lightingcontroller;

public class Networking {

    static String plugNightstandIp = "192.168.70.20";
    static String plugSideLampIp = "192.168.70.21";
    static String plugDeskLampIp = "192.168.70.22";
    static String plugBedLampIp = "192.168.70.23";
    static String switchMainIp = "192.168.70.24";
    static String switchSecondaryIp = "192.168.70.25";
    static String plugHallLampIp = "192.168.70.26";
    static String switchChazBthFanIp = "192.168.70.27";

    static String baseUri = "http://";
    static String onUri = "/cm?cmnd=Power on";
    static String offUri = "/cm?cmnd=Power off";
    static String pulseTime5MinUri = "/cm?cmnd=PulseTime 400";
    static String pulseTimeOffUri = "/cm?cmnd=PulseTime 0";
}
