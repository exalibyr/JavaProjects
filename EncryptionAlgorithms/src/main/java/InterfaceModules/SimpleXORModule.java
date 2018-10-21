package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;

public class SimpleXORModule extends Module {

    private JTextField message;
    private JTextField key;
    private JLabel encodedMessage;
    private JLabel decodedMessage;

    public SimpleXORModule(JPanel panel){
        super(panel);

        message = new JTextField("message");
        message.setPreferredSize(new Dimension(100, 25));
        panel.add(message);

        key = new JTextField("key");
        key.setPreferredSize(new Dimension(100, 25));
        panel.add(key);

        encodedMessage = new JLabel("encoded message: ");
        encodedMessage.setPreferredSize(new Dimension(300, 25));
        panel.add(encodedMessage);

        decodedMessage = new JLabel("decoded message: ");
        decodedMessage.setPreferredSize(new Dimension(300, 25));
        panel.add(decodedMessage);
    }

    protected void calculate(){
        String result[] = Logic.xor(message.getText(), key.getText());
        encodedMessage.setText("encoded message: " + result[0]);
        decodedMessage.setText("decoded message: " + result[1]);
    }
}
