/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.City;
import Inteface.Identifiable;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author NN
 */
public class MeasurementsDataBase<T extends Identifiable>{
    
    public TreeMap<Date, T> db = new TreeMap();
    public static TreeSet<CityImpl> cities = new TreeSet();

    //private MeasurementsDataBase instance = new MeasurementsDataBase();
    
    
    public MeasurementsDataBase() {

    }
    
    /*public static MeasurementsDataBase getInstance(){
        return instance;
    }*/
    
    public void addAll(ArrayList<T> ts){
        for(T t: ts){
            this.addMeasurement(t);
        }
    }
    
    public boolean addCity(CityImpl c){
        return cities.add(c);
    }
    
    public TreeSet<CityImpl> getCities(){
        return cities;
    }
    
    public T addMeasurement(T t){
        return db.put(t.getDay(), t);
    }
    
    public T updateMeasurement(T t){
        return db.put(t.getDay(), t);
    }
    
    public T getMeasurement(Date d){
        return db.get(d);
    }
    
    public ArrayList<T> getDb(){
        ArrayList<T> ts = new ArrayList(db.values());
        return ts;
    }
    
    public ArrayList<SensorImpl> getSensors(Date d1, Date d2, SensorImpl v){
        ArrayList<Measurement> ts = new ArrayList(db.values());
        MeasurementsCollector mc = new MeasurementsCollector(ts);
        
        ArrayList<SensorImpl> s = mc.getSensorArraybyCityDate(d1, d2, v);
        return s;
    }
    
    
}
