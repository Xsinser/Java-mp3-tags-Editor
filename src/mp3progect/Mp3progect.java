/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp3progect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Xsinser
 */
public class Mp3progect extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));        
        Scene scene = new Scene(root);            
        stage.getIcons().add(new Image(Mp3progect.class.getResourceAsStream("icon.jpg"))) ;
        stage.setScene(scene);
        stage.show();
        stageArray.add(stage);
       
    }

      
     static private ArrayList stageArray=new ArrayList<Stage>();
     static private String    pathDirectFrom, pathDirectTo;
     
     static public Stage getPreviousStage(){
         return (Stage)stageArray.get(stageArray.size()-1);
     }
    static public Stage getStage(int index){
         return (Stage)stageArray.get(index);
     }
    
    static public void removeLastStage(){
      stageArray.remove(stageArray.size()-1);
    }
    
     static public  void addStage(Stage thisStage){
         stageArray.add(thisStage);
     }
     
     static  public void moveToFirstW(){
     Stage bufStage=(Stage) stageArray.get(0);
     for(int i=1;i<stageArray.size()-1;i++) {
         ((Stage)stageArray.get(i)).close();
     }
     stageArray.clear();
     stageArray.add(bufStage);
     bufStage.show();
     }
  
   static public String getPathDirectFrom() {
         return pathDirectFrom;
     }
   
    static public void setPathDirectFrom(String gettingPath) {
         pathDirectFrom=gettingPath;
     }
    
     static public String getPathDirectTo() {
         return pathDirectTo;
     }
    static public void setPathDirectTo(String gettingPath) {
         pathDirectTo=gettingPath;
     }
  
  private static boolean isCloseB=false;
  
  static public  void setCloseParametr(boolean bool) {
      isCloseB=bool;
  }
  
     static public boolean getCloseParametr() {
     return isCloseB;
     }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
