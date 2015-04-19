package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RemoteApplication extends Application
{
    private int windowsWidth = 750;
    private int windowsHeight = 900;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("remote.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root, windowsWidth, windowsHeight));
        /*
        primaryStage.setMinWidth(windowsWidth);
        primaryStage.setMinHeight(windowsHeight);
        primaryStage.setMaxWidth(windowsWidth);
        primaryStage.setMaxHeight(windowsHeight);
        */
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
