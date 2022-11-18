import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;


public class BST {

    private static TreeNode root;

    public BST() {}

    public static void init() {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        n5.leftNode = n3;
        n5.rightNode = n7;
        n3.leftNode = n1;
        n3.rightNode = n4;
        n1.leftNode = n0;
        n1.rightNode = n2;
        n7.leftNode = n6;
        root = n5;
    }

    /**
     * THAO TAC VOI CAY
     * @param root
     */

    public static TreeNode insert(TreeNode root, int value) {
        if(root == null) {
            return new TreeNode(value);
        }
        TreeNode newRoot = root;
        while ( newRoot != null) {
            if(value > newRoot.value) {
                if(newRoot.rightNode == null) {
                    newRoot.rightNode = new TreeNode(value);
                    return root;
                }
                else {
                    newRoot = newRoot.rightNode;
                }
            } else if(value < newRoot.value) {
                if(newRoot.leftNode == null) {
                    newRoot.leftNode = new TreeNode(value);
                    return root;
                }
                else {
                    newRoot = newRoot.leftNode;
                }
            }
        }
        return null;
     }

     //XOA

     public static TreeNode findLeftNode(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode leftMostNode = root;
        while(leftMostNode.leftNode != null ) {
            leftMostNode = leftMostNode.leftNode;
        }
        return leftMostNode;
     }
     
     public static TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        //B1: di tim node xoa
        if(key < root.value) {
            root.leftNode = deleteNode(root.leftNode, key);
        } else if( key > root.value) {
            root.rightNode = deleteNode(root.rightNode, key);
        } else {
            //Xoa node 
            if( root.leftNode == null && root.rightNode == null) {
                return null;
            } 
            if (root.leftNode == null && root.rightNode != null) {
                return root.rightNode;
            }
            if (root.rightNode == null && root.leftNode != null) {
                return root.leftNode;
            } 
            TreeNode tmpNode = findLeftNode(root.rightNode);
            root.value = tmpNode.value;
            root.rightNode = deleteNode(root.rightNode, tmpNode.value);
        }
        return root;
     }
     


     //TINH TONG TU ROOT - LEAF, xem co tong nao bang targetSum khong
     public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        if(targetSum == root.value && root.leftNode == null && root.rightNode == null ) {
            return true;
        }
        return hasPathSum(root.leftNode,targetSum - root.value) || hasPathSum(root.rightNode,targetSum - root.value);
    }

    /**
     * DUYET CAY
     * @param root
     */

    //LEFT - NODE - RIGHT
    public static void inOrder(TreeNode root) {
        if( root == null) {
            return;
        }
        inOrder(root.leftNode);
        System.out.print(root.value + " ");
        inOrder(root.rightNode);
    }

    //Root - Left - Right
    //Recursive
    public static void preOrderRC(TreeNode root) {
        if( root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderRC(root.leftNode);
        preOrderRC(root.rightNode);
    }

    //PreOrder using Stack
    public static void preOrderSt(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while(!stack.isEmpty()) {
          TreeNode tmp = stack.peek(); 
          System.out.print(stack.pop().value + " ");
          if (tmp.rightNode != null) {
            stack.push(tmp.rightNode); 
          }
          if (tmp.leftNode != null) {
            stack.push(tmp.leftNode); 
          }
        }
      }

    //LEFT - RIGHT - ROOT
    public static void postOrder(TreeNode root) {
        if(root == null) {
            return;
        }
        postOrder(root.leftNode);
        postOrder(root.rightNode);
        System.out.print(root.value + " ");

    }
    //LEVEL ORDER TRAVERSAL
    //Root - Left - Right but in level order
    public static void levelOrder(TreeNode root) {
        if(root == null ) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.peek();
            System.out.print(queue.poll().value + " ");
            if(tmp.leftNode != null) {
                queue.add(tmp.leftNode);
            }
            if(tmp.rightNode != null) {
                queue.add(tmp.rightNode);
            }
        }
    }
    
    //SO NODE
    public static int getNumOfNode(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return getNumOfNode(root.leftNode) + getNumOfNode(root.rightNode) +1;
    }

    public static void getNodesOfSubBST(TreeNode root) {
        if(root == null) {
            return;
        }
        System.out.println("NODES: " + getNumOfNode(root));
        getNodesOfSubBST(root.leftNode);
        getNodesOfSubBST(root.rightNode);
    }

    //CHIA DE TRI
    public static long getFn(int n, int k) {
        if( k == 0) {
            return 1;
        } else return n*getFn(n,k-1);
    }


    public static void main(String[] args) {
        init();
        root = insert(root,8);
        root = deleteNode(root, 1);
        System.out.println("INORDER");
        inOrder(root);
        System.out.println();
        System.out.println("PREORDER");
        preOrderRC(root);
        System.out.println();
        System.out.println("POSTORDER");
        postOrder(root);
        System.out.println();
        System.out.println("LEVEL ORDER");
        levelOrder(root);
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println();
        getNodesOfSubBST(root);
        System.out.println(getFn(9,24));
    }
}
