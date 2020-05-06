import java.util.HashMap;
import java.util.Stack;

public class BinaryTree {
    static Node root;

    class Node {
        int data;
        char key;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            left = right = null;
        }

        public Node(char key) {
            this.key = key;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        BinaryTree binaryTree = new BinaryTree();
        /* Approach 1:- For explanation
        root = binaryTree.insert(15, root);
        root = binaryTree.insert(7, root);
        root = binaryTree.insert(25, root);
        root = binaryTree.insert(68, root);
        root = binaryTree.insert(9, root);*/

        // Approach 3:- For coding *** use this approach
     /*   root = binaryTree.insert(15, root);
        binaryTree.insert(10, root);
        binaryTree.insert(20, root);
        binaryTree.insert(40, root);*/


        // Approach 2:- For coding
       /* binaryTree.add(15);
        binaryTree.add(7);
        binaryTree.add(25);
        binaryTree.add(68);
        binaryTree.add(9);*/

        // binaryTree.print(root);
        //boolean value = binaryTree.search(68, root);
        //System.out.println("Element exist:- " + value);
        char in[] = new char[]{'D', 'B', 'E', 'A', 'F', 'C'};
        char pre[] = new char[]{'A', 'B', 'D', 'E', 'C', 'F'};
        int len = in.length;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            hashMap.put(in[i], i);
        }
        int l = hashMap.size();
        int i = hashMap.get(pre[3]);
//        Node root = binaryTree.constructTree(in, pre, 0, len - 1, hashMap, 0);
//        Node root = binaryTree.buildTree(in, pre, 0, len - 1,0);

        // building the tree by printing inorder traversal
        System.out.println("Inorder traversal of constructed tree is : ");
       // binaryTree.printInorder(root);

        CheckingAndPrinting checkingAndPrinting = new CheckingAndPrinting();
        checkingAndPrinting.createBinaryTree();

