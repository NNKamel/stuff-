package exams.finalexam.spring2018.core;

import exams.finalexam.spring2018.interfaces.WiFi;


public class WiFiImpl implements WiFi{
    
    private String ip;
    private String ssid;

    public WiFiImpl(String ip, String ssid) {
        this.ip = ip;
        this.ssid = ssid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    
    
    @Override
    public String getIP() {
       return ip;
    }

    @Override
    public String getSSID() {
       return ssid; 
    }

    @Override
    public String toString() {
        return "WiFiImpl{" + "ip=" + ip + ", ssid=" + ssid + '}';
    }
    
    
    
}
