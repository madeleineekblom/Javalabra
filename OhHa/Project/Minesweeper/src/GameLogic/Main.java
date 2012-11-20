package GameLogic;

/*
 * @author Madeleine Ekblom
 * @since 2012-10-21
 * 
 * Just a simple test....
*/

public class Main {

    public static void main(String[] args) {
        GameMoves m = new GameMoves(2,2,0);
        m.createGameBoard();

        m.openButtons(0,1);
        boolean[][] h = m.help;
        
        
        for (int i = 0; i < h.length; i++) {
            for (int j = 0; j < h[i].length; j++) {
                System.out.print(Character.toString(m.matrix[i][j]));
                System.out.print(Boolean.toString(h[i][j]));
            }
            System.out.println("");
        }

    }
}
