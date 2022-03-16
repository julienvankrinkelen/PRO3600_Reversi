package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
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