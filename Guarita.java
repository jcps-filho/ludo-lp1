import java.util.Arrays;

/**
 *
 * Implementa a guarita, um espaço reservado as peças fora do jogo.
 * 
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 */
public class Guarita {
    private final Casa[] casas;
    private final String cor;

    /**
     * Inicializa a guarita vazia.
     * @param cor Cor
     */
    public Guarita(String cor) {
        this.cor = cor;
        this.casas = new Casa[] { new Casa(this), new Casa(this), new Casa(this), new Casa(this) };
    }
    
    /**
     * Obtém a casa associada a esta guarita de acordo com o índice especificado.
     * @param indice Índice da casa, entre 0 (inclusivo) e 4 (exclusivo).
     * @return Casa
     */
    public Casa getCasa(int indice) {
        return casas[indice];
    }
    
    /**
     * Obtém o número de peças presentes nas casas da guarita.
     * @return Número de peças
     */
    public int getNumeroDePecas() {
        int s = 0;
        for (Casa casa : casas) {
            if (casa.possuiPeca()) {
                s++;
            }
        }
        return s;
    }
    
    /**
     * Obtém uma cópia da lista de casas desta guarita.
     * Útil para iterar sobre as casas usando o for(each).
     * @return Array contendo as casas.
     */
    public Casa[] getTodasAsCasas() {
        return Arrays.copyOf(casas, casas.length);
    }

    /**
     * @return A cor da guarita
     */
    public String getCor() {
        return cor;
    }
    
    /**
     * Obtem uma casa livre, ou seja, sem peca na guarita.
     * @return Casa que n�o possui peca na guarita ou nulo
     */
    public Casa getGuaritaLivre() {
         for (Casa casa : casas) {
             if (!casa.possuiPeca()) {
                 return casa;
             }
         }
		return null;
    }
}
