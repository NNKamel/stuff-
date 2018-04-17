/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering.core;

import java.util.TreeMap;
import softwareengineering.Interface.DatabaseEvents;
import softwareengineering.Interface.Event;

/**
 *
 * @author 216110031
 */
public class DatabaseEventsImpl implements DatabaseEvents{

    TreeMap <Date, Event> db = new TreeMap();
    
    public DatabaseEventsImpl(){
    }
    
    public boolean addEvent(Date d, Event e){
        try{
            if(db.keySet().contains(d)){
                IllegalArgumentException r = new IllegalArgumentException();
                throw r;
            }
            db.put(d, e);
            return true;
            
        } catch (IllegalArgumentException r){
            System.out.println("error cant add event");
            return false;
        }
    }
    
    public void listEvents(){
        for (int i = 0; i < db.keySet().size(); i++) {
            
            
            
        }
    }
    
}
