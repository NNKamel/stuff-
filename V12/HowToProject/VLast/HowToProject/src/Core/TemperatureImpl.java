/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.Temperature;

/**
 *
 * @author NN
 */
public class TemperatureImpl extends SensorImpl implements Temperature{

    public TemperatureImpl(String unit, double value) {
        super(unit, value);
    }
    
    public TemperatureImpl(){
    }
    
    public void setUnit(String unit){
            super.setUnit(unit);
    }
    
    /*public Temperature convertToCelsius(){
        if ((getUnit()=='F')||(getUnit()=='f')){
            setValue((getValue() - 32)/1.8);
            setUnit('C');
        }else
            System.out.println("Same unit, no conversion");
        //Temperature t = new Temperature(value, unit, date);
        return this;
        
        //it will return a Temperature object with the values of the attribute 
        //value, unit and date
    }
    
    public Temperature convertToFahrenheit(){
        if ((getUnit()=='C')||(getUnit()=='c')){
            setValue((getValue()*1.8)+32);
            setUnit('F');
        }else
            System.out.println("Same unit, no conversion");
        //Temperature t = new Temperature(value, unit, date);
        return this;
        
        //it will return a Temperature object with the values of the attribute 
        //value, unit and date
    }*/
    @Override
    public String toString() {
        return "TemperatureImpl{" +super.toString() +'}';
    }
}
