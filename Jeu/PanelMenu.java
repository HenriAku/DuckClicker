import javax.swing.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

public class PanelMenu extends JPanel implements ActionListener 
{
	private JButton[] tabBnt       ;
	private FrameA    frame        ;
	private Point     point        ;
	private int       mutiplicateur;

	public PanelMenu(FrameA frame)
	{
		this.setLayout(new GridLayout(5,1,10,10));

		//initialisation des Attribut
		this.frame = frame;
		this.point = new Point(frame);

		this.mutiplicateur = 0;
		
		this.tabBnt = new JButton[5];
		String[] tabName = {"X2","new Clique","time - 300","Double clicke","RPG"};

		//creation des btn
		this.tabBnt[0] = new JButton("<html><center>"+tabName[0]+"<br><font size='2'>Double le nombre <br> de point du boutton  </font></center></html>");
		this.tabBnt[1] = new JButton("<html><center>"+tabName[1]+"<br><font size='2'>Ajoute un autre clique <br> toute les 3000s</font></center></html>");
		this.tabBnt[2] = new JButton("<html><center>"+tabName[2]+"<br><font size='2'>Reduit le temps <br> des autre clique      </font></center></html>");
		this.tabBnt[3] = new JButton("<html><center>"+tabName[3]+"<br><font size='2'>Clique 2 fois   <br> chauqe clique         </font></center></html>");
		this.tabBnt[4] = new JButton("<html><center>"+tabName[4]+"<br><font size='2'>clique 10 fois  <br> chauqe clique         </font></center></html>");

		

		//positionement et activation
		for(int i=0;i<this.tabBnt.length;i++)
		{
			this.tabBnt[i].setBackground(new Color(200,150,200));

			this.add(this.tabBnt[i]);

			this.tabBnt[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.tabBnt[0]) 
		{
			mutiplicateur+= 2;
			this.point.setBonus(mutiplicateur);
		}

		if (e.getSource()==this.tabBnt[1]) 
			this.point.newClique();

		if (e.getSource()==this.tabBnt[2]) 
			this.point.reductionTime();

		if (e.getSource()==this.tabBnt[3]) 
			this.point.doubleClique(1);
		
		if (e.getSource()==this.tabBnt[4]) 
			this.point.doubleClique(2);	
	}

	public int getMutiplicateur () {return mutiplicateur;}
	public void setMutiplicateur(int mutiplicateur) {this.mutiplicateur =  mutiplicateur;}

}
