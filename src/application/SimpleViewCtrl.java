package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SimpleViewCtrl {
	
	//white disks declaration
    @FXML
    private ImageView BlackPawn00, BlackPawn01, BlackPawn02, BlackPawn03, BlackPawn04, BlackPawn05, BlackPawn06, BlackPawn07,
    				  BlackPawn10, BlackPawn11, BlackPawn12, BlackPawn13, BlackPawn14, BlackPawn15, BlackPawn16, BlackPawn17,
    				  BlackPawn20, BlackPawn21, BlackPawn22, BlackPawn23, BlackPawn24, BlackPawn25, BlackPawn26, BlackPawn27,
    				  BlackPawn30, BlackPawn31, BlackPawn32, BlackPawn33, BlackPawn34, BlackPawn35, BlackPawn36, BlackPawn37,
    				  BlackPawn40, BlackPawn41, BlackPawn42, BlackPawn43, BlackPawn44, BlackPawn45, BlackPawn46, BlackPawn47,
    				  BlackPawn50, BlackPawn51, BlackPawn52, BlackPawn53, BlackPawn54, BlackPawn55, BlackPawn56, BlackPawn57,
    				  BlackPawn60, BlackPawn61, BlackPawn62, BlackPawn63, BlackPawn64, BlackPawn65, BlackPawn66, BlackPawn67,
    				  BlackPawn70, BlackPawn71, BlackPawn72, BlackPawn73, BlackPawn74, BlackPawn75, BlackPawn76, BlackPawn77;
    
    //black disks declaration
    @FXML
    private ImageView WhitePawn00, WhitePawn01, WhitePawn02, WhitePawn03, WhitePawn04, WhitePawn05, WhitePawn06, WhitePawn07,
    				  WhitePawn10, WhitePawn11, WhitePawn12, WhitePawn13, WhitePawn14, WhitePawn15, WhitePawn16, WhitePawn17,
    				  WhitePawn20, WhitePawn21, WhitePawn22, WhitePawn23, WhitePawn24, WhitePawn25, WhitePawn26, WhitePawn27,
    				  WhitePawn30, WhitePawn31, WhitePawn32, WhitePawn33, WhitePawn34, WhitePawn35, WhitePawn36, WhitePawn37,
    				  WhitePawn40, WhitePawn41, WhitePawn42, WhitePawn43, WhitePawn44, WhitePawn45, WhitePawn46, WhitePawn47,
    				  WhitePawn50, WhitePawn51, WhitePawn52, WhitePawn53, WhitePawn54, WhitePawn55, WhitePawn56, WhitePawn57,
    				  WhitePawn60, WhitePawn61, WhitePawn62, WhitePawn63, WhitePawn64, WhitePawn65, WhitePawn66, WhitePawn67,
    				  WhitePawn70, WhitePawn71, WhitePawn72, WhitePawn73, WhitePawn74, WhitePawn75, WhitePawn76, WhitePawn77;

    
    @FXML
    private ImageView Board;

    @FXML
    private Button button00, button01, button02, button03, button04, button05, button06, button07,
    			   button10, button11, button12, button13, button14, button15, button16, button17,
    			   button20, button21, button22, button23, button24, button25, button26, button27,
    			   button30, button31, button32, button33, button34, button35, button36, button37,
    			   button40, button41, button42, button43, button44, button45, button46, button47,
    			   button50, button51, button52, button53, button54, button55, button56, button57,
    			   button60, button61, button62, button63, button64, button65, button66, button67,
    			   button70, button71, button72, button73, button74, button75, button76, button77;

	int tour=1;
	//tour = Main.test.currentPlayer;
    @FXML
    void onClick(MouseEvent event) {
    	//Creating black and white disks arrays already positioned on the squares and invisible by default
    	ImageView[] WhiteTab = {WhitePawn00, WhitePawn01, WhitePawn02, WhitePawn03, WhitePawn04, WhitePawn05, WhitePawn06, WhitePawn07, 
    							WhitePawn10, WhitePawn11, WhitePawn12, WhitePawn13, WhitePawn14, WhitePawn15, WhitePawn16, WhitePawn17,
    							WhitePawn20, WhitePawn21, WhitePawn22, WhitePawn23, WhitePawn24, WhitePawn25, WhitePawn26, WhitePawn27,
    							WhitePawn30, WhitePawn31, WhitePawn32, WhitePawn33, 			 WhitePawn35, WhitePawn36, WhitePawn37,
    							WhitePawn40, WhitePawn41, WhitePawn42, 				WhitePawn44, WhitePawn45, WhitePawn46, WhitePawn47,
    							WhitePawn50, WhitePawn51, WhitePawn52, WhitePawn53, WhitePawn54, WhitePawn55, WhitePawn56, WhitePawn57,
    							WhitePawn60, WhitePawn61, WhitePawn62, WhitePawn63, WhitePawn64, WhitePawn65, WhitePawn66, WhitePawn67,
    							WhitePawn70, WhitePawn71, WhitePawn72, WhitePawn73, WhitePawn74, WhitePawn75, WhitePawn76, WhitePawn77};
    	
    	ImageView[] BlackTab = {BlackPawn00, BlackPawn01, BlackPawn02, BlackPawn03, BlackPawn04, BlackPawn05, BlackPawn06, BlackPawn07, 
    							BlackPawn10, BlackPawn11, BlackPawn12, BlackPawn13, BlackPawn14, BlackPawn15, BlackPawn16, BlackPawn17,
    							BlackPawn20, BlackPawn21, BlackPawn22, BlackPawn23, BlackPawn24, BlackPawn25, BlackPawn26, BlackPawn27,
    							BlackPawn30, BlackPawn31, BlackPawn32, 				BlackPawn34, BlackPawn35, BlackPawn36, BlackPawn37,
    							BlackPawn40, BlackPawn41, BlackPawn42, BlackPawn43, 			 BlackPawn45, BlackPawn46, BlackPawn47,
    							BlackPawn50, BlackPawn51, BlackPawn52, BlackPawn53, BlackPawn54, BlackPawn55, BlackPawn56, BlackPawn57,
    							BlackPawn60, BlackPawn61, BlackPawn62, BlackPawn63, BlackPawn64, BlackPawn65, BlackPawn66, BlackPawn67,
    							BlackPawn70, BlackPawn71, BlackPawn72, BlackPawn73, BlackPawn74, BlackPawn75, BlackPawn76, BlackPawn77};
    	
    	//getting the button clicked in order to opacity it and disable it
    	Object o = event.getSource();
    	Button buttonpushed = (Button)o;
    	buttonpushed.setOpacity(0);
    	buttonpushed.setDisable(true);
    	
    	//getting acces to black and white disks ImageView ids with the position of the button
    	String butname = buttonpushed.getId();
    	
    	//white's turn
    	if(Main.test.currentPlayer==2) {
    		String whitenameiv = "WhitePawn" +butname.substring(6);
    		for(int i = 0; i<WhiteTab.length; i++) {
    			if(WhiteTab[i].getId().equals(whitenameiv)) {
    				//displaying white disk on the square of the clicked button
    					WhiteTab[i].setVisible(true);
    					Main.test.currentPlayer=1;
    				
    			}
    		}
    	}
    	//black's turn
    	else if(Main.test.currentPlayer==1) {
    		String blacknameiv = "BlackPawn" +butname.substring(6);
    		for(int i = 0; i<BlackTab.length; i++) {
    			if(BlackTab[i].getId().equals(blacknameiv)) {
    				//displaying black disk on the square of the clicked button
    				BlackTab[i].setVisible(true);
    				Main.test.currentPlayer=2;
    			}
  
    		}
    	}
    }
}