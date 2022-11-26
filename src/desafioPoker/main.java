package desafioPoker;

import desafioPoker.controle.Jogo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

       try {
            System.out.println("Digite a quantidade de jogadores: ");
            Scanner entrada = new Scanner(System.in);
            int qJogadores = entrada.nextInt();
            if (qJogadores<2 || qJogadores>23){
                throw new Exception ();
            }
            Jogo partida01 = new Jogo(qJogadores); //inicia a partida
            partida01.cadastrarJogadores();

            partida01.definirDealerInicial(); //define quem será o dealer o SB e o BB
            partida01.darCartas(); //embaralha e distribui as cartas comunitárias e para os jogadores
            partida01.preFlop();

            partida01.statusJogadores();
           partida01.perguntarVencedor();




        }catch (InputMismatchException e){
            System.out.println("Digite um número válido");
        } catch (Exception e){
            System.out.println("Digite um número válido");
        } finally {
       }


    }
}
