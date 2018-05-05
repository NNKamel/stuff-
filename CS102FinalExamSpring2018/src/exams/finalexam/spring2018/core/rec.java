/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams.finalexam.spring2018.core;

/**
 *
 * @author 172-m7
 */
public class rec {
    
    //base case
    //f(x,1) = (x^0)/1 = 1

    
    //general case
    //f(x,1) = 1
    //f(x,2) = 1 + (x^1)/2
    //f(x,3) = 1 + (x^1)/2 + (x^2)/3
    //f(x,4) = 1 + (x^1)/2 + (x^2)/3 + (x^3)/4
    //f(x,n) = f(x,n-1) + (x^(n-1))/n 
    
    public static double f(double x, int n) {
        if (!(Math.abs(x) < 1)) throw new IllegalArgumentException();
        if (n < 1) throw new IllegalArgumentException();
        
        if(n==1) return 1;

        return f(x, n - 1) + Math.pow(x, n - 1) / n;
    }

    public static void main(String[] args) {
        System.out.println("x=0.1, n=3: "+f(0.1, 3));
        System.out.println("x=0.1, n=10: "+f(0.1, 10));
    }
}
