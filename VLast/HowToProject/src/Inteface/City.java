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
public interface City {
    public void setName(String name);
    public void setLocation(GPS coordinate);
    
    public String getName();
    public GPS getLocation();
}
