package GameLogic;

/**
 *
 * @author Madeleine Ekblom
 * @since 2012-11-20
 */
public class GameMoves {

    public Game game;
    public boolean[][] visited;
    public char[][] matrix;
    public boolean[][] winMatrix;
    public boolean[][] flagMatrix;
    public boolean[][] help;
    
    int numFlags;
    
    /**
     * Constructor
     * @param rows      How many rows
     * @param columns   How many columns
     * @param mines     How many mines
     */
    public GameMoves(int rows, int columns, int mines) {
        game = new Game(rows, columns, mines);
        visited = new boolean[game.getRows()][game.getColumns()];
        numFlags = game.getMines();
        winMatrix = new boolean[game.getRows()][game.getColumns()];
        flagMatrix = new boolean[game.getRows()][game.getColumns()];
        help = new boolean[game.getRows()][game.getColumns()];
    }
    
    /**
     * creates the matrix containg mines and numbers
     */
    public void createGameBoard() {
        matrix = game.createGame();
    }

    /**
     * Sets the help matrix at the following coordinate to be true, and updates
     * the winMatrix 
     * 
     * @param row       the row coordinate
     * @param column    the column coordinate
     */
    public void openButtons(int row, int column) {
        if (game.indexOutsideMatrix(row, column) || visited[row][column]) {
            return;
        }
        if (matrix[row][column] != '0') {
            visited[row][column] = true;
            help[row][column] = true;
            if (matrix[row][column] != '*') {
                winMatrix[row][column] = true;
            }
        } else {
            winMatrix[row][column] = true;
            visited[row][column] = true;
            help[row][column] = true;
            openButtons(row - 1, column);
            openButtons(row + 1, column);
            openButtons(row, column - 1);
            openButtons(row, column + 1);
            openButtons(row - 1, column - 1);
            openButtons(row - 1, column + 1);
            openButtons(row + 1, column + 1);
            openButtons(row + 1, column - 1);
        }
    }

    /**
     * Sets the flagMatrix at the following coordinate to be true, and updates
     * the winMatrix (true if it is a bomb in the same square)
     * @param row       row coordinate
     * @param column    column coordinate
     */
    public void markWithFlags(int row, int column) {
        if (visited[row][column]) {
            return;
        }
        flagMatrix[row][column] = true;
        visited[row][column] = true;
        setFlags(getFlags() - 1);
        if (matrix[row][column] == '*') {
            winMatrix[row][column] = true;
        }
    }
    
    /**
     * Sets the flagMatrix to be false at the following coordinate
     * @param row       the row coordinate
     * @param column    the column coordinate
     */
    public void unFlagSquare(int row, int column) {
        flagMatrix[row][column] = false;
        visited[row][column] = false;
        setFlags(getFlags()+1);
        winMatrix[row][column] = false;
    }
    
    /**
     * Checks if the game is won or not, when all flags have been placed
     * 
     * @return  true if the winMatrix is true for all i,j,
     */
    public boolean win() {
        if (getFlags() != 0) {
            return false;
        }
        
        for (int i = 0; i < winMatrix.length; i++) {
            for (int j = 0; j < winMatrix[i].length; j++) {
                if (!winMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void lose() {
        System.out.println("You lost!");
        
    }
    
    /**
     * 
     * @return  Number of flags that has not been placed
     */
    public int getFlags() {
        return numFlags;
    }
    
    /**
     * Changes the number of flags.
     * 
     * @param num   the number of flags are changed into num
     */
    public void setFlags(int num) {
        numFlags = num;
    }
    
}
