package com.sopra.pox3.programme;

import java.util.ArrayList;
import java.util.List;

public class Disque {
	private String name;
	private String codeBarre;
	private List<Chanson> song;

	public Disque(String name, String codeBarre) {
		this.name = name;
		this.codeBarre = codeBarre;
		song = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public List<Chanson> getSong() {
		return song;
	}

	public void setSong(List<Chanson> song) {
		this.song = song;
	}

	void ajouterChanson(Chanson chanson) {
		song.add(chanson);
	}

	int getduree() {
		int result = 0;
		for (int i = 0; i < song.size(); i++) {
			result = result + song.get(i).getDuration();

		}
		return result;
	}

	public void afficher() {
		System.out.println("Disque :" + name);
		System.out.println("code barre :" + codeBarre);
	}

	public void afficherDetails() {
		afficher();

		for (Chanson chanson : song)
			chanson.afficher();
	}

	@Override
	public String toString() {
		return "Disque [name=" + name + ", codeBarre=" + codeBarre + ", song=" + song + "]";
	}
}