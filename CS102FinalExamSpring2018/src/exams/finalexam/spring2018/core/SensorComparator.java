/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.core;

import exams.finalexam.spring2018.interfaces.Sensor;
import java.util.Comparator;

/**** Question 3: Sensor Comparator *****/

public class SensorComparator<T extends Sensor> implements Comparator<T>{

    @Override
    public int compare(T s1, T s2) {
        if(s1.getValue()>s2.getValue()){
            return 1;
        }
        else if(s1.getValue()<s2.getValue()){
            return -1;
        }
        else return 0;
    }  
}