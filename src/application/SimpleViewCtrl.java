package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SimpleViewCtrl {
	
	//white disks declaration
    @FXML
    private ImageView BlackDisk00, BlackDisk01, BlackDisk02, BlackDisk03, BlackDisk04, BlackDisk05, BlackDisk06, BlackDisk07,
    				  BlackDisk10, BlackDisk11, BlackDisk12, BlackDisk13, BlackDisk14, BlackDisk15, BlackDisk16, BlackDisk17,
    				  BlackDisk20, BlackDisk21, BlackDisk22, BlackDisk23, BlackDisk24, BlackDisk25, BlackDisk26, BlackDisk27,
    				  BlackDisk30, BlackDisk31, BlackDisk32, BlackDisk33, BlackDisk34, BlackDisk35, BlackDisk36, BlackDisk37,
    				  BlackDisk40, BlackDisk41, BlackDisk42, BlackDisk43, BlackDisk44, BlackDisk45, BlackDisk46, BlackDisk47,
    				  BlackDisk50, BlackDisk51, BlackDisk52, BlackDisk53, BlackDisk54, BlackDisk55, BlackDisk56, BlackDisk57,
    				  BlackDisk60, BlackDisk61, BlackDisk62, BlackDisk63, BlackDisk64, BlackDisk65, BlackDisk66, BlackDisk67,
    				  BlackDisk70, BlackDisk71, BlackDisk72, BlackDisk73, BlackDisk74, BlackDisk75, BlackDisk76, BlackDisk77;
    
    //black disks declaration
    @FXML
    private ImageView WhiteDisk00, WhiteDisk01, WhiteDisk02, WhiteDisk03, WhiteDisk04, WhiteDisk05, WhiteDisk06, WhiteDisk07,
    				  WhiteDisk10, WhiteDisk11, WhiteDisk12, WhiteDisk13, WhiteDisk14, WhiteDisk15, WhiteDisk16, WhiteDisk17,
    				  WhiteDisk20, WhiteDisk21, WhiteDisk22, WhiteDisk23, WhiteDisk24, WhiteDisk25, WhiteDisk26, WhiteDisk27,
    				  WhiteDisk30, WhiteDisk31, WhiteDisk32, WhiteDisk33, WhiteDisk34, WhiteDisk35, WhiteDisk36, WhiteDisk37,
    				  WhiteDisk40, WhiteDisk41, WhiteDisk42, WhiteDisk43, WhiteDisk44, WhiteDisk45, WhiteDisk46, WhiteDisk47,
    				  WhiteDisk50, WhiteDisk51, WhiteDisk52, WhiteDisk53, WhiteDisk54, WhiteDisk55, WhiteDisk56, WhiteDisk57,
    				  WhiteDisk60, WhiteDisk61, WhiteDisk62, WhiteDisk63, WhiteDisk64, WhiteDisk65, WhiteDisk66, WhiteDisk67,
    				  WhiteDisk70, WhiteDisk71, WhiteDisk72, WhiteDisk73, WhiteDisk74, WhiteDisk75, WhiteDisk76, WhiteDisk77;

    //board image declaration
    @FXML
    private ImageView Board;
    
    //buttons declaration
    @FXML
    private Button button00, button01, button02, button03, button04, button05, button06, button07,
    			   button10, button11, button12, button13, button14, button15, button16, button17,
    			   button20, button21, button22, button23, button24, button25, button26, button27,
    			   button30, button31, button32, button33, button34, button35, button36, button37,
    			   button40, button41, button42, button43, button44, button45, button46, button47,
    			   button50, button51, button52, button53, button54, button55, button56, button57,
    			   button60, button61, button62, button63, button64, button65, button66, button67,
    			   button70, button71, button72, button73, button74, button75, button76, button77;
    @FXML
    private Button buttonPlay, buttonLeave;
    
   

    
    /**
     * This method is called by any button we click on the board
     * 
     * Once a button is clicked on: 
     * 	Gets the source of the button with the method Object getSource() 
     * 	Set it invisible and disabled with the methods Button setVisible() and Button setDisable()
     * 	
     * To get the right disk, we have to initialize arrays of the disks ids. In fact, the image is not linked with the button in the fxml file (the button
     *  is just on top of the disk covering it). So, by cutting the position string of the event Button, it builds the ids of the black and white disks of
     *  the position. Then, by searching the disk with the right id in the array, it can display it (either BLACK or WHITE according to Main.test.currentPlayer)
     * 	
     * @param event
     */

    @FXML
    void onClick(MouseEvent event) {
    	//Creating black and white disks arrays already positioned on the squares and invisible by default
    	ImageView[] WhiteTab = {WhiteDisk00, WhiteDisk01, WhiteDisk02, WhiteDisk03, WhiteDisk04, WhiteDisk05, WhiteDisk06, WhiteDisk07, 
    							WhiteDisk10, WhiteDisk11, WhiteDisk12, WhiteDisk13, WhiteDisk14, WhiteDisk15, WhiteDisk16, WhiteDisk17,
    							WhiteDisk20, WhiteDisk21, WhiteDisk22, WhiteDisk23, WhiteDisk24, WhiteDisk25, WhiteDisk26, WhiteDisk27,
    							WhiteDisk30, WhiteDisk31, WhiteDisk32, WhiteDisk33, WhiteDisk34, WhiteDisk35, WhiteDisk36, WhiteDisk37,
    							WhiteDisk40, WhiteDisk41, WhiteDisk42, WhiteDisk43,	WhiteDisk44, WhiteDisk45, WhiteDisk46, WhiteDisk47,
    							WhiteDisk50, WhiteDisk51, WhiteDisk52, WhiteDisk53, WhiteDisk54, WhiteDisk55, WhiteDisk56, WhiteDisk57,
    							WhiteDisk60, WhiteDisk61, WhiteDisk62, WhiteDisk63, WhiteDisk64, WhiteDisk65, WhiteDisk66, WhiteDisk67,
    							WhiteDisk70, WhiteDisk71, WhiteDisk72, WhiteDisk73, WhiteDisk74, WhiteDisk75, WhiteDisk76, WhiteDisk77};
    	
    	ImageView[] BlackTab = {BlackDisk00, BlackDisk01, BlackDisk02, BlackDisk03, BlackDisk04, BlackDisk05, BlackDisk06, BlackDisk07, 
    							BlackDisk10, BlackDisk11, BlackDisk12, BlackDisk13, BlackDisk14, BlackDisk15, BlackDisk16, BlackDisk17,
    							BlackDisk20, BlackDisk21, BlackDisk22, BlackDisk23, BlackDisk24, BlackDisk25, BlackDisk26, BlackDisk27,
    							BlackDisk30, BlackDisk31, BlackDisk32, BlackDisk33,	BlackDisk34, BlackDisk35, BlackDisk36, BlackDisk37,
    							BlackDisk40, BlackDisk41, BlackDisk42, BlackDisk43, BlackDisk44, BlackDisk45, BlackDisk46, BlackDisk47,
    							BlackDisk50, BlackDisk51, BlackDisk52, BlackDisk53, BlackDisk54, BlackDisk55, BlackDisk56, BlackDisk57,
    							BlackDisk60, BlackDisk61, BlackDisk62, BlackDisk63, BlackDisk64, BlackDisk65, BlackDisk66, BlackDisk67,
    							BlackDisk70, BlackDisk71, BlackDisk72, BlackDisk73, BlackDisk74, BlackDisk75, BlackDisk76, BlackDisk77};
    
    	

    	
    	//getting the button clicked in order to opacity it and disable it
    	Object o = event.getSource();
    	Button buttonpushed = (Button)o;
    	buttonpushed.setOpacity(0);
    	buttonpushed.setDisable(true);
    	
    	//getting acces to black and white disks ImageView ids with the position of the button
    	String butname = buttonpushed.getId();
    	//Get the coordinates
    	String strx = butname.substring(6,7);
    	String stry = butname.substring(7,8);
    	
    	int x = Integer.parseInt(strx);
    	int y = Integer.parseInt(stry);
    	
    	Position position = new Position(x,y);
    		
    	//white's turn
    	if(Main.testGame.currentGame.currentPlayer==Color.WHITE) {
    		String whitenameiv = "WhiteDisk" +butname.substring(6);
    		for(int i = 0; i<WhiteTab.length; i++) {
    			if(WhiteTab[i].getId().equals(whitenameiv)) {
    				//displaying white disk on the square of the clicked button
    					WhiteTab[i].setVisible(true);
    					
    					Move move = new Move(Color.WHITE, position, Main.testGame.currentGame);
    					
    				//flip et met la pos dans la grid
    					ArrayList<Position> flippedDisks = move.flipDisks();
        				
        				for(Position toflip : flippedDisks) {
        					String whiteivtodisplay = "WhiteDisk" + Integer.valueOf(toflip.x) + Integer.valueOf(toflip.y);
        				
        					flipDisks(whiteivtodisplay);
        				}
    					
    				//Change le tour
    					Main.testGame.currentGame.currentPlayer=Color.BLACK;
    				
    			}
    		}
    	}
    	//black's turn
    	else if(Main.testGame.currentGame.currentPlayer==Color.BLACK) {
    		String blacknameiv = "BlackDisk" +butname.substring(6);
    		for(int i = 0; i<BlackTab.length; i++) {
    			if(BlackTab[i].getId().equals(blacknameiv)) {
    				//displaying black disk on the square of the clicked button
    				BlackTab[i].setVisible(true);
    				
    				Move move = new Move(Color.BLACK, position, Main.testGame.currentGame);
    				//flip et met la pos dans la grid
    				ArrayList<Position> flippedDisks = move.flipDisks();
    				
    				for(Position toflip : flippedDisks) {
    					String blackivtodisplay = "BlackDisk" + Integer.valueOf(toflip.x) + Integer.valueOf(toflip.y);
    					System.out.println(blackivtodisplay);
    					
    					flipDisks(blackivtodisplay);
    				}
    				
    				Main.testGame.currentGame.currentPlayer=Color.WHITE;
    				
    			}
    		}
    	}
    	
    	Main.testGame.currentGame.displayGrid(); //test affichage
    	
    	
    	
    	
    	// Disabling every button
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			String buttontodisable = "button" + Integer.valueOf(i) + Integer.valueOf(j);
    			//System.out.println(buttontodisable);
    			disableButton(buttontodisable);
    		}
    	}
    	
    	ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play in
    	System.out.println(validPositions.toString());
    	for(Move buttontodisplay: validPositions) {
    		String but_x = String.valueOf(buttontodisplay.position.x);
    		String but_y = String.valueOf(buttontodisplay.position.y);
    		String but_string = "button" + but_x + but_y;
    		displayButton(but_string);
    		
    	}
    	
    		
    	
    			 
    	
    	
    	

    	
    }
    
    /**
     * 
     */
