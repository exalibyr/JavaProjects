package InterfaceModules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Module {

    Module(JPanel modulePanel){
        JButton calculateButton = new JButton("Calculate");
        modulePanel.add(calculateButton);
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    calculate();
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Input error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

     protected abstract void calculate();
}
