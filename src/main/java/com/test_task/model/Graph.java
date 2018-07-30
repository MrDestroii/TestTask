package com.test_task.model;

import java.util.ArrayList;

public class Graph {
	private ArrayList<Dot> dotsList = new ArrayList<Dot>();

	public void setDotsList(ArrayList<Dot> dotsList) {
		this.dotsList = dotsList;
	}

	public ArrayList<Dot> getDotsList() {
		return this.dotsList;
	}
	
	public void addDot(Dot dot) {
		this.dotsList.add(dot);
	}
}
