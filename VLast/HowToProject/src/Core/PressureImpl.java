/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.Pressure;

/**
 *
 * @author NN
 */
public class PressureImpl extends SensorImpl implements Pressure{

    public PressureImpl() {
    }

    
    
    public PressureImpl(String unit, double value) {
        super(unit, value);
    }
    
}
