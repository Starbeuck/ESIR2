package interfaceGraphique;
import mainpck.IHM;
import mementopck.Memento;

import java.util.Iterator;

import commandes.Command;
import commandes.Cut;
import commandes.Insert;
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
	private static boolean recordMacro=false;
	public static void main(String[] args) {
		launch(args);
	}

	//affichage début et fin de la selection
	public void afficheDebFin(TextField t1, TextField t2, IHM i) {
		t1.setText("Début : " + i.getStartSel());
		t2.setText("Fin : " + i.getEndSel());
	}

	//savoir si shift est pressé
	public void isShift(TextField t) {
		if(holdShift) {
			t.setText("~ SHIFT hold ~");
		} else {
			t.setText("");
		}
	}

	//savoir si ctrl est pressé
	public void isCtrl(TextField t) {
		if(holdCtrl) {
			t.setText("~ CTRL hold ~");
		} else {
			t.setText("");
		}
	}


	@Override
	public void start(Stage primaryStage) {

		IHM ihm = new IHM();

		//container general
		VBox root = new VBox();
		root.setAlignment(Pos.CENTER);

		//container secondaire
		HBox intoBox = new HBox();
		intoBox.setAlignment(Pos.CENTER);

		root.getChildren().add(new Label("Taper un message : "));

		//ajout info completaire
		TextField Begin = new TextField();
		Begin.setDisable(true);
		TextField End = new TextField();
		End.setDisable(true);
		TextField Shift = new TextField();
		Shift.setDisable(true);
		TextField Ctrl = new TextField();
		Ctrl.setDisable(true);

		//ajout informations container secondaire
		intoBox.getChildren().add(Begin);
		intoBox.getChildren().add(End);
		intoBox.getChildren().add(Shift);
		intoBox.getChildren().add(Ctrl);

		//textArea ou on écrit
		TextArea textArea = new TextArea();

		//modif clavier pour desactiver les shortcuts de base
		textArea.addEventFilter(KeyEvent.ANY, e -> {
			if(e.getEventType().equals(KeyEvent.KEY_RELEASED)) {

				if(e.getCode().isLetterKey() || e.getCode().isWhitespaceKey() || e.getCode().isDigitKey()){

					System.out.println("Tapé : " + e.getText());

					//copier
					if(e.getCode().equals(KeyCode.C) && holdCtrl) {
						System.out.println("CTRL C, copier ");
						ihm.copier.execute();
						if(recordMacro){
							ihm.getbuffer().getMemCom().addCommand(ihm.copier);
						}
					} 

					//coller
					else if (e.getCode().equals(KeyCode.V) && holdCtrl) {
						System.out.println("CTRL V, coller");
						ihm.paste.execute();
						if(recordMacro){
							ihm.getbuffer().getMemCom().addCommand(ihm.paste);
						}
					} 

					//couper
					else if(e.getCode().equals(KeyCode.X) && holdCtrl){
						System.out.println("CTRL X, couper");
						ihm.cut.execute();
						if(recordMacro){
							Command coud= new Cut(ihm.getbuffer());
							ihm.getbuffer().getMemCom().addCommand(coud);
						}
					} 
					//insertion
					else {
						ihm.insert(e.getText().charAt(0));
						ihm.insert.execute();
						if(recordMacro){
							ihm.setTempoChar(e.getText().charAt(0));
							ihm.getbuffer().getMemCom().addCommand(ihm.insert);
						}
					}

					//maj affichage
					textArea.setText(ihm.read());
					textArea.positionCaret(ihm.getStartSel());

					afficheDebFin(Begin, End, ihm);

				} 
				//backspace = delete suprresion lettre
				else if(e.getCode().equals(KeyCode.BACK_SPACE)){
					if("".equals(textArea.getText())){
						e.consume();
					}
					
					ihm.delete.execute();
					textArea.setText(ihm.read());
					textArea.positionCaret(ihm.getStartSel());
					afficheDebFin(Begin, End, ihm);
					if(recordMacro){
						ihm.getbuffer().getMemCom().addCommand(ihm.delete);
					}

				} 


				//si on appuie sur fleche gauche
				else if(e.getCode().equals(KeyCode.LEFT)) {

					//si on shift
					if(holdShift){
						ihm.selectionner(ihm.getStartSel()-1, ihm.getEndSel());
					} else {
						ihm.selectionner(ihm.getStartSel()-1, ihm.getStartSel()-1);
					}

					//on selectionne
					ihm.selection.execute();
					textArea.positionCaret(ihm.getEndSel());
					textArea.selectPositionCaret(ihm.getStartSel());

					afficheDebFin(Begin, End, ihm);

				} 

				//si on appuie sur fleche droite
				else if(e.getCode().equals(KeyCode.RIGHT)) {


					//si on shift
					if(holdShift){
						ihm.selectionner(ihm.getStartSel(), ihm.getEndSel()+1);
					} else {
						ihm.selectionner(ihm.getStartSel()+1, ihm.getStartSel()+1);
					}
					ihm.selection.execute();

					textArea.positionCaret(ihm.getStartSel());
					textArea.selectPositionCaret(ihm.getEndSel());

					afficheDebFin(Begin, End, ihm);
					if(recordMacro){
						ihm.getbuffer().getMemCom().addCommand(ihm.selection);
					}
				}  

				//si on shift
				else if(e.getCode().equals(KeyCode.SHIFT)) {
					holdShift = false;
					isShift(Shift);
				} 

				//si on ctrl
				else if(e.getCode().equals(KeyCode.CONTROL)) {
					holdCtrl = false;
					isCtrl(Ctrl);
				}
			}	

			// si key pressed + shift/ctrl
			else if(e.getEventType().equals(KeyEvent.KEY_PRESSED)) {

				if(e.getCode().equals(KeyCode.SHIFT)) {

					holdShift = true;
					isShift(Shift);

				} else if(e.getCode().equals(KeyCode.CONTROL)) {

					holdCtrl = true;
					isCtrl(Ctrl);
				}

			}

			//sinon on fait rien
			e.consume();
		});



		//version button _ ajout fonction button
		HBox aLotOfButton = new HBox();
		aLotOfButton.setAlignment(Pos.CENTER);
		HBox OptionSelection = new HBox();
		OptionSelection.setAlignment(Pos.CENTER);
		HBox MacroButton = new HBox();
		MacroButton.setAlignment(Pos.CENTER);

		//creation des boutons
		Button copier = new Button("Copier");
		Button coller = new Button("Coller");
		Button couper = new Button("Couper");
		Button select = new Button("Selectionner");
		Button macro = new Button("Macro");
		Button undo = new Button("Undo");
		Button redo = new Button("Redo");
		Button launchMacro= new Button ("launch macro");

		//creation des boutons de la selection
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

		//selectionner _ action bouton
		select.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String tmpdeb = debut.getText();
				String tmpfin = theend.getText();
				ihm.selectionner(Integer.parseInt(tmpdeb),Integer.parseInt(tmpfin));
				ihm.selection.execute();

				if(recordMacro){
					ihm.getbuffer().getMemCom().addCommand(ihm.selection);
				}
				textArea.setText(ihm.read());
				textArea.positionCaret(ihm.getStartSel());

				afficheDebFin(Begin, End, ihm);

				textArea.positionCaret(ihm.getStartSel());
				textArea.selectPositionCaret(ihm.getEndSel());

				afficheDebFin(Begin, End, ihm);

				System.out.println("Contenu sélectionner !");
			}
		});


		//copier _ action button
		copier.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Contenu Copier !");
				ihm.copier.execute();
				if(recordMacro){
					ihm.getbuffer().getMemCom().addCommand(ihm.copier);
				}
			}
		});


		//copier _ action button
		coller.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ihm.paste.execute();
				textArea.setText(ihm.read());
				textArea.positionCaret(ihm.getStartSel());
				if(recordMacro){
					ihm.getbuffer().getMemCom().addCommand(ihm.paste);
				}

				afficheDebFin(Begin, End, ihm);

				System.out.println("Contenu Coller !");
			}
		});

		//couper _ action button
		couper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ihm.cut.execute();
				if(recordMacro){
					ihm.getbuffer().getMemCom().addCommand(ihm.cut);
				}

				textArea.setText(ihm.read());
				textArea.positionCaret(ihm.getStartSel());

				afficheDebFin(Begin, End, ihm);
				System.out.println("Contenu Couper !");
			}
		});

		//macro _ action button
		macro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				ihm.getbuffer().getMemCom().record();
				recordMacro =ihm.getbuffer().getMemCom().getStatusMacro();
				if (recordMacro){
					ihm.getbuffer().getMemCom().resetListCommandus();
					System.out.println("Debut Macro !");
				}else{
					System.out.println("Fin Macro !");
				}
			}
		});

		//macro lance la macro 
		launchMacro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Iterator<Command> it = ihm.getbuffer().getMemCom().getListComm().iterator();
				while(it.hasNext()) {
					it.next().execute();
					textArea.setText(ihm.read());

				}

				ihm.getbuffer().getMemCom().resetListCommandus();

				System.out.println("Macro faite !");
			}
		});


		//undo _ action button
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ihm.getbuffer().undo();
				textArea.setText(ihm.read());
				textArea.positionCaret(ihm.getStartSel());

				afficheDebFin(Begin, End, ihm);
				System.out.println("Undo");
			}
		});

		//redo _ action button
		redo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ihm.getbuffer().redo();
				textArea.setText(ihm.read());
				textArea.positionCaret(ihm.getStartSel());

				afficheDebFin(Begin, End, ihm);
				System.out.println("Redo");
			}
		});





		//ajout des boutons au container respectif
		aLotOfButton.getChildren().add(copier);
		aLotOfButton.getChildren().add(coller);	
		aLotOfButton.getChildren().add(couper);
		aLotOfButton.getChildren().add(macro);
		aLotOfButton.getChildren().add(launchMacro);

		OptionSelection.getChildren().add(select);
		OptionSelection.getChildren().add(deb);
		OptionSelection.getChildren().add(debut);
		OptionSelection.getChildren().add(fin);
		OptionSelection.getChildren().add(theend);

		MacroButton.getChildren().add(undo);
		MacroButton.getChildren().add(redo);

		//ajout du container general au container root
		root.getChildren().add(intoBox);
		root.getChildren().add(textArea);
		root.getChildren().add(aLotOfButton);
		root.getChildren().add(OptionSelection);
		root.getChildren().add(MacroButton);

		//parametre + affichage de la fenetre
		primaryStage.setTitle("MDI");
		Scene scene = new Scene(root, 500, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
