/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.core;

/**** Question 2: SensorDataImpl class *****/

import exams.finalexam.spring2018.interfaces.Sensor;
import exams.finalexam.spring2018.interfaces.SensorData;
import java.util.ArrayList;
import java.util.List;


public class SensorDataImpl<T extends Sensor> implements SensorData<T> {

    private List<T> data = new ArrayList<T>();
    private String city;

    public SensorDataImpl(String city) {
        setCity(city);
    }
    
    @Override
    public List<T> getData() {
        return data;
    }

    @Override
    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void add(T t) {
        data.add(t);
    }

    @Override
    public void remove(T t) {
        data.remove(t);
    }

    @Override
    public void remove(int i) {
        data.remove(i);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s",city,data);
    }
    
    
}
