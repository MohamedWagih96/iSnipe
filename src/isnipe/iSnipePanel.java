package isnipe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class iSnipePanel extends JPanel {
    private Image Bkgd;
    private JLabel targetImage=new JLabel();
    private JLabel LvlUpImage=new JLabel();
    private JLabel Score =new JLabel();
    private JLabel Level =new JLabel();
    private JLabel Counter =new JLabel();
    private String Jet1="j1.png";
    private String Jet2="lvl4h.png";
    private String Helicopter1="h2.png";
    private String Helicopter2="h3.png";
    private String Parachute="parachute11.png";
    private String Exp="boom.gif";
    private String LevelUp="levelUP.png";
    private String Transparent="Hide.png";
    private int Snipes=0;
    private int Lvl=1;
    private Random rand=new Random();
    private int CountDown;
    private String Name;
    protected int TotalScore;
    public Font f = new Font("Battlefield Rock",Font.PLAIN,30);
    
  
    public iSnipePanel()
    {
        
        BackgroundSoundEffects();
     
        //setTarget:
         setLayout(null);
         targetImage.setIcon(new ImageIcon(Helicopter1));
         targetImage.setBounds(0,0,175,63);
         add(LvlUpImage);
         add(targetImage);


        //setCursor :
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
        new ImageIcon("Xhair(1).png").getImage(),
        new Point(0,0),"XHair"));

        //setScore :
        Score.setFont(f);
        Score.setText("Snipes:" + Integer.toString(Snipes));
        Score.setBounds(600,10,250,50);
        add(Score);

        //setLevel :
        Level.setFont(f);
        Level.setText("Level:" + Integer.toString(Lvl));
        Level.setBounds(1000,10,200,50);
        add(Level);


        //Initial movement (Move Helicopter first) 
        movingHelicopter1.start();


        //Sniping mechanism :
        targetImage.addMouseListener(new MouseAdapter(){
        public void mouseClicked (MouseEvent e)
            {
            int mX=e.getX();
            int mY=e.getY();
            int tX=targetImage.getX();
            int tY=targetImage.getX();
            boolean state=false;
                
            if(mX<=tX && (tX+150)>=mX && mY<=tY && (tY+81)>=mY)
            {  
            state=true;
            SniperShot();
            Explode();
            }
            
            if(state)
            {
                
            targetImage.setIcon(new ImageIcon(Exp));
       
              //After 130 mseconds hide the explosion
                      new java.util.Timer().schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        
                        if(targetImage.getIcon().equals("Parachute"))
                  {
                  targetImage.setIcon(new ImageIcon(Transparent));
                  targetImage.setLocation(0, 300);
                  }
                  targetImage.setIcon(new ImageIcon(Transparent));
                  targetImage.setLocation(1500, 0);}}, 130);
                  Snipes++;
                  Score.setText("Snipes:" + Integer.toString(Snipes));
              
              switch(Snipes)
            {
                
            case 5 :      
                 
                repaint();
                LevelUp();           
                Level.setText("Level:" + "2");
                targetImage.setIcon(new ImageIcon(Helicopter2));
                movingHelicopter1.stop();
                targetImage.hide();
                LevelUpMsg();
                new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                targetImage.show();
                movingHelicopter2.start();
                }}, 2000);
                break;
 
            case 10 :
                repaint();
                LevelUp();
                Level.setText("Level:" + "3");
                targetImage.setIcon(new ImageIcon(Jet1));
                movingHelicopter2.stop();
                targetImage.hide();
                LevelUpMsg();
                new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                targetImage.show();
                movingJet1.start();
                }}, 2000);
                break;
                
            case 15 :
               
                repaint();
                LevelUp();
                Level.setText("Level:" + "4");
                targetImage.setIcon(new ImageIcon(Jet2));
                movingJet1.stop();
                targetImage.hide();
                LevelUpMsg();
                 new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                targetImage.show();
                movingJet2.start();
                }}, 2000);
                break;
   
            case 20 :
                 
                repaint();
                LevelUp();
                Level.setText("Level:" + "5");
                CountDown=30;
                Counter.setText("Seconds Left=30");
                Counter.setFont(f);
                Counter.setBounds(10,10,450,50);
                add(Counter);
                Count(); 
                movingJet2.stop();
                targetImage.hide();
                LevelUpMsg();
               
               new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                targetImage.show();
                movingParachute.start();
               }}, 1000);
              
               break;
                 
              } } }});
    }
    
            int targetImageX , targetImageY;
            int randomVertical=80 , randomHorizontal=0;

            Thread movingHelicopter1 = new Thread (new Runnable() {
                @Override
                public void run() {
              for(int i=1; ;i++)
            {
                try {               
                    movingHelicopter1.sleep(5); 
                } catch (InterruptedException ex) {}
                    
                    if(targetImage.getX()>1700) 
                    {    
                    randomVertical=rand.nextInt((200 - 70) + 1) + 1; 
                    targetImage.setLocation(0,randomVertical); 
                    targetImage.setIcon(new ImageIcon(Helicopter1));  
                    }
                    //Moving Helicopter1 2 pixels cont.
                    targetImageX=targetImage.getX()+2;
                    targetImage.setLocation(targetImageX,randomVertical);
            }}},"Thread A");
      
            Thread movingHelicopter2 = new Thread (new Runnable() {
                @Override
                   public void run() {
            
                           for(int i=1; ;i++)
            {
                 try {
                    movingHelicopter2.sleep(4);
                    
                } catch (InterruptedException ex) {}
                    
                    if(targetImage.getX()>1700)
                    {
                    randomVertical=rand.nextInt((200 - 50) + 1) + 1; 
                    targetImage.setLocation(0,randomVertical); 
                    targetImage.setIcon(new ImageIcon(Helicopter2));
                    }
               
                    targetImageX=targetImage.getX()+3;
                    targetImage.setLocation(targetImageX,randomVertical);
                 }}},"Thread B");
    
              Thread movingJet1 = new Thread (new Runnable() {
              @Override
                    public void run() {
                       for(int i=1; ;i++)
               {
                  try {
                    movingJet1.sleep(3);
                    
                } catch (InterruptedException ex) {}
                    
                    if(targetImage.getX()>1700)
                    {
                    randomVertical=rand.nextInt((200 - 50) + 1) + 1;
                    targetImage.setLocation(0,randomVertical);
                    targetImage.setIcon(new ImageIcon(Jet2));
                    }
                    targetImageX=targetImage.getX()+3;
                    targetImage.setLocation(targetImageX,randomVertical);
              } }},"Thread C");
    
              Thread movingJet2 = new Thread (new Runnable() {
              @Override
                   public void run() {
                           for(int i=1; ;i++)
            { try {
                    movingJet2.sleep(2);
                    
                } catch (InterruptedException ex) {}
                    
                    if(targetImage.getX()>1700)
                    {
                    randomVertical=rand.nextInt((200 - 50) + 1) + 1; 
                    targetImage.setLocation(0,randomVertical); 
                    targetImage.setIcon(new ImageIcon(Jet1));
                    }
                    targetImageX=targetImage.getX()+3;
                    targetImage.setLocation(targetImageX,randomVertical);
              } }},"Thread D");
    
            Thread movingParachute = new Thread (new Runnable() {
            @Override
                   public void run() {
            
                       for(int i=1; ;i++)
            {
                try {
                    movingParachute.sleep(6);
                    
                } catch (InterruptedException ex) {}
                    
                    if(targetImage.getY()>500)
                    {
                    targetImage.setIcon(new ImageIcon(Parachute));
                    randomHorizontal=rand.nextInt((500 - 1) + 1) + 1; 
                    targetImage.setLocation(randomHorizontal,1);                
                    }            
                    targetImageY=targetImage.getY()+1;
                    targetImage.setLocation(randomHorizontal,targetImageY);
             }}},"Thread E");
    
    
            public void Count(){
                       
       new java.util.Timer().schedule(new java.util.TimerTask() {
                @Override
                public void run() {
                if(CountDown!=-1)
                { 
                if(CountDown==11)
                {CDown();}
                 int c=CountDown--;
                 Counter.setText("Seconds Left:"+Integer.toString(c));
                 Counter.setFont(f);
                 Count();
                }
                else
                {
                MainMenu.s1.dispose();    
                End();
                Save();
                }}}, 1000);
                       
            }
      
            public void Save()
    {
            Name=JOptionPane.showInputDialog ( "Enter your name soldier!" );
            TotalScore=Snipes;
            Player p =new Player(TotalScore,Name);
            HallOfFame hof=new HallOfFame();
            hof.setVisible(true);            
    }
       
            public void LevelUpMsg()
       {
            LvlUpImage.setIcon(new ImageIcon(LevelUp));
            LvlUpImage.setBounds(570,150,500,391);
            new java.util.Timer().schedule( new java.util.TimerTask() {
            @Override
            public void run() {
            LvlUpImage.setIcon(new ImageIcon(Transparent));
            }},2000);
        }
    
            @Override
            public void paintComponent(Graphics g)
    {
        if(Snipes<5)
        { 
            Bkgd=new javax.swing.ImageIcon(getClass().getResource("BG2.jpg")).getImage();
            super.paintComponent(g);
            g.drawImage(Bkgd, 0, 0, null);
        }
        else if(Snipes>=5 && Snipes<10)
        {
            Bkgd=new javax.swing.ImageIcon(getClass().getResource("BG3.jpg")).getImage();
            super.paintComponent(g);
            g.drawImage(Bkgd, 0, 0, null);      
        }
        
        else if(Snipes>=10 && Snipes<15)
        {
            Bkgd=new javax.swing.ImageIcon(getClass().getResource("BG4.jpg")).getImage();
            super.paintComponent(g);
            g.drawImage(Bkgd, 0, 0, null);
        }
        
        else if(Snipes>=15 && Snipes<20)
        {
            Bkgd=new javax.swing.ImageIcon(getClass().getResource("BG5.jpg")).getImage();
            super.paintComponent(g);
            g.drawImage(Bkgd, 0, 0, null);
        }

        else if(Snipes>=20 || Snipes>=25)
        {
            Bkgd=new javax.swing.ImageIcon(getClass().getResource("BG6.jpg")).getImage();
            super.paintComponent(g);
            g.drawImage(Bkgd, 0, 0, null);
        }  
    }

        public static void BackgroundSoundEffects()
    {     
          try { 
          File file = new File("BGS" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); 
          clip.loop(Clip.LOOP_CONTINUOUSLY);} 
          catch (Exception e) {}
    } 

        public static void LevelUp()
    { 
          try {    
          File file = new File("MissionAc" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); } 
          catch (Exception e) {}
    } 

        public static void GameOver()
    { 
          try {           
          File file = new File("GameOver" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); } 
          catch (Exception e) {}
    } 

        public static void SniperShot()
    { 
          try {           
          File file = new File("SniperShot" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); } 
          catch (Exception e) {}
    } 

        public static void Explode()
    { 
          try {  
          File file = new File("Explode" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); } 
          catch (Exception e) {}
    }    

        public static void CDown()
    { 
          try { 
          File file = new File("Count" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); } 
          catch (Exception e) {}
    }     

        public static void End()
    { 
          try {           
          File file = new File("End" + ".wav"); 
          Clip clip = AudioSystem.getClip(); 
          clip.open(AudioSystem.getAudioInputStream(file)); 
          clip.start(); } 
          catch (Exception e) {}
    }        

    }