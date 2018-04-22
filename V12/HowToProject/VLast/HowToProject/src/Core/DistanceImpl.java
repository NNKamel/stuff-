/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.Distance;
import Inteface.GPS;

/**
 *
 * @author NN
 */
public class DistanceImpl  extends SensorImpl implements Distance{

    public DistanceImpl() {
    }
    


    public DistanceImpl(String unit, double value) {
        super(unit, value);
    }

    @Override
    public void setUnit(String unit) {
            super.setUnit(unit);
    }

    @Override
    public void setValue(double value) {
        super.setValue(value);
    }
    

    @Override
    public String toString() {
        return "DistanceImpl{" +super.toString() +'}';
    }
    
    

    
    
    
    
}
