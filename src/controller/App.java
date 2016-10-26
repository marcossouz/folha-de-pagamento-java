package controller;

import view.*;

public class App {

	public static void main(String[] args) {
		Screen screen = new Screen();
		Capture capture = new Capture();
		boolean loop = true;
		Control.setStart();
		
		do {
			
			screen.menuPrincipal();
			switch (capture.opcaoMenu()) {
			case 9:
				Control.printEmp();
				break;
			case 0:
				loop = false;
				break;
				
			case 1:
				
				Control.addEmpregado();
				break;
			case 2:
				Control.editarDados();
				break;
			case 3:
				Control.removeEmpregado();
				break;
				
			case 4:
				screen.ponto();
				switch(capture.opcaoMenu())	{
				case 1:
					Control.entrada();
				break;	
					
				case 2:
					Control.saida();
				break;
		
				}
				break;
			case 5:
				Control.adcTaxa();
				
				break;
			case 6:
				Control.sindicato();
				break;
			case 7:
				Control.resultadoDeVenda();
				break;
			case 8:
				Control.rodarFolhaDePagamento();
				break;
			default:
				break;
			}
			
		}while(loop);	
	}

}
