package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Marty Germane
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;

    private  int blocked = -1;

    private int spaceErrorRow = -1;
    private int spaceErrorCol = -1;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;

    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;


        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.

        /*if (side.equals(Side.NORTH))
        {
            System.out.println("I am North");
        }

        else if (side.equals(Side.SOUTH))
        {

        }

        else if (side.equals(Side.EAST))
        {

        }

        else if (side.equals(Side.WEST))
        {

        }*/

        board.setViewingPerspective(side);
        for (int i = board.size() - 1; i >= 0; i--)
        {
            this.blocked = -1;
            for (int j = board.size() - 2; j >= 0; j--)
            {
                Tile t = board.tile(i, j);
                if (t == null)
                    continue;
                if (board.tile(i, j) != null)
                {
                    int newRow = similarValueCheck(i, j, board, this.blocked);
                   /* System.out.println("newRow: " + newRow);*/

                    if (newRow != -1)
                    {

                        t = board.tile(i, j);

                        boolean mergeOrNot = board.move(i, newRow, t);
                       /* System.out.println(i + "Row" + newRow + "old Row: " + j);*/
                        changed = true;
                        if (mergeOrNot)
                        {
                            int scoreKeeperNew = board.tile(i, newRow).value();
                            score += scoreKeeperNew;
                        }
                    }

                }
            }
        }

        // For Empty space
        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board.size(); j++)
            {

                Tile t = board.tile(i, j);
            /*    System.out.println(t + "i: " + i + "j: " + j);*/

                if (t != null)
                {
                    /*System.out.println("Empty Space: " + i + " " + j);*/
                    int newRow = nullFill(i, j, board, this.spaceErrorCol, this.spaceErrorRow);
                    System.out.println(newRow);
                    if (newRow == -1)
                    {
                        continue;
                    }

                    boolean mergeOrNot = board.move(i, newRow, t);

                    System.out.println("Row: " +  i + " Row (nullFill): " + newRow + " old Row: " + j);
                    System.out.println(t);
                    System.out.println(board);
                    changed = true;

                }

            }
        }

        /*// For Basic Merge


        boolean mergeOrNot = board.move(i, board.size() - 1, t);
        changed = true;
        if (mergeOrNot) {
            int scoreKeeperNew = board.tile(i, board.size() - 1).value();
            score += scoreKeeperNew;
        }*/

        // For space error after adjacency error.
        System.out.println("Space error row: " + this.spaceErrorRow);
        if (this.spaceErrorRow != -1 && this.spaceErrorCol != -1)
        {
            System.out.println("Space error col: " + this.spaceErrorCol);
            int i = this.spaceErrorCol;
            int j = this.spaceErrorRow;

                    int newRow = nullFill(i, j, board);
            System.out.println("nullFill: " + newRow);
                    if (newRow != -1)
                    {
                        Tile t = board.tile(i, j - 1);
                        System.out.println(board);
                        System.out.println("board.tile (WRIN): " + (board.tile(i, j - 1)));
                        System.out.println("Row: " + i + " Row (nullFill): " + newRow + " old Row: " + j);
                       if (!(board.tile(i, j - 1) == null)) {
                           boolean mergeOrNot = board.move(i, newRow, t);

                           System.out.println(t);
                           System.out.println(board);
                           changed = true;
                       }
                    }
                }



        board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }




    // This is to check only the "Up" direction values.
    // Returns the value of row iff there is a similar value in the column
    // Else returns -1
    public int similarValueCheck(int col, int row, Board b, int blockedRow)
    {
        // Remember column would remain same.
        for (int i = row + 1; i < b.size(); i++)
        {

            if (b.tile(col, row) == null || i == blockedRow || b.tile(col, i) == null)
            {
                // Skip
//                System.out.println("i (blocked row): " + i);
                continue;
            }
            /*System.out.println("b.tile (col, row): " + b.tile(col, row).value());
            System.out.println("b.tile (col, i): " + b.tile(col, i).value());*/
            if (b.tile(col, row).value() == b.tile(col, i).value())
            {
               /* System.out.println("(blocked row): " + blockedRow);*/
                this.blocked = i;
                return i;
            }
        }
    return -1;
    }

    public int nullFill(int col, int row, Board b, int spaceErrorCol, int spaceErrorRow)
    {
        int rowStore = -1;
        // Remember column would remain same.
        for (int i = row + 1; i < b.size(); i++)
        {
            if (b.tile(col, i) != null)
            {
                this.spaceErrorCol = col;
                this.spaceErrorRow = i;
                System.out.println("Tile check: " + board.tile(col, i));
                System.out.println("spaceError Row: " + i);
                return rowStore;
            }

            if (b.tile(col, i) == null)
            {
                rowStore = i;
                System.out.println("RowStore: " + rowStore);
                System.out.println(i);
                System.out.println("B.tile: " + b.tile(col, i));
                return rowStore;
            }

        }
        return rowStore;
    }


    public int nullFill(int col, int row, Board b)
    {
        int rowStore = -1;
        // Remember column would remain same.
        for (int i = row - 1; i < b.size(); i++)
        {
            System.out.println("col: " + col + "row: " + row);
            System.out.println("After null check: " + b.tile(col, i));
            if (b.tile(col, i) != null)
            {
                continue;
            }

            if (b.tile(col, i) == null)
            {
                rowStore = i;

                System.out.println(i);
                System.out.println("B.tile: " + b.tile(col, i));
                System.out.println("rowStore: " + rowStore);
            }
        }
        System.out.println("RowStore: " + rowStore);
        return rowStore;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // Empty Space method is complete.

        for (int i = 0; i < b.size(); i++)
        {
            for (int j = 0; j < b.size(); j++)
            {
                if (b.tile(i, j) == null)
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // maxTileExists complete.

        for (int i = 0; i < b.size(); i++)
        {
            for (int j = 0; j < b.size(); j++)
            {
                if (b.tile(i, j) == null)
                    continue;
                if (b.tile(i, j).value() == MAX_PIECE)
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // One Move Exists complete.
        // 1. At least one empty space
        if (emptySpaceExists(b))
        {
            return true;
        }
        // 2. Two adjacent values
        for (int i = 0; i < b.size(); i++)
        {
            for (int j = 0; j < b.size(); j++)
            {
                int tileValue = b.tile(i, j).value();
                /*if (b.tile(i, j) == null)
                    continue;*/
                    int left = leftAdj(i, j, b);
                    int right = rightAdj(i, j, b);
                    int down = downAdj(i, j, b);
                    int up = upAdj(i, j, b);

                    if (left == tileValue
                    ||  right == tileValue
                    ||  down == tileValue
                    ||  up == tileValue)
                    {
                        return true;

                }
            }
        }
        return false;
    }

    // Method to check if same value exists in the adjacent index.
    // 1. Left
    public static int leftAdj(int col, int row, Board b)
    {
        col--;
        if (col < 0)
            return -1;
        return b.tile(col, row).value();
    }

    // 2. Right
    public static int rightAdj(int col, int row, Board b)
    {
        col++;
        if (col >= b.size())
            return -1;
        return b.tile(col, row).value();
    }

    // 3. Up
    public static int upAdj(int col, int row, Board b)
    {
        row--;
        if (row < 0)
            return -1;
        return b.tile(col, row).value();
    }

    // 4. Down
    public static int downAdj(int col, int row, Board b)
    {
        row++;
        if (row >= b.size())
            return -1;
        return b.tile(col, row).value();
    }

    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
