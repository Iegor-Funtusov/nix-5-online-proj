package ua.com.nkrasnovoronka.tasks.level2.task2;

public class TreeNode{
    private static final int COUNT =  5;
    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode createTreeFromArray(int[] array, TreeNode root, int counter){
        if (counter < array.length) {
            root = new TreeNode(array[counter]);
            root.left = createTreeFromArray(array, root.left, 2 * counter + 1);
            root.right = createTreeFromArray(array, root.right, 2 * counter + 2);
        }
        return root;
    }

    public static void printTree(TreeNode root, int space){
        if (root == null){
            return;
        }

        space += COUNT;

        printTree(root.right, space);
        System.out.println();

        for (int i = COUNT; i < space; i++){
            System.out.print(" ");
        }

        System.out.print(root.val + "\n");

        printTree(root.left, space);
    }
    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}


