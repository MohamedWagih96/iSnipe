package isnipe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    
      JLabel Bckgd=new JLabel();
      JButton Play = new JButton("Play");
      JButton Guide = new JButton("Quick Guide");
      JButton Exit = new JButton("Exit");
      public static ISnipe s1=new ISnipe();

    public MainMenu()  {
     
    setSize(900,450);
    setLocationRelativeTo(null);//To center the frame on any pc
    setTitle("Main Menu");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    Container c =getContentPane();
    c.setLayout(null);
        
    //Background
    Bckgd.setIcon(new ImageIcon("Menu.jpg"));
    
    //Buttons
    Play.setBounds(600,50 ,100,50);
    Play.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent evt)
        {
         hide();
         s1=new ISnipe();
         s1.setVisible(true);
         
         
        }});
    
        c.add(Play);
    
    
    
    Guide.setBounds(600,150 ,105,50);
    Guide.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent evt)
        {
            JLabel s=new JLabel();
            JDialog jd=new JDialog();
            jd.setVisible(true);
            jd.setSize(1100,600);
            jd.setLocationRelativeTo(null);
            jd.setLayout(null);
            s.setIcon(new ImageIcon("Guide.jpg"));
            s.setBounds(0,0,1100,600);
            jd.add(s);
        } }  );
    
        c.add(Guide);
    
 
    Exit.setBounds(600,250 ,100,50);
    Exit.addActionListener(new ActionListener()
    
    {
        public void actionPerformed(ActionEvent evt)
        {
        System.exit(0);
        
        }});
        c.add(Exit);
    
     
    Bckgd.setBounds(0,0,900,450);
    c.add(Bckgd);
        
    }
    
}