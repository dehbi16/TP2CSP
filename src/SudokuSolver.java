import java.util.ArrayList;

public class SudokuSolver {
	protected static int colonnes = 9;
	protected static int lignes = 9;
	protected static int[][] grilleR = SudokuGenerator.grilleR;
	protected static int[][] solution = new int[lignes][colonnes];
	static int count = 0;
	public SudokuSolver() {
		for(int i=0; i<lignes; i++) {
			for(int j=0; j<colonnes; j++) {
				solution[i][j] = grilleR[i][j];
			}
		}
		solve(0,0);
	}

	public static void solve(int i, int j) {
		if (i==lignes) {
			
			if(count==0) {
				
				System.out.println("\n");
				System.out.println(" --------------- ");
				for (int k=0; k<SudokuGenerator.lignes; k++) {
					for (int l=0; l<SudokuGenerator.lignes; l++) {
						if(l == 2 || l == 5 || l == 8 ) {
							System.out.print(solution[k][l] + "|");
						}
						else {
							System.out.print(solution[k][l] + ".");
						}
					}
					System.out.println();
					if(k == 2 || k == 5 || k == 8 ) {
						System.out.println("------------------");
					}
				}
				count++;  
			}		
			
			
		}
		else {
			if (grilleR[i][j]!=0) {
				solution[i][j] = grilleR[i][j];
				if (j==colonnes-1) solve(i+1,0);
				else solve(i,j+1);

			}
			else {
				ArrayList<Integer> position=nombrePossible(i,j);
				
				for(int k=0;k<position.size();k++) {
					solution[i][j] = position.get(k);
					if (j==colonnes-1) solve(i+1,0);
					else solve(i,j+1);
				}
				solution[i][j] = 0;
			}
		}

	}

	private static ArrayList<Integer> nombrePossible(int i, int j) {
		ArrayList <Integer> positionpossible = new ArrayList <Integer>();
		for (int k=1; k<=lignes; k++) {
			if (verificationColonne(k,i,j) && verificationLigne(k, i, j) && verificationCarre(k, i, j)) positionpossible.add(k);

		}
		return positionpossible;
	}

	private static boolean verificationCarre(int k, int i, int j) {
		int carreDepartLigne = (i/(lignes/3)) * lignes/3;
		int carreArriveeLigne = carreDepartLigne + lignes/3;
		int carreDepartColonne = (j/(colonnes/3)) * colonnes/3;
		int carreArriveeColonne = carreDepartColonne + colonnes/3;

		for(int l=carreDepartLigne;l<carreArriveeLigne;l++) {
			for(int m=carreDepartColonne;m<carreArriveeColonne;m++) {
				if(k == solution[l][m] && l!=i && m!=j) {
					return false;
				}


			}
		}
		return true;
	}

	private static boolean verificationLigne(int k, int i, int j) {
		for(int l=0; l<lignes; l++) {
			if (k == solution[l][j] && l!=i) return false;


		}
		return true;
	}

	private static boolean verificationColonne(int k, int i, int j) {
		for(int l=0; l<colonnes; l++) {
			if (k == solution[i][l] && l!=j) return false;

		}
		return true;
	}
}
