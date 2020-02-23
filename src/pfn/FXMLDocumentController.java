/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pfn;


import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Salah_Mer
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML  
    private JFXTextField username;
     @FXML
    Hyperlink  text;
  // private String user="",passw="";
    @FXML
      private JFXButton btnLogin; // botuun signin pour ligin 
     @FXML
      private JFXPasswordField pass; 
   
    /// methde lance apres click sur buottun signin   pour fiare login 
    @FXML
      private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        String user=getUsername(),pas=getPassword();
        
        if(user.equals("admin") && pas.equals("1234"))
        {
             label.setTextFill(Color.GREENYELLOW);
             label.prefWidth(40);
            label.setText("Sign seucces"); 
             Stage primaryStage =(Stage)((Node)event.getSource()).getScene().getWindow();
              Parent root = FXMLLoader.load(getClass().getResource("FXMLAccueil.fxml"));
                  
                  Scene scene = new Scene(root);
        
                  primaryStage.setScene(scene);
                   primaryStage.show();
        }
        else
        { 
            label.setTextFill(Color.RED);
            label.setText("Sign field");
        }
    }
     /// methode pour get user name  a textfield  user name
       public String getPassword() {
		return pass.getText();
	}
       /// methode pour get pass word  a textfield  password
	public String getUsername() {
		return username.getText();
	}
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
