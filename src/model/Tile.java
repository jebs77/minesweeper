package model;

public class Tile extends AbstractTile{
    private boolean open;
    private boolean flagged;
    private boolean explosive;

    public Tile() {
        open = false;
        flagged = false;
        explosive = false;
    }

    @Override
    public boolean open() {
        open = true;
        return open;
    }

    @Override
    public void flag() {
        flagged = true;
    }

    @Override
    public void unflag() {
        flagged = false;
    }

    @Override
    public boolean isFlagged() {
        return flagged;
    }

    @Override
    public boolean isExplosive() {
        return explosive;
    }

    public void makeExplosive() {
        explosive = true;
    }


    @Override
    public boolean isOpened() {
        return open;
    }
}
