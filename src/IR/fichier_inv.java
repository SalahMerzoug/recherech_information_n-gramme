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
public class fichier_inv {
    int [][] corpus;
     ArrayList <mot> motes;
     double [] IDF;
  int nbrdocm;
    public fichier_inv() 
    {
       motes=new ArrayList<>();
    }
     
     public void add_mote(String mot)
    {
        int comp=0 ,index=-1; 
        for(int i=0;i<motes.size();i++)
        {
           String str= motes.get(i).getMote();
               if(mot.equals(str))
               { comp++; index=i; break;}
        }
         if(comp!=0)
         {
             motes.get(index).freq++;
         }   
         else
         {
           mot m=new mot(0,mot);
           motes.add(m);
         }
    }
     public void creationcurpus( ArrayList<doc> listdocs)
     {
         nbrdocm=listdocs.size();
        
                 corpus=new int[nbrdocm][motes.size()];
                 IDF=new double[motes.size()];
         for(int i=0;i<nbrdocm;i++)
         {
             doc d=listdocs.get(i);
             for(int j=0;j<motes.size();j++)
             {
                 String m=motes.get(j).getMote();
               corpus[i][j]= d.recheremot(m);
             }
         }
        //IDF(); 
     }
     public void IDF()
     {
         for(int j=0;j<motes.size();j++)
             {
                 int sum=0;
                 for(int i=0;i<nbrdocm;i++)
                    {
                     sum+= corpus[i][j];
                    }
                if(sum!=0) 
                    IDF[j]=Math.log(((double)nbrdocm)/sum);
                else 
                    IDF[j]=0;
                
             }
     }
   public void affichem()
   {
      for(int j=0;j<motes.size();j++)
             {
                 System.out.println(motes.get(j).toString());
             }
   }
   public void affiche ()
   {
      System.out.println(""+nbrdocm);
       for(int i=0;i<nbrdocm;i++)
         {
             
             System.out.print((i+1)+": ");
             for(int j=0;j<motes.size();j++)
             {
               
               System.out.print(corpus[i][j]+"   ");
             }
             System.out.println();
         } 
   }
   public double getIDF(String m)
   {
       for(int j=0;j<motes.size();j++)
             {
              String ms=motes.get(j).getMote();
              if(ms.equals(m))
              {
                 return IDF[j]; 
              }
             }
       return 0;
   }
}
