package interfaceGraphique;
import mainpck.IHM;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	 
	private static boolean holdShift = false;
	private static boolean holdCtrl = false;
	
    public static void main(String[] args) {
        launch(args);
    }
 
    //keybord
    public void afficheDebFin(TextField t1, TextField t2, IHM i) {
    	t1.setText("Début : " + i.getStartSel());
    	t2.setText("Fin : " + i.getEndSel());
    }
    
    public void afficheShift(TextField t) {
    	if(holdShift) {
    		t.setText("~ SHIFT ~");
    	} else {
    		t.setText("");
    	}
    }
    
    public void afficheCtrl(TextField t) {
    	if(holdCtrl) {
    		t.setText("~ CTRL ~");
    	} else {
    		t.setText("");
    	}
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	IHM ihm = new IHM();
    	
    	//container
    	VBox root = new VBox();
    	root.setAlignment(Pos.CENTER);
    	
    	HBox intoBox = new HBox();
    	intoBox.setAlignment(Pos.CENTER);

    	root.getChildren().add(new Label("Taper un message : "));

    	//textArea
    	TextArea textArea = new TextArea();
    	
    	TextField Begin = new TextField();
    	Begin.setDisable(true);
    	TextField End = new TextField();
    	End.setDisable(true);
    	TextField Shift = new TextField();
    	Shift.setDisable(true);
    	TextField Ctrl = new TextField();
    	Ctrl.setDisable(true);
    	
    	intoBox.getChildren().add(Begin);
    	intoBox.getChildren().add(End);
    	intoBox.getChildren().add(Shift);
    	intoBox.getChildren().add(Ctrl);
    	
    	//modif clavier
    	textArea.addEventFilter(KeyEvent.ANY, e -> {
    		if(e.getEventType().equals(KeyEvent.KEY_RELEASED)) {
    			
    			if(e.getCode().isLetterKey() || e.getCode().isWhitespaceKey() || e.getCode().isDigitKey()){

					System.out.println("Tapé : " + e.getText());
					
    				if(e.getCode().equals(KeyCode.C) && holdCtrl) {
    					System.out.println("CTRL C, copier ");
    					ihm.copier.execute();
    				} else if (e.getCode().equals(KeyCode.V) && holdCtrl) {
    					System.out.println("CTRL V, coller");
    					ihm.paste.execute();
    				} else if(e.getCode().equals(KeyCode.X) && holdCtrl){
    					System.out.println("CTRL X, couper");
    					ihm.cut.execute();
    				} else {
    					ihm.insert(e.getText().charAt(0));
    					ihm.insert.execute();
    				}
    				
    				textArea.setText(ihm.read());
    				textArea.positionCaret(ihm.getStartSel());
    				
    				afficheDebFin(Begin, End, ihm);
    				
    			} else if(e.getCode().equals(KeyCode.BACK_SPACE)){
				
					ihm.delete.execute();
					
					textArea.setText(ihm.read());
					
					textArea.positionCaret(ihm.getStartSel());
					
					afficheDebFin(Begin, End, ihm);
				
				} else if(e.getCode().equals(KeyCode.LEFT)) {
					
					if(holdShift){
						ihm.selectionner(ihm.getStartSel()-1, ihm.getEndSel());
					} else {
						ihm.selectionner(ihm.getStartSel()-1, ihm.getStartSel()-1);
					}
					ihm.select.execute();
					
					textArea.positionCaret(ihm.getEndSel());
					textArea.selectPositionCaret(ihm.getStartSel());
					
					afficheDebFin(Begin, End, ihm);
					
				} else if(e.getCode().equals(KeyCode.RIGHT)) {
					
					if(holdShift){
						ihm.selectionner(ihm.getStartSel(), ihm.getEndSel()+1);
					} else {
						ihm.selectionner(ihm.getStartSel()+1, ihm.getStartSel()+1);
					}
					ihm.select.execute();
					
					textArea.positionCaret(ihm.getStartSel());
					textArea.selectPositionCaret(ihm.getEndSel());
					
					afficheDebFin(Begin, End, ihm);
					
				}  else if(e.getCode().equals(KeyCode.SHIFT)) {
					
					holdShift = false;
					afficheShift(Shift);
					
				} else if(e.getCode().equals(KeyCode.CONTROL)) {
					
					holdCtrl = false;
					afficheCtrl(Ctrl);
				}
    		}	else if(e.getEventType().equals(KeyEvent.KEY_PRESSED)) {
				
				if(e.getCode().equals(KeyCode.SHIFT)) {
					
					holdShift = true;
					afficheShift(Shift);
					
				} else if(e.getCode().equals(KeyCode.CONTROL)) {
					
					holdCtrl = true;
					afficheCtrl(Ctrl);
				}
				
			}
			
			e.consume();
		});
		
		//version button
		HBox aLotOfButton = new HBox();
		aLotOfButton.setAlignment(Pos.CENTER);
		HBox OptionSelection = new HBox();
		OptionSelection.setAlignment(Pos.CENTER);
		
		Button copier = new Button("Copier");
		Button coller = new Button("Coller");
		Button couper = new Button("Couper");
		Button select = new Button("Selectionner");
		
		TextField deb = new TextField("Début Sélection");
		deb.setDisable(true);
		TextArea debut = new TextArea();
		debut.setPrefSize(5, 5);
		debut.setWrapText(true);
		
		TextField fin = new TextField("Fin Sélection");
		fin.setDisable(true);
		TextArea theend = new TextArea();
		theend.setPrefSize(5, 5);
		theend.setWrapText(true);
		
		//selectionner
		select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String tmpdeb = debut.getText();
            	String tmpfin = theend.getText();
            	ihm.selectionner(Integer.parseInt(tmpdeb),Integer.parseInt(tmpfin));
            	ihm.select.execute();
            	textArea.setText(ihm.read());
            	textArea.positionCaret(ihm.getStartSel());
				
				afficheDebFin(Begin, End, ihm);
            	
            	textArea.positionCaret(ihm.getStartSel());
				textArea.selectPositionCaret(ihm.getEndSel());
				
				afficheDebFin(Begin, End, ihm);
				
                System.out.println("Contenu sélectionner !");
            }
        });
		

		//copier
		copier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Contenu Copier !");
                ihm.copier.execute();
            }
        });
		

		//copier
		coller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ihm.paste.execute();
                textArea.setText(ihm.read());
                textArea.positionCaret(ihm.getStartSel());
				
				afficheDebFin(Begin, End, ihm);
				
                System.out.println("Contenu Coller !");
            }
        });
		
		//couper
		couper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ihm.cut.execute();
                textArea.setText(ihm.read());
                textArea.positionCaret(ihm.getStartSel());
				
				afficheDebFin(Begin, End, ihm);
                System.out.println("Contenu Couper !");
            }
        });
		
		
		aLotOfButton.getChildren().add(copier);
		aLotOfButton.getChildren().add(coller);	
		aLotOfButton.getChildren().add(couper);
		
		OptionSelection.getChildren().add(select);
		OptionSelection.getChildren().add(deb);
		OptionSelection.getChildren().add(debut);
		OptionSelection.getChildren().add(fin);
		OptionSelection.getChildren().add(theend);
		
		root.getChildren().add(textArea);
		root.getChildren().add(intoBox);
		root.getChildren().add(aLotOfButton);
		root.getChildren().add(OptionSelection);
		
    	primaryStage.setTitle("MDI");
		Scene scene = new Scene(root, 500, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}
