package exams.finalexam.spring2018.core;

import exams.finalexam.spring2018.interfaces.Measurement;
import exams.finalexam.spring2018.interfaces.Sensor;
import exams.finalexam.spring2018.interfaces.Temperature;
import java.util.Comparator;


public class MeasurementTemperatureComparator implements Comparator<Measurement>{

    @Override
    public int compare(Measurement m1, Measurement m2) {
        double t1=-9999;
        double t2=-9999;
        for (Sensor s : m1.getSensors()){
            if (s instanceof Temperature){
                t1=s.getValue();
                break;
            }
        }
        if (t1==-9999) throw new IllegalArgumentException("No temperature found in m1");
        for (Sensor s : m2.getSensors()){
            if (s instanceof Temperature){
                t2=s.getValue();
                break;
            }
        }
        if (t2==-9999) throw new IllegalArgumentException("No temperature found in m2");
        if (t1>t2)
            return 1;
        else if (t1<t2)
            return -1;
        return 0;
        
    }
    
}
