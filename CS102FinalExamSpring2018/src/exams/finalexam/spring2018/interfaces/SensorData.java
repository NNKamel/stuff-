/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.interfaces;

import java.util.List;

/**** Question 1: SensorData interface *****/

public interface SensorData<T extends Sensor>{
    public List<T> getData();
    public void  setData(List<T> data);
    public String getCity();
    public void setCity(String city);
    public void add(T t);
    public void remove(T t);
    public void remove(int i);
}