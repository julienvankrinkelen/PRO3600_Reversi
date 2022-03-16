package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SimpleViewCtrl {

    @FXML
    private ImageView BlackPawn00;
    
    @FXML
    private ImageView BlackPawn01;
    
    @FXML
    private ImageView BlackPawn02;
    
    @FXML
    private ImageView BlackPawn03;

    @FXML
    private ImageView BlackPawn04;

    @FXML
    private ImageView BlackPawn05;

    @FXML
    private ImageView BlackPawn06;

    @FXML
    private ImageView BlackPawn07;

    @FXML
    private ImageView BlackPawn34;

    @FXML
    private ImageView BlackPawn43;

    @FXML
    private ImageView Board;

    @FXML
    private ImageView WhitePawn00;

    @FXML
    private ImageView WhitePawn01;

    @FXML
    private ImageView WhitePawn02;

    @FXML
    private ImageView WhitePawn03;

    @FXML
    private ImageView WhitePawn04;

    @FXML
    private ImageView WhitePawn05;

    @FXML
    private ImageView WhitePawn06;

    @FXML
    private ImageView WhitePawn07;

    @FXML
    private ImageView WhitePawn33;

    @FXML
    private ImageView WhitePawn44;

    @FXML
    private Button button00;

    @FXML
    private Button button01;

    @FXML
    private Button button02;

    @FXML
    private Button button03;

    @FXML
    private Button button04;

    @FXML
    private Button button05;

    @FXML
    private Button button06;

    @FXML
    private Button button07;
   
    

    
   //Remplacer plus tard par la var currentPlayer
	int tour =0;
	
    @FXML
    void onClick(MouseEvent event) {
    	//Création des tableaux des pions noirs / blancs déjà positionnés sur les cases en invisible par défaut
    	//Pour l'instant, test avec uniquement les boutons / ImageView de la première ligne (ligne 0)
    	ImageView[] WhiteTab = {WhitePawn00, WhitePawn01, WhitePawn02, WhitePawn03, WhitePawn04, WhitePawn05, WhitePawn06, WhitePawn07};
    	ImageView[] BlackTab = {BlackPawn00, BlackPawn01, BlackPawn02, BlackPawn03, BlackPawn04, BlackPawn05, BlackPawn06, BlackPawn07};
    	
    	
    	
    	//On récupère le bouton sur lequel on a cliqué pour l'opacifier et le désactiver
    	Object o = event.getSource();
    	Button buttonpushed = (Button)o;
    	buttonpushed.setOpacity(0);
    	buttonpushed.setDisable(true);
    	
    	//On veut avoir accès aux ImageView des pions noirs et blancs : on récupère les id des ImageView avec la position du bouton récupéré
    	String butname = buttonpushed.getId();
    	String blacknameiv = "BlackPawn" +butname.substring(6);
    	String whitenameiv = "WhitePawn" +butname.substring(6);
    		
    	//Si c'est le tour des blancs :
    	if(tour==1) {
    		for(int i = 0; i<WhiteTab.length; i++) {
    			if(WhiteTab[i].getId().equals(whitenameiv)) {
    				//On affiche le pion blanc sur la case du bouton appuyé
    					WhiteTab[i].setVisible(true);
    					tour=0;
    					
    			}
    		}
    	}
    	//Si c'est le tour des noirs : 
    	else if(tour==0) {
    		for(int i = 0; i<BlackTab.length; i++) {
    			if(BlackTab[i].getId().equals(blacknameiv)) {
    				//On affiche le pion noir sur la case du bouton appuyé
    				BlackTab[i].setVisible(true);
    				tour=1;
    			}
  
    		}
    	}
    }
}
  


