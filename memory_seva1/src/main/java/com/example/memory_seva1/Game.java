package com.example.memory_seva1;

public class Game implements Comparable<Game> {
    private double tempo;
    private String nome;

    private int tentativi;
    @Override
    public String toString() {
        return "Nome: " + nome + ", " + "Tempo: " + tempo + ", " + "Tentativi: " + tentativi +"\n";
    }
    public Game(String nome, double tempo, int tentativi) {
        this.tempo = tempo;
        this.nome = nome;
        this.tentativi = tentativi;
    }
    @Override
    public int compareTo(Game game) {
        return Double.compare(this.tempo, game.getTempo());
    }
    public void setTempo(long tempo) {
        this.tempo = tempo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTempo() {
        return tempo;
    }

    public String getNome() {
        return nome;
    }
}
