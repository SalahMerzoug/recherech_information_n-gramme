/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IR;

/**
 *
 * @author Salah_Mer
 */
public class mot {
    int range;
    String mot;
    int freq; // frequence
   

    public mot(int range, String mote, int freq) 
    {
        this.range = range;
        this.mot = mote;
        this.freq = freq;
    }
     public mot(int range, String mote) 
    {
        this.range = range;
        this.mot = mote;
        this.freq = 1;
    }
    

    public mot()
    {
         this.range = 0;
        this.mot = "";
        this.freq = 0;
    }
     
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getMote() {
        return mot;
    }

    public void setMote(String mote) {
        this.mot = mote;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) 
    {
        this.freq = freq;
    }
    public double Ft(int nbrt)
    {
        return (((double)freq)/(double)nbrt); 
    }
    @Override
    public String toString() {
        return " "+range+"        "+ mot+"        "+freq;
    }
    
    
}
