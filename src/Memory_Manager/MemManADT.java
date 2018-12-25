package Memory_Manager;

public interface MemManADT {
   public MemHandle request(int[] info, int length, int method);
   public int[] getVaule(MemHandle h);
   public void delete(MemHandle h, int l);
}
