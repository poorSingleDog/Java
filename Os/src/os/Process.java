package os;

public class Process {
	private int pid;
	private int space;
	private int time;
	private int priority;
	private int status;			//后备状态
	private Process former=null;
	private Process latter=null;
	
	public Process(int a,int b,int c,int d,int e) {				//构造函数
		pid=a;
		space=b;
		time=c;
		priority=d;
		status=e;
	}
	
	public Process(int a,int b,int c,int d,int e,Process f,Process g) {
		pid=a;
		space=b;
		time=c;
		priority=d;
		status=e;
		former=f;
		latter=g;
	}
	
	public void set_status(int i) {
		status=i;
	}
	
	public void set_priority(int i) {
		priority=i;
	}
	
	public void set_time(int i) {
		time=i;
	}
	
	public int get_pid() {
		return pid;
	}
	public int get_space() {
		return space;
	}
	public int get_time() {
		return time;
	}
	public int get_priority() {
		return priority;
	}
	public int get_status() {
		return status;
	}
	public Process get_former() {
		return former;
	}
	public Process get_latter() {
		return latter;
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		Process p=new Process();
//		System.out.println(p.get_latter());					//输出为null
		
//		Process n=new Process(1,2,3,4,5);
//		Process p=new Process(1,2,3,4,5,null,n);
//		System.out.println(p.get_former());
//		System.out.println(p.get_latter().get_pid());
//		System.out.println(n);
		long totalMilliSeconds = System.currentTimeMillis();
		System.out.println(totalMilliSeconds);
		System.out.println(totalMilliSeconds);
		System.out.println(totalMilliSeconds);
		System.out.println(totalMilliSeconds);
		System.out.println(totalMilliSeconds);
	}

}
