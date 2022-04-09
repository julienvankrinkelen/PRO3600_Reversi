package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    
    @FXML
    private ImageView woodbackground, background_endgame;
      
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
    private Button buttonPlay, buttonLeave, buttonPlayAgain, buttonUndo, buttonRedo;
    
    @FXML
    private TextField whitescore, blackscore;
    
    
   

    
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
    	//getting the button clicked in order to hide it and disable it
    	Object o = event.getSource();
    	Button buttonpushed = (Button)o;
    	buttonpushed.setVisible(false);
    	buttonpushed.setDisable(true);    	
    	String butname = buttonpushed.getId(); //gets access to black and white disks ImageView ids with the position of the button
    	//the following two lines extract the coordinates of the button clicked
    	String strx = butname.substring(6,7);
    	String stry = butname.substring(7,8);
    	
    	int x = Integer.parseInt(strx);
    	int y = Integer.parseInt(stry);
    	
    	Position position = new Position(x,y);
    		
    	//white's turn
    	if(Main.testGame.currentGame.currentPlayer==Color.WHITE) {
    		System.out.println("o's turn"); //debug
    		String whitenameiv = "WhiteDisk" +butname.substring(6);
    		displayDisk(whitenameiv, Color.WHITE, true); //displays the blakc disk corresponding to the button clicked
   			Move move = new Move(Color.WHITE, position, Main.testGame.currentGame);
   			//the following 5 lines update the gui by flipping the sandwiched disks and showing the disk that has been played
    		ArrayList<Position> flippedDisks = move.flipDisks();        				
        	for(Position toflip : flippedDisks) {
        		String positiontoflip = String.valueOf(toflip.x) + String.valueOf(toflip.y);
        		flipDisks(positiontoflip);
        	}
    		Main.testGame.currentGame.currentPlayer=Color.BLACK; //next turn
    			
    		
    	}
    	//black's turn
    	else if(Main.testGame.currentGame.currentPlayer==Color.BLACK) {
    		System.out.println("x's turn"); //debug
    		String blacknameiv = "BlackDisk" +butname.substring(6);
    		displayDisk(blacknameiv, Color.BLACK, true); //displays the black disk corresponding to the button clicked
    		Move move = new Move(Color.BLACK, position, Main.testGame.currentGame);
    		//the following 5 lines update the gui by flipping the sandwiched disks and showing the disk that has been played
    		ArrayList<Position> flippedDisks = move.flipDisks();
    		for(Position toflip : flippedDisks) {
    			String positiontoflip = String.valueOf(toflip.x) + String.valueOf(toflip.y);
    			
    			flipDisks(positiontoflip);
    		}
    		Main.testGame.currentGame.currentPlayer=Color.WHITE; //next turn
    			
    		
    	}
    	Main.testGame.currentGame.displayGrid(); //debug
    	//the following 5 lines disable every button on the board
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			String buttontodisable = "button" + Integer.valueOf(i) + Integer.valueOf(j);
    			displayButton(buttontodisable, false);
    		}
    	}
    	ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play
    	if (validPositions.size()!=0) { //if the current player can play
	    	System.out.println("Valid positions: " + validPositions.toString()); //debug
	    	for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
	    		displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);	
	    	}
    	}
    	else { //if the current player cannot play, skip a turn
    		Main.testGame.currentGame.currentPlayer = Main.testGame.currentGame.currentPlayer.Opponent(); //skips a turn
    		System.out.println("Skipped a turn");
    		validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play for the new player
    		if (validPositions.size()!=0) { //if the new current player can play
    			System.out.println("Valid positions: " + validPositions.toString()); //debug
    	    	for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
    	    		displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);	
    	    	}
    		}
    		else { //none of the players can play: end the game
    			background_endgame.setVisible(true);
    			background_endgame.setDisable(false);
    			buttonPlayAgain.setVisible(true);
    			buttonPlayAgain.setDisable(false);
    			System.out.println("GAME OVER"); //debug
    			System.out.println("Scores:    o: " + Main.testGame.currentGame.scores()[1] + " |   x: " + Main.testGame.currentGame.scores()[0]); //debug
    		}
    	}
    	displayScore(); //updates scores
    }
    
    
    void displayScore() {
    	blackscore.setText("black : " + Main.testGame.currentGame.scores()[0]);
    	whitescore.setText("white : " + Main.testGame.currentGame.scores()[1]);
    }
    
    /**
     * Flip one disk in the gui (changes its color)
     * @param disktoflip The disk that has to be flipped
     */
    void flipDisks(String positiontoflip) {
    	String whitetoflip = "WhiteDisk" + positiontoflip;
    	String blacktoflip = "BlackDisk" + positiontoflip;
		if(Main.testGame.currentGame.currentPlayer==Color.BLACK) { //black's turn
		
			displayDisk(whitetoflip, Color.WHITE, false);
			
			displayDisk(blacktoflip, Color.BLACK, true);
			
			}
		else if(Main.testGame.currentGame.currentPlayer==Color.WHITE) { //white's turn
			displayDisk(blacktoflip, Color.BLACK, false);
			
			displayDisk(whitetoflip, Color.WHITE, true);
			
		}
	}


	void displayButton(String buttontochange, boolean display) { //displays or disables button according to boolean
		Button[] buttonTab =   {button00, button01, button02, button03, button04, button05, button06, button07,
				    			button10, button11, button12, button13, button14, button15, button16, button17,
				    			button20, button21, button22, button23, button24, button25, button26, button27,
				    			button30, button31, button32, button33, button34, button35, button36, button37,
				    			button40, button41, button42, button43, button44, button45, button46, button47,
				    			button50, button51, button52, button53, button54, button55, button56, button57,
				    			button60, button61, button62, button63, button64, button65, button66, button67,
				    			button70, button71, button72, button73, button74, button75, button76, button77};
		if(display) {
			for(int i = 0; i<buttonTab.length; i++) {
				if(buttonTab[i].getId().equals(buttontochange)) {
					buttonTab[i].setDisable(false);
					buttonTab[i].setVisible(true);
					System.out.println("Enabled " + buttontochange); //debug
				}
			}
		}
		else {
			for(int i = 0; i<buttonTab.length; i++) {
				if(buttonTab[i].getId().equals(buttontochange)) {
					buttonTab[i].setDisable(true);
					buttonTab[i].setVisible(false);
				}
			}
		}
	}
	void displayDisk(String disktochange, Color color, boolean display) { //displays or disables disk according to boolean	
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
		if(color == Color.BLACK) {
			for(int i = 0; i<BlackTab.length; i++) {
				if(BlackTab[i].getId().equals(disktochange)) {
					if(display == true) {
						BlackTab[i].setVisible(true);
					}
					else if (display == false) {
						BlackTab[i].setVisible(false);
					}
				}
			}
		}
		else if(color == Color.WHITE) {
			for(int i = 0; i<WhiteTab.length; i++) {
				if(WhiteTab[i].getId().equals(disktochange)) {
					if(display==true) {
						WhiteTab[i].setVisible(true);
					}
					else if(display == false) {
						WhiteTab[i].setVisible(false);
					}
				}
			}
		}
	}

	@FXML
	void onClickPlay(MouseEvent event) {
		buttonPlay.setDisable(true); //disables Play button: the player cannot start two games at once
		ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play in
		System.out.println("Valid positions: " + validPositions.toString()); //debug
		for(Move buttontodisplay: validPositions) { //for each valid move, the for loop enables the corresponding button
			String but_x = String.valueOf(buttontodisplay.position.x);
			String but_y = String.valueOf(buttontodisplay.position.y);
			String but_string = "button" + but_x + but_y;
			displayButton(but_string, true);
		}
	}

	@FXML
	void onClickLeave(MouseEvent event) { //allows the player to cleanly quit the game by clicking on the "Leave" button
		System.out.println("Player has left the game"); //debug
		System.out.println("Scores:    o: " + Main.testGame.currentGame.scores()[1] + " |   x: " + Main.testGame.currentGame.scores()[0]); //debug
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	// CREER METHODE START GAME APPELEE PAR PLAY ET PLAYAGAIN
	
	@FXML
	void onClickPlayAgain(MouseEvent event) {
		background_endgame.setVisible(false);
		background_endgame.setDisable(true);
		buttonPlayAgain.setDisable(true); //disables Play button: the player cannot start two games at once
		buttonPlay.setVisible(false);
		System.out.println("Click on Play Again detected"); //debug
		//Disables every disk on the board to play again
		for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			String whitedisktodisable = "WhiteDisk" + Integer.valueOf(i) + Integer.valueOf(j);
    			String blackdisktodisable = "BlackDisk" + Integer.valueOf(i) + Integer.valueOf(j);
    			displayDisk(whitedisktodisable, Color.WHITE, false);
    			displayDisk(blackdisktodisable, Color.BLACK, false);
    		}
    	}
		//the following 4 lines display the four initial disks
		displayDisk("BlackDisk34", Color.BLACK, true);
		displayDisk("BlackDisk43", Color.BLACK, true);
		displayDisk("WhiteDisk33", Color.WHITE, true);
		displayDisk("WhiteDisk44", Color.WHITE, true);
		Main.testGame.currentGame.currentPlayer = Color.BLACK; //initializes first player
		Main.testGame.currentGame.grid = new Color[][]{
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.WHITE, Color.BLACK, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.BLACK, Color.WHITE, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY},
            {Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY, Color.EMPTY}
            }; //initializing boardgame grid
		ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play
		System.out.println("Valid positions: " + validPositions.toString()); //debug
		for(Move buttontodisplay: validPositions) { //for each valid move, the for loop enables the corresponding button
			String but_x = String.valueOf(buttontodisplay.position.x);
			String but_y = String.valueOf(buttontodisplay.position.y);
			String but_string = "button" + but_x + but_y;
			displayButton(but_string, true);
			System.out.println(but_string);
		}
	}

