
public class SudokuGenerator {

	private static int colonnes = 9;
	private static int lignes = 9;

	public static void main(String[] args) {
		int[][] grille = new int[lignes][colonnes];
		initialisationGrille(grille);
		affichageGrille(grille);
	}

	private static void initialisationGrille(int[][] tableau) {
		// initialisation du tableau qui va remplir le grille avec que des 0
		for(int i=0;i<tableau.length;i++) {
			for(int j=0;j<tableau.length;j++) {
				tableau[i][j] = 0;
			}
		}
	}

	private static void affichageGrille(int[][] tableau) {
		System.out.println("Générateur de Sudoku");
		for(int i=0;i<tableau.length;i++) {
			for(int j=0;j<tableau.length;j++) {
				if(j == 2 || j == 5 || j == 8 ) {
					System.out.print(tableau[i][j] + "|");
				}
				else {
					System.out.print(tableau[i][j] + ".");
				}

			}
			System.out.println("");
			if(i == 2 || i == 5 || i == 8 ) {
				System.out.println("------------------");
			}

		}

	}
}
