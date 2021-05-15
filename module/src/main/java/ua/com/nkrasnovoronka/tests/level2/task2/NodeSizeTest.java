package ua.com.nkrasnovoronka.tests.level2.task2;

import ua.com.nkrasnovoronka.tasks.level2.task2.NodeSize;
import ua.com.nkrasnovoronka.tasks.level2.task2.TreeNode;
import ua.com.nkrasnovoronka.tests.Test;
import ua.com.nkrasnovoronka.util.UserInput;

import java.util.List;
import java.util.Random;

public class NodeSizeTest implements Test {
    private static final Random random = new Random();

    public void randomTest() {
        System.out.println("Starting random binary tree test");
        int[] array = new int[random.nextInt(10) + 1];
        System.out.println("Initialized array with length " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10) + 1;
        }
        TreeNode root = new TreeNode();
        root = TreeNode.createTreeFromArray(array, root, 0);
        TreeNode.printTree(root, 0);
        System.out.println("\nTree deep is " + NodeSize.getTreeNodeSize(root));
    }

    public void userTest() {
        System.out.println("Starting user input binary tree test");
        System.out.println("enter tree nodes in array as [1, 2, 3] 1 -> parent, 2 -> leftChild, 3 -> rightChild");
        List<Integer> integers = UserInput.userInputNumbers();
        int[] array = integers.stream().mapToInt(value -> value).toArray();
        TreeNode root = new TreeNode();
        root = TreeNode.createTreeFromArray(array, root, 0);
        TreeNode.printTree(root, 0);
        System.out.println("\nTree deep is " + NodeSize.getTreeNodeSize(root));
    }
}
