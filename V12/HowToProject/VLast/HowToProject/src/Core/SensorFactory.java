/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.Sensor;

/**
 *
 * @author NN
 */
public class SensorFactory {
    private Sensor sensor;

    public SensorFactory() {
    }
    
    public Sensor getSensorType(String type){
        if (type.equalsIgnoreCase("t")){
            sensor = new TemperatureImpl();
            return sensor;
        }
        if(type.equalsIgnoreCase("p")){
            sensor = new PressureImpl();
            return sensor;
        }
        if(type.equalsIgnoreCase("h")){
            sensor = new HumidityImpl();
            return sensor;
        }
        if(type.equalsIgnoreCase("d")){
            sensor = new DistanceImpl();
            return sensor;
        }
        else return null;
    }
    
}
