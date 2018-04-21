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
    public char getUnit();
    public void setUnit(char unit);
    public void setValue(double value);
    
    
    
}
