/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp3progect;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
 
import javafx.concurrent.Task;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import mp3progect.sorting.SecondWindow.SecondSortWController;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.UnsupportedEncodingException;
import java.nio.file.InvalidPathException;
import javafx.scene.control.Alert;

/**
 *
 * @author Xsinser
 */
public class SortClas extends  Task<DoubleProperty>{
  
 public boolean finishd=false;
 
  @Override
  protected DoubleProperty call() throws Exception{
    String[] targetFilesMasStrings=  getFiles();
    int i;
    int countFiles=targetFilesMasStrings.length;
    if(countFiles<1){
        this.updateProgress(1, 1); 
        showMessage("Ошибка",null,"Музыка не найдена!");
        return null;
    }
    for( i=0;i<countFiles;i++){      
        Files.move(Paths.get(Mp3progect.getPathDirectFrom()+"\\"+targetFilesMasStrings[i]), 
               Paths.get(sortFile(Mp3progect.getPathDirectTo(), targetFilesMasStrings[i])), 
               StandardCopyOption.REPLACE_EXISTING);           
        this.updateProgress(i, countFiles);       
       }
    DoubleProperty db =new SimpleDoubleProperty(i/100);       
    return db ;
   }
  
  private String[] getFiles(){
      File fileFolder=new File(Mp3progect.getPathDirectFrom());
      String[] filePathesStrings = fileFolder.list(new FilenameFilter() {
          @Override
          public boolean accept(File dir, String name) {
              return name.endsWith(".mp3");
          }
      });
      return  filePathesStrings;
  }
  
private  String sortFile(String moveFilesPathTo, String fileName){  
    try{
    Mp3File  mp3file =new Mp3File(Mp3progect.getPathDirectFrom()+"\\"+fileName);    
    String artistTrack,albumTrack;
    artistTrack=albumTrack="Unknown";
     if(mp3file.hasId3v2Tag()){
        artistTrack= getString(mp3file.getId3v2Tag().getArtist());
        albumTrack=getString(mp3file.getId3v2Tag().getAlbum());
        try{
        Paths.get(Mp3progect.getPathDirectFrom()+"\\"+artistTrack+"\\"+albumTrack);
        }
        catch(InvalidPathException exception){
                   artistTrack=albumTrack="Unknown"; 
                }
     }
      folderCreate(artistTrack,Mp3progect.getPathDirectTo());
      folderCreate(albumTrack,moveFilesPathTo+"\\"+artistTrack);        
      return moveFilesPathTo+"\\"+artistTrack+"\\"+albumTrack+"\\"+fileName;
              }
              catch(IOException ioe){
                  System.out.println(ioe.toString());
              }
              catch(UnsupportedTagException e){
                  System.out.println(e.toString());                
              }
              catch(InvalidDataException ie){
                  System.out.println(ie.toString());
              }
    return  null;
  }
  
  private  String getString(String string) throws UnsupportedEncodingException, IOException
  {
      if(string==null){
          string="Unknown";
      }
      else{          
         String cleanString="|/\':*<?>";
         for(int i=0;i<cleanString.length();i++)
         string =string.replace(cleanString.charAt(i), ' ');
      }
      
      return   string;
  }
      private void showMessage(String titleText,String headerText,String contentText)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
   private boolean folderCreate(String nameFolder, String parenthFolder)
    {
        String folderString=parenthFolder+"\\"+nameFolder;        
        File newFolderPathFile=new File(folderString.replace("\\", "/"));
        if(!newFolderPathFile.exists()){
          return newFolderPathFile.mkdir();
        }
        else{
            return false; 
        }
        
    }  
   
}
