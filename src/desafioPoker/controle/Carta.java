package desafioPoker.controle;

public class Carta {
    private int numero;
    private int naipe;

    protected Carta(int numero, int naipe) {
        this.numero = numero;
        this.naipe = naipe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNaipe() {
        return naipe;
    }

    public void setNaipe(int naipe) {
        this.naipe = naipe;
    }

    protected void converterNumeroCarta(){
        String tipoNumero = null;
        switch (numero) {
            case 1:
                tipoNumero = "A";
                break;
            case 11:
                tipoNumero = "J";
                break;
            case 12:
                tipoNumero = "Q";
                break;
            case 13:
                tipoNumero = "K";
                break;
            default:
                tipoNumero = Integer.toString(numero);
        }

        String tipoNaipe = null;
        switch (naipe) {
            case 1:
                tipoNaipe = "♥";
                break;
            case 2:
                tipoNaipe = "♠";
                break;
            case 3:
                tipoNaipe = "♣";
                break;
            case 4:
                tipoNaipe = "♦";
                break;
        }
        System.out.print(tipoNumero + " " + tipoNaipe + "  ");

    }

}
