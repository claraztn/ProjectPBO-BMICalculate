package id.bmicalculator;

import id.bmicalculator.controller.BMIController;
import id.bmicalculator.model.BMIModel;
import id.bmicalculator.view.BMIView;

public class Main {
    public static void main(String[] args) {
        BMIModel model = new BMIModel();
        BMIView view = new BMIView(null);  // Inisialisasi tanpa controller
        BMIController controller = new BMIController(model, view);
        view.setController(controller);    // Set controller pada view
        view.setVisible(true);
    }
}





