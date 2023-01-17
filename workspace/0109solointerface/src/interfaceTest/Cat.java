package interfaceTest;

public class Cat implements Pet{

	@Override
	public void giveYourHand() {
		System.out.println("못본척한다");
	}

	@Override
	public void bang() {
		System.out.println("할퀸다");
	}

	@Override
	public void sitDown() {
		System.out.println("까라한다");
		
	}

	@Override
	public void waitNow() {
		System.out.println("싫어한다");
	}

	@Override
	public void getNose() {
		System.out.println("하악질한다");
	}

}
