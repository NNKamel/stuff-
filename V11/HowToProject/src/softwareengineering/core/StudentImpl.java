/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softwareengineering.core;

import softwareengineering.core.Date;

/**
 *
 * @author akoubaa
 */
public class StudentImpl {
    
    //attribute: instance variable
    private String id;
    private String firstName;
    private String lastName;
    private double grade;
    private int age;
    
    private Date birthDate;
        
    public static int count;
    
    
    //constructor: it is a method with three features:
    // 1- it has the same number of the class
    // 2- it is public
    // 3- it does not return anything (not event void)
    
    public StudentImpl(){
        count = count +1;
    }
    
    
    public StudentImpl(String sid, String fn, String ln, double g, int ag, Date bd){
        //id = sid;
        //firstName = fn;
        //lastName = ln;
        //grade =g;
        //age = ag;
        this.setId(sid);
        setFirstName(fn);
        setLastName(ln);
        setGrade(g);
        setAge(ag);
        setBirthDate(bd);
        count = count +1;
    }
    
    public StudentImpl(String id, String firstName, String lastName, Date bd){
        this(id, firstName, lastName, 0.0, 17, bd);
    }
    
    
    //methods
    
    public void displayInfo(){
        System.out.printf("Student%s: %s %s has a grade %.2f\n" ,
                this.id,this.getFirstName(),lastName,getGrade());
    }
    
    
    public Date getBirthDate(){
        return birthDate;
    }
    
    public void setBirthDate(Date bd){
        birthDate = bd;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    
    
    public String getLastName(){
        return lastName;
    }
    
    public String getId(){
        return id;
    }
    
    public double getGrade(){
        return grade;
    }
    
    public int getAge(){
        return age;
    }
    
    public void setId(String sid){
        if (sid.length()==5)
        id = sid;
        else 
            throw new IllegalArgumentException("ID must have 5 characters");
    }
    
    public void setAge(int a){
        if (a>16)
        age = a;
        else throw new IllegalArgumentException("age must be greater than 16");
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setGrade(double grade){
        if ((grade>=0)&&(grade<=4))
            this.grade = grade;
        else throw new IllegalArgumentException("grade must be between 0 and 4");
    }
    
    @Override
    public String toString(){
        return String.format("%s %s : grade = %.2f",
                firstName, this.getLastName(), this.grade);
    }
    
    
}