//method that highlights a button when mouseover
	@FXML
	void onEntered(MouseEvent event) {
		
		Object o = event.getSource();
    	Button buttonover = (Button)o;
		
	    buttonover.setStyle("-fx-background-color: #279AF1; -fx-background-radius: 100");
	}
//method that disables the highlight when mouse moves out of the button
	@FXML
	void onExited(MouseEvent event) {
		Object o = event.getSource();
    	Button buttonover = (Button)o;
		
		buttonover.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 100");
	}

//method that highlights a buttonmenu when mouseover
	@FXML
	void onEnteredMenuButton(MouseEvent event) {	
		Object o = event.getSource();
	    Button buttonover = (Button)o;
	    buttonover.setStyle("-fx-border-color: #279AF1; -fx-background-color: #DCDCDC");
		
	}
	
//method that disables the highlight when mouse moves out of the buttonmenu
	@FXML
	void onExitedMenuButton(MouseEvent event) {
		Object o = event.getSource();
    	Button buttonover = (Button)o;
	    buttonover.setStyle("-fx-border-color: #FFFFFF; -fx-background-color: #FFFFFF");
	
	}


	@FXML
	void onClickUndo(MouseEvent event){
			
	}

	@FXML
	void onClickRedo(MouseEvent event) {
		
	}

}


