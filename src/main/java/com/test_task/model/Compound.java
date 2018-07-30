package com.test_task.model;

public class Compound {
	private int toDotName;
	private int weight;
	public Compound(int toDotName, int weight){
		this.toDotName = toDotName;
		this.weight = weight;
	}
	public Compound() {
		
	}
	public void setToDotName(int toDotName) {
		this.toDotName = toDotName;
	}
	
	public int getToDotName() {
		return this.toDotName;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return this.weight;
	}
}
