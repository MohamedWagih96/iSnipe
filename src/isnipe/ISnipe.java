package isnipe;
import java.awt.*;
import javax.swing.*;

public class ISnipe extends JFrame {
    public ISnipe ()
    {
    Container c = getContentPane();
    setSize(1700,1000);
    setLocationRelativeTo(null);
    setTitle("iSnipe Ver.1");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    iSnipePanel sp= new iSnipePanel();
    c.add(sp);
    }
    
    public static void main(String[] args) {
      
      MainMenu m = new MainMenu();
      m.setVisible(true);
    }
    
}