void flipDisks(String disktoflip) {
	
	ImageView[] WhiteTab = {WhiteDisk00, WhiteDisk01, WhiteDisk02, WhiteDisk03, WhiteDisk04, WhiteDisk05, WhiteDisk06, WhiteDisk07, 
							WhiteDisk10, WhiteDisk11, WhiteDisk12, WhiteDisk13, WhiteDisk14, WhiteDisk15, WhiteDisk16, WhiteDisk17,
							WhiteDisk20, WhiteDisk21, WhiteDisk22, WhiteDisk23, WhiteDisk24, WhiteDisk25, WhiteDisk26, WhiteDisk27,
							WhiteDisk30, WhiteDisk31, WhiteDisk32, WhiteDisk33, WhiteDisk34, WhiteDisk35, WhiteDisk36, WhiteDisk37,
							WhiteDisk40, WhiteDisk41, WhiteDisk42, WhiteDisk43,	WhiteDisk44, WhiteDisk45, WhiteDisk46, WhiteDisk47,
							WhiteDisk50, WhiteDisk51, WhiteDisk52, WhiteDisk53, WhiteDisk54, WhiteDisk55, WhiteDisk56, WhiteDisk57,
							WhiteDisk60, WhiteDisk61, WhiteDisk62, WhiteDisk63, WhiteDisk64, WhiteDisk65, WhiteDisk66, WhiteDisk67,
							WhiteDisk70, WhiteDisk71, WhiteDisk72, WhiteDisk73, WhiteDisk74, WhiteDisk75, WhiteDisk76, WhiteDisk77};
	
	ImageView[] BlackTab = {BlackDisk00, BlackDisk01, BlackDisk02, BlackDisk03, BlackDisk04, BlackDisk05, BlackDisk06, BlackDisk07, 
							BlackDisk10, BlackDisk11, BlackDisk12, BlackDisk13, BlackDisk14, BlackDisk15, BlackDisk16, BlackDisk17,
							BlackDisk20, BlackDisk21, BlackDisk22, BlackDisk23, BlackDisk24, BlackDisk25, BlackDisk26, BlackDisk27,
							BlackDisk30, BlackDisk31, BlackDisk32, BlackDisk33,	BlackDisk34, BlackDisk35, BlackDisk36, BlackDisk37,
							BlackDisk40, BlackDisk41, BlackDisk42, BlackDisk43, BlackDisk44, BlackDisk45, BlackDisk46, BlackDisk47,
							BlackDisk50, BlackDisk51, BlackDisk52, BlackDisk53, BlackDisk54, BlackDisk55, BlackDisk56, BlackDisk57,
							BlackDisk60, BlackDisk61, BlackDisk62, BlackDisk63, BlackDisk64, BlackDisk65, BlackDisk66, BlackDisk67,
							BlackDisk70, BlackDisk71, BlackDisk72, BlackDisk73, BlackDisk74, BlackDisk75, BlackDisk76, BlackDisk77};

	if(Main.testGame.currentGame.currentPlayer==Color.BLACK) {
		//System.out.println(disktoflip);
		for(int i = 0; i<BlackTab.length; i++) {
			if(BlackTab[i].getId().equals(disktoflip)) {
				System.out.println(disktoflip);
				WhiteTab[i].setVisible(false);
				BlackTab[i].setVisible(true);
				
				}
			}
	
		}
	else if(Main.testGame.currentGame.currentPlayer==Color.WHITE) {
		for(int i = 0; i<WhiteTab.length; i++) {
			if(WhiteTab[i].getId().equals(disktoflip)) {
				System.out.println(disktoflip);
				BlackTab[i].setVisible(false);
				WhiteTab[i].setVisible(true);
							}
		}
	}
}
void disableButton(String buttontodisable) {

	Button[] buttonTab =   {button00, button01, button02, button03, button04, button05, button06, button07,
							button10, button11, button12, button13, button14, button15, button16, button17,
							button20, button21, button22, button23, button24, button25, button26, button27,
							button30, button31, button32, button33, button34, button35, button36, button37,
							button40, button41, button42, button43, button44, button45, button46, button47,
							button50, button51, button52, button53, button54, button55, button56, button57,
							button60, button61, button62, button63, button64, button65, button66, button67,
							button70, button71, button72, button73, button74, button75, button76, button77};
	
	for(int i = 0; i<buttonTab.length; i++) {
		if(buttonTab[i].getId().equals(buttontodisable)) {
				buttonTab[i].setDisable(true);
				buttonTab[i].setVisible(false);
	}
}
	
	
}

