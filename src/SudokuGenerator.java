import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

	protected static int colonnes = 9;
	protected static int lignes = 9;
	private static int pourcentageGrilleCachee = 60; // à modifier selon la difficulté
	private static List<Integer> listeAleatoire = new ArrayList<Integer>();
	protected static int[][] grilleR = new int[lignes][colonnes];

	public SudokuGenerator() {
		int[][] grille = new int[lignes][colonnes];
		initialisationGrille(grille);

		while(!remplissageEntierGrille(grille)) {
			initialisationGrille(grille);
		}
		affichageGrille(grille);
	}
	

	/*
	 * MaFonction : initialisationGrille
	 * Rôle : initialisation du tableau qui va remplir le grille avec que des 0
	 */
	private static void initialisationGrille(int[][] tableau) {
		for(int i=0;i<tableau.length;i++) {
			for(int j=0;j<tableau.length;j++) {
				tableau[i][j] = 0;
			}
		}
	}

	/*
	 * MaFonction : remplissageEntierGrille
	 * Rôle : remplir la grille avec un nombre aléatoire en vérifiant qu'il est seul
	 */
	private static boolean remplissageEntierGrille(int[][] tableau) {
		int nbrAleatoire;
		int echec = 0;
		Random generateurAleatoire = new Random((new Date().getTime()));
		nbrAleatoire = generateurAleatoire.nextInt(colonnes)+1;

		for(int i=0;i<tableau.length;i++) {
			for(int j=0;j<tableau.length;j++) {
				listeAleatoire.clear();
				do {
					nbrAleatoire = generateurAleatoire.nextInt(colonnes)+1;
					if(listeAleatoire.contains(nbrAleatoire)) {
						continue;
					}
					else {
						listeAleatoire.add(nbrAleatoire);
						if(listeAleatoire.size() == colonnes) {
							nbrAleatoire = -1;
							echec++;
							break;
						}
					}
				} while(!verificationEstSeul(nbrAleatoire, tableau, i, j));
				tableau[i][j] = nbrAleatoire;
			}
		}
		return (echec == 0);
	}

	/*
	 * MaFonction : affichageGrille
	 * Rôle : afficher la grille remplie prête à être jouée
	 */
	private static void affichageGrille(int[][] tableau) {

		Random generateurAleatoire = new Random((new Date().getTime()));
		System.out.println("Générateur de Sudoku");
		System.out.println(pourcentageGrilleCachee + "% de la grille est cachée");
		System.out.println(" --------------- ");
		for(int i=0;i<tableau.length;i++) {
			for(int j=0;j<tableau.length;j++) {
				if(generateurAleatoire.nextInt(100) < pourcentageGrilleCachee) { // seulement une partie est affichée selon le pourcentage choisi
					grilleR[i][j] = 0;
					if(j == 2 || j == 5 || j == 8 ) {
						System.out.print("*|");
					}
					else {
						System.out.print("*.");
					}

				}
				else {
					grilleR[i][j] = tableau[i][j];
					if(j == 2 || j == 5 || j == 8 ) {
						System.out.print(tableau[i][j] + "|");
					}
					else {
						System.out.print(tableau[i][j] + ".");
					}
				}

			}
			System.out.println("");
			if(i == 2 || i == 5 || i == 8 ) {
				System.out.println("------------------");
			}

		}

	}

	/*
	 * MaFonction : verificationEstSeul
	 * Rôle : vérifier que le nbr aléatoire qu'on ajoute est bien seul dans son carré, sa colonne et sa ligne
	 */
	private static boolean verificationEstSeul(int nbrAleatoire, int[][] tableau, int ligne, int colonne) {
		return(verificationColonne(nbrAleatoire, tableau, ligne, colonne) && verificationLigne(nbrAleatoire, tableau, ligne, colonne) && verificationCarre(nbrAleatoire, tableau, ligne, colonne));
	}

	/* 
	 * MaFonction : verificationColonne
	 * Rôle : vérifier que le nbr aléatoire est seul dans sa colonne
	 */
	private static boolean verificationColonne(int nbrAleatoire, int[][] tableau, int ligne, int colonne) { 
		for(int i=0;i<ligne;i++) {
			if(nbrAleatoire == tableau[i][colonne]){
				return false;
			}
		}
		for(int i=ligne;i<tableau.length-1;i++) {
			if(nbrAleatoire == tableau[i][colonne]){
				return false;
			}
		}
		return true;
	}

	/*
	 * MaFonction : verificationLigne
	 * Rôle : vérifier que le nbr aléatoire est seul dans sa ligne
	 */
	private static boolean verificationLigne(int nbrAleatoire, int[][] tableau, int ligne, int colonne) {
		for(int i=0;i<colonne;i++) {
			if(nbrAleatoire == tableau[ligne][i]){
				return false;
			}
		}
		for(int i=colonne;i<tableau.length-1;i++) {
			if(nbrAleatoire == tableau[ligne][i]){
				return false;
			}
		}
		return true;
	}

	/*
	 * MaFonction : verificationCarre
	 * Rôle : vérifier que le nbr aléatoire est seul dans son carré
	 */
	private static boolean verificationCarre(int nbrAleatoire, int[][] tableau, int ligne, int colonne) {
		int carreDepartLigne = (ligne/(lignes/3)) * lignes/3;
		int carreArriveeLigne = carreDepartLigne + lignes/3;
		int carreDepartColonne = (colonne/(colonnes/3)) * colonnes/3;
		int carreArriveeColonne = carreDepartColonne + colonnes/3;

		for(int i=carreDepartLigne;i<carreArriveeLigne;i++) {
			for(int j=carreDepartColonne;j<carreArriveeColonne;j++) {
				if(nbrAleatoire == tableau[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
