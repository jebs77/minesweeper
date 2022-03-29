package model;

import notifier.IGameStateNotifier;
import view.MinesweeperView;

import java.util.ArrayList;
import java.util.Random;

public class Minesweeper extends AbstractMineSweeper {
    private int width;
    private int height;
    private int mines;
    private AbstractTile[][] board;
    private int flagCount;


    public Minesweeper() {
        width = 0;
        height = 0;
        mines = 0;
        flagCount = 0;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void startNewGame(Difficulty level) {
        if (level == Difficulty.EASY) {
            startNewGame(8,8,10);
        } else if (level == Difficulty.MEDIUM) {
            startNewGame(16,16,40);
        } else {
            startNewGame(16,30,99);
        }

    }

    @Override
    public void startNewGame(int col, int row, int explosionCount) {

        height = col;
        width = row;
        mines = explosionCount;
        board = new Tile[height][width];
        Random rand = new Random();
        int placedMines = 0;
        while ( placedMines < mines) {
            int x = rand.nextInt(col - 1);
            int y = rand.nextInt(row - 1);
            if (getTile(y, x) == null) {
                board[x][y] = generateExplosiveTile();
                placedMines++;
            }
        }
        for(int i = 0; i< col; i++){
            for (int j = 0;  j< row; j++){
                if (getTile(j, i)  == null){
                    board[i][j] = generateEmptyTile();
                }
            }

        }

        viewNotifier.notifyNewGame(row, col);

    }

    @Override
    public void toggleFlag(int x, int y) {
        AbstractTile newTile = getTile(y, x);
        if (newTile.isOpened() == false) {
            if (newTile.isFlagged()) {

                newTile.unflag();
                flagCount--;
                viewNotifier.notifyUnflagged(x, y);
                viewNotifier.notifyFlagCountChanged(flagCount);
            } else {
                newTile.flag();
                flagCount++;
                viewNotifier.notifyFlagged(x, y);
                viewNotifier.notifyFlagCountChanged(flagCount);
            }
        }

        }

        @Override
        public void setWorld (AbstractTile[][] world){

            board = world;
            height = world.length;
            width = world[0].length;
        }

        @Override
        public AbstractTile getTile(int y, int x) {
            if((0 <= x && x < height)&& (0 <= y && y <width) ){
                return board[x][y];
            }
            else {
                return null;
            }
        }

        @Override
        public void open ( int x, int y) {
            int surrounded = 0;
            if(getTile(y,x).isExplosive()== false) {

//                top left corner
                if (x == 0 && y == 0) {
                    if (getTile(0, 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = 0; i < 2; i++) {
                        if (getTile(1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            top right corner
                else if (x == width - 1 && y == 0) {
                    if (getTile(0, x - 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = width - 1; i > width - 3; i--) {
                        if (getTile(1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            bottom left cornser
                else if (x == 0 && y == height - 1) {
                    if (getTile(y, x + 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = 0; i < 2; i++) {
                        if (getTile(y-1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            bottom right corner
                else if (x == width - 1 && y == height - 1) {
                    if (getTile(y , x - 1).isExplosive() == true) {
                        surrounded++;
                    }
                    for (int i = width - 1; i > width - 3; i--) {
                        if (getTile(y - 1, i).isExplosive() == true) {
                            surrounded++;
                        }
                    }
                }
//            left wall
                else if (x == 0 && y != 0 && y != height - 1) {
                    if (getTile(y - 1, x).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y + 1, x).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y + i, x + 1).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
//            right wall
                else if (x == width - 1 && y != 0 && y != height - 1) {
                    if (getTile(y - 1, x).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y + 1, x).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y + i, x - 1).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
//            top wall
                else if (y == 0 && x != 0 && x != width - 1) {
                    if (getTile(y, x - 1).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y, x + 1).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y + 1, x + i).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
//            bottom wall
                else if (y == height-1 && x != 0 && x != width - 1) {
                    if (getTile(y, x - 1).isExplosive()) {
                        surrounded++;
                    }
                    if (getTile(y, x + 1).isExplosive()) {
                        surrounded++;
                    }
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y -1, x + i).isExplosive()) {
                            surrounded++;
                        }
                    }
                }
                else{
                    for (int i = -1; i < 2; i++) {
                        if (getTile(y - 1, x + i).isExplosive()) {
                            surrounded++;
                        }
                    }
                    for (int j = -1; j < 2; j++) {
                        if (getTile(y + 1, x + j).isExplosive()) {
                            surrounded++;
                        }
                    }
                        if (getTile(y, x - 1).isExplosive()) {
                            surrounded++;
                        }
                        if (getTile(y, x + 1).isExplosive()) {
                            surrounded++;
                        }
                    }
                getTile(y,x).open();
                viewNotifier.notifyOpened(x, y, surrounded);
                if (surrounded == 0){
                    if(y != 0) {
                        if(getTile(y-1, x).isOpened()== false) {
                            open(x, y - 1);
                        }
                    }
                    if(y != height-1) {
                        if(getTile(y+1, x).isOpened()== false) {
                            open(x, y + 1);
                        }
                    }
                    if(x != 0) {
                        if (getTile(y, x-1).isOpened()== false) {
                            open(x - 1, y);
                        }
                    }
                    if(x != width-1) {
                        if (getTile(y, x+1).isOpened()== false) {
                            open(x + 1, y);
                        }
                    }
                    if(x != 0 && y != 0){
                        if(getTile(y-1,x-1).isOpened() == false){
                            open(x-1,y-1);
                        }
                    }
                    if(x != width-1 && y != 0){
                        if(getTile(y-1,x+1).isOpened() == false){
                            open(x+1,y-1);
                        }
                    }
                    if(x != 0 && y != height-1){
                        if(getTile(y+1,x-1).isOpened() == false){
                            open(x-1,y+1);
                        }
                    }
                    if(x != width-1 && y != height-1){
                        if(getTile(y+1,x+1).isOpened() == false){
                            open(x+1,y+1);
                        }
                    }

                }
                }
            else {
                viewNotifier.notifyExploded(x,y);
                viewNotifier.notifyGameLost();
            }
            }




        @Override
        public void flag( int x, int y){
            board[x][y].flag();
            viewNotifier.notifyFlagged(x, y);
            viewNotifier.notifyFlagCountChanged(flagCount+1);
        }

        @Override
        public void unflag( int x, int y){
            board[x][y].unflag();
            viewNotifier.notifyUnflagged(x, y);
            viewNotifier.notifyFlagCountChanged(flagCount-1);
        }

        @Override
        public void deactivateFirstTileRule () {

        }



        @Override
        public AbstractTile generateEmptyTile() {
            AbstractTile emptyTile = new Tile( false);
            return emptyTile;
        }


        @Override
        public AbstractTile generateExplosiveTile () {
            AbstractTile newTile = new Tile(true);
            return newTile;
        }


    }


