package main.model;

import java.util.ArrayList;

public class Dot {
	private int name;
	private ArrayList<Compound> compounds = new ArrayList<>();
	public Dot(int name) {
		this.name = name;
	}
	public void setDotName(int name) {
		this.name = name;
	}
	
	public int getDotName() {
		return this.name;
	}
	
	public void setCompounds(ArrayList<Compound> compounds) {
		this.compounds = compounds;
	}
	
	public ArrayList<Compound> getCompaunds(){
		return this.compounds;
	}
	
	public void addCompound(Compound compound) {
		this.compounds.add(compound);
	}
}
