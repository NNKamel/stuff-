/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inteface;

import Core.Address;


/**
 *
 * @author NN
 */
public interface City {
    public void setName(String name);
    public void setLocation(GPS coordinate);
    public void setAddress(Address address);
    
    public String getName();
    public GPS getLocation();
    public Address getAddress();
}
