package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

        //variable globale dans la classe main pour résoudre le pb de com avec le ctrl
		private static GameState test;
        static Game testGame;
        @Override
        public void start(Stage primaryStage) {

                Parent root;

                try {
                        root = FXMLLoader.load(getClass().getResource("SimpleView.fxml"));

                        Scene scene = new Scene(root);
                        primaryStage.setScene(scene);
                        primaryStage.sizeToScene();
                        primaryStage.show();

                }
                catch (IOException e) {
                        e.printStackTrace();
                        return;
                }

        }
        public static void main(String[] args) {
        	test = new GameState(); //initialise before launch(args) because SceneBuilder waits for evenements
        	testGame = new Game(test);
        	
        	//testGame.startReversi();
        	launch(args); //uncomment to enable gui
        }

}
