package CommonInterface;

import InterfaceModules.*;
import InterfaceModules.Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

    private JPanel modulePanel;
    private Module module;

    public GUI(){
        super("Encryption algorithms");

        final JPanel panel = initPanel();
        final JComboBox<String> comboBox = initComboBox(panel);
        setUniqueComboBoxModel(comboBox);
        JButton chooseButton = initChooseButton(panel);

        getContentPane().add(BorderLayout.WEST, panel);
        initDefaultModule(comboBox);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        locateWindow();
        pack();
        setVisible(true);

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String choice = (String)comboBox.getSelectedItem();
                    switch (choice){
                        case "Exponentiation":{
                            modulePanel.removeAll();
                            module = new ExponentiationModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "Euclid algorithm":{
                            modulePanel.removeAll();
                            module = new EuclidModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "XOR algorithm":{
                            modulePanel.removeAll();
                            module = new SimpleXORModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "Elgamal's algorithm":{
                            modulePanel.removeAll();
                            module = new ElgamalModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "Diffie Hellman":{
                            modulePanel.removeAll();
                            module = new DiffieHellmanModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "Shamir's algorithm":{
                            modulePanel.removeAll();
                            module = new ShamirModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "RSA algorithm":{
                            modulePanel.removeAll();
                            module = new RSAModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        case "Fermat's factorization":{
                            modulePanel.removeAll();
                            module = new FermatFactorizationModule(modulePanel);
                            pack();
                            repaint();
                            break;
                        }
                        default: break;
                    }
                }
                catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private JPanel initPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.YELLOW);
        panel.setPreferredSize(new Dimension(200, 200));
        return panel;
    }

    private JComboBox<String> initComboBox(JPanel panel){
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setAutoscrolls(true);
        comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        comboBox.setAlignmentY(Component.TOP_ALIGNMENT);
        comboBox.setMaximumRowCount(10);
        panel.add(comboBox);
        return comboBox;
    }

    private void setUniqueComboBoxModel(JComboBox<String> comboBox){
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        comboBoxModel.addElement("Exponentiation");
        comboBoxModel.addElement("Euclid algorithm");
        comboBoxModel.addElement("XOR algorithm");
        comboBoxModel.addElement("Elgamal's algorithm");
        comboBoxModel.addElement("Diffie Hellman");
        comboBoxModel.addElement("Shamir's algorithm");
        comboBoxModel.addElement("RSA algorithm");
        comboBoxModel.addElement("Fermat's factorization");
        comboBox.setModel(comboBoxModel);
    }

    private JButton initChooseButton(JPanel panel){
        JButton chooseButton = new JButton("Select algorithm");
        panel.add(chooseButton);
        return chooseButton;
    }

    private void locateWindow(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(new Dimension(700, 200));
        Point point = new Point();
        point.x = (screenSize.width - windowSize.width) / 2;
        point.y = (screenSize.height - windowSize.height) / 2 - 50;
        setLocation(point);
        setResizable(false);
        setPreferredSize(windowSize);
    }


    private void initDefaultModule(JComboBox<String> comboBox){
        modulePanel = new JPanel();
        modulePanel.setBackground(new Color(0, 200, 0, 255));
        getContentPane().add(modulePanel);
        try{
            String choice = (String)comboBox.getSelectedItem();
            switch (choice){
                case "Exponentiation":{
                    module = new ExponentiationModule(modulePanel);
                    break;
                }
                case "Euclid algorithm":{
                    module = new EuclidModule(modulePanel);
                    break;
                }
                case "XOR algorithm":{
                    module = new SimpleXORModule(modulePanel);
                    break;
                }
                case "Elgamal's algorithm":{
                    module = new ElgamalModule(modulePanel);
                    break;
                }
                case "Diffie Hellman":{
                    module = new DiffieHellmanModule(modulePanel);
                    break;
                }
                case "Shamir's algorithm":{
                    module = new ShamirModule(modulePanel);
                    break;
                }
                case "RSA algorithm":{
                    module = new RSAModule(modulePanel);
                    break;
                }
                case "Fermat's factorization":{
                    module = new FermatFactorizationModule(modulePanel);
                    break;
                }
                default: break;
            }
        }
        catch (NullPointerException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
