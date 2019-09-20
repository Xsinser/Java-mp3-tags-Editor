/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp3progect.redaction.FinalWindow;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import  java.io.RandomAccessFile;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import javafx.scene.image.PixelFormat;
import mp3progect.Mp3progect;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Xsinser
 */
public class FinalyRewrWController implements Initializable {
    @FXML
    private Pane chosePanel;
    @FXML
    private Button choseButton;
    @FXML
    private Button choseSomeoneButton;
    @FXML
    private Button backMenuButton;
    @FXML
    private Pane changePanel;
    @FXML
    private TextField albumEdit;
    @FXML
    private TextField titleEdit;
    @FXML
    private TextField artistEdit;
    @FXML
    private ImageView imageBox;
    @FXML
    private Button backButton;
    @FXML
    private TextField yearEdit;
    @FXML
    private TextField styleEdit;
    @FXML
    private Button saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filePathList=new ArrayList<File>();
        mp3FilesList=new ArrayList<Mp3File>();
        imageBox.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event){
                        if ( event.getClickCount() == 2){
                             FileChooser fileChooser=new FileChooser();
                             
                             FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("Image files (*.jpg)","*.jpg");
                             fileChooser.getExtensionFilters().add(extensionFilter);
                             imageFilePath = fileChooser.showOpenDialog((Stage)choseSomeoneButton.getScene().getWindow());
                             if(imageFilePath!=null){
                             Image img = new Image(imageFilePath.toURI().toString());
                             imageBox.setImage(img);
                             }
                        }
                    }
                    });
    }  
    
   private boolean multiFileCheck=false;     
   private ArrayList mp3FilesList;//длинна 1 для единичного файла, а более для мультифайла
   private ArrayList filePathList;
   private File imageFilePath;
    
   private void changeEnabledTextFeild(boolean enabledFeild){
       titleEdit.setDisable(enabledFeild);
   }
    
    private void switchPanel(boolean showSecondPanel){
        changePanel.setVisible(showSecondPanel);
        chosePanel.setVisible(!showSecondPanel); 
    }
    @FXML
    private void onChoseButtonClick(ActionEvent event) throws IOException,UnsupportedTagException, InvalidDataException{
        
        
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter extensionFilter= new FileChooser.ExtensionFilter("Audio files (*.mp3)","*.mp3");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog((Stage)choseSomeoneButton.getScene().getWindow());
        if(file!=null){
       
        filePathList.add(file);
        changeEnabledTextFeild(false);
        multiFileCheck=false;
        switchPanel(true);
        Mp3File bufferFile=new Mp3File(file);
        mp3FilesList.add(bufferFile);
        addInformation(bufferFile,false);

        }
    }

    @FXML
    private void onChoseSomeoneClick(ActionEvent event) throws IOException, UnsupportedTagException, InvalidDataException{ 
            
             FileChooser fileChooser=new FileChooser();
             List<File> chosenFiles=fileChooser.showOpenMultipleDialog((Stage)choseSomeoneButton.getScene().getWindow());             
             if(chosenFiles!=null)
             {           
             switchPanel(true);
             multiFileCheck=true;
             changeEnabledTextFeild(true);
 
                for (File file : chosenFiles) {  
                filePathList.add(file);    
                Mp3File bufferFile=new Mp3File(file);                             
                mp3FilesList.add(bufferFile);                  
                 }
             addInformation((Mp3File)mp3FilesList.get(0),true);
             }
                                      
             
    }    
  

    
    private String getString(String string){
        if(string==null)
            return  "Unknown";
        else
            return string;
            
    }
    
    private  Image getImage(byte [] bytes)  throws FileNotFoundException {
  
 if(bytes!=null){
     try{
        BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytes));
        ImageIO.write(imag, "jpg", new File("cover.jpg"));                      
        File file = new File("cover.jpg");
        Image img = new Image(file.toURI().toString());
        file.delete();      
        return img;
     }
     catch(Exception e){
         System.out.println(e.toString());
         Class<?> clazz = this.getClass(); 
         InputStream input = clazz.getResourceAsStream("/mp3progect/redaction/FinalWindow/UnknownIcon.jpg");
 
         Image image = new Image(input); 
         return image;
     }
    }
 else{
     
    Class<?> clazz = this.getClass(); 
    InputStream input = clazz.getResourceAsStream("/mp3progect/redaction/FinalWindow/UnknownIcon.jpg"); 
    Image image = new Image(input); 
    return image;

 }
 }
    
    private  byte [] getBytes( ) throws  IOException
    {
    if (imageFilePath==null) {
    Class<?> clazz = this.getClass(); 
    InputStream input = clazz.getResourceAsStream("/mp3progect/redaction/FinalWindow/UnknownIcon.jpg"); 
    OutputStream out = new FileOutputStream(System.getProperty("user.dir")+"/"+"cvr.jpg");
    int data;
    while ((data = input.read()) != -1) {
            out.write(data);
    }    
    byte[] bytes= Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/"+"cvr.jpg"));  
    File bufFile=new File(System.getProperty("user.dir")+"/"+"cvr.jpg");    
    bufFile.delete();    
    return bytes;
    }
    else{
    byte[] bytes= Files.readAllBytes(imageFilePath.toPath());  
    return bytes;
        }
    }
   
    private void addInformation(Mp3File mp3File, boolean multiFile) throws IOException{
        if(multiFile){
             if (mp3File.hasId3v2Tag()){
           albumEdit.setText(getString(mp3File.getId3v2Tag().getAlbum()));      
           artistEdit.setText(getString(mp3File.getId3v2Tag().getArtist()));     
           imageBox.setImage(getImage(mp3File.getId3v2Tag().getAlbumImage()));
           yearEdit.setText(getString(mp3File.getId3v2Tag().getYear()));
           styleEdit.setText(getString(mp3File.getId3v2Tag().getGrouping()));
            }
            else {    
          albumEdit.setText("Unknown");           
          artistEdit.setText("Unknown");
          imageBox.setImage(getImage(null));
          yearEdit.setText("Unknown");
          styleEdit.setText("Unknown"); 
            }
        }
        else{
      if (mp3File.hasId3v2Tag()){
      albumEdit.setText(getString(mp3File.getId3v2Tag().getAlbum()));
      titleEdit.setText(getString(mp3File.getId3v2Tag().getTitle()));
      artistEdit.setText(getString(mp3File.getId3v2Tag().getArtist()));     
      imageBox.setImage(getImage(mp3File.getId3v2Tag().getAlbumImage()));
      yearEdit.setText(getString(mp3File.getId3v2Tag().getYear()));
      styleEdit.setText(getString(mp3File.getId3v2Tag().getGrouping()));
            }
            else{    
          albumEdit.setText("Unknown");
          titleEdit.setText("Unknown");
          artistEdit.setText("Unknown");
          imageBox.setImage(getImage(null));
          yearEdit.setText("Unknown");
          styleEdit.setText("Unknown"); 
            }
        }
    }
    
    private void moveToPreviousPanel(){
      switchPanel(false);
      changeEnabledTextFeild(false);
      imageBox.setImage(null);
      artistEdit.setText(null);
      albumEdit.setText(null);
      titleEdit.setText(null);
      yearEdit.setText(null);
      styleEdit.setText(null);
      multiFileCheck=false;
      filePathList.clear();
      mp3FilesList.clear();
      imageFilePath=null;    
    }
    

    @FXML
    private void onBackMenuButtonClick(ActionEvent event) {
        
    Stage previousStage=Mp3progect.getPreviousStage();      
    Stage thisStage=(Stage)backButton.getScene().getWindow();     
    previousStage.show(); 
    thisStage.close();  
    
    }

    @FXML
    private void onbackButtonClick(ActionEvent event) {
    moveToPreviousPanel();
    }

    private void multiFileSave() throws  IOException, NotSupportedException{
       
      for(int i=0;i<mp3FilesList.size();i++){
      ID3v2 iD3v2; 
      if(((Mp3File)mp3FilesList.get(i)).hasId3v2Tag()){
      iD3v2= ((Mp3File)mp3FilesList.get(i)).getId3v2Tag();
      }
      else{
         iD3v2= new ID3v24Tag();
         ((Mp3File)mp3FilesList.get(i)).setId3v2Tag(iD3v2);
      }        
      iD3v2.setAlbum(albumEdit.getText());
      
      iD3v2.setYear(yearEdit.getText());
      iD3v2.setGrouping(styleEdit.getText());
      iD3v2. setArtist(artistEdit.getText());
      iD3v2.setAlbumImage(getBytes(), "image/jpg");                                         
                                           
      String bufString=((File)filePathList.get(i)).getName();                   
      ((Mp3File)mp3FilesList.get(i)).save(bufString);
      File fileInPrograFolderFile=new File(System.getProperty("user.dir")+"/"+bufString);
                               
      ((File)filePathList.get(i)).delete();
      
      Files.move(Paths.get(fileInPrograFolderFile.getPath()), 
              Paths.get(((File)filePathList.get(i)).getPath()), 
              StandardCopyOption.REPLACE_EXISTING);  
      }
      
    }
    
    private void singleFileSave()throws  IOException, NotSupportedException{
    ID3v2 iD3v2;      
      if(((Mp3File)mp3FilesList.get(0)).hasId3v2Tag()){
      iD3v2= ((Mp3File)mp3FilesList.get(0)).getId3v2Tag();
      }
      else{
         iD3v2= new ID3v24Tag();
         ((Mp3File)mp3FilesList.get(0)).setId3v2Tag(iD3v2);
      }        
      iD3v2.setAlbum(albumEdit.getText());
      iD3v2.setTitle(titleEdit.getText());
      iD3v2.setYear(yearEdit.getText());
      iD3v2.setGrouping(styleEdit.getText());
      iD3v2. setArtist(artistEdit.getText());
      iD3v2.setAlbumImage(getBytes(), "image/jpg");                                         
                                           
      String bufString=((File)filePathList.get(0)).getName();                   
      ((Mp3File)mp3FilesList.get(0)).save(bufString);
      File fileInPrograFolderFile=new File(System.getProperty("user.dir")+"/"+bufString);
                               
      ((File)filePathList.get(0)).delete();
      
      Files.move(Paths.get(fileInPrograFolderFile.getPath()), 
              Paths.get(((File)filePathList.get(0)).getPath()), 
              StandardCopyOption.REPLACE_EXISTING);        
    }
    
    private  void endSave(){
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Изменение тэгов файла");
        alert.setHeaderText(null);
        alert.setContentText("Сохранение завершено!");
        alert.showAndWait();
        moveToPreviousPanel();
        
    }
    
    @FXML
    private void onSaveButtonClick(ActionEvent event)   {
    try {           
       
        if(multiFileCheck){
         multiFileSave();
        }
        else{
          singleFileSave();
        }
     endSave();   
   } catch (Exception e) {
             System.out.println(e.toString());
        }
    }
    
}
