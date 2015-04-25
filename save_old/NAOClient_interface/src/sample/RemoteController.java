package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class RemoteController
{
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private MediaView mediaView;


    @FXML
    public void disconnect(ActionEvent actionEvent)
    {
        System.out.println("Retout ecran config adresse et port");
    }

    @FXML
    public void closeWindow(ActionEvent actionEvent)
    {
        Stage stage = (Stage)closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void goForward(ActionEvent actionEvent)
    {
        System.out.println("Envoyer socket goForward");
    }

    @FXML
    public void goBackward(ActionEvent actionEvent)
    {
        System.out.println("Envoyer socket goBackward");
    }

    @FXML
    public void turnRigth(ActionEvent actionEvent)
    {
        System.out.println("Envoyer socket turnRigth");
    }

    @FXML
    public void turnLeft(ActionEvent actionEvent)
    {
        System.out.println("Envoyer socket turnLeft");
    }

    @FXML
    public void showCameraVision(ActionEvent actionEvent)
    {
        System.out.println("Afficher interface vision NAO temps reel");

        Media media =  new Media("file:///C:/Users/kimsavinfo/Documents/NAO/media/NAO_Aldebaran_Robotics.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
            }
        });

    }

    @FXML
    public void showSayForm(ActionEvent actionEvent)
    {
        System.out.println("Afficher txt formulaire");
    }
}
