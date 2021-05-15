package ua.com.nkrasnovoronka.level2.task2;

import java.util.LinkedList;
import java.util.Queue;

public class NodeSize {
    private static int COUNTER = 2;

    public static int getTreeNodeSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        TreeNode tmp;
        int deep = 0;
        while (!treeNodes.isEmpty()) {
            int size = treeNodes.size();
            while (size-- > 0) {
                tmp = treeNodes.poll();
                if (tmp.getLeft() != null) {
                    treeNodes.add(tmp.getLeft());
                }
                if (tmp.getRight() != null) {
                    treeNodes.add(tmp.getRight());
                }
            }
            deep++;
        }
        return deep;
    }

    public static void printTreeNodeSize(TreeNode root, int space){
        if(root == null){
            return;
        }
        space += COUNTER;

        printTreeNodeSize(root.getRight(), space);
        System.out.println();
        for (int i = COUNTER; i < space ; i--) {
            System.out.print(" ");
        }
        System.out.println(root.getVal());
        printTreeNodeSize(root.getLeft(), space);

    }
}
