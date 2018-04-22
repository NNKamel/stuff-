/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;





public class Date implements Comparable<Date>{

    public Date() {
    }
    
    
    

    @Override
    public int compareTo(Date o) {
        if(this.year>o.year){
            return 1;
        }
        else if(this.year == o.year){
            if(this.month>o.month){
                return 1;
            }
            else if(this.month == o.month){
                if(this.day>o.day){
                    return 1;
                }
                else if(this.day==o.day){
                    return 0;
                }
                else return -1;
            }
            else return -1;
        }
        else return -1;
    }
    private int day;
    private int month;
    private int year;
    
    private static final int [] dayPerMonth =
    {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    public Date(int d, int m, int y){
        setYear(y);
        setMonth(m);
        setDay(d);
    }

    public Date(Date date) {
        this(date.day,date.month,date.year);
    }
    
    public void nextDay(){
        int x = 0;
        while(x ==0){
            try{
            this.setDay(this.getDay()+1);
            x = 1;
            } catch (IllegalArgumentException e){
                x = 0;
            }
            if(x == 0){
                try{
                    this.setDay(1);
                    this.setMonth(this.getMonth()+1);
                    x = 1;
                } catch (IllegalArgumentException e){
                    x = 0;
                }
                if(x == 0){
                    this.setDay(1);
                    this.setMonth(1);
                    this.setYear(this.getYear()+1);
                    x = 1;
                }
            }
        }
    }
    
    
    public void setYear(int year){
        if((year>1950) && (year<2100))
            this.year=year;
        else
            throw new IllegalArgumentException("year must be 1950-2100");
    }
    
    public int getYear(){
        return year;
    }
    
    public void setMonth(int m){
        if ((m>0)&&(m<=12))
            month = m;
        else
            throw new IllegalArgumentException("month must be 1-12");
    }
    public int getMonth(){
        return month;
    }
    
    public void setDay(int d){
        if ((d>0)&&(d<=dayPerMonth[month]))
            day = d;
        //for leap year
        else
        if ((month==2) && (d==29)&&(year%400==0 || year%4==0 && year%100!=0))
            day = d;
        else
            throw new IllegalArgumentException("day is out of range"); 
    }
    
    public int getDay(){
        return day;
    }
    
    public String toString(){
        return String.format("%02d/%02d/%d", getDay(), month, year);
    }
    
      
}
