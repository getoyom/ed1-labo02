package ed.lab;

public class TreeNode <T> {
    protected T value;
    protected TreeNode<T> left;
    protected TreeNode<T> right;
    protected int height;

    public TreeNode(T value) {
        this.value = value;
        this.height = 0;
    }

    @Override
    public String toString() {
        return String.format("(%s) [left: %s] [right: %s]",
                value,
                left != null ? left.value : "null",
                right != null ? right.value : "null");
    }
}
