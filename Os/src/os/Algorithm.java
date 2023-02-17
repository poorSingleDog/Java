package os;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
	public static final int memory = 64; 
	public static final int os = 4;  
    public static final int cut1=14;
    public static final int cut2=34;
    public static final int piece=1;			//ʱ��Ƭ
    public ArrayList<Process>pcb;				//������Ϣ
    public ArrayList<Area>area_table;			//������	
    public ArrayList<Process>prepare;				//�󱸶���
    public ArrayList<Process>ready;			//��������
    public ArrayList<Process>susque;		//�������
    public Process excu;					//����ִ�еĽ���
    public ArrayList<Process>finish;			//��ɶ���
    
    public Algorithm() {				//���캯��
    	pcb=new ArrayList<Process>();
    	area_table=new ArrayList<Area>();
    	prepare=new ArrayList<Process>();
    	ready=new ArrayList<Process>();
    	excu=new Process(0,0,0,-99,0);
    	finish=new ArrayList<Process>();
    	susque=new ArrayList<Process>();
    	
    	pcb.add(new Process(1,2,3,10,1));
    	pcb.add(new Process(2,4,3,11,1));
    	pcb.add(new Process(3,8,3,12,1));
    	pcb.add(new Process(4,4,3,13,1));
    	pcb.add(new Process(5,13,3,14,1));
    	pcb.add(new Process(6,30,3,15,1));
    	prepare.add(pcb.get(0));
    	prepare.add(pcb.get(1));
    	prepare.add(pcb.get(2));
    	prepare.add(pcb.get(3));
    	prepare.add(pcb.get(4));
    	prepare.add(pcb.get(5));
    	
    	area_table.add(new Area(0,os,true));
    	area_table.get(0).set_p_id(-1);
    	area_table.add(new Area(os,cut1-os,false));
    	area_table.add(new Area(cut1,cut2-cut1,false));
    	area_table.add(new Area(cut2,memory-cut2,false));
    	
    }
    
    public static Algorithm enter_memory(Algorithm w) {			//�ж��ڴ����򣬷�������ڴ�
    	int len=w.prepare.size();
    	for(int n=0;n<len;n++) {  		    	
    	int s=w.prepare.get(n).get_space();			//�󱸶���,���׽��̵Ĵ�С
    	for(int i=0;i<w.area_table.size();i++) {	//���ҿ�����
    		if(w.area_table.get(i).get_status()==false) {
    			if(w.area_table.get(i).get_length()>s) {		//�����ɺ�״̬תΪ����״̬�����������룬�󱸶��г����ڴ������
    				w.prepare.get(n).set_status(2);
    				w.ready.add(w.prepare.get(n));
    				w.area_table.get(i).set_p_id(w.prepare.get(n).get_pid());		//��pid��area
    				w.prepare.remove(n);
    				n--;
    				len--;
    				w.area_table.add(i+1,new Area(w.area_table.get(i).get_start()+s,w.area_table.get(i).get_length()-s,false));
    				
    				w.area_table.get(i).set_length(s);
    				w.area_table.get(i).set_status(true);
    				
    				break;			
    			}
    			else if(w.area_table.get(i).get_length()==s) {
    				w.prepare.get(n).set_status(2);
    				w.ready.add(w.prepare.get(n));
    				w.area_table.get(i).set_p_id(w.prepare.get(n).get_pid());
    				w.prepare.remove(n);
    				n--;
    				len--;
    				w.area_table.get(i).set_status(true);
    				break;
    			}
    		}
    	}
    	}
    	return w;
    }
    
    public static Algorithm combine(Algorithm w) {			//�ϲ���������
    	for(int i=0;i<w.area_table.size()-1;i++) {
    		if(w.area_table.get(i).get_status()==false&&w.area_table.get(i+1).get_status()==false) {
    			if(w.area_table.get(i+1).get_start()==cut1||w.area_table.get(i+1).get_start()==cut2) {
    				continue;
    			}
    			else {
    				w.area_table.get(i).set_length(w.area_table.get(i).get_length()+w.area_table.get(i+1).get_length());
    				w.area_table.remove(i+1);
    				i--;
    			}
    		}
    	}
    	return w;
    }
    
    public static Algorithm excute(Algorithm w) {		//�Ӿ�������ѡȡ���ȼ���ߵ�ִ��1��,״̬��Ϊִ�У��Ӿ�������ȡ��
    	if(w.ready.size()>=1) {
    		int t=0;
    		if(w.excu.get_priority()==-99) {			//��ִ���г���
    			for(int i=0;i<w.ready.size();i++) {
    				if(w.ready.get(i).get_priority()>t)
    					t=w.ready.get(i).get_priority();
    					
    				}
    			for(int i=0;i<w.ready.size();i++) {
    				if(w.ready.get(i).get_priority()==t) {
    					w.excu=w.ready.get(i);
    					w.ready.remove(i);
    					w.excu.set_status(3);
    					break;
    				}
    			}
    		}
    		else {
    			for(int i=0;i<w.ready.size();i++) {			//��ִ���г����ж����ȼ��Ƿ���ߣ������滻
    				if(w.ready.get(i).get_priority()>w.excu.get_priority()) {
    					w.excu.set_status(2);
    					w.ready.add(i,w.excu);
    					w.excu=w.ready.get(i+1);
    					w.ready.remove(i+1);
    					w.excu.set_status(3);
    				
    				}
    			}
    		
    		}	
    	}
    	return w;
    }
    
    public static Algorithm exover(Algorithm w) {		//ִ��һ��ļ�����ж��Ƿ�ִ����
    	if(w.excu.get_priority()==-99)
    		return w;
    	w.excu.set_priority(w.excu.get_priority()-1);	//���ȼ���ʱ�䶼��һ
    	w.excu.set_time(w.excu.get_time()-1);

    	if(w.excu.get_time()==0) {
    		for(int i=0;i<w.area_table.size();i++) {
    			if(w.area_table.get(i).get_p_id()==w.excu.get_pid()) {
    				w.area_table.get(i).set_status(false);
    				w.area_table.get(i).set_p_id(0);
    				break;
    			}
    				
    		}
    		w.excu.set_priority(-99);
    		w.excu.set_status(5);
    		w.finish.add(w.excu);
    		
    	}
    	return w;
    }
    
    public static Algorithm suspend(Algorithm w,int id) {			//����ָ��id�Ľ���
    	for(int i=0;i<w.ready.size();i++) {
    		if(w.ready.get(i).get_pid()==id) {
    			w.ready.get(i).set_status(4);
    			w.susque.add(w.ready.get(i));
    			for(int j=0;j<w.area_table.size();j++) {
        			if(w.area_table.get(j).get_p_id()==w.ready.get(i).get_pid()) {
        				w.area_table.get(j).set_status(false);
        				w.area_table.get(j).set_p_id(0);
        				break;
        			}
        				
        		}
    			w.ready.remove(i);
    			break;
    		}
    	}
    	return w;
    }
    
    public static Algorithm unsuspend(Algorithm w,int id) {			//���ָ��id�Ľ���,���ڴ治����ԭ�ⲻ��
    	for(int n=0;n<w.susque.size();n++) {  
    		if(w.susque.get(n).get_pid()==id) {
    			int s=w.susque.get(n).get_space();			//���̵Ĵ�С
    			for(int i=0;i<w.area_table.size();i++) {	//���ҿ�����
    				if(w.area_table.get(i).get_status()==false) {
    					if(w.area_table.get(i).get_length()>s) {		//�����ɺ�״̬תΪ����״̬�����������룬�󱸶��г����ڴ������
    						w.susque.get(n).set_status(2);
    						w.ready.add(w.susque.get(n));
    						w.area_table.get(i).set_p_id(w.susque.get(n).get_pid());		//��pid��area
    						w.susque.remove(n);
    	
    						w.area_table.add(i+1,new Area(w.area_table.get(i).get_start()+s,w.area_table.get(i).get_length()-s,false));
    				
    						w.area_table.get(i).set_length(s);
    						w.area_table.get(i).set_status(true);
    				
    						break;		
    					}
    					else if(w.area_table.get(i).get_length()==s) {
    						w.susque.get(n).set_status(2);
    						w.ready.add(w.susque.get(n));
    						w.area_table.get(i).set_p_id(w.susque.get(n).get_pid());
    						w.susque.remove(n);
    			
    						w.area_table.get(i).set_status(true);
    						break;
    					}
    				}
    			}
    		}
    		}
    		return w;
    }
    
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Algorithm a=new Algorithm();
//		a.excu=a.prepare.get(0);		//����new��ͬһ��������ֵ�ĸı䶼�Ǵ��ݵģ�remove����ʹ������ʧ
//		a.excu.set_priority(-1);
//		a.prepare.remove(0);
//		System.out.println(a.prepare.get(0).get_space());
		a=enter_memory(a);
		a=excute(a);			//1s
		a=exover(a);
		a=excute(a);			//2s
		a=exover(a);
		a=excute(a);			//3s
		a=exover(a);			
		a=excute(a);			//4s
		a=exover(a);
		a=excute(a);			//5s
		a=exover(a);
		a=excute(a);			//6s
		a=exover(a);
		a=excute(a);			//7s
		a=exover(a);
		a=excute(a);			//8s
		a=exover(a);
		
		a=combine(a);
		a=enter_memory(a);
		a=suspend(a,6);
		a.prepare.add(new Process(7,20,3,16,1));
		a=enter_memory(a);
		a=unsuspend(a,6);				//����idΪ7�Ľ�����������У����6����Ч
		for(int i=0;i<a.area_table.size();i++) {
			System.out.println(a.area_table.get(i).get_start()+"   "+a.area_table.get(i).get_length()+"   "+a.area_table.get(i).get_status());
		}
		System.out.println();
		for(int i=0;i<a.pcb.size();i++) {
			System.out.println(a.pcb.get(i).get_pid()+"   "+a.pcb.get(i).get_space()+"   "+a.pcb.get(i).get_time()+"   "+a.pcb.get(i).get_priority()+"   "+a.pcb.get(i).get_status());
		}
		System.out.println();
		for(int i=0;i<a.finish.size();i++) {
			System.out.println(a.finish.get(i).get_pid()+"   "+a.finish.get(i).get_space()+"   "+a.finish.get(i).get_time()+"   "+a.finish.get(i).get_priority()+"   "+a.finish.get(i).get_status());
		}
		System.out.println();
//		for(int i=0;i<a.susque.size();i++) {
//			System.out.println(a.susque.get(i).get_pid()+"   "+a.susque.get(i).get_space()+"   "+a.susque.get(i).get_time()+"   "+a.susque.get(i).get_priority()+"   "+a.susque.get(i).get_status());
//		}
//		
//		a.pcb.get(0).set_time(3);
		
//		a.prepare.remove(1);
//		System.out.println(a.area_table.size());
//		System.out.println(a.prepare.size());
//		System.out.println(a.prepare.get(0).get_time());
//		System.out.println(a.prepare.poll().get_pid());
//		System.out.println(a.prepare.get(0).get_pid());
	}

}
