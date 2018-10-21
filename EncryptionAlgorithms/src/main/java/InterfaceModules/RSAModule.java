package InterfaceModules;

import Logic.Logic;

import javax.swing.*;
import java.awt.*;


public class RSAModule extends Module {

    private JTextField message;
    private JLabel p;
    private JLabel q;
    private JLabel n;
    private JLabel f;
    private JLabel d;
    private JLabel c;
    private JLabel e;
    private JLabel receivedMessage;

    public RSAModule(JPanel panel){
        super(panel);

        message = new JTextField("message");
        message.setPreferredSize(new Dimension(100, 25));
        panel.add(message);

        p = new JLabel("p=?");
        p.setPreferredSize(new Dimension(100, 25));
        panel.add(p);

        q = new JLabel("q=?");
        q.setPreferredSize(new Dimension(100, 25));
        panel.add(q);

        n = new JLabel("n=?");
        n.setPreferredSize(new Dimension(100, 25));
        panel.add(n);

        f = new JLabel("f=?");
        f.setPreferredSize(new Dimension(100, 25));
        panel.add(f);

        d = new JLabel("d=?");
        d.setPreferredSize(new Dimension(100, 25));
        panel.add(d);

        c = new JLabel("c=?");
        c.setPreferredSize(new Dimension(100, 25));
        panel.add(c);

        e = new JLabel("e=?");
        e.setPreferredSize(new Dimension(100, 25));
        panel.add(e);

        receivedMessage = new JLabel("received message=?");
        receivedMessage.setPreferredSize(new Dimension(300, 25));
        panel.add(receivedMessage);
    }

    protected void calculate(){
        int[] results = Logic.rsa(Integer.parseInt(message.getText()));
        p.setText("P=" + String.valueOf(results[0]));
        q.setText("Q=" + String.valueOf(results[1]));
        n.setText("N=" + String.valueOf(results[2]));
        f.setText("F=" + String.valueOf(results[3]));
        d.setText("d=" + String.valueOf(results[4]));
        c.setText("C=" + String.valueOf(results[5]));
        e.setText("e=" + String.valueOf(results[6]));
        receivedMessage.setText("received message=" + String.valueOf(results[7]));
    }
}
