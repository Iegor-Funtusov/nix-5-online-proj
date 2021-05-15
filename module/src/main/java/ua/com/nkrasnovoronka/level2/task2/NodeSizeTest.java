package ua.com.nkrasnovoronka.level2.task2;

import java.util.Random;
import java.util.TreeMap;

public class NodeSizeTest {
    private static final Random random = new Random();

    public static void randomBinaryTreeTest(){
        System.out.println("Starting random binary tree test");
        int[] array = new int[random.nextInt(10) + 1];
        System.out.println("Initialized array with length " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10) + 1;
        }
        TreeNode root = new TreeNode();
        root = TreeNode.createTreeFromArray(array, root, 0);
        System.out.println(root);
        NodeSize.printTreeNodeSize(root, 0);
    }
}
