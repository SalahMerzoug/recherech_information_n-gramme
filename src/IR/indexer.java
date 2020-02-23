/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IR;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Salah_Mer
 */
public class indexer {
    ArrayList<doc> listdocs;
    fichier_inv fichier;
    doc d;
     int nbrmots;
    Steamer s;
    String[] stopWord = { " a ", " about ", " did ", " above ", " above ", " across ", " after ",
			" afterwards ", " again ", " against ", " all ", " almost ", " alone ", " along ", " already ", "also",
			" although ", " always ", " am ", " among ", " amongst ", " amoungst ", " amount ", " an ", " and ",
			" another ", " any ", " anyhow ", " anyone ", " anything ", " anyway ", " anywhere ", " are ", " around ",
			" as ", " at ", " back ", " be ", " became ", " because ", " become ", " becomes ", " becoming ", " been ",
			" before ", " beforehand ", " behind ", " being ", " below ", " beside ", " besides ", " between ",
			" beyond ", " bill ", " both ", " bottom ", " but ", " by ", " call ", " can ", " cannot ", " cant ",
			" co ", " con ", " could ", " couldnt ", " cry ", " de ", " describe ", " detail ", " do ", " done ",
			" down ", " due ", " during ", " each ", " eg ", " eight ", " either ", " eleven ", " else ", " elsewhere ",
			" empty ", " enough ", " etc ", " even ", " ever ", " every ", " everyone ", " everything ", " everywhere ",
			" except ", " few ", " fifteen ", " fify ", " fill ", " find ", " fire ", " first ", " five ", " for ",
			" former ", " formerly ", " forty ", " found ", " four ", " from ", " front ", " full ", " further ",
			" get ", " give ", " go ", " had ", " has ", " hasnt ", " have ", " he ", " hence ", " her ", " here ",
			" hereafter ", " hereby ", " herein ", " hereupon ", " hers ", " herself ", " him ", " himself ", " his ",
			" how ", " however ", " hundred ","I" ," ie ", " if ", " in ", " inc ", " indeed ", " interest ", " into ",
			" is ", " it ", " its ", " itself ", " keep ", " last ", " latter ", " latterly ", " least ", " less ",
			" ltd ", " made ", " many ", " may ", " me ", " meanwhile ", " might ", " mill ", " mine ", " more ",
			" moreover ", " most ", " mostly ", " move ", " much ", " must ", " my ", " myself ", " name ", " namely ",
			" neither ", " never ", " nevertheless ", " next ", " nine ", " no ", " nobody ", " none ", " noone ",
			" nor ", " not ", " nothing ", " now ", " nowhere ", " of ", " off ", " often ", " on ", " once ", " one ",
			" only ", " onto ", " or ", " other ", " others ", " otherwise ", " our ", " ours ", " ourselves ", " out ",
			" over ", " own ", " part ", " per ", " perhaps ", " please ", " put ", " rather ", " re ", " same ",
			" see ", " seem ", " seemed ", " seeming ", " seems ", " serious ", " several ", " she ", " should ",
			" show ", " side ", " since ", " sincere ", " six ", " sixty ", " so ", " some ", " somehow ", " someone ",
			" something ", " sometime ", " sometimes ", " somewhere ", " still ", " such ", " system ", " take ",
			" ten ", " than ", " that ", " the ", " their ", " them ", " themselves ", " then ", " thence ", " there ",
			" thereafter ", " thereby ", " therefore ", " therein ", " thereupon ", " these ", " they ", " thickv ",
			" thin ", " third ", " this ", " those ", " though ", " three ", " through ", " throughout ", " thru ",
			" thus ", " to ", " together ", " too ", " top ", " toward ", " towards ", " twelve ", " twenty ", " two ",
			" un ", " under ", " until ", " up ", " upon ", " us ", " very ", " via ", " was ", " we ", " well ",
			" were ", " what ", " whatever ", " when ", " whence ", " whenever ", " where ", " whereafter ",
			" whereas ", " whereby ", " wherein ", " whereupon ", " wherever ", " whether ", " which ", " while ",
			" whither ", " who ", " whoever ", " whole ", " whom ", " whose ", " why ", " will ", " with ", " within ",
			" without ", " would ", " yet ", " you ", " your ", " yours ", " yourself ", " yourselves ", "1", "2", "3",
			"4", "5", "6", "7", "8", "9", "0", " terms ", " CONDITIONS ", " conditions ", " values ", " interested. ",
			" care ", " sure ", " . ", " ! ", " @ ", " # ", " $ ", " % ", " ^ ", " & ", " : ", " ; ", " , ", " < ",
			" . ", " > ", " / ", " _ ", " - ", " = ", " contact ", " grounds ", " buyers ", " tried ", " said, ",
			" plan ", " value ", " principle. ", " forces ", " sent: ", " is, ", " was ", " like ", " discussion ",
			" tmus ", " diffrent. ", " layout ", " area. ", " thanks ", " thankyou ", " hello ", " bye ", " rise ",
			" fell ", " fall ", " psqft. ", " http:// ", " km ", " miles " };

