package com.sopra.pox3.programme;

public class Chanson {

	private String name;
	private int duration;

	public Chanson(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void afficher(){
		System.out.println("Disque :" + name);
		System.out.println("Duree :" + duration);
	}

	@Override
	public String toString() {
		return "Chanson [name=" + name + ", duration=" + duration + "]";
	}
	
}
