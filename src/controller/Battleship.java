package controller;
import model.Field;
import util.Keyboard;

public class Battleship {

	public static void main(String[] args) {
		System.out.println("\n\t\t\t      BEM VINDO AO JOGO BATALHA NAVAL!\n\n"
				+ "\n\t   Em Batalha Naval, dois algozes jogadores disputam o controle dos mares"
				+ "\n\t   sobre um campo de frentes horizontais (1—20) e verticais (A—T)"
				+ "\n\t    • Ambos jogadores posicionam dez embarcações ao mar"
				+ "\n\t     cada posição requer uma coordenada cartesiana e uma direção cardeal"
				+ "\n\t     cada embarcação ocupa um espaço específico sobre o campo disponível"
				+ "\n\t      Porta-aeronaves — 5 espaços"
				+ "\n\t      Couraçado — 4 espaços"
				+ "\n\t      Cruzador — 3 espaços"
				+ "\n\t      Submarino — 3 espaços"
				+ "\n\t      Contratorpedeiro — 2 espaços"
				+ "\n\t    • Ambos jogadores disparam uma artilharia às cegas alternadamente"
				+ "\n\t     cada disparo ou acerta uma embarcação ou atravessa os mares"
				+ "\n\t     cada disparo revela o espaço atingido e o resultado logrado"
				+ "\n\t      1 embarcação inteira atingida — 1 naufrágio"
				+ "\n\t      10 naufrágios — vitória\n\n");

		Field playerOneAllyField = new Field();		// aqui é o 1º objeto campo para o jogador um posicionar embarcação
		Field PlayerTwoAllyField = new Field();		// aqui é o 2º objeto campo para o jogador dois posicionar embarcação
		Field playerOneAdversaryField = new Field();		// aqui é o 3º objeto campo para o jogador um disparar artilharia
		Field playerTwoAdversaryField = new Field();		// aqui é o 4º objeto campo para o jogador dois disparar artilharia
		String playerOneName[] = {null};
		String playerTwoName[] = {null};
		posicionarEmbarcacao(playerOneName, playerOneAllyField);
		posicionarEmbarcacao(playerTwoName, PlayerTwoAllyField);

		int turnos = 0;
		int acertosJogadorUm[] = {0};
		int acertosJogadorDois[] = {0};
		final String corVermelha = "\u001B[31m";
		final String corPadrao = "\u001B[0m";
		boolean algumaEsquadraNaufragou = false;
		while (algumaEsquadraNaufragou == false) {	// aqui é o laço principal do jogo
			if (turnos % 2 == 0) {		// aqui o resto define o turnos entre o jogador um e o jogador dois
				dispararArtilharia(playerOneName, playerOneAdversaryField, playerOneAllyField, PlayerTwoAllyField, acertosJogadorUm, corVermelha, corPadrao);
			} else {
				dispararArtilharia(playerTwoName, playerTwoAdversaryField, PlayerTwoAllyField, playerOneAllyField, acertosJogadorDois, corVermelha, corPadrao);
			}
			turnos ++;
			if (acertosJogadorUm[0] == 34) {		// 34 equivale aos espaços ocupados por todas embarcações
				System.out.println("\n\n\t\t\t        JOGADOR " + playerOneName[0].toUpperCase() + ", MISSÃO CUMPRIDA!\n"
						+ "\n\t\t\t\t    Você conquistou os mares,"
						+ "\n\t\t\t      afundando a esquadra inteira inimiga!\n");
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if (j == 0) {
							System.out.print("\t\t\t    " + PlayerTwoAllyField.field[i][j]);
						} else {
							System.out.print(PlayerTwoAllyField.field[i][j]);	
						}
					}
					System.out.println();
				}
				algumaEsquadraNaufragou = true;
			}
			if (acertosJogadorDois[0] == 34) {		// 34 equivale aos espaços ocupados por todas embarcações
				System.out.println("\n\n\t\t\t        JOGADOR " + playerTwoName[0].toUpperCase() + ", MISSÃO CUMPRIDA!\n"
						+ "\n\t\t\t\t    Você conquistou os mares,"
						+ "\n\t\t\t      afundando a esquadra inteira inimiga!\n");
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
							if (j == 0) {
								System.out.print("\t\t\t    " + playerOneAllyField.field[i][j]);
							} else {
								System.out.print(playerOneAllyField.field[i][j]);
							}
					}
					System.out.println();
				}
				algumaEsquadraNaufragou = true;
			}
		}
	}

	public static void posicionarEmbarcacao(String nomeJogador[], Field campoPosicao) {
		nomeJogador[0] = Keyboard.stringDataTypeInput("\n\n\t\t\t\t Jogador, insira seu nome:");
		System.out.println("\n\t\t\t\t Jogador " + nomeJogador[0] + ", seu turno.\n");
		String esquadra[] = { "Porta-aeronaves", "Couraçado", "Cruzador", "Submarino", "Contratorpedeiro" };
		int embarcacaoDisponivel[] = { 2, 2, 2, 2, 2 };

		for (int turno = 0; turno < 10; turno++) {
			String direcao[] = { "Norte", "Sul", "Leste", "Oeste" };
			int direcaoDisponivel[] = { 1, 2, 3, 4 };		// aqui os quatro itens são necessários para comparar se o usuário inseriu um número compatível com as direções disponíveis

			int escolhaEmbarcacao = 1111;
			while (escolhaEmbarcacao == 1111) {
				for (int i = 0; i < 5; i++) {
					if (i == 0) {
						System.out.println("\n\t\t\t\t\t ESQUADRA");
					}
					if (embarcacaoDisponivel[i] > 0) {
						System.out.println("\t\t\t         " + (i + 1) + " — " + esquadra[i] + " (" + embarcacaoDisponivel[i] + "/2)");
					}
				}
				System.out.println("\n\t\t\t\t        " + (turno + 1) + "ª escolha");
				try {
					int entradaEmbarcacao = Keyboard.intDataTypeInput("\t\t\t\t  Selecione a embarcação:");
					switch (entradaEmbarcacao) {
					case 1:
						if (embarcacaoDisponivel[0] > 0) {
							escolhaEmbarcacao = 5;		// 5 equivale à extensão do Porta-aviões
							embarcacaoDisponivel[0]--;
							break;
						} else {
							System.out.println("\n\t\t\t    Todos " + esquadra[0] + " estão apostos.");
							break;
						}
					case 2:
						if (embarcacaoDisponivel[1] > 0) {
							escolhaEmbarcacao = 4;		// 4 equivale à extensão do Couraçado
							embarcacaoDisponivel[1]--;
							break;
						} else {
							System.out.println("\n\t\t\t       Todos " + esquadra[1] + "s estão apostos.");
							break;
						}
					case 3:
						if (embarcacaoDisponivel[2] > 0) {
							escolhaEmbarcacao = 3;		// 3 equivale à extensão do Cruzador
							embarcacaoDisponivel[2]--;
							break;
						} else {
							System.out.println("\n\t\t\t       Todos " + esquadra[2] + "es estão apostos.");
							break;
						}
					case 4:
						if (embarcacaoDisponivel[3] > 0) {
							escolhaEmbarcacao = 3;		// 3 equivale à extensão do Submarino
							embarcacaoDisponivel[3]--;
							break;
						} else {
							System.out.println("\n\t\t\t      Todos " + esquadra[3] + "s estão apostos.");
							break;
						}
					case 5:
						if (embarcacaoDisponivel[4] > 0) {
							escolhaEmbarcacao = 2;		// 2 equivale à extensão do Contratorpedeiro
							embarcacaoDisponivel[4]--;
							break;
						} else {
							System.out.println("\n\t\t\t    Todos " + esquadra[4] + "s estão apostos.");
							break;
						}
					default:
						System.out.println("\n\t\t\t          Embarcação inexistente.");
					}
				} catch (NumberFormatException e) {
					System.out.println("\n\t\t\t  Embarcação inválida: opção para números.");
				}
			}

			int escolhaCoordenada[] = { 1111, 1111 };
			while (escolhaCoordenada[0] == 1111 && escolhaCoordenada[1] == 1111) {
				for (int linha = 0; linha < 20; linha++) {
					if (linha == 0) {
						System.out.println("\n\t\t\t\t\t - MAPA -");
						System.out.println("\n\t\t\t   A B C D E F G H I J K L M N O P Q R S T");
					}
					for (int coluna = 0; coluna < 20; coluna++) {
						if (coluna == 0 && linha < 9) {
							System.out.print("\t\t         " + (linha + 1) + " " + campoPosicao.field[linha][coluna]);
						} else if (coluna == 0) {
							System.out.print("\t\t        " + (linha + 1) + " " + campoPosicao.field[linha][coluna]);
						} else {
							System.out.print(campoPosicao.field[linha][coluna]);
						}
					}
					System.out.println();
				}
				System.out.println("\n\t\t\t\t        " + (turno + 1) + "ª posição");
				try {
					int entradaEixoHorizontal = Keyboard.intDataTypeInput("\t\t           Forneça o eixo horizontal — de 1 a 20:") + (-1);
					String entradaEixoVertical = Keyboard.stringDataTypeInput("\t\t           Forneça o eixo vertical — de A a T:").toUpperCase();
					int conversaoEixoVertical = 0;		// essa variável é desnecessária, porém está declarada para melhor clareza
					switch (entradaEixoVertical) {
					case "A":
						conversaoEixoVertical = 0;
						break;
					case "B":
						conversaoEixoVertical = 1;
						break;
					case "C":
						conversaoEixoVertical = 2;
						break;
					case "D":
						conversaoEixoVertical = 3;
						break;
					case "E":
						conversaoEixoVertical = 4;
						break;
					case "F":
						conversaoEixoVertical = 5;
						break;
					case "G":
						conversaoEixoVertical = 6;
						break;
					case "H":
						conversaoEixoVertical = 7;
						break;
					case "I":
						conversaoEixoVertical = 8;
						break;
					case "J":
						conversaoEixoVertical = 9;
						break;
					case "K":
						conversaoEixoVertical = 10;
						break;
					case "L":
						conversaoEixoVertical = 11;
						break;
					case "M":
						conversaoEixoVertical = 12;
						break;
					case "N":
						conversaoEixoVertical = 13;
						break;
					case "O":
						conversaoEixoVertical = 14;
						break;
					case "P":
						conversaoEixoVertical = 15;
						break;
					case "Q":
						conversaoEixoVertical = 16;
						break;
					case "R":
						conversaoEixoVertical = 17;
						break;
					case "S":
						conversaoEixoVertical = 18;
						break;
					case "T":
						conversaoEixoVertical = 19;
						break;
					default:
						conversaoEixoVertical = 1111;
						break;
					}
					if (campoPosicao.field[entradaEixoHorizontal][conversaoEixoVertical].equals("░░")) {	// aqui verifica se a coordenada está disponível nos limites do campo
						for (int comprimentoEmbarcacao = 0; comprimentoEmbarcacao < escolhaEmbarcacao; comprimentoEmbarcacao++) {	// aqui verifica se as direções estão livres
							if (entradaEixoHorizontal - escolhaEmbarcacao >= 0
							&& campoPosicao.field[entradaEixoHorizontal - comprimentoEmbarcacao][conversaoEixoVertical].equals("██")) {		// direção norte (y-1)
								direcaoDisponivel[0] = 1111;
								}							
							if (entradaEixoHorizontal + escolhaEmbarcacao <= 19
							&& campoPosicao.field[entradaEixoHorizontal + comprimentoEmbarcacao][conversaoEixoVertical].equals("██")) {		// direção sul (y+1)
								direcaoDisponivel[1] = 1111;
								}
							if (conversaoEixoVertical + escolhaEmbarcacao <= 19
							&& campoPosicao.field[entradaEixoHorizontal][conversaoEixoVertical + comprimentoEmbarcacao].equals("██")) {		// direção leste (x+1)
								direcaoDisponivel[2] = 1111;
								}
							if (conversaoEixoVertical - escolhaEmbarcacao >= 0
							&& campoPosicao.field[entradaEixoHorizontal][conversaoEixoVertical - comprimentoEmbarcacao].equals("██")) {		// direção oeste (x-1)
								direcaoDisponivel[3] = 1111;
								}
							}
						if (direcaoDisponivel[0] != 1111 || direcaoDisponivel[1] != 1111 || direcaoDisponivel[2] != 1111 || direcaoDisponivel[3] != 1111) {
							escolhaCoordenada[0] = entradaEixoHorizontal;
							escolhaCoordenada[1] = conversaoEixoVertical;
							} else {
								System.out.println("\n        Coordenada indisponível: outras embarcações ocupam todas quatro direções.\n");
								direcaoDisponivel[0] = 1;
								direcaoDisponivel[1] = 2;
								direcaoDisponivel[2] = 3;
								direcaoDisponivel[3] = 4;
							}
					} else {
						System.out.println("\n\t       Coordenada indisponível: outra embarcação ocupa esta localização.\n");
						
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("\n\t\t    Coordenada inexistente: opções de 1 a 20 e de A a T.\n");
				} catch (NumberFormatException e) {
					System.out.println("\n\t\t        Eixo horizontal inválido: opção para números.\n");
				}
			}

			int escolhaDirecao = 1111;
			while (escolhaDirecao == 1111) {		// aqui exibe as direções disponíveis, considerando os limites do campo
				System.out.println("\n\t\t\t\t\t  Direção");
				if (direcaoDisponivel[0] != 1111 && (escolhaCoordenada[0] - escolhaEmbarcacao >= 0)) {		// direção norte (y-1)
					System.out.println("\t\t\t\t\t 1 — " + direcao[0]);
				} else {
					direcaoDisponivel[0] = 1111;
				}
				if (direcaoDisponivel[1] != 1111 && (escolhaCoordenada[0] + escolhaEmbarcacao <= 19)) {		// direção sul (y+1)
					System.out.println("\t\t\t\t\t 2 — " + direcao[1]);
				} else {
					direcaoDisponivel[1] = 1111;
				}
				if (direcaoDisponivel[2] != 1111 && (escolhaCoordenada[1] + escolhaEmbarcacao <= 19)) {		// direção leste (x+1)
					System.out.println("\t\t\t\t\t 3 — " + direcao[2]);
				} else {
					direcaoDisponivel[2] = 1111;
				}
				if (direcaoDisponivel[3] != 1111 && (escolhaCoordenada[1] - escolhaEmbarcacao >= 0)) {		// direção oeste (x-1)
					System.out.println("\t\t\t\t\t 4 — " + direcao[3]);
				} else {
					direcaoDisponivel[3] = 1111;
				}
				try {
					int entradaDirecao = Keyboard.intDataTypeInput("\n\t\t\t\t     Forneça a direção:");
					if (entradaDirecao != 1111 && (entradaDirecao == direcaoDisponivel[0] || entradaDirecao == direcaoDisponivel[1]
					|| entradaDirecao == direcaoDisponivel[2] || entradaDirecao == direcaoDisponivel[3])) {		// aqui verifica se o número é compatível com as direções disponíveis
						escolhaDirecao = entradaDirecao;
					} else {
						System.out.println("\n\t\t\t    Direção inexistente ou indisponível.");
					}
				} catch (NumberFormatException e) {
					System.out.println("\n\t\t\t   Direção inválida: opção para números.");
				}
			}

			switch (escolhaDirecao) {		// aqui posiciona a embarcação, considerando a escolha da direção e o comprimento da embarcação
			case 1:
				for (int comprimentoEmbarcacao = 0; comprimentoEmbarcacao < escolhaEmbarcacao; comprimentoEmbarcacao++) {
					campoPosicao.field[escolhaCoordenada[0] + (- comprimentoEmbarcacao)][escolhaCoordenada[1]] = "██";
				}
				break;
			case 2:
				for (int comprimentoEmbarcacao = 0; comprimentoEmbarcacao < escolhaEmbarcacao; comprimentoEmbarcacao++) {
					campoPosicao.field[escolhaCoordenada[0] + comprimentoEmbarcacao][escolhaCoordenada[1]] = "██";
				}
				break;
			case 3:
				for (int comprimentoEmbarcacao = 0; comprimentoEmbarcacao < escolhaEmbarcacao; comprimentoEmbarcacao++) {
					campoPosicao.field[escolhaCoordenada[0]][escolhaCoordenada[1] + comprimentoEmbarcacao] = "██";
				}
				break;
			case 4:
				for (int comprimentoEmbarcacao = 0; comprimentoEmbarcacao < escolhaEmbarcacao; comprimentoEmbarcacao++) {
					campoPosicao.field[escolhaCoordenada[0]][escolhaCoordenada[1] + (- comprimentoEmbarcacao)] = "██";
				}
				break;
			}
		}
	}

	public static void dispararArtilharia(String nomeJogador[], Field campoDisparo, Field campoPosicao, Field campoAdversario, int acertos[], String corVermelha, String corPadrao) {
		System.out.println("\n\t\t\t\t Jogador " + nomeJogador[0] + ", seu turno.\n");

		boolean coordenadaEstaDisponivel = false;
		while (coordenadaEstaDisponivel == false) {
			for (int linha = 0; linha < 20; linha++) {
				if (linha == 0) {
					System.out.print("\n\t      - ESQUADRA INIMIGA -");
					System.out.println("\t\t\t      SUA ESQUADRA");
					System.out.println("\n     A B C D E F G H I J K L M N O P Q R S T");
				}
				for (int coluna = 0; coluna < 20; coluna++) {		// esse primeiro laço exibe a Esquadra inimiga
					if (coluna == 0 && linha < 9) {
						System.out.print("   " + (linha + 1) + " " + campoDisparo.field[linha][coluna]);
					} else if (coluna == 0) {
						System.out.print("  " + (linha + 1) + " " + campoDisparo.field[linha][coluna]);
					} else {
						System.out.print(campoDisparo.field[linha][coluna]);
					}
				}
				for (int coluna = 0; coluna < 20; coluna++) {		// esse segundo laço exibe a Esquadra aliada
					if (coluna == 0) {
						System.out.print("\t" + campoPosicao.field[linha][coluna]);
					} else {
						System.out.print(campoPosicao.field[linha][coluna]);
					}
				}
				System.out.println();
			}
			try {
				int entradaEixoHorizontal = Keyboard.intDataTypeInput("\n     Indique o eixo horizontal — de 1 a 20:") + (-1);
				String entradaEixoVertical = Keyboard.stringDataTypeInput("     Indique o eixo vertical — de A a T:").toUpperCase();
				int conversaoEixoVertical = 0;		// essa variável é desnecessária, porém está declarada para melhor clareza
				switch (entradaEixoVertical) {
				case "A":
					conversaoEixoVertical = 0;
					break;
				case "B":
					conversaoEixoVertical = 1;
					break;
				case "C":
					conversaoEixoVertical = 2;
					break;
				case "D":
					conversaoEixoVertical = 3;
					break;
				case "E":
					conversaoEixoVertical = 4;
					break;
				case "F":
					conversaoEixoVertical = 5;
					break;
				case "G":
					conversaoEixoVertical = 6;
					break;
				case "H":
					conversaoEixoVertical = 7;
					break;
				case "I":
					conversaoEixoVertical = 8;
					break;
				case "J":
					conversaoEixoVertical = 9;
					break;
				case "K":
					conversaoEixoVertical = 10;
					break;
				case "L":
					conversaoEixoVertical = 11;
					break;
				case "M":
					conversaoEixoVertical = 12;
					break;
				case "N":
					conversaoEixoVertical = 13;
					break;
				case "O":
					conversaoEixoVertical = 14;
					break;
				case "P":
					conversaoEixoVertical = 15;
					break;
				case "Q":
					conversaoEixoVertical = 16;
					break;
				case "R":
					conversaoEixoVertical = 17;
					break;
				case "S":
					conversaoEixoVertical = 18;
					break;
				case "T":
					conversaoEixoVertical = 19;
					break;
				default:
					conversaoEixoVertical = 1111;
					break;
				}
				if (!campoAdversario.field[entradaEixoHorizontal][conversaoEixoVertical].equals(corVermelha + "▓▓" + corPadrao)
				&& !campoAdversario.field[entradaEixoHorizontal][conversaoEixoVertical].equals("▒▒")) {		// aqui verifica se a coordenada recebeu um disparo anterior
					if (campoAdversario.field[entradaEixoHorizontal][conversaoEixoVertical].equals("██")) {
						campoAdversario.field[entradaEixoHorizontal][conversaoEixoVertical] = corVermelha + "▓▓" + corPadrao;
						campoDisparo.field[entradaEixoHorizontal][conversaoEixoVertical] = corVermelha + "▓▓" + corPadrao;
						System.out.println("\n\t  EMBARCAÇÃO INIMIGA ATINGIDA!\n");
						acertos[0]++;
						coordenadaEstaDisponivel = true;
					} else {
						campoAdversario.field[entradaEixoHorizontal][conversaoEixoVertical] = "▒▒";
						campoDisparo.field[entradaEixoHorizontal][conversaoEixoVertical] = "▒▒";
						System.out.println("\n\t        DISPARO PERDIDO!\n");
						coordenadaEstaDisponivel = true;
					}
				} else {
					System.out.println("\n\t    Coordenada indisponível:"
							+ "\n    outro disparo atingiu esta localização.");
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("\n\t    Coordenada inexistente:"
						+ "\n\t  opções de 1 a 20 e de A a T.");
			} catch (NumberFormatException e) {
				System.out.println("\n\t   Eixo horizontal inválido:"
						+ "\n\t      opção para números.");
			}
		}
	}
}