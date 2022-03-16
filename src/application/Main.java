package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

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
                launch(args);
                launch(args);
                GameState test = new GameState();
                test.grid = new int[][]{
        	             {0, 0, 0, 0, 0, 0, 0, 0},
        	             {0, 0, 0, 0, 0, 0, 0, 0},
        	             {0, 2, 1, 0, 0, 1, 0, 0},
        	             {0, 0, 1, 1, 1, 2, 0, 0},
        	             {0, 0, 1, 1, 1, 2, 0, 0},
        	             {0, 0, 1, 2, 2, 2, 0, 0},
        	             {0, 0, 0, 0, 0, 0, 0, 0},
        	             {0, 0, 0, 0, 0, 0, 0, 0}
        	             };
        	    test.displayGrid();
        	    System.out.println(test.validPositions(2));
        	    
            }

        }
}