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
    private TextField whitescore, blackscore, whitescoreglobal, blackscoreglobal;
    
    //bot = true  -> the bot is used
    //bot = false -> the bot is not used. It is the 1V1 local mode
    boolean bot;
    
    
    //initializing global scores to 0. No need to initalize the disk scores, it is done by the score function
    int blackGlobalScore =0;
    int whiteGlobalScore =0;
    
    //variable that is used to know how many redo we can do in a row (=number of undo we just did in a row)
    int numberOfUndoInARow=0;
    
    /**
     * This method is called either by the button "1 versus 1" or the button "1 versus Bot" when the program is first executed
     * First initializes buttons and textfields to make the game playable for the player
     * Then, if the player chooses 1versusBot mode : enable bot (bot=true). If the player chooses the 1versus1 mode : disable the bot (bot=false)
     * @param event
     */
    
    @FXML
    void onClickButtonMode(MouseEvent event) {
    	Object o = event.getSource();
    	Button buttonmode = (Button)o;
    	whitescore.setDisable(false);
    	blackscore.setDisable(false);
    	whitescoreglobal.setDisable(false);
    	blackscoreglobal.setDisable(false);
    	chooseMode.setDisable(true);
    	chooseMode.setVisible(false);
    	buttonHumanMode.setDisable(true);
    	buttonHumanMode.setVisible(false);
    	buttonBotMode.setDisable(true);
    	buttonBotMode.setVisible(false);
    	background_endgame.setDisable(true);
    	background_endgame.setVisible(false);
    	buttonUndo.setDisable(true);
    	buttonRedo.setDisable(true);
    	
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
     * This method is called by any button we click on the BOARD (not menu buttons outside of the board).
     * 
     * Once a button is clicked on: 
     * 	Displays the disk according to the position of the button we clicked on and according to the color of the current player
     * 	Switches player and scans the valid positions for the current player
     * 
     * 	if 1versus1 mode :
     * 		if the new player can play : displays its valid positions with the buttons
     * 		if not : switches player.
     * 			if the new player can play : displays its valid positions with the buttons
     * 			if not : none of the players can play : END THE GAME
     * 
     * 	if 1versusBot mode :
     * 		if the bot can play : chooses a position and play. 
     * 							  switches to human player
     * 							  repeat :
     * 									   if the human player can play : displays its valid positions and EXIT the loop
     * 									   if not : switches to bot. scans its valid positions
     * 												if the bot can play : chooses a move and plays (go to repeat)
     * 												else : both the bot and the players cannot play : END THE GAME
     *		
     *		if the bot cannnot play : switches to human player, scans its valid positions
     *								  if the human player can play : displays its valid positions
     *								  if not : both the player and the bot cannot play : END THE GAME	
     * @param event 
     * 
     */

    @FXML
    void onClick(MouseEvent event){
    	numberOfUndoInARow=0;//to tell redo button to enable or disable
    	Main.testGame.currentGame.grids[Main.testGame.currentGame.turn] = Main.testGame.currentGame.copygrid();
    	Main.testGame.currentGame.players[Main.testGame.currentGame.turn] = Main.testGame.currentGame.currentPlayer;
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
    		String whitenameiv = "WhiteDisk" +butname.substring(6);
    		displayDisk(whitenameiv, Color.WHITE, true); //displays the black disk corresponding to the button clicked
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
    	//the following 5 lines disable every button on the board
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			String buttontodisable = "button" + Integer.valueOf(i) + Integer.valueOf(j);
    			displayButton(buttontodisable, false);
    		}
    	}
    	ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play
    	if(bot==false) { //if two humans are playing together
    		if (validPositions.size()!=0) { //if the current player can play
	    			for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
	    				displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);				
	    			}
    		}
    		else { //if the current player cannot play, skip a turn
    			Main.testGame.currentGame.currentPlayer = Main.testGame.currentGame.currentPlayer.Opponent(); //skips a turn
    			validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play for the new player
    			if (validPositions.size()!=0) { //if the new current player can play
    				for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
    					displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);	
    				}
    			}
    			else { //none of the players can play: end the game
    				int[] scores = Main.testGame.currentGame.scores();
    				//the following 11 lines show the game over screen (winner, buttons...)
    				background_endgame.setVisible(true);
    				background_endgame.setDisable(false);
    				gameOverText.setVisible(true);
    				if (scores[0]<scores[1]) { //white wins
    					winnerWhite.setVisible(true);
    					whiteGlobalScore++;
    				}
    				else if (scores[0]>scores[1]) { //black wins
    					winnerBlack.setVisible(true);
    					blackGlobalScore++;
    				}
    				else { //draw: the winner is the current player
    					if (Main.testGame.currentGame.currentPlayer == Color.WHITE) {
    						winnerWhite.setVisible(true);
    						whiteGlobalScore++;
    					}
    					else {
    						winnerBlack.setVisible(true);
    						blackGlobalScore++;
    					}
    				}
    				displayGlobalScore();//update global score
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
    		Main.testGame.currentGame.turn++; //update next turn
    		if(Main.testGame.currentGame.turn>=1) {
    			buttonUndo.setDisable(false);
    		}
    		else {
    			buttonUndo.setDisable(true);
    		}
    		buttonRedo.setDisable(true);
    		Main.testGame.currentGame.grids[Main.testGame.currentGame.turn] = Main.testGame.currentGame.grid; //stores the obtained grid in order to restore it with a redo if needed
    		}
    	else if(bot==true) { //if a human is playing against the bot
		if (validPositions.size()!=0) { //if the bot can play
			Bot randomtestbot = new Bot(validPositions);//initializes the bot
			Move randomMove = randomtestbot.simpleBot(Color.WHITE, Main.testGame.currentGame.turn);//computes a random move among the valid positions
			if(Main.testGame.currentGame.currentPlayer==Color.WHITE) {
				String whitenameiv = "WhiteDisk" + randomMove.position.x + randomMove.position.y;
				displayDisk(whitenameiv, Color.WHITE, true); // the bot plays the move
				ArrayList<Position> flippedDisks = randomMove.flipDisks();
	    		for(Position toflip : flippedDisks) {
	    			String positiontoflip = String.valueOf(toflip.x) + String.valueOf(toflip.y);
	    			flipDisks(positiontoflip);
	    		}	
			}
			else if(Main.testGame.currentGame.currentPlayer==Color.BLACK) {
				String blacknameiv = "BlackDisk" + randomMove.position.x + randomMove.position.y;
				displayDisk(blacknameiv, Color.WHITE, true); // the bot plays the move
				ArrayList<Position> flippedDisks = randomMove.flipDisks();
	    		for(Position toflip : flippedDisks) {
	    			String positiontoflip = String.valueOf(toflip.x) + String.valueOf(toflip.y);
	    			flipDisks(positiontoflip);
	    		}	
			}
			Main.testGame.currentGame.currentPlayer = Main.testGame.currentGame.currentPlayer.Opponent(); //next turn
			validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer);
			boolean keep=true; // defines a boolean to stay in the loop while the player skips turn and the bot plays
			while(keep==true){
			  	if(validPositions.size()!=0){ //if the player can play
					for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
						displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);	
						keep=false;//sort de la boucle
						}
					}
				else{ //skip turn
			 		Main.testGame.currentGame.currentPlayer = Main.testGame.currentGame.currentPlayer.Opponent(); //next turn
					validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer);
						if(validPositions.size()!=0){ // if the bot can play
							Bot randomtestbotafterskip = new Bot(validPositions);//initializes the bot
			    			Move randomMoveafterskip = randomtestbotafterskip.simpleBot(Color.WHITE, Main.testGame.currentGame.turn);//computes a random move among the valid positions
			    			if(Main.testGame.currentGame.currentPlayer==Color.WHITE) {
			    				String whitenameiv = "WhiteDisk" + randomMoveafterskip.position.x + randomMoveafterskip.position.y;
			    				displayDisk(whitenameiv, Color.WHITE, true); // the bot plays the move
			    				ArrayList<Position> flippedDisks = randomMoveafterskip.flipDisks();
			    	    		for(Position toflip : flippedDisks) {
			    	    			String positiontoflip = String.valueOf(toflip.x) + String.valueOf(toflip.y);
			    	    			flipDisks(positiontoflip);
			    	    		}	
			    			}
			    			else if(Main.testGame.currentGame.currentPlayer==Color.BLACK) {
			    				String blacknameiv = "BlackDisk" + randomMoveafterskip.position.x + randomMoveafterskip.position.y;
			    				displayDisk(blacknameiv, Color.WHITE, true); // the bot plays the move
			    				ArrayList<Position> flippedDisks = randomMoveafterskip.flipDisks();
			    	    		for(Position toflip : flippedDisks) {
			    	    			String positiontoflip = String.valueOf(toflip.x) + String.valueOf(toflip.y);
			    	    			flipDisks(positiontoflip);
			    	    		}	
			    			}
			    			Main.testGame.currentGame.currentPlayer = Main.testGame.currentGame.currentPlayer.Opponent(); //next turn
			    			validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer);
			    			//goes to the beginning of the loop: either the bot will play again or the player will be able to play, and the loop will end
						}
						else { 	//if none of the players can play: exit procedure
		    				int[] scores = Main.testGame.currentGame.scores();
		    				//the following 11 lines show the game over screen (winner, buttons...)
		    				background_endgame.setVisible(true);
		    				background_endgame.setDisable(false);
		    				gameOverText.setVisible(true);
		    				if (scores[0]<scores[1]) { //white wins
		    					winnerWhite.setVisible(true);
		    					whiteGlobalScore++;
		    				}
		    				else if (scores[0]>scores[1]) { //black wins
		    					winnerBlack.setVisible(true);
		    					blackGlobalScore++;
		    				}
		    				else { //draw: the winner is the current player
		    					if (Main.testGame.currentGame.currentPlayer == Color.WHITE) {
		    						winnerWhite.setVisible(true);
		    						whiteGlobalScore++;
		    					}
		    					else {
		    						winnerBlack.setVisible(true);
		    						blackGlobalScore++;
		    					}
		    				}
		    				displayGlobalScore();//update global score
		    				buttonPlayAgain.setVisible(true);
		    				buttonPlayAgain.setDisable(false);
		    				buttonLeaveMenu.setVisible(true);
		    				buttonLeaveMenu.setDisable(false);
		    				break;
						}
			 		}
    			}
    		}
		else { //if the bot cannot play, skips a turn
			Main.testGame.currentGame.currentPlayer = Main.testGame.currentGame.currentPlayer.Opponent(); //skips a turn
			validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play for the new player
			if (validPositions.size()!=0) { //if the new current player can play (the human player)
				for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
					displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);	
				}
			}
			else { //none of the players can play: end the game
				int[] scores = Main.testGame.currentGame.scores();
				//the following 11 lines show the game over screen (winner, buttons...)
				background_endgame.setVisible(true);
				background_endgame.setDisable(false);
				gameOverText.setVisible(true);
				if (scores[0]<scores[1]) { //white wins
					winnerWhite.setVisible(true);
					whiteGlobalScore++;
				}
				else if (scores[0]>scores[1]) { //black wins
					winnerBlack.setVisible(true);
					blackGlobalScore++;
				}
				else { //draw: the winner is the current player
					if (Main.testGame.currentGame.currentPlayer == Color.WHITE) {
						winnerWhite.setVisible(true);
						whiteGlobalScore++;
					}
					else {
						winnerBlack.setVisible(true);
						blackGlobalScore++;
					}
				}
				displayGlobalScore();//update global score
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
		Main.testGame.currentGame.turn++; //update next turn
		if(Main.testGame.currentGame.turn>=1) {
			buttonUndo.setDisable(false);
		}
		else {
			buttonUndo.setDisable(true);
		}
		buttonRedo.setDisable(true);
		Main.testGame.currentGame.grids[Main.testGame.currentGame.turn] = Main.testGame.currentGame.grid; //stores the obtained grid in order to restore it with a redo if needed
		}
    }
    
    /**
     * Displays scores by updating the textFields "blackscore" and "whitescore" with the currentGame.scores() method.
     */
    void displayScore() {
    	int[] scores = Main.testGame.currentGame.scores();
    	blackscore.setText(""+scores[0]);
    	whitescore.setText(""+scores[1]);
    }
    void displayGlobalScore() {
    	whitescoreglobal.setText(""+whiteGlobalScore);
    	blackscoreglobal.setText(""+blackGlobalScore);
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
     * @param display (if display == true, displays the button. if display == false, disables the button)
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
	 * @param color of the disk to be displayed or disabled. Useful to look into the right array (WhiteTab or BlackTab)
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
	 *  It enables the 4 valid positions at the start of the game by enabling the 4 buttons.
	 *  
	 * @param event
	 */
	@FXML
	void onClickPlay(MouseEvent event) {
		buttonPlay.setDisable(true); //disables Play button: the player cannot start two games at once
		ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play in
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
		Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	/**
	 * This method is called when we click on the button Play Again.
	 * 	First cleans the game over screen and sets up a the right buttons for the player
	 * 	Then disables every disk on the board (no need to clean disk_buttons because at the end of a game : there is no disks to be placed
	 * 	Then initializes the 4 central disks and creates a new GameState by initializing all of its attributes
	 * 	Finally : scans the valid positions to be played -> enables the 4 buttons corresponding to the 4 valid positions of the start and updates scores
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
		buttonUndo.setDisable(true);
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
        Main.testGame.currentGame.grids = new Color[64][8][8]; // initializing the grids of the boardgames
        Main.testGame.currentGame.turn=0; //initialize the turn counter
        Main.testGame.currentGame.players = new Color[64];
		ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play
		for(Move buttontodisplay: validPositions) { //for each valid move, the for loop enables the corresponding button
			String but_x = String.valueOf(buttontodisplay.position.x);
			String but_y = String.valueOf(buttontodisplay.position.y);
			String but_string = "button" + but_x + but_y;
			displayButton(but_string, true);
		}
		displayScore(); 
	}

	
	
	
	/**
	 * method that highlights a button when mouseover (only for disk buttons, on the board)
	 * @param event
	 */
	@FXML
	void onEntered(MouseEvent event) {
		Object o = event.getSource();
    	Button buttonover = (Button)o;
	    buttonover.setStyle("-fx-background-color: #279AF1; -fx-background-radius: 100");
	}
	
	/**
	 * Method that disables the highlight when mouse moves out of the button (only for disk buttons, on the board)
	 * @param event
	 */
	@FXML
	void onExited(MouseEvent event) {
		Object o = event.getSource();
    	Button buttonover = (Button)o;
		buttonover.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 100");
	}
	
	/**
	 * Method that highlights a buttonmenu when mouseover 
	 * @param event
	 */
	@FXML
	void onEnteredMenuButton(MouseEvent event) {	
		Object o = event.getSource();
	    Button buttonover = (Button)o;
	    buttonover.setStyle("-fx-border-color: #279AF1; -fx-background-color: #DCDCDC");
		
	}
	
	/**
	 * Method that disables the highlight when mouse moves out of the buttonmenu
	 * @param event
	 */
	@FXML
	void onExitedMenuButton(MouseEvent event) {
		Object o = event.getSource();
    	Button buttonover = (Button)o;
	    buttonover.setStyle("-fx-border-color: #FFFFFF; -fx-background-color: #FFFFFF");
	}

	/**
	 * First, sets the actual grid to the previous one using grids[]
	 * Then disables every button and disk on the GUI.
	 * If it is 1versus1 mode, we have to switch player. (if 1versusBot mode, no need to, because the human player is the only one that can undo)
	 * Then scans the validpositions to play according to the new (previous) grid and displays the buttons.
	 * Finally : updates the currentplayer fields, images and updates scores
	 * 
	 * IMPORTANT : the variable numberOfUndoInARow is incremented for each undo in a row. It is absolutely necessary to the method redo.
	 * 			   In fact, we can only redo as many times as we did undo without playing meanwhile.
	 * @param event
	 */
	@FXML
	void onClickUndo(MouseEvent event){
		numberOfUndoInARow++;
		Main.testGame.currentGame.turn--; //on undo	
		Main.testGame.currentGame.grid = Main.testGame.currentGame.grids[Main.testGame.currentGame.turn];
		//resetting the grid
		for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			String whitedisktochange = "WhiteDisk" + Integer.valueOf(i) + Integer.valueOf(j);
    			String blackdisktochange= "BlackDisk" + Integer.valueOf(i) + Integer.valueOf(j);
				String buttontodisable = "button" + i + j;
				displayDisk(whitedisktochange, Color.WHITE, false);
				displayDisk(blackdisktochange, Color.BLACK, false);
				displayButton(buttontodisable, false);
				if(Main.testGame.currentGame.grid[i][j]==Color.BLACK){
					displayDisk(blackdisktochange, Color.BLACK, true);
				}
				else if(Main.testGame.currentGame.grid[i][j]==Color.WHITE) {
					displayDisk(whitedisktochange, Color.WHITE, true);
					}
				}
			}
		
		//if bot = false, the other human player has to play, so it changes player. if bot = true, it doesn't change player, 
		//		because it is the same human player that will play. 
		if(bot==false) {
			if(Main.testGame.currentGame.currentPlayer == Color.BLACK) {
				Main.testGame.currentGame.currentPlayer = Color.WHITE;
			}
			else if(Main.testGame.currentGame.currentPlayer == Color.WHITE) {
				Main.testGame.currentGame.currentPlayer = Color.BLACK;
			}
		}
			ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play
			for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
				displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);
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
    	if(Main.testGame.currentGame.turn>=1) {
    		buttonUndo.setDisable(false);
    	}
    	else {
        	buttonUndo.setDisable(true);  	
    	}
    	buttonRedo.setDisable(false);
	}

	/**
	 * First, sets the actual grid to the next one using grids[]
	 * Then disables every button and disk on the GUI.
	 * If it is 1versus1 mode, we have to switch player. (if 1versusBot mode, no need to, because the human player is the only one that can undo)
	 * Then scans the validpositions to play according to the new (next) grid and displays the buttons.
	 * Finally : updates the currentplayer fields, images and updates scores
	 * 
	 * IMPORTANT : the variable numberOfUndoInARow is decremented for each redo in a row. See javadoc on Undo method for more information.
	 * @param event
	 */
	@FXML
	void onClickRedo(MouseEvent event) {
		numberOfUndoInARow--;	
		Main.testGame.currentGame.turn++; // Coming back to the previous turn	
		Main.testGame.currentGame.grid = Main.testGame.currentGame.grids[Main.testGame.currentGame.turn];
		//resetting the grid
		for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			String whitedisktochange = "WhiteDisk" + Integer.valueOf(i) + Integer.valueOf(j);
    			String blackdisktochange= "BlackDisk" + Integer.valueOf(i) + Integer.valueOf(j);
				String buttontodisable = "button" + i + j;
				displayDisk(whitedisktochange, Color.WHITE, false);
				displayDisk(blackdisktochange, Color.BLACK, false);
				displayButton(buttontodisable, false);
				if(Main.testGame.currentGame.grid[i][j]==Color.BLACK){
					displayDisk(blackdisktochange, Color.BLACK, true);
				}
				else if(Main.testGame.currentGame.grid[i][j]==Color.WHITE) {
					displayDisk(whitedisktochange, Color.WHITE, true);
					}
				}
			}
		//if bot = false, the other human player has to play, so it changes player. if bot = true, it doesn't change player, 
		//because it is the same human player that will play. 
		if(bot==false) {
			if(Main.testGame.currentGame.currentPlayer == Color.BLACK) {
				Main.testGame.currentGame.currentPlayer = Color.WHITE;
			}
			else if(Main.testGame.currentGame.currentPlayer == Color.WHITE) {
				Main.testGame.currentGame.currentPlayer = Color.BLACK;
			}
		}
		ArrayList<Move> validPositions = Main.testGame.currentGame.validPositions(Main.testGame.currentGame.currentPlayer); //calculates the list of legal moves to play
    	for(Move buttontodisplay: validPositions) { //for each valid move, enable the corresponding button
    		displayButton("button" + String.valueOf(buttontodisplay.position.x) + String.valueOf(buttontodisplay.position.y), true);	
    	}
    	//the following lines update the current player display
    	if (Main.testGame.currentGame.players[Main.testGame.currentGame.turn] == Color.WHITE) {
			curPlayerWhite.setVisible(true);
			curPlayerBlack.setVisible(false);
		}
		else {
			curPlayerBlack.setVisible(true);
			curPlayerWhite.setVisible(false);			
		}
    	displayScore(); //updates scores
    	if(Main.testGame.currentGame.turn>=1) {
    		buttonUndo.setDisable(false);
    	}
    	else {
        	buttonUndo.setDisable(true);  	
    	}
    	if(numberOfUndoInARow==0) {
    		buttonRedo.setDisable(true);
    	}
	}
}