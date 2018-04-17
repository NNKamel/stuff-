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
public class SensorImpl implements Sensor{
    
    private char unit;
    private double value;

    public SensorImpl(char unit, double value) {
        setUnit(unit);
        setValue(value);
    }

    public SensorImpl() {
    }

    public char getUnit() {
        return unit;
    }

    public void setUnit(char unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "unit=" + unit + ", value=" + value ;
    }
}
