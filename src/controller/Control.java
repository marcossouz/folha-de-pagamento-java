package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import model.Empregado;
import view.Capture;
import view.Screen;

/**
 * 
 * @author Marcos Souza
 *
 */
public class Control {
	
	static Capture capture = new Capture();
	static List<Empregado> empregados = new ArrayList<Empregado>();
	private static int id = 1000;
	private static int idSindicato = 50;

	/**
	 * Gera aleatoriamente um id a partir de 1000
	 * @return id que identifica o empregado na empresa
	 */
	public static int geraIdEmpregado() {
		return id++;
	}
	/**
	 * Gera aleatoriamente um id a partir de 50
	 * @return id que identifica o empregado no sindicato
	 */
	public static int geraIdSindicato() {
		return idSindicato++;
	}
	/**
	 * tres funcionario inseridos no sistema para testes
	 */
	static void setStart() {
		Empregado emp = new Empregado();
		Empregado emp2 = new Empregado();
		Empregado emp3 = new Empregado();
		
		emp.setTipo(1);
		emp.setSindicato(1);
		emp.setTaxaSindical(24.10);
		emp.setFormaPagamento(1);
		emp.setNome("Pessoa Aleatoria");
		emp.setValorHora(32);
		emp.setSalario(0);
		emp.setPercentualComissao(0);
		emp.endereco.setRua("Grota");
		emp.endereco.setNum(13);
		emp.endereco.setBairro("Jacintinho");
		emp.endereco.setCidade("Maceio");
		emp.endereco.setEstado("AL");
		
		emp3.setTipo(3);
		emp3.setSindicato(1);
		emp3.setTaxaSindical(29.40);
		emp3.setFormaPagamento(2);
		emp3.setNome("Marcos Souza");
		emp3.setValorHora(32);
		emp3.setSalario(1500);
		emp3.setPercentualComissao(0.20);//20 por cento
		emp3.endereco.setRua("Gilvan Carmelo");
		emp3.endereco.setNum(123);
		emp3.endereco.setBairro("Feitosa");
		emp3.endereco.setCidade("Maceio");
		emp3.endereco.setEstado("AL");

		emp2.setTipo(2);
		emp2.setSindicato(0);
		emp2.setTaxaSindical(0);
		emp2.setFormaPagamento(3);
		emp2.setNome("Jose Souza");
		emp2.setValorHora(27);
		emp2.setSalario(3000);
		emp2.setPercentualComissao(0.25);//25 por cento
		emp2.endereco.setRua("Lourenco Albuquerque");
		emp2.endereco.setNum(32);
		emp2.endereco.setBairro("Graciliano");
		emp2.endereco.setCidade("Maceio");
		emp2.endereco.setEstado("AL");

		empregados.add(emp);
		empregados.add(emp2);
		empregados.add(emp3);
		
	}
	/**
	 * Adiciona um empregado ao sistema
	 */
	static void addEmpregado() {
		try {

			Empregado emp = new Empregado();

			emp.setTipo(capture.tipo());
			switch(emp.getTipo()){
			case 1:
				emp.setSalario(0);
				break;
			case 2:
				emp.setSalario(3000); //Salario fixo mensal
				break;
			case 3:
				emp.setSalario(1500); //Salario fixo quinzenal
				break;
			default:
				System.out.println("tipo invalido, retornando");
				return;
			}
			emp.setSindicato(capture.sindicato());
			if(emp.getSindicato() == 1) emp.setTaxaSindical(capture.taxaSindical());
			emp.setFormaPagamento(capture.formaPagamento());
			emp.setNome(capture.nome());
			emp.setValorHora(capture.valorHora());
			emp.setComissao(capture.percentualComissao());
			emp.endereco.setRua(capture.rua());
			emp.endereco.setNum(capture.num());
			emp.endereco.setBairro(capture.bairro());
			emp.endereco.setCidade(capture.cidade());
			emp.endereco.setEstado(capture.estado());

			empregados.add(emp);
		} catch (Exception ex) {
			return;
		}
	}
	/**
	 * Imprime os funcionario cadastrados no sistema com as informacoes que selecionei abaixo
	 */
	static void printEmp() {
		for (int i = 0; i < empregados.size(); i++) {
			System.out.print(empregados.get(i).getId() + " ");
			System.out.println(empregados.get(i).getNome());
			System.out.println("Id Sindicato: " + empregados.get(i).getIdSindicato());
			System.out.println("Salario ate o momento: " + empregados.get(i).getSalario());
			System.out.println("Descontos : " + empregados.get(i).getTaxaServico());
			System.out.println("Entrada: " + empregados.get(i).getEntrada());
			System.out.println("Saida: " + empregados.get(i).getSaida());
			System.out.println();
		}
	}
	/**
	 * Remove um empregado cadastrado no sistema, mediante a digitacao de seu identificador
	 */
	public static void removeEmpregado() {
		System.out.print("id do empregado a remover: ");
		int id = capture.opcaoMenu();
		boolean bool = false;
		
		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id) {
				empregados.remove(i);
				bool = true;
			}
		}
		if(bool) {
			System.out.println("Empregado removido com sucesso");
		} else {
			System.out.println("Codigo nao encontrado, nao ha o que remover");
		}
	}
	/**
	 * Entrada de ponto para funcionarios do tipo 1
	 */
	public static void entrada() {
		System.out.print("codCartao(idEmpregado): ");
		int id = capture.opcaoMenu();
		boolean bool = false;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id) {
				if(empregados.get(i).getEntrada() != null && empregados.get(i).getSaida() == null) {
					System.out.println("Voce ja deu entrada, retornando");
					return;
				}
				if(empregados.get(i).getTipo() != 1) {
					System.out.println("Voce nao precisa marcar o ponto");
					return;
				}
				
				empregados.get(i).setEntrada(sdf.format(new Date()));
				empregados.get(i).setSaida(null);
				System.out.println("   Entrada - " + sdf.format(new Date()));
				bool = true;
			}
		}
		
		if(!bool) System.out.println("Codigo nao encontrado");

	}
	/**
	 * Saida de ponto para funcionarios do tipo 1
	 */
	public static void saida() {
		System.out.print("codCartao(idEmpregado): ");
		int id = capture.opcaoMenu();
		boolean bool = false;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id) {
				if(empregados.get(i).getTipo() != 1) {
					System.out.println("Voce nao precisa marcar o ponto");
					return;
				}
				empregados.get(i).setSaida(sdf2.format(new Date()));
				if (empregados.get(i).getEntrada() != null) {
					
					bool = true;
					System.out.println("   Saida - " + sdf2.format(new Date()));
					try {
						//teste de lancamento correto do ponto foi feito aqui
						Date dataDe = sdf.parse(/*"25/10/2016 08:00"*/empregados.get(i). getEntrada());
						Date dataAte = sdf2.parse(/*"25/10/2016 18:00"*/empregados.get(i). getSaida());

						long tempoTrabalhado = (dataAte.getTime() - dataDe.getTime()) / (1000 * 60);// minutos
						if (tempoTrabalhado < 0) {
							tempoTrabalhado = ((-1) * tempoTrabalhado) - 240;
						}
						
						double extra = 0, valorExtra = empregados.get(i).getValorHora() * 0.015;
						if(tempoTrabalhado > 8) extra = tempoTrabalhado/60 - 8; 

						System.out.println("Tempo Trabalhado: " + tempoTrabalhado / 60 + ":" + tempoTrabalhado % 60);
						empregados.get(i).setSalario(empregados.get(i).getSalario()
								+ (tempoTrabalhado * (empregados.get(i).getValorHora() / 60)) + extra * valorExtra);// setando valor atual
																							
					} catch (Exception ex) {
						System.out.println("erro ao registrar saida");
					}

				} else {
					System.out.println("Entrada nao identificada");
					return;
				}
			}
		}
		
		if(!bool) System.out.println("Codigo nao encontrado");

	}
	/**
	 * adiciona taxas seja sindical ou governamental,  ele adiciona somando as taxas
	 */

	public static void adcTaxa() {
		System.out.print("codCartao(idEmpregado): ");
		int id = capture.opcaoMenu();

		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id) {
				empregados.get(i).setTaxaServico(empregados.get(i).getTaxaServico() + capture.taxa());
			}
		}

	}
	/**
	 * Edicao de dados cadastrais
	 */
	public static void editarDados() {

		System.out.print("codCartao(idEmpregado): ");
		int id = capture.opcaoMenu();

		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id) {
				Empregado emp = empregados.get(i);

				System.out.println("tipo atual: " + emp.getTipo());
				if(capture.alterar()){
					emp.setTipo(capture.tipo());
					switch(emp.getTipo()){
					case 1:
						emp.setSalario(0);
						break;
					case 2:
						emp.setSalario(3000);
						break;
					case 3:
						emp.setSalario(1500);
						break;
					}
				}
				
				System.out.println("participa do Sindicato?(0 nao, 1 sim): " + emp.getSindicato());
				if(capture.alterar()){
					emp.setTipo(capture.sindicato());
					if(emp.getSindicato() == 1){
						emp.setTaxaSindical(capture.taxaSindical());
					}
				}
				
				System.out.println("forma de Pagamento: " + emp.getFormaPagamento());
				if(capture.alterar()){
				emp.setTipo(capture.formaPagamento());
				}
				
				System.out.println("Nome Atual: " + emp.getNome());
				if(capture.alterar()){
				emp.setNome(capture.nome());
				}
				
				if(emp.getTipo() == 1){
					System.out.println("Valor da Hora trabalhada: " + emp.getValorHora());
					if(capture.alterar()){
					emp.setValorHora(capture.valorHora());
					}
				}
				
				if(emp.getTipo() == 3){
					System.out.println("Valor do percentual da Comissao: " + emp.getPercentualComissao());
					if(capture.alterar()){
					emp.setComissao(capture.percentualComissao());
					}
				}
				
				System.out.println("Rua atual: " + emp.endereco.getRua());
				if(capture.alterar()){
				emp.endereco.setRua(capture.rua());
				}
				System.out.println("Numero atual: " + emp.endereco.getNum());
				if(capture.alterar()){
				emp.endereco.setNum(capture.num());
				}
				System.out.println("Bairro atual: " + emp.endereco.getBairro());
				if(capture.alterar()){
				emp.endereco.setBairro(capture.bairro());
				}
				System.out.println("Cidade atual: " + emp.endereco.getCidade());
				if(capture.alterar()){
				emp.endereco.setCidade(capture.cidade());
				}
				System.out.println("Estado atual: " + emp.endereco.getEstado());
				if(capture.alterar()){
				emp.endereco.setEstado(capture.estado());
				}
			}
		}

	}
	/**
	 * Informacoes sobre o sindicato
	 */

	public static void sindicato() {
		System.out.print("codCartao(idEmpregado): ");
		int id = capture.opcaoMenu();

		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id) {
				Empregado emp = empregados.get(i);
				if (emp.getSindicato() == 0) {
					System.out.println("Nao pertence ao sindicato.");
					switch (capture.sindicato()) {
					case 1:
						emp.setSindicato(1);
						System.out.println("Adicionado ao sindicato com sucesso");
						break;
					case 0:
						System.out.println("Ok, continua nao pertencendo");
						break;
					}
				} else {
					System.out.println("Voce ja eh afiliado ao sindicato.");
					switch (capture.sindicato()) {
					case 0:
						emp.setSindicato(0);
						System.out.println("Removido do sindicato com sucesso");
						break;
					case 1:
						System.out.println("OK, continua afiliado");
						break;
					}
				}
			}
		}
	}
	
	/**
	 * roda a folha de pagamento e libera a quantia de cada empregado que recebe nesse dia
	 */
	public static void rodarFolhaDePagamento() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.print("Data: " + sdf.format(new Date()) + " - ");
		
		try {
			
			//teste de folha aqui 07/10/2016
							//    14/10/2016
							//	  31/10/2016
			Date date = sdf.parse(/*"14/10/2016"*/sdf.format(new Date())); 
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(date);
			
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
			int diaDoMes = gc.get(GregorianCalendar.DAY_OF_MONTH);
			System.out.println( Screen.diaPorExt(diaDaSemana));
			
			for (int i = 0; i < empregados.size(); i++) {
				Empregado emp = empregados.get(i);
				
				switch(emp.getTipo()){
				case 1:
					if(diaDaSemana == 6){
						emp.setSalario(0);
						emp.setTaxaServico(0);
						
						switch(emp.getFormaPagamento()){
						case 1:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Cheque no valor de " + (emp.getSalario()
							- emp.getTaxaServico() - emp.getTaxaSindical()) + " enviado pelos correios com sucesso");
							break;
						case 2:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Cheque no valor de " + (emp.getSalario()
									- emp.getTaxaServico() - emp.getTaxaSindical()) + " impresso com sucesso");
							
							break;
						case 3:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Salario no valor de " + (emp.getSalario()
									- emp.getTaxaServico() - emp.getTaxaSindical()) + " Depositado na conta com sucesso");
							break;
						}
					}
					
					break;
				case 2:
					if(Datas.ultimoDiaMes(date)){
						emp.setTaxaServico(0);
						switch(emp.getFormaPagamento()){
						case 1:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Cheque no valor de " + (emp.getSalario()
							- emp.getTaxaServico() - emp.getTaxaSindical()) + " enviado pelos correios com sucesso");
							break;
						case 2:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Cheque no valor de " + (emp.getSalario()
									- emp.getTaxaServico() - emp.getTaxaSindical()) + " impresso com sucesso");
							
							break;
						case 3:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Salario no valor de " + (emp.getSalario()
									- emp.getTaxaServico() - emp.getTaxaSindical()) + " Depositado na conta com sucesso");
							break;
						}
					}
					
					break;
				case 3:
					if(diaDaSemana == 6 && (diaDoMes >= 14 && diaDoMes <= 20)){
						emp.setTaxaServico(0);
						switch(emp.getFormaPagamento()){
						case 1:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Cheque no valor de " + (emp.getSalario()
							- emp.getTaxaServico() - emp.getTaxaSindical() + emp.getComissao()) + " enviado pelos correios com sucesso");
							break;
						case 2:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Cheque no valor de " + (emp.getSalario()
									- emp.getTaxaServico() - emp.getTaxaSindical() + emp.getComissao()) + " impresso com sucesso");
							
							break;
						case 3:
							System.out.println(emp.getId() + " " + emp.getNome());
							System.out.println("Salario no valor de " + (emp.getSalario()
									- emp.getTaxaServico() - emp.getTaxaSindical() + emp.getComissao()) + " Depositado na conta com sucesso");
							break;
						}
					}
					break;
				}
			}
				
		} catch (Exception ex) {
			System.out.println("erro ao registrar ao rodar a folha de pagamento");
			return;
		}
	}
	/**
	 * Insere no sistema o resultado das vendas dos empregados tipo 3
	 */
	public static void resultadoDeVenda() {
		System.out.print("codCartao(idEmpregado): ");
		int id = capture.opcaoMenu();
		boolean bool = false;
		for (int i = 0; i < empregados.size(); i++) {
			if (empregados.get(i).getId() == id && empregados.get(i).getTipo() == 3) {
				
				String data = capture.recebeDataVenda(); 
				if(data == null) Screen.dataErrada();
				empregados.get(i).setComissao(empregados.get(i).getComissao() + (capture.valorVenda() * empregados.get(i).getPercentualComissao()));
				
				
				bool = true;
			}
		}
		if(!bool) System.out.println("Codigo nao encontrado/Voce nao recebe comissao");
	}
}
