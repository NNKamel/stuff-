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
    
    private ArrayList<Measurement> measurements = new ArrayList<>();

    private TreeSet<CityImpl> cities = new TreeSet();
    public DataAnalyticsImpl() {
    }
    
    public TreeSet<CityImpl> getCities(){
        return cities;
    }
    
    
    public boolean imread(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("sensor-data.txt"));
            
            int i = 0;
            String Line = "";
            while((Line = br.readLine())!=null){
                
                StringTokenizer st = new StringTokenizer(Line,";");
                
                String CityName = st.nextToken();
                double lat = Double.parseDouble(st.nextToken());
                double lon = Double.parseDouble(st.nextToken());
                CityImpl city = new CityImpl(CityName,new GPSImpl(lat,lon));
                cities.add(city);
                
                double tempV = Double.parseDouble(st.nextToken());
                char tempU = st.nextToken().charAt(0);
                TemperatureImpl temp = new TemperatureImpl(tempU, tempV);
                
                tempV = Double.parseDouble(st.nextToken());
                tempU = st.nextToken().charAt(0);
                HumidityImpl humidity = new HumidityImpl(tempU, tempV);
                
                tempV = Double.parseDouble(st.nextToken());
                tempU = st.nextToken().charAt(0);
                PressureImpl pressure = new PressureImpl(tempU, tempV);
                
                tempV = Double.parseDouble(st.nextToken());
                tempU = st.nextToken().charAt(0);
                DistanceImpl distance = new DistanceImpl(tempU, tempV);
                
                String date = st.nextToken();
                String [] d = date.split("/");
                Date dat = new Core.Date(Integer.parseInt(d[0]),Integer.parseInt(d[1]),Integer.parseInt(d[2]));
                
                Measurement measurement = new Measurement(dat,city,humidity, pressure, distance, temp);
                measurements.add(measurement);
                
                //System.out.println(measurement);
                
                /*i++;
                if(i==100)
                    break;*/
            }
            //System.out.println(measurements);
            
            return true;
            
        } catch (IOException ex) {
            System.out.println("file not found");
            return false;
        } 
    }
    
    @Override
    public TreeMap<String, Double> hottestTemperature(Date d1, Date d2) {

        /*Date x = new Date(1,1,1999);
        Date y = new Date(1,1,2020);
        ArrayList<SensorImpl> s = mc.getSensorArraybyCityDate(x, y, t,"riyadh");
        ArrayList<TemperatureImpl> p = new ArrayList(s);*/
        MeasurementsCollector mc = new MeasurementsCollector(measurements);
        TemperatureImpl t = new TemperatureImpl();
        TreeMap<City, ArrayList<TemperatureImpl>> tempsOfCities = new TreeMap();
        Iterator<CityImpl> cityIt = cities.iterator();
        while(cityIt.hasNext()){
            City c = cityIt.next();
            ArrayList<SensorImpl> sensors = mc.getSensorArraybyCityDate(d1, d2, t, c.getName());
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
                //System.out.println(val);
                values.add(val);
            }
            maxes[j] = Collections.max(values);
            j++;
        }
        TreeMap<String,Double> tm = new TreeMap();
        cityIt = cities.iterator();
        for (int i = 0; i < maxes.length; i++) {
            tm.put(cityIt.next().getName(), maxes[i]);
        }
        return tm;
        
        
        /*for (int i = 0; i < maxes.length; i++) {
            ArrayList<TemperatureImpl> tempC = temps.get(i);

            for(TemperatureImpl T :tempC){
                values.add(T.getValue());
            }
            
            maxes[i] = Collections.max(values);
        }
        
        cityIt = cities.iterator();
        
        for(double max : maxes){
            String name = cityIt.next().getName();
            tm.put(name, max);
        }
        return tm;
        
        
        
        
        /*Date i = new Date(d1);
        int j = 0;
        Date q = new Date(d1);
        int test = 0;
        TreeMap<String,ArrayList<Double>> temps = new TreeMap();
        while(i.compareTo(d2)<=0){
            
            /*inifite proof
            if(i.compareTo(q)==0)
                test++;
            if(test>1)
                break;
            //-----*/
            
            
            /*temps.put("Riyadh", new ArrayList<Double>());
            temps.put("Makkah", new ArrayList<Double>());
            temps.put("Madinah", new ArrayList<Double>());
            temps.put("Jeddah", new ArrayList<Double>());
            temps.put("Abha", new ArrayList<Double>());

            int y = 0;
            for (int l = 0; l < measurements.size(); l++) {
                
                
                if(measurements.get(l).getDay().compareTo(i)==0){
                    y = l;
                    //System.out.println("found it");
                    break;
                }
                
            }
                for (int l = y; l < 5+y&&l<measurements.size(); l++) {
                
                    /*System.out.println(temp);
                    System.out.println(measurements.get(l).getCity().getName());
                    System.out.println("date i : " +i);
                    System.out.println("date k : " +k);*/

                    /*if(measurements.get(l).getCity().getName().equalsIgnoreCase("Riyadh")){
                        temps.get("Riyadh").add(measurements.get(l).getTemperature().getValue());
                    }
                    if(measurements.get(l).getCity().getName().equalsIgnoreCase("Makkah")){
                        temps.get("Makkah").add(measurements.get(l).getTemperature().getValue());
                    }
                    if(measurements.get(l).getCity().getName().equalsIgnoreCase("Madinah")){
                        temps.get("Madinah").add(measurements.get(l).getTemperature().getValue());
                    }
                    if(measurements.get(l).getCity().getName().equalsIgnoreCase("Jeddah")){
                        temps.get("Jeddah").add(measurements.get(l).getTemperature().getValue());
                    }
                    if(measurements.get(l).getCity().getName().equalsIgnoreCase("Abha")){
                        temps.get("Abha").add(measurements.get(l).getTemperature().getValue());
                    }
                    //_________________________________________________________________________________________________________
                    /*
                    double temp = measurements.get(l).getTemperature().getValue();
                    if(measurements.get(l).getCity().getName().equalsIgnoreCase("Riyadh")){
                        if(maxtRd<temp){
                            maxtRd = temp;
                        }
                    }

                    else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Makkah")){
                        if(maxtMk<temp){
                            maxtMk = temp;
                        }
                    }

                    else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Madinah")){
                        if(maxtMd<temp){
                            maxtMd = temp;
                        }
                    }

                    else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Jeddah")){
                        if(maxtJd<temp){
                            maxtJd = temp;
                        }
                    }

                    else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Abha")){
                        if(maxtAb<temp){
                            maxtAb = temp;
                        }
                    }

                    /*System.out.printf("HOTTEST TEMPERATURE DURING D1 AND D2\n"
                        + "Riyadh -> %.2f\n"
                        + "Makkah -> %.2f\n"
                        + "Madinah -> %.2f\n"
                        + "Jeddah -> %.2f\n"
                        + "Abha -> %.2f\n",maxtRd,maxtMk,maxtMd,maxtJd,maxtAb
                    );*/
                }
                
                
                
            
            //____________________________________________________________________
            //increment counter
            /*i.nextDay();
            //System.out.println("i after increment: "+i);
        }
        
        TreeMap<String,Double> toReturn = new TreeMap();
        
        maxtRd = Collections.max(temps.get("Riyadh"));
        maxtMk = Collections.max(temps.get("Makkah"));
        maxtMd = Collections.max(temps.get("Madinah"));
        maxtJd = Collections.max(temps.get("Jeddah"));
        maxtAb = Collections.max(temps.get("Abha"));
        
        toReturn.put("Riyadh", maxtRd);
        toReturn.put("Makkah", maxtMk);
        toReturn.put("Madinah", maxtMd);
        toReturn.put("Jeddah", maxtJd);
        toReturn.put("Abha", maxtAb);
        
        return toReturn;*/

    
        
        
    

    @Override
    public TreeMap<String,Double> averageMeasurements(City city, Date d1, Date d2) {
        ArrayList<Double> temps = new ArrayList<>();
        ArrayList<Double> humidities = new ArrayList<>();
        ArrayList<Double> pressures = new ArrayList<>();
        ArrayList<SensorImpl> sensors = new ArrayList<>();
        SensorImpl s;
        
        MeasurementsCollector mc = new MeasurementsCollector(measurements);
        
        TemperatureImpl t = new TemperatureImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, t , city.getName());
        for(SensorImpl sen : sensors){
            temps.add(sen.getValue());
        }
        
        PressureImpl p = new PressureImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, p , city.getName() );
        for(SensorImpl sen : sensors){
            pressures.add(sen.getValue());
        }
        
        HumidityImpl h = new HumidityImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, h , city.getName() );
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
        
        
        
        
        /*double Atemp =0;
        double AHumidities =0;
        double APressures =0;
        
        Date i = new Date(d1);
        int j = 0;
        Date q = new Date(d1);
        int test = 0;
        
        while(i.compareTo(d2)<=0){
            
            //inifite proof
            if(i.compareTo(q)==0)
                test++;
            if(test>1)
                break;
            //-----
            
            Date k = new Date(i);
            int y = 0;
            for (int l = 0; l < measurements.size(); l++) {
                if(measurements.get(l).getDay().compareTo(k)==0){
                    y = l;
                    //System.out.println("found it");
                    break;
                }
                
            }
            
            
            
                
            /*System.out.println(temp);
            System.out.println(measurements.get(l).getCity().getName());
            System.out.println("date i : " +i);
            System.out.println("date k : " +k);*/
            /*if(measurements.get(y).getCity().getName().equalsIgnoreCase(city.getName())){
                temps.add(measurements.get(y).getTemperature().getValue());
                humidities.add(measurements.get(y).getHumidity().getValue());
                pressures.add(measurements.get(y).getPressure().getValue());
            }
            
            double tTemp = 0;
            double tPressures = 0;
            double tHumidities = 0;

            for (int l = 0; l < temps.size(); l++) {
                tTemp += temps.get(l);
                tHumidities += humidities.get(l);
                tPressures += pressures.get(l);
            }

            Atemp = tTemp/temps.size();
            AHumidities = tHumidities/humidities.size();
            APressures = tPressures/pressures.size();

            //____________________________________________________________________
            //increment counter
            i.nextDay();
        }
        
        
        
        
        avg_T_H_P.add(Atemp);
        avg_T_H_P.add(AHumidities);
        avg_T_H_P.add(APressures);
        
        return avg_T_H_P;*/
        
        //System.out.printf("AVERAGE VALUES OF CITY %s DURING %s AND %s\n",city.getName(),d1,d2);
        //System.out.printf("%s -> %.3f C -> %.3f %s -> %.3f mb\n",city.getName(),Atemp,AHumidities,"%",APressures);
    }
    

    @Override
    public ArrayList<String> citiesByTemperature(Date d1, Date d2) {
        
        ArrayList<Double> averages = new ArrayList();
        Iterator<CityImpl> cityIt = cities.iterator();
        HashMap<String, Double> hm = new HashMap(); 
        
        while(cityIt.hasNext()){
            City c = cityIt.next();
            double avg = averageMeasurements(c, d1, d2).get("Temperature");
            hm.put(c.getName(), avg);
        }
        
        TreeMapComparator tmc = new TreeMapComparator(hm);
        TreeMap<String, Double> tm = new TreeMap<String, Double>(tmc);
        
        tm.putAll(hm);
        ArrayList<String> toReturn = new ArrayList(tm.keySet());
        return toReturn;
        

        
        
        /*ArrayList<Double> tempsR = new ArrayList<>();
        ArrayList<Double> tempsK = new ArrayList<>();
        ArrayList<Double> tempsM = new ArrayList<>();
        ArrayList<Double> tempsJ = new ArrayList<>();
        ArrayList<Double> tempsA = new ArrayList<>();
        
        Date i = new Date(d1);
        int j = 0;
        Date q = new Date(d1);
        int test = 0;
        
        double tTempR = 0;
        double tTempK = 0;
        double tTempM = 0;
        double tTempJ = 0;
        double tTempA = 0;
        while(i.compareTo(d2)<=0){
            
            //inifite proof
            if(i.compareTo(q)==0)
                test++;
            if(test>1)
                break;
            //-----
            
            Date k = new Date(i);
            int y = 0;
            for (int l = 0; l < measurements.size(); l++) {
                if(measurements.get(l).getDay().compareTo(k)==0){
                    y = l;
                    //System.out.println("found it");
                    break;
                }
                
            }
            //____________________________________________________________________
            
            
            for (int l = y; l < 5+y&&l<measurements.size(); l++) {

                if(measurements.get(l).getCity().getName().equalsIgnoreCase("Riyadh")){
                    tempsR.add(measurements.get(l).getTemperature().getValue());
                    //tTempR += measurements.get(l).getTemperature().getValue();
                    //System.out.println(tTempR);
                } 

                //============
                else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Makkah")){
                    tempsK.add(measurements.get(l).getTemperature().getValue());
                    //tTempK += measurements.get(l).getTemperature().getValue();
                    //System.out.println(tTempK);
                }

                //============
                else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Madinah")){
                    tempsM.add(measurements.get(l).getTemperature().getValue());
                    //tTempM += measurements.get(l).getTemperature().getValue();
                    //System.out.println(tTempM);
               }

                //============
                else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Jeddah")){
                    tempsJ.add(measurements.get(l).getTemperature().getValue());
                    //tTempJ += measurements.get(l).getTemperature().getValue();
                    //System.out.println(tTempJ);
                }

                //============
                else if(measurements.get(l).getCity().getName().equalsIgnoreCase("Abha")){
                    tempsA.add(measurements.get(l).getTemperature().getValue());
                    //tTempA += measurements.get(l).getTemperature().getValue();
                    //System.out.println(tTempA);
               }
            }
            
            //____________________________________________________________________
            //increment counter
            i.nextDay();
        }
        /*
            SET OF CITIES ORGANIZED BY THEIR INCREASING ORDER OR AVG TEMPERATURE
            CityName1 -> CityName2 -> CityName3 -> CityName4 -> CityName5
        */
        
        /*for(double a: tempsR){
            tTempR+= a;
        }
        for(double a: tempsK){
            tTempK+= a;
        }
        for(double a: tempsM){
            tTempM+= a;
        }
        for(double a: tempsJ){
            tTempJ+= a;
        }
        for(double a: tempsA){
            tTempA+= a;
        }
        
        double AtempR = tTempR/tempsR.size();
        double AtempK = tTempK/tempsK.size();
        double AtempM = tTempM/tempsM.size();
        double AtempJ = tTempJ/tempsJ.size();
        double AtempA = tTempA/tempsA.size();

        HashMap hm = new HashMap();

        hm.put("Riyadh", AtempR);
        hm.put("Makkah", AtempK);
        hm.put("Madinah", AtempM);
        hm.put("Jeddah", AtempJ);
        hm.put("Abha", AtempA);
        
        //System.out.println(hm);

        //make comparator for ordering the map by value
        Comparator<String> c = new TreeMapTempComparator(hm);
        TreeMap<String, Double> tm = new TreeMap<String, Double>(c);

        //put values from hash to the tree map
        tm.putAll(hm);
        System.out.println(tm);
        ArrayList<String> cities = new ArrayList(tm.keySet());
        return cities;
        //put the ordered values of cities into an array
        /*String [] citynames = new String [5];
        tm.keySet().toArray(citynames);
        
        //for(double r : tm.values()){
        //    System.out.println(r);
        //}
        
        System.out.println("SET OF CITIES ORGANIZED BY THEIR INCREASING ORDER OF AVG TEMPERATURE");
        System.out.printf("%s -> %s -> %s -> %s -> %s\n",citynames[0],citynames[1],citynames[2],citynames[3],citynames[4]);
        */
    }

    @Override
    public void alert(City city, Date d1, Date d2) {
        
        ArrayList<Double> temps = new ArrayList<>();
        ArrayList<Double> humidities = new ArrayList<>();
        ArrayList<Double> pressures = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        ArrayList<SensorImpl> sensors = new ArrayList<>();
        SensorImpl s;
        
        MeasurementsCollector mc = new MeasurementsCollector(measurements);
        
        TemperatureImpl t = new TemperatureImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, t , city.getName());
        for(SensorImpl sen : sensors){
            temps.add(sen.getValue());
        }
        
        PressureImpl p = new PressureImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, p , city.getName() );
        for(SensorImpl sen : sensors){
            pressures.add(sen.getValue());
        }
        
        HumidityImpl h = new HumidityImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, h , city.getName() );
        for(SensorImpl sen : sensors){
            humidities.add(sen.getValue());
        }
        
        DistanceImpl d = new DistanceImpl();
        sensors = mc.getSensorArraybyCityDate(d1, d2, d , city.getName() );
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
        
        /*int distanceA = 0;
        int tempA = 0;
        int pressureA = 0;
        int humidityA = 0;
        
        Date i = new Date(d1);
        int j = 0;
        Date q = new Date(d1);
        int test = 0;
        
        while(i.compareTo(d2)<=0){
            
            /*inifite proof
            if(i.compareTo(q)==0)
                test++;
            if(test>1)
                break;
            -----*/
            
            /*Date k = new Date(i);
            int y = 0;
            for (int l = 0; l < measurements.size(); l++) {
                if(measurements.get(l).getDay().compareTo(k)==0){
                    y = l;
                    //System.out.println("found it");
                    break;
                }
                
            }
            /*System.out.println(temp);
            System.out.println(measurements.get(l).getCity().getName());
            System.out.println("date i : " +i);
            System.out.println("date k : " +k);*/
            /*if(measurements.get(y).getCity().getName().equalsIgnoreCase(city.getName())){
                
                if(measurements.get(y).getDistance().getValue()<21)
                    distanceA++;
                if(measurements.get(y).getTemperature().getValue()>45)
                    tempA++;
                if(measurements.get(y).getPressure().getValue()>2050
                        ||measurements.get(y).getPressure().getValue()<1010)
                    pressureA++;
                if(measurements.get(y).getHumidity().getValue()>35)
                    humidityA++;
                
            }
            
            
        i.nextDay();
        }
                
                
                

        //____________________________________________________________________
        //increment counter
        
        /*
        ALERTS OF CITY CITY_NAME BETWEEN D1 AND D2
        Distance Alert: x
        Temperature Alert: y
        Pressure Alert: z
        Humidity Alert: w

        */
        
        
    }
    
}
