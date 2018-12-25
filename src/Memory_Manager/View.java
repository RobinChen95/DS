package Memory_Manager;

import javax.swing.*;
import java.util.*;

public class View {

	ArrayList<Members> lists=new ArrayList<Members>();
	MemManager m;

	public void menu(){

		String step1 = JOptionPane.showInputDialog("输入空间大小:");
		if(isNumber(step1)){
			int size=Integer.parseInt(step1); //用size初始化存储池
			if(size>10){
				m = new MemManager(size);}
			else m = new MemManager(10);
			allocate();
		}
		else{JOptionPane.showMessageDialog(null,"请输入有效数字!"
				, "输入错误",JOptionPane.ERROR_MESSAGE);
			menu();
		}
	}

	public void allocate(){
		String step2 = JOptionPane.showInputDialog("1.分配变量\n2.删除变量\n3.搜索变量\n4.查看空间\n0.退出程序");
		if(isNumber1(step2)){
			int s2=Integer.parseInt(step2);
			if(s2==1){
				parameter_allocate(); //变量分配
			}else if(s2==2){
				String s = JOptionPane.showInputDialog("输入需要删除的变量的名称:");
				delete(s);  //删除名称为s的变量，未编写
			}else if(s2==3){
				String s = JOptionPane.showInputDialog("输入查找的变量的名称:");
				parameter_check(s);  //显示名称为s的变量
			}else if(s2==4){
				space_check(); //显示存储池状态
			}else if(s2==0){
				System.exit(0); //退出程序
			}
			allocate();
		}
		else{JOptionPane.showMessageDialog(null,"请输入0-4的数字！"
				, "输入错误",JOptionPane.ERROR_MESSAGE);
			allocate();
		}
	}

	public void parameter_allocate(){  //变量分配
		int method = -1;
		String name    = JOptionPane.showInputDialog("1.变量名称:");
		String space   = JOptionPane.showInputDialog("2.变量空间:");
		int s=Integer.parseInt(space);
		if(m.firstFit(s)==-1){JOptionPane.showMessageDialog(null,"存储池容量不足,请重新分配!"
				, "错误信息",JOptionPane.ERROR_MESSAGE);return;}
		if(isNumber1(space)&&(s<m.mempool.length));
		else{JOptionPane.showMessageDialog(null,"请输入一个有效数字!"
				, "输入错误",JOptionPane.ERROR_MESSAGE);return;}
		String content = JOptionPane.showInputDialog("3.变量内容:");
		int[] na = new int[content.length()];
		for(int i=0;i<content.length();i++){
			na[i]=(int)content.charAt(i);
		}
		if(s<content.length()){JOptionPane.showMessageDialog(null,"存储内容过大,请分配更大的空间!"
				, "错误信息",JOptionPane.ERROR_MESSAGE);
			parameter_allocate();}
		String info =
				"选择算法:\n" +"1.首次适应算法(默认)\n"+"2.最佳适应算法\n"+"3.最坏适应算法\n";
		char option=JOptionPane.showInputDialog(info).charAt(0);
		switch(option){
			case '1':
				method = 1; //首次适应
				break;
			case '2':
				method = 2;  //最佳适应
				break;
			case '3':
				method = 3;  //最坏适应
				break;
			default :
				method = 1;  //默认首次适应
		}
		lists.add(new Members(m.request(na,s,method),name,s));
	}

	public int all(){  //返回变量总占用空间
		int total=4;
		for(Members temp:lists){
			total+=(temp.getLength()+4);
		}
		return total;
	}

	public void space_check(){  //显示存储池
		int n=0;
		if(all()==4)JOptionPane.showMessageDialog(null,"\n尚未储存任何变量!\n存储池大小:"+m.mempool.length
				,"存储池信息:",JOptionPane.PLAIN_MESSAGE);
		else JOptionPane.showMessageDialog(null,"存储池剩余容量:"+(m.mempool.length-all())
				,"存储池信息:",JOptionPane.PLAIN_MESSAGE);
		for(Members temp:lists){
			int[] abc =m.getVaule(temp.getMem());
			char ab[] = new char[abc.length];
			for(int i=0;i<abc.length;i++){
				ab[i]=(char)abc[i];
			}
			String str = String.valueOf(ab);
			JOptionPane.showMessageDialog(null,"名称为:\""+temp.getName()+"\",\n"+"长度为:"+temp.getLength()+"\n值为:\""+str+"\",\n"+"开始于@存储池第"+(temp.getMem().getPos()+1)+"位"
					,"第"+(n+1)+"个数据的信息"+"(共"+lists.size()+"个)",JOptionPane.PLAIN_MESSAGE);
			n++;
		}
	}

	public void parameter_check(String s){  //检查变量
		boolean ok = true;
		for(Members tem:lists){
			if(tem.getName().equals(s)){
				ok =false;
				int[] abc =m.getVaule(tem.getMem());
				char ab[] = new char[abc.length];
				for(int i=0;i<abc.length;i++){
					ab[i]=(char)abc[i];
				}
				String str = String.valueOf(ab);
				JOptionPane.showMessageDialog(null,"名称为:\""+tem.getName()+"\",\n"+"长度为:"+tem.getLength()+"\n值为:\""+str+"\",\n"+"开始于@存储池第"+(tem.getMem().getPos()+1)+"位"
						,s+"的查询结果",JOptionPane.PLAIN_MESSAGE);
			}
		}
		if(ok)JOptionPane.showMessageDialog(null,"存储池中无此变量!"
				, s+"的查询结果:",JOptionPane.ERROR_MESSAGE);
	}

	public boolean isNumber(String str){  //检查输入
		if((str.equals(""))||(str.charAt(0)=='0'))return false;
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	public boolean isNumber1(String str){  //检查输入
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	public void delete(String s){
		boolean ok = true;
		for(int i=0;i<lists.size();i++){
			if(lists.get(i).getName().equals(s)){
				ok =false;
				m.delete(lists.get(i).getMem(),lists.get(i).getLength());
				lists.remove(i);
				JOptionPane.showMessageDialog(null,"删除成功!"
						, "结果",JOptionPane.PLAIN_MESSAGE);
			}
		}
		if(ok)JOptionPane.showMessageDialog(null,"存储池中无此变量!"
				, "结果",JOptionPane.ERROR_MESSAGE);
	}

}