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

    //images declaration
    @FXML
    private ImageView Board, woodbackground, background_endgame, gameOverText, winnerWhite, winnerBlack, curPlayerWhite, curPlayerBlack, chooseMode;
      
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
    
    //Menu buttons declaration
    @FXML
    private Button buttonPlay, buttonLeave, buttonPlayAgain, buttonUndo, buttonRedo, buttonLeaveMenu, buttonBotMode, buttonHumanMode;
    
    //texts that show score declaration
    @FXML
    private TextField whitescore, blackscore;
    
    
   
    //bot = true  -> the bot is used
    //bot = false -> the bot is not used. It is the 1V1 local mode
    boolean bot;
    
    
    /**
     * This method is called either by the button "1 versus 1" or the button "1 versus Bot" when the program is first executed
     * @param event
     */
    
    @FXML
    void onClickButtonMode(MouseEvent event) {
    	Object o = event.getSource();
    	Button buttonmode = (Button)o;
    	whitescore.setDisable(false);
    	blackscore.setDisable(false);
    	chooseMode.setDisable(true);
    	chooseMode.setVisible(false);
    	buttonHumanMode.setDisable(true);
    	buttonHumanMode.setVisible(false);
    	buttonBotMode.setDisable(true);
    	buttonBotMode.setVisible(false);
    	background_endgame.setDisable(true);
    	background_endgame.setVisible(false);
    	if(buttonmode == buttonHumanMode) {
    		//Activate the 1V1 mode
    		bot=false;
    	}
    	
    	else if (buttonmode == buttonBotMode) {
    		//Activate the Bot mode
    		bot=true;
    	}
    }
    
    
    
    /**
     * This method is called by any button we click on the BOARD
     * 
     * Once a button is clicked on: 
     * 	Gets the source of the button with the method Object getSource() 
     * 	Set it invisible and disabled with the methods Button setVisible() and Button setDisable()
     * 
     * +...	
     * 	
     * @param event
     */

    @FXML
    void onClick(MouseEvent event) {
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
    		Main.testGame.currentGame.currentPlayer = Color.WHITE; //next turn
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
    			System.out.println("GAME OVER"); //debug
    			int[] scores = Main.testGame.currentGame.scores();
    			System.out.println("Scores:    o: " + scores[1] + " |   x: " + scores[0]); //debug
    			//the following 11 lines show the game over screen (winner, buttons...)
    			background_endgame.setVisible(true);
    			background_endgame.setDisable(false);
    			gameOverText.setVisible(true);
    			if (scores[0]<scores[1]) { //white wins
    				winnerWhite.setVisible(true);
    			}
    			else if (scores[0]>scores[1]) { //black wins
    				winnerBlack.setVisible(true);
    			}
    			else { //draw: the winner is the current player
    				if (Main.testGame.currentGame.currentPlayer == Color.WHITE) {
    					winnerWhite.setVisible(true);
    				}
    				else {
    					winnerBlack.setVisible(true);
    				}
    			}
    			buttonPlayAgain.setVisible(true);
    			buttonPlayAgain.setDisable(false);
    			buttonLeaveMenu.setVisible(true);
    			buttonLeaveMenu.setDisable(false);
    		}
    	}
    	//the following lines update the current player display
    	if (Main.testGame.currentGame.currentPlayer == Color.WHITE) {
			curPlayerWhite.setVisible(true);
			curPlayerBlack.setVisible(false);
		}
		else {
			curPlayerBlack.setVisible(true);
			curPlayerWhite.setVisible(false);			
		}
    	displayScore(); //updates scores
    }
    
    /**
     * Displays scores by updating the textFields "blackscore" and "whitescore" with the currentGame.scores() method.
     */
    void displayScore() {
    	int[] scores = Main.testGame.currentGame.scores();
    	blackscore.setText(""+scores[0]);
    	whitescore.setText(""+scores[1]);
    }
    
    /**
     * Takes a position in the grid. 
     * 	If it's black's turn, displays a black disk and disables the white disk
     * 	If it's white's turn, displays a black disk and disables the black disk
     * 
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

    /**
     * This method takes the String id of a button and displays or disables the button
	 * 	This method is necessary because we can't use the button methods setDisable and setVisible on a string
	 * 	Then we use the method .getId().equals(String button_id) to get the right button in the array buttonTab
	 * 
     * @param buttontochange the button to be enabled or disabled
     * @param display (if display = true, displays the button. if displays = false, disables the button)
     */
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
	/**
	 * This method takes the String id of an image and displays or disables the image. 
	 * 	This method is necessary because we can't use the button method setVisible on a string.
	 * 	Then we use the method .getId().equals(String button_id) to get the right button in the arrays WhiteTab and BlackTab
	 * 
	 * @param disktochange the disk to be displayed or disabled
	 * @param color (White or Black)
	 * @param display (if display = true, displays the disk. if display = false, disables the disk)
	 */
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
	
	/**
	 * This method is called when we click on the button Play (if you want to play again, you have to click on the button Play Again).
	 *  It enables the 4 valid positions at the start of the game by enabling 4 buttons.
	 *  
	 * @param event
	 */
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
	
	/**
	 * This method is called when we click on the button Leave
	 * allows the player to cleanly quit the game 
	 * 
	 * @param event
	 */
	@FXML
	void onClickLeave(MouseEvent event) { 
		System.out.println("Player has left the game"); //debug
		System.out.println("Scores:    o: " + Main.testGame.currentGame.scores()[1] + " |   x: " + Main.testGame.currentGame.scores()[0]); //debug
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	// CREER METHODE START GAME APPELEE PAR PLAY ET PLAYAGAIN ?
	/**
	 * This method is called when we click on the button Play Again.
	 * 
	 * @param event
	 */
	@FXML
	void onClickPlayAgain(MouseEvent event) {
		//the following lines hide the game over screen
		background_endgame.setVisible(false);
		background_endgame.setDisable(true);
		gameOverText.setVisible(false);
		winnerWhite.setVisible(false);
		winnerBlack.setVisible(false);
		buttonPlayAgain.setDisable(true); //disables Play button: the player cannot start two games at once
		buttonPlayAgain.setVisible(false);
		buttonLeaveMenu.setDisable(true);
		buttonLeaveMenu.setVisible(false);
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


