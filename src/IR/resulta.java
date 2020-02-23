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
public class resulta {
    doc d;
    double score;
    Double tf_idf;

    public resulta(doc d, double score) {
        this.d = d;
        this.score = score;
    }
     public resulta(doc d, double score,double tfidf) {
        this.d = d;
        this.score = score;
        this.tf_idf=tfidf;
    }

    public doc getD() {
        return d;
    }

    public void setD(doc d) {
        this.d = d;
    }

    public double getScore() {
        return score;
    }

    public Double getTf_idf() {
        return tf_idf;
    }

    public void setTf_idf(Double tf_idf) {
        this.tf_idf = tf_idf;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    
}
