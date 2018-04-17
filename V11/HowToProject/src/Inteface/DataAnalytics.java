/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inteface;

import Core.Date;
import java.util.ArrayList;
import java.util.TreeMap;


/**
 *
 * @author NN
 */
public interface DataAnalytics {
    public TreeMap<String, Double> hottestTemperature(Date d1, Date d2);
    public TreeMap<String,Double> averageMeasurements(City city, Date d1, Date d2);
    public ArrayList<String> citiesByTemperature(Date d1, Date d2);
    public void alert(City city, Date d1, Date d2);
}
