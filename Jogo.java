/**
 * Implementa as mecânicas e regras do jogo Ludo.
 *
 * @author Alan Moraes / alan@ci.ufpb.br
 * @author Victor Koehler / koehlervictor@cc.ci.ufpb.br
 */

import java.io.IOException;

public class Jogo {

    // Tabuleiro do jogo
    private final Tabuleiro tabuleiro;
    
    // Dados do jogo.
    private final Dado[] dados;
    
    // As quatro guaritas do jogo.
    private Guarita[] guaritaDaVez = new Guarita[] {new Guarita("VERDE"),new Guarita("VERMELHO"), new Guarita("AZUL"), new Guarita("AMARELO")}; 
    
    private Casa casaGuarita;
    private Casa casaInicio;
    private boolean guaritaAtiva = false;
    private Peca[] peca = new Peca[4];
    private String[] cores = new String[] {"VERDE", "VERMELHO", "AZUL", "AMARELO"};
    private int c = 0;
    private String vez = cores[c];
    private int x;
  
    
    /**
     * Construtor padrão do Jogo Ludo.
     * Isto é, um jogo de Ludo convencional com dois dados.
     */
    public Jogo() {
//    	this.dados = new Dado[] {new Dado(), new Dado() };
//		this.tabuleiro = new Tabuleiro();
//    	
//		inicializaJogo();
    	this(2);
    }
    
    /**
     * Construtor do Jogo Ludo para inserção de um número arbitrário de dados.
     * @param numeroDados Número de dados do jogo.
     */
    public Jogo(int numeroDados) {
		this.tabuleiro = new Tabuleiro();
        this.dados = new Dado[numeroDados];
        
        for (int i = 0; i < this.dados.length; i++) {
            // remover par�metro do construtor para dado n�o batizado
            this.dados[i] = new Dado(i);
        }

        inicializaJogo();
    }

    /**
     * Construtor do Jogo Ludo para inserção de dados arbitrários.
     * Útil para inserir dados "batizados" e fazer testes.
     * @param dados Dados
     */
    public Jogo(Dado[] dados) {
		this.tabuleiro = new Tabuleiro();
        this.dados = dados;
        assert dados.length > 0; // TO BE REMOVED

        inicializaJogo();
    }

    private void inicializaJogo() {

        // AQUI SUGERE-SE QUE SE INSIRA A INICIALIZAÇÃO DO JOGO
        // ISTO É, A INSERÇÃO DAS PEÇAS NO TABULEIRO E AS DEFINIÇÕES DOS CAMPOS
        // SE NECESSÁRIO, MODIFIQUE A ASSINATURA DO MÉTODO
        //
        
        for(int i=0; i<=3; i++){
            guaritaDaVez[i] = tabuleiro.getGuarita(cores[i]);
            for (Casa casaGuarita : guaritaDaVez[i].getTodasAsCasas()) {
                Peca novaPeca = new Peca(cores[i]);
                novaPeca.mover(casaGuarita);
            }
        }
    
        System.out.println("INÍCIO DE JOGO.");
        System.out.println("JOGUE OS DADOS: " + vez);
        
    }
    
    //Indica qual jogador deve jogar no momento.
    public void corDaVez(int i, int n0, int n1){
        
           int m=0;
           for (Casa casaGuarita : guaritaDaVez[i].getTodasAsCasas()) {
        	   if(casaGuarita.possuiPeca() == true){
        		   m++;
        	   }
           }
           if(n0 == n1){
                int t=0;
                guaritaAtiva = true;
                if(m==4){
                	cls();
                	System.out.println("ESCOLHA UMA PE�A NA GUARITA PARA INICIAR O SEU JOGO:"+ getJogadorDaVez());
//                    casaGuarita =guaritaDaVez[i].getCasa(i);
//                    peca[i] = casaGuarita.getPeca();
//                    casaInicio = tabuleiro.getCasaInicio(cores[i]);
//                    peca[i].mover(casaInicio);
                    x=1;
//                    System.out.println("VEZ DO: "+ cores[i] + ".");
//                    System.out.println("JOGUE OS DADOS NOVAMENTE.");
                }
                else{
                    cls();
                    x = 1;
                    System.out.println("VEZ DO: "+ cores[i] + ".");
                    System.out.println("MOVA UMA PEÇA DO TABULEIRO OU TIRE UMA PEÇA DA GUARITA.");
                }  
           }
           else if(m<4 && n0!=n1){
        	   x = 1;
                System.out.println("MOVA UMA PEÇA DO TABULEIRO.");
           }
           else if(m==4 && n0!=n1){
                c++;
                if(c==4){
                    c=0;
                }
                x = 0;
                vez=cores[c];
                cls();
                System.out.println("VEZ DO: " + vez);
            }
      
    }
    
