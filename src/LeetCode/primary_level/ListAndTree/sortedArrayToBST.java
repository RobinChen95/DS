package LeetCode.primary_level.ListAndTree;

public class sortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length==0) return null;

        return sortedArrayToBST(nums,0,nums.length-1);
    }

    public TreeNode sortedArrayToBST(int[] nums,int start,int end){
        if (start>end) return null;
        int mid = (start+end)/2;
        TreeNode temp = new TreeNode(nums[mid]);
        temp.left = sortedArrayToBST(nums,start,mid-1);
        temp.right = sortedArrayToBST(nums,mid+1,end);
        return temp;
    }
}
