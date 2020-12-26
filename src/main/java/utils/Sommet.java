package utils;

import java.util.ArrayList;
import java.util.List;

public class Sommet {
	int id;
	List<Sommet> voisins;
	
	public Sommet(int id) 
	{
		this.id = id;
		voisins = new ArrayList<Sommet>();
	}
	
	public void addVoisin(Sommet nouveauVoisin) 
	{
		voisins.add(nouveauVoisin);
	}
	
	public List<Sommet> getVoisins()
	{
		return voisins;
	}
	
	public int getId() 
	{
		return id;
	}
}
