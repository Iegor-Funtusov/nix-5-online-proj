/*
Ввод: 1 2 3 0 0 4 0 0 5 6 0 0 7 0 0
                          1
                        /   \
                      2       5
                    / \      / \
                   3   4    6   7

Симметричный обход: 3 2 4 1 6 5 7


Ввод: 1 2 3 4 0 0 0 0 5 0 0
                          1
                        /   \
                      2       5
                     /
                   3
                  /
                 4
Симметричный обход: 4 3 2 1 5


Ввод: 1 0 2 0 3 4 0 0 5 0 0
                         1
                           \
                            2
                              \
                               3
                              / \
                             4   5
Симметричный обход: 1 2 4 3 5
 */

package Level2;

import java.util.Scanner;

public class BinaryTree {
    private int info;
    private BinaryTree left;
    private BinaryTree right;


    public void formTree(){
        Scanner scanner = new Scanner(System.in);
        int info;
        System.out.println("Enter element of a tree");
        info = scanner.nextInt();
        if (info == 0)
            return;
        this.info = info;
        this.left = new BinaryTree();
        this.left.formTree();
        this.right = new BinaryTree();
        this.right.formTree();
    }


    public void displaySymmetric(){
        if(this.left != null && this.right != null){
            left.displaySymmetric();
            System.out.print(this.info + " ");
            right.displaySymmetric();
        }
    }


    public int maxDepth(){
        if(this.left == null && this.right == null){
            return 0;
        }
        else{
            int leftMax = this.left.maxDepth() + 1;
            int rightMax = this.right.maxDepth() + 1;
            return Math.max(leftMax, rightMax);
        }
    }

}
