/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IR;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Salah_Mer
 */
public class ML {
    indexer indx;
    ArrayList<mot> query;
     ArrayList<String> RS=new ArrayList<>();
     ArrayList<Double[]> RP =new ArrayList<>();;
     int n;
    ngramm ML_Ngramm;
    ArrayList<resulta>  res=new ArrayList<>();
     
    public ML(int n, String[] quer) {
        this.RP = new ArrayList<>();
       this.n=n;
     
        indx=new indexer();
       this.query = indx.elimination_Steame_qurey(quer);
       ML_Ngramm= new ngramm(n, query);
         
    }
   public void SetRS(String[] quer)
   {
      for(int i=0;i<quer.length;i++)
      {
        RS.add(quer[i]);
      }
   }
    public ML(int n) {
        this.RP = new ArrayList<>();
        this.n = n;
          indx=new indexer();
         ML_Ngramm= new ngramm(n);     
    }

    public ML() {
        this.RP = new ArrayList<>();
         indx=new indexer(); 
    }
    public void indexationquery(String quer)
    {
         String[] trmsq=quer.split(("[^a-zA-Z0-9]+"));
        this.query = indx.elimination_Steame_qurey(trmsq); 
        ML_Ngramm.setQuery(query);
       for(int i=0;i<query.size();i++)
       {
           mot m=query.get(i);
           System.out.println(m.toString());
       }
    }
    public ArrayList<mot> getQuery() {
        return query;
    }

    public void setQuery(ArrayList<mot> query) {
        this.query = query;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
     public void recuperedoc_indexation() throws IOException
     {
         indx.reupererdoc();
         
     }
     
   public ArrayList<resulta> runLM( int select)
   {
       
       res=new ArrayList<>();
       
       double score;
        resulta re;
       int taille=indx.getListdocs().size();
       ML_Ngramm.setQuery(query);
         
        ML_Ngramm.setNbrtotaltkn(indx.fichier.motes.size());
       for(int i=0 ;i<taille;i++)
       {
          doc d=indx.getListdocs().get(i);
          ML_Ngramm.setDoc(d);
          score=ML_Ngramm.run_Ngramm(select);
          
          re=new resulta(d, score,tf_idf(i));
         
         insertres(re);
       }
       return res;
   }
   
   public void insertres( resulta re)
   {
      double l =re.getScore();
      int k=0;
      
     if(res.size()==1)
     {
         if(l>res.get(0).getScore())
          {
              res.add(0, re);
              k=1;
          }
       
     }
     else
        {
         for(int i=0;i<res.size();i++)
          {
          if(l>res.get(i).getScore())
          {
              res.add((i), re);
              k=1;
              break;
          }
          }
        }
     if(k==0)
         res.add(re);
       
   }
   public double tf_idf( int d)
      {
          double idf,tf,score=0;
          
          for(int i=0;i<query.size();i++)
          {
            mot m=query.get(i);
             
              doc ds=indx.getListdocs().get(d);
              tf=ds.recheremot1(m.getMote())/((double)ds.getNbrtotal());
               if(tf!=0)
                   score=indx.tf_idf(m,tf);
               else
                   score=0;
             
          }
          return score;
      }
   public ArrayList<doc> getlist()
   {
       return this.indx.getListdocs();
   }
    public void  setLaplace(int a)
    {
        ML_Ngramm.setLaplace(a);
    }
    public int cherech(String m)
    {
        for(int i=0;i<RS.size();i++)
        {
            String s=RS.get(i);
            if(s.equals(m))
                return 1;
           
        }
      return 0;
    }
   public void Rapple_pre()
   {
       int taille =RS.size();
       Double[] r_p;
       int r=0;
      RP= new ArrayList<>();
       
       int par=1;
        r_p=new Double[2];
        r_p[0]=0.0;
        r_p[1]=100.0;
        RP.add(r_p);
       for(int i=0;i<res.size();i++)
       {
           String textn=res.get(i).getD().getNom();
           if(cherech(textn)==1)
           {
               //p++;
                r_p=new Double[2];
                 r++;
                 r_p[0]=(((double)r)/((double)taille))*100;
                 r_p[1]=(((double)r)/((double)par))*100;
               RP.add(r_p); 
               if(r_p[0]>=90)
                   break;
           }   
            par++;
          
       }
   }

    public ArrayList<Double[]> getRP() {
        return RP;
    }
   
   
}