    public indexer() {
        s=new Steamer();
        //nbrmots=0;
        listdocs=new ArrayList<doc>();
        fichier=new fichier_inv();
    }
    public  void  elimination_Steamer(String[] term,int ind)
    {
      String tr;
        for(int i=0;i<term.length;i++)
        {
            term[i]=term[i].toLowerCase();
            if(!is_stopword(term[i]))
            {
                tr=s.runStemmer(term[i]);
               d.add_mote(tr,ind);
               fichier.add_mote(tr);
              // nbrmots++;
            }
          ind++;   
        }
     // return nwterms;
    }
    public boolean is_stopword( String term)
    {
        for(int i=0;i<stopWord.length;i++)
        {
              if(term.length()<=1)
                   return true;
            String  terms =" "+term+" ";
            if(terms.equals(stopWord[i].toLowerCase()))
                return true;
          
        }
        return false;
    }
    
     public  void indextion(BufferedReader in, int n) throws IOException
	{
		
		int i=1;
              //  nbrmots=0;
              d= new doc(n);
            
                 ArrayList motesn=new ArrayList(); 
		String str;
		while((str=in.readLine()) != null)
		{
			//String [] bb=str.split(" ");         
                          String terms[] = str.split("[^a-zA-Z0-9]+");
                      this.elimination_Steamer(terms, i);
                    i+=str.length();
                }
       //  d.setNmbrmot(nbrmots);
	 this.listdocs.add(d);
	}
      public void  reupererdoc() throws FileNotFoundException, IOException
    {
        File file = new File("Collection_TIME/");
		File[] files = file.listFiles();
		BufferedReader a[] = new BufferedReader[files.length];
                String patch="";
                String nom;
                double size=0;
		for(int i=0;i<files.length;i++)
                {
			a[i] = new BufferedReader(new FileReader(files[i].getAbsolutePath()));
                        patch=files[i].getAbsolutePath();
                        int aa=files[i].getName().indexOf(".");
                        nom=files[i].getName().substring(0, aa);
                        size=files[i].getTotalSpace();
                          indextion(a[i],i+1);
                        d.setPatch(patch);
                        d.setNom(nom);
                        d.setSize(size);
	        }
                
              fichier.creationcurpus(listdocs);
        
    }
    public void affichierdoc()
    {
        for(int i=0;i<listdocs.size();i++)
        {
            doc ds=listdocs.get(i);
            ds.afficherdocumnet();
            System.out.println("/////////////////////////////////////////////////////////////////");
        }
       
       // fichier.affichem();
      //  fichier.affiche();
    }

    public ArrayList<doc> getListdocs() {
        return listdocs;
    }

    public fichier_inv getFichier() {
        return fichier;
    }
    public  ArrayList<mot>  elimination_Steame_qurey(String[] term)
    {
      String tr;
      doc q= new doc(0);
       ArrayList<mot> motes=new  ArrayList<mot> ();
        for(int i=0;i<term.length;i++)
        {
            term[i]=term[i].toLowerCase();
            if(!is_stopword(term[i]))
            {
                tr=s.runStemmer(term[i]);
              // q.add_mote(tr,(i+1));
               mot m=new mot((i+1),tr);
              motes.add(m);
              // nbrmots++;
            }
          
        }
     return motes;
    }
  
   public double idf(mot m)
   {
      return  fichier.getIDF(m.getMote());
   }
    public double tf_idf(mot m,double ft)
    {
        double score=0,idf=0;
        int com=0;
         int taille=listdocs.size();
        String ms=m.getMote();
        for(int i=0; i<taille;i++)
        {
            if(listdocs.get(i).recheremot(ms)!=0)
            {
                com++;
            }
        }
         double a=((double)taille)/((double)com);
        idf=Math.log10(a);
        score=ft*idf;
        return score;
    }
}
