import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class PanelBtn extends JPanel implements ActionListener
{
	private JButton btn  ;
	private FrameA  frame;
	private Point   point;
	private JLabel  lbl;

	public PanelBtn(FrameA frame)
	{
		//initialisation Attribut
		this.frame = frame;
		this.point = new Point(frame);

		//creation du btn
		this.btn   = new JButton( new ImageIcon (  "cannard.png"   ) );
		this.btn.setOpaque(false);
		this.btn.setContentAreaFilled(false);
		this.btn.setBorderPainted(false);

		//Dimention du btn
		this.btn.setPreferredSize ( new Dimension (700,700) );
		this.add(btn,BorderLayout.CENTER);
		this.btn .setBackground (new Color(158, 103, 243 ));

		//activation
		this.btn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btn)  
			this.point.ajouterPoint();  
	}

}