/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.core;

import exams.finalexam.spring2018.interfaces.Humidity;
import exams.finalexam.spring2018.interfaces.Database;
import exams.finalexam.spring2018.interfaces.Distance;
import exams.finalexam.spring2018.interfaces.Measurement;
import exams.finalexam.spring2018.interfaces.Pressure;
import exams.finalexam.spring2018.interfaces.Sensor;
import exams.finalexam.spring2018.interfaces.SensorData;
import exams.finalexam.spring2018.interfaces.SensorFactory;
import exams.finalexam.spring2018.interfaces.Temperature;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class IoTApp {
    
    Database <Measurement> db = DatabaseImpl.getInstance();
    SensorFactory sf = new SensorFactoryImpl();
    Set<String> cities = new TreeSet<String>();
    
    
    /**** Question 4: getSensorData method:
     Get a Sensor of certain type From Measurement*****/
    
    public Sensor getSensorData(Measurement m, String sensorType){
        for(Sensor s: m.getSensors()){
            if(sensorType.equalsIgnoreCase("temperature")){
                if(s instanceof Temperature){
                    return s;
                }
            }
            else if(sensorType.equalsIgnoreCase("pressure")){
                if(s instanceof Pressure){
                    return s;
                }
            }
            else if(sensorType.equalsIgnoreCase("distance")){
                if(s instanceof Distance){
                    return s;
                }
            }
            else if(sensorType.equalsIgnoreCase("humidity")){
                if(s instanceof Humidity){
                    return s;
                }
            }
            else throw new IllegalArgumentException("wrong type");
        }
        return null;
    }
    
        
    /**** Question 5: getData Method:
     get a list of sensors for a specific city for a specific type
     *****/
    
    public SensorData<Sensor> getData(String cityName, String SensorType){
        SensorData<Sensor> sd = new SensorDataImpl(cityName);
        for(Measurement m : db.getDB()){
            //check if m is for cityName
            if(m.getCityName().equalsIgnoreCase(cityName)){
                //get the sensorType required form the sensors of m(Measurement)
                Sensor s = getSensorData(m,SensorType);
                //store
                sd.add(s);
            }
        }
        return sd;
    }
    
    /**** Question 6: Average Map :
    get the average value from a list of sensors
    *****/
    
    public double getAverage(SensorData<Sensor> sensorData){
        double total = 0;
        double count = 0;
        for(Sensor s: sensorData.getData()){
            total+= s.getValue();
            count++;
        }
        return (total/count);
    }
    
    
    /**** Question 7: Average Map 
     get a map containing average for each city Map(city,average) 
     *****/
    
    public Map<String, Double> getAverageBySensorType(String SensorType){
        Map<String, Double> averages = new TreeMap();
        //loop over all the cities
        for(String currentCity: cities){
            //get the sensors list for each city for the specified type
            SensorData<Sensor> sd = getData(currentCity, SensorType);
            //calculate the average for the sensors values
            double average = getAverage(sd);
            //store
            averages.put(currentCity, average);
        }
        return averages;
    }
    
    /*** code from previous exams ****/
    
    public void loadData(String filename) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(filename));
        
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String [] st = line.split(";");
                //Riyadh;24.7136;46.6753;41.09;C;21.65;p;1022.93;mb;27.50;m;01/01/2018
                String cityName = st[0];
                double lat = Double.parseDouble(st[1]);
                double lng = Double.parseDouble(st[2]);
                double temp = Double.parseDouble(st[3]);
                String tempUnit = st[4];
                Temperature tp = (Temperature)(sf.getSensor("temperature", tempUnit, temp));
                double hum = Double.parseDouble(st[5]);
                String humidityUnit = st[6];
                Humidity humidity = (Humidity)(sf.getSensor("humidity", humidityUnit, hum));
                double press = Double.parseDouble(st[7]);
                String pressureUnit = st[8];
                Pressure pressure = (Pressure)(sf.getSensor("pressure", pressureUnit, press));
                double dist = Double.parseDouble(st[9]);
                String distanceUnit = st[10];
                Distance distance = (Distance)(sf.getSensor("distance", distanceUnit, dist));
                String date = st[11];
                int day = Integer.parseInt(date.split("/")[0]);
                int month = Integer.parseInt(date.split("/")[1]);
                int year = Integer.parseInt(date.split("/")[2]);
                Set<Sensor> sensors = new HashSet<Sensor>();
                sensors.add(tp);
                sensors.add(humidity);
                sensors.add(pressure);
                sensors.add(distance);
                Measurement m = new MeasurementImpl(new Date(day, month, year), cityName, new GPSImpl(lat, lng), sensors);
                db.storeElement(m);
                cities.add(cityName.toLowerCase());
            
        }
    }
    
    
    public boolean withinDateInterval(Date d, Date dmin, Date dmax){
        if (dmin.compareTo(dmax)<0){
            if ((d.compareTo(dmin)>=0)&&(d.compareTo(dmax)<=0)){
                return true;
            }
        }
        if (dmin.compareTo(dmax)>0){   
            if ((d.compareTo(dmin)<=0)&&(d.compareTo(dmax)>=0)){
                return true;
            }
        }
        return false;
    }
    
   public Set<Measurement> FilterByDate (Date dmin, Date dmax){
       Set <Measurement> dbm = new HashSet<Measurement>();
       
       for (Measurement m : db.getDB()){
           if (withinDateInterval(m.getDate(), dmin, dmax))
               dbm.add(m);
       }
       return dbm;
   }
   
   public Set<Measurement> FilterByDateAndCity (String cityName, Date dmin, Date dmax){
       Set <Measurement> dbm = new HashSet<Measurement>();
       
       if (cityName.equalsIgnoreCase("all")){
           dbm = db.getDB();
       }else if (cities.contains(cityName.toLowerCase())) {
           for (Measurement m : db.getDB()){
               if (withinDateInterval(m.getDate(), dmin, dmax) && (cityName.equalsIgnoreCase(m.getCityName())))
                   dbm.add(m);
            }
       }else 
           throw new IllegalArgumentException("City Not Found");
       return dbm;
   }
   
   public double maxTemperature (String cityName, Date dmin, Date dmax){
       Set<Measurement> mSet = new HashSet<Measurement>();
       mSet = FilterByDateAndCity(cityName, dmin, dmax);
       
       double ht = -9999;
       Measurement m = Collections.max(mSet, new MeasurementTemperatureComparator());
       for (Sensor s : m.getSensors()){
            if (s instanceof Temperature){
                ht=s.getValue();
                break;
            }
        }
       if (ht==-9999) throw new IllegalArgumentException("No temperature found in m1");
       return ht;
   }
    
    
    
}
