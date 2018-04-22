/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Inteface.City;
import Inteface.Sensor;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author NN
 */
public class MeasurementsCollector {

    private ArrayList<Measurement> data = new ArrayList();

    public MeasurementsCollector(ArrayList<Measurement> m) {
        data = m;
    }

    public ArrayList<SensorImpl> getSensorArraybyCityDate(Date d1, Date d2, SensorImpl v) {
        Iterator<Measurement> it = data.iterator();
        ArrayList<SensorImpl> sensors = new ArrayList();

        Measurement m = new Measurement();
        Sensor s = new SensorImpl();
        while (it.hasNext()) {
            m = it.next();

            if (v instanceof TemperatureImpl) {
                    if (m.getDay().compareTo(d1) >= 0 && m.getDay().compareTo(d2) <= 0) {
                        TemperatureImpl t = m.getTemperature();
                        sensors.add(t);
                    }
            } else if (v instanceof PressureImpl) {
                    if (m.getDay().compareTo(d1) >= 0 && m.getDay().compareTo(d2) <= 0) {
                        PressureImpl t = m.getPressure();
                        sensors.add(t);
                    }
            } else if (v instanceof DistanceImpl) {
                    if (m.getDay().compareTo(d1) >= 0 && m.getDay().compareTo(d2) <= 0) {
                        DistanceImpl t = m.getDistance();
                        sensors.add(t);
                    }
            } else if (v instanceof HumidityImpl) {
                    if (m.getDay().compareTo(d1) >= 0 && m.getDay().compareTo(d2) <= 0) {
                        HumidityImpl t = m.getHumidity();
                        sensors.add(t);
                    }
            }
        }
    return sensors;
    }
}
