import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Login extends JFrame implements ActionListener
{
	JPanel jp;
	JLabel userl,userl2,t1,t2,t3,t4,t5,cr;
	JPasswordField pass;
	JButton submit,change;
	Font ft1,ft2,ft3;
	Color c1,c2;
	Dimension d;
	Background bg;
	Image img;
	ImageIcon imgicon1,imgicon2,imgicon3,lionimg;
	int a,b,c,e,f;

	Connection con;
	Statement st;
	ResultSet rs;
	
	Login()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			//try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }catch(Exception e1){}
			//try {UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());} catch (Exception e) {}
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			bg=new Background();
			jp=new JPanel();
			jp.setLayout(null);
					
			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);			
			setTitle("Department Library Login");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(bg.wrapInBackgroundImage(jp,new ImageIcon(Login.class.getResource("LoginBg.jpg"))));

			userl=new JLabel();
			userl2=new JLabel("CSE Departement Library,MIT");
			cr=new JLabel(" CopyRights © 2011,Department of Computer Science Engineering,MIT.");												
		
			pass=new JPasswordField(20);
			
			submit=new JButton();
			change=new JButton();
			
			jp.add(cr);		
			jp.add(userl);
			jp.add(userl2);
			jp.add(pass);
			jp.add(submit);
			jp.add(change);
			jp.getRootPane().setDefaultButton(submit);		
			
				
			ft1=new Font("Times New Roman",1,18); 
			ft2=new Font("Times New Roman",1,30);
			ft3=new Font("Calibri",1,16); 
			c1=new Color(0,100,0);
			c2=new Color(0,0,0);

			userl2.setFont(ft1);
			pass.setFont(ft2);
			cr.setFont(ft3);
			
			
			//cr.setForeground(c2);
			userl2.setForeground(c1);
			pass.setForeground(c1);
			
			imgicon1=new ImageIcon(Login.class.getResource("DL.png"));
			userl.setIcon(imgicon1);
			imgicon2=new ImageIcon(Login.class.getResource("LoginBtn.png"));
			submit.setIcon(imgicon2);
			imgicon3=new ImageIcon(Login.class.getResource("ChPwdBtn.png"));
			change.setIcon(imgicon3);
			lionimg=new ImageIcon(Login.class.getResource("lion.gif"));
			
			
			submit.setOpaque(false);
			submit.setActionCommand("Submit");
			submit.setContentAreaFilled(false);
			submit.setBorderPainted(false);
			submit.setToolTipText("Login");
			change.setOpaque(false);
			change.setContentAreaFilled(false);
			change.setActionCommand("Change Password");
			change.setBorderPainted(true);
			change.setToolTipText("Change Password");
			pass.requestFocus(); 	
															
			a=((d.width-128)/2);
			b=((d.height-290)/2);
			c=((d.width-250)/2);
			e=(d.width-260);
			f=(d.height-200);

			userl.setBounds(a,b,130,130);
			userl2.setBounds(c,b+120,250,30);
			pass.setBounds(c,b+150,220,30);
			submit.setBounds(c+220,b+140,50,50);
			change.setBounds(10,d.height-110,50,50);
			cr.setBounds(c-120,b+430,500,30);
								
			submit.addActionListener(this);
			change.addActionListener(this);
						
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e, "Exception", JOptionPane.WARNING_MESSAGE,lionimg);
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae1)
	{
		String b="";
		String s=ae1.getActionCommand();
		if(s=="Submit")
		{
			try
			{
				String a=pass.getText().trim();
				st=con.createStatement();
				rs=st.executeQuery("SELECT password FROM pass WHERE id=1");
				while(rs.next())
				{
					b=rs.getString(1);	
				}
				if(a.equals(b))
				{
					Home hobj=new Home();
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Please Re-Enter Your Password", "Worng Password", JOptionPane.WARNING_MESSAGE,lionimg);
					pass.setText("");
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, e, "Exception", JOptionPane.WARNING_MESSAGE,lionimg);				
				System.out.println(e);
			}
		}
		if(s=="Change Password")
		{
			ChangePwd chpobj=new ChangePwd();
			dispose();
		}
	}
	public static void main(String args[])
	{
		//JFrame.setDefaultLookAndFeelDecorated(true);
		//JDialog.setDefaultLookAndFeelDecorated(true);
		UIManager UI=new UIManager();
		UI.put("OptionPane.background",new Color(255,255,255));
		UI.put("Panel.background",new Color(255,255,255));

		if (SystemTray.isSupported())
		{
			MenuItem closeItem = new MenuItem("Close");
			closeItem.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {System.exit(0);}});
			PopupMenu pmenu = new PopupMenu();
			pmenu.add(closeItem);
			Image img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			TrayIcon trayIcon = new TrayIcon(img, "Maamallan Institute of Technology",pmenu);
			trayIcon.setImageAutoSize(true);
			SystemTray tray = SystemTray.getSystemTray();
			try
			{
				tray.add(trayIcon);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}	
		Login lobj=new Login();
	}
}



//------------------------------------------------------------------------------------------------------------------------------

class Background
{
	public static final GridBagConstraints gbc;
	static {
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;
	        }
	
	public static JPanel wrapInBackgroundImage(JComponent component,Icon backgroundIcon) 
	{
		return wrapInBackgroundImage(component,backgroundIcon,JLabel.TOP,JLabel.LEADING);
	}
	
	public static JPanel wrapInBackgroundImage(JComponent component,Icon backgroundIcon,int verticalAlignment,int horizontalAlignment) 
	{
		component.setOpaque(false);
	        	JPanel backgroundPanel = new JPanel(new GridBagLayout());
	        	backgroundPanel.add(component, gbc);
	        	JLabel backgroundImage = new JLabel(backgroundIcon);
	        	backgroundImage.setPreferredSize(new Dimension(1,1));
	        	backgroundImage.setMinimumSize(new Dimension(1,1));
	        	backgroundImage.setVerticalAlignment(verticalAlignment);
	        	backgroundImage.setHorizontalAlignment(horizontalAlignment);
	        	backgroundPanel.add(backgroundImage, gbc);
	        	return backgroundPanel;
	}
}

//-------------------------------------------------------------------------------------------------------------------------------

class Home extends JFrame implements ActionListener
{
	JPanel jp;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	JLabel tl,mitl,csel,miti,dd;
	Dimension d;
	Background bg;
	Image img;
	ImageIcon imgicon0,imgicon2,imgicon1,imgicon3;
	Font ft1,ft2,ft3;
	Color c1;
	int a,b,c,e,f;
	
	Home()
	{
		d = Toolkit.getDefaultToolkit().getScreenSize();
		img = Toolkit.getDefaultToolkit().getImage("Logo.png");
		bg=new Background();
		jp=new JPanel();
		jp.setLayout(null);
		
		setLayout(null);
		setVisible(true);
		setSize(d.width,d.height);
		setIconImage(img);
		setTitle("Department Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(jp);
		setContentPane(bg.wrapInBackgroundImage(jp,new ImageIcon(Home.class.getResource("bg.jpg"))));
		
		imgicon0=new ImageIcon(Home.class.getResource("mit.jpg"));
		
					
		tl=new JLabel("HH : MM : SS");
		mitl=new JLabel("JEPPIAAR MAAMALLAN INSTITUTE OF TECHNOLOGY, SRIPERUMPUDUR");
		csel=new JLabel("DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING");
		miti=new JLabel();
		dd=new JLabel("Designed and Developed by");
	
				
		miti.setIcon(imgicon0);
					
		jb1= new JButton("STUDENT DETAILS");
		jb2= new JButton("BOOK DETAILS");
		jb3= new JButton("BOOK ISSUE");
		jb4= new JButton("FACULTY");
		jb5= new JButton("LEND");
		jb6= new JButton("BORROW");
		jb7=new JButton();
		jb8=new JButton(new ImageIcon("summary.png"));
		jb9=new JButton(new ImageIcon("backup.png"));
		jb10=new JButton(new ImageIcon("help.png"));
		
		//---------------------------------------------------------------------------------------------------
			
		class Slide extends JPanel 
		{
			int x,y;
			public Slide()
			{
				x = 0;
				y = 0;
			}
			public void paintComponent(Graphics g)
			{
				x -= 1;
				if ( x < d.width-2500)
					x =0;
				Graphics2D g2 = (Graphics2D) g;
				Image ph1=Toolkit.getDefaultToolkit().getImage("ph1.jpg");
				Image ph2=Toolkit.getDefaultToolkit().getImage("ph2.jpg");
				Image ph3=Toolkit.getDefaultToolkit().getImage("ph3.jpg");
				Image ph4=Toolkit.getDefaultToolkit().getImage("ph4.jpg");
				Image ph5=Toolkit.getDefaultToolkit().getImage("ph5.jpg");
				Image ph6=Toolkit.getDefaultToolkit().getImage("ph6.jpg");
				Image ph7=Toolkit.getDefaultToolkit().getImage("ph7.jpg");
				Image ph8=Toolkit.getDefaultToolkit().getImage("ph8.jpg");
				Image ph9=Toolkit.getDefaultToolkit().getImage("ph9.jpg");
				Image ph10=Toolkit.getDefaultToolkit().getImage("ph10.jpg");
				g2.drawImage(ph1,x,y,250,150,this);
				g2.drawImage(ph2,x+250,y,250,150,this);
				g2.drawImage(ph3,x+500,y,250,150,this);
				g2.drawImage(ph4,x+750,y,250,150,this);
				g2.drawImage(ph5,x+1000,y,250,150,this);
				g2.drawImage(ph6,x+1250,y,250,150,this);
				g2.drawImage(ph7,x+1500,y,250,150,this);
				g2.drawImage(ph8,x+1750,y,250,150,this);
				g2.drawImage(ph9,x+2000,y,250,150,this);
				g2.drawImage(ph10,x+2250,y,250,150,this);
			}
		}

		//---------------------------------------------------------------------------------------------------		
		class DesDev extends JPanel 
		{
			int x,y;
			public DesDev()
			{
				x = 420;
				y = 20;
			}
			public void paintComponent(Graphics g)
			{
				x -= 1;
				if ( x < -420)
					x =420;
				Graphics2D g2 = (Graphics2D) g;
				Font font = new Font("Times New Roman",3,18);
				g2.setFont(font);
				g2.setPaint(new Color(0,0,140));
				g2.drawString("Prakash.K",x,y);
				g2.setPaint(new Color(0,200,0));
				g2.drawString("CSE(2008-2012)",x+90,y);
				g2.setPaint(new Color(0,0,140));
				g2.drawString("Harish.S",x+240,y);
				g2.setPaint(new Color(0,200,0));
				g2.drawString("CSE(2009-2013)",x+320,y);
			}
		}

		//---------------------------------------------------------------------------------------------------
		Slide slide=new Slide();
		DesDev desdev=new DesDev();		
			
		//---------------------------------------------------------------------------------------------------		
		class Clock extends TimerTask
		{
			int a,b,c,i=1;
			Calendar now;
			public void run()
			{
				repaint();
				now = Calendar.getInstance();
				a=now.get(Calendar.HOUR_OF_DAY);
				b=now.get(Calendar.MINUTE);
            				c=now.get(Calendar.SECOND);
				tl.setText(" "+a+" : "+b+" : "+c+" ");	
												
			}
		}	
		
		//---------------------------------------------------------------------------------------------------		

		imgicon3=new ImageIcon(Login.class.getResource("aboutus.png"));
		jb7.setIcon(imgicon3);
		
				
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);
		jp.add(jb4);
		jp.add(jb5);
		jp.add(jb6);
		jp.add(jb7);
		jp.add(jb8);
		jp.add(jb9);
		jp.add(jb10);
		jp.add(mitl);
		jp.add(csel);
		jp.add(miti);
		jp.add(tl);
		jp.add(dd);
		
		ft1=new Font("Digital-7",1,19); 
		ft2=new Font("Algerian",1,21);
		ft3=new Font("Times New Roman",1,16);
		c1=new Color(50,140,90);
		

		tl.setFont(ft1);		
		mitl.setFont(ft2);
		csel.setFont(ft2);
		dd.setFont(ft3);
			
				
		Clock clock=new Clock();
		java.util.Timer t=new java.util.Timer();
		t.schedule(clock,1,1);
		jp.add(slide);
		jp.add(desdev);	

		jb7.setOpaque(false);
		jb7.setActionCommand("About Us");
		jb7.setContentAreaFilled(false);
		jb7.setBorderPainted(false);
		jb7.setToolTipText("About Us");
		jb8.setActionCommand("Final");
		jb8.setToolTipText("Summary Of Department Books");
		jb8.setOpaque(false);
		jb8.setContentAreaFilled(false);
		jb8.setBorderPainted(false); 
		jb9.setActionCommand("BackUp");
		jb9.setToolTipText("Back Up It");
		jb9.setOpaque(false);
		jb9.setContentAreaFilled(false);
		jb9.setBorderPainted(false); 
		jb10.setActionCommand("Help");
		jb10.setToolTipText("Help");
		jb10.setOpaque(false);
		jb10.setContentAreaFilled(false);
		jb10.setBorderPainted(false); 

				
		a=(d.width-550)/2;
		b=350;
		c=(d.width-740)/2;
		e=(d.width-120)/2;
		f=(d.width-240)/2;
	
		slide.setBounds(0,0,d.width,150);
		desdev.setBounds(d.width-420,d.height-100,420,60);

		jb1.setBounds(a,b,150,30);
		jb2.setBounds(a+400,b,150,30);
		jb3.setBounds(a+200,b,150,30);
		jb4.setBounds(a+200,b+100,150,30);
		jb5.setBounds(a,b+100,150,30);
		jb6.setBounds(a+400,b+100,150,30);
		jb10.setBounds(f,d.height-120,50,50);
		jb7.setBounds(f+60,d.height-120,50,70);
		jb8.setBounds(f+120,d.height-120,50,50);
		jb9.setBounds(f+180,d.height-120,50,50);

	
		dd.setBounds(d.width-240,d.height-120,200,20);
		tl.setBounds(10,d.height-100,110,20);		
		mitl.setBounds(c,160,740,30);
		csel.setBounds(c+70,190,600,30);
		miti.setBounds(e,220,120,120);
		
			
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		jb8.addActionListener(this);	
		jb9.addActionListener(this);	
		jb10.addActionListener(this);	
				
	} 
	public void actionPerformed(ActionEvent ae2)
	{
		String s=ae2.getActionCommand();
		repaint();
		if(s=="STUDENT DETAILS")
		{
			Student sobj=new Student();
			dispose();
		}
		if(s=="BOOK DETAILS")
		{
			Book bobj=new Book();
			dispose();		
		}
		if(s=="BOOK ISSUE")
		{
			Transaction tobj=new Transaction();
			dispose();	
			
		}
		if(s=="FACULTY")
		{
			Faculty fobj=new Faculty();
			dispose();
		}
		if(s=="LEND")
		{
			Lend lobj=new Lend();
			dispose();
		}
		if(s=="BORROW")
		{
			Borrow boobj=new Borrow();
			dispose();
		}
		if(s=="About Us")
		{
			AboutUs abbj=new AboutUs();
		}
		if(s=="Final")
		{
			Final fobj=new Final();
		}
		if(s=="Help")
		{
			Login lobj=new Login();
			dispose();
		}
		if(s=="BackUp")
		{
			try{
				byte b[]=new byte[10000000];
				File f=new File("library.mdb");
				FileInputStream fis=new FileInputStream(f);
				fis.read(b);
				//java.util.Date date=new java.util.Date("dd-mm-yy");
				//String filename="D:////"+date+".mdb";
				//FileOutputStream fos=new FileOutputStream(filename);	
				FileOutputStream fos=new FileOutputStream("D://library.mdb");	
				fos.write(b);
				JOptionPane.showMessageDialog(this, "Back up", "BackUp Successfully",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(Home.class.getResource("lion.gif")));
						
			}
			catch(IOException e)
			{
				System.out.println(e);
			}
		}
	}
}

//-------------------------------------------------------------------------------------------------------------------------------

class Student extends JFrame implements ActionListener
{
	JPanel jp;
	JLabel regl,namel,batchl,secl,bookl,border1,border2;
	JTextField reg,name,batch;
	JComboBox sec;
	JButton add,updat,find,clear,home,stud;
	Font ft1,ft2;
	Color c1,c2;
	Dimension d;
	Image img;
	ImageIcon imgicon1,imgicon2,imgicon,lionimg;
	int a,b,c,e,f;
	

	Connection con;
	Statement st;
	ResultSet rs;
	
