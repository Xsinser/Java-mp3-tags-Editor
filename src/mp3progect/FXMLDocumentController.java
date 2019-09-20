/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp3progect;

import com.sun.javaws.Main;
import com.sun.javaws.ui.SplashScreen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
/**
 *
 * @author Xsinser
 */
public class FXMLDocumentController implements Initializable {
 
    
    @FXML
    javafx.scene.control.Button sortButton, redactButton;
/////////////////////////////////////
    private Stage thisStage;
    @FXML
    private  void  onSortButtonClick(ActionEvent event) throws Exception
            
    {
      
        
    // do what you have to do
   // stage1.close();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/mp3progect/sorting/FirstWindow/firstSortW.fxml"));
     Parent pr=(Parent)loader.load();
     Stage stage=new Stage();
      
       thisStage=(Stage) sortButton.getScene().getWindow();
       stage.getIcons().add(new Image(Mp3progect.class.getResourceAsStream("icon.jpg"))) ;
     stage.setScene(new Scene(pr));
        
    
     stage.show();
    thisStage.hide();
 
    }
    


  @FXML
     private  void  onRedactButtonClick(ActionEvent event) throws Exception
     {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/mp3progect/redaction/FinalWindow/finalyRewrW.fxml"));
     Parent pr=(Parent)loader.load();
     Stage stage=new Stage();
     stage.getIcons().add(new Image(Mp3progect.class.getResourceAsStream("icon.jpg"))) ; 
      thisStage=(Stage) redactButton.getScene().getWindow();
     stage.setScene(new Scene(pr));
        
    
     stage.show();
    thisStage.hide();
     }
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    
    
}
