package a207.fall18.gamecentre;

import android.support.annotation.NonNull;

import java.util.Observable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * The sliding tiles board.
 * Which implement Iterable<Tile> and Serializable.
 */
public class Board extends Observable implements Serializable, Iterable<Tile> {

    /**
     * The number of rows.
     */
    static int NUM_ROWS = 4;

    /**
     * The number of rows.
     */
    static int NUM_COLS = 4;

    /**
     * l
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles = new Tile[NUM_ROWS][NUM_COLS];

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == NUM_ROWS * NUM_COLS
     *
     * @param tiles the tiles for the board
     */
    Board(List<Tile> tiles) {
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != Board.NUM_ROWS; row++) {
            for (int col = 0; col != Board.NUM_COLS; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    int numTiles() {
        return NUM_ROWS * NUM_COLS;
    }

    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    void swapTiles(int row1, int col1, int row2, int col2) {
        Tile original_tile = getTile(row1, col1);
        tiles[row1][col1] = getTile(row2, col2);
        tiles[row2][col2] = original_tile;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    @NonNull
    @Override
    public Iterator<Tile> iterator() {
        return new BoardIterator();
    }

    private class BoardIterator implements Iterator<Tile> {
        int rowIndex = 0;
        int columnIndex = 0;

        @Override
        public boolean hasNext() {
            return rowIndex != NUM_ROWS - 1 ||
                    columnIndex != NUM_COLS - 1;
        }

        @Override
        public Tile next() {
            Tile result = tiles[rowIndex][columnIndex];
            if (rowIndex != Board.NUM_ROWS - 1) {
                if (columnIndex != Board.NUM_COLS - 1) {
                    columnIndex++;
                } else {
                    rowIndex++;
                    columnIndex = 0;
                }
            } else {
                if (columnIndex != Board.NUM_COLS - 1) {
                    columnIndex++;
                }
            }
            return result;
        }

    }

}