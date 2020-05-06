public class CheckingAndPrinting {

    Node root;

    public void createBinaryTree() {
        Node root = new Node(4);
        root.left = new Node(3);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.left.right.left = new Node(4);
        root.left.right.right = new Node(7);
        root.right = new Node(10);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);

        printTreeInOrder(root);
    }
    public void printTreeInOrder(Node root) {

        if (root == null)
            return;
        printTreeInOrder(root.left);
        System.out.print(root.data + " ");
        printTreeInOrder(root.right);

    }


    /**
     *  Q3:- Check sum of Covered and Uncovered nodes of Binary Tree
     *  Boundary Traversal iteratively as well as recursively
     * @param root
     * @return boundarySum of tree
     */
    private int boundarySum(Node root){
        if(root==null)
            return 0;
        int left = leftBoundarySum(root.left);
        int right = rightBoundarySum(root.right);
        int leftRecursively = leftBoundarySumRecursively(root.left);
        int rightRecursively = rightBoundarySumRecursively(root.right);
        return root.data + left + right;
    }
    private int leftBoundarySum(Node temp){
        int sum = 0;
        while (temp!=null){
            sum  = sum + temp.data;
            if(temp.left!=null)
                temp = temp.left;
            else
                temp = temp.right;

        }

        return sum;
    }
    private int leftBoundarySumRecursively(Node temp){
        if (temp.left == null && temp.right==null)
            return temp.data;
        if(temp.left!=null)
            return temp.data + leftBoundarySumRecursively(temp.left);
        else
            return temp.data + leftBoundarySumRecursively(temp.right);

    }
    private int rightBoundarySum(Node temp){
        int sum = 0;
        while (temp!=null){
            sum  = sum + temp.data;
            if(temp.right!=null)
                temp = temp.right;
            else
                temp = temp.left;

        }

        return sum;
    }
    private int rightBoundarySumRecursively(Node temp){
        if (temp.left == null && temp.right==null)
            return temp.data;
        if(temp.right!=null)
            return temp.data + rightBoundarySumRecursively(temp.right);
        else
            return temp.data + rightBoundarySumRecursively(temp.left);

    }

    /**
     * Q4:- Check if two nodes are cousins in a Binary Tree
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private boolean isCousin(Node root, Node node1, Node node2){

        int level1 = findLevel(root, node1.data, 1);
        int level2 = findLevel(root, node2.data, 1);
        if(level1!=level2)
            return false;
        if (isSibling(root, node1, node2))
            return false;
        else return true;

    }
    private int findLevel(Node root, int key, int level){
        if(root==null)
            return -1;
        if (root.data==key)
            return level;

        // Return level if Node is present in left subtree
        int left = findLevel(root.left, key, level+1);
        if(left!=-1)
            return left;
            // Else search in right subtree
        else return findLevel(root.right, key, level+1);
    }
    boolean isSibling(Node node, Node node1, Node node2) {
        if (node == null)
            return false;
        if ((node.left == node1 && node.right == node2) || (node.left == node2 && node.right == node1))
            return true;
        boolean left = isSibling(node.left, node1, node2);
        if (left != false)
            return left;
        return isSibling(node.right, node1, node2);

      /*  // Base case
        if (node == null)
            return false;
        return ((node.left == a && node.right == b) ||
                (node.left == b && node.right == a) ||
                isSibling(node.left, a, b) ||
                isSibling(node.right, a, b));*/
    }




}
