/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp3progect.sorting.FinalWindow;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mp3progect.Mp3progect;
import mp3progect.SortClas;

/**
 * FXML Controller class
 *
 * @author Xsinser
 */
public class EndSortWController implements Initializable {
    @FXML
    private Pane progresPanel;
    @FXML
    private ProgressBar progresBar;
    @FXML
    private Button finishButton;
    @FXML
    private Pane startPanel;
    @FXML
    private Button startButton;
    @FXML
    private Button backButton;

    private boolean checkPress=true;
    private Stage thisStage,previousStage; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onFinishButtonClick(ActionEvent event) {
     Mp3progect.moveToFirstW();
     thisStage=(Stage)backButton.getScene().getWindow();
     thisStage.close();
    }
    private  void  showFirst(){
        Mp3progect.setCloseParametr(true);
        previousStage.close();
        thisStage.close();
    }
    private void showMessage(String titleText,String headerText,String contentText)
    {
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    @FXML
    private void onStartButtonClick(ActionEvent event) {
        startPanel.visibleProperty().setValue(false);
        progresPanel.visibleProperty().setValue(true);
        int x=0;
        if(checkStartProcess())
        {
        SortClas ssClas=new SortClas();
        progresBar.progressProperty().bind(ssClas.progressProperty());
        ssClas.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                finishButton.setDisable(false);
                showMessage("Сортировка файлов", null, "Сортировка завершена!");
            }
        });
        new Thread(ssClas).start();
        }
        
    }

    private boolean checkStartProcess()
    {
        if(Mp3progect.getPathDirectFrom()==Mp3progect.getPathDirectTo())
        {
            finishButton.setDisable(false);
            showMessage("Ошибка", null, "Папка с музыкой и папка для сохранения доолжны различаться!");
            return false;
        }
        else
            return true;
    }
    
    @FXML
    private void onBackButtonClick(ActionEvent event) {
    if (checkPress)
    previousStage=Mp3progect.getPreviousStage();      
    thisStage=(Stage)backButton.getScene().getWindow();
    previousStage.show();
    Mp3progect.removeLastStage();
    thisStage.close();
    }
    
}
