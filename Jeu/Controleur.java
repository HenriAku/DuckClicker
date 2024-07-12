import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Controleur 
{
	private FrameA  framejeu  ;
	private Point   point     ;
	private Menu    menu	  ;
	private String  saveActuel;
	private boolean firstSave ;

	public Controleur()
	{
		this.menu = new Menu(this);
		this.saveActuel = null;
		this.firstSave = false;
	}

	public String getSave       ()  		 {return this.saveActuel;}
	public boolean getFirstSave ()  		 {return this.firstSave ;}

	public void   setSave      (String save) {this.saveActuel = save;}
	public void   setFirstSave ()            {this.firstSave  = true;}

	public void lancerJeu()
	{
		this.framejeu = new FrameA(this);
		this.point	  = this.framejeu.getPoint();
	}

	public void fermerFenetre()
	{
		this.framejeu.fermer();
		this.menu = new Menu(this);
	}

	public List<String> nbSave()
	{
		List<String> tabS = new ArrayList<String>();
		String ligne;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Save.txt"))))
		{
			while ((ligne = reader.readLine()) != null)
			{
				String[] tabTmp = ligne.split(",");
				tabS.add(tabTmp[0]) ;
				//String p = ligne.substring(0, ligne.indexOf(","));
				//System.out.println(p);
			}

		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}

		return  tabS;
	}

	public void chargerSave(String save)
	{
		String ligne;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Save.txt"))))
		{
			while ((ligne = reader.readLine()) != null)
			{
				String[] tabTmp = ligne.split(",");
				if (tabTmp[0].equals(save)) 
				{
					Point.setScoore (Integer.parseInt(tabTmp[1].trim()));
					Point.setBonu   (Integer.parseInt(tabTmp[2].trim()));
					Point.setTime   (Integer.parseInt(tabTmp[3].trim()));
					Point.setDoubleC(Boolean.parseBoolean(tabTmp[4].trim()));
					Point.setRpg    (Boolean.parseBoolean(tabTmp[5].trim()));

					this.framejeu.setMutiplicateur(Integer.parseInt(tabTmp[6].trim()));

					int[] margeTmp = new int[5];
					for(int cpt=0; cpt<5 ; cpt++)
						margeTmp[cpt] = Integer.parseInt(tabTmp[6+cpt+1].trim());

					this.point.setMarge(margeTmp);
				}
			}

		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}


	public void sauvegarder()
	{
		String name = "";
			if (this.getSave() != null) 
				name = this.getSave();
			else
				name = "Save"+ (this.nbSave().size() +1);
			//creation de la ligne
			String newLine =  	name +", "+
								Point.getScoore     ()+", " +
								Point.getBonus      ()+", " +
								Point.getTime       ()+", " +
								Point.isDoubleC     ()+", " +
								Point.isRpg         ()+", " +

								this.framejeu.getMutiplicateur()+", ";

			int[] tmp = new int[this.point.getMarge().length];
			tmp = this.point.getMarge();
			for(int cpt =0 ; cpt < 5; cpt++)
				newLine += tmp[cpt] +", ";

		List<String> lines = new ArrayList<>();
		try (PrintWriter writer = new PrintWriter(new FileOutputStream("Save.txt", true)))
		{
			// Lire toutes les lignes du fichier et les stocker dans une liste
			File file = new File("Save.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null)
				lines.add(line);
			reader.close();
		}
		catch (IOException i)
		{
			i.printStackTrace();
		}

		if (this.getFirstSave()) 
		{
			try (PrintWriter writer = new PrintWriter(new FileOutputStream("Save.txt", false)))
			{

				// Trouver la ligne contenant "saveX" et la remplacer
				boolean found = false;
				for (int i = 0; i < lines.size(); i++) {
					if (lines.get(i).contains(this.getSave())) {
						lines.set(i, newLine);
						found = true;
						break;
					}
				}

				// Écrire toutes les lignes modifiées de nouveau dans le fichie
				for (String l : lines) 
					writer.println(l);
				
				writer.close();
			}
		 	catch (IOException i)
			{
				i.printStackTrace();
			}
		}
		else{
			try (PrintWriter writer = new PrintWriter(new FileOutputStream("Save.txt", true)))
			{
				writer.println(newLine);
			}
			catch (IOException i)
			{
				i.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Controleur();
	}

}
