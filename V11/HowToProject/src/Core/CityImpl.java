/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.City;
import Inteface.GPS;

/**
 *
 * @author NN
 */
public class CityImpl implements City , Comparable<CityImpl>{

    @Override
    public int compareTo(CityImpl o) {
        return this.getName().compareToIgnoreCase(o.getName());
    }

    private String name;
    private GPS location;
    private Address address;

    public CityImpl(String name, GPS location, Address address) {
        setName(name);
        setLocation(location);
        setAddress(address);
    }
    
    public CityImpl(String name, GPS location){
        setName(name);
        setLocation(location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GPS getLocation() {
        return location;
    }

    public void setLocation(GPS location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        if(address==null)
            return "CityImpl{" + "name=" + name + ", location=" + location +'}';
        return "CityImpl{" + "name=" + name + ", location=" + location + ", address=" + address + '}';
    }
    
    
    
    
    
    
}
