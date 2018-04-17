/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package softwareengineering.Interface;

import java.util.TreeMap;
import softwareengineering.core.Date;

/**
 *
 * @author 216110186
 */
public interface DatabaseEvents implements Database{
    
    TreeMap <Date, Event> db = new TreeMap();
    
}
