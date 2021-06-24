
import view.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;

public class MainWPilot extends Application {

    //private Communication receiver;
    private MainView main;

    private void close() {
        System.out.println("Closing thread");
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void start(Stage primaryStage) {
        main = new MainView();
        Scene scene = main.createView();
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.setTitle("RemotePilot Team 3 - June 2020");
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