    /**
     * Método invocado pelo usuário através da interface gráfica ou da linha de comando para jogar os dados.
     * Aqui deve-se jogar os dados e fazer todas as verificações necessárias.
     * 
     * c==0 : VERDE
     * c==1 : VERMELHO
     * c==2 : AZUL
     * c==3 : AMARELO
     * 
     * n = VALOR DOS DADOS
     */
    public void rolarDados() {

        // AQUI SE IMPLEMENTARÁ AS REGRAS DO JOGO.
        // TODA VEZ QUE O USUÁRIO CLICAR NO DADO DESENHADO NA INTERFACE GRÁFICA,
        // ESTE MÉTODO SERÁ INVOCADO.
        if(c==4){
            c=0;
        }
        int[] n = new int[2];
        int i = 0;
       
        
		// Aqui percorremos cada dado para lançá-lo individualmente.
        if(x == 0) {
        	for (Dado dado : dados) {
        		dado.rolar();
        		n[i]=dado.getValor();
        		i++;
        	}
        } else {
        	System.out.println("CADA JOGADOR S� PODE ROLAR OS DADOS UMA VEZ POR TURNO:"+ getJogadorDaVez());
        	return;
        }
        
        if(c==0){
            corDaVez(c, n[0], n[1]);
        }
        else if(c==1){
            corDaVez(c, n[0], n[1]);
        }
        else if(c==2){
            corDaVez(c, n[0], n[1]);
        }
        else if(c==3){
            corDaVez(c, n[0], n[1]);
        }
        
    }
    
