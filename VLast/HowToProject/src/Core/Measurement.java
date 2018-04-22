/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 *
 * @author NN
 */
public class Measurement implements iMeasurement{
    private Date day;
    private City city;
    private Humidity humidity;
    private Pressure pressure;
    private Distance distance;
    private Temperature temperature;

    public Measurement(Date day, City city, Humidity humidity, Pressure pressure, Distance distance, Temperature temperature) {
        setDay(day);
        setCity(city);
        setHumidity(humidity);
        setPressure(pressure);
        setDistance(distance);
        setTemperature(temperature);
    }

    public Measurement() {
    }
    
    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public HumidityImpl getHumidity() {
        return (HumidityImpl)humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public PressureImpl getPressure() {
        return (PressureImpl)pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public DistanceImpl getDistance() {
        return (DistanceImpl)distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public TemperatureImpl getTemperature() {
        return (TemperatureImpl)temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "Measurement{" + "day=" + day + ", city=" + city + ", humidity=" + humidity + ", pressure=" + pressure + ", distance=" + distance + ", temperature=" + temperature + '}';
    }
    
    
    
}
