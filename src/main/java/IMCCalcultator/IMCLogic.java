package IMCCalcultator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class IMCLogic {
    private DoubleProperty kg = new SimpleDoubleProperty();
    private DoubleProperty cm = new SimpleDoubleProperty();
    private DoubleProperty resultado = new SimpleDoubleProperty();

    public IMCLogic() {
        kg.addListener((o, ov, nv) -> calcularIMC());
        cm.addListener((o, ov, nv) -> calcularIMC());
    }

    public DoubleProperty kgProperty() {
        return kg;
    }

    public DoubleProperty cmProperty() {
        return cm;
    }

    public DoubleProperty resultadoProperty() {
        return resultado;
    }

    public void calcularIMC() {
        double kgValue = kg.get();
        double cmValue = cm.get() / 100; // Convertir cm a metros
        if (kgValue > 0 && cmValue > 0) {
            double imc = kgValue / (cmValue * cmValue);
            resultado.set(imc);
        } else {
            resultado.set(0.0); // O manejar un valor predeterminado si los datos son incorrectos
        }
    }
}


