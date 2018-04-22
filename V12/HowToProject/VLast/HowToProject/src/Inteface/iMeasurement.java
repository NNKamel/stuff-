/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inteface;

import Core.Date;
import Core.DistanceImpl;
import Core.HumidityImpl;
import Core.PressureImpl;
import Core.TemperatureImpl;

/**
 *
 * @author NN
 */
public interface iMeasurement extends Identifiable{
    @Override
    public Date getDay();

    public void setDay(Date day);

    @Override
    public City getCity();

    public void setCity(City city);

    public HumidityImpl getHumidity();
    
    public void setHumidity(Humidity humidity);

    public PressureImpl getPressure();
    
    public void setPressure(Pressure pressure);

    public DistanceImpl getDistance();

    public void setDistance(Distance distance);

    public TemperatureImpl getTemperature();
    
    public void setTemperature(Temperature temperature);
    
}
