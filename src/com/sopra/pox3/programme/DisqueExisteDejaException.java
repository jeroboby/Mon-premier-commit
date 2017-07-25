package com.sopra.pox3.programme;

public class DisqueExisteDejaException extends Exception {
	public DisqueExisteDejaException(Disque disqueEnErreur) {
		
		super("Existe deja (" + disqueEnErreur.getCodeBarre()+ ")");
	}
}
