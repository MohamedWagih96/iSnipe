package isnipe;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

 
        class Player
        {
        int Score;
        String Name; 
        public static List<Player> HList = new ArrayList<Player>();


        public Player(int s , String n)
        {
            this.Score=s;
            this.Name=n;
            writeToFile(s, n);

        }
        public Player()
        {}


        public void writeToFile(int s, String n) //Write the player's info in "Container" file
        {
            try{
                    FileWriter fw = new FileWriter("Container.txt", true);
                    PrintWriter pw =new PrintWriter(fw);

                    pw.println(s+":"+n);
                    pw.close();
                    sortFile();

                }
            catch(Exception e){}
        }


        public void sortFile() //Sort the unsorted list then save it to a new file
        {
    
    try {
            File f = new File("Container.txt");
            Scanner sc = new Scanner(f);
            
                while(sc.hasNextLine()){
                int c=0;
                String line = sc.nextLine();
                String[] details = line.split(":");
                String a = details[0];
                String b= details[1];
                
                Player m= new Player();
                m.Score=Integer.parseInt(a);
                m.Name=b;
              
                HList.add(m);
                c++;
  
            }
                sc.close();
    }
        
        
            catch (FileNotFoundException e) {         
            e.printStackTrace();
        }
              for(int i=0 ; i<HList.size() ;i++)
            {
             for(int j=i+1 ; j<HList.size();j++)
             { 
                 if(HList.get(i).Score<HList.get(j).Score)
                 { 
                     int temp=HList.get(i).Score;
                     HList.get(i).Score=HList.get(j).Score;
                     HList.get(j).Score=temp;
                     
                     String tempo=HList.get(i).Name;
                     HList.get(i).Name=HList.get(j).Name;
                     HList.get(j).Name=tempo;
                 
                 }
                 else {continue;}
            }}
              
    
    try{
            FileWriter fwp = new FileWriter("FinalList.txt",false);
            PrintWriter pwp =new PrintWriter(fwp);
       
            for(int j=0 ; j<HList.size();j++)
             { pwp.println(HList.get(j).Name+ " "+ HList.get(j).Score);}
            
             pwp.close();}
    

        catch(Exception e){}
         }}

        public class HallOfFame extends JFrame {

            JLabel[] lbls=new JLabel[10];
            JLabel[] lbls2=new JLabel[10];
            JLabel Bckgd=new JLabel();
            JButton Back = new JButton("Menu");
            JButton Exit = new JButton("Exit");
            JLabel Gold =new JLabel();
            JLabel Silver =new JLabel();
            JLabel Bronze =new JLabel();
            JLabel HOF = new JLabel();

            public HallOfFame ()
            {

            setSize(1500,800);
            setLocationRelativeTo(null);
            setTitle("Hall Of Fame");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            Container ct =getContentPane();
            ct.setLayout(null);
            Bckgd.setBounds(0,0,1500,800);

            //Back button
            Back.setBounds(850,700,100,60);
            Back.addActionListener(new ActionListener()

                    {
                    public void actionPerformed(ActionEvent e)
                    {
                        hide();
                        MainMenu.s1=null;

                        MainMenu m=new MainMenu();
                        m.setVisible(true);

                    }});
               ct.add(Back);


            //Exit button
            Exit.setBounds(1050,700,100,60);
            Exit.addActionListener(new ActionListener()

                {public void actionPerformed(ActionEvent e)
                {System.exit(0);}});

                ct.add(Exit);

            int y=50;
            for(int i=0 ; i<10;i++)
            { 
                lbls[i]=new JLabel(" ");
                lbls[i].setBounds(850,y,200,60);
                lbls[i].setOpaque(true);
                lbls[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));

            ct.add(lbls[i]);
            y+=60;
            }
            
            //Adding awards and Hall of fame icon
            
                HOF.setBounds(10,10,208,92);
                HOF.setIcon(new ImageIcon("hoficon.png"));
                add(HOF);
            
                Gold.setBounds(790,50,65,64);
                Gold.setIcon(new ImageIcon("Gold.png"));
                add(Gold);
                
                Silver.setBounds(790,110,65,64);
                Silver.setIcon(new ImageIcon("Silver.png"));
                add(Silver);
                
                Bronze.setBounds(790,170,65,64);
                Bronze.setIcon(new ImageIcon("Bronze.png"));
                add(Bronze);
          
            
            for(int j=0 ; j<Player.HList.size() && j<10;j++)
            {lbls[j].setText("   "+Player.HList.get(j).Name);
            lbls[j].setOpaque(true);
            }

            int y2=50;
            for(int i=0 ; i<10;i++)
            { 
                lbls2[i]=new JLabel(" ");
                lbls2[i].setBounds(1050,y2,200,60);
                lbls2[i].setOpaque(true);
                lbls2[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));

            ct.add(lbls2[i]);
            y2+=60;

            }

            for(int k=0 ; k<Player.HList.size()&& k<10;k++)
            {lbls2[k].setText("                                 "+Integer.toString(Player.HList.get(k).Score));
            lbls2[k].setOpaque(true);

            }

             Bckgd.setIcon(new ImageIcon("HOFBG.jpg"));
             ct.add(Bckgd);

            }


            }