        Summation summation = new Summation();
        summation.createBinaryTree();

    }

    private void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.data + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    /**
     * Insert element iteratively in tree
     *
     * @param root root of tree
     * @param key  data to be inserted
     * @return root node
     * Time complexity is O(h) where h is height of the tree
     */
    private Node insertIteratively(Node root, int key) {
        Node temp = root;        // start with root node
        Node parent = null;        // pointer to store parent node of current node

        // if tree is empty, create a new node and set root
        if (temp == null) {
            temp = new Node(key);
            return temp;
        }

        // traverse the tree and find parent node of key
        while (temp != null) {
            parent = temp;
            if (key < temp.data) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }

        if (key < parent.data) {
            parent.left = new Node(key);
        } else {
            parent.right = new Node(key);
        }

        return root;
    }

    private void add(int data) {
        root = insert(data, root);
    }

    /**
     * Insert element recursively in tree
     *
     * @param data
     * @param root address of root node
     * @return
     */
    private Node insert(int data, Node root) {
        if (root == null) {   // Tree, so root of main tree
            root = new Node(data);
            return root;
        } else if (data < root.data) { // left subtree (recursively a tree, root of left subtree is left child
            root.left = insert(data, root.left);
        } else {   // right subtree (recursively a tree that's why  root of right  subtree)
            root.right = insert(data, root.right);
        }
        return root;
    }

    private void print(Node root) {
        if (root == null)
            return;
        System.out.println(root.data);
        print(root.left);
        print(root.right);
    }

    private boolean search(int key, Node root) {
        boolean b = false;
        if (root == null)
            return false;
        else if (root.data == key) return true;
        else if (key < root.data) b = search(key, root.left);
        else if (key >= root.data) b = search(key, root.right);

        return b;
    }

    private Node deleteNode(int key, Node root) {
        Node curr = root;
        Node parent = null;

        while (curr != null && curr.data != key) {
            parent = curr;
            if (key < curr.data) {
                curr = curr.left;
            } else
                curr = curr.right;
        }

        // return if key is not found in the tree
        if (curr == null) {
            System.out.println("Element not found");
            return root;
        }

        // case1:-  0 child
        if (curr.left == null && curr.right == null) {
            // if node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != root) {
                if (curr == parent.right)
                    parent.right = null;
                else
                    parent.left = null;

            } else {            // if tree has only root node, delete it and set root to null
                root = null;
            }
        }

        // case2:-  2 child
        else if (curr.left != null && curr.right != null) {
            int data = FindMin(curr.right);
            deleteNode(data, curr.right);
            curr.data = data;
        }
        // case3:-  1 child
        else { // right child
          /*  if(curr==parent.left){
                parent.left=curr.right;
                curr.right=null;
            }else {
                parent.right=curr.right;
                curr.right=null;
            }

        }else if(curr.left!=null && curr.right==null){   // left child
            if(curr==parent.left){
                parent.left=curr.left;
                curr.left=null;
            }else {
                parent.right=curr.left;
                curr.left=null;
            }*/
            // find child node
            Node child = (curr.left != null) ? curr.left : curr.right;

            // if node to be deleted is not a root node, then set its parent
            // to its child
            if (curr != root) {
                if (curr == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }

            // if node to be deleted is root node, then set the root to child
            else {
                root = child;
            }
        }

        return root;
    }

    private int FindMin(Node right) {
        Node temp = right;
        int min = temp.data;

       /* while (temp.left!=null && temp.right!=null) {
            if (temp.left.data < min) {
                min = temp.left.data;
                temp = temp.left;
            } else if (temp.right.data < min) {
                min = temp.right.data;
                temp = temp.right;
            }
        }
        while (temp.left!=null && temp.left.data<min){
            min = temp.left.data;
            temp = temp.left;
        }
        while (temp.right!=null && temp.right.data<min){
            min = temp.right.data;
            temp = temp.right;
        }*/
        while (temp.left != null)
            temp = temp.left;


        return temp.data;
    }

    private void inorderStack(Node root) {
        Node curr = root;
        Stack<Node> stack = new Stack<>();
        while (curr != null || stack.size() > 0) {


            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            Node node = stack.pop();
            System.out.print(node.data);
            curr = node.right;
        }
    }

    private Node buildTree(char in[], char pre[], int start, int end, int index) {

        if (start > end)
            return null;
        int data = pre[index++];
        Node node = new Node(data);
        if (start == end)
            return node;
        int position = IndexOfData(data, in);

        node.left = buildTree(in, pre, 0, position - 1, index);
        node.right = buildTree(in, pre, position + 1, in.length - 1, index);

        return node;
    }

    private Node constructTree(char[] in, char[] pre, int start, int end, HashMap<Character, Integer> hashMap, int index) {
        Node node = null;

        try {
            if (start > end)
                return null;
            char data = pre[index];
            node = new Node(data);
            int position = hashMap.get(data);
            node.left = constructTree(in, pre, start, position - 1, hashMap, index);
            node.right = constructTree(in, pre, position + 1, end, hashMap, index);
            return node;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return node;
    }

    private int IndexOfData(int data, char[] in) {
        int i = 0;
        for (; i < in.length; i++) {
            if (data == in[i])
                return i;
        }
        return i;
    }

    /**
     * Construct Tree from inorder & level order
     *
     * @param inorder
     * @param levelOrder
     * @param iStart
     * @param iEnd
     * @return
     */
    public Node makeBTree(int[] inorder, int[] levelOrder, int iStart, int iEnd) {
        if (iStart > iEnd) {
            return null;
        }
        int rootVal = levelOrder[0];
        Node root = new Node(rootVal);
        if (iStart == iEnd) {
            return root;
        }
        int index = findIndex(inorder, rootVal, iStart, iEnd);

        int[] newleftLevel = newLevelOrder(inorder, levelOrder, iStart,
                index - 1);
        int[] newrighttLevel = newLevelOrder(inorder, levelOrder, index + 1,
                iEnd);

        root.left = makeBTree(inorder, newleftLevel, iStart, index - 1);
        root.right = makeBTree(inorder, newrighttLevel, index + 1, iEnd);

        return root;
    }

    public int[] newLevelOrder(int[] inorder, int[] levelOrder, int iStart, int iEnd) {
        int[] newlevel = new int[iEnd - iStart + 1];
        int x = 0;
        for (int i = 0; i < levelOrder.length; i++) {
            if (findIndex(inorder, levelOrder[i], iStart, iEnd) != -1) {
                newlevel[x] = levelOrder[i];
                x++;
            }
        }
        return newlevel;
    }

    public int findIndex(int[] inorder, int value, int iStart, int iEnd) {
        int x = -1;
        for (int i = iStart; i <= iEnd; i++) {
            if (value == inorder[i]) {
                x = i;
            }
        }
        return x;
    }


    // Q:- Check for Children Sum Property in a Binary Tree
    boolean isValidBinaryTree(Node node) {
        int left_data = 0, right_data = 0;
        if (node == null || (node.left == null && node.right == null))
            return true;
        else {
            if (node.left != null)
                left_data = node.left.data;
            if (node.right != null)
                right_data = node.right.data;
            if ((node.data == left_data + right_data) && isValidBinaryTree(node.left) && isValidBinaryTree(node.right))
                return true;
            else
                return false;
        }
    }

    /**
     * Program to find the level of a node
     *
     * @param node
     * @param level
     * @param key
     * @return
     */

    int findLevel(Node node, int level, int key) {
        int y = -1;
        if (node == null)
            return -1;
        if (node.data == key)
            return level;
        level++;
        y = findLevel(node.left, level, key);
        if (y != -1)
            return y;
        y = findLevel(node.right, level, key);
        return y;
    }

    /**
     * Program to check sibling of two nodes node1 & node2
     *
     * @param node
     * @param node1
     * @param node2
     * @return
     */

    boolean isSibling(Node node, Node node1, Node node2) {
        if (node == null)
            return false;
        if ((node.left == node1 && node.right == node2) || (node.left == node2 && node.right == node1))
            return true;
        boolean left = isSibling(node.left, node1, node2);
        if (left != false)
            return left;
        return isSibling(node.right, node1, node2);
    }

}
