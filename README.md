	public class DisqueExisteDejaException extends Exception {
		public DisqueExisteDejaException(Disque disqueEnErreur) {
			
			super("Existe deja (" + disqueEnErreur.getCodeBarre()+ ")");
		}
	}
