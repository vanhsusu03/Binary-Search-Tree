public class TreeNode {
    public int value;
    public TreeNode leftNode = null;
    public TreeNode rightNode = null;

    public TreeNode(int value) {
        this.value = value;
    }
    public void setLeftNode (TreeNode left) {
        this.leftNode = left;
    }
    public void setRightNode (TreeNode right) {
        this.rightNode = right;
    }
    public TreeNode getLeftNode() {
        return this.leftNode;
    }
    public TreeNode getRighTreeNode() {
        return this.rightNode;
    }

}