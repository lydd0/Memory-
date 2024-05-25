package com.example.memory_seva1;

public class Cell {
    private String percorso;
    private int col;

    private int row;

    private boolean girata;

    public boolean isGirata() {
        return girata;
    }

    public void setGirata(boolean girata) {
        this.girata = girata;
    }

    public boolean compareRowandCol(int col, int row)
    {
        if(row == getRow() && col == getCol())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void setPercorso(String percorso) {
        this.percorso = percorso;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getPercorso() {
        return percorso;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Cell(String percorso, int col, int row)
    {
        setPercorso(percorso);
        setCol(col);
        setRow(row);
        setGirata(false);
    }
}
