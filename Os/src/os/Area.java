package os;

public class Area {

	private int start=-1;						//��ַ
	private int length=0;						//���򳤶�
	private boolean status=false;					//����״̬,0��δ��
	private int p_id=0;
	
	public  Area(int a,int b,boolean c) {				//���캯��
		start=a;
		length=b;
		status=c;
	}
	
	public int get_start() {
		return start;
	}
	public int get_length() {
		return length;
	}
	public boolean get_status() {
		return status;
	}
	public int get_p_id() {
		return p_id;
	}
	
	public void set_start(int i) {
		start=i;
	}
	
	public void set_length(int i) {
		length=i;
	}
	
	public void set_status(boolean i) {
		status=i;
	}
	
	public void set_p_id(int i) {
		p_id=i;
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		for(int i=0;;i++) {
			try {
				Thread.sleep(2*1000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			System.out.print(i);
		}
	}

}
