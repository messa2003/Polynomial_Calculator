package ui;

import operations.Operatii;
import polinom.Polinom;

import javax.swing.*;
import java.awt.*;

public class CalculatorInterfata {
    private final JTextField fieldPolinom1 = new JTextField(20);
    private final JTextField fieldPolinom2 = new JTextField(20);
    private final JTextField fieldRezultat = new JTextField(20);

    public CalculatorInterfata() {
        JPanel c = new JPanel();
        c.setLayout(new GridLayout(7, 2, 10, 10)); // 7 r, 2 col, cu spațiu de 10 pixeli

        fieldRezultat.setEditable(false);
        JButton adunareButton = new JButton("Adunare (+)");
        JButton scadereButton = new JButton("Scadere (-)");
        JButton inmultireButton = new JButton("Inmultire (*)");
        JButton impartireButton = new JButton("Impartire (/)");
        JButton derivareButton = new JButton("Derivare (dx/dt)");
        JButton integrareButton = new JButton("Integrare (∫)");

        JLabel labelPolinom1 = new JLabel("Primul polinom: ");
        c.add(labelPolinom1);
        c.add(fieldPolinom1);
        JLabel labelPolinom2 = new JLabel("Al doilea polinom: ");
        c.add(labelPolinom2);
        c.add(fieldPolinom2);
        JLabel labelRezultat = new JLabel("Rezultat: ");
        c.add(labelRezultat);
        c.add(fieldRezultat);

        c.add(adunareButton);
        c.add(scadereButton);
        c.add(inmultireButton);
        c.add(impartireButton);
        c.add(derivareButton);
        c.add(integrareButton);

        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(c);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        adunareButton.addActionListener(e -> {
            if(!fieldPolinom1.getText().isEmpty() && !fieldPolinom2.getText().isEmpty()){
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try{
                    p1.citirePolinom(fieldPolinom1.getText());
                    p2.citirePolinom(fieldPolinom2.getText());
                    Polinom p3 = Operatii.adunare(p1,p2);
                    fieldRezultat.setText(p3.prettyPrint());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Polinom introdus greșit", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        scadereButton.addActionListener(e -> {
            if(!fieldPolinom1.getText().isEmpty() && !fieldPolinom2.getText().isEmpty()){
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try{
                    p1.citirePolinom(fieldPolinom1.getText());
                    p2.citirePolinom(fieldPolinom2.getText());
                    Polinom p3 = Operatii.scadere(p1,p2);

                    fieldRezultat.setText(p3.prettyPrint());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Polinom introdus greșit", "Eroare", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        inmultireButton.addActionListener(e -> {
            if(!fieldPolinom1.getText().isEmpty() && !fieldPolinom2.getText().isEmpty()){
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try{
                    p1.citirePolinom(fieldPolinom1.getText());
                    p2.citirePolinom(fieldPolinom2.getText());
                    Polinom p3 = Operatii.inmultire(p1,p2);

                    fieldRezultat.setText(p3.prettyPrint());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Polinom introdus greșit", "Eroare", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        derivareButton.addActionListener(e -> {
            if(!fieldPolinom1.getText().isEmpty()){
                Polinom p1 = new Polinom();

                try{
                    p1.citirePolinom(fieldPolinom1.getText());

                    Polinom p3 = Operatii.derivare(p1);


                    fieldRezultat.setText(p3.prettyPrint());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Polinom introdus greșit", "Eroare", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        integrareButton.addActionListener(e -> {
            if(!fieldPolinom1.getText().isEmpty()){
                Polinom p1 = new Polinom();

                try{
                    p1.citirePolinom(fieldPolinom1.getText());

                    Polinom p3 = Operatii.integrare(p1);

                    fieldRezultat.setText(p3.prettyPrint());
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "Polinom introdus greșit", "Eroare", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        impartireButton.addActionListener(e -> {
            if (!fieldPolinom1.getText().isEmpty() && !fieldPolinom2.getText().isEmpty()) {
                Polinom p1 = new Polinom();
                Polinom p2 = new Polinom();
                try {
                    p1.citirePolinom(fieldPolinom1.getText());
                    p2.citirePolinom(fieldPolinom2.getText());
                    fieldRezultat.setText(Operatii.impartire(p1,p2));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Polinom introdus greșit", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorInterfata::new);
    }
}

