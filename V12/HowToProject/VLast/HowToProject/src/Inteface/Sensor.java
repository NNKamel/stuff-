/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inteface;

/**
 *
 * @author NN
 */
public interface Sensor{
    
    
    public double getValue();
    public String getUnit();
    public void setUnit(String unit);
    public void setValue(double value);
    
    
    
}
