package proje2;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import proje2.LinkedL.Node1;

public class Proje2 {
    public static void main(String[] args) {
        Scanner l=null;
        Scanner s=null;
        boolean file = true;
        LinkedL l1=new LinkedL();
        try{
            l = new Scanner(new FileInputStream("bilgiler.txt")).useDelimiter(".");
        }catch(FileNotFoundException e){
            file = false;
        }
        ArrayList<String> lines = new ArrayList<>();
        int i=0;
        while(l.hasNext()){
            //text dosyasındaki datalar ile objeleri oluşturur
            lines.add(l.nextLine());
            s= new Scanner(lines.get(i)).useDelimiter(",");
            i++;
            String tempName = s.next();
            String tempHeader= s.next().substring(1);
            int tempRd= Integer.parseInt(s.next().substring(1));
            int tempPrice=Integer.parseInt(s.next().substring(1));
            ArrayList<String> tempL = new ArrayList<>();
            while(s.hasNext()){
                String temp = s.next().substring(1);
                tempL.add(temp);
            }//bilgiler.txt de satır sonundaki "." karakterini yok ederek listeye ekleme.
            String tempSon = tempL.get(tempL.size()-1);
            tempSon=tempSon.substring(0,tempSon.length()-1);
            tempL.set(tempL.size()-1, tempSon);
            DVD tempD = new DVD(tempName,tempHeader,tempRd,tempPrice,tempL);
            l1.insertInOrder(tempD);
        }
        //Ana Menü
        JFrame frame=new JFrame("DVD Programı");
        frame.setLocation(400,150);
        frame.setSize(350,450);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon backIcon = new ImageIcon("gericon.png");
        Image image = backIcon.getImage();
        image = image.getScaledInstance(image.getWidth(null)/6, image.getHeight(null)/6, Image.SCALE_SMOOTH);
        backIcon.setImage(image);
        JPanel mainpanel=new JPanel();
        mainpanel.setBackground(Color.DARK_GRAY);
        mainpanel.setLayout(null);
        //menüdeki butonların oluşturulması
        JButton listAz=new JButton();
        listAz.setText("DVD'leri Listele (A...Z)");
        listAz.setSize(250,35);
        listAz.setLocation(45,30);
        listAz.setHorizontalAlignment(0);
        Color listAzDef = listAz.getBackground();
        listAz.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                listAz.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                listAz.setBackground(listAzDef);
                super.mouseExited(arg0);
            }
        });
        listAz.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                // A'dan Z'ye butonunun işlevi
                JFrame listFrame = new JFrame("DVD Listele");
                JPanel listPanel = new JPanel();
                listPanel.setBackground(Color.DARK_GRAY);
                listPanel.setLayout(null);
                listFrame.setLocation(200,100);
                listFrame.setSize(800,600);
                listFrame.setResizable(false);
                listFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                JButton backButton=new JButton(backIcon);
                backButton.setSize(39,35);
                backButton.setLocation(700,500);
                backButton.setBackground(Color.darkGray);
                backButton.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        listFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                listPanel.add(backButton);
                JLabel j1=new JLabel("Ad Soyad");
                j1.setForeground(Color.RED);
                j1.setSize(100,30);
                j1.setLocation(50,0);
                listPanel.add(j1);
                Node1 iterator=l1.head;
                int i=50;
                while(iterator!=null){
                    //sanatçı isimleri için text area oluşturma
                    JTextArea temp = new JTextArea();
                    temp.setSize(100,30);
                    temp.setLocation(50,i);
                    i+=50;
                    temp.setText(iterator.dvd.getName());
                    temp.setBackground(Color.DARK_GRAY);
                    temp.setForeground(Color.WHITE);
                    listPanel.add(temp);
                    iterator=iterator.next;
                }
                JLabel j2=new JLabel("Albüm");
                j2.setForeground(Color.RED);
                j2.setSize(100,30);
                j2.setLocation(200,0);
                listPanel.add(j2);
                Node1 iterator2=l1.head;
                int j=50;
                while(iterator2!=null){
                    //albüm isimleri için text area oluşturma
                    JTextArea temp2 = new JTextArea();
                    temp2.setSize(200,30);
                    temp2.setLocation(200,j);
                    j+=50;
                    temp2.setText(iterator2.dvd.getHeader());
                    temp2.setBackground(Color.DARK_GRAY);
                    temp2.setForeground(Color.WHITE);
                    listPanel.add(temp2);
                    iterator2=iterator2.next;
                }
                Node1 iterator3=l1.head;
                int k=50;
                JLabel j3=new JLabel("Çıkış Tarihi");
                j3.setForeground(Color.RED);
                j3.setSize(100,30);
                j3.setLocation(400,0);
                listPanel.add(j3);
                while(iterator3!=null){
                    //albüm çıkış tarihleri için text area oluşturma
                    JTextArea temp3 = new JTextArea();
                    temp3.setSize(40,30);
                    temp3.setLocation(400,k);
                    k+=50;
                    temp3.setText(""+iterator3.dvd.getReleaseDate());
                    temp3.setBackground(Color.DARK_GRAY);
                    temp3.setForeground(Color.WHITE);
                    listPanel.add(temp3);
                    iterator3=iterator3.next;
                }
                Node1 iterator4=l1.head;
                int l=50;
                JLabel j4=new JLabel("Fiyat");
                j4.setForeground(Color.red);
                j4.setSize(100,30);
                j4.setLocation(550,0);
                listPanel.add(j4);
                while(iterator4!=null){
                    //albüm fiyatları için text area oluşturma
                    JTextArea temp4 = new JTextArea();
                    temp4.setSize(40,30);
                    temp4.setLocation(550,l);
                    l+=50;
                    temp4.setText(""+iterator4.dvd.getPrice());
                    temp4.setBackground(Color.DARK_GRAY);
                    temp4.setForeground(Color.WHITE);
                    listPanel.add(temp4);
                    iterator4=iterator4.next;
                }
                Node1 iterator5=l1.head;
                int h=50;
                while(iterator5!=null){
                    //her sanatçı için şarkılarını gösteren buton
                    JButton temp7 = new JButton("Şarkılarını Gör");
                    temp7.setSize(120,20);
                    temp7.setLocation(600,h);
                    listPanel.add(temp7);
                    String sson = iterator5.dvd.getSongs();
                    iterator5=iterator5.next;
                    h+=50;
                    temp7.addActionListener(new ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt){
                            JOptionPane.showMessageDialog(null,sson+" ","Şarkılar",JOptionPane.PLAIN_MESSAGE);
                        }
                    });
                }
                listFrame.setContentPane(listPanel);
                frame.setVisible(false);
                listFrame.setVisible(true);
                }
        });
        mainpanel.add(listAz);
        // ana menüye dvd'leri z..a sıralama butonu
        JButton listZa=new JButton();
        listZa.setText("DVD'leri Listele (Z...A)");
        listZa.setSize(250,35);
        listZa.setLocation(45,80);
        listZa.setHorizontalAlignment(0);
        Color listZaDef = listZa.getBackground();
        listZa.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                listZa.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                listZa.setBackground(listZaDef);
                super.mouseExited(arg0);
            }
        });
        listZa.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                // z ... a butonunun işlevi
                JFrame listFrame = new JFrame("DVD Listele");
                JPanel listPanel = new JPanel();
                listPanel.setBackground(Color.DARK_GRAY);
                listPanel.setLayout(null);
                listFrame.setLocation(200,100);
                listFrame.setSize(800,600);
                listFrame.setResizable(false);
                listFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                JButton backButton=new JButton(backIcon);
                backButton.setSize(39,35);
                backButton.setLocation(700,500);
                backButton.setBackground(Color.darkGray);
                backButton.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        listFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                listPanel.add(backButton);
                JLabel j1=new JLabel("Ad Soyad");
                j1.setForeground(Color.RED);
                j1.setSize(100,30);
                j1.setLocation(50,0);
                listPanel.add(j1);
                Node1 iterator=l1.tail;
                int i=50;
                while(iterator!=null){
                    //sanatçı isimleri için text area oluşturma
                    JTextArea temp = new JTextArea();
                    temp.setSize(100,30);
                    temp.setLocation(50,i);
                    i+=50;
                    temp.setText(iterator.dvd.getName());
                    temp.setBackground(Color.DARK_GRAY);
                    temp.setForeground(Color.WHITE);
                    listPanel.add(temp);
                    iterator=iterator.previous;
                }
                JLabel j2=new JLabel("Albüm");
                j2.setForeground(Color.RED);
                j2.setSize(100,30);
                j2.setLocation(200,0);
                listPanel.add(j2);
                Node1 iterator2=l1.tail;
                int j=50;
                while(iterator2!=null){
                    //albüm isimleri için text area oluşturma
                    JTextArea temp2 = new JTextArea();
                    temp2.setSize(200,30);
                    temp2.setLocation(200,j);
                    j+=50;
                    temp2.setText(iterator2.dvd.getHeader());
                    temp2.setBackground(Color.DARK_GRAY);
                    temp2.setForeground(Color.WHITE);
                    listPanel.add(temp2);
                    iterator2=iterator2.previous;
                }
                Node1 iterator3=l1.tail;
                int k=50;
                JLabel j3=new JLabel("Çıkış Tarihi");
                j3.setForeground(Color.RED);
                j3.setSize(100,30);
                j3.setLocation(400,0);
                listPanel.add(j3);
                while(iterator3!=null){
                    // çıkış tarihleri için text area
                    JTextArea temp3 = new JTextArea();
                    temp3.setSize(40,30);
                    temp3.setLocation(400,k);
                    k+=50;
                    temp3.setText(""+iterator3.dvd.getReleaseDate());
                    temp3.setBackground(Color.DARK_GRAY);
                    temp3.setForeground(Color.WHITE);
                    listPanel.add(temp3);
                    iterator3=iterator3.previous;
                }
                Node1 iterator4=l1.tail;
                int l=50;
                JLabel j4=new JLabel("Fiyat");
                j4.setForeground(Color.red);
                j4.setSize(100,30);
                j4.setLocation(550,0);
                listPanel.add(j4);
                while(iterator4!=null){
                    //dvd fiyatlalrı için text area
                    JTextArea temp4 = new JTextArea();
                    temp4.setSize(40,30);
                    temp4.setLocation(550,l);
                    l+=50;
                    temp4.setText(""+iterator4.dvd.getPrice());
                    temp4.setBackground(Color.DARK_GRAY);
                    temp4.setForeground(Color.WHITE);
                    listPanel.add(temp4);
                    iterator4=iterator4.previous;
                }
                Node1 iterator5=l1.tail;
                int h=50;
                while(iterator5!=null){
                    //şarkıları listeleyen butonları ekler
                    JButton temp7 = new JButton("Şarkılarını Gör");
                    temp7.setSize(120,20);
                    temp7.setLocation(600,h);
                    listPanel.add(temp7);
                    String sson = iterator5.dvd.getSongs();
                    iterator5=iterator5.previous;
                    h+=50;
                    temp7.addActionListener(new ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt){
                            JOptionPane.showMessageDialog(null,sson+" ","Şarkılar",JOptionPane.PLAIN_MESSAGE);
                        }
                    });
                }
                listFrame.setContentPane(listPanel);
                frame.setVisible(false);
                listFrame.setVisible(true);
                }
        });
        mainpanel.add(listZa);
        //ana menüye 2000'den önce çıkan dvd'leri sıralama butonu ekler
        JButton list2000=new JButton();
        list2000.setText("2000'den Önce Çıkan DVD'ler");
        list2000.setSize(250,35);
        list2000.setLocation(45,130);
        list2000.setHorizontalAlignment(0);
        Color list2000Def = list2000.getBackground();
        list2000.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                list2000.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                list2000.setBackground(list2000Def);
                super.mouseExited(arg0);
            }
        });
        list2000.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                // 2000'den öncekileri sırala butonu işlevi
                JFrame listFrame = new JFrame("DVD Listele");
                JPanel listPanel = new JPanel();
                listPanel.setBackground(Color.DARK_GRAY);
                listPanel.setLayout(null);
                listFrame.setLocation(200,100);
                listFrame.setSize(800,600);
                listFrame.setResizable(false);
                listFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                JButton backButton=new JButton(backIcon);
                backButton.setSize(39,35);
                backButton.setLocation(700,500);
                backButton.setBackground(Color.darkGray);
                backButton.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        listFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                listPanel.add(backButton);
                JLabel j1=new JLabel("Ad Soyad");
                j1.setForeground(Color.RED);
                j1.setSize(100,30);
                j1.setLocation(50,0);
                listPanel.add(j1);
                //dvd tarihlerini kontrol ederek text area oluşturma
                Node1 iterator=l1.head;
                int i=50;
                while(iterator!=null){
                    if(iterator.dvd.getReleaseDate()>=2000){
                        iterator=iterator.next;
                        continue;
                    }
                    JTextArea temp = new JTextArea();
                    temp.setSize(100,30);
                    temp.setLocation(50,i);
                    i+=50;
                    temp.setText(iterator.dvd.getName());
                    temp.setBackground(Color.DARK_GRAY);
                    temp.setForeground(Color.WHITE);
                    listPanel.add(temp);
                    iterator=iterator.next;
                }
                JLabel j2=new JLabel("Albüm");
                j2.setForeground(Color.RED);
                j2.setSize(100,30);
                j2.setLocation(200,0);
                listPanel.add(j2);
                //albüm isileri için text area
                Node1 iterator2=l1.head;
                int j=50;
                while(iterator2!=null){
                    if(iterator2.dvd.getReleaseDate()>=2000){
                        iterator2=iterator2.next;
                        continue;
                    }    
                    JTextArea temp2 = new JTextArea();
                    temp2.setSize(200,30);
                    temp2.setLocation(200,j);
                    j+=50;
                    temp2.setText(iterator2.dvd.getHeader());
                    temp2.setBackground(Color.DARK_GRAY);
                    temp2.setForeground(Color.WHITE);
                    listPanel.add(temp2);
                    iterator2=iterator2.next;
                }
                Node1 iterator3=l1.head;
                int k=50;
                JLabel j3=new JLabel("Çıkış Tarihi");
                j3.setForeground(Color.RED);
                j3.setSize(100,30);
                j3.setLocation(400,0);
                listPanel.add(j3);
                //çıkış tarihleri için text area
                while(iterator3!=null){
                    if(iterator3.dvd.getReleaseDate()>=2000){
                        iterator3=iterator3.next;
                        continue;
                    }
                    JTextArea temp3 = new JTextArea();
                    temp3.setSize(40,30);
                    temp3.setLocation(400,k);
                    k+=50;
                    temp3.setText(""+iterator3.dvd.getReleaseDate());
                    temp3.setBackground(Color.DARK_GRAY);
                    temp3.setForeground(Color.WHITE);
                    listPanel.add(temp3);
                    iterator3=iterator3.next;
                }
                Node1 iterator4=l1.head;
                int l=50;
                JLabel j4=new JLabel("Fiyat");
                j4.setForeground(Color.red);
                j4.setSize(100,30);
                j4.setLocation(550,0);
                listPanel.add(j4);
                //dvd fiyatları için text area
                while(iterator4!=null){
                    if(iterator4.dvd.getReleaseDate()>=2000){
                        iterator4=iterator4.next;
                        continue;
                    }
                    JTextArea temp4 = new JTextArea();
                    temp4.setSize(40,30);
                    temp4.setLocation(550,l);
                    l+=50;
                    temp4.setText(""+iterator4.dvd.getPrice());
                    temp4.setBackground(Color.DARK_GRAY);
                    temp4.setForeground(Color.WHITE);
                    listPanel.add(temp4);
                    iterator4=iterator4.next;
                }
                //her sanatçı için şarkıları listeleyen buton ekleme
                Node1 iterator5=l1.head;
                int h=50;
                while(iterator5!=null){
                    if(iterator5.dvd.getReleaseDate()>=2000){
                        iterator5=iterator5.next;
                        continue;
                    }
                    JButton temp7 = new JButton("Şarkılarını Gör");
                    temp7.setSize(120,20);
                    temp7.setLocation(600,h);
                    listPanel.add(temp7);
                    String sson = iterator5.dvd.getSongs();
                    iterator5=iterator5.next;
                    h+=50;
                    temp7.addActionListener(new ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt){
                            JOptionPane.showMessageDialog(null,sson+" ","Şarkılar",JOptionPane.PLAIN_MESSAGE);
                        }
                    });
                }
                listFrame.setContentPane(listPanel);
                frame.setVisible(false);
                listFrame.setVisible(true);
                }
        });
         mainpanel.add(list2000);
        //ana menüye sanatçı adıyla dvd arama butonu ekleme   
        JButton searchbutton=new JButton();
        searchbutton.setText("Sanatçı Adıyla DVD Ara");
        searchbutton.setSize(250,35);
        searchbutton.setLocation(45,180);
        searchbutton.setHorizontalAlignment(0);
        Color searchbuttonDef = searchbutton.getBackground();
        searchbutton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                searchbutton.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                searchbutton.setBackground(searchbuttonDef);
                super.mouseExited(arg0);
            }
        });
        searchbutton.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                //isimle dvd arama butonu işlevi
                String s =JOptionPane.showInputDialog(null,"Ad Soyad","Sanatçı Adına Göre Arama",JOptionPane.QUESTION_MESSAGE);
                if(s==null){
                    frame.setVisible(true);
                }else{
                    JFrame listFrame = new JFrame("DVD Listele");
                    JPanel listPanel = new JPanel();
                    listPanel.setBackground(Color.DARK_GRAY);
                    listPanel.setLayout(null);
                    listFrame.setLocation(200,100);
                    listFrame.setSize(800,600);
                    listFrame.setResizable(false);
                    listFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    JButton backButton=new JButton(backIcon);
                    backButton.setSize(39,35);
                    backButton.setLocation(700,500);
                    backButton.setBackground(Color.darkGray);
                    backButton.addActionListener(new ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt){
                            listFrame.setVisible(false);
                            frame.setVisible(true);
                        }
                    });
                    listPanel.add(backButton);
                    JLabel j1=new JLabel("Ad Soyad");
                    j1.setForeground(Color.RED);
                    j1.setSize(100,30);
                    j1.setLocation(50,0);
                    listPanel.add(j1);
                    Node1 iterator=l1.head;
                    int i=50;
                    while(iterator!=null){
                        //sanatçı adını kontrol ederek text area oluşturma
                        if(!(iterator.dvd.getName().equals(s))){
                            iterator=iterator.next;
                            continue;
                        }
                        JTextArea temp = new JTextArea();
                        temp.setSize(100,30);
                        temp.setLocation(50,i);
                        i+=50;
                        temp.setText(iterator.dvd.getName());
                        temp.setBackground(Color.DARK_GRAY);
                        temp.setForeground(Color.WHITE);
                        listPanel.add(temp);
                        iterator=iterator.next;
                    }
                    JLabel j2=new JLabel("Albüm");
                    j2.setForeground(Color.RED);
                    j2.setSize(100,30);
                    j2.setLocation(200,0);
                    listPanel.add(j2);
                    //albüm isimleri için text area oluşturur
                    Node1 iterator2=l1.head;
                    int j=50;
                    while(iterator2!=null){
                        if(!(iterator2.dvd.getName().equals(s))){
                            iterator2=iterator2.next;
                            continue;
                        }  
                        JTextArea temp2 = new JTextArea();
                        temp2.setSize(200,30);
                        temp2.setLocation(200,j);
                        j+=50;
                        temp2.setText(iterator2.dvd.getHeader());
                        temp2.setBackground(Color.DARK_GRAY);
                        temp2.setForeground(Color.WHITE);
                        listPanel.add(temp2);
                        iterator2=iterator2.next;
                    }
                    //çıkış tarihleri için text area
                    Node1 iterator3=l1.head;
                    int k=50;
                    JLabel j3=new JLabel("Çıkış Tarihi");
                    j3.setForeground(Color.RED);
                    j3.setSize(100,30);
                    j3.setLocation(400,0);
                    listPanel.add(j3);
                    while(iterator3!=null){
                        if(!(iterator3.dvd.getName().equals(s))){
                            iterator3=iterator3.next;
                            continue;
                        }
                        JTextArea temp3 = new JTextArea();
                        temp3.setSize(40,30);
                        temp3.setLocation(400,k);
                        k+=50;
                        temp3.setText(""+iterator3.dvd.getReleaseDate());
                        temp3.setBackground(Color.DARK_GRAY);
                        temp3.setForeground(Color.WHITE);
                        listPanel.add(temp3);
                        iterator3=iterator3.next;
                    }
                    Node1 iterator4=l1.head;
                    int l=50;
                    JLabel j4=new JLabel("Fiyat");
                    j4.setForeground(Color.red);
                    j4.setSize(100,30);
                    j4.setLocation(550,0);
                    listPanel.add(j4);
                    //dvd fiyatları için text area oluşturur
                    while(iterator4!=null){
                        if(!(iterator4.dvd.getName().equals(s))){
                            iterator4=iterator4.next;
                            continue;
                        }
                        JTextArea temp4 = new JTextArea();
                        temp4.setSize(40,30);
                        temp4.setLocation(550,l);
                        l+=50;
                        temp4.setText(""+iterator4.dvd.getPrice());
                        temp4.setBackground(Color.DARK_GRAY);
                        temp4.setForeground(Color.WHITE);
                        listPanel.add(temp4);
                        iterator4=iterator4.next;
                    }
                    //sanatçı şarkılarını listeleyen butonları ekleme
                    Node1 iterator5=l1.head;
                    int h=50;
                    while(iterator5!=null){
                        if(!(iterator5.dvd.getName().equals(s))){
                            iterator5=iterator5.next;
                            continue;
                        }
                        JButton temp7 = new JButton("Şarkılarını Gör");
                        temp7.setSize(120,20);
                        temp7.setLocation(600,h);
                        listPanel.add(temp7);
                        String sson = iterator5.dvd.getSongs();
                        iterator5=iterator5.next;
                        h+=50;
                        temp7.addActionListener(new ActionListener(){
                            public void actionPerformed(java.awt.event.ActionEvent evt){
                                JOptionPane.showMessageDialog(null,sson+" ","Şarkılar",JOptionPane.PLAIN_MESSAGE);
                            }
                        });
                    }
                    listFrame.setContentPane(listPanel);
                    frame.setVisible(false);
                    listFrame.setVisible(true);
                    }}
        });
        mainpanel.add(searchbutton);
        //ana menüye dvd ekle butonu ekler
        JButton addbutton=new JButton();
        addbutton.setText("DVD Ekle");
        addbutton.setSize(250,35);
        addbutton.setLocation(45,230);
        addbutton.setHorizontalAlignment(0);
        Color addbuttonDef = addbutton.getBackground();
        addbutton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                addbutton.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                addbutton.setBackground(addbuttonDef);
                super.mouseExited(arg0);
            }
        });
        addbutton.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                // dvd ekleme butonu işlevi
                JFrame addFrame = new JFrame("DVD Ekle");
                JPanel addPanel = new JPanel();
                addPanel.setLayout(null);
                addPanel.setBackground(Color.DARK_GRAY);
                addFrame.setLocation(400,100);
                addFrame.setSize(300,400);
                addFrame.setResizable(false);
                addFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                JButton backButton=new JButton(backIcon);
                backButton.setSize(39,35);
                backButton.setLocation(220,300);
                backButton.setBackground(Color.darkGray);
                backButton.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        addFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                addPanel.add(backButton);
                JTextField addTf1 = new JTextField("Ad Soyad");
                JTextField addTf2 = new JTextField("Albüm");
                JTextField addTf3 = new JTextField("Çıkış Tarihi");
                JTextField addTf4 = new JTextField("Fiyat");
                JTextField addTf5 = new JTextField("Şarkılar");
                addTf1.setLocation(85,30);
                addTf1.setSize(120,30);
                addTf1.setForeground(Color.black);
                addTf1.setBackground(Color.white);
                addPanel.add(addTf1);
                addTf2.setLocation(85,70);
                addTf2.setSize(120,30);
                addTf2.setForeground(Color.black);
                addTf2.setBackground(Color.white);
                addPanel.add(addTf2);
                addTf3.setLocation(85,110);
                addTf3.setSize(120,30);
                addTf3.setForeground(Color.black);
                addTf3.setBackground(Color.white);
                addPanel.add(addTf3);
                addTf4.setLocation(85,150);
                addTf4.setSize(120,30);
                addTf4.setForeground(Color.black);
                addTf4.setBackground(Color.white);
                addPanel.add(addTf4);
                addTf5.setLocation(85,190);
                addTf5.setSize(120,30);
                addTf5.setForeground(Color.black);
                addTf5.setBackground(Color.white);
                addPanel.add(addTf5);
                //şarkı bilgilerini almak için ekle butonu oluşturulması
                JButton addSong=new JButton("EKLE");
                addSong.setSize(75,30);
                addSong.setLocation(105,240);
                addPanel.add(addSong);
                addFrame.setContentPane(addPanel);
                addSong.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        //ekle butonunun işlevi  
                        ArrayList<String> a = new ArrayList<>();
                        Scanner s = new Scanner(addTf5.getText()).useDelimiter(",");
                        while(s.hasNext()){
                            a.add(s.next());
                        }
                        try{
                            DVD temp = new DVD(addTf1.getText(),addTf2.getText(),
                            Integer.parseInt(addTf3.getText()),Integer.parseInt(addTf4.getText()),a);
                            l1.insertInOrder(temp);
                            addFrame.setVisible(false);
                            frame.setVisible(true);
                        }
                        catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Tarih veya Fiyat Bilgisine Metin Girilemez!",
                            "Hata",JOptionPane.ERROR_MESSAGE);
                        }
                        }});
                addFrame.setContentPane(addPanel);
                frame.setVisible(false);
                addFrame.setVisible(true);
                }
            });
        mainpanel.add(addbutton);
        //ana menüye dvd sil butonu ekleme      
        JButton deletebutton=new JButton();
        deletebutton.setText("DVD Sil");
        deletebutton.setSize(250,35);
        deletebutton.setLocation(45,280);
        deletebutton.setHorizontalAlignment(0);
        Color deletebuttonDef = deletebutton.getBackground();
        deletebutton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                deletebutton.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                deletebutton.setBackground(deletebuttonDef);
                super.mouseExited(arg0);
            }
        });
        deletebutton.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                // dvd sil butonu işlevi
                JFrame addFrame = new JFrame("DVD Ekle");
                JPanel addPanel = new JPanel();
                addPanel.setLayout(null);
                addPanel.setBackground(Color.DARK_GRAY);
                addFrame.setLocation(400,100);
                addFrame.setSize(300,400);
                addFrame.setResizable(false);
                addFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                JButton backButton=new JButton(backIcon);
                backButton.setSize(39,35);
                backButton.setLocation(220,300);
                backButton.setBackground(Color.darkGray);
                backButton.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        addFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                });
                addPanel.add(backButton);
                JTextField addTf1 = new JTextField("Ad Soyad");
                JTextField addTf2 = new JTextField("Albüm");
                addTf1.setLocation(85,30);
                addTf1.setSize(120,30);
                addTf1.setForeground(Color.black);
                addTf1.setBackground(Color.white);
                addPanel.add(addTf1);
                addTf2.setLocation(85,70);
                addTf2.setSize(120,30);
                addTf2.setForeground(Color.black);
                addTf2.setBackground(Color.white);
                addPanel.add(addTf2);
                //sil butonu
                JButton addSong=new JButton("SİL");
                addSong.setSize(75,30);
                addSong.setLocation(105,150);
                addPanel.add(addSong);
                addFrame.setContentPane(addPanel);
                addSong.addActionListener(new ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        //sil butonu işlevi
                        if(l1.deleteByName(addTf1.getText(),addTf2.getText())){
                            addFrame.setVisible(false);
                            frame.setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(null,"Girilen Bilgilerle DVD Bulunamadı",
                                    "HATA",JOptionPane.WARNING_MESSAGE);
                        }
                        }});
                addFrame.setContentPane(addPanel);
                frame.setVisible(false);
                addFrame.setVisible(true);
                }
            });
        mainpanel.add(deletebutton);
        //ana menüye kaydet ve çık butonu ekler
        JButton exitbutton=new JButton();
        exitbutton.setText("KAYDET VE ÇIK");
        exitbutton.setSize(250,35);
        exitbutton.setLocation(45,330);
        exitbutton.setHorizontalAlignment(0);
        Color exitbuttonDef = deletebutton.getBackground();
        exitbutton.addMouseListener(new MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent arg0){
                exitbutton.setBackground(Color.ORANGE);
                super.mouseEntered(arg0);
            }
            public void mouseExited(java.awt.event.MouseEvent arg0){
                exitbutton.setBackground(exitbuttonDef);
                super.mouseExited(arg0);
            }
        });
        exitbutton.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                BufferedWriter writer = null;
                // kaydet ve çık butonu işlevi
                try{
                    writer= new BufferedWriter(new FileWriter("bilgiler.txt"));
                    Node1 i1 = l1.head;
                    while(i1!=null){
                        writer.write(i1.toString());
                        writer.newLine();
                        i1=i1.next;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    try{writer.close();
                    frame.setEnabled(false);
                    System.exit(0);
                }
                catch(Exception e){}
                }
            }
        });
        mainpanel.add(exitbutton);
        frame.setContentPane(mainpanel);
        frame.setVisible(true);
    }
}