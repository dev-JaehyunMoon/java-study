package interfaceTest;

public class Cat implements Pet{

	@Override
	public void giveYourHand() {
		System.out.println("����ô�Ѵ�");
	}

	@Override
	public void bang() {
		System.out.println("������");
	}

	@Override
	public void sitDown() {
		System.out.println("����Ѵ�");
		
	}

	@Override
	public void waitNow() {
		System.out.println("�Ⱦ��Ѵ�");
	}

	@Override
	public void getNose() {
		System.out.println("�Ͼ����Ѵ�");
	}

}
