/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IR;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Salah_Mer
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
      // NGram N=new NGram(2);
    // System.out.println(N.distance("salah", "salah"));
   //  System.out.println(N.distance("slaah", "salah"));
    // System.out.println(N.distance("halas", "salah"));
    // System.out.println(N.distance("merzdz", "salah"));
      BufferedReader  reader = null;
        int nbLigne = 0;
 
        
            reader = new BufferedReader(new FileReader("C:/Users/Salah_Mer/Desktop/time/TIME.ALL"));
            String line = reader.readLine();
             System.out.println(""+line.charAt(0));
            /*while (line!=null) {
                nbLigne ++;
                System.out.println(line);
                line = reader.readLine();
            }*/
 
        
    }
}
