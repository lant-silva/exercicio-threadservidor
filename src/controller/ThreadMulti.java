package controller;

import java.util.concurrent.Semaphore;

public class ThreadMulti extends Thread {

	static Semaphore transacao;
	int id;
	int idType;
	double idnew;
	
	public ThreadMulti(int id, Semaphore transacao){
		
		this.id = id;
		ThreadMulti.transacao = transacao;
		
	}
	
	@Override
	public void run() {
		idnew = id;
		idType = getType(idnew);
		operacao();
		
	}
	
	public int getType(double idcalc) {
		if(idcalc % 3 == 1) {
			idnew = 1;
		}else {
			if(idcalc % 3 == 2) {
				idnew = 2;
			}else {
				if(idcalc % 3 == 0){
					idnew = 3;
				}
			}
		}
		return (int) idnew;
	}
	
	public void operacao() {
		switch(idType) {
		
		case 1:processar(0.2, 1.0, 1);
			break;
		case 2:processar(0.5, 1.5, 1.5);
			break;
		case 3:processar(1, 2, 1.5);
			break;
		default: System.err.println("Erro");;
			break;
		}
	}
	
	public void processar(double min, double max, double trans) {
		if(idType==1) {
			for(int times=1;times<=2;times++) {
				System.out.println("Processo "+id+": Iniciando Calculo");
				int time = (int) ((Math.random()*max)+min);
				try {
					sleep(time*1000);
				} catch (InterruptedException e) {
					
				}
				System.out.println("Processo "+id+": Calculo Finalizado");
				try {
					transacao.acquire();
					System.out.println("Processo "+id+": Iniciando Transacao");
					sleep((long) trans*1000);
				} catch (Exception e) {
					
				} finally {
					System.out.println("Processo "+id+": Transacao Finalizada");
					transacao.release();
				}
			}
		}else {
			for(int times=1;times<=3;times++) {
				System.out.println("Processo "+id+": Iniciando Calculo");
				int time = (int) ((Math.random()*max)+min);
				try {
					sleep(time*1000);
				} catch (InterruptedException e) {
					
				}
				System.out.println("Processo "+id+": Calculo Finalizado");
				try {
					transacao.acquire();
					System.out.println("Processo "+id+": Iniciando Transacao");
					sleep((long) trans*1000);
				} catch (Exception e) {
					
				} finally {
					System.out.println("Processo "+id+": Transacao Finalizada");
					transacao.release();
				}
			}
		}
	}
}