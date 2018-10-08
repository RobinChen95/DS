package LeetCode.primary_level.ListAndTree;

public class isValidBST {
    /*这是错的，因为会导致右子树中会有节点大于根结点
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left!=null&&root.val<=root.left.val) return false;
        if (root.right!=null&&root.val>=root.right.val) return false;
        return isValidBST(root.left)&&isValidBST(root.right);
    }*/

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean valid(TreeNode root, long low, long high) {
        if (root == null) return true;
        if (root.val <= low || root.val >= high) return false;
        return valid(root.left, low, root.val) && valid(root.right, root.val, high);
    }
}

