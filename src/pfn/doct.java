/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfn;

/**
 *
 * @author Salah_Mer
 */
public class doct {
    String nomd;
    String nbrt;
    String totl;
    String score;
    String size;
    String patch;
     String ft_idf;
    public doct(String nomd, int nbrt, int totl) {
        this.nomd = nomd;
        this.nbrt = ""+nbrt;
        this.totl =""+ totl;
    }

    public doct(String nomd, String sore, String size, String patch,String ft) {
        this.nomd = nomd;
        this.score = sore;
        this.size = size;
        this.patch = patch;
        this.ft_idf=ft;
        
    }

    public String getNomd() {
        return nomd;
    }

    public void setNomd(String nomd) {
        this.nomd = nomd;
    }

    public String getFt_idf() {
        return ft_idf;
    }

    public void setFt_idf(String ft_idf) {
        this.ft_idf = ft_idf;
    }

    public String getNbrt() {
        return nbrt;
    }

    public void setNbrt(String nbrt) {
        this.nbrt = nbrt;
    }

    public String getTotl() {
        return totl;
    }

    public void setTotl(String totl) {
        this.totl = totl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }
    
}
