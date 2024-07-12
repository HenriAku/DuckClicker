import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FrameA extends JFrame implements ActionListener
{
	private final Color VIOLET  = new Color(147, 129, 255);
	private final Color VIOLETD = new Color(184, 184, 255);
	private final Color BLANCV  = new Color(248, 247, 255);

	private JLabel       scoore;
	private JProgressBar bar   ;
	private JButton      btn   ;

	private int   max  ;
	private int   lvl  ;

	private Point point;

	private PanelBtn  panelBnt ;
	private PanelMenu panelMenu;

	private Controleur ctrl;
	
	public FrameA(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Cliker");
		this.setLayout(new BorderLayout());

		//initialisation des attribut
		this.lvl    = 0;
		this.max    = 5;
		this.point  = new Point(this);

		//creation des composent
		JPanel panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTop.setBackground (this.VIOLETD);

		this.btn = new JButton( new ImageIcon (  "paramettre2.png"   ));
		this.btn.setPreferredSize(new Dimension(37, 37));
		this.btn.setContentAreaFilled(false);
		this.btn.setBorderPainted(false);

		this.scoore    = new JLabel   (0 +" pt \t Niveau : 0" , SwingConstants.RIGHT);

		//ProgresseBar
		this.bar = new JProgressBar();
		this.bar.setMaximum(this.max);
		this.bar.setStringPainted(true);
		this.bar.setPreferredSize(new Dimension(600, 20));


		//add des panel
		this.panelBnt  = new PanelBtn (this);
		this.panelBnt  .setBackground (this.VIOLETD);
		this.panelMenu = new PanelMenu(this);
		this.panelMenu .setBackground (this.VIOLETD);
		
		// Ajout des marges entre les panneaux
        int marginSize = 10; // taille de la marge
        panelTop      .setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));
        this.panelBnt .setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));
        this.panelMenu.setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));

		//Activation des composent
		panelTop.add(this.btn);
        panelTop.add(Box.createHorizontalStrut(60)); // Pour pousser la barre au centre
        panelTop.add(this.bar);
        panelTop.add(Box.createHorizontalStrut(70)); // Espacement entre la barre et le scoore
        panelTop.add(this.scoore);
		
		this.add(panelTop      ,BorderLayout.NORTH );
		this.add(this.panelBnt ,BorderLayout.CENTER);
		this.add(this.panelMenu,BorderLayout.EAST  );

		this.btn.addActionListener(this);

		//Fermeture fenetre + visibiliter 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.pack();
		this.setVisible(true);
	}

	public void scoore(int scoore)
	{
		if (scoore >= this.bar.getMaximum()) 
		{
			this.bar.setMinimum(this.max);
			this.bar.setMaximum(this.max = this.max *2);
			this.lvl++;	
			this.bar.setValue(0);
		}

		this.scoore.setText(scoore +" pt " );

		this.bar.setValue (scoore);
		this.bar.setString(scoore+ " / " + this.max +" \t Niveaux : " + this.lvl);
	}

	public int  getMutiplicateur () {return this.panelMenu.getMutiplicateur();}
	public Point getPoint () {return this.point;}
	public void setMutiplicateur(int mutiplicateur) {this.panelMenu.setMutiplicateur(mutiplicateur);}

	public void fermer(){this.dispose();}

	public void actionPerformed(ActionEvent e) 
	{
		new Paramettre(this.ctrl);
	}


}
