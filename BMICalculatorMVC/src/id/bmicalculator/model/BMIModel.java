package id.bmicalculator.model;

public class BMIModel {
    public double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }

    public String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Kurus";
        } else if (bmi < 24.9) {
            return "Normal";
        } else if (bmi < 29.9) {
            return "Kelebihan Berat Badan";
        } else {
            return "Obesitas";
        }
    }
}


