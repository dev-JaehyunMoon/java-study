package threadTest;

public class ThreadTest {
	public static void main(String[] args) {
//		Runnable thread = new Thread2();
		Runnable target = () -> {
			for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName());
			try {Thread.sleep(500);} catch (InterruptedException e) {;}}
		};
		Thread t1 = new Thread(target , "?");
		Thread t2 = new Thread(target, "!");
		
		t1.start();
		try {t1.join();} catch (InterruptedException e) {;}		
		t2.start();
}
		
//		Thread t1 = new Thread1("¢¾");
//		Thread t2 = new Thread1("¡Ú");
//		
//		t1.start();
//		t2.start();
		
//		t1.run();
//		t2.run();
		
	}


