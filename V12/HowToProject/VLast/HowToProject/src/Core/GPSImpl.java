/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Inteface.GPS;

/**
 *
 * @author 216110186
 */
public class GPSImpl implements GPS{
    
    private double x;
    private double y;
    private double z;

    public GPSImpl(double x, double y, double z) {
        setLat(x);
        setAlt(y);
        setLon(z);
    }

    public GPSImpl(double x, double z) {
        setLat(x);
        setLon(z);
    }
    
    
    
    

    @Override
    public double getLon() {
        return z;
    }

    @Override
    public void setLon(double z) {
        this.z = z;
    }

    @Override
    public void setLat(double x) {
        this.x = x;
    }

    @Override
    public void setAlt(double y) {
        this.y = y;
    }

    @Override
    public double getLat() {
        return x;
    }

    @Override
    public double getAlt() {
        return y;
    }

    @Override
    public String toString() {
        if(y==0)
        return "GPSImpl{" + "x=" + x + ", z=" + z + '}';
        else 
        return "GPSImpl{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
}
