package ui;

import IR.ML;
import IR.doc;
import IR.resulta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pfn.doct;

public class UiController implements Initializable {

    @FXML
    private AnchorPane parent;
    @FXML
    private LineChart<?, ?> lineChart;
     @FXML
    private LineChart<?, ?> Chartrappel;
      @FXML
    private LineChart<?, ?> Chartpres;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
     @FXML
    private ImageView image;
     @FXML
    private ImageView imageac;
     @FXML
    private ImageView imagecrp;
     @FXML
    private ImageView imagerech;
     @FXML
    private ImageView imagedisg;

  /**
     * Initializes the controller class.
     */
     ML m=null;
      String name="";
     ArrayList<Double[]> RP;
    @FXML
    private Label nbrdoc;
    @FXML
    private Label labl1;
    @FXML
    private Label labl2;
    @FXML
    private Label labl3;
    @FXML
    private Label labl4;
    
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
    @FXML
    private JFXButton btnLogindaig;
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
        pAcc.setVisible(false);
        image.setImage(imageac.getImage());
         pcorp.setVisible(false);
         prech.setVisible(false);
          labl1.setBackground(btnLoginac.getBackground());
         labl2.setBackground(Background.EMPTY);
          labl3.setBackground(Background.EMPTY);
           labl4.setBackground(Background.EMPTY);
        
         
    }
         // methode lance apres click sur butoon rechrech(button dans page recherch)   pour rechrech sur curpus et affichier resulta dan table  tabres resulta  
      @FXML
    private void rest(ActionEvent event) {
         tabres.getItems().clear();
         textreq.setText("");
         Docp.setText("");
         lineChart.getData().clear();
         Chartrappel.getData().clear();
         Chartpres.getData().clear();
         
    }   
    @FXML
    private void handlerechrech(ActionEvent event) throws IOException
    {
        if(m!=null)
        {
          if(alert())
          { int sel=0;
            tabres.getItems().clear();
           ArrayList<resulta> list;
        System.out.println("recherche ............");
         String requte=textreq.getText();
         String ds=Docp.getText();
         String[] docpq=ds.split(" ");
         
          m.setLaplace(1);
           m.indexationquery(requte);
           m.SetRS(docpq);
          
         String select=  (String) combonbrm.getSelectionModel().getSelectedItem();
        System.out.println(select);
        if(select.equals("Unigram"))
            {   
                sel=1;
                name="Unigram";
            }
               
        else
        {   if(select.equals("Bigram"))
               {   sel=2;
                   name="Bigram";
                 }
            else
              {  sel=3;
                 name="Thrigram";
              }
        }
        
            list=m.runLM(sel);
            m.Rapple_pre();
            RP=m.getRP();
           // fillChart(name);
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
        else
        {
            Alert alert = new Alert(AlertType.ERROR, " corpus est vide ", ButtonType.YES);
              alert.show();
        }
         
    }
     // methode lance apres click sur butoon corpus   pour affichier  page corpus
     
    @FXML
    private void handlecorpusAction(ActionEvent event) throws IOException {
         pcorp.setVisible(true);
         prech.setVisible(false);
         pAcc.setVisible(false);
         image.setImage(imagecrp.getImage());
         labl2.setBackground(btnLogincrp.getBackground());
         labl1.setBackground(Background.EMPTY);
          labl3.setBackground(Background.EMPTY);
           labl4.setBackground(Background.EMPTY);
    }
     // methode lance apres click sur butoon recherch   pour affichier  page recherche
     
    @FXML
    private void handlerechrAction(ActionEvent event) throws IOException {
         //prech.toFront();
          pcorp.setVisible(false);
          pAcc.setVisible(false);
         prech.setVisible(true);
         image.setImage(imagerech.getImage());
        labl3.setBackground(btnLoginrch.getBackground());
         labl2.setBackground(Background.EMPTY);
          labl1.setBackground(Background.EMPTY);
           labl4.setBackground(Background.EMPTY);
    }
     @FXML
    private void handldisgnerAction(ActionEvent event) throws IOException {
         //prech.toFront();
         pcorp.setVisible(false);
         image.setImage(imagedisg.getImage());
         prech.setVisible(false);
          pAcc.setVisible(true);
        labl4.setBackground(btnLogindaig.getBackground());
         labl2.setBackground(Background.EMPTY);
          labl3.setBackground(Background.EMPTY);
           labl1.setBackground(Background.EMPTY);
           fillChart();
          
         
    }
      // methode lance avent  lancement de aplication   pour initialise kes donnes dans intrface 
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         pAcc.setVisible(false);
        pcorp.setVisible(false);
         prech.setVisible(false);
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

    private void fillChart( ) {
        
        lineChart.getData().clear();
         
        XYChart.Series series = new XYChart.Series();
         XYChart.Series seriesrp = new XYChart.Series();
          XYChart.Series seriesPres = new XYChart.Series();
         series.setName(name);
          seriesrp.setName(name);
           seriesPres.setName(name);
        for(int i=0;i<RP.size();i++)
        {
            Double[] rp=RP.get(i);
            series.getData().add(new XYChart.Data(""+rp[0],rp[1]));
            seriesrp.getData().add(new XYChart.Data(""+i,rp[0]));
             seriesPres.getData().add(new XYChart.Data(""+i,rp[1]));
        }    
     
        lineChart.getData().add(series);
        Chartrappel.getData().add(seriesrp);
        Chartpres.getData().add(seriesPres);
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }
    
   public boolean alert()
   {
       Alert alert = null;
      if(textreq.getText().equals("") || Docp.getText().equals(""))
      { alert = new Alert(AlertType.ERROR, " requet vide ", ButtonType.YES);
      alert.show();
       return false;
      }
      return true;
      
   }


}
