/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.City;
import Inteface.DataAnalytics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 *
 * @author NN
 */
public class DataAnalyticsImpl implements DataAnalytics{
    
    //private ArrayList<Measurement> measurements = new ArrayList<>();

    //private TreeSet<CityImpl> cities = new TreeSet();
    
    //private static MeasurementsDataBase db = MeasurementsDataBase.getInstance();
    private static DataAnalyticsImpl instance = new DataAnalyticsImpl();
    private TreeMap<CityImpl,MeasurementsDataBase> tdb = new TreeMap();
    
    private DataAnalyticsImpl() {
        
    }
    
    /*public TreeSet<CityImpl> getCities(){
        return cities;
    }*/
    
    public static DataAnalyticsImpl getInstance(){
        return instance;
    }
    
    
    public TreeMap<CityImpl,MeasurementsDataBase> imread(){
        try {
            int count = 0;
            BufferedReader br = new BufferedReader(new FileReader("sensor-data.txt"));
            
            int i = 0;
            String Line = "";
            while((Line = br.readLine())!=null){
                try{
                MeasurementsDataBase db;
                StringTokenizer st = new StringTokenizer(Line,";");
                
                String CityName = st.nextToken();
                double lat = Double.parseDouble(st.nextToken());
                double lon = Double.parseDouble(st.nextToken());
                CityImpl city = new CityImpl(CityName,new GPSImpl(lat,lon));
                
                double tempV = Double.parseDouble(st.nextToken());
                String tempU = st.nextToken();
                TemperatureImpl temp = new TemperatureImpl(tempU, tempV);
                
                tempV = Double.parseDouble(st.nextToken());
                tempU = st.nextToken();
                HumidityImpl humidity = new HumidityImpl(tempU, tempV);
                
                tempV = Double.parseDouble(st.nextToken());
                tempU = st.nextToken();
                PressureImpl pressure = new PressureImpl(tempU, tempV);
                
                tempV = Double.parseDouble(st.nextToken());
                tempU = st.nextToken();
                DistanceImpl distance = new DistanceImpl(tempU, tempV);
                
                String date = st.nextToken();
                String [] d = date.split("/");
                Date dat = new Date(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
                
                
                Measurement measurement = new Measurement(dat,city,humidity, pressure, distance, temp);
                if(tdb.get(city)!=null){
                    db = tdb.get(city);
                    db.addMeasurement(measurement);
                    tdb.put(city, db);
                } else {
                    db = new MeasurementsDataBase();
                    db.addMeasurement(measurement);
                    tdb.put(city, db);
                }
                }catch (Exception e){
                    count++;
                }
                
                //(new MeasurementsDataBase()).addMeasurement(measurement);
                //measurements.add(measurement);
                
                //System.out.println(measurement);
                
                /*i++;
                if(i==100)
                    break;*/
            }
            //System.out.println(measurements)
            if(count>0){
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");;
                System.out.println("there have been " + count + " lines that were unable to be read");
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");;
            }
            return tdb;
            
        } catch (IOException ex) {
            System.out.println("file not found");
            return null;
        } 
    }
    
    @Override
    public TreeMap<String, Double> hottestTemperature(Date d1, Date d2) {
        
        TemperatureImpl t = (TemperatureImpl)new SensorFactory().getSensorType("t");
        TreeMap<City, ArrayList<TemperatureImpl>> tempsOfCities = new TreeMap();
        Iterator<CityImpl> cityIt = tdb.keySet().iterator();
        while(cityIt.hasNext()){
            City c = cityIt.next();
            ArrayList<SensorImpl> sensors = tdb.get(c).getSensors(d1, d2, t);
            ArrayList<TemperatureImpl> temps = new ArrayList(sensors);
            tempsOfCities.put(c, temps);
        }
        
        
        
        Collection<ArrayList<TemperatureImpl>> temps = tempsOfCities.values();
        double [] maxes = new double[temps.size()];        
        
        
        Iterator<ArrayList<TemperatureImpl>> it = temps.iterator();
        int j = 0;
        while(it.hasNext()){//5 times - 5 cities
            ArrayList<TemperatureImpl> tempC = it.next();
            ArrayList<Double> values = new ArrayList();
            for (int i = 0; i < tempC.size(); i++) {
                double val = tempC.get(i).getValue();
                values.add(val);
            }
            maxes[j] = Collections.max(values);
            j++;
        }
        TreeMap<String,Double> tm = new TreeMap();
        cityIt = tdb.keySet().iterator();
        for (int i = 0; i < maxes.length; i++) {
            tm.put(cityIt.next().getName(), maxes[i]);
        }
        return tm;
        
        
        
    }

    @Override
    public TreeMap<String,Double> averageMeasurements(City city, Date d1, Date d2) {
        ArrayList<Double> temps = new ArrayList<>();
        ArrayList<Double> humidities = new ArrayList<>();
        ArrayList<Double> pressures = new ArrayList<>();
        ArrayList<SensorImpl> sensors = new ArrayList<>();
        SensorImpl s;
        SensorFactory sf = new SensorFactory();
        
        s = (TemperatureImpl)sf.getSensorType("t");
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            temps.add(sen.getValue());
        }
        
        s = (PressureImpl)sf.getSensorType("p");
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            pressures.add(sen.getValue());
        }
        
