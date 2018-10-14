package AlgorithmCoursePKU;

public class singlePeak {
    public int search(int[] L, int i, int j){
        if (j-i<=3)return findMax(L,i,j);
        else {
            int mid = (i+j)/2;
            if (L[mid]>L[mid-1]&L[mid]>L[mid+1]) return L[mid];
            else if (L[mid+1]>L[mid]&L[mid]>L[mid-1]){
                return search(L,mid,j);
            }
            else return search(L,i,mid);
        }
    }

    public int findMax(int[] L, int i, int j){
        if (L.length==1)return L[i];
        if (L.length==2)return Math.max(L[i],L[j]);
        return Math.max(Math.max(L[i],L[i+1]),Math.max(L[i+1],L[j]));
    }

    public static void main(String[] args){
        int[] a = {1,2,5,6,11,18,115,0};
        singlePeak sp = new singlePeak();
        System.out.println(sp.search(a,0,a.length-1));
    }
}
