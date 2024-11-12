package id.bmicalculator.controller;

import id.bmicalculator.model.BMIModel;
import id.bmicalculator.view.BMIView;

public class BMIController {
    private BMIModel model;
    private BMIView view;

    public BMIController(BMIModel model, BMIView view) {
        this.model = model;
        this.view = view;
    }

    public void calculateBMI() {
        double height = view.getInputHeight();
        double weight = view.getInputWeight();
        String gender = view.getGender();
        if (height != -1 && weight != -1 && gender != null) {
            double bmi = model.calculateBMI(height, weight);
            String category = model.getBMICategory(bmi);
            view.showBMIResult(bmi, category, gender);
        }
    }
}



