package com.sopra.pox3.programme;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
	String name;
	List<Disque> mediatheque = new ArrayList<>();

	public Bibliotheque(String name) {
		this.name = name;
	}

	public void afficherDisques() {

		System.out.println("Liste des disques de la bibliotheque");
		System.out.println("Il y a " + mediatheque.size() + " disque(s)");
		for (int i = 0; i < mediatheque.size(); i++) {
			System.out.println(mediatheque.get(i));
		}

	}

	public List<Chanson> rechercherChansons(String recherche) {
		recherche = recherche.toLowerCase();
		List<Chanson> listechansontrouve = new ArrayList<>();
		for (int i = 0; i < mediatheque.size(); i++) {
			Disque DisqueNoi = mediatheque.get(i);
			List<Chanson> listechanson = DisqueNoi.getSong();
			for (int j = 0; j < listechanson.size(); j++) {
				Chanson ChansonNoi = listechanson.get(j);
				/*
				 * if (recherche.equals(ChansonNoi.getName())) {
				 * listechansontrouve.add(ChansonNoi);
				 * 
				 * }
				 */
				if (ChansonNoi.getName().toLowerCase().contains(recherche)) {
					listechansontrouve.add(ChansonNoi);
				}

			}
		}
		return listechansontrouve;
	}

	public List<Disque> rechercherDisques(String recherche) {
		List<Disque> listeDisqueTrouve = new ArrayList<>();
		for (int i = 0; i < mediatheque.size(); i++) {
			Disque disqueNoi = mediatheque.get(i);
			if (recherche.equals(disqueNoi.getCodeBarre())) {
				listeDisqueTrouve.add(disqueNoi);
			}
		}
		return listeDisqueTrouve;
	}

	boolean retirerDisque(String codeBarre) {

		for (int i = 0; i < mediatheque.size(); i++) {
			Disque disqueNi = mediatheque.get(i);
			if (codeBarre.equals(disqueNi.getCodeBarre())) {
				mediatheque.remove(disqueNi);
				System.out.println("disque retire");
				return true;
			}
		}
		System.out.println("Disque inconnu");
		return false;
	}

	void ajouterDisque(Disque disque) throws DisqueExisteDejaException {
		List<Disque> disques = rechercherDisques(disque.getCodeBarre());
		if (disques != null && !disques.isEmpty()) {
			throw new DisqueExisteDejaException(disque);
		}

		mediatheque.add(disque);

	}

	public Disque getDisque(String codeBarre) {
		for (Disque disque : mediatheque) {
			String codeBarreDisque = disque.getCodeBarre();
			if (codeBarreDisque.equals(codeBarre))
				return disque;
		}

		return null;
	}

	public void sauverDisque(String fileToSave) {
		File file = new File(fileToSave);
		try {
			FileOutputStream fos = new FileOutputStream(file, false);
			fos.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		for (int i = 0; i < mediatheque.size(); i++) {

			try {
				FileOutputStream fos = new FileOutputStream(file, true);

				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");

				Writer out = new BufferedWriter(osw);

				out.append(mediatheque.get(i).getName()).append("-");
				out.append(mediatheque.get(i).getCodeBarre()).append("\r\n");

				out.flush();

				out.close();

				osw.close();

				fos.close();
			}
			// new FileOutputStream( file );
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			// new OutputStreamWriter( fos, "UTF8" );
			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// append(), flush() et close()
			catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void chargerDisque(String fileToRead) throws DisqueExisteDejaException {

		File file = new File(fileToRead);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);

			InputStreamReader isr = new InputStreamReader(fis, "UTF8");

			BufferedReader reader = new BufferedReader(isr);

			while (true) {
				String nom = reader.readLine();
				if (nom == null)
					break;
				String codeBarre = reader.readLine();
				Disque disque = new Disque(nom, codeBarre);
				ajouterDisque(disque);
			}

			reader.close();

			isr.close();

			fis.close();
		}
		// FileInputStream
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// InputStreamReader
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// reader.readLine();, reader.close(); isr.close(), fis.close()
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
