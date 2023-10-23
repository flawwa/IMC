package IMCCalcultator;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox vbox1 = new VBox();
		HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
		TextField kgText = new TextField();
        TextField cmText = new TextField();
        TextField resultadoText = new TextField();        
        
        vbox1.getChildren().addAll(hbox1, hbox2, hbox3, resultadoText);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(10);
        
        hbox1.getChildren().addAll(new Label("Peso: "), kgText, new Label("kg"));
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setSpacing(10);
        hbox2.getChildren().addAll(new Label("Altura: "), cmText, new Label("cm"));
        hbox2.setAlignment(Pos.CENTER);
        hbox2.setSpacing(10);
        
        //BINDINGS
        IMCLogic resultado = new IMCLogic();

        kgText.textProperty().bindBidirectional(resultado.kgProperty(), new NumberStringConverter());
        cmText.textProperty().bindBidirectional(resultado.cmProperty(), new NumberStringConverter());

        resultado.resultadoProperty().addListener((o, ov, nv) -> {
            double imc = resultado.resultadoProperty().get();
            System.out.println("Resultado del IMC = " + imc);
        });

        resultadoText.textProperty().bind(Bindings.createStringBinding(() -> {
            double imc = resultado.resultadoProperty().get();
            
            if (imc < 18.5) {
                return "Bajo peso";
            } else if (imc < 24.9) {
                return "Normal";
            } else if (imc < 29.9) {
                return "Sobrepeso";
            } else {
                return "Obeso";
            }
        }, resultado.resultadoProperty()));

        //RESULTADO
        Scene scene = new Scene(vbox1, 600, 400); // Crear una escena con el contenido deseado
        primaryStage.setScene(scene); // Configurar la escena en la ventana principal
        primaryStage.setTitle("IMC.fx");
        primaryStage.show(); // Mostrar la ventana
        
	}
}
