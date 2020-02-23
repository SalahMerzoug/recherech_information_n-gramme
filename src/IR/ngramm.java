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
public class ngramm {
    int n;
    doc doc;
    ArrayList<mot> query;
    int laplace=0;
   int nbrtotaltkn=0;
    public ngramm(int n) {
        this.n = n;
        laplace=0;
    }

    public ngramm(int n, ArrayList<mot> query) {
        this.n = n;
        this.query = query;
        laplace=0;
    }

    public int getN() {
        return n;
    }
   
    public void setN(int n) {
        this.n = n;
    }

    public doc getDoc() {
        return doc;
    }

    public void setDoc(doc doc) {
        this.doc = doc;
    }

    public ArrayList<mot> getQuery() {
        return query;
    }

    public void setLaplace(int laplace) {
        this.laplace = laplace;
    }
   
    public void setQuery(ArrayList<mot> query) {
        this.query = query;
    }
  public double score_ungramm()
  {
     double p=1;
     for(int i=0;i<query.size();i++)
     {
        mot m=query.get(i);
         p*= ungramm(m);     
     }
     return p;
  }
  public double ungramm(mot m)
  {
     if(laplace==1)
           return ((double)(doc.recheremot1(m.getMote())+1)/(double)(doc.getNbrtotal()+nbrtotaltkn));
     return ((double)doc.recheremot1(m.getMote())/(double)doc.getNbrtotal());
  }
   public double bigramme(String m1,String m2)
   {
      double p=0;
      int comp=0;
      ArrayList<String> motes =doc.motes;
      for(int i=1;i<motes.size();i++)
      {
          String m=motes.get(i);
          if(m.equals(m2) && m1.equals(motes.get(i-1)))
          {
              comp++;
          }
          
      }
      if(laplace==1 )
      { p=(comp+1)/((double)doc.recheremot1(m1)+nbrtotaltkn);}
      else
      {
          if(comp!=0) 
                 p=comp/((double)doc.recheremot1(m1));
          else 
              p=0;
       }
       
      return p;
   }
   public double score_bigramme()
   {
       double p=1;
       p=ungramm(query.get(0));
       for(int i=1;i<query.size();i++)
       {
           mot m1 =query.get(i-1);
            mot m2 =query.get(i);
            p*=bigramme(m1.getMote(), m2.getMote());
       }
     p*=ungramm(query.get((query.size()-1)));
    return p;   
   }
    public double thrigramme(String m1,String m2,String m3)
   {
      double p=0;
      int comp1_2=0,comp3=0;
      
      ArrayList<String> motes =doc.motes;
      for(int i=2;i<motes.size();i++)
      {
          String m=motes.get(i);
          if(m1.equals(motes.get(i-2)) && m2.equals(motes.get(i-1)))
          {
              comp1_2++;
             if( m.equals(m3))
             {
                 comp3++;
             }
          }
          
      }
      if(laplace==1 )
      { p=(comp3+1)/((double)comp1_2 +nbrtotaltkn);}
      else
      {
          if(comp3!=0) 
                 p=comp3/((double)comp1_2);
          else 
              p=0;
       }
       
      return p;
   }
   public double score_thrigramme()
   {
       double p=1;
       p=bigramme(query.get(0).getMote(),query.get(1).getMote());
       for(int i=2;i<query.size();i++)
       {
           mot m1 =query.get(i-2);
           mot m2 =query.get(i-1);
            mot m3 =query.get(i);
            p*=thrigramme(m1.getMote(), m2.getMote(),m3.getMote());
       }
     p*=bigramme(query.get((query.size()-2)).getMote(),query.get((query.size()-1)).getMote());
    return p;   
   }
   public  double run_Ngramm(int n)
   {
       switch(n)
       {
           case 1:return this.score_ungramm();
           
           case 2: return this.score_bigramme();
           case 3: return this.score_thrigramme();
       }
      return this.score_ungramm();
   }

    public int getNbrtotaltkn() {
        return nbrtotaltkn;
    }

    public void setNbrtotaltkn(int nbrtotaltkn) {
        this.nbrtotaltkn = nbrtotaltkn;
    }
   
}