void displayButton(String buttonvalid) {
	//On passe en argument l'id du bouton pour l'afficher et l'activer.
	Button[] buttonTab =   {button00, button01, button02, button03, button04, button05, button06, button07,
			    			button10, button11, button12, button13, button14, button15, button16, button17,
			    			button20, button21, button22, button23, button24, button25, button26, button27,
			    			button30, button31, button32, button33, button34, button35, button36, button37,
			    			button40, button41, button42, button43, button44, button45, button46, button47,
			    			button50, button51, button52, button53, button54, button55, button56, button57,
			    			button60, button61, button62, button63, button64, button65, button66, button67,
			    			button70, button71, button72, button73, button74, button75, button76, button77};
	
	for(int i = 0; i<buttonTab.length; i++) {
		if(buttonTab[i].getId().equals(buttonvalid)) {
				buttonTab[i].setDisable(false);
				buttonTab[i].setVisible(true);
		}
	}
}



@FXML
void onClickPlay(MouseEvent event) {
	
	ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play in
	System.out.println(validPositions.toString());
	for(Move buttontodisplay: validPositions) {
		String but_x = String.valueOf(buttontodisplay.position.x);
		String but_y = String.valueOf(buttontodisplay.position.y);
		String but_string = "button" + but_x + but_y;
		displayButton(but_string);
		
	}
	
	//Initialisation des coups possibles pour les noirs (toujours les mêmes coups de départ)
	disableButton("button33");
	disableButton("button44");
	disableButton("button43");
	disableButton("button34");
	
    
	buttonPlay.setDisable(true);
		}



@FXML
void onClickLeave(MouseEvent event) {
	//permet de cliquer sur un bouton "quitter", ce qui permet de quitter le jeu sans erreur
	Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
	stage.close();
		}
}





