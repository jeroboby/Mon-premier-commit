package com.sopra.pox3.programme;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BibliothequeApplication {

	public static void main(String[] args) throws DisqueExisteDejaException {
		Bibliotheque bibliotheque = new Bibliotheque("medicis");

		Disque d1 = new Disque("Toto", "LEJFBDEJF");
		d1.ajouterChanson(new Chanson("Hello", 90));
		bibliotheque.ajouterDisque(d1);
		bibliotheque.afficherDisques();

		Disque d2 = new Disque("patoch", "JEIENE");
		d2.ajouterChanson(new Chanson("Coli", 90));
		bibliotheque.ajouterDisque(d2);
		bibliotheque.afficherDisques();

		while (true) {
			System.out.println("1. Liste des disques");
			System.out.println("2. Liste des disques par CB");
			System.out.println("3. Recherche d'une chanson");
			System.out.println("4. Recherche d'un disque");
			System.out.println("5. Supprimer un disque");
			System.out.println("6. Ajouter disque");
			System.out.println("7. Sauvergarder la mediatheque");
			System.out.println("8. Charger la mediatheque");
			System.out.println("Q. Quitter");

			String saisie = Saisie.saisie("Quel est ton choix ?");

			switch (saisie) {
			case "1":
				bibliotheque.afficherDisques();
				break;
			case "2":
				String codeBarre = Saisie.saisie("code barres stp");
				Disque disqueTrouve = bibliotheque.getDisque(codeBarre);
				if (disqueTrouve == null)
					System.out.println("Pas de disque avec ce CB");

				else
					disqueTrouve.afficherDetails();
				break;
			case "3":
				String motrecherche = Saisie.saisie("nom de la chanson stp");
				List<Chanson> chansontrouve = bibliotheque.rechercherChansons(motrecherche);
				if (chansontrouve == null || chansontrouve.isEmpty())
					System.out.println("Pas de chanson");
				else
					System.out.println("" + chansontrouve);
				break;
			case "4":
				String disquerecherche = Saisie.saisie("nom du disque stp");
				List<Disque> disqueTrouve1 = bibliotheque.rechercherDisques(disquerecherche);
				if (disqueTrouve1 == null || disqueTrouve1.isEmpty())
					System.out.println("Pas de disque");
				else
					System.out.println("" + disqueTrouve1);
				break;
			case "5":
				bibliotheque.afficherDisques();
				String CodeBarreEnlever = Saisie.saisie("code barre à supprimer");
				bibliotheque.retirerDisque(CodeBarreEnlever);
				bibliotheque.afficherDisques();
				break;
			case "6":
				String nomdudisque = Saisie.saisie("Nom du disque a ajouter");
				String codebarre = Saisie.saisie("Code barre du disque ?");
				Disque disque = new Disque(nomdudisque, codebarre);
				while (true) {
					Chanson chanson = saisirChanson();
					if (chanson != null) {
						disque.ajouterChanson(chanson);

					} else {
						break;
					}
				}
				try {
					bibliotheque.ajouterDisque(disque);
					System.out.println("Le disque est bien ajoute");
				} catch (DisqueExisteDejaException e) {
					System.out.println("Impossible d'ajouter le disque");
				}
				break;
			case "7":
				bibliotheque.sauverDisque("ma mediatheque.txt");
				System.out.println("La mediatheque a bien ete sauvergarde");
				break;
			case "8":
				bibliotheque.chargerDisque("ma mediatheque.txt");
				System.out.println("Le fichier a bien ete ouvert");
				break;
			case "Q":
			case "q":
				System.exit(0);
				break;
			default:
				System.out.println("Ce choix n'existe pas !");
			}
		}

	}

	private static Chanson saisirChanson() {
		String nom = Saisie.saisie("Nom de la chanson ?");
		if (nom == null || nom.isEmpty())
			return null;
		int duree = Saisie.saisieInt("Duree ?");
		return new Chanson(nom, duree);
	}
}
