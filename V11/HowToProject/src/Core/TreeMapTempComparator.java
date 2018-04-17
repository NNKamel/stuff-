/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author NN
 */
public class TreeMapTempComparator implements Comparator<String>{
    HashMap<String,Double> map = new HashMap<>();

    public TreeMapTempComparator(HashMap<String,Double> map){
        this.map.putAll(map);
    }
    
    @Override
    public int compare(String o1, String o2) {
        if(map.get(o1) <= map.get(o2))
            return 1;
        else return -1;
    }
}
