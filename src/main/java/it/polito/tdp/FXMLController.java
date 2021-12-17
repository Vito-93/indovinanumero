/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

    private final int NMAX = 100;
    private final int TMAX = 8;
    private int segreto;
    private int tentativiFatti;
    private boolean inGioco = false;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader


    @FXML // fx:id="layoutTentativo"
    private HBox layoutTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativoUtente"
    private TextField txtTentativoUtente; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
        //gestione nuova partita
        this.segreto = (int) (Math.random() * NMAX) + 1;
        this.tentativiFatti = 0;
        this.inGioco = true;

        //gestione dell'interfaccia
        this.txtTentativi.setText(Integer.toString(TMAX));
        this.layoutTentativo.setDisable(!this.inGioco);
        this.txtRisultato.clear();

    }

    @FXML
    void doTentativo(ActionEvent event) {
        //lettura input utente
        String ts = txtTentativoUtente.getText();
        int tentativo;
        try {
            tentativo = Integer.parseInt(ts);
        }catch (NumberFormatException e){
            txtRisultato.setText("Devi inserire un numero intero!");
            this.txtTentativoUtente.clear();
            return;
        }
        this.txtTentativoUtente.clear();
        this.tentativiFatti++;
        this.txtTentativi.setText(Integer.toString(TMAX - this.tentativiFatti));

        if(tentativo == this.segreto) {
            //Ho Indovinato
            txtRisultato.setText("HAI VINTO CON " + this.tentativiFatti + " TENTATIVI");
            this.inGioco = false;
            this.layoutTentativo.setDisable(!this.inGioco);
            return;
        }

        if(this.tentativiFatti == TMAX){
            txtRisultato.setText("HAI PERSO! IL NUMERO SEGRETO ERA: " + this.segreto);
            this.inGioco = false;
            this.layoutTentativo.setDisable(!this.inGioco);
            return;
        }

        //Non ho vinto --> devo informare l'utente della bontà del suo tentativo
        if(tentativo < this.segreto){
            txtRisultato.setText("Il numero da indovinare è più GRANDE");
        } else {
            txtRisultato.setText("Il numero da indovinare è più PICCOLO");
        }




    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
