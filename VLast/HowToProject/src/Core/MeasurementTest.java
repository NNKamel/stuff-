/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.City;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author NN
 */
public class MeasurementTest {
    
    static DataAnalyticsImpl da = DataAnalyticsImpl.getInstance();
    static TreeMap<CityImpl,MeasurementsDataBase> db;
    
    public static void citiesByTemperature()throws IOException{ // d d
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        do{
            int x = 0;
            Date d1 = new Date();
            Date d2 = new Date();
            do{
                try{
                    System.out.println("Enter date1 day/month/year");
                    String input = br.readLine();
                    String [] date = input.split("/");
                    d1 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));

                    System.out.println("Enter date2 day/month/year");
                    input = br.readLine();
                    date = input.split("/");
                    d2 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
                    
                    x = 1;
                }catch(Exception e){
                    System.out.println("wrong date try again");
                    x = 0;
                }
            }while(x==0);
            
            if(d1.compareTo(d2)>0){
                Date temp = new Date(d1);
                d1 = new Date(d2);
                d2 = new Date(temp);
            }
            
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            ArrayList<String> names = da.citiesByTemperature(d1, d2);
            System.out.println("SET OF CITIES ORGANIZED BY THEIR INCREASING ORDER OF AVG TEMPERATURE");

            System.out.printf("%s -> %s -> %s -> %s -> %s\n"
            ,names.get(0),names.get(1),names.get(2)
            ,names.get(3),names.get(4));

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
            run = false;
        }while(run == true);

        
    }
    
    public static void hottestTemperature()throws IOException{// d d 
;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        do{
            int x = 0;
            Date d1 = new Date();
            Date d2 = new Date();
            do{
                try{
                    System.out.println("Enter date1 day/month/year");
                    String input = br.readLine();
                    String [] date = input.split("/");
                    d1 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));

                    System.out.println("Enter date2 day/month/year");
                    input = br.readLine();
                    date = input.split("/");
                    d2 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
                    
                    x = 1;
                }catch(Exception e){
                    System.out.println("wrong date try again");
                    x = 0;
                }
            }while(x==0);
            
            if(d1.compareTo(d2)>0){
                Date temp = new Date(d1);
                d1 = new Date(d2);
                d2 = new Date(temp);
            }
            
            TreeMap<String, Double> ht = da.hottestTemperature(d1, d2);

            Iterator<String> its = ht.keySet().iterator();
            Iterator<Double> itd = ht.values().iterator();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("HOTTEST TEMPERATURE DURING %s AND %s\n",d1,d2);
            while(its.hasNext()){
                String s = its.next();
                double d = itd.next();
                System.out.printf("%s -> %.3f\n",s,d);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
            run = false;
                
        }while(run == true);
            
        
    }
    
    public static void averageMeasurements()throws IOException{ // c d d
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        do{
            boolean found = false;
            TreeSet<CityImpl> cities = new TreeSet(db.keySet());
            Iterator<CityImpl> cIt = cities.iterator();
            Iterator<CityImpl> cIt2 = cities.iterator();
            System.out.println("choose from city names");
            cIt2 = cities.iterator();
            while(cIt2.hasNext()){
                City city2= cIt2.next();
                System.out.println(city2.getName().charAt(0)+""+city2.getName().charAt(2)+": "+city2.getName());
            }
            String input = br.readLine();
            input = input.trim();
            input = input.toUpperCase();
            while(cIt.hasNext()){
                City city = cIt.next();
                if(input.charAt(0)==city.getName().charAt(0)&&input.charAt(1)==city.getName().toUpperCase().charAt(2)){
                    found = true;
                    int x = 0;
                    Date d1 = new Date();
                    Date d2 = new Date();
                    do{
                        try{
                            System.out.println("Enter date1 day/month/year");
                            input = br.readLine();
                            String [] date = input.split("/");
                            d1 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));

                            System.out.println("Enter date2 day/month/year");
                            input = br.readLine();
                            date = input.split("/");
                            d2 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));

                            x = 1;
                        }catch(Exception e){
                            System.out.println("wrong date try again");
                            x = 0;
                        }
                    }while(x==0);

                    if(d1.compareTo(d2)>0){
                        Date temp = new Date(d1);
                        d1 = new Date(d2);
                        d2 = new Date(temp);
                    }
                    TreeMap<String,Double> avgs = da.averageMeasurements(city, d1, d2);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.printf("AVERAGE VALUES OF %s C DURING %s AND %s\n"
                            ,city.getName(),d1,d2);

                    System.out.printf("%s -> %.3f C -> %.3f %s -> %.3f mb \n"
                            ,city.getName(),avgs.get("Temperature"),avgs.get("Humidity"),"%",avgs.get("Pressure"));
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    run = false;
                    break;
                        
                    }
                run = false;
            }

            if(found == false){
                run = true;
            }
        }while(run == true);
    }
    
    public static void alerts()throws IOException{ // c d d

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean run = true;
        do{
            boolean found = false;
            TreeSet<CityImpl> cities = new TreeSet(db.keySet());
            Iterator<CityImpl> cIt = cities.iterator();
            Iterator<CityImpl> cIt2 = cities.iterator();
            System.out.println("choose from city names");
            cIt2 = cities.iterator();
            while(cIt2.hasNext()){
                City city2= cIt2.next();
                System.out.println(city2.getName().charAt(0)+""+city2.getName().charAt(2)+": "+city2.getName());
            }
            String input = br.readLine();
            input = input.trim();
            input = input.toUpperCase();
            while(cIt.hasNext()){
                City city = cIt.next();
                if(input.charAt(0)==city.getName().charAt(0)&&input.charAt(1)==city.getName().toUpperCase().charAt(2)){
                    found = true;
                    int x = 0;
            Date d1 = new Date();
            Date d2 = new Date();
            do{
                try{
                    System.out.println("Enter date1 day/month/year");
                    input = br.readLine();
                    String [] date = input.split("/");
                    d1 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));

                    System.out.println("Enter date2 day/month/year");
                    input = br.readLine();
                    date = input.split("/");
                    d2 = new Date(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
                    
                    x = 1;
                }catch(Exception e){
                    System.out.println("wrong date try again");
                    x = 0;
                }
            }while(x==0);
            
            if(d1.compareTo(d2)>0){
                Date temp = new Date(d1);
                d1 = new Date(d2);
                d2 = new Date(temp);
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    da.alert(city, d1, d2);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~$$$$^$$$$~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    run = false;
                    break;
                }
            }

            if(found == false){
                run = true;
            }
        }while(run == true);
    }
    
    public static void main(String []args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        db = da.imread();
        int choice = 5;
        System.out.println("\t--Data Analyzer Program--");
        do{
            System.out.println("_____________________________________________________________");
            System.out.println("*************************************************************");
            System.out.println("Enter a number for a function:");
            System.out.println("1: hottest temperature between two dates");
            System.out.println("2: Average Measurements of certain city between two dates");
            System.out.println("3: all the cities in increasing order of their temperature");
            System.out.println("4: number of alert for a certain city");
            System.out.println("0: Exit");
            System.out.println("_____________________________________________________________");
            System.out.println("*************************************************************");
        
            String input = br.readLine();
            if(Character.isDigit(input.charAt(0))){
            choice = Integer.parseInt(Character.toString(input.charAt(0)));
            
            switch(choice){
                case 0: break;
                case 1: hottestTemperature(); break;
                case 2: averageMeasurements(); break;
                case 3: citiesByTemperature(); break;
                case 4: alerts(); break;
                default: choice = 1; System.out.println("wrong input, try again");
            }
            }else {System.out.println("wrong input, try again"); choice = 1;}
            
        
        }while(choice!=0);
        //while()
        
        
        /*Date d1 = new Date(1,1,1999);
        Date d2 = new Date(01,01,2050);
        CityImpl city = new CityImpl("Riyadh", new GPSImpl(24.7136,46.6753));
        /*
        System.out.println("Date before next:" + d1);
        d1.nextDay();
        System.out.println("Date after next:" + d1);
        System.out.println("_________________________________");
        
        da.hottestTemperature(d1, d2);
        
        System.out.println("_________________________________");
        da.averageMeasurements(city, d1, d2);
        System.out.println("_________________________________");
        da.citiesByTemperature(d1, d2);
        System.out.println("_________________________________");
        
        */
        
        
        
        
        
        
        
        
        
    }
}
