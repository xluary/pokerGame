package desafioPoker.controle;

import desafioPoker.controle.Baralho;
import desafioPoker.controle.Carta;
import desafioPoker.controle.Jogador;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    public static int qJogadores; // tem que ter entre 2 e 8 jogadores

    ArrayList<Jogador> participantes = new ArrayList();

    public int valorBlind;
    public int qCartasMesa;
    public int qCartasMao;
    public int posicaoDealer;
    public int posicaoSmallBlind;
    public int posicaoBigBlind;
    public int valorMesa;
    public int maiorLanceMesa;
    public Jogador dealer;

    int jogadoresNaPartida;
    public Carta[] cartasComunitarias = new Carta[5];

    public Jogo(int qJogadores) {
        this.qJogadores = qJogadores;
        this.qCartasMesa = 5;
        this.qCartasMao = 2;
        this.valorBlind = 100;
        this.valorMesa=0;
        this.maiorLanceMesa=0;
    }

    public void cadastrarJogadores(){

        for(int i=0; i<qJogadores; i++){
            System.out.printf("Digite o nome do jogador %d:   ",(i+1));
            Scanner s1 = new Scanner(System.in);
            String novoJogador = s1.next();
            participantes.add(new Jogador(novoJogador));
        }
        apresentacao();
    }

    public void statusJogadores(){
        for(int i=0; i<qJogadores;i++) {
            System.out.println(participantes.get(i).toString());
            System.out.print("Cartas do jogador: ");
            participantes.get(i).getCarta1().converterNumeroCarta();
            participantes.get(i).getCarta2().converterNumeroCarta();
            System.out.println("\n");
            System.out.println("------------------------------");

        }
        System.out.println("Cartas comunitária: ");
        for(int j=0; j<cartasComunitarias.length; j++){
                cartasComunitarias[j].converterNumeroCarta();
        }

    }

    public void apresentacao(){
        System.out.print("\n");
        System.out.println("Apresentação dos jogadores: ");
        for(int i=0; i<qJogadores;i++) {
            System.out.println(participantes.get(i).apresentarJogador());
        }
    }

    public void definirDealerInicial(){
        Random aleatorio= new Random();
        int dealerInicial = aleatorio.nextInt(participantes.size() - 1);
        this.posicaoDealer=dealerInicial;
        this.posicaoSmallBlind=(posicaoDealer+1>qJogadores-1)?(((qJogadores-1)-(posicaoDealer))):(posicaoDealer+1);
        this.posicaoBigBlind=(posicaoDealer+2>qJogadores-1)?(1-((qJogadores-1)-(posicaoDealer))):(posicaoDealer+2);

        System.out.println("------------------------------------------------------------");
        System.out.print("Dealer: "+ participantes.get(posicaoDealer).nome);
        System.out.print("   SmallBlind: "+ participantes.get(posicaoSmallBlind).nome);
        System.out.println("   BigBlind: "+ participantes.get(posicaoBigBlind).nome);
        System.out.println("------------------------------------------------------------");
    }

    public void darCartas(){
        Baralho baralho = new Baralho();
        baralho.embaralhar();

        System.out.println("Baralho embaralhado");
        baralho.imprimirBaralho();
        System.out.println("\n");

        // dar as cartas comunitarias
        for(int i=0; i<cartasComunitarias.length; i++){
            cartasComunitarias[i]= baralho.getOrdemCartas()[i];
            baralho.getOrdemCartas()[i]=new Carta(0,0);
           // cartasComunitarias[i].converterNumeroCarta();
           // cartasComunitarias[i]=null;
        }

        // dar as cartas para os jogadores
        int posicaoBaralho=4;
        for (int j=0; j<qJogadores; j++){
            participantes.get(j).setCarta1(baralho.getOrdemCartas()[posicaoBaralho+1]);
            participantes.get(j).setCarta2(baralho.getOrdemCartas()[posicaoBaralho+2]);
        //    System.out.println("-----------------");
        //    participantes.get(j).getCarta1().converterNumeroCarta();
        //    participantes.get(j).getCarta2().converterNumeroCarta();
            posicaoBaralho+=2;
        }
    }

    public void preFlop(){
        System.out.println("Começo de partida: ");
        System.out.println(participantes.get(this.posicaoSmallBlind).nome + " aposta obrigatóriamente:  " + (this.valorBlind/2));
        participantes.get(posicaoSmallBlind).apostar(this.valorBlind/2);
        this.valorMesa+=this.valorBlind/2;
        System.out.println(participantes.get(this.posicaoBigBlind).nome + " aposta obrigatóriamente:  " + (this.valorBlind));
        participantes.get(posicaoBigBlind).apostar(this.valorBlind);
        this.valorMesa+=this.valorBlind;

        this.maiorLanceMesa = this.valorBlind;
        Jogador jogadorAnterior = participantes.get(posicaoBigBlind);
        jogadoresNaPartida=participantes.size();
        Scanner s1 = new Scanner(System.in);

        boolean ok = false;
        apostas: while (ok==false){

           int jogadorDaVez=((participantes.indexOf(jogadorAnterior)+1)>(participantes.size()-1)?(0):(participantes.indexOf(jogadorAnterior)+1));

            if(participantes.get(jogadorDaVez).jogando && participantes.get(jogadorDaVez).getAllwin() == false ) {
                System.out.println("----------------------------------------");
                System.out.println(participantes.get(jogadorDaVez).nome + "  Aposta corrente " + participantes.get(jogadorDaVez).getApostaCorrente());
                perguntarJogador();
                int opcao = s1.nextInt();
                switch (opcao) {
                    case 1:
                        this.valorMesa += this.maiorLanceMesa-participantes.get(jogadorDaVez).getApostaCorrente();
                        participantes.get(jogadorDaVez).cobrir(this.maiorLanceMesa);
                        break;
                    case 2:
                        System.out.println("Digite o valor da aposta: ");
                        int valorAposta = s1.nextInt();
                        while (valorAposta>participantes.get(jogadorDaVez).getMontanteFichas() || valorAposta<(this.maiorLanceMesa-participantes.get(jogadorDaVez).getApostaCorrente())){
                            if(valorAposta>participantes.get(jogadorDaVez).getMontanteFichas()) {
                                System.out.println(" Valor insuficiante! Digite uma quantidade menor ou igual a " + participantes.get(jogadorDaVez).getMontanteFichas());
                            }else{
                                System.out.println(" Valor menor que o mínimo, digite pelo menos " + (this.maiorLanceMesa -participantes.get(jogadorDaVez).getApostaCorrente()));
                            }
                            valorAposta = s1.nextInt();
                        }

                        participantes.get(jogadorDaVez).apostar(valorAposta);
                        this.maiorLanceMesa = participantes.get(jogadorDaVez).getApostaCorrente();
                        this.valorMesa += valorAposta;
                        break;
                    case 3:
                        participantes.get(jogadorDaVez).correr();
                        jogadoresNaPartida--;
                        break;
                }
            }
           jogadorAnterior = participantes.get(jogadorDaVez);

           int quantidadeOk=0;
            for(int i=0; i<participantes.size();i++){
                if (participantes.get(i).getApostaCorrente() == maiorLanceMesa && participantes.get(i).getJogando()==true){
                    quantidadeOk++;
                };
            }
           if(quantidadeOk == jogadoresNaPartida){
               System.out.println("Fim do pre-flop");
               System.out.print("\n");
               break apostas;
           }


        }





    }

    public Jogador getDealer() {
        return dealer;
    }

    public void setDealer(Jogador dealer) {
        this.dealer = dealer;
    }

    protected void perguntarJogador(){

        System.out.println("Digite para: \n 1- Cobrir \n 2- Apostar \n 3- Correr ");
    }

    public void perguntarVencedor(){
        System.out.print("\n");
        System.out.println("Quem venceu a partida? ");

        for(int i=0; i<qJogadores;i++){
            System.out.print((i+1) + "- " + participantes.get(i).nome);
            if (participantes.get(i).jogando == false) {
                System.out.println("  (desistente)");
            } else {
                System.out.println(" ");
            }
        }
        Scanner entrada = new Scanner(System.in);
        int vencedor= entrada.nextInt();
        System.out.println("Vencedor: " + participantes.get(vencedor-1).nome + " recebeu "+ this.valorMesa + "!");
    }

    public int getMaiorLanceMesa() {
        return maiorLanceMesa;
    }

    public void setMaiorLanceMesa(int maiorLanceMesa) {
        this.maiorLanceMesa = maiorLanceMesa;
    }




}
