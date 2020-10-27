
public class Main {

	public static void main(String[] args) {
		new SudokuGenerator();
		/*
		System.out.println();
		
		for (int i=0; i<SudokuGenerator.lignes; i++) {
			for (int j=0; j<SudokuGenerator.lignes; j++) {
				System.out.print(SudokuGenerator.grilleR[i][j]+" ");
			}
			System.out.println();
		}
		*/
		new SudokuSolver();
		
		System.out.println("\n\n");
		System.out.println("nombre de solution : "+SudokuSolver.m);
		System.out.println("FIN!!!");
	}

}
