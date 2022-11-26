package desafioPoker.controle;

import java.util.Random;
import java.util.Scanner;

public class Baralho {
     private int quantidadeCartas = 52;
     protected int quantidadeEmbaralhar = 100;
     private Carta[] ordemCartas = new Carta[quantidadeCartas];

    protected Baralho() {
        int qNaipe=4;
        int valorCarta=13;
        int controlePosicaoCarta=0;

        for(int i=1;i<=qNaipe;i++){ //para cada um dos naipes
            for(int j=1; j<=valorCarta; j++){ //para cada valor de carta
                ordemCartas[controlePosicaoCarta]= new Carta(j, i);
                controlePosicaoCarta++;
            }
        }
    }

    public void imprimirBaralho() {
        for (int i = 0; i < quantidadeCartas; i++) {
            if(ordemCartas[i].getNumero()!=0) {
                ordemCartas[i].converterNumeroCarta();
            }
        }
    }
    protected void embaralhar() {
        Random nAleatorio = new Random();
        int controle = 0;
        while (controle < this.quantidadeEmbaralhar) {
            int carta1 = nAleatorio.nextInt(this.quantidadeCartas - 1);
            int carta2 = nAleatorio.nextInt(this.quantidadeCartas - 1) ;

            int valorCarta1 = this.ordemCartas[carta1].getNumero();
            int valorNaipe1 = this.ordemCartas[carta1].getNaipe();
            int valorCarta2 = this.ordemCartas[carta2].getNumero();
            int valorNaipe2 = this.ordemCartas[carta2].getNaipe();

            this.ordemCartas[carta1].setNumero(valorCarta2);
            this.ordemCartas[carta1].setNaipe(valorNaipe2);
            this.ordemCartas[carta2].setNumero(valorCarta1);
            this.ordemCartas[carta2].setNaipe(valorNaipe1);

            controle++;
        }

    }

    public void temCarta(){
        Scanner n1= new Scanner(System.in);
        System.out.println("Qual o valor da carta que deseja verificar?");
        String valor = n1.next();
        System.out.println("Qual o naipe da carta que deseja verificar?");
        String naipe = n1.next();
        int valorCarta= converterCartaNumero(valor);
        int naipeCarta = converterCartaNaipe(naipe);

        for (int i=0; i<ordemCartas.length; i++){
            if(ordemCartas[i].getNumero() == valorCarta && ordemCartas[i].getNaipe()==naipeCarta){
                System.out.println("Esta carta está no baralho");
                break;
            }
        }


    } //conferir

    protected int converterCartaNumero(String tipoNumero){
        int numero = 0;

        switch (tipoNumero) {
            case "A":
                numero = 1;
                break;
            case "J":
                numero = 11;
                break;
            case "Q":
                numero = 12;
                break;
            case "K":
                numero = 13;
                break;
            default:
                numero = Integer.valueOf(tipoNumero);
        }

        return  numero;

    }

    protected int converterCartaNaipe (String tipoNaipe){
        int naipe = 0;
        switch (tipoNaipe.toLowerCase()) {
            case "copas":
                naipe = 1;
                break;
            case "espadas":
                naipe = 2;
                break;
            case "paus":
                naipe = 3;
                break;
            case "ouros":
                naipe = 4;
                break;
        }
        return naipe;
    }

    public Carta[] getOrdemCartas() {
        return ordemCartas;
    }

    public void setOrdemCartas(Carta[] ordemCartas) {
        this.ordemCartas = ordemCartas;
    }


}
