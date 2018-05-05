/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.interfaces;

import java.util.ArrayList;

/**
 *
 * @author akoubaa
 */
public interface Node {
    public abstract ArrayList<Measurable> getSensors();
    public abstract WiFi getWiFi();
}
