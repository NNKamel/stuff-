/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.Humidity;

/**
 *
 * @author NN
 */
public class HumidityImpl extends SensorImpl implements Humidity{

    public HumidityImpl() {
    }
    
    

    public HumidityImpl(char unit, double value) {
        super(unit, value);
    }
    @Override
    public String toString() {
        return "HumidityImpl{" +super.toString() +'}';
    }
    
    
    
}
