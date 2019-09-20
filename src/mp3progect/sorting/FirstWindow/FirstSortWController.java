/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp3progect.sorting.FirstWindow;

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
import javax.swing.JFileChooser;
import mp3progect.Mp3progect;

/**
 * FXML Controller class
 *
 * @author Xsinser
 */
public class FirstSortWController implements Initializable {

    @FXML
    Button backButton,chose;
    
   private Stage thisStage,previousStage;   
   private  boolean checkPress=true;
   @FXML
   private  void  backButtonClick(ActionEvent event) throws Exception{
      if (checkPress)
      previousStage=Mp3progect.getPreviousStage();
      
      thisStage=(Stage)backButton.getScene().getWindow();    
      previousStage.show();
      thisStage.close();  
    
    }
   
   
   private  void  nextForm()throws Exception{
               
        checkPress=false;
        previousStage=Mp3progect.getPreviousStage();
        thisStage=(Stage)backButton.getScene().getWindow();
        Mp3progect.addStage(thisStage);
        
        
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/mp3progect/sorting/SecondWindow/secondSortW.fxml"));
     Parent pr=(Parent)loader.load();
     Stage stage=new Stage();     
     stage.getIcons().add(new Image(Mp3progect.class.getResourceAsStream("icon.jpg"))) ; 
     stage.setScene(new Scene(pr));        
   
     stage.show();
     thisStage.hide();
   }
   
  @FXML
      private  void   onChoseBurronClick(ActionEvent event) throws Exception{
        JFileChooser filePath = new JFileChooser();
        filePath.setFileSelectionMode(filePath.DIRECTORIES_ONLY);
        int ret=filePath.showDialog(null, "Выбирете папку");
        if(ret==JFileChooser.APPROVE_OPTION){
            String s;
            Mp3progect.setPathDirectFrom( String.valueOf( filePath.getSelectedFile()));
            nextForm();
         }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    
}