    /**
     * Método invocado pelo usuário através da interface gráfica ou da linha de comando quando escolhida uma peça.
     * Aqui deve-se mover a peça e fazer todas as verificações necessárias.
     * @param casa Casa escolhida pelo usuário/jogador.
     */
    public void escolherCasa(Casa casa) {

        // AQUI SE IMPLEMENTARÁ AS REGRAS DO JOGO.
        // TODA VEZ QUE O USUÁRIO CLICAR EM UMA CASA DESENHADA NA INTERFACE GRÁFICA,
        // ESTE MÉTODO SERÁ INVOCADO.
        
        
        //
        // TRECHO DE EXEMPLO
        //
        
        // Perguntamos à casa se ela possui uma peça. 
        // Se não possuir, não há nada para se fazer.
        if (!casa.possuiPeca()) {
            return;
        }
        
        // Perguntamos à casa qual é a peça.
        Peca peca = casa.getPeca();
        
        if(peca.getCor() != getJogadorDaVez()) {
        	System.out.println("N�O � A SUA VEZ DE JOGAR AINDA:"+peca.getCor());
        	return;
        }

        // Percorremos cada dado, somando o valor nele à variável somaDados.
        int somaDados = 0;
        for (Dado dado : dados) {
            somaDados += dado.getValor();
        }
        
        // Percorreremos N casas.
        Casa proximaCasa = casa;
        for (int i = 0; i < somaDados && proximaCasa != null; i++) {
        	
        	//Valida se � segura
        	if(proximaCasa.ehEntradaZonaSegura()) {
        		if(peca.getCor() == proximaCasa.getCasaSegura().getCor()) {
        			proximaCasa = proximaCasa.getCasaSegura();
        		}
        	}
        	//Regra da zona segura
        	//Implementa��o que garante a regra da zona de segurar de ir e voltar baseado na dado
        	if(proximaCasa.ehCasaFinal()) {
        		if(i == somaDados) {
        			if(proximaCasa.getQuantidadePecas() == 4) {
        				System.out.println("Jogador " + peca.getCor() +" venceu!");
        				System.exit(1);
        			}
        		} else {
        			
        			while(x < 5 && i != somaDados) {
        				proximaCasa = proximaCasa.getCasaAnterior();
        				x++;
        				i++;
        			}
        		}
        	}
        	x = 0;
        	proximaCasa = proximaCasa.getCasaSeguinte();
        } 
        if(peca.getCasa().getGuarita() != null) {
        		casaInicio = tabuleiro.getCasaInicio(cores[c]);
        		proximaCasa = casaInicio;
        }

        if (proximaCasa != null) {
            // Finalmente, a variável casaN contém a casa que a peça deve ser inserida.
        	if(proximaCasa != tabuleiro.getCasaInicio(getJogadorDaVez())) {
        		if (peca.getCor() != vez) {
        			cls();
        			System.out.println("VOCÊ NÃO PODE MOVER ESSA PEÇA.");
        			System.out.println("MOVA UMA PEÇA DA COR " + vez + ".");
        		} else {
        			if(!proximaCasa.possuiPeca()) {
        				peca.mover(proximaCasa);
        			} else if(proximaCasa.getPeca().getCor() == peca.getCor()){
        				if(proximaCasa.ehCasaFinal()) {
        					peca.mover(proximaCasa);
        				} else {
        					System.out.println("J� EXISTE UMA PE�A"+ getJogadorDaVez() +" NA CASA DESTINO");
        					System.out.println("POR FAVOR ESCOLHA OUTRA PE�A PARA MOVER");
        					return;
        				}
        			} else {
        				for(int i = 0; i < guaritaDaVez.length - 1;i++) {
        					if(guaritaDaVez[i].getCor() == proximaCasa.getPeca().getCor()) {
        						proximaCasa.getPeca().mover(guaritaDaVez[i].getGuaritaLivre());
        					}
        				}
        				peca.mover(proximaCasa);
        			}
        		}
        		guaritaAtiva = false;
        		c++;
        		if (c == 4) {
        			c = 0;
        		}
        		vez = cores[c];
        		cls();
        		x = 0;
        		System.out.println("VEZ DO: " + vez);
        	}else {
        		x=0;
        		System.out.println("JOGUE OS DADOS NOVAMENTE:"+ getJogadorDaVez());
        		peca.mover(proximaCasa);
            }
        }
        //Caso a peça esteja na guarita.
        else {
            
            if (casa.pertenceGuarita() && guaritaAtiva == true && peca.getCor() == vez ){
               
                peca.mover(casaInicio);
                guaritaAtiva = false;
                c++;
                if(c==4){
                    c=0;
                }
                vez=cores[c];
                cls();
                x = 0;
                System.out.println("VEZ DO: " + vez);
            }
            else if (casa.pertenceGuarita() && guaritaAtiva == false && peca.getCor() == vez){
               cls();
               System.out.println("VOCÊ PRECISA TIRAR DOIS NÚMEROS IGUAIS PARA MOVER AS PEÇAS DA GUARITA.");
                System.out.println("MOVA UMA PEÇA DO TABULEIRO DA COR " + vez + ".");
            }
        }
        
       
    }
    
    /**
     * Retorna o jogador que deve jogar os dados ou escolher uma peça.
     * @return Cor do jogador.
     */
    public String getJogadorDaVez() {
    	switch(c) {
    		case 0:
    			return "VERDE";
    		case 1:
    			return "VERMELHO";
    		case 2:
    			return "AZUL";
    		case 3:
    			return "AMARELO";
    		default:
    			return "ERRO!";
    	}
    }
    
    /**
     * O tabuleiro deste jogo.
     * @return O tabuleiro deste jogo.
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Retorna o i-ésimo dado deste jogo entre 0 (inclusivo) e N (exclusivo).
     * Consulte getQuantidadeDeDados() para verificar o valor de N
     * (isto é, a quantidade de dados presentes).
     * @param i Indice do dado.
     * @return O i-ésimo dado deste jogo.
     */
    public Dado getDado(int i) {
        return dados[i];
    }
    
    /**
     * Obtém a quantidade de dados sendo usados neste jogo.
     * @return Quantidade de dados.
     */
    public int getQuantidadeDados() {
        return dados.length;
    }
    /**
     * Limpar console.
     */
    public static void cls(){
    try
    {   
        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
    }catch(Exception E)
        {
            System.out.println(E);
        }
    }
}
