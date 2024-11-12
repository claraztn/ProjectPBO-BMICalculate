package id.bmicalculator.view;

import javax.swing.*;
import java.awt.*;
import id.bmicalculator.controller.BMIController;

public class BMIView extends JFrame {
    private JTextField heightField;
    private JTextField weightField;
    private JLabel resultLabel;
    private JLabel categoryLabel;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup genderGroup;
    private BMIController controller;
    private JLabel imageLabel;
    private JPanel mainPanel;

    public BMIView(BMIController controller) {
        this.controller = controller;
        initComponents();
    }

    public void setController(BMIController controller) {
        this.controller = controller;
    }

    private void initComponents() {
        setTitle("Body Mass Index");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400, 500)); // Menetapkan ukuran minimum
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(173, 216, 230)); // Latar belakang biru muda
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Body Mass Index");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 139)); // Warna teks biru dongker
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, c);

        JLabel heightLabel = new JLabel("Tinggi Badan (cm):");
        heightLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.EAST;
        mainPanel.add(heightLabel, c);

        heightField = new JTextField(10);
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(heightField, c);

        JLabel weightLabel = new JLabel("Berat Badan (kg):");
        weightLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        mainPanel.add(weightLabel, c);

        weightField = new JTextField(10);
        c.gridx = 1;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(weightField, c);

        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        genderLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.EAST;
        mainPanel.add(genderLabel, c);

        maleButton = new JRadioButton("Pria");
        femaleButton = new JRadioButton("Wanita");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JPanel genderPanel = new JPanel();
        genderPanel.setBackground(new Color(173, 216, 230)); // Latar belakang biru muda
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        c.gridx = 1;
        c.gridy = 3;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(genderPanel, c);

        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        JButton exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints bc = new GridBagConstraints();
        bc.insets = new Insets(5, 5, 5, 5);

        bc.gridx = 0;
        bc.gridy = 0;
        buttonPanel.add(calculateButton, bc);

        bc.gridx = 1;
        buttonPanel.add(clearButton, bc);

        bc.gridx = 2;
        buttonPanel.add(exitButton, bc);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, c);

        categoryLabel = new JLabel("Kategori: ");
        categoryLabel.setForeground(Color.BLACK);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(categoryLabel, c);

        // Gambar dengan ukuran tetap dan menyesuaikan ukuran tampilan layar
        ImageIcon originalIcon = new ImageIcon("bmi.jpeg"); // Ganti path sesuai dengan lokasi gambar Anda
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(300, 100, Image.SCALE_SMOOTH); // Ukuran tetap
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledIcon);
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(imageLabel, c);

        calculateButton.addActionListener(e -> {
            if (controller != null) {
                controller.calculateBMI();
            }
        });

        clearButton.addActionListener(e -> clearFields());

        exitButton.addActionListener(e -> System.exit(0));

        add(mainPanel, BorderLayout.CENTER);
    }

    public double getInputHeight() {
        try {
            double heightInCm = Double.parseDouble(heightField.getText());
            return heightInCm / 100; // Konversi dari cm ke meter
        } catch (NumberFormatException e) {
            showError("Input tinggi tidak valid");
            return -1;
        }
    }

    public double getInputWeight() {
        try {
            return Double.parseDouble(weightField.getText());
        } catch (NumberFormatException e) {
            showError("Input berat tidak valid");
            return -1;
        }
    }

    public String getGender() {
        if (maleButton.isSelected()) {
            return "Pria";
        } else if (femaleButton.isSelected()) {
            return "Wanita";
        } else {
            showError("Silakan pilih jenis kelamin");
            return null;
        }
    }

    public void showBMIResult(double bmi, String category, String gender) {
        String message = String.format("BMI anda = %.2f\nAnda termasuk %s dengan berat badan %s", bmi, gender, category);
        JOptionPane.showMessageDialog(this, message, "Hasil BMI", JOptionPane.INFORMATION_MESSAGE);
    }

    public void clearFields() {
        heightField.setText("");
        weightField.setText("");
        genderGroup.clearSelection();
        resultLabel.setText("BMI Anda adalah: ");
        categoryLabel.setText("Kategori: ");
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}






