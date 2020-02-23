/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IR;

import java.util.ArrayList;

/**
 *
 * @author Salah_Mer
 */
public class doc {
    String nom="documnet n:";
    String patch="";
    double size;
    ArrayList<mot> motecle=new ArrayList();
    ArrayList<String> motes=new ArrayList(); 
      int nbrdoc;
      int nmbrmot;
    int nbrtotal;
    public doc(int nbrdoc) {
        this.nbrdoc = nbrdoc;
        this.nom+=" "+nbrdoc;
         motecle=new ArrayList();
         nbrtotal=0;
         motes=new ArrayList(); 
    }

    public ArrayList getMotecle() {
        return motecle;
    }

    public void setMotecle(ArrayList motecle) {
        this.motecle = motecle;
    }
    public int getsize()
    {
        return this.motecle.size();
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "document "+nbrdoc+":    nombre de motes cle:"+this.getsize();
    } 
    public String toString1() {
        return "range-------mote-------freq";
    } 

    public int getNmbrmot() {
        return nmbrmot;
    }

    public void setNmbrmot(int nmbrmot) {
        this.nmbrmot = nmbrmot;
    }
    
    public void  afficherdocumnet()
    {
      System.out.println("documnt no: "+nbrdoc+" taille: "+nbrtotal);
      System.out.println("range-------mote-------freq");
      for(int i=0;i<motecle.size();i++)
      {
          System.out.println(motecle.get(i).toString());
      }
    }
    public void add_mote(String mot ,int rang)
    {
        int comp=0 ,index=-1; 
        for(int i=0;i<motecle.size();i++)
        {
           String str= motecle.get(i).getMote();
               if(mot.equals(str))
               { 
                   comp++; index=i; 
                   break;
               }
        }
         if(comp!=0)
         {
             motecle.get(index).freq++;
         }   
         else
         {
           mot m=new mot(rang,mot);
           motecle.add(m);
         }
         String m= new String(""+mot);
         motes.add(m);
       nbrtotal++;
    }

    public int getNbrtotal() {
        return nbrtotal;
    }

    public void setNbrtotal(int nbrtotal) {
        this.nbrtotal = nbrtotal;
    }
    
    public int recheremot( String mot)
    {
       for(int i=0;i<motecle.size();i++)
       {
          mot m=motecle.get(i);
           if(mot.equals(m.getMote())) 
               return 1;
       }
        return 0;
    }
    public int recheremot1(String mot)
    {
       for(int i=0;i<motecle.size();i++)
       {
          mot m=motecle.get(i);
           if(mot.equals(m.getMote())) 
               return m.getFreq();
       }
        return 0;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getNom(){
        return nom;
    }
    
    
}
