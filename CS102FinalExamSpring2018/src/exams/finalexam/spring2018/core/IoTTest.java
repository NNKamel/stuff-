/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.core;

import exams.finalexam.spring2018.interfaces.Database;
import exams.finalexam.spring2018.interfaces.Sensor;
import exams.finalexam.spring2018.interfaces.SensorFactory;
import java.io.FileNotFoundException;
import java.util.Iterator;


public class IoTTest {
       public static void main(String []args) throws FileNotFoundException{
           
           IoTApp app = new IoTApp();
           
           Date d = new Date(2,1,2018);
           Date d1 = new Date(3,1,2018);
           Date d2 = new Date(1,1,2018);
           
           System.out.println(app.withinDateInterval(d, d1, d2));
           
           
           app.loadData("sensor-data-final.txt");
           //System.out.println(app.db.getDB());
           
           System.out.println(app.maxTemperature ("all", new Date(1,1,2018), new Date(10,1,2018)));
           System.out.println(app.maxTemperature ("riyadh", new Date(1,1,2018), new Date(10,1,2018)));
           System.out.println(app.maxTemperature ("Jeddah", new Date(1,1,2018), new Date(10,1,2018)));
       
           
           System.out.println(app.getData("riyadh", "pressure"));
           
           System.out.println(app.getData("madinah", "distance"));
           
           System.out.println(app.getAverageBySensorType("humidity"));
           
       }
       
}

