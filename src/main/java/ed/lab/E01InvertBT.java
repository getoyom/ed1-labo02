package ed.lab;

public class E01InvertBT {
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null) {return null;} //Return null
        TreeNode left = invertTree(root.left); //Transverse left node
        TreeNode right = invertTree(root.right); //Transverse right node
        root.right = left; //Switch right node with left node
        root.left = right; //Switch left node with right node
        return root;
    }
}
