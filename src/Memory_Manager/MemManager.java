package Memory_Manager;


public class MemManager implements MemManADT {
	
	private static final int STARTTAG     = 0;
	private static final int FULLSIZE     = 1;
	private static final int USERSIZE     = 2;
	private static final int LPTR         = 2;
	private static final int RPTR         = 3;
	private static final int DATAPOS      = 3;
	private static final int FREE         =-1;
	private static final int RESERVED     =-2;
	private static final int ENDSIZE      = 4;
	private static final int FREEENDTAG   = 5;
	private static final int RESENDTAG    = 3;
	private static final int MINEXTRA     =-2;
	private static final int FREEOVERHEAD = 6;
	private static final int RESOVERHEAD  = 4;
	
	int[] mempool;
	MemHandle freelist;
	
	MemManager(int size){
		mempool=new int[size];
		freelist = new MemHandle(0);
        mempool = new int[size];
        freelist = new MemHandle(0);
        mempool[STARTTAG] = mempool[size-1] = FREE;
        mempool[FULLSIZE] = mempool[size-2] = size-FREEOVERHEAD;
        mempool[LPTR] =mempool[RPTR]=0;
	}

	@Override
	public MemHandle request(int[] info,int size,int method) {
		int datasize=size;int start=-1;
		switch(method){
		case '1':{start = firstFit(datasize);break;}
		case '2':{start = bestFit(datasize);break;}
		case '3':{start = worstFit(datasize);break;}
		default :{start = firstFit(datasize);break;}  //Ĭ���״���Ӧ
		}
		if(start==-1) return null;
		if(mempool[start+FULLSIZE]>(datasize+RESOVERHEAD)){
			int oldsize = mempool[start+FULLSIZE]-datasize-RESOVERHEAD;
			mempool[start+oldsize+FREEENDTAG]=FREE;
			mempool[start+oldsize+ENDSIZE]=oldsize;
			mempool[start+FULLSIZE]=oldsize;
			int newstart =start+mempool[start+FULLSIZE]+FREEOVERHEAD;
			mempool[newstart+STARTTAG]=RESERVED;
			mempool[newstart+FULLSIZE]=datasize;
			mempool[newstart+USERSIZE]=info.length;
			mempool[newstart+datasize+RESENDTAG]=RESERVED;
			for(int i=0;i<info.length;i++)
				mempool[newstart+DATAPOS+i]= info[i];
			return new MemHandle(newstart);
		}
		else{
			if(mempool[start+RPTR]==start)
				freelist=null;
			else{
				mempool[mempool[start+RPTR]+LPTR]=mempool[start+LPTR];
				mempool[mempool[start+LPTR]+RPTR]=mempool[start+RPTR];
			}
			mempool[start+STARTTAG]=RESERVED;
			mempool[start+FULLSIZE]+=FREEOVERHEAD-RESOVERHEAD;
			mempool[start+USERSIZE]=info.length;
			for(int i=0;i<info.length;i++)
				mempool[start+DATAPOS+i]=info[i];
			mempool[start+mempool[start+FULLSIZE]+RESENDTAG]=RESERVED;
			return new MemHandle(start);
		}
	}

	@Override
	public int[] getVaule(MemHandle h) {
        int startpos = h.getPos();
        int length = mempool[startpos+ USERSIZE];
        int startdata = startpos+DATAPOS;
        int[] stuff= new int[length];
        for(int i=0; i<length;i++){
        	stuff[i]=mempool[startdata+i];
        }
		return stuff;
	}
	
	protected int firstFit(int length){
		if(freelist == null)return -1;
		int freestart = freelist.getPos();
		for(int curr=freestart;;)
			if(mempool[curr+FULLSIZE]>=(length+MINEXTRA))
				return curr;
			else{
				curr = mempool[curr+RPTR];
			if(curr==freestart)return -1;
			}
	}

	protected int bestFit(int length){
		int min=firstFit(length);
		if((freelist == null)|| (min==-1))return -1;
		int freestart = freelist.getPos();
		for(int curr=freestart;curr<mempool.length;curr = mempool[curr+RPTR])
			if((mempool[curr+FULLSIZE]>=(length+MINEXTRA))&&(mempool[curr+FULLSIZE]<min)){
				min=mempool[curr+FULLSIZE];
			}
		return min;
		}
	
	protected int worstFit(int length){
		int max=firstFit(length);
		if((freelist == null)|| (max==-1))return -1;
		int freestart = freelist.getPos();
		for(int curr=freestart;curr<mempool.length;curr = mempool[curr+RPTR])
			if((mempool[curr+FULLSIZE]>=(length+MINEXTRA))&&(mempool[curr+FULLSIZE]>max)){
				max=mempool[curr+FULLSIZE];
			}
		return max;
		}
	
	@Override
	public void delete(MemHandle h,int length) {
		int start=h.getPos(),left_free=-3,right_free=-3,left_count=0,right_count=0;
		for(int i=start;i>=0;i--){
			if(mempool[i]==FREE){left_count++;}
		    if(left_count==2){left_free=i;break;}
		}
		for(int i=start;i<mempool.length;i++){
			if(mempool[i]==FREE){right_count++;}
			if(right_count==2){right_free=i;break;}
		}
		if((left_free==-3)&&(right_free==-3)){
	          mempool[STARTTAG] = mempool[mempool.length-1] = FREE;
	        mempool[FULLSIZE] = mempool[mempool.length-2] = mempool.length-FREEOVERHEAD;
	        mempool[LPTR] =mempool[RPTR]=0;
		}
		else if((left_free==-3)&&(right_free!=-3)){
			mempool[LPTR]=0;
			mempool[RPTR]=right_free;
			mempool[FULLSIZE]=length-2;
			mempool[length+2]=length-2;
		}
		else if((left_free!=-3)&&(right_free==-3)){
			mempool[start+LPTR]=left_free;
			mempool[start+RPTR]=0;
			mempool[start+FULLSIZE]=length-2;
			mempool[start+length+2]=length-2;
		}
		else{
			mempool[start+LPTR]=left_free;
			mempool[start+RPTR]=right_free;
			mempool[start+FULLSIZE]=length-2;
			mempool[start+length+2]=length-2;
	    }
		mempool[start]=mempool[start+length+3]=FREE;
		if(mempool[start-1]==FREE){
			mempool[left_free+FULLSIZE]=length-2+mempool[left_free+FULLSIZE]+6;
			mempool[start+length+2]=mempool[left_free+FULLSIZE];
			mempool[left_free+RPTR]=mempool[start+RPTR];
		}
		if((start+length+4)<mempool.length){
		if(mempool[start+length+4]==FREE){
			mempool[right_free+FULLSIZE]=length-2+mempool[right_free+FULLSIZE]+6;
			mempool[right_free+mempool[FULLSIZE]+4]=mempool[right_free+FULLSIZE];
			mempool[start+RPTR]=mempool[right_free+RPTR];
		}
		else return;
	  }
	}
}
