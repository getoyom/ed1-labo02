package ed.lab;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class E03AVLTree<T> {
    private final Comparator<T> comparator;
    private TreeNode<T> root;

    public E03AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private TreeNode<T> insert(TreeNode<T> root, T value) {
        /*root null equal to new root*/
        if (root == null) {
            root = new TreeNode<>(value);
        }
        /*If value is min than root, value is going to be inserted at left */
        if (comparator.compare(value, root.value) < 0) {
            root.left = insert(root.left, value);
        /*If value is max than root, value is going to be inserted at right */
        } else if (comparator.compare(value, root.value) > 0) {
            root.right = insert(root.right, value);
        }

        /*Update each root height and rebalanced Binary Tree*/
        updateHeight(root);
        return rebalanced(root);
    }

    public void delete(T value) {
        /*Update root when we want to delete a value*/
        root = delete(root, value);
    }

    private TreeNode<T> delete(TreeNode<T> root, T value) {
        if(root == null){return null;}
        /*If value is min than root, root value is going to be deleted at left */
        if (comparator.compare(value, root.value) < 0) {
            root.left = delete(root.left, value);
        }
        /*If value is max than root, root value is going to be deleted at right */
        else if (comparator.compare(value, root.value) > 0) {
            root.right = delete(root.right, value);
        }
        else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode<T> minRoot = finMin(root.right);
            root.value = minRoot.value;
            root.right = delete(root.right, minRoot.value);
        }
        updateHeight(root);
        return rebalanced(root);
    }

    private TreeNode<T> finMin(TreeNode<T> root) {
        if (root == null) { return null; }
        TreeNode<T> min = root;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    public T search(T value) {
        return search(root, value);
    }

    private T search(TreeNode<T> root, T value) {
        if (root == null) { return null;}
        /*If value is equal than root, return value */
        if (comparator.compare(value, root.value) == 0) {
            return value;
        }
        /*If value is min than root, search at left */
        if (comparator.compare(value, root.value) < 0) {
            return search(root.left, value);
        }
        /*Then, if value is max than root, search at left */
        return search(root.right, value);
    }

    public int height() {
        if(root == null){return 0;}
        return getHeight(root);
    }

    private void updateHeight(TreeNode<T> root) {
        if (root != null) {
            /*Get left and right height, compare and get max height, then add 1*/
            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        }
    }

    private int getHeight(TreeNode<T> root) {
        if (root == null) { return 0; }
        return root.height;
    }


    /*Return balanceFactor of the current root*/
    private int balanceFactor(TreeNode<T> root) {
        if (root == null) { return 0; }
        return getHeight(root.left) - getHeight(root.right);
    }

    private TreeNode<T> rotateRight(TreeNode<T> root) {
        /*Create new leftNode*/
        TreeNode<T> left = root.left;
        /*left of root now is right of left root*/
        root.left = left.right;
        /*right of left now is the root*/
        left.right = root;

        /*Update each height*/
        updateHeight(root);
        updateHeight(left);

        return left;
    }

    private TreeNode<T> rotateLeft(TreeNode<T> root) {
        /*Create new rightNode*/
        TreeNode<T> right = root.right;
        /*right of root now is left of right root*/
        root.right = right.left;
        /*left of right now is the root*/
        right.left = root;

        /*Update each height*/
        updateHeight(root);
        updateHeight(right);

        return right;
    }

    private TreeNode<T> rebalanced(TreeNode<T> root) {
        if (root == null) return null;

        /*Get balance factor of current disbalanced root*/
        int balance = balanceFactor(root);

        // Disbalanced at left
        if (balance > 1) {
            if (balanceFactor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            root = rotateRight(root);
        }

        // Disbalanced at right
        if (balance < -1) {
            if (balanceFactor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            root = rotateLeft(root);
        }

        updateHeight(root);
        return root;
    }


    public int size() {
        return size(root);
    }

    private int size(TreeNode<T> root) {
        if (root == null) { return 0; }

        /*Recursive code to get left and right sizes*/
        int left = size(root.left);
        int right = size(root.right);

        /*return both sizes plus 1, that represent root new size*/
        return left + right + 1;
    }

    LinkedList<T> AvlTree = new LinkedList<>();
    public void traverse() {
        traverse(root, AvlTree);
        System.out.println("AVL Tree: ");
        for(T value : AvlTree) {
            System.out.println(value);
        }
        AvlTree.clear();
    }

    private void traverse(TreeNode<T> root, List<T> result){
        if(root == null)
            return;
        traverse(root.left, result);
        result.add(root.value);
        traverse(root.right, result);
    }


}
