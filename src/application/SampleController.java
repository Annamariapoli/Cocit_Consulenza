package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import bean.AutoreAutoreArticolo;
import bean.Creator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model model= new Model();
	
	public void setModel(Model model){
		this.model=model;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Creator> combo1;

    @FXML
    private ComboBox<Creator> combo2;

    @FXML
    private Button btnCerca;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCerca(ActionEvent event) throws SQLException {
    	txtResult.clear();
    	Creator c1 = combo1.getValue();
    	Creator c2 = combo2.getValue();
    	if(c1.equals(c2)){
    		txtResult.appendText("Seleziona due autori diversi \n");
    		return;
    	}
    	if(c1==null || c2==null){
    		txtResult.appendText("Seleziona due autori!\n");
    		return;
    	}
    	
    	model.buildGraph();
//    	
//  
//    	int peso = model.pesoArcoTraDue(c1, c2);
//    	txtResult.appendText("Il peso del loro arco �  : "+ peso+" \n");
////  
    	
    	txtResult.appendText("VICINI grafo !\n");
    	
    	List<Creator> vicini = model.getVicini(c1);
    	
    	txtResult.appendText("I vicini di c1 ( "+c1+" )  sono :  \n");
    	txtResult.appendText(vicini.toString());
    	
    	txtResult.appendText("VICINI query: \n");
    	List<Creator > viciniQuery = model.getViciniQuery(c1);
    	txtResult.appendText(viciniQuery.toString());
//    	
//    	
//    	int pesoArcoMax = model.getArcoPiuPesante();
//    	txtResult.appendText(" il peso dell'arco piu pesnate �  : "+ pesoArcoMax);
//    
//    	List<Creator> cammino = model.getCamminoMinimo(c1, c2);
//    	if(cammino.size()==0){
//    		txtResult.appendText("Il cammino non esiste \n ");
//    	}
//    	else {
//    		txtResult.appendText("Il cammino tra "+ c1 + " e " + c2 + "   �  : ");
//    		for(Creator cre : cammino){
//    		   txtResult.appendText(cre+ " \n");
//    	}}

    }

    @FXML
    void initialize() throws SQLException {
        assert combo1 != null : "fx:id=\"combo1\" was not injected: check your FXML file 'Sample.fxml'.";
        assert combo2 != null : "fx:id=\"combo2\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCerca != null : "fx:id=\"btnCerca\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        combo1.getItems().addAll(model.getAutori());
        combo2.getItems().addAll(model.getAutori());
    }
}
