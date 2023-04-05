package view;
import java.util.concurrent.Semaphore;
import controller.ThreadMulti;

public class Main {

	static Semaphore transacao;
	public static void main(String[] args) {
		transacao = new Semaphore(1);
		
		for(int i=1;i<=21;i++) {
			Thread idCalc = new ThreadMulti(i, transacao);
			idCalc.start();
		}
	}
}
