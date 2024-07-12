import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame implements ActionListener
{
	private JButton btnNew ;
	private JButton btnSave;
	private Controleur ctrl;
	private JComboBox jcbSave;

	public Menu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Menu");
		this.setSize(400,200);
		this.setLayout(new GridLayout(3,2));
		

		this.btnNew  = new JButton("New Partie");
		this.btnSave = new JButton("Continuer");

		//ajout des item dans la liste deroulante
		String[] tmp = new String[this.ctrl.nbSave().size()];
		for(int cpt = 0; cpt < tmp.length; cpt++)
			tmp[cpt] = this.ctrl.nbSave().get(cpt);

		this.jcbSave = new JComboBox<String>(tmp);
		
		this.add(new JLabel("Lancer une nouvelle partie :"));
		this.add(new JLabel("Reprendre une partie :"));
		this.add(this.btnNew );
		this.add(this.btnSave);
		this.add(new JLabel(""));
		this.add(this.jcbSave);

		this.btnNew .addActionListener(this);
		this.btnSave.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnNew)
			this.ctrl.lancerJeu();
		

		if (e.getSource() == this.btnSave) 
		{
			this.ctrl.lancerJeu();
			this.ctrl.chargerSave((String) this.jcbSave.getSelectedItem());
		}
		this.dispose();
	}
}
