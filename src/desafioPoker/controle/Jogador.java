package desafioPoker.controle;

import desafioPoker.controle.Carta;

import java.util.Scanner;

public class Jogador {
    protected String nome;
    protected boolean jogando;
    private int montanteFichas; //valor total em fichas
    private int apostaCorrente; //aposta da rodada
    private Carta carta1;
    private Carta carta2;
    private boolean allwin;

    protected Jogador(String nome) {
        this.jogando=true;
        this.nome = nome;
        this.montanteFichas = 10000;
        this.apostaCorrente=0;
        this.allwin= false;
    }

    protected void apostar(int valor){
        setMontanteFichas(this.montanteFichas-valor);
        this.apostaCorrente+=valor;
        System.out.println( "O jogador apostou "+ this.apostaCorrente + " nessa rodada!");
    }


    protected void correr(){
        this.jogando = false;
        System.out.println("PO PO PO");
   }

    protected void cobrir(int valor){ //valor do maior lance

        int diferenca=valor-this.apostaCorrente;

        if (diferenca>this.montanteFichas){
            System.out.println("O jogador deu ALL WIN apostando " + this.montanteFichas);
            allwin=true;
            this.apostaCorrente+=montanteFichas;
            this.montanteFichas=0;
        } else{
            this.apostaCorrente+=diferenca;
            this.montanteFichas= this.montanteFichas-diferenca;
        }
        System.out.println( "O jogador cobriu a aposta e colocou um total de "+ this.apostaCorrente + " nessa rodada!");

    }


    @Override
    public String toString() {
        return "Jogador: " +
                 nome +
                " montanteFichas: " + montanteFichas +
                "   apostas nessa rodada: " + apostaCorrente;
    }

    public String apresentarJogador() {
        return  "nome: " + nome +
                "   Quantidade total de fichas: " + montanteFichas;
    }


    public int getMontanteFichas() {
        return montanteFichas;
    }

    private void setMontanteFichas(int montanteFichas) {
        this.montanteFichas = montanteFichas;
    }

    protected boolean getJogando() {
        return jogando;
    }

    protected void setJogando(boolean jogando) {
        this.jogando = jogando;
    }

    protected Carta getCarta1() {
        return carta1;
    }

    protected void setCarta1(Carta carta1) {
        this.carta1 = carta1;
    }

    protected Carta getCarta2() {
        return carta2;
    }

    protected void setCarta2(Carta carta2) {
        this.carta2 = carta2;
    }

    public int getApostaCorrente() {
        return apostaCorrente;
    }

    public void setApostaCorrente(int apostaCorrente) {
        this.apostaCorrente = apostaCorrente;
    }

    public boolean getAllwin() {
        return allwin;
    }

    public void setAllwin(boolean allwin) {
        this.allwin = allwin;
    }


}
