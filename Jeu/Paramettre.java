import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Paramettre extends JFrame implements ActionListener
{
	private JButton btnNew ;
	private JButton btnSave;
	private JButton btnQuite;

	private Controleur ctrl;
	private JComboBox jcbSave;

	public Paramettre(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Menu");
		this.setSize(400,200);
		this.setLayout(new GridLayout(3,2));
		

		this.btnNew  = new JButton(new ImageIcon("Save.png"));
		this.btnNew.setPreferredSize(new Dimension(37, 37));
		this.btnNew.setContentAreaFilled(false);
		this.btnNew.setBorderPainted(true);

		this.btnSave  = new JButton("Continuer");
		this.btnQuite = new JButton("Quiter");


		//ajout des item dans la liste deroulante
		String[] tmp = new String[this.ctrl.nbSave().size()];
		for(int cpt = 0; cpt < tmp.length; cpt++)
			tmp[cpt] = this.ctrl.nbSave().get(cpt);

		this.jcbSave = new JComboBox<String>(tmp);
		
		this.add(new JLabel("Sauvegarder"));
		this.add(this.btnNew  );
		this.add(this.btnSave );
		this.add(this.jcbSave );
		this.add(this.btnQuite);

		
		this.btnNew  .addActionListener(this);
		this.btnSave .addActionListener(this);
		this.btnQuite.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnNew)
		{
			if (!this.ctrl.getFirstSave()) 
			{
				this.ctrl.sauvegarder();
				this.ctrl.setSave(this.ctrl.nbSave().get(this.ctrl.nbSave().size()-1));
				this.ctrl.setFirstSave();
			}
			else
				this.ctrl.sauvegarder();
	
		}

		if (e.getSource() == this.btnSave) 
		{
			this.ctrl.sauvegarder();
			this.ctrl.chargerSave((String) this.jcbSave.getSelectedItem());
			this.ctrl.setSave((String) this.jcbSave.getSelectedItem());
			this.ctrl.setFirstSave();
		}

		if (e.getSource() == this.btnQuite) 
		{
			this.ctrl.fermerFenetre();
		}
		this.dispose();
	}
}