        s = (HumidityImpl)sf.getSensorType("h");
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            humidities.add(sen.getValue());
        }
        
        //********************************************************************************
        ArrayList<Double> avg_T_H_P = new ArrayList();
        TreeMap<String, Double> tm = new TreeMap();
        tm.put("Temperature",0.0);
        tm.put("Humidity",0.0);
        tm.put("Pressure",0.0);
        
        for (int i = 0; i < humidities.size(); i++) {
            if(!avg_T_H_P.isEmpty()){
            } 
            if (i==0){                
                avg_T_H_P.add(temps.get(i));
                avg_T_H_P.add(humidities.get(i));
                avg_T_H_P.add(pressures.get(i));
                
            }
            else {                
                avg_T_H_P.set(0, avg_T_H_P.get(0)+temps.get(i));
                avg_T_H_P.set(1, avg_T_H_P.get(1)+humidities.get(i));
                avg_T_H_P.set(2, avg_T_H_P.get(2)+pressures.get(i));
            }
        }        
        
        avg_T_H_P.set(0, avg_T_H_P.get(0)/temps.size());
        avg_T_H_P.set(1, avg_T_H_P.get(1)/humidities.size());
        avg_T_H_P.set(2, avg_T_H_P.get(2)/pressures.size());
        
        tm.put("Temperature", avg_T_H_P.get(0));
        tm.put("Humidity", avg_T_H_P.get(1));
        tm.put("Pressure", avg_T_H_P.get(2));
        
        
        
        return tm;
    }
    

    @Override
    public ArrayList<String> citiesByTemperature(Date d1, Date d2) {
        
        ArrayList<Double> averages = new ArrayList();
        Iterator<CityImpl> cityIt = tdb.keySet().iterator();
        HashMap<String, Double> hm = new HashMap(); 
        
        while(cityIt.hasNext()){
            City c = cityIt.next();
            double avg = averageMeasurements(c, d1, d2).get("Temperature");
            hm.put(c.getName(), avg);
        }
        
        TreeMapComparator tmc = new TreeMapComparator(hm);
        
        TreeMap<String, Double> tm = new TreeMap(tmc);
        
        tm.putAll(hm);
        
        ArrayList<String> toReturn = new ArrayList(tm.keySet());
        return toReturn;
    }

    @Override
    public void alert(City city, Date d1, Date d2) {
        
        ArrayList<Double> temps = new ArrayList();
        ArrayList<Double> humidities = new ArrayList();
        ArrayList<Double> pressures = new ArrayList();
        ArrayList<Double> distances = new ArrayList();
        ArrayList<SensorImpl> sensors = new ArrayList();
        SensorImpl s;
        
        SensorFactory sf = new SensorFactory();
        
        s = new TemperatureImpl();
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            temps.add(sen.getValue());
        }
        
        s = (PressureImpl)sf.getSensorType("p");
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            pressures.add(sen.getValue());
        }
        
        s = (HumidityImpl)sf.getSensorType("h");
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            humidities.add(sen.getValue());
        }
        
        s = (DistanceImpl)sf.getSensorType("d");
        sensors = tdb.get(city).getSensors(d1, d2, s );
        for(SensorImpl sen : sensors){
            distances.add(sen.getValue());
        }
        
        Iterator<Double> tIt = temps.iterator();
        Iterator<Double> pIt = pressures.iterator();
        Iterator<Double> hIt = humidities.iterator();
        Iterator<Double> dIt = distances.iterator();
        double value = 0;
        
        /*
-	A distance is smaller than 21 m
-	A temperature higher than 45 degrees
-	A pressure higher than 2050 or lower than 1010
-	A humidity higher than 35
        */
        TreeMap<String, Integer> tm = new TreeMap();
        tm.put("Temperature", 0);
        tm.put("Pressure", 0);
        tm.put("Humidity", 0);
        tm.put("Distance", 0);
        int g = 0;
        
        while(tIt.hasNext()){
            value = tIt.next();
            if(value>45){
                g = tm.get("Temperature");
                g++;
                tm.put("Temperature", g);
            }
            
            value = pIt.next();
            if(value>2050||value<1010){
                g = tm.get("Pressure");
                g++;
                tm.put("Pressure", g);
            }
            
            value = hIt.next();
            if(value>35){
                g = tm.get("Humidity");
                g++;
                tm.put("Humidity", g);
            }
            
            value = dIt.next();
            if(value<21){
                g = tm.get("Distance");
                g++;
                tm.put("Distance", g);
            }
        }
        
        System.out.printf("ALERTS OF CITY %s BETWEEN %s AND %s\n",city.getName(),d1,d2);
        System.out.printf(""
                + "Distance Alert: %d\n" +
            "Temperature Alert: %d\n" +
            "Pressure Alert: %d\n" +
            "Humidity Alert: %d\n"
        ,tm.get("Distance"),tm.get("Temperature"), tm.get("Pressure"), tm.get("Humidity"));
    }
    
}
