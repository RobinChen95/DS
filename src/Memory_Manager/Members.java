package Memory_Manager;

public class Members {
  
  private MemHandle MH;
  private String name;
  private int length;
  
  public Members(MemHandle a,String n,int l){
	  MH=a;name=n;length=l;
  }
  
  public MemHandle getMem(){
	  return MH;
  }
  
  public String getName(){
	  return name;
  }
  
  public int getLength(){
	  return length;
  }
}
