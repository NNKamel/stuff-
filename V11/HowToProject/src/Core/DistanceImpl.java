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
    


    public DistanceImpl(char unit, double value) {
        super(unit, value);
    }
    
    //public DistanceImpl(GPS start,GPS end, char unit){
    //    calculateDistance(start, end);
    //    setUnit(unit);
    //}

    //@Override
    //public void calculateDistance(GPS start, GPS end) {    
    //    super.setValue(GPSDistanceCalculator.distance(start, end, Character.toString(getUnit())));
    //}

    @Override
    public void setUnit(char unit) {
        if(unit=='K'||unit=='N'||unit=='m')
            super.setUnit(unit);
        else throw new IllegalArgumentException();
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
