/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfn;

import IR.ML;
import IR.doc;
import IR.resulta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

/**
 * FXML Controller class
 *
 * @author Salah_Mer
 */
public class FXMLAccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ML m=null;
    @FXML
    private Label nbrdoc;
     @FXML  
    private JFXTextField ACC;  
     @FXML  
    private JFXTextField textreq; // textfilien iput requet
     @FXML  
    private JFXTextField Docp;//Les documents pertinents pour la requête Q  
    @FXML
    private JFXButton btnLoginac; // button  affichier page accueil
    @FXML
    private JFXButton btnLogincrp;// button  affichier page corpus
    @FXML
    private JFXButton btnLoginrch; //button  affichier page recherche
    @FXML AnchorPane pAcc; //  pane  page accueil
     @FXML AnchorPane pcorp; // pane  page corpus
      @FXML AnchorPane prech; // pane page recherche
     @FXML
     private TableView<doct>  tab; // affichier dans  table  documnts apres recuperes docemnt corpus
     @FXML
     private TableView<doct>  tabres; // table affhier resulta dans table  page recherch 
     @FXML
	private TableColumn<doct, String> nomd;   // colum nom de documnt dans  table tab 
     @FXML
	private TableColumn<doct, String> nbrt;// colum nbre de terems de documnt dans  table tab  
     @FXML
	private TableColumn<doct, String> totl; // colum nbr total  de motes de documnt dans  table tab  
     @FXML
	private TableColumn<doct, String> nomd1; // // colum nom de documnt dans  table tabres  page recherch
     @FXML
      private TableColumn<doct, String> patch;  // colum patch de documnt dans  table tabres  page recherch
     @FXML
	private TableColumn<doct, String> score;// colum score de documnt dans  table tabres  page recherch
       @FXML
	private TableColumn<doct, String> size; //// colum size de documnt dans  table tabres  page recherch
        @FXML
	private TableColumn<doct, String> ft_idf; // colum  ft*idf de documnt dans  table tabres  page recherch
          @FXML
       JFXComboBox combonbrm; // combobox pour choisir  taille de ngramm "unigram / bigram/thrigram

           // methode lance apres click sur butoon recupere documnt  pour recupere les docmunt dans corpus
    @FXML 
    private void handleButtonAction(ActionEvent event) throws IOException {
        ArrayList<doc> list;
         tab.getItems().clear();
        System.out.println("recupere mes document!");
        m=new ML(2);
           m.recuperedoc_indexation();
           list=m.getlist();
           for(int i=0;i<list.size();i++)
           {
               doc d=list.get(i);
                doct t=new doct(d.getNom(), d.getMotecle().size(),d.getNbrtotal());
               tab.getItems().add(t);
             
           }
        nbrdoc.setText("nombre de documents dans corpus : "+list.size());
        
    }
     // methode lance apres click sur butoon Accueil   pour affichier  page accueil
     @FXML
    private void handleAccueilAction(ActionEvent event) throws IOException {
         pAcc.toFront();
         btnLoginac.setBackground(pAcc.getBackground());
         btnLogincrp.setBackground(Background.EMPTY);
         btnLoginrch.setBackground(Background.EMPTY);
         
    }
         // methode lance apres click sur butoon rechrech(button dans page recherch)   pour rechrech sur curpus et affichier resulta dan table  tabres resulta  
         @FXML
    private void handlerechrech(ActionEvent event) throws IOException
    {
        if(m!=null)
        {
            int sel=0;
            tabres.getItems().clear();
           ArrayList<resulta> list;
        System.out.println("recherche ............");
         String requte=textreq.getText();
         
          m.setLaplace(1);
           m.indexationquery(requte);
         String select=  (String) combonbrm.getSelectionModel().getSelectedItem();
        System.out.println(select);
        if(select.equals("Unigram"))
                    sel=1;
        else
        {   if(select.equals("Bigram"))
                    sel=2;
            else
                    sel=3;
        }
        
            list=m.runLM(sel);
           for(int i=0;i<list.size();i++)
           {
               resulta f=list.get(i);
               doc d=f.getD();
               String scor=""+f.getScore();
               String size =""+d.getSize();
                doct t=new doct(d. getNom(),scor,size,d.getPatch(),""+f.getTf_idf());
               tabres.getItems().add(t);
              // nbrt.setVisible(true);
           }
        }
         
    }
     // methode lance apres click sur butoon corpus   pour affichier  page corpus
     
    @FXML
    private void handlecorpusAction(ActionEvent event) throws IOException {
         pcorp.toFront();
         btnLogincrp.setBackground(pcorp.getBackground());
         btnLoginac.setBackground(Background.EMPTY);
         btnLoginrch.setBackground(Background.EMPTY);
    }
     // methode lance apres click sur butoon recherch   pour affichier  page recherche
     
    @FXML
    private void handlerechrAction(ActionEvent event) throws IOException {
         prech.toFront();
         btnLoginrch.setBackground(prech.getBackground());
         btnLoginac.setBackground(Background.EMPTY);
         btnLogincrp.setBackground(Background.EMPTY);
    }
      // methode lance avent  lancement de aplication   pour initialise kes donnes dans intrface 
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         pAcc.toFront();
         nomd.setCellValueFactory(new PropertyValueFactory<doct, String>("nomd"));
		nbrt.setCellValueFactory(new PropertyValueFactory<doct, String>("nbrt"));
                totl.setCellValueFactory(new PropertyValueFactory<doct, String>("totl"));
                ///////////////////
                nomd1.setCellValueFactory(new PropertyValueFactory<doct, String>("nomd"));
               
            		size.setCellValueFactory(new PropertyValueFactory<doct, String>("size"));
                score.setCellValueFactory(new PropertyValueFactory<doct, String>("score"));
                patch.setCellValueFactory(new PropertyValueFactory<doct, String>("patch"));
                ft_idf.setCellValueFactory(new PropertyValueFactory<doct, String>("ft_idf"));
                combonbrm.getItems().addAll("Unigram","Bigram","Thrigram");
                
    }    
    
}
