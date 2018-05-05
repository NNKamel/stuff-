/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.interfaces;

import exams.finalexam.spring2018.core.Date;
import java.util.Set;

/**
 *
 * @author akoubaa
 */
public interface Measurement {
    public Date getDate();
    Set<Sensor> getSensors();
    String getCityName();
    public GPS getGps();
}
