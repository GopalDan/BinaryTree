public class Summation {

    Node root;
    int sum = 0;
    int maxSum = Integer.MIN_VALUE;
    public void createBinaryTree() {
        System.out.println("Summation!");
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.right = new Node(9);
        printTreeInOrder(root);

       /* Q 9
        System.out.println("After Deletion!");
        Node newRoot =  deleteLeaf(root, 0, 10);
        printTreeInOrder(newRoot);*/
    }

    public void printTreeInOrder(Node root) {

        if (root == null)
            return;
        printTreeInOrder(root.left);
        System.out.print(root.data + " ");
        printTreeInOrder(root.right);

    }

    /**
     * Q2: Sum of all the parent nodes having child node x
     * @param node
     * @param x
     */
    private void  sumOfParentOfX(Node node, int x){
        if (root==null || (root.left==null && root.right==null))
            return;

        // if left or right child of root is 'x', then add the root's data to 'sum'
        if (root.left!=null && root.left.data==x || root.right!=null && root.right.data==x)
            sum+=root.data;

        // recursively find the required parent nodes in the left and right subtree
        sumOfParentOfX(root.left, x);
        sumOfParentOfX(root.right, x);
    }

    /**
     * Q3-4:- Find sum of all left leaves in a given Binary Tree
     * Approach1:- bottom-up approach
     * Approach2:- top-bottom approach
     * @param root
     * @param isLeftSide
     */
    private void leftLeaveSum(Node root, boolean isLeftSide){
        if (root==null)
            return;
        if((root.left==null && root.right==null) && isLeftSide)
            sum = sum + root.data;
        leftLeaveSum(root.left, true);
        leftLeaveSum(root.right, false);
    }
    private void leftLeaveSum2(Node root){
        if (root==null)
            return;
        if (root.left!=null){
            if (root.left.left==null && root.left.right==null)
                sum = sum + root.left.data;
        }
        leftLeaveSum2(root.left);
        leftLeaveSum2(root.right);

    }

    /**
     * Fundamental Question:- Longest Leaf path sum (root to leaf):- longest path, max path
     * Q8:- Sum of nodes on the longest path from root to leaf node
     *     |
     *     A
     *    / \
     *   B   C
     *   Top-Bottom approach:- first thing root as -A find sum then do same for left & right subtree
     * @param root
     * @param currentSum
     */
    private void longestLeafSum(Node root, int currentSum){
        if (root==null)
            return;

        currentSum = currentSum + root.data;

        if (root.left==null && root.right==null){
            if (currentSum > maxSum){
                maxSum = currentSum;
                return;
            }
        }
        longestLeafSum(root.left, currentSum);
        longestLeafSum(root.right, currentSum);

    }
    /**
     * Fundamental Question:- Leaf path sum  & Deleting node
     * Q9:- Remove all nodes which don’t lie in any path with sum>= k
     * @param root
     * @param currentSum
     * @param k
     * @return
     */
    private Node deleteLeaf(Node root, int currentSum, int k){

        if (root==null)
            return null;

        currentSum = currentSum + root.data;

        if (root.left==null && root.right==null){
            if (currentSum <= k){
                root = null;
                return root;
            }
        }
        root.left =  deleteLeaf(root.left, currentSum, k);
        root.right = deleteLeaf(root.right, currentSum, k);
         return root;

    }

    /**
     *  Fundamental Question:- Max Leaf path sum (root to leaf):- longest path, max path
     *  Q10:- Find the maximum path sum between two leaves of a binary tree
     *       A
     *     /  \
     *    B    C
     * @param root
     * @return
     */
    private int maximumLeafSum(Node root){
        if (root==null)
            return Integer.MIN_VALUE;
        if (root.left==null && root.right==null)
            return root.data;

        int left = maximumLeafSum(root.left);
        int right = maximumLeafSum(root.right);

        if (left>right)
            return left + root.data;
        else return right + root.data;

    }
    private void fixedRootLeaveToLeaveMaxSum(Node root){
        if (root==null)
            return;
        int leftSubtreeMaxLeaveSum = maximumLeafSum(root.left);
        int rightSubtreeMaxLeaveSum = maximumLeafSum(root.right);
        int totalSum = root.data + leftSubtreeMaxLeaveSum + rightSubtreeMaxLeaveSum;
        if(totalSum> maxSum)
            maxSum = totalSum;
    }
    private void leaveToLeaveMaxSum(Node root){
        fixedRootLeaveToLeaveMaxSum(root);
        fixedRootLeaveToLeaveMaxSum(root.left);
        fixedRootLeaveToLeaveMaxSum(root.right);
    }

    /** Fundamental Question:- print root to targeted node x
     * Q11:- Find the maximum sum leaf to root path in a Binary Tree
     * @param node
     * @param targetedNode
     * @return
     */
    private int printPath(Node node, Node targetedNode){
        if (root==null)
            return -1;
        if (root==targetedNode){
            System.out.print(root.data);
            return 1;
        }
        int left = printPath(node.left, targetedNode);
        if(left!=-1)
            System.out.print(root.data);
        int right = printPath(node.right, targetedNode);
        if(right!=-1)
            System.out.print(root.data);

        if (left==1 || right ==1 )
            return 1;
        else return -1;

    }




}
