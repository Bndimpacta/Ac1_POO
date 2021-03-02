package JogoDaVelha;

import java.util.Scanner;

public class Jogo {

	/**
	 * Autor:
	 * 
	 * Caio Vinicius Gonçalves
	 * Gabriel Munhoz
	 * Matheus Dominici
	 * Jose Lucas
	 * */
	
//Declarando a matriz 3x3 com valores preenchidos
public static String[][] tabuleiro = {{"1","2","3"},
										  {"4","5","6"},
										  {"7","8","9"}};
	
public static void main(String[] args) { 
		
		Scanner ler = new Scanner(System.in);
		String posicao;
		int valida = 0, jogadas = 0;
		
		System.out.println(" Jogo da velha");
		initialize();
		
		/**
		 * faz o loop para que cada jogador possa ir jogando, 
		 * ate que gere um vencedor ou ocorra um empate
		 * */
		while(true){
			do{//Inicia o Jogador 1
				System.out.print("Jogador 1, informe uma posição: ");
				posicao = ler.next();
				while(!Step(posicao)){
					System.out.println("Jogada invalida, tente novamente!");
					System.out.print("Jogador 1, informe uma posição: ");
					posicao = ler.next();
					valida = 0;
				}
				Jogada(posicao, "X");
				valida = 1;
				
			}while(valida == 0);//verifica se a posição ao qual o jogador selecionou, já está ocupada.
			
			jogadas++;
			valida = 0;
			initialize();
			if(!(Status(jogadas) == -1)) {
				break; //sai do laço de repetição
			}
			
			do{// inicia no jogador 2
				System.out.print("Jogador 2, informe uma posição: ");
				posicao = ler.next();
				while(!Step(posicao)){
					System.out.println("Jogada invalida, tente novamente!");
					System.out.print("Jogador 2, informe uma posição: ");
					posicao = ler.next();
					valida = 0;
				}
				Jogada(posicao, "O");
				valida = 1;
				
			}while(valida == 0);//verifica se a posição ao qual o jogador selecionou, já está ocupada.
			
			jogadas++;
			valida = 0;
			initialize();
			if(!(Status(jogadas) == -1)) {
				break; //sai do laço
			}
		}
		
		//printa na tela quem foi o vencedor ou se houve um empate.
		if(Status(jogadas) == 0){
			System.out.println("Os dois jogadores empataram ");
		}else if(Status(jogadas) == 1) {
			System.out.println("O venceu!");
		}else if(Status(jogadas) == 2) {
			System.out.println("X venceu!");
		}
	}


	public static String [ ][ ] initialize() {
		//Inicializando as matrizes
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				//exibe a matriz 3x3
				System.out.print("   " + tabuleiro[i][j]);
			}
			System.out.println("\n");
		}
		return null;
	}
	
	public static boolean Step(String p) {
		//Ve se a posição ja foi preenchida
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(tabuleiro[i][j].equals(p))
					return true;
			}
		}
		return false;
	}
	
	public static void Jogada(String p, String j) {
		//definindo as posiçoes e seus parametros de entrada
		if(p.equals("1"))
			tabuleiro[0][0] = j;
		else if(p.equals("2"))
			tabuleiro[0][1] = j;
		else if(p.equals("3"))
			tabuleiro[0][2] = j;
		else if(p.equals("4"))
			tabuleiro[1][0] = j;
		else if(p.equals("5"))
			tabuleiro[1][1] = j;
		else if(p.equals("6"))
			tabuleiro[1][2] = j;
		else if(p.equals("7"))
			tabuleiro[2][0] = j;
		else if(p.equals("8"))
			tabuleiro[2][1] = j;
		else if(p.equals("9"))
			tabuleiro[2][2] = j;
	}
	
	
	public static int Status(int jogadas) {
		String[] T = new String[8];
		//-1 (o jogo pode continuar),
		int vencedor = -1;
		if(jogadas == 9) {
			//0 (ocorreu um empate),
			vencedor = 0;
			
		}
		// jogadas que devem ser feitas para ganhar
		T[0] = tabuleiro[0][0] + tabuleiro[0][1] + tabuleiro[0][2];
		T[1] = tabuleiro[1][0] + tabuleiro[1][1] + tabuleiro[1][2];
		T[2] = tabuleiro[2][0] + tabuleiro[2][1] + tabuleiro[2][2];
				
		T[3] = tabuleiro[0][0] + tabuleiro[1][0] + tabuleiro[2][0];
		T[4] = tabuleiro[0][1] + tabuleiro[1][1] + tabuleiro[2][1];
		T[5] = tabuleiro[0][2] + tabuleiro[1][2] + tabuleiro[2][2];
				
		T[6] = tabuleiro[0][0] + tabuleiro[1][1] + tabuleiro[2][2];
		T[7] = tabuleiro[0][2] + tabuleiro[1][1] + tabuleiro[2][0];
				
		//ve quem foi o vencedor
		int i=0;
		while(i<T.length) {	
			if(T[i].equals("XXX")) {
				//2 (X venceu)
				vencedor = 2;
			}else if(T[i].equals("OOO")){
				//1 (O venceu)
				vencedor = 1;
			}
			i++;
		}
		return vencedor;
	}
}
