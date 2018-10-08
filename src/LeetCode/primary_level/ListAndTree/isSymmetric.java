package LeetCode.primary_level.ListAndTree;

public class isSymmetric {
    public boolean isSymmetric(TreeNode root) {
        TreeNode p = root;
        TreeNode q = root;
        return isOk(p,q);
    }

    public boolean isOk(TreeNode p,TreeNode q){
        if (p==null||q==null)return p==q;
        return (p.val==q.val)&&(isOk(p.left,q.right))&&(isOk(p.right,q.left));
    }
}
