package LeetCode.primary_level.ListAndTree;

import java.util.*;

public class levelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new  ArrayList<List<Integer>>();//注意，List是一个虚拟类不能new，Queue同理
        if (root == null) return res;
        int width = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> t = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            t.add(temp.val);
            width--;
            if (temp.left!=null){queue.offer(temp.left);}
            if (temp.right!=null){queue.offer(temp.right);}
            if (width==0){
                res.add(t);
                t= new ArrayList<Integer>();
                width = queue.size();
            }
        }
        return res;
    }
}
