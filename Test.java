/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project216110186_216110031;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author NN
 */
public class Test {
    
    public static void main(){
        Scanner in = new Scanner(System.in);
        HHSR List = new HHSR();
        
        System.out.println("Enter data");
        String input = in.nextLine();
        
        StringTokenizer st = new StringTokenizer(input);
        
        int chooser = Integer.parseInt(st.nextToken());
        while(input!=null){
        switch(chooser){
            case 1: 
                String c1 = st.nextToken();
                String c2 = st.nextToken();
                String D = st.nextToken();
                String id = st.nextToken();
                String n = st.nextToken();
                String name = "";
                while(!n.isEmpty()){
                    name+= " " + n;
                    n = st.nextToken();
                }
                name.trim();
                List.saveNode(c1,c2,D,id,name); break;
            case 2:
                c1 = st.nextToken();
                c2 = st.nextToken();
                D = st.nextToken();
                id = st.nextToken();                
                List.removeNode(c1,c2,D,id); break;
            case 3: 
                c1 = st.nextToken();
                c2 = st.nextToken();
                D = st.nextToken();                
                List.removeAllNodes(c1,c2,D); break;
            case 4: 
                
                List.searchAllNodes(); break;
        }
            
        }
    }
}