	Student()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();			
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);
			
			setLayout(null);
			setVisible(true);
			setSize(d.width,d.height);
			setIconImage(img);
			setTitle("Student");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(jp);
			
			regl=new JLabel("Reg. No");
			namel=new JLabel("Name");
			batchl=new JLabel("Batch");
			secl=new JLabel("Section");
			bookl=new JLabel();
			border1=new JLabel();
			border2=new JLabel();
						
			reg=new JTextField(20);
			name=new JTextField(20);
			batch=new JTextField(20);

			sec=new JComboBox();
			sec.addItem("A");
			sec.addItem("B");
			
			add=new JButton("Add");
			updat=new JButton("Update");
			clear=new JButton("Clear");
			stud=new JButton("Details");
			find=new JButton(new ImageIcon("find.png"));
			home=new JButton(new ImageIcon("home.jpg"));
						
			ft1=new Font("Times New Roman",1,18); 
			ft2=new Font("Times New Roman",1,18); 
			c1=new Color(0,100,0);
			c2=new Color(0,100,0);

			regl.setFont(ft1);
			namel.setFont(ft1);
			batchl.setFont(ft1);
			secl.setFont(ft1);
			regl.setForeground(c1);
			namel.setForeground(c1);
			batchl.setForeground(c1);
			secl.setForeground(c1);
					
			reg.setFont(ft2);
			name.setFont(ft2);
			batch.setFont(ft2);			
			sec.setFont(ft2);			
			reg.setForeground(c2);
			name.setForeground(c2);
			batch.setForeground(c2);
			sec.setForeground(c2);
			
			imgicon=new ImageIcon(Book.class.getResource("book.gif"));
			bookl.setIcon(imgicon);
			imgicon1=new ImageIcon(Book.class.getResource("border1.jpg"));
			border1.setIcon(imgicon1);
			imgicon2=new ImageIcon(Book.class.getResource("border2.jpg"));
			border2.setIcon(imgicon2);
			lionimg=new ImageIcon(Book.class.getResource("lion.gif"));
			
			home.setActionCommand("Home");
			home.setToolTipText("Go to Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false); 	
			find.setActionCommand("Find");
			find.setToolTipText("Find");
			find.setOpaque(false);
			find.setContentAreaFilled(false);
			find.setBorderPainted(false);
			
			jp.add(regl);
			jp.add(namel);
			jp.add(batchl);
			jp.add(secl);
			jp.add(reg);
			jp.add(name);
			jp.add(sec);
			jp.add(batch);
			jp.add(add);
			jp.add(find);
			jp.add(updat);
			jp.add(clear);
			jp.add(stud);
			jp.add(home);
			jp.add(bookl);
			jp.add(border1);
			jp.add(border2);
			
			a=(d.width-195)/2;
			b=(d.height-200)/2;
			c=(d.width-430)/2;
			e=d.width-100;
			f=(d.height-544)/2;

		
			regl.setBounds(a,b,75,20);
			reg.setBounds(a+75,b,120,20);
			find.setBounds(a+200,b-5,30,30);
			namel.setBounds(a,b+30,75,20);
			name.setBounds(a+75,b+30,120,20);
			batchl.setBounds(a,b+60,75,20);
			batch.setBounds(a+75,b+60,120,20);
			secl.setBounds(a,b+90,75,20);
			sec.setBounds(a+75,b+90,120,20);
			bookl.setBounds(10,f,300,244);
			border1.setBounds(0,d.height-380,350,380);
			border2.setBounds(d.width-350,d.height-380,350,380);


			stud.setBounds(c,b+130,100,30);
			add.setBounds(c+110,b+130,100,30);
			updat.setBounds(c+220,b+130,100,30);
			clear.setBounds(c+330,b+130,100,30);
			
			home.setBounds(e,10,75,75);

			add.addActionListener(this);
			updat.addActionListener(this);
			find.addActionListener(this);			
			clear.addActionListener(this);
			home.addActionListener(this);				
			stud.addActionListener(this);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e, "Exception", JOptionPane.WARNING_MESSAGE,lionimg);
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae3)
	{
		String s=ae3.getActionCommand();
		if(s=="Add")
		{
			try{
				String s1="";
				if(s1.equals(reg.getText())||s1.equals(name.getText())||s1.equals(batch.getText()))
				{
					JOptionPane.showMessageDialog(this, "Some field has null value", "Added Successfully",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				else
				{
					st=con.createStatement();
					st.executeUpdate("INSERT INTO student(regno,name,batch,section) VALUES('"+reg.getText()+"','"+name.getText()+"','"+batch.getText()+"','"+sec.getSelectedItem()+"')");
					JOptionPane.showMessageDialog(this, "Student Details Added Successfully", "Added Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
							
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, e , "Exception Raised", JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		}
		if(s=="Update")
		{
			try{
				String s1="";
				if(s1.equals(reg.getText())||s1.equals(name.getText())||s1.equals(batch.getText()))
				{
					JOptionPane.showMessageDialog(this, "Some field has null value", "Added Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				else
				{
					st=con.createStatement();
					st.executeUpdate("UPDATE student SET name='"+name.getText()+"',batch='"+batch.getText()+"',section='"+sec.getSelectedItem()+"' WHERE regno='"+reg.getText()+"'");
					JOptionPane.showMessageDialog(this, "Student Details Updated Successfully", "Updated Successfully",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}		
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, e , "Exception Raised", JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		}
		if(s=="Find")
		{
			try{
				st=con.createStatement();
				rs=st.executeQuery("SELECT * FROM student WHERE regno='"+reg.getText()+"'");
				while(rs.next())
				{			
					name.setText(rs.getString("name"));
					batch.setText(rs.getString("batch"));
					sec.setSelectedItem(rs.getString("section"));
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, e , "Added Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);

			}
		}
		if(s=="Clear")
		{
			reg.setText("");
			name.setText("");
			batch.setText("");
		}
		if(s=="Home")
		{
			Home hobj=new Home();
			dispose();
		}
		if(s=="Details")
		{
			Details2 obj=new Details2();
			
		}		
	}
	
}

//------------------------------------------------------------------------------------------------------------------------------

class Book extends JFrame implements ActionListener
{
	JPanel jp;
	JLabel idl,nol,authorl,publ,edil,pril,bookl,nobl,border1,border2;
	JTextField id,no,author,pub,edi,pri,nob;
	JButton add,updat,find,clear,missed,available,multiple,home,found;
	Dimension d;
	Font ft1,ft2;
	Color c1,c2;
	Image img;
	ImageIcon imgicon,imgicon1,imgicon2,lionimg;
	int a,b,c,e,f,g;

	Connection con;
	Statement st;
	ResultSet rs;
	
	Book()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();			
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);
			
			setLayout(null);
			setVisible(true);
			setSize(d.width,d.height);
			setIconImage(img);		
			setTitle("Book");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(jp);
				
			idl=new JLabel("Book ID");
			nol=new JLabel("Book No");
			authorl=new JLabel("Author");
			publ=new JLabel("Publication");
			edil=new JLabel("Edition");
			pril=new JLabel("Price");
			bookl=new JLabel();
			border1=new JLabel();
			border2=new JLabel();
			nobl=new JLabel("No of Books");
						
			id=new JTextField(20);
			no=new JTextField(20);
			author=new JTextField(20);
			pub=new JTextField(20);
			edi=new JTextField(20);
			pri=new JTextField(20);
			nob=new JTextField(20); 
			
			add=new JButton("Add");
			updat=new JButton("Update");
			find=new JButton(new ImageIcon("find.png"));
			clear=new JButton("Clear");
			missed=new JButton("Missed");
			multiple=new JButton("Multiple");
			available=new JButton("Available");
			found=new JButton("Found");
			home=new JButton(new ImageIcon("home.jpg"));
			
						
			ft1=new Font("Times New Roman",1,18); 
			ft2=new Font("Times New Roman",1,18); 
			c1=new Color(0,100,0);
			c2=new Color(0,100,0);
			
			idl.setFont(ft1);
			nol.setFont(ft1);
			authorl.setFont(ft1);
			publ.setFont(ft1);
			edil.setFont(ft1);
			pril.setFont(ft1);
			nobl.setFont(ft1);

			idl.setForeground(c1);
			nol.setForeground(c1);
			authorl.setForeground(c1);
			publ.setForeground(c1);
			edil.setForeground(c1);
			pril.setForeground(c1);
			nobl.setForeground(c1);
			
			id.setFont(ft2);
			no.setFont(ft2);
			author.setFont(ft2);
			pub.setFont(ft2);
			edi.setFont(ft2);
			pri.setFont(ft2);
			nob.setFont(ft2);

			id.setForeground(c2);
			no.setForeground(c2);
			author.setForeground(c2);
			pub.setForeground(c2);
			edi.setForeground(c2);
			pri.setForeground(c2);
			nob.setForeground(c2);
			
			imgicon=new ImageIcon(Book.class.getResource("book.gif"));
			bookl.setIcon(imgicon);
			imgicon1=new ImageIcon(Book.class.getResource("border1.jpg"));
			border1.setIcon(imgicon1);
			imgicon2=new ImageIcon(Book.class.getResource("border2.jpg"));
			border2.setIcon(imgicon2);
			lionimg=new ImageIcon(Book.class.getResource("lion.gif"));


			home.setActionCommand("Home");
			home.setToolTipText("Go to Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false); 
			find.setActionCommand("Find");
			find.setToolTipText("Find");
			find.setOpaque(false);
			find.setContentAreaFilled(false);
			find.setBorderPainted(false); 
			
		

			jp.add(idl);
			jp.add(nol);
			jp.add(authorl);
			jp.add(publ);
			jp.add(edil);
			jp.add(pril);
			jp.add(nobl);

			jp.add(id);
			jp.add(no);
			jp.add(author);
			jp.add(pub);
			jp.add(edi);
			jp.add(pri);
			jp.add(nob);

			jp.add(add);
			jp.add(find);
			jp.add(updat);
			jp.add(clear);
			jp.add(missed);
			jp.add(multiple);
			jp.add(available);
			jp.add(found);
			jp.add(home);
			jp.add(bookl);
			jp.add(border1);
			jp.add(border2);

			a=(d.width-220)/2;
			b=(d.height-400)/2;
			c=(d.width-320)/2;
			e=d.width-100;
			f=(d.height-544)/2;
			g=a-230;
	

			idl.setBounds(a,b+50,100,20);
			id.setBounds(a+100,b+50,120,20);
			nol.setBounds(a,b+80,100,20);
			no.setBounds(a+100,b+80,120,20);
			find.setBounds(a+230,b+75,30,30);
			authorl.setBounds(a,b+110,100,20);
			author.setBounds(a+100,b+110,120,20);
			publ.setBounds(a,b+140,100,20);
			pub.setBounds(a+100,b+140,120,20);
			edil.setBounds(a,b+170,100,20);
			edi.setBounds(a+100,b+170,120,20);
			pril.setBounds(a,b+200,100,20);
			pri.setBounds(a+100,b+200,120,20);
			
			bookl.setBounds(10,f,300,244);
			border1.setBounds(0,d.height-380,350,380);
			border2.setBounds(d.width-350,d.height-380,350,380);
			
			add.setBounds(c,b+230,100,30);
			updat.setBounds(c+110,b+230,100,30);
			clear.setBounds(c+220,b+230,100,30);
			found.setBounds(c,b+270,100,30);
			missed.setBounds(c+110,b+270,100,30);
			available.setBounds(c+220,b+270,100,30);

			nobl.setBounds(c,b+320,100,20);
			nob.setBounds(c+100,b+320,110,20);
			multiple.setBounds(c+220,b+315,100,30);

			home.setBounds(e,10,75,75);

			add.addActionListener(this);
			updat.addActionListener(this);
			find.addActionListener(this);
			clear.addActionListener(this);
			missed.addActionListener(this);
			available.addActionListener(this);
			multiple.addActionListener(this);
			found.addActionListener(this);
			home.addActionListener(this);
					
		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(this, e, "Exception", JOptionPane.WARNING_MESSAGE,lionimg);
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae4)
	{
		String s=ae4.getActionCommand();
		if(s=="Add")
		{
			try{	
				String s1="";
				if(s1.equals(id.getText())||s1.equals(no.getText())||s1.equals(author.getText())||s1.equals(pub.getText())||s1.equals(edi.getText())||s1.equals(pri.getText()))
				{
					JOptionPane.showMessageDialog(this,"Some fields have null value", "Error", JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				else
				{
					String YES=new String("YES");
					String cse=new String("CSE");
					String mit=new String("MIT");
					st=con.createStatement();
					st.executeUpdate("INSERT INTO book VALUES('"+id.getText()+"','"+no.getText()+"','"+author.getText()+"','"+pub.getText()+"','"+edi.getText()+"','"+pri.getText()+"','"+YES+"','"+cse+"','"+mit+"')");
					JOptionPane.showMessageDialog(this,"Book Details Added Successfully", "Added Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);
				}			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this, e, "Exception", JOptionPane.WARNING_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
		if(s=="Update")
		{
			try{
				String s1="";
				if(s1.equals(id.getText())||s1.equals(no.getText())||s1.equals(author.getText())||s1.equals(pub.getText())||s1.equals(edi.getText())||s1.equals(pri.getText()))
				{
					JOptionPane.showMessageDialog(this,"Some fields have null value", "Added Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				else
				{
					st=con.createStatement();
					st.executeUpdate("UPDATE book SET bid='"+id.getText()+"',author='"+author.getText()+"',publication='"+pub.getText()+"',edition='"+edi.getText()+"',price='"+pri.getText()+"' WHERE bno='"+no.getText()+"'");
					JOptionPane.showMessageDialog(this,"Book Details Updated Successfully", "Updated Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);
				}			
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
		if(s=="Find")
		{
			try{
				st=con.createStatement();
				rs=st.executeQuery("SELECT * FROM book WHERE bno='"+no.getText()+"'");
				while(rs.next())
				{			
					id.setText(rs.getString(1));
					author.setText(rs.getString(3));
					pub.setText(rs.getString(4));
					edi.setText(rs.getString(5));
					pri.setText(rs.getString(6));
				}
			}
			catch(Exception e)
			{
				System.out.println(e);

			}
		}
		if(s=="Clear")
		{
			id.setText("");
			no.setText("");
			author.setText("");
			pub.setText("");
			edi.setText("");
			pri.setText("");
		}
		if(s=="Missed")
		{
			try{	
				String s1="YES";
				String s2="";
				st=con.createStatement();
				rs=st.executeQuery("select  available from book where bno='"+no.getText()+"'");
				while(rs.next())
				{
					s2=rs.getString(1);
				}
				int res =JOptionPane.showConfirmDialog(this,"Are you sure whether the book Missed ", "Missed Book", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(res==JOptionPane.YES_OPTION)
				{
					if(s1.equals(s2))
					{
						String miss=new String("MISSED");
						st=con.createStatement();
						st.executeUpdate("update book set available='"+miss+"' where bno='"+no.getText()+"'");
						JOptionPane.showMessageDialog(this,"Book status changed to Missed","Status Changed",JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Sorry,the book is not avail database", "Missed Book", JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
		if(s=="Multiple")
		{
			try
			{
				String k="";
				String s1="";
				String s2="null";
				int x=Integer.parseInt(nob.getText());
				for(int i=1;i<=x;i++)
				{
					k=JOptionPane.showInputDialog("Enter the Book no");
					if(s1.equals(id.getText())||s1.equals(k)||s2.equals(k)||s1.equals(author.getText())||s1.equals(pub.getText())||s1.equals(edi.getText())||s1.equals(pri.getText()))
					{
						JOptionPane.showMessageDialog(this,"Null value not allowed", "Error", JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
					else
					{
						st=con.createStatement();
						st.executeUpdate("insert into book values('"+id.getText()+"','"+k+"','"+author.getText()+"','"+pub.getText()+"','"+edi.getText()+"','"+pri.getText()+"','YES','CSE','MIT')");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,e,"Some problem Occured",JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		}
		if(s=="Found")
		{
			try{	
				String s1="MISSED";
				String s2="";
				st=con.createStatement();
				rs=st.executeQuery("select  available from book where bno='"+no.getText()+"'");
				while(rs.next())
				{
					s2=rs.getString(1);
				}
				int z=0;
				int n = JOptionPane.showConfirmDialog(null,"Would you like to change the status to Yes?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
				if(z==n)
				{
					if(s1.equals(s2))
					{
						Statement st;
						String b=new String("YES");
						st=con.createStatement();
						st.executeUpdate("update book set available='"+b+"' where bno='"+no.getText()+"'");
						JOptionPane.showMessageDialog(this,"Book status changed to Yes","Status Change",JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Sorry,the book is not a missed book", "Book Found", JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
				}
				else
				{
					System.out.println("Action incomplete");
				}
				st.close();
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,"Book is already available ","Aleady available",JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		
		}
		if(s=="Home")
		{
			Home h=new Home();
			dispose();
		}
		if(s=="Available")
		{
			BookAvail bobj=new BookAvail();
		}	
		
	}
	
}

//-------------------------------------------------------------------------------------------------------------------------------
class Transaction extends JFrame implements ActionListener,FocusListener
{
	Connection con;
	PreparedStatement stmt;
	Statement st,st1,st2;
	ResultSet rs;
	
	JPanel jp;
	JCheckBox b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12;
	JLabel regl,ps1,ps2,book1l,book2l,book3l,book4l,book5l,book6l,book7l,book8l,book9l,book10l,book11l,book12l,border1,border2,bookl,booknol;
	JTextField reg,book1,book2,book3,book4,book5,book6,book7,book8,book9,book10,book11,book12,bookno,name,b1l,b2l,b3l,b4l,b5l,b6l,b7l,b8l,b9l,b10l,b11l,b12l;
	JButton Issue,Return,Find,Print,BookAvail,Clear,home,Remove,findb;
	JList jl;
	Dimension d;
	Image img;
	ImageIcon imgicon,imgicon1,imgicon2,lionimg;
		
	int a,b,c,e,f;
	
	Transaction()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);
					
			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);			
			setTitle("Transaction Form");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(jp);
			
			regl=new JLabel("Register Number");
			book1l=new JLabel("B01 ");
			book2l=new JLabel("B02");
			book3l=new JLabel("B03");
			book4l=new JLabel("B04");
			book5l=new JLabel("B05");
			book6l=new JLabel("B06");
			book7l=new JLabel("B07");
			book8l=new JLabel("B08");
			book9l=new JLabel("B09");
			book10l=new JLabel("B10");
			book11l=new JLabel("B11");
			book12l=new JLabel("B12");
			booknol=new JLabel("Book Number");
			bookl=new JLabel();
			border1=new JLabel();
			border2=new JLabel();
			ps1=new JLabel("Book Id");
			ps2=new JLabel("Book No");
			
			reg=new JTextField(20);
			name=new JTextField(20);
			book1=new JTextField(10);
			book2=new JTextField(10);
			book3=new JTextField(10);
			book4=new JTextField(10);
			book5=new JTextField(10);
			book6=new JTextField(10);
			book7=new JTextField(10);
			book8=new JTextField(10);
			book9=new JTextField(10);
			book10=new JTextField(10);
			book11=new JTextField(10);
			book12=new JTextField(10);
			bookno=new JTextField(10);
			b1l=new JTextField(10);
			b2l=new JTextField(10);
			b3l=new JTextField(10);
			b4l=new JTextField(10);
			b5l=new JTextField(10);
			b6l=new JTextField(10);
			b7l=new JTextField(10);
			b8l=new JTextField(10);
			b9l=new JTextField(10);
			b10l=new JTextField(10);
			b11l=new JTextField(10);
			b12l=new JTextField(10);
			
			
			b1=new JCheckBox();
			b2=new JCheckBox();
			b3=new JCheckBox();
			b4=new JCheckBox();
			b5=new JCheckBox();
			b6=new JCheckBox();
			b7=new JCheckBox();
			b8=new JCheckBox();
			b9=new JCheckBox();
			b10=new JCheckBox();
			b11=new JCheckBox();
			b12=new JCheckBox();
			
			Issue=new JButton("Issue");
			Return=new JButton("Return");
			Find=new JButton(new ImageIcon("find.png"));
			findb=new JButton(new ImageIcon("find.png"));
			Print=new JButton("Print");
			BookAvail=new JButton("Available");
			Clear=new JButton("Clear");
			Remove=new JButton("Remove");
			home=new JButton(new ImageIcon("Home.jpg"));
			
			imgicon=new ImageIcon(Transaction.class.getResource("Book.gif"));
			bookl.setIcon(imgicon);
			imgicon1=new ImageIcon(Transaction.class.getResource("border1.jpg"));
			border1.setIcon(imgicon1);
			imgicon2=new ImageIcon(Transaction.class.getResource("border2.jpg"));
			border2.setIcon(imgicon2);
			lionimg=new ImageIcon(Transaction.class.getResource("lion.gif"));

			home.setActionCommand("Home");
			home.setToolTipText("Go to Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false); 
			Find.setActionCommand("Find");
			Find.setToolTipText("Find");
			Find.setOpaque(false);
			Find.setContentAreaFilled(false);
			Find.setBorderPainted(false); 
			findb.setActionCommand("FindB");
			findb.setToolTipText("Find");
			findb.setOpaque(false);
			findb.setContentAreaFilled(false);
			findb.setBorderPainted(false); 
			jp.getRootPane().setDefaultButton(Find);

			jp.add(regl);
			jp.add(book1l);
			jp.add(book2l);
			jp.add(book3l);
			jp.add(book4l);
			jp.add(book5l);
			jp.add(book6l);
			jp.add(book7l);
			jp.add(book8l);
			jp.add(book9l);
			jp.add(book10l);
			jp.add(book11l);
			jp.add(book12l);
			jp.add(booknol);
			jp.add(reg);
			jp.add(book1);
			jp.add(book2);
			jp.add(book3);
			jp.add(book4);
			jp.add(book5);
			jp.add(book6);
			jp.add(book7);
			jp.add(book8);
			jp.add(book9);
			jp.add(book10);
			jp.add(book11);
			jp.add(book12);
			jp.add(bookno);
			jp.add(name);
			jp.add(b1l);
			jp.add(b2l);
			jp.add(b3l);
			jp.add(b4l);
			jp.add(b5l);
			jp.add(b6l);
			jp.add(b7l);
			jp.add(b8l);
			jp.add(b9l);
			jp.add(b10l);
			jp.add(b11l);
			jp.add(b12l);
			jp.add(ps1);
			jp.add(ps2);		

			jp.add(b1);
			jp.add(b2);
			jp.add(b3);
			jp.add(b4);
			jp.add(b5);
			jp.add(b6);
			jp.add(b7);
			jp.add(b8);
			jp.add(b9);
			jp.add(b10);
			jp.add(b11);
			jp.add(b12);
			
			jp.add(Issue);
			jp.add(Return);
			jp.add(Find);
			jp.add(findb);
			jp.add(Print);
			jp.add(BookAvail);
			jp.add(Clear);
			jp.add(Remove);
			jp.add(home);
			jp.add(bookl);
			jp.add(border1);
			jp.add(border2);
		
			a=(d.width-170)/2;
			b=(d.height-500)/2;
			c=a-260;
			e=(d.width-320)/2;
			f=(d.height-544)/2;
					
			
			regl.setBounds(c,b,100,20);
			reg.setBounds(c+100,b,100,20);
			name.setBounds(a+200,b,150,20);
			Find.setBounds(c+200,b-5,30,30);
			booknol.setBounds(c,b+270,100,20);
			bookno.setBounds(c+100,b+270,100,20);
			findb.setBounds(c+200,b+265,30,30);
			ps1.setBounds(a+8,b-30,50,20);
			ps2.setBounds(a+75,b-30,50,20);
			book1l.setBounds(a-20,b,30,20);
			b1l.setBounds(a+5,b,50,20);
			book1.setBounds(a+60,b,80,20);
			b1.setBounds(a+150,b,20,20);
			book2l.setBounds(a-20,b+30,30,20);
			b2l.setBounds(a+5,b+30,50,20);
			book2.setBounds(a+60,b+30,80,20);
			b2.setBounds(a+150,b+30,20,20);
			book3l.setBounds(a-20,b+60,30,20);
			b3l.setBounds(a+5,b+60,50,20);
			book3.setBounds(a+60,b+60,80,20);
			b3.setBounds(a+150,b+60,20,20);
			book4l.setBounds(a-20,b+90,30,20);
			b4l.setBounds(a+5,b+90,50,20);
			book4.setBounds(a+60,b+90,80,20);
			b4.setBounds(a+150,b+90,20,20);
			book5l.setBounds(a-20,b+120,30,20);
			b5l.setBounds(a+5,b+120,50,20);
			book5.setBounds(a+60,b+120,80,20);
			b5.setBounds(a+150,b+120,20,20);
			book6l.setBounds(a-20,b+150,30,20);
			b6l.setBounds(a+5,b+150,50,20);
			book6.setBounds(a+60,b+150,80,20);
			b6.setBounds(a+150,b+150,20,20);
			book7l.setBounds(a-20,b+180,30,20);
			b7l.setBounds(a+5,b+180,50,20);
			book7.setBounds(a+60,b+180,80,20);
			b7.setBounds(a+150,b+180,20,20);
			book8l.setBounds(a-20,b+210,30,20);
			b8l.setBounds(a+5,b+210,50,20);
			book8.setBounds(a+60,b+210,80,20);
			b8.setBounds(a+150,b+210,20,20);	
			book9l.setBounds(a-20,b+240,30,20);
			b9l.setBounds(a+5,b+240,50,20);
			book9.setBounds(a+60,b+240,80,20);
			b9.setBounds(a+150,b+240,20,20);
			book10l.setBounds(a-20,b+270,30,20);
			b10l.setBounds(a+5,b+270,50,20);
			book10.setBounds(a+60,b+270,80,20);
			b10.setBounds(a+150,b+270,20,20);
			book11l.setBounds(a-20,b+300,50,20);
			b11l.setBounds(a+5,b+300,50,20);
			book11.setBounds(a+60,b+300,80,20);
			b11.setBounds(a+150,b+300,20,20);
			book12l.setBounds(a-20,b+330,30,20);
			b12l.setBounds(a+5,b+330,50,20);
			book12.setBounds(a+60,b+330,80,20);
			b12.setBounds(a+150,b+330,20,20);
		

			Issue.setBounds(e,b+370,100,30);
			Return.setBounds(e+110,b+370,100,30);
			Clear.setBounds(e+220,b+370,100,30);
			BookAvail.setBounds(e,b+410,100,30);
			Remove.setBounds(e+110,b+410,100,30);
			Print.setBounds(e+220,b+410,100,30);
			home.setBounds(d.width-100,10,75,75);
			
			bookl.setBounds(10,f,300,244);
			border1.setBounds(0,d.height-380,350,380);
			border2.setBounds(d.width-350,d.height-380,350,380);
			
			Issue.addActionListener(this);
			Return.addActionListener(this);
			Find.addActionListener(this);
			findb.addActionListener(this);
			Print.addActionListener(this);
			BookAvail.addActionListener(this);
			Clear.addActionListener(this);
			Remove.addActionListener(this);
			home.addActionListener(this);
			Find.addFocusListener(this);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	public void focusGained(FocusEvent fe1)
	{
		int k;
		try
		{
			String b,c,d,e,f,g,h,i,j,kj,l,q,z;
			
			String a;
			String batch="";
			int n;
			String qf="";
			a=reg.getText();
			String str="SELECT * FROM  student WHERE regno='"+ a +"'";
			st=con.createStatement();
			rs=st.executeQuery(str);
			while(rs.next())
			{
				z=rs.getString(2);
				b=rs.getString(5);
				c=rs.getString(6);
				d=rs.getString(7);
				e=rs.getString(8);
				f=rs.getString(9);
				g=rs.getString(10);
				h=rs.getString(11);
				i=rs.getString(12);
				j=rs.getString(13);
				kj=rs.getString(14);
				l=rs.getString(15);
				q=rs.getString(16);
				batch=rs.getString(3);
				book1.setText(b);
				book2.setText(c);
				book3.setText(d);
				book4.setText(e);
				book5.setText(f);
				book6.setText(g);
				book7.setText(h);
				book8.setText(i);
				book9.setText(j);
				book10.setText(kj);
				book11.setText(l);
				book12.setText(q);
				name.setText(z);
			}
			/*if(qf.equals(batch))
			{
				JOptionPane.showMessageDialog(this,"Register Number is not Found in Database","Regno Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
			}*/
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	
	public void focusLost(FocusEvent kdk)
	{
	}
		
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		if(s=="Issue")
		{
			String que;
			PreparedStatement stmt2;
			Statement st;
			try
			{	
				String a,b,c,d,e,f,g,h,i,j,k,l,q;
				String section="";
				String batch="";
				String b1="";
				String b2="";
				String b3="";
				String b4="";
				String b5="";
				String b6="";
				String b7="";
				String b8="";
				String b9="";
				String b10="";
				String b11="";
				String b12="";
				String m="YES";
				String o="NO";
				String r="MISSED";
				String p="";
				String NO=new String("NO");
				
				a=reg.getText();
				b=book1.getText();
				c=book2.getText();
				d=book3.getText();
				e=book4.getText();
				f=book5.getText();
				g=book6.getText();
				h=book7.getText();
				i=book8.getText();
				j=book9.getText();
				k=book10.getText();
				l=book11.getText();
				q=book12.getText();
				
				
				String str1="select batch from student where regno='"+a+"'";
				st=con.createStatement();
				rs=st.executeQuery(str1);
				while(rs.next())
				{
					batch=rs.getString(1);
				}
				
				String str2="select section from student where regno='"+a+"'";
				rs=st.executeQuery(str2);
				st1=con.createStatement();
				while(rs.next())
				{
					section=rs.getString(1);
				}

				st=con.createStatement();
				rs=st.executeQuery("select available from book where bno='"+b+"'");
				while(rs.next())
				{
					b1=rs.getString(1);
				}
				st=con.createStatement();
							
				
				if(p.equals(batch))
				{
					JOptionPane.showMessageDialog(this,"Register Number is  not found in the Database","Regno not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				else
				{
							
					if(p.equals(b1))
					{
						if(p.equals(b))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book1 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b1))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book1='"+b+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+b+"'");
							JOptionPane.showMessageDialog(this,"Book1 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b1)||r.equals(b1))
						{
							JOptionPane.showMessageDialog(this,"Book1 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					
					}
					
				
					rs=st.executeQuery("select available from book where bno='"+c+"'");
					while(rs.next())
					{
						b2=rs.getString(1);
					}				
					if(p.equals(b2))
					{
						if(p.equals(c))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book2 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b2))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book2='"+c+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+c+"'");
							JOptionPane.showMessageDialog(this,"Book2 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b2)||r.equals(b2))
						{
							JOptionPane.showMessageDialog(this,"Book2 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
	
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+d+"'");
					while(rs.next())
					{
						b3=rs.getString(1);
					}
					if(p.equals(b3))
					{
						if(p.equals(d))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book3 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else	
					{				
						if(m.equals(b3))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book3='"+d+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+d+"'");
							JOptionPane.showMessageDialog(this,"Book3 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b3)||r.equals(b3))
						{
							JOptionPane.showMessageDialog(this,"Book3 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+e+"'");
					while(rs.next())
					{	
						b4=rs.getString(1);
					}
					

					if(p.equals(b4))
					{
						if(p.equals(e))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book4 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b4))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book4='"+e+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+e+"'");
							JOptionPane.showMessageDialog(this,"Book4 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b4)||r.equals(b4))
						{
							JOptionPane.showMessageDialog(this,"Book4 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
	
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+f+"'");
					while(rs.next())
					{
						b5=rs.getString(1);
					}		
					
					if(p.equals(b5))
					{
						if(p.equals(f))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book5 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b5))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book5='"+f+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+f+"'");
							JOptionPane.showMessageDialog(this,"Book5 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b5)||r.equals(b5))
						{
							JOptionPane.showMessageDialog(this,"Book5 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}
	
				
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+g+"'");
					while(rs.next())
					{
						b6=rs.getString(1);
					}
									
					if(p.equals(b6))
					{
						if(p.equals(g))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book6 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else	
					{
						if(m.equals(b6))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book6='"+g+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+g+"'");
							JOptionPane.showMessageDialog(this,"Book6 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b6)||r.equals(b6))
						{
							JOptionPane.showMessageDialog(this,"Book6 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}

					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+h+"'");
					while(rs.next())
					{
						b7=rs.getString(1);
					}	
					
					if(p.equals(b7))
					{
						if(p.equals(h))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book7 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b7))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book7='"+h+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+h+"'");
							JOptionPane.showMessageDialog(this,"Book7 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b7)||r.equals(b7))
						{
							JOptionPane.showMessageDialog(this,"Book7 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
				
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+i+"'");
					while(rs.next())
					{
						b8=rs.getString(1);
					}
					

					if(p.equals(b8))
					{
						if(p.equals(i))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book8 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b8))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book8='"+i+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+i+"'");
							JOptionPane.showMessageDialog(this,"Book8 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b8)||r.equals(b8))
						{
							JOptionPane.showMessageDialog(this,"Book8 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+j+"'");
					while(rs.next())
					{
						b9=rs.getString(1);
					}
									
					if(p.equals(b9))
					{
						if(p.equals(j))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book9 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b9))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book9='"+j+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+j+"'");
							JOptionPane.showMessageDialog(this,"Book9 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b9)||r.equals(b9))
						{
							JOptionPane.showMessageDialog(this,"Book9 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+k+"'");
					while(rs.next())
					{
						b10=rs.getString(1);
					}	
									
					if(p.equals(b10))
					{
						if(p.equals(k))
						{}
						else	
						{
							JOptionPane.showMessageDialog(this,"Book10 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else	
					{
						if(m.equals(b10))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book10='"+k+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+k+"'");
							JOptionPane.showMessageDialog(this,"Book10 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b10)||r.equals(b10))
						{
							JOptionPane.showMessageDialog(this,"Book10 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}
				
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+l+"'");
					while(rs.next())
					{	
						b11=rs.getString(1);
					}
				
					if(p.equals(b11))
					{
						if(p.equals(l))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book11 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}	
					else
					{
						if(m.equals(b11))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book11='"+l+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+l+"'");
							JOptionPane.showMessageDialog(this,"Book11 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b11)||r.equals(b11))
						{
							JOptionPane.showMessageDialog(this,"Book11 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}	
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+q+"'");
					while(rs.next())
					{
						b12=rs.getString(1);
					}
					
					if(p.equals(b12))
					{
						if(p.equals(q))
						{}
						else	
						{
							JOptionPane.showMessageDialog(this,"Book12 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{	
						if(m.equals(b12))
						{
							st1=con.createStatement();
							st1.executeUpdate("update student set book12='"+q+"' where regno='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+q+"'");
							JOptionPane.showMessageDialog(this,"Book12 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b12)||r.equals(b12))
						{
							JOptionPane.showMessageDialog(this,"Book12 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,"This regno is already  available","Already available",JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		}
		else if(s=="Home")
		{
			Home obj=new Home();
			setVisible(false);
		}

		else if(s=="FindB")
		{
			try{	
				String s1="";
				String s2=bookno.getText();
				String s3="YES";
				String s5="IN OUR DATABASE";
				String s6="";
				String s7="Faculty";
				st=con.createStatement();
				rs=st.executeQuery("SELECT bno FROM book WHERE bno='"+bookno.getText()+"' and available='"+s3+"'");
				while(rs.next())
				{			
					s1=rs.getString(1);	
				}
					
				if(s1.equals(s2))
				{
					name.setText(s5);
				}
				else
				{
					st=con.createStatement();
					rs=st.executeQuery("SELECT regno,name FROM student WHERE book1='"+bookno.getText()+"' or book2='"+bookno.getText()+"' or book3='"+bookno.getText()+"' or book4='"+bookno.getText()+"' or book5='"+bookno.getText()+"' or book6='"+bookno.getText()+"' or book7='"+bookno.getText()+"' or book8='"+bookno.getText()+"' or book9='"+bookno.getText()+"' or book10='"+bookno.getText()+"' or book11='"+bookno.getText()+"' or book12='"+bookno.getText()+"'");
					while(rs.next())
					{			
					s1=rs.getString(1);
					s5=rs.getString(2);		
					}
					name.setText(s5);
					reg.setText(s1);
					if(s1.equals(s6))
					{		
						rs=st.executeQuery("SELECT dept FROM lend WHERE bno='"+bookno.getText()+"'");
						while(rs.next())
						{			
						s1=rs.getString(1);	
						}
						if(!s1.equals(s6))
						{
						name.setText(s1);
						}
						else {
						rs=st.executeQuery("SELECT sid,sname FROM staff WHERE book1='"+bookno.getText()+"' or book2='"+bookno.getText()+"' or book3='"+bookno.getText()+"' or book4='"+bookno.getText()+"' or book5='"+bookno.getText()+"'");
						while(rs.next())
						{			
						s1=rs.getString(1);
						s5=rs.getString(2);		
						}
						name.setText(s5);
						reg.setText(s7+s1);
						}
					}
			}	
			}
			catch(Exception e){System.out.println(e);}
		}
			
		else if(s=="Remove")
		{
			try{
				Statement st1,st2;
				String a;
				int z=0;
				int n = JOptionPane.showConfirmDialog(null,"Would you like to remove this student?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
				if(z==n)		
				{
					String b1="";
					String b2="";
					String b3="";	
					String b4="";
					String b5="";
					String b6="";
					String b7="";
					String b8="";
					String b9="";
					String b10="";
					String b11="";
					String b12="";
					String p="";
					String q="0";
					a=reg.getText();
					
					st1=con.createStatement();
					rs=st1.executeQuery("SELECT book1,book2,book3,book4,book5,book6,book7,book8,book9,book10,book11,book12 FROM student where regno='"+a+"'");
					while(rs.next())
					{
						b1=rs.getString(1);
						b2=rs.getString(2);
						b3=rs.getString(3);
						b4=rs.getString(4);
						b5=rs.getString(5);
						b6=rs.getString(6);
						b7=rs.getString(7);
						b8=rs.getString(8);
						b9=rs.getString(9);
						b10=rs.getString(10);
						b11=rs.getString(11);
						b12=rs.getString(12);
					}	
				
							
					if(p.equals(b1)||q.equals(b1))
					{
						if(p.equals(b2)||q.equals(b2))
						{
							if(p.equals(b3)||q.equals(b3))
							{
								if(p.equals(b4)||q.equals(b4))
								{
									if(p.equals(b5)||q.equals(b5))
									{
										if(p.equals(b6)||q.equals(b6))
										{
											if(p.equals(b7)||q.equals(b7))
											{
												if(p.equals(b8)||q.equals(b8))
												{
													if(p.equals(b9)||q.equals(b9))
													{
														if(p.equals(b10)||q.equals(b10))
														{
															if(p.equals(b11)||q.equals(b11))
															{
																if(p.equals(b12)||q.equals(b12))
																{
																	st2=con.createStatement();
																	st2.executeUpdate("delete * from student where regno='"+a+"'");
																}
																else	
																{
																	JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
																}
															}
															else
															{
																JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
															}
														}	
														else
														{
															JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
														}
													}	
													else
													{
														JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
													}
												}
												else
												{
													JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
												}
											}
											else
											{
												JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
											}
										}
										else
										{
											JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
										}
							
									}
									else
									{
										JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
									}
								}
								else
								{	
									JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
							}
						}		
						else
						{
							JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"You Have Cancelled"," Action incomplete",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}	
			}
			catch(Exception e)
			{	
				System.out.println(e);
			}
		}
		else if(s=="Find")
		{	
			int k;
			try
			{	

				String a,c,d,e,f,g,h,i,j,kj,l,q,z;
				String b1="";
				String b2="";
				String b3="";
				String b4="";
				String b5="";
				String b6="";
				String b7="";
				String b8="";
				String b9="";
				String b10="";
				String b11="";
				String b12="";
				String b="";
				String batch="";
				int n;
				String qf="";
				a=reg.getText();
				String str="SELECT * FROM  student WHERE regno='"+ a +"'";
				st=con.createStatement();
				rs=st.executeQuery(str);
				while(rs.next())
				{
					z=rs.getString(2);
					b=rs.getString(5);
					c=rs.getString(6);
					d=rs.getString(7);
					e=rs.getString(8);
					f=rs.getString(9);
					g=rs.getString(10);
					h=rs.getString(11);
					i=rs.getString(12);
					j=rs.getString(13);
					kj=rs.getString(14);
					l=rs.getString(15);
					q=rs.getString(16);
					batch=rs.getString(3);
					
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+b+"'");
					while(rs.next())
					{
					b1=rs.getString(1);
					b1l.setText(b1);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+c+"'");
					while(rs.next())
					{
					b2=rs.getString(1);
					b2l.setText(b2);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+d+"'");
					while(rs.next())
					{
					b3=rs.getString(1);
					b3l.setText(b3);
					}
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+e+"'");
					while(rs.next())
					{
					b4=rs.getString(1);
					b4l.setText(b4);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+f+"'");
					while(rs.next())
					{
					b5=rs.getString(1);
					b5l.setText(b5);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+g+"'");
					while(rs.next())
					{
					b6=rs.getString(1);
					b6l.setText(b6);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+h+"'");
					while(rs.next())
					{
					b7=rs.getString(1);
					b7l.setText(b7);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+i+"'");
					while(rs.next())
					{
					b8=rs.getString(1);
					b8l.setText(b8);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+j+"'");
					while(rs.next())
					{
					b9=rs.getString(1);
					b9l.setText(b9);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+kj+"'");
					while(rs.next())
					{
					b10=rs.getString(1);
					b10l.setText(b10);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+l+"'");
					while(rs.next())
					{
					b11=rs.getString(1);
					b11l.setText(b11);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+q+"'");
					while(rs.next())
					{
					b12=rs.getString(1);
					b12l.setText(b12);
					}		
					
					
					/*book1.setText(b);
					book2.setText(c);
					book3.setText(d);
					book4.setText(e);
					book5.setText(f);
					book6.setText(g);
					book7.setText(h);
					book8.setText(i);
					book9.setText(j);
					book10.setText(kj);
					book11.setText(l);
					book12.setText(q);*/
					if(b.equals("0"))
						book1.setText("");
					else
						book1.setText(""+b);
					if(c.equals("0"))
						book2.setText("");
					else
						book2.setText(""+c);
					if(d.equals("0"))
						book3.setText("");
					else
						book3.setText(""+d);
					if(e.equals("0"))
						book4.setText("");
					else
						book4.setText(""+e);
					if(f.equals("0"))
						book5.setText("");
					else
						book5.setText(""+f);
					if(g.equals("0"))
						book6.setText("");
					else
						book6.setText(""+g);
					if(h.equals("0"))
						book7.setText("");
					else
						book7.setText(""+h);
					if(i.equals("0"))
						book8.setText("");
					else
						book8.setText(""+i);
					if(j.equals("0"))
						book9.setText("");
					else
						book9.setText(""+j);
					if(kj.equals("0"))
						book10.setText("");
					else
						book10.setText(""+kj);
					if(l.equals("0"))
						book11.setText("");
					else
						book11.setText(""+l);
					if(q.equals("0"))
						book12.setText("");
					else
						book12.setText(""+q);
					
					name.setText(z);
			
						
		
				}
					
					
					
				if(qf.equals(batch))
				{
					JOptionPane.showMessageDialog(this,"Register Number is not Found in Database","Regno Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Return")
		{
			try{
				String a,b,c,d,e,f,g,h,i,j,k,l,q;
				String YES=new String("YES");
				Statement st2;
				a=reg.getText();
				b=book1.getText();
				c=book2.getText();
				d=book3.getText();
				e=book4.getText();
				f=book5.getText();
				g=book6.getText();
				h=book7.getText();
				i=book8.getText();
				j=book9.getText();
				k=book10.getText();
				l=book11.getText();
				q=book12.getText();
				
				if(b1.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+b+"'");
					b="0";
					JOptionPane.showMessageDialog(this,"Book1 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b2.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+c+"'");
					c="0";
					JOptionPane.showMessageDialog(this,"Book2 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);					
				}
				if(b3.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+d+"'");
					d="0";
					JOptionPane.showMessageDialog(this,"Book3 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b4.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+e+"'");
					e="0";
					JOptionPane.showMessageDialog(this,"Book4 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b5.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+f+"'");
					f="0";
					JOptionPane.showMessageDialog(this,"Book5 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b6.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+g+"'");	
					g="0";
					JOptionPane.showMessageDialog(this,"Book6 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b7.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+h+"'");
					h="0";
					JOptionPane.showMessageDialog(this,"Book7 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b8.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+i+"'");
					i="0";
					JOptionPane.showMessageDialog(this,"Book8 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b9.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+j+"'");
					j="0";
					JOptionPane.showMessageDialog(this,"Book9 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b10.isSelected()==true)
				{	st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+k+"'");
					k="0";
					JOptionPane.showMessageDialog(this,"Book10 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b11.isSelected()==true)
				{	st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+l+"'");
					l="0";
					JOptionPane.showMessageDialog(this,"Book11 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b12.isSelected()==true)
				{	st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+q+"'");
					q="0";
					JOptionPane.showMessageDialog(this,"Book12 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				String sstr="update student set book1=?,book2=?,book3=?,book4=?,book5=?,book6=?,book7=?,book8=?,book9=?,book10=?,book11=?,book12=? where regno='"+a+"' ";
				PreparedStatement stmt=con.prepareStatement(sstr);
				stmt.setString(1,b);
				stmt.setString(2,c);
				stmt.setString(3,d);
				stmt.setString(4,e);
				stmt.setString(5,f);
				stmt.setString(6,g);
				stmt.setString(7,h);
				stmt.setString(8,i);
				stmt.setString(9,j);
				stmt.setString(10,k);
				stmt.setString(11,l);
				stmt.setString(12,q);
				stmt.executeUpdate();
			
				String be1,c1,d1,e1,f1,g1,h1,i1,j1,kj1,l1,m1,z1;
				
				String a1;
				String batch="";
				int n;
				String qf="";
				a1=reg.getText();
				String str="SELECT * FROM  student WHERE regno='"+ a1 +"'";
				st=con.createStatement();
				rs=st.executeQuery(str);
				while(rs.next())
				{
					z1=rs.getString(2);
					be1=rs.getString(5);
					c1=rs.getString(6);
					d1=rs.getString(7);
					e1=rs.getString(8);
					f1=rs.getString(9);
					g1=rs.getString(10);
					h1=rs.getString(11);
					i1=rs.getString(12);
					j1=rs.getString(13);
					kj1=rs.getString(14);
					l1=rs.getString(15);
					m1=rs.getString(16);
					batch=rs.getString(3);
					
				String bo1="";
				String bo2="";
				String bo3="";
				String bo4="";
				String bo5="";
				String bo6="";
				String bo7="";
				String bo8="";
				String bo9="";
				String bo10="";
				String bo11="";
				String bo12="";
				
					
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+be1+"'");
					while(rs.next())
					{
					bo1=rs.getString(1);
					b1l.setText(bo1);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+c1+"'");
					while(rs.next())
					{
					bo2=rs.getString(1);
					b2l.setText(bo2);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+d1+"'");
					while(rs.next())
					{
					bo3=rs.getString(1);
					b3l.setText(bo3);
					}
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+e1+"'");
					while(rs.next())
					{
					bo4=rs.getString(1);
					b4l.setText(bo4);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+f1+"'");
					while(rs.next())
					{
					bo5=rs.getString(1);
					b5l.setText(bo5);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+g1+"'");
					while(rs.next())
					{
					bo6=rs.getString(1);
					b6l.setText(bo6);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+h1+"'");
					while(rs.next())
					{
					bo7=rs.getString(1);
					b7l.setText(bo7);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+i1+"'");
					while(rs.next())
					{
					bo8=rs.getString(1);
					b8l.setText(bo8);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+j1+"'");
					while(rs.next())
					{
					bo9=rs.getString(1);
					b9l.setText(bo9);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+kj1+"'");
					while(rs.next())
					{
					bo10=rs.getString(1);
					b10l.setText(bo10);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+l1+"'");
					while(rs.next())
					{
					bo11=rs.getString(1);
					b11l.setText(bo11);
					}	
					st=con.createStatement();
					rs=st.executeQuery("select bid  from book where bno='"+m1+"'");
					while(rs.next())
					{
					bo12=rs.getString(1);
					b12l.setText(bo12);
					}	
					/*book1.setText(be1);
					book2.setText(c1);
					book3.setText(d1);
					book4.setText(e1);
					book5.setText(f1);
					book6.setText(g1);
					book7.setText(h1);
					book8.setText(i1);
					book9.setText(j1);
					book10.setText(kj1);
					book11.setText(l1);
					book12.setText(m1);*/
					
				
				
				
				
				
				
				
					if(be1.equals("0"))
						{book1.setText("");b1l.setText("");}
					else
						book1.setText(""+be1);
					if(c1.equals("0"))
						{book2.setText("");b2l.setText("");}
					else
						book2.setText(""+c1);
					if(d1.equals("0"))
						{book3.setText("");b3l.setText("");}
					else
						book3.setText(""+d1);
					if(e1.equals("0"))
						{book4.setText("");b4l.setText("");}
					else
						book4.setText(""+e1);
					if(f1.equals("0"))
						{book5.setText("");b5l.setText("");}
					else
						book5.setText(""+f1);
					if(g1.equals("0"))
						{book6.setText("");b6l.setText("");}
					else
						book6.setText(""+g1);
					if(h.equals("0"))
						{book7.setText("");b7l.setText("");}
					else
						book7.setText(""+h);
					if(i1.equals("0"))
						{book8.setText("");b8l.setText("");}
				
					else
						book8.setText(""+i1);
					if(j1.equals("0"))
						{book9.setText("");b9l.setText("");}
				
					else
						book9.setText(""+j1);
					if(kj1.equals("0"))
						{book10.setText("");b10l.setText("");}
				
					else
						book10.setText(""+kj1);
					if(l1.equals("0"))
						{book11.setText("");b11l.setText("");}
				
					else
						book11.setText(""+l1);
					if(m1.equals("0"))
						{book12.setText("");b12l.setText("");}
					else
						book12.setText(""+m1);
					
					name.setText(z1);
				}
				b1.setSelected(false);
				b2.setSelected(false);
				b3.setSelected(false);
				b4.setSelected(false);
				b5.setSelected(false);
				b6.setSelected(false);
				b7.setSelected(false);
				b8.setSelected(false);
				b9.setSelected(false);
				b10.setSelected(false);
				b11.setSelected(false);
				b12.setSelected(false);
				reg.requestFocus(); 		
		
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Print")
		{
			new Details();
		}
		if(s=="Available")
		{
			new BookAvail();
		}
		if(s=="Clear")
		{
			try
			{
				book1.setText("");
				book2.setText("");
				book3.setText("");
				book4.setText("");
				book5.setText("");
				book6.setText("");
				book7.setText("");
				book8.setText("");
				book9.setText("");
				book10.setText("");
				book11.setText("");
				book12.setText("");
				bookno.setText("");
				b1l.setText("");
				b2l.setText("");
				b3l.setText("");
				b4l.setText("");
				b5l.setText("");
				b6l.setText("");
				b7l.setText("");
				b8l.setText("");
				b9l.setText("");
				b10l.setText("");
				b11l.setText("");
				b12l.setText("");
				bookno.setText("");
				name.setText("");
				b1.setSelected(false);
				b2.setSelected(false);
				b3.setSelected(false);
				b4.setSelected(false);
				b5.setSelected(false);
				b6.setSelected(false);
				b7.setSelected(false);
				b8.setSelected(false);
				b9.setSelected(false);
				b10.setSelected(false);
				b11.setSelected(false);
				b12.setSelected(false);
				reg.requestFocus(); 	
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
	}
	
}


//-------------------------------------------------------------------------------------------------------------------------------

class ChangePwd extends JFrame implements ActionListener
{
	
	JPanel jp;
	JLabel oldl,nwl,confirml;
	JPasswordField old,nw,confirm;
	JButton change,home;
	Dimension d;
	Background bg;
	Image img;
	ImageIcon lionimg;
	int a,b,c,e;
	Font ft1,ft2;
	
	Connection con;
	Statement st;
	ResultSet rs;
	

	ChangePwd()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();		
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			bg=new Background();
			jp=new JPanel();
			jp.setLayout(null);
			
			setLayout(null);
			setVisible(true);			
			a=(d.width-510)/2;
			b=(d.height-500)/2;
			setBounds(a,b,510,400);			
			setIconImage(img);			
			setTitle("Department Library Change Password");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(bg.wrapInBackgroundImage(jp,new ImageIcon(ChangePwd.class.getResource("ChBg.jpg"))));
			setResizable(false);
						
			oldl=new JLabel("Old Password");
			nwl=new JLabel("New Password");
			confirml=new JLabel("Confirm Password");
			
			old=new JPasswordField(20);
			nw=new JPasswordField(20);
			confirm=new JPasswordField(20);

			lionimg=new ImageIcon(ChangePwd.class.getResource("lion.gif"));
			
			ft1=new Font("Times New Roman",1,18); 
			ft2=new Font("Times New Roman",1,30); 
			Color c1=new Color(101,67,33);
			Color c2=new Color(101,67,33);
			
			oldl.setFont(ft1);
			nwl.setFont(ft1);
			confirml.setFont(ft1);
			
			oldl.setForeground(c1);
			nwl.setForeground(c1);
			confirml.setForeground(c1);

			old.setFont(ft2);
			nw.setFont(ft2);
			confirm.setFont(ft2);
			
			old.setForeground(c2);
			nw.setForeground(c2);
			confirm.setForeground(c2);
			
			change=new JButton("Change");
			home=new JButton("Login");
						
			jp.add(oldl);
			jp.add(nwl);
			jp.add(confirml);
			jp.add(old);
			jp.add(nw);
			jp.add(confirm);
			jp.add(change);
			jp.add(home);
			
			c=30;
			e=110;

			oldl.setBounds(c,e,150,30);
			old.setBounds(c+150,e,150,30);
			nwl.setBounds(c,e+40,150,30);
			nw.setBounds(c+150,e+40,150,30);
			confirml.setBounds(c,e+80,150,30);
			confirm.setBounds(c+150,e+80,150,30);
			change.setBounds(c,e+120,145,30);
			home.setBounds(c+155,e+120,145,30);
			
			change.addActionListener(this);
			home.addActionListener(this);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae6)
	{
		String s=ae6.getActionCommand();
		if(s=="Change")
		{
			try{
				String a,b,c;	
				String k="";
				a=old.getText().trim();
				b=nw.getText().trim();
				c=confirm.getText().trim();
				st=con.createStatement();
				rs=st.executeQuery("SELECT password FROM pass WHERE id=1");
				if(!b.equals(c))
				{
					JOptionPane.showMessageDialog(this, "Your Password And Confirm-Password Should Match", "Password Not Matched", JOptionPane.INFORMATION_MESSAGE,lionimg);
					old.setText("");
					nw.setText("");
					confirm.setText("");
				}
				else
				{
					while(rs.next())
					{
						k=rs.getString(1);	
					}
					if(a.equals(k))
					{
							st=con.createStatement();
							st.executeUpdate("UPDATE pass SET password='"+c+"' WHERE id=1");
							JOptionPane.showMessageDialog(this, "Password Has Been Changed Successfully", "Password Changed", JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Please Re-Enter Your Password", "Worng Password", JOptionPane.INFORMATION_MESSAGE,lionimg);
						old.setText("");
						nw.setText("");
						confirm.setText("");
					}
				
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Login")
		{
			Login hobj=new Login();
			dispose();
		}
	}
	
}

//------------------------------------------------------------------------------------------------------------------------------

class Faculty extends JFrame implements ActionListener,FocusListener
{
	JPanel jp;
	JLabel sidl,snamel,deptl,book1l,book2l,book3l,book4l,book5l,bookl,border1,border2;
	JTextField sid,sname,book1,book2,book3,book4,book5;
	JCheckBox b1,b2,b3,b4,b5;
	JComboBox dept;
	JButton add,issue,retun,remove,find,clear,home,details;
	Dimension d;
	Font ft1,ft2;
	Color c1,c2;
	Image img;
	ImageIcon imgicon,imgicon1,imgicon2,lionimg;
	int a,b,c,e,f;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	Faculty()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);
			
			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);			
			setTitle("Staff login");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(jp);
			
			sidl=new JLabel("Staff ID");
			snamel=new JLabel("Staff Name");
			deptl=new JLabel("Department");
			book1l=new JLabel("Book1 ");
			book2l=new JLabel("Book2");
			book3l=new JLabel("Book3");
			book4l=new JLabel("Book4");
			book5l=new JLabel("Book5");
			bookl=new JLabel();
			border1=new JLabel();
			border2=new JLabel();
			
			sid=new JTextField(20);
			sname=new JTextField(20);
			book1=new JTextField(10);
			book2=new JTextField(10);
			book3=new JTextField(10);
			book4=new JTextField(10);
			book5=new JTextField(10);
	
			dept=new JComboBox();
			dept.setEditable(true);
			dept.addItem("CSE");
			dept.addItem("IT");
			dept.addItem("CIVIL");
			dept.addItem("MECH");
			dept.addItem("ECE");
			dept.addItem("MBA");
			dept.addItem("MCA");
			dept.addItem("EEE");
			
			b1=new JCheckBox();
			b2=new JCheckBox();
			b3=new JCheckBox();
			b4=new JCheckBox();
			b5=new JCheckBox();
	
			add=new JButton("Add");
			issue=new JButton("Issue");
			retun=new JButton("Return");
			find=new JButton(new ImageIcon("find.png"));
			clear=new JButton("Clear");
			remove=new JButton("Remove");
			details=new JButton("Details");
			home=new JButton(new ImageIcon("home.jpg"));
			
			ft1=new Font("Times New Roman",1,18); 
			ft2=new Font("Times New Roman",1,18); 
			c1=new Color(0,100,0);
			c2=new Color(0,100,0);
			
			sidl.setFont(ft1);
			snamel.setFont(ft1);
			deptl.setFont(ft1);
			book1l.setFont(ft1);
			book2l.setFont(ft1);
			book3l.setFont(ft1);
			book3l.setFont(ft1);
			book4l.setFont(ft1);
			book5l.setFont(ft1);
			
			sidl.setForeground(c1);
			snamel.setForeground(c1);
			deptl.setForeground(c1);
			book1l.setForeground(c1);
			book2l.setForeground(c1);
			book3l.setForeground(c1);
			book3l.setForeground(c1);
			book4l.setForeground(c1);
			book5l.setForeground(c1);

			sid.setFont(ft2);
			sname.setFont(ft2);
			dept.setFont(ft2);
			book1.setFont(ft2);
			book2.setFont(ft2);
			book3.setFont(ft2);
			book3.setFont(ft2);
			book4.setFont(ft2);
			book5.setFont(ft2);

			sid.setForeground(c2);
			sname.setForeground(c2);
			dept.setForeground(c2);
			book1.setForeground(c2);
			book2.setForeground(c2);
			book3.setForeground(c2);
			book3.setForeground(c2);
			book4.setForeground(c2);
			book5.setForeground(c2);
			
			jp.add(sidl);
			jp.add(snamel);
			jp.add(deptl);
			jp.add(sid);
			jp.add(sname);
			jp.add(dept);
			jp.add(book1l);
			jp.add(book2l);
			jp.add(book3l);
			jp.add(book4l);
			jp.add(book5l);
			jp.add(book1);
			jp.add(book2);
			jp.add(book3);
			jp.add(book4);
			jp.add(book5);
			jp.add(b1);
			jp.add(b2);
			jp.add(b3);
			jp.add(b4);
			jp.add(b5);
			jp.add(add);
			jp.add(find);
			jp.add(issue);
			jp.add(retun);
			jp.add(remove);
			jp.add(clear);
			jp.add(home);
			jp.add(details);
			jp.add(bookl);
			jp.add(border1);
			jp.add(border2);
			
			imgicon=new ImageIcon(Faculty.class.getResource("book.gif"));
			bookl.setIcon(imgicon);
			imgicon1=new ImageIcon(Faculty.class.getResource("border1.jpg"));
			border1.setIcon(imgicon1);
			imgicon2=new ImageIcon(Faculty.class.getResource("border2.jpg"));
			border2.setIcon(imgicon2);
			lionimg=new ImageIcon(Faculty.class.getResource("lion.gif"));

			home.setActionCommand("Home");
			home.setToolTipText("Go to Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false); 
			find.setActionCommand("Find");
			find.setToolTipText("Search");
			find.setOpaque(false);
			find.setContentAreaFilled(false);
			find.setBorderPainted(false); 
			
			a=((d.width-270)/2);
			b=((d.height-310)/2);
			c=(d.width-320)/2;
			e=d.width-100;
			f=(d.height-544)/2;
			
			sidl.setBounds(a,b,100,20);
			sid.setBounds(a+100,b,120,20);
			find.setBounds(a+250,b-5,30,30);
			snamel.setBounds(a,b+30,100,20);
			sname.setBounds(a+100,b+30,120,20);
			deptl.setBounds(a,b+60,100,20);
			dept.setBounds(a+100,b+60,120,20);
			book1l.setBounds(a,b+90,100,20);
			book1.setBounds(a+100,b+90,120,20);
			b1.setBounds(a+250,b+90,20,20);
			book2l.setBounds(a,b+120,100,20);
			book2.setBounds(a+100,b+120,120,20);
			b2.setBounds(a+250,b+120,20,20);
			book3l.setBounds(a,b+150,100,20);
			book3.setBounds(a+100,b+150,120,20);
			b3.setBounds(a+250,b+150,20,20);
			book4l.setBounds(a,b+180,100,20);
			book4.setBounds(a+100,b+180,120,20);
			b4.setBounds(a+250,b+180,20,20);
			book5l.setBounds(a,b+210,100,20);
			book5.setBounds(a+100,b+210,120,20);
			b5.setBounds(a+250,b+210,20,20);
			bookl.setBounds(10,f,300,244);

			issue.setBounds(c,b+240,100,30);
			retun.setBounds(c+110,b+240,100,30);
			clear.setBounds(c+220,b+240,100,30);
			add.setBounds(c,b+280,100,30);
			remove.setBounds(c+110,b+280,100,30);
			details.setBounds(c+220,b+280,100,30);


			home.setBounds(e,10,75,75);
			border1.setBounds(0,d.height-380,350,380);
			border2.setBounds(d.width-350,d.height-380,350,380);

			add.addActionListener(this);
			issue.addActionListener(this);
			retun.addActionListener(this);
			remove.addActionListener(this);
			clear.addActionListener(this);
			find.addActionListener(this);
			find.addFocusListener(this);
			home.addActionListener(this);
			details.addActionListener(this);
			jp.getRootPane().setDefaultButton(find);
	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void focusGained(FocusEvent fe1)
	{
		try{
			String b="";
			String c="";
			String d="";
			String e="";
			String f="";
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM staff WHERE sid='"+sid.getText()+"'");
			while(rs.next())
			{
				sname.setText(rs.getString(2));
				dept.setSelectedItem(rs.getString(3));
				b=rs.getString(4);
				c=rs.getString(5);
				d=rs.getString(6);
				e=rs.getString(7);
				f=rs.getString(8);
				/*book1.setText(b);
				book2.setText(c);
				book3.setText(d);
				book4.setText(e);
				book5.setText(f);*/
				if(b.equals("0"))
					book1.setText("");
				else
					book1.setText(""+b);
				if(c.equals("0"))
					book2.setText("");
				else
					book2.setText(""+c);
				if(d.equals("0"))
					book3.setText("");
				else
					book3.setText(""+d);
				if(e.equals("0"))
					book4.setText("");
				else
					book4.setText(""+e);
				if(f.equals("0"))
					book5.setText("");
				else
					book5.setText(""+f);
					
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	public void focusLost(FocusEvent fe1)
	{
	}
	public void actionPerformed(ActionEvent ae7)
	{
		String s=ae7.getActionCommand();
		if(s=="Add")
		{
			try
			{				
				st=con.createStatement();
				st.executeUpdate("INSERT INTO staff(sid,sname,dept) VALUES('"+sid.getText()+"','"+sname.getText()+"','"+dept.getSelectedItem().toString()+"')");
				JOptionPane.showMessageDialog(this, "Staff Details Added Successfully", "Added Successfully", JOptionPane.INFORMATION_MESSAGE,lionimg);
				JOptionPane.showMessageDialog(this, "Books not Transfered,For adding the books use Issue button", "Not Allowed", JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,"Same staff id found already","Exception",JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		}
		if(s=="Clear")
		{
			sid.setText("");
			sname.setText("");
			book1.setText("");
			book2.setText("");
			book3.setText("");
			book4.setText("");
			book5.setText("");
			b1.setSelected(false);
			b2.setSelected(false);
			b3.setSelected(false);
			b4.setSelected(false);
			b5.setSelected(false);
			sid.requestFocus();
		}
		if(s=="Find")
		{	
			try{
				String b="";
				String c="";
				String d="";
				String e="";
				String f="";
				st=con.createStatement();
				rs=st.executeQuery("SELECT * FROM staff WHERE sid='"+sid.getText()+"'");
				while(rs.next())
				{
					sname.setText(rs.getString(2));
					dept.setSelectedItem(rs.getString(3));
					b=rs.getString(4);
					c=rs.getString(5);
					d=rs.getString(6);
					e=rs.getString(7);
					f=rs.getString(8);
					/*book1.setText(b);
					book2.setText(c);
					book3.setText(d);
					book4.setText(e);
					book5.setText(f);*/
					if(b.equals("0"))
						book1.setText("");
					else
						book1.setText(""+b);
					if(c.equals("0"))
						book2.setText("");
					else
						book2.setText(""+c);
					if(d.equals("0"))
						book3.setText("");
					else
						book3.setText(""+d);
					if(e.equals("0"))
						book4.setText("");
					else
						book4.setText(""+e);
					if(f.equals("0"))
						book5.setText("");
					else
						book5.setText(""+f);
					
		
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,e,"Exception",JOptionPane.INFORMATION_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
		if(s=="Issue")
		{
			try{
				String a,b,c,d,e,f,g,h;
				ResultSet rs;
				Statement st,st1,st2,st3,st4;
				
				String b1="";
				String b2="";
				String b3="";
				String b4="";
				String b5="";
				String s1="";
				String deptl="";
				String snamel="";
				
				
				a=sid.getText();
				b=sname.getText();
				c=dept.getSelectedItem().toString();
				d=book1.getText();
				e=book2.getText();
				f=book3.getText();
				g=book4.getText();
				h=book5.getText();
				
				String p="";
				String m="YES";
				String o="NO";
				String r="MISSED";
				String NO=new String("NO");
			
				if(a.equals(""))
				{
					JOptionPane.showMessageDialog(this,"Please Enter the Staff id","Staffid not found",JOptionPane.INFORMATION_MESSAGE,lionimg);			
				}
				else
				{
										
					st=con.createStatement();
					st.executeUpdate("update staff set sname='"+b+"' where sid='"+a+"'");
					
					st=con.createStatement();
					st.executeUpdate("update staff set dept='"+c+"' where sid='"+a+"'");
					
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+d+"'");
					while(rs.next())
					{
						b1=rs.getString(1);
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+e+"'");
					while(rs.next())
					{
						b2=rs.getString(1);
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+f+"'");
					while(rs.next())
					{
						b3=rs.getString(1);
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+g+"'");
					while(rs.next())
					{
						b4=rs.getString(1);
					}
					st=con.createStatement();
					rs=st.executeQuery("select available from book where bno='"+h+"'");
					while(rs.next())
					{
						b5=rs.getString(1);
					}
					
					if(p.equals(b1))
					{
						if(p.equals(d))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book1 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);	
						}
					}
					else
					{
					
						if(m.equals(b1))
						{
							st1=con.createStatement();
							st1.executeUpdate("update staff set book1='"+d+"' where sid='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+d+"'");
							JOptionPane.showMessageDialog(this,"Book1 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b1)||r.equals(b1))
						{
							JOptionPane.showMessageDialog(this,"Book1 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
						
					
					if(p.equals(b2))
					{
						
						if(p.equals(e))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book2 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}	
					else
					{
						if(m.equals(b2))
						{
							st1=con.createStatement();
							st1.executeUpdate("update staff set book2='"+e+"' where sid='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+e+"'");
							JOptionPane.showMessageDialog(this,"Book2 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b2)||r.equals(b2))
						{
							JOptionPane.showMessageDialog(this,"Book2 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}	
					}
	
					if(p.equals(b3))
					{
						if(p.equals(f))
						{}
						else	
						{
							JOptionPane.showMessageDialog(this,"Book3 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else	
					{				
						if(m.equals(b3))
						{
							st1=con.createStatement();
							st1.executeUpdate("update staff set book3='"+f+"' where sid='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+f+"'");
							JOptionPane.showMessageDialog(this,"Book3 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b3)||r.equals(b3))
						{
							JOptionPane.showMessageDialog(this,"Book3 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
	
					if(p.equals(b4))
					{
						if(p.equals(g))
						{}
						else
						{	
							JOptionPane.showMessageDialog(this,"Book4 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						if(m.equals(b4))
						{
							st1=con.createStatement();
							st1.executeUpdate("update staff set book4='"+g+"' where sid='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+g+"'");
							JOptionPane.showMessageDialog(this,"Book4 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b4)||r.equals(b4))
						{
							JOptionPane.showMessageDialog(this,"Book4 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}	
	
					if(p.equals(b5))
					{
						if(p.equals(h))
						{}
						else
						{
							JOptionPane.showMessageDialog(this,"Book5 not Found in Database","Not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{	
						if(m.equals(b5))
						{
							st1=con.createStatement();
							st1.executeUpdate("update staff set book5='"+h+"' where sid='"+a+"'");
							st2=con.createStatement();
							st2.executeUpdate("update book set available='"+NO+"' where bno='"+h+"'");
							JOptionPane.showMessageDialog(this,"Book5 is Issued Succcesfully","Issued",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
						else if(o.equals(b5)||r.equals(b5))
						{
							JOptionPane.showMessageDialog(this,"Book5 is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
				}	
				
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,e,"Exception",JOptionPane.INFORMATION_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
	
		if(s=="Remove")
		{
			try{
				Statement st1,st2;
				String a;
				int z=0;
				int n = JOptionPane.showConfirmDialog(null,"Would you like to remove ?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
				if(z==n)
				{
					String b1="";
					String b2="";
					String b3="";
					String b4="";
					String b5="";
					String p="";
					String q="0";
					a=sid.getText();
					JOptionPane.showMessageDialog(null,a);
					st1=con.createStatement();
					rs=st1.executeQuery("SELECT book1,book2,book3,book4,book5 FROM staff where sid='"+a+"'");
					while(rs.next())
					{
						b1=rs.getString(1);
						b2=rs.getString(2);
						b3=rs.getString(3);
						b4=rs.getString(4);	
						b5=rs.getString(5);
					}
					JOptionPane.showMessageDialog(null,"hello");
					if(p.equals(b1)||q.equals(b1))
					{
						if(p.equals(b2)||q.equals(b2))
						{
							if(p.equals(b3)||q.equals(b3))
							{
								if(p.equals(b4)||q.equals(b4))
								{
									if(p.equals(b5)||q.equals(b5))
									{
										st2=con.createStatement();
										st2.executeUpdate("delete * from staff where sid='"+a+"'");
									}
									else
									{
										JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
									}
								}
								else
								{
									JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
								}
							}
							else
							{
								JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
							}
						}		
						else
						{
							JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Book is Pending","Pending",JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,"You Have Cancelled"," Action incomplete",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,e,"Exception",JOptionPane.INFORMATION_MESSAGE,lionimg);
			}
		}
		if(s=="Return")
		{
			try{
				String a,b,c,d,e,f,g,h,i,j,k,l;
				int q;
				String YES=new String("YES");
				Statement st2;
				a=sid.getText();
				b=sname.getText();
				c=dept.getSelectedItem().toString();
				d=book1.getText();
				e=book2.getText();
				f=book3.getText();
				g=book4.getText();
				h=book5.getText();
				
				if(b1.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+d+"'");
					d="0";
					JOptionPane.showMessageDialog(this,"Book1 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b2.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+e+"'");
					e="0";
					JOptionPane.showMessageDialog(this,"Book2 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b3.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+f+"'");
					f="0";
					JOptionPane.showMessageDialog(this,"Book3 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b4.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+g+"'");
					g="0";
					JOptionPane.showMessageDialog(this,"Book4 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				if(b5.isSelected()==true)
				{
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+h+"'");
					h="0";
					JOptionPane.showMessageDialog(this,"Book5 Returned Successfully","Returned",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
				
				String sstr="update staff set book1=?,book2=?,book3=?,book4=?,book5=? where sid='"+a+"' ";
				PreparedStatement stmt=con.prepareStatement(sstr);
				stmt.setString(1,d);
				stmt.setString(2,e);
				stmt.setString(3,f);
				stmt.setString(4,g);
				stmt.setString(5,h);
				stmt.executeUpdate();
				b1.setSelected(false);
				b2.setSelected(false);
				b3.setSelected(false);
				b4.setSelected(false);
				b5.setSelected(false);
				sid.requestFocus();
				b="";
				c="";
				d="";
				e="";
				f="";
				st=con.createStatement();
				rs=st.executeQuery("SELECT * FROM staff WHERE sid='"+sid.getText()+"'");
				while(rs.next())
				{
					sname.setText(rs.getString(2));
					dept.setSelectedItem(rs.getString(3));
					b=rs.getString(4);
					c=rs.getString(5);
					d=rs.getString(6);
					e=rs.getString(7);
					f=rs.getString(8);
					/*book1.setText(b);
					book2.setText(c);
					book3.setText(d);
					book4.setText(e);
					book5.setText(f);*/
					if(b.equals("0"))
						book1.setText("");
					else
						book1.setText(""+b);
					if(c.equals("0"))
						book2.setText("");
					else
						book2.setText(""+c);
					if(d.equals("0"))
						book3.setText("");
					else
						book3.setText(""+d);
					if(e.equals("0"))
						book4.setText("");
					else
						book4.setText(""+e);
					if(f.equals("0"))
						book5.setText("");
				}	
												
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Home")
		{
			Home obj=new Home();
			dispose();
		}
		if(s=="Details")
		{
			FDetails obj=new FDetails();
		}
	}
}

//------------------------------------------------------------------------------------------------------------------------------

class Details extends JFrame implements ActionListener,Printable
{
	JPanel jp;
	JTable jtable1;
	JScrollPane qscroller1;	
	DefaultTableModel model1;
	Object o2[];
	Dimension d;
	JLabel batch,sec;
	JComboBox cb1,cb2;
	JButton print,find;
	Image img;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	Details()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			print=new JButton("print");
			find=new JButton("find");
			batch=new JLabel("Batch");
			sec=new JLabel("SEC");
			
			
			cb1=new JComboBox();
			cb1.setEditable(true);
			st=con.createStatement();
			rs=st.executeQuery("Select distinct batch from student");
			while(rs.next())
			{
				cb1.addItem(rs.getString(1));
			}
			
			cb2=new JComboBox();
			cb2.setEditable(true);
			cb2.addItem("B");
			cb2.addItem("A");
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("logo.png");
			jp=new JPanel();
			jp.setLayout(null);
			
				

			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);	
			setTitle("Student Table Generated");
			
			setDefaultCloseOperation(2);
			add(jp);
			setContentPane(jp);
			
			jp.add(print);
			jp.add(find);
			jp.add(cb1);
			jp.add(cb2);
			jp.add(batch);
			jp.add(sec);
			jp.getRootPane().setDefaultButton(find);
			batch.setBounds(10,10,70,20);
			cb1.setBounds(60,10,70,20);
			sec.setBounds(160,10,70,20);
			cb2.setBounds(200,10,70,20);
			print.setBounds(400,10,70,20);
			print.addActionListener(this);
			find.setBounds(300,10,70,20);
			find.addActionListener(this);
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
	    	if (page > 0)
 		{
      			return NO_SUCH_PAGE;
    		}
    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
    		return PAGE_EXISTS;
  	}

  	public void actionPerformed(ActionEvent e)
 	{
		String s=e.getActionCommand();
		if(s=="print")
		{
   			PrinterJob job = PrinterJob.getPrinterJob();
  	 		job.setPrintable(this);
    			boolean ok = job.printDialog();
    			if (ok) 
			{
      				try 
				{
        					job.print();
      				}
				catch (PrinterException ex) 
				{
       	 				/* The job did not successfully complete */
      				}
    			}
		}
		if(s=="find")
		{
			try
			{
				TableColumn column=null;
				model1=new DefaultTableModel(new Object[][]{{"REGNO","B1","B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12"},},new String[]{null,null,null,null,null,null,null,null,null,null,null,null,null});
				jtable1=new JTable(model1);
				qscroller1=new JScrollPane(jtable1);
			
				for(int i=0;i<13;i++)
				{
					column=jtable1.getColumnModel().getColumn(i);
					if(i==0)
					{
						column.setPreferredWidth(60);
					}
					else
					{
						column.setPreferredWidth(30);
					}
				}

				qscroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				qscroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
				jp.add(qscroller1);
			
				qscroller1.setBounds(10,35,d.width-330,d.height-130);
				
				Statement st;
				st=con.createStatement();
				rs=st.executeQuery("select * from student where batch='"+(String) cb1.getSelectedItem()+"' and section='"+ (String) cb2.getSelectedItem()+"'");
	
				while(rs.next())
				{	
					o2=new Object[]{rs.getString(1),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
					rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),};
					model1.addRow(o2);	
				}
			}
			catch(Exception e1)
			{	
				System.out.println(e1);
			}
		}

		if(s=="Clear")
		{
			try
			{
				Statement st;
				st=con.createStatement();
				model1.removeRow(1);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
	  }
	
}

//------------------------------------------------------------------------------------------------------------------------------
class BookAvail extends JFrame implements ActionListener,Printable
{
	JPanel jp;
	JTable jtable1;
	JLabel l1,l2,l3,l4,l5;
	JTextField tf1,tf2,tf3,tf4;
	JButton find,print;
	JComboBox cb1;
	JScrollPane qscroller1;	
	DefaultTableModel model1;
	Object o2[];
	String z,j;
	String y="YES";
	Dimension d;
	Image img;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	BookAvail()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);


			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);	
			setTitle("BookAvailablity");
			setDefaultCloseOperation(2);
			add(jp);
			setContentPane(jp);

			l1=new JLabel("Book ID");
			l2=new JLabel("Avail");
			l3=new JLabel("Issued");
			l4=new JLabel("Missed");
			l5=new JLabel("Total");
			
			tf1=new JTextField(20);
			tf2=new JTextField(20);
			tf3=new JTextField(20);
			tf4=new JTextField(20);
			
			cb1=new JComboBox();
			st=con.createStatement();
			rs=st.executeQuery("Select distinct bid from book");
			while(rs.next())
			{
				cb1.addItem(rs.getString(1));
			}
			
			find=new JButton("Find");
			find.addActionListener(this);
			print=new JButton("Print");
			print.addActionListener(this);

			jp.add(l1);
			jp.add(l2);
			jp.add(l3);
			jp.add(l4);
			jp.add(l5);
			
			jp.add(cb1);
			jp.add(find);
			jp.add(print);
	
			l1.setBounds(10,10,70,20);
			cb1.setBounds(70,10,100,20);
			l2.setBounds(180,10,45,20);
			l3.setBounds(280,10,45,20);
			l4.setBounds(380,10,45,20);
			l5.setBounds(480,10,45,20);
			find.setBounds(580,10,70,20);
			print.setBounds(680,10,70,20);
		
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	public void actionPerformed(ActionEvent ae15)
	{
		String s=ae15.getActionCommand();
		if(s=="Find")
		{
			String z="";
			try
			{	
				try
				{
					String s1=(String) cb1.getSelectedItem();
					String s2="YES";
					String s3="";
					st=con.createStatement();
					rs=st.executeQuery("select count(*) from book where bid='"+s1+"' and available='"+s2+"'");
					while(rs.next())
					{
						s3=rs.getString(1);
					}
					tf1.setText(s3);
								
	  			}
				catch(Exception e)
				{
					System.out.println(e);
				}
				try
				{
					String s1=(String) cb1.getSelectedItem();
					String s2="No";
					String s3="";
					st=con.createStatement();
					rs=st.executeQuery("select count(*) from book where bid='"+s1+"' and available='"+s2+"'");
					while(rs.next())
					{
						s3=rs.getString(1);
					}
					tf2.setText(s3);
		
				}
				catch(Exception e)
				{			
					System.out.println(e);
				}
				try
				{
					String s1=(String) cb1.getSelectedItem();
					String s2="MISSED";
					String s3="";
					st=con.createStatement();
					rs=st.executeQuery("select count(*) from book where bid='"+s1+"' and available='"+s2+"'");
					while(rs.next())
					{
						s3=rs.getString(1);
					}
					tf3.setText(s3);
				
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
	
				try
				{
					int i=0;
					String s1=(String) cb1.getSelectedItem();
					String s3="";
					st=con.createStatement();
					rs=st.executeQuery("select count(*) from book where bid='"+s1+"'");
					while(rs.next())
					{
						s3=rs.getString(1);
					}
					tf4.setText(s3);
				
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				tf1.setBounds(205,10,35,20);
				tf2.setBounds(305,10,35,20);
				tf3.setBounds(405,10,35,20);
				tf4.setBounds(505,10,35,20);
			
				model1=new DefaultTableModel(new Object[][]{{"BOOKID","BOOKNO","STATUS"},},new String[]{null,null,null});
				jtable1=new JTable(model1);
				qscroller1=new JScrollPane(jtable1);

				qscroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				qscroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
				jp.add(qscroller1);
				qscroller1.setBounds(10,35,d.width-500,d.height-130);
				jp.add(tf1);
				jp.add(tf2);
				jp.add(tf3);
				jp.add(tf4);
				z=(String) cb1.getSelectedItem();
				st=con.createStatement();
				rs=st.executeQuery("select * from book where bid='"+ z +"'");
				while(rs.next())
				{
					o2=new Object[]{rs.getString(1),rs.getString(2),rs.getString(7)};
					model1.addRow(o2);	
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
				JOptionPane.showMessageDialog(this,e,"Problem is Occured",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(Login.class.getResource("lion.gif")));
			}	
		}
		if(s=="Print")
		{
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
		   	boolean ok = job.printDialog();
		    	if (ok) 
			{	
      				try 
				{
	        				job.print();
      				}
				catch (PrinterException ex) 
				{
		       	 		/* The job did not successfully complete */
      				}
		    	}
		}
	}
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
	    	if (page > 0)
 		{
      			return NO_SUCH_PAGE;
    		}

    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
   		return PAGE_EXISTS;
  	}
	
}

//------------------------------------------------------------------------------------------------------------------------------
class Lend extends JFrame implements ActionListener
{
	JLabel deptl,nobookl,border1,border2,bookl;
	JTextField nobook;
	JButton lend,Remove,Print,home;
	JComboBox dept;
	JPanel jp;
	Dimension d;
	Image img;
	ImageIcon imgicon,imgicon1,imgicon2,lionimg;
	Font ft1;
	Color c1;
	Connection con;
	int a,b,c,e;

	Lend()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
				
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);
						
			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);			
			setTitle("Lend Form");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(jp);
			
			deptl=new JLabel("Department");
			nobookl=new JLabel("No of Books");
			border1=new JLabel();
			border2=new JLabel();
			bookl=new JLabel();

			nobook=new JTextField(20);
	
			dept=new JComboBox();
			dept.setEditable(true);
			dept.addItem("IT");
			dept.addItem("CIVIL");
			dept.addItem("MECH");
			dept.addItem("ECE");
			dept.addItem("MBA");
			dept.addItem("MCA");
			dept.addItem("EEE");
			dept.addItem("MAINLIB");

	
			lend=new JButton("Lend");
			Remove=new JButton("Remove");
			Print=new JButton("Print");
			home=new JButton(new ImageIcon("Home.jpg"));
			jp.getRootPane().setDefaultButton(lend);

			ft1=new Font("Times New Roman",1,18);
			c1=new Color(0,100,0);

			deptl.setFont(ft1);
			dept.setFont(ft1);
			nobookl.setFont(ft1);
			nobook.setFont(ft1);
			deptl.setForeground(c1);
			dept.setForeground(c1);
			nobookl.setForeground(c1);
			nobook.setForeground(c1);

			imgicon=new ImageIcon(Lend.class.getResource("book.gif"));
			bookl.setIcon(imgicon);
			imgicon1=new ImageIcon(Lend.class.getResource("border1.jpg"));
			border1.setIcon(imgicon1);
			imgicon2=new ImageIcon(Lend.class.getResource("border2.jpg"));
			border2.setIcon(imgicon2);	
			lionimg=new ImageIcon(Login.class.getResource("lion.gif"));

					
			home.setActionCommand("Home");
			home.setToolTipText("Go to Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false); 	
			
			jp.add(deptl);
			jp.add(nobookl);
			jp.add(dept);
			jp.add(nobook);
			jp.add(lend);
			jp.add(Remove);
			jp.add(Print);
			jp.add(home);
			jp.add(bookl);
			jp.add(border1);
			jp.add(border2);	

			a=(d.width-200)/2;
			b=(d.height-200)/2;
			c=(d.width-320)/2;
			e=(d.height-544)/2;
						
			deptl.setBounds(a,b,100,20);
			dept.setBounds(a+100,b,100,20);
			nobookl.setBounds(a,b+40,100,20);
			nobook.setBounds(a+100,b+40,100,20);
			lend.setBounds(c,b+80,100,30);
			Remove.setBounds(c+110,b+80,100,30);
			Print.setBounds(c+220,b+80,100,30);
			
			home.setBounds(d.width-100,10,75,75);
			bookl.setBounds(10,e,300,244);
			border1.setBounds(0,d.height-380,350,380);
			border2.setBounds(d.width-350,d.height-380,350,380);
			

			lend.addActionListener(this);
			Remove.addActionListener(this);
			Print.addActionListener(this);
			home.addActionListener(this);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae10)
	{
		String s=ae10.getActionCommand();
		if(s=="Lend")
		{
			try{
				String a,b;
				String b1="";
				String b2="";
				String k="";
				int x,i;
				ResultSet rs;
				Statement st1,st2;
				String j1="Book added";
				a=dept.getSelectedItem().toString();
				b=nobook.getText();	
				x=Integer.parseInt(b);
				String p="YES";
				String q="NO";
				String NO=new String("NO");
				
				for(i=1;i<=x;i++)
				{
					b1="";
					b2="";
					k=JOptionPane.showInputDialog("Enter the Book no");
					st1=con.createStatement();
					rs=st1.executeQuery("select available from book where bno='"+k+"'");
					while(rs.next())
					{
						b1=rs.getString(1);
					}
					st1=con.createStatement();
					rs=st1.executeQuery("select bid from book where bno='"+k+"'");
					while(rs.next())
					{
						b2=rs.getString(1);
					}
					
					if(p.equals(b1))
					{
						String sstr="insert into lend values(?,?,?)";
						PreparedStatement stmt=con.prepareStatement(sstr);
						stmt.setString(1,b2);
						stmt.setString(2,k);
						stmt.setString(3,a);
						stmt.executeUpdate();
						st2=con.createStatement();
						st2.executeUpdate("update book set available='"+NO+"' where bno='"+k+"'");
					}
					else if(q.equals(b1))
					{
						JOptionPane.showMessageDialog(this,"Book is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Book is not Found in Database","Book not Found",JOptionPane.INFORMATION_MESSAGE,lionimg);
					}
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"Please Enter the number of Books to be Lend","Check It",JOptionPane.INFORMATION_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
		if(s=="Remove")
		{
			try{
				String b;
				Statement st,st1,st2;
				ResultSet rs;
				String k="";
				String j="";
				String b1="";
				String m="";
				int x;
				String q="";
				String j1="Book added to Dept Database";
				String p="YES";
				String YES=new String("YES");
				
				int i;
				b=nobook.getText();	
				x=Integer.parseInt(b);
				j=JOptionPane.showInputDialog("Enter the Book id");
				for(i=1;i<=x;i++)
				{
					k=JOptionPane.showInputDialog("Enter the Book no");
					st=con.createStatement();
					st.executeUpdate("delete * from lend where bno='"+k+"'");
					st2=con.createStatement();
					st2.executeUpdate("update book set available='"+YES+"' where bno='"+k+"'");
				}
			}
			catch(Exception e)
			{
				
				JOptionPane.showMessageDialog(this,"Please Enter the number of Books to be removed","Check it",JOptionPane.INFORMATION_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
		if(s=="Print")
		{
			try
			{
				new LendAvail();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Home")
		{
			Home obj=new Home();
			dispose();
		}
			
	}
}
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class LendAvail extends JFrame implements ActionListener,Printable	
{
	JPanel jp;
	JTable jtable1;
	JScrollPane qscroller1;	
	JLabel deptl,bookidl;
	JComboBox dept,bookid;
	JButton find,print;
	DefaultTableModel model1;
	Object o2[];
	String z,j;
	Dimension d;
	Image img;
	
	Connection con;
	Statement st;
	ResultSet rs,rs1,rs2;
	int a;
	
	LendAvail()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);

			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);	
			setTitle("BookAvailablity");
			setDefaultCloseOperation(2);
			add(jp);
			setContentPane(jp);
				
		
			deptl=new JLabel("Department");
			bookidl=new JLabel("Book ID");
			
			dept=new JComboBox();
			bookid=new JComboBox();
			
			find=new JButton("Find");
			print=new JButton("Print");
			
			
			st=con.createStatement();			
			rs1=st.executeQuery("Select distinct dept from lend");			
			while(rs1.next())
			{
				dept.addItem(rs1.getString(1));
			}

			rs2=st.executeQuery("Select distinct bid from lend");
			while(rs2.next())
			{
				bookid.addItem(rs2.getString(1));
			}
			model1=new DefaultTableModel(new Object[][]{{"BOOKID","BOOKNO","DEPT"},},new String[]{null,null,null});
			jtable1=new JTable(model1);
			qscroller1=new JScrollPane(jtable1);
			qscroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			qscroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jp.add(qscroller1);	
			
			jp.add(deptl);
			jp.add(dept);
			jp.add(bookidl);
			jp.add(bookid);
			jp.add(find);
			jp.add(print);
			
			a=(d.width-550)/2;
		
			qscroller1.setBounds(25,75,d.width-50,d.height-150);
			deptl.setBounds(a,25,70,20);
			dept.setBounds(a+70,25,100,20);
			bookidl.setBounds(a+180,25,50,20);
			bookid.setBounds(a+230,25,100,20);
			find.setBounds(a+340,25,100,20);
			print.setBounds(a+450,25,100,20);
			
			find.addActionListener(this);
			print.addActionListener(this);
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae2)
	{
		String s=ae2.getActionCommand();
		if(s=="Find")
		{	try{
				
				z=dept.getSelectedItem().toString();
				j=bookid.getSelectedItem().toString();
				rs=st.executeQuery("select * from lend where dept='"+ z +"' and bid='"+j+"'");
				while(rs.next())
				{
					o2=new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)};
					model1.addRow(o2);	
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Print")
		{
			PrinterJob job = PrinterJob.getPrinterJob();
  	 		job.setPrintable(this);
    			boolean ok = job.printDialog();
    			if (ok) 
			{
      				try 
				{
        					job.print();
      				}
				catch (PrinterException ex) 
				{
       	 				/* The job did not successfully complete */
      				}
    			}
		}
	}
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
    		if (page > 0)
 		{
      			return NO_SUCH_PAGE;
    		}
    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
    		return PAGE_EXISTS;
  	}
}
//------------------------------------------------------------------------------------------------------------------------------
class Borrow extends JFrame implements ActionListener
{
	JLabel deptl,bookidl,nobookl,bookl,border1,border2;
	JTextField bookid,nobook;
	JButton Add,Remove,Print,home;
	JPanel jp;
	JComboBox dept;
	Font ft1;
	Color c1;
	Dimension d;
	Image img;
	ImageIcon imgicon,imgicon1,imgicon2,lionimg;
	Connection con;
	int a,b,c,e;
	
	Borrow()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);
					
			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);			
			setTitle("Borrow Form");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(jp);
			setContentPane(jp);
			
			ft1=new Font("Times New Roman",1,18);
			c1=new Color(0,100,0);
			
			deptl=new JLabel("Department");
			bookidl=new JLabel("Book ID");
			nobookl=new JLabel("No of Books");
			bookl=new JLabel();
			border1=new JLabel();
			border2=new JLabel();
			
			dept=new JComboBox();
			dept.setEditable(true);
			dept.addItem("IT");
			dept.addItem("CIVIL");
			dept.addItem("MECH");
			dept.addItem("ECE");
			dept.addItem("MBA");
			dept.addItem("MCA");
			dept.addItem("EEE");
			dept.addItem("MAINLIB");
			
			bookid=new JTextField(20);
			nobook=new JTextField(20);
			
			deptl.setFont(ft1);
			bookidl.setFont(ft1);
			nobookl.setFont(ft1);
			dept.setFont(ft1);
			bookid.setFont(ft1);
			nobook.setFont(ft1);
			
			deptl.setForeground(c1);
			bookidl.setForeground(c1);
			nobookl.setForeground(c1);
			dept.setForeground(c1);
			bookid.setForeground(c1);
			nobook.setForeground(c1);
			
			Add=new JButton("Add");
			Remove=new JButton("Remove");
			Print=new JButton("Print");
			home=new JButton(new ImageIcon("Home.jpg"));
			jp.getRootPane().setDefaultButton(Add);
			
			imgicon=new ImageIcon(Borrow.class.getResource("book.gif"));
			bookl.setIcon(imgicon);
			imgicon1=new ImageIcon(Borrow.class.getResource("border1.jpg"));
			border1.setIcon(imgicon1);
			imgicon2=new ImageIcon(Borrow.class.getResource("border2.jpg"));
			border2.setIcon(imgicon2);	
			lionimg=new ImageIcon(Login.class.getResource("lion.gif"));

			home.setActionCommand("Home");
			home.setToolTipText("Go to Home");
			home.setOpaque(false);
			home.setContentAreaFilled(false);
			home.setBorderPainted(false); 	

			jp.add(deptl);
			jp.add(bookidl);
			jp.add(nobookl);
			jp.add(dept);
			jp.add(bookid);
			jp.add(nobook);
			jp.add(Add);
			jp.add(Remove);
			jp.add(Print);
			jp.add(home);
			jp.add(bookl);
			jp.add(border1);
			jp.add(border2);
			
			a=(d.width-200)/2;
			b=(d.height-200)/2;
			c=(d.width-320)/2;
			e=(d.height-544)/2;			

			deptl.setBounds(a,b,100,20);
			dept.setBounds(a+100,b,100,20);
			nobookl.setBounds(a,b+40,100,20);
			nobook.setBounds(a+100,b+40,100,20);
			bookidl.setBounds(a,b+80,100,20);
			bookid.setBounds(a+100,b+80,100,20);

			Add.setBounds(c,b+120,100,30);
			Remove.setBounds(c+110,b+120,100,30);
			Print.setBounds(c+220,b+120,100,30);
			home.setBounds(d.width-100,10,75,75);
		
			bookl.setBounds(10,e,300,244);
			border1.setBounds(0,d.height-380,350,380);
			border2.setBounds(d.width-350,d.height-380,350,380);
			Add.addActionListener(this);
			Remove.addActionListener(this);
			Print.addActionListener(this);
			home.addActionListener(this);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		if(s=="Add")
		{
			try{
				String b,c;
				String k="";
				int x;
				int i;
				String j="Book added";
				b=nobook.getText();	
				c=bookid.getText();
				x=Integer.parseInt(b);
				String y=new String("YES");
				for(i=1;i<=x;i++)
				{
					k=JOptionPane.showInputDialog("Enter the Book no");
					String sstr="insert into book values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement stmt=con.prepareStatement(sstr);
					stmt.setString(1,c);
					stmt.setString(2,k);
					stmt.setString(3,"");
					stmt.setString(4,"");
					stmt.setString(5,"");
					stmt.setString(6,"");
					stmt.setString(7,"YES");
					stmt.setString(8,(String) dept.getSelectedItem());
					stmt.setString(9,"MIT");
					stmt.executeUpdate();
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,e,"Some problem Ocurred",JOptionPane.INFORMATION_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
		if(s=="Remove")
		{
			try{	
				int z=0;
				int n = JOptionPane.showConfirmDialog(null,"Would you like to remove ?","Confirm Dialog",JOptionPane.YES_NO_OPTION);
				if(z==n)
				{
					String b,c;
					String k="";
					//String av="";
					//String bv="YES";
					//i="";
					String av="";
					String bv="YES";
					int x;
					Statement st;
					int i;
					b=nobook.getText();	
					c=bookid.getText();
					x=Integer.parseInt(b);
					for(i=1;i<=x;i++)
					{
						k=JOptionPane.showInputDialog("Enter the Book no");
						st=con.createStatement();
						ResultSet rs=st.executeQuery("select available from book where bno='"+k+"'");
						while(rs.next())
						{
							av=rs.getString(1);
						}
						if(av.equals(bv))
						{
							st=con.createStatement();
							st.executeUpdate("delete * from book where bno='"+k+"'");
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Book is not available","Not Available",JOptionPane.INFORMATION_MESSAGE,lionimg);
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this,e,"Action Incomplete",JOptionPane.INFORMATION_MESSAGE,lionimg);
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,"Please Enter the number of Books to be removed","Check it",JOptionPane.INFORMATION_MESSAGE,lionimg);
				System.out.println(e);
			}
		}
		if(s=="Print")
		{
			try
			{
				new BorrowAvail();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Home")
		{
			Home obj=new Home();
			dispose();
		}
	}

}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
class BorrowAvail extends JFrame implements ActionListener,Printable
{
	JPanel jp;
	JTable jtable1;
	JScrollPane qscroller1;
	JLabel deptl,bookidl;
	JComboBox dept,bookid;
	JButton find,print;
	DefaultTableModel model1;
	Object o2[];
	String z,j;
	Dimension d;
	Image img;
	
	Connection con;
	Statement st;
	ResultSet rs,rs1,rs2;
	int a;
	
	BorrowAvail()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			st=con.createStatement();
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("Logo.png");
			jp=new JPanel();
			jp.setLayout(null);

			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);	
			setTitle("BookAvailablity");
			setDefaultCloseOperation(2);
			add(jp);
			setContentPane(jp);
			
			deptl=new JLabel("Department");
			bookidl=new JLabel("Book ID");
			
			dept=new JComboBox();
			bookid=new JComboBox();
			
			find=new JButton("Find");
			print=new JButton("Print");
		
			rs1=st.executeQuery("Select distinct dept from book");			
			while(rs1.next())
			{
				dept.addItem(rs1.getString(1));
			}

			rs2=st.executeQuery("Select distinct bid from book");
			while(rs2.next())
			{
				bookid.addItem(rs2.getString(1));
			}

			model1=new DefaultTableModel(new Object[][]{{"BOOKID","BOOKNO","STATUS","DEPT"},},new String[]{null,null,null,null});
			jtable1=new JTable(model1);
			qscroller1=new JScrollPane(jtable1);
			qscroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			qscroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			jp.add(qscroller1);
			jp.add(deptl);
			jp.add(dept);
			jp.add(bookidl);
			jp.add(bookid);
			jp.add(find);
			jp.add(print);

			a=(d.width-550)/2;
					
			qscroller1.setBounds(25,75,d.width-50,d.height-150);

			deptl.setBounds(a,25,70,20);
			dept.setBounds(a+70,25,100,20);
			bookidl.setBounds(a+180,25,50,20);
			bookid.setBounds(a+230,25,100,20);
			find.setBounds(a+340,25,100,20);
			print.setBounds(a+450,25,100,20);
			
			find.addActionListener(this);
			print.addActionListener(this);
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void actionPerformed(ActionEvent ae2)
	{
		String s=ae2.getActionCommand();
		if(s=="Find")
		{	try{
				z=dept.getSelectedItem().toString();
				j=bookid.getSelectedItem().toString();
				rs=st.executeQuery("select * from book where dept='"+ z +"' and bid='"+j+"'");
				while(rs.next())
				{
					o2=new Object[]{rs.getString(1),rs.getString(2),rs.getString(7),rs.getString(8)};
					model1.addRow(o2);	
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		if(s=="Print")
		{
			PrinterJob job = PrinterJob.getPrinterJob();
  	 		job.setPrintable(this);
    			boolean ok = job.printDialog();
    			if (ok) 
			{
      				try 
				{
        					job.print();
      				}
				catch (PrinterException ex) 
				{
       	 				/* The job did not successfully complete */
      				}
    			}
		}
	}
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
    		if (page > 0)
 		{
      			return NO_SUCH_PAGE;
    		}
    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
    		return PAGE_EXISTS;
  	}
}

//-------------------------------------------------------------------------------------------------------------------------------
class Final extends JFrame implements ActionListener,Printable
{
	JLabel tbl,ibl,mbl,abl,tb,ib,mb,ab,ctb,cib,cmb,cab,itb,iib,imb,iab,etb,eib,emb,eab,eetb,eeib,eemb,eeab,mtb,mib,mmb,mab,cltb,clib,clmb,clab,mbatb,mbaib,mbamb,mbaab,mcatb,mcaib,mcamb,mcaab,otb,oib,omb,oab,all,cse,it,civil,mech,ece,mca,mba,eee,other;
	JPanel jp;
	JButton print;
	Dimension d;
	Image img;
	Font ft1,ft2;
	Background bg;
	Connection con;
	ResultSet rs;
	Statement st;

	Final()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("logo.png");
			bg=new Background();
			jp=new JPanel();
			jp.setLayout(null);
					
			
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);			
			setTitle("Report:Dept Of Computer Science and Engineering,Mit");
			setDefaultCloseOperation(2);
			add(jp);
			tbl=new JLabel("Total books");
			ibl=new JLabel("Issued");
			mbl=new JLabel("Missed");
			abl=new JLabel("Available");
			all=new JLabel("TOTAL");
			cse=new JLabel("CSE");
			it=new JLabel("IT");
			civil=new JLabel("CIVIL");
			ece=new JLabel("ECE");
			mech=new JLabel("MECH");
			eee=new JLabel("EEE");
			mba=new JLabel("MBA");
			mca=new JLabel("MCA");
			other=new JLabel("OTHER");
			
			ft1=new Font("Calibri (Body)",1,14); 
			ft2=new Font("Calibri (Body)",0,14); 
			
			print=new JButton("Print");
			try
			{
				String s2="";
				String s1="MIT";
				st=con.createStatement();
				rs=st.executeQuery("select count(*) from book where coll='"+s1+"'");
				while(rs.next())
				{
					s2=rs.getString(1);
				}
				/*rs=st.executeQuery("select * from book where coll='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}*/
				
				tb=new JLabel(s2);
		
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
			try
			{
				int i=0;
				String s1="MIT";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where coll='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				ib=new JLabel(s3);
				
		  	}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="MIT";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where coll='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mb=new JLabel(s3);
				
			}
			catch(Exception e)
			{		
				System.out.println(e);
			}		
			try
			{
				int i=0;
				String s1="MIT";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where coll='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				ab=new JLabel(s3);
				
			}	
			catch(Exception e)
			{
				System.out.println(e);
			}	
		
			try
			{
				int i=0;
				String s1="CSE";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				ctb=new JLabel(s2);
				
			 }	
			catch(Exception e)
			{
				System.out.println(e);
			}
		
			try
			{
				int i=0;
				String s1="CSE";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				cib=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="CSE";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				cmb=new JLabel(s3);		
				
			 }
			catch(Exception e)
			{
				System.out.println(e);
			}		
			try
			{
				int i=0;
				String s1="CSE";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				cab=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="IT";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				itb=new JLabel(s2);
					
			}
			catch(Exception e)	
			{		
				System.out.println(e);
			}
		
			try
			{	
				int i=0;
				String s1="IT";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				iib=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="IT";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				imb=new JLabel(s3);
				
	 		}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="IT";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				iab=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			try
			{
				int i=0;
				String s1="CIVIL";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				cltb=new JLabel(s2);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		
			try
			{
				int i=0;
				String s1="CIVIL";
				String s2=	"No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				clib=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="CIVIL";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				clmb=new JLabel(s3);
				
	     		}
			catch(Exception e)
			{	
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="CIVIL";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				clab=new JLabel(s3);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
	
			try
			{
				int i=0;
				String s1="MCA";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				mcatb=new JLabel(s2);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
	
			try
			{
				int i=0;
				String s1="MCA";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mcaib=new JLabel(s3);
				
			}	
			catch(Exception e)
			{
				System.out.println(e);
			}
		   	try
			{
				int i=0;
				String s1="MCA";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mcamb=new JLabel(s3);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="MCA";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mcaab=new JLabel(s3);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			try
			{
				int i=0;
				String s1="MECH";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				mtb=new JLabel(s2);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
	
			try
			{
				int i=0;
				String s1="MECH";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mib=new JLabel(s3);
		
			     }
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="MECH";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mmb=new JLabel(s3);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="MECH";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mab=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

			try
			{
				int i=0;
				String s1="MBA";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())		
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				mbatb=new JLabel(s2);
		
	     		}	
			catch(Exception e)
			{
				System.out.println(e);
			}

			try
			{
				int i=0;
				String s1="MBA";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mbaib=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="MBA";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mbamb=new JLabel(s3);
		
	     		}	
			catch(Exception e)
			{
				System.out.println(e);
			}
		  	try
			{
				int i=0;
				String s1="MBA";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				mbaab=new JLabel(s3);
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	
			try
			{
				int i=0;
				String s1="ECE";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				etb=new JLabel(s2);
				
		     	}	
			catch(Exception e)
			{
				System.out.println(e);
			}

			try
			{
				int i=0;
				String s1="ECE";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				eib=new JLabel(s3);
				
		     	}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="ECE";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				emb=new JLabel(s3);
				
			}	
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="ECE";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				eab=new JLabel(s3);
				
			}		
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="EEE";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				eetb=new JLabel(s2);
				
			}	
			catch(Exception e)
			{
				System.out.println(e);
			}

			try
			{
				int i=0;
				String s1="EEE";
				String s2="No";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				eeib=new JLabel(s3);
				
		     	}
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="EEE";
				String s2="Missed";
				st=con.createStatement();
				rs=st.executeQuery("	select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				eemb=new JLabel(s3);
				
			}	
			catch(Exception e)
			{
				System.out.println(e);
			}
	  		try
			{
				int i=0;
				String s1="EEE";
				String s2="Yes";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept='"+s1+"' and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s3=String.valueOf(i);
				eeab=new JLabel(s3);
		
	     		}	
			catch(Exception e)
			{
				System.out.println(e);
			}

			try
			{
				int i=0;
				String s1="CSE";
				String s3="IT";
				String s4="CIVIL";
				String s5="ECE";
				String s6="MECH";
				String s7="MBA";
				String s8="MCA";
				String s9="EEE";
				String s12="null";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept not in('"+s1+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s12+"')");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s2=String.valueOf(i);
				otb=new JLabel(s2);
				
	     		}	
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="CSE";
				String s2="No";
				String s3="IT";
				String s4="CIVIL";
				String s5="ECE";
				String s6="MECH";
				String s7="MBA";
				String s8="MCA";
				String s9="EEE";
				String s12="null";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept not in('"+s1+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s12+"') and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s10=String.valueOf(i);
				oib=new JLabel(s10);
				
			}	
			catch(Exception e)
			{
				System.out.println(e);
			}
			try
			{
				int i=0;
				String s1="CSE";
				String s2="Missed";
				String s3="IT";
				String s4="CIVIL";
				String s5="ECE";
				String s6="MECH";
				String s7="MBA";
				String s8="MCA";
				String s9="EEE";
				String s12="null";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept not in('"+s1+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s12+"') and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s10=String.valueOf(i);
				omb=new JLabel(s10);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}

			try
			{
				int i=0;
				String s1="CSE";
				String s2="Yes";
				String s3="IT";
				String s4="CIVIL";
				String s5="ECE";
				String s6="MECH";
				String s7="MBA";
				String s8="MCA";
				String s9="EEE";
				String s12="null";
				st=con.createStatement();
				rs=st.executeQuery("select * from book where dept not in('"+s1+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"','"+s12+"') and available='"+s2+"'");
				while(rs.next())
				{
					rs.getString(1);
					i++;
				}
				String s10=String.valueOf(i);
				oab=new JLabel(s10);
				
	     		}
			catch(Exception e)
			{
				System.out.println(e);
			}
			cse.setFont(ft1);
			it.setFont(ft2);
			civil.setFont(ft2);
			ece.setFont(ft2);
			mech.setFont(ft2);
			eee.setFont(ft2);
			mca.setFont(ft2);
			mba.setFont(ft2);
			other.setFont(ft2);
			ctb.setFont(ft1);
			cib.setFont(ft1);
			cmb.setFont(ft1);
			cab.setFont(ft1);
			itb.setFont(ft2);
			iib.setFont(ft2);
			imb.setFont(ft2);
			iab.setFont(ft2);
			cltb.setFont(ft2);
			clib.setFont(ft2);
			clmb.setFont(ft2);
			clab.setFont(ft2);
			etb.setFont(ft2);
			eib.setFont(ft2);
			emb.setFont(ft2);
			eab.setFont(ft2);
			eetb.setFont(ft2);
			eeib.setFont(ft2);
			eemb.setFont(ft2);
			eeab.setFont(ft2);
			mtb.setFont(ft2);
			mib.setFont(ft2);
			mmb.setFont(ft2);
			mab.setFont(ft2);
			mbatb.setFont(ft2);
			mbaib.setFont(ft2);
			mbamb.setFont(ft2);
			mbaab.setFont(ft2);
			mcatb.setFont(ft2);
			mcaib.setFont(ft2);
			mcamb.setFont(ft2);
			mcaab.setFont(ft2);
			otb.setFont(ft2);
			oib.setFont(ft2);
			omb.setFont(ft2);
			oab.setFont(ft2);
			tbl.setFont(ft1);
			ibl.setFont(ft1);
			mbl.setFont(ft1);
			abl.setFont(ft1);
			all.setFont(ft1);
			tb.setFont(ft1);
			ib.setFont(ft1);
			mb.setFont(ft1);
			ab.setFont(ft1);
			
			tbl.setBounds(80,50,90,60);
			ibl.setBounds(180,50,90,60);
			mbl.setBounds(280,50,90,60);
			abl.setBounds(380,50,90,60);
			
			
			cse.setBounds(20,100,90,60);
			it.setBounds(20,150,90,60);
			civil.setBounds(20,200,90,60);
			ece.setBounds(20,250,90,60);
			mech.setBounds(20,300,90,60);
			eee.setBounds(20,350,90,60);
			mca.setBounds(20,400,90,60);
			mba.setBounds(20,450,90,60);
			other.setBounds(20,500,90,60);
			all.setBounds(20,550,90,60);	

			
			ctb.setBounds(100,100,90,60);
			itb.setBounds(100,150,90,60);
			cltb.setBounds(100,200,90,60);
			etb.setBounds(100,250,90,60);
			mtb.setBounds(100,300,90,60);
			eetb.setBounds(100,350,90,60);
			mcatb.setBounds(100,400,90,60);
			mbatb.setBounds(100,450,90,60);
			otb.setBounds(100,500,90,60);
			tb.setBounds(100,550,90,60);
			
			
			cib.setBounds(200,100,90,60);
			iib.setBounds(200,150,90,60);
			clib.setBounds(200,200,90,60);
			eib.setBounds(200,250,90,60);
			mib.setBounds(200,300,90,60);
			eeib.setBounds(200,350,90,60);
			mcaib.setBounds(200,400,90,60);
			mbaib.setBounds(200,450,90,60);
			oib.setBounds(200,500,90,60);
			ib.setBounds(200,550,90,60);
			
			
			cmb.setBounds(300,100,90,60);
			imb.setBounds(300,150,90,60);
			clmb.setBounds(300,200,90,60);
			emb.setBounds(300,250,90,60);
			mmb.setBounds(300,300,90,60);
			eemb.setBounds(300,350,90,60);
			mcamb.setBounds(300,400,90,60);
			mbamb.setBounds(300,450,90,60);
			omb.setBounds(300,500,90,60);
			mb.setBounds(300,550,90,60);
			
			
			cab.setBounds(400,100,90,60);
			iab.setBounds(400,150,90,60);
			clab.setBounds(400,200,90,60);
			eab.setBounds(400,250,90,60);
			mab.setBounds(400,300,90,60);
			eeab.setBounds(400,350,90,60); 
			mcaab.setBounds(400,400,90,60);
			mbaab.setBounds(400,450,90,60);
			oab.setBounds(400,500,90,60);
			ab.setBounds(400,550,90,60);

			print.setBounds(20,650,80,25);
			print.addActionListener(this);
			
			jp.add(tbl);
			jp.add(tb);
			jp.add(ctb);
			jp.add(itb);
			jp.add(cltb);
			jp.add(etb);
			jp.add(mtb);
			jp.add(eetb);
			jp.add(mcatb);
			jp.add(mbatb);
			jp.add(otb);
			jp.add(ibl);
			jp.add(ib);
			jp.add(cib);
			jp.add(iib);
			jp.add(clib);
			jp.add(eib);
			jp.add(mib);
			jp.add(eeib);
			jp.add(mcaib);
			jp.add(mbaib);
			jp.add(oib);
			jp.add(mbl);
			jp.add(mb);
			jp.add(cmb);
			jp.add(imb);
			jp.add(clmb);
			jp.add(emb);
			jp.add(mmb);
			jp.add(eemb);
			jp.add(mcamb);
			jp.add(mbamb);
			jp.add(omb);
			jp.add(abl);
			jp.add(ab);
			jp.add(cab);
			jp.add(iab);
			jp.add(clab);
			jp.add(eab);
			jp.add(mab);
			jp.add(eeab);
			jp.add(mcaab);
			jp.add(mbaab);
			jp.add(oab);
			jp.add(all);
			jp.add(cse);
			jp.add(it);
			jp.add(civil);
			jp.add(ece);
			jp.add(mech);
			jp.add(eee);
			jp.add(mca);
			jp.add(mba);
			jp.add(other);
			jp.add(print);	
							
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
    		if (page > 0)
 		{ 
      			return NO_SUCH_PAGE;
    		}
    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
		return PAGE_EXISTS;
  	}

	public void actionPerformed(ActionEvent ae12)
	{
		String s=ae12.getActionCommand();
	
		if(s=="Print")
		{
			PrinterJob job = PrinterJob.getPrinterJob();
  	 		job.setPrintable(this);
    			boolean ok = job.printDialog();
    			if (ok) 
			{
      				try 
				{
        					job.print();
      				}
				catch (PrinterException ex) 
				{
       	 				/* The job did not successfully complete */
      				}
    			}
		}
	}
}
//-----------------------------------------------------------------------------------------------------------------------
class FDetails extends JFrame implements ActionListener,Printable
{
	JPanel jp;
	JTable jtable1;
	JScrollPane qscroller1;	
	DefaultTableModel model1;
	Object o2[];
	Dimension d;
	JLabel dept;
	JComboBox cb1;
	JButton print,find;
	Image img;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	FDetails()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			print=new JButton("print");
			find=new JButton("find");
			dept=new JLabel("Department");
			
			
			cb1=new JComboBox();
			cb1.setEditable(true);
			st=con.createStatement();
			rs=st.executeQuery("Select distinct dept from Staff");
			while(rs.next())
			{
				cb1.addItem(rs.getString(1));
			}
			
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("logo.png");
			jp=new JPanel();
			jp.setLayout(null);
			
				

			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);	
			setTitle("Staff Table Generated");
			
			setDefaultCloseOperation(2);
			add(jp);
			setContentPane(jp);
			
			jp.add(print);
			jp.add(find);
			jp.add(cb1);
			jp.add(dept);
			jp.getRootPane().setDefaultButton(find);
			dept.setBounds(10,10,70,20);
			cb1.setBounds(80,10,70,20);
			print.setBounds(300,10,70,20);
			print.addActionListener(this);
			find.setBounds(200,10,70,20);
			find.addActionListener(this);

			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
	    	if (page > 0)
 		{
      			return NO_SUCH_PAGE;
    		}

    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
   		return PAGE_EXISTS;
  	}

  	public void actionPerformed(ActionEvent e)
 	{
		String s=e.getActionCommand();
		if(s=="print")
		{
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
		   	boolean ok = job.printDialog();
		    	if (ok) 
			{	
      				try 
				{
	        				job.print();
      				}
				catch (PrinterException ex) 
				{
		       	 		/* The job did not successfully complete */
      				}
		    	}
		}
		if(s=="find")
		{
			try
			{
				TableColumn column=null;
				model1=new DefaultTableModel(new Object[][]{{"STAFFID","NAME","DEPT","BOOK1","BOOK2","BOOK3","BOOK4","BOOK5"},},new String[]{null,null,null,null,null,null,null,null});
				jtable1=new JTable(model1);
				qscroller1=new JScrollPane(jtable1);
				for(int i=0;i<8;i++)
				{
					column=jtable1.getColumnModel().getColumn(i);
					if(i==1)
					{
						column.setPreferredWidth(65);
					}
					else
					{
						column.setPreferredWidth(15);
					}
				}
				qscroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				qscroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
				jp.add(qscroller1);
			
				qscroller1.setBounds(10,35,d.width-450,d.height-130);
				
				Statement st;
				st=con.createStatement();
				rs=st.executeQuery("select * from staff where dept='"+(String) cb1.getSelectedItem()+"'");
		
				while(rs.next())
				{
					o2=new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
					model1.addRow(o2);	
				}
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
	
	}
	
}
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
class AboutUs extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,l20,l21,l22,dlms,ab1,ab2,ab3,ab4,ab5,ab6;
	JButton cjug;
	JPanel jp;
	Font f1,f2,f3,f4;
	Color c1;
	Dimension d;
	Background bg;
	Image img;
	int a,b,c,e;

	AboutUs()
	{
		d = Toolkit.getDefaultToolkit().getScreenSize();
		a=(d.width-800)/2;
		b=(d.height-600)/2;	
		img = Toolkit.getDefaultToolkit().getImage("Logo.png");
		bg=new Background();
		jp=new JPanel();
		jp.setLayout(null);
		
		setLayout(null);
		setVisible(true);			
		setBounds(a,b,800,600);
		setIconImage(img);			
		setTitle("Department Library Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(jp);
		setContentPane(bg.wrapInBackgroundImage(jp,new ImageIcon(Login.class.getResource("ABg.jpg"))));
		cjug=new JButton(" HARISH.S CSE(2009) MIT");	
		cjug.setOpaque(false);
		cjug.setActionCommand("Site");
		cjug.setContentAreaFilled(false);
		cjug.setBorderPainted(false);
		cjug.setToolTipText("CJUG");		
		dlms=new JLabel("Product Name:Department Library Management System       Version:1.0");

		ab1=new JLabel("This software has been formed by the guidance of our Sir Mr.Yuvaraj.G");
		ab2=new JLabel("Chennai. and our friend Mr.Suresh,MCA . We are very thankful to them for their ");
		ab3=new JLabel("motivation and help in developing this Application. ");
		ab4=new JLabel("And of course we are very happy to implement this Application for the first time ");
		ab5=new JLabel("in our own college.");
		ab6=new JLabel("Thank you !");
	
		l1=new JLabel("Our Sincere thanks to,");
		l2=new JLabel("*Dr.Abbas Mohaiden,Principal");
		l3=new JLabel("*Prof Mr.Partha Sarathy,H.O.D");
		l4=new JLabel("*Prof Mr.ManiRaj.K,Asst. H.O.D");
		l5=new JLabel("*Prof Mr.Anbu Nathan,Dept. Lib.");
		l6=new JLabel("*Prof Miss.Anigo Merjora");
		l7=new JLabel("*Prof Mr.Anto Rakesh.B");
		l8=new JLabel("*Prof Mr.Darwin.J");
		l9=new JLabel("*Prof Miss.Deepika Raaja.S.R");
		l10=new JLabel("*Prof Miss.Jayamabel Rani");
		l11=new JLabel("*Prof Miss.Jayarathinam");
		l12=new JLabel("*Prof Miss.Jenila");
		l13=new JLabel("*Prof Mr.Justindhas.Y");
		l14=new JLabel("*Prof Mrs.Mary Subaja");
		l15=new JLabel("*Prof Mrs.Merlin Mary Jenitha");		
		l16=new JLabel("*Prof Mrs.Nalini.S");
		l17=new JLabel("*Prof Mr.Ponnuvel.C");
		l18=new JLabel("*Prof Miss.Poovizhi.V");
		l19=new JLabel("*Prof Miss Tamilvizhi");

		l20=new JLabel("Contact Us:");
		l21=new JLabel("BLOG:http://harishcsemit.blogspot.in");
		l22=new JLabel("EMAIL-ID:harish_sci@hotmail.com");
		
		
		f1=new Font("Times New Roman",2,16);
		f2=new Font("Times New Roman",1,18);
		f3=new Font("Times New Roman",1,16);
		f4=new Font("Algerian",1,16);
		c1=new Color(0,100,0);
				
		l1.setFont(f2);
		l2.setFont(f3);
		l3.setFont(f3);
		l4.setFont(f1);
		l5.setFont(f1);
		l6.setFont(f1);
		l7.setFont(f1);
		l8.setFont(f1);
		l9.setFont(f1);
		l10.setFont(f1);
		l11.setFont(f1);
		l12.setFont(f1);
		l13.setFont(f1);
		l14.setFont(f1);
		l15.setFont(f1);
		l16.setFont(f1);
		l17.setFont(f1);
		l18.setFont(f1);
		l19.setFont(f1);
		l20.setFont(f2);
		l21.setFont(f3);
		l22.setFont(f3);
		cjug.setFont(f4);
		dlms.setFont(f3);
		ab1.setFont(f1);
		ab2.setFont(f1);
		ab3.setFont(f1);
		ab4.setFont(f1);
		ab5.setFont(f1);
		ab6.setFont(f1);

		l1.setForeground(c1);
		l2.setForeground(c1);
		l3.setForeground(c1);
		l4.setForeground(c1);
		l5.setForeground(c1);
		l6.setForeground(c1);
		l7.setForeground(c1);
		l8.setForeground(c1);
		l9.setForeground(c1);
		l10.setForeground(c1);
		l11.setForeground(c1);
		l12.setForeground(c1);
		l13.setForeground(c1);
		l14.setForeground(c1);
		l15.setForeground(c1);
		l16.setForeground(c1);
		l17.setForeground(c1);
		l18.setForeground(c1);
		l19.setForeground(c1);
		l20.setForeground(c1);
		l21.setForeground(c1);
		l22.setForeground(c1);
		dlms.setForeground(c1);
		cjug.setForeground(c1);
		ab1.setForeground(c1);
		ab2.setForeground(c1);
		ab3.setForeground(c1);
		ab4.setForeground(c1);
		ab5.setForeground(c1);
		ab6.setForeground(c1);


		jp.add(l1);
		jp.add(l2);
		jp.add(l3);
		jp.add(l4);
		jp.add(l5);
		jp.add(l6);
		jp.add(l7);
		jp.add(l8);
		jp.add(l9);
		jp.add(l10);
		jp.add(l11);
		jp.add(l12);
		jp.add(l13);
		jp.add(l14);
		jp.add(l15);
		jp.add(l16);
		jp.add(l17);
		jp.add(l18);
		jp.add(l19);
		jp.add(l20);
		jp.add(l21);
		jp.add(l22);
		jp.add(dlms);
		jp.add(cjug);
		jp.add(ab1);
		jp.add(ab2);
		jp.add(ab3);
		jp.add(ab4);
		jp.add(ab5);
		jp.add(ab6);
				
		c=230;
		e=230;
		
		cjug.setBounds(370,25,250,20);
		dlms.setBounds(250,55,500,20);
		
		ab1.setBounds(250,110,550,20);
		ab2.setBounds(200,130,550,20);
		ab3.setBounds(200,150,550,20);
		ab4.setBounds(250,170,550,20);		
		ab5.setBounds(200,190,550,20);		
		ab6.setBounds(450,210,80,20);		
		
		l1.setBounds(400,e,180,20);
		l2.setBounds(c,e+25,270,15);
		l4.setBounds(c,e+45,270,15);
		l5.setBounds(c,e+65,270,15);
		l6.setBounds(c,e+85,220,15);
		l7.setBounds(c,e+105,270,15);
		l8.setBounds(c,e+125,270,15);
		l9.setBounds(c,e+145,270,15);
		l10.setBounds(c,e+165,270,15);
		l11.setBounds(c,e+185,270,15);
		l3.setBounds(c+300,e+25,270,15);
		l12.setBounds(c+300,e+45,270,15);
		l13.setBounds(c+300,e+65,270,15);
		l14.setBounds(c+300,e+85,270,15);
		l15.setBounds(c+300,e+105,270,15);
		l16.setBounds(c+300,e+125,270,15);
		l17.setBounds(c+300,e+145,270,15);
		l18.setBounds(c+300,e+165,270,15);
		l19.setBounds(c+300,e+185,270,15);

		l20.setBounds(500,e+250,100,20);
		l21.setBounds(500,e+270,300,20);
		l22.setBounds(500,e+290,300,20);
		cjug.addActionListener(this);
		jp.getRootPane().setDefaultButton(cjug);
		dlms.requestFocus();

	}
	public void actionPerformed(ActionEvent ae1)
	{
		String s=ae1.getActionCommand();
		if(s=="Site")
		{	
			Desktop desktop = Desktop.getDesktop();                                        
			try 
			{                                                
				desktop.browse(new java.net.URI("http://www.cityjava.blogspot.com"));      
			}
			catch(Exception e)
			{
				System.out.println(e); 
			}
		}
	} 
}                   
class Details2 extends JFrame implements ActionListener,Printable
{
	JPanel jp;
	JTable jtable1;
	JScrollPane qscroller1;	
	DefaultTableModel model1;
	Object o2[];
	Dimension d;
	JLabel batch,sec,l1;
	JTextField tf1;
	JComboBox cb1,cb2;
	JButton print,find;
	Image img;
	
	Connection con;
	Statement st;
	ResultSet rs;
	
	Details2()
	{
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:library","Admin","libsys");
			
			print=new JButton("print");
			find=new JButton("find");
			batch=new JLabel("Batch");
			sec=new JLabel("SEC");
			l1=new JLabel("Total");
			tf1=new JTextField(20);
			
			cb1=new JComboBox();
			cb1.setEditable(true);
			st=con.createStatement();
			rs=st.executeQuery("Select distinct batch from student");
			while(rs.next())
			{
				cb1.addItem(rs.getString(1));
			}
			
			cb2=new JComboBox();
			cb2.setEditable(true);
			cb2.addItem("B");
			cb2.addItem("A");
			d = Toolkit.getDefaultToolkit().getScreenSize();
			img = Toolkit.getDefaultToolkit().getImage("logo.png");
			jp=new JPanel();
			jp.setLayout(null);
			
				

			setLayout(null);
			setVisible(true);			
			setSize(d.width,d.height);
			setIconImage(img);	
			setTitle("Student Table Generated");
			
			setDefaultCloseOperation(2);
			add(jp);
			setContentPane(jp);
			jp.add(l1);
			jp.add(tf1);
			jp.add(print);
			jp.add(find);
			jp.add(cb1);
			jp.add(cb2);
			jp.add(batch);
			jp.add(sec);
			jp.getRootPane().setDefaultButton(find);
			batch.setBounds(10,10,70,20);
			cb1.setBounds(60,10,70,20);
			sec.setBounds(160,10,70,20);
			cb2.setBounds(200,10,70,20);
			print.setBounds(400,10,70,20);
			l1.setBounds(500,10,50,20);
			tf1.setBounds(560,10,40,20);
			print.addActionListener(this);
			find.setBounds(300,10,70,20);
			find.addActionListener(this);
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException
 	{
	    	if (page > 0)
 		{
      			return NO_SUCH_PAGE;
    		}
    		Graphics2D g2d = (Graphics2D) g;
    		g2d.translate(pf.getImageableX(), pf.getImageableY());
    		this.printAll(g);
    		return PAGE_EXISTS;
  	}

  	public void actionPerformed(ActionEvent e)
 	{
		String s=e.getActionCommand();
		if(s=="print")
		{
   			PrinterJob job = PrinterJob.getPrinterJob();
  	 		job.setPrintable(this);
    			boolean ok = job.printDialog();
    			if (ok) 
			{
      				try 
				{
        					job.print();
      				}
				catch (PrinterException ex) 
				{
       	 				/* The job did not successfully complete */
      				}
    			}
		}
		if(s=="find")
		{
			try
			{	
				try
				{
					String s1=(String) cb1.getSelectedItem();
					String s2=(String) cb2.getSelectedItem();
					String s3="";
					st=con.createStatement();
					rs=st.executeQuery("select count(*) from student where batch='"+s1+"' and section='"+s2+"'");
					while(rs.next())
					{
						s3=rs.getString(1);
					}
					tf1.setText(s3);
								
	  			}
				catch(Exception ps)
				{
					System.out.println(ps);
				}	
				TableColumn column=null;
				model1=new DefaultTableModel(new Object[][]{{"REGNO","NAME","BATCH","SECTION"},},new String[]{null,null,null,null});
				jtable1=new JTable(model1);
				qscroller1=new JScrollPane(jtable1);
			
				for(int i=0;i<4;i++)
				{
					column=jtable1.getColumnModel().getColumn(i);
					if(i==0)
					{
						column.setPreferredWidth(60);
					}
					else
					{
						column.setPreferredWidth(30);
					}
				}

				qscroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				qscroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
				jp.add(qscroller1);
			
				qscroller1.setBounds(10,35,d.width-330,d.height-130);
				
				Statement st;
				st=con.createStatement();
				rs=st.executeQuery("select * from student where batch='"+(String) cb1.getSelectedItem()+"' and section='"+ (String) cb2.getSelectedItem()+"'");
	
				while(rs.next())
				{	
					o2=new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),};
					model1.addRow(o2);	
				}
			}
			catch(Exception e1)
			{	
				System.out.println(e1);
			}
		}

		if(s=="Clear")
		{
			try
			{
				Statement st;
				st=con.createStatement();
				model1.removeRow(1);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
	  }
	
}

//------------------------------------------------------------------------------------------------------------------------------                                                                    