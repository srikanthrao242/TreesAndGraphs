public class IsBalanceBinaryTree {

    public class Node{
        Node left;
        Node right;
        int data;
        public Node(int data){
            this.data = data;
        }
    }

    public int heightOfTree(Node node){
        if(node == null){
            return -1;
        }
        return Math.max(heightOfTree(node.left),heightOfTree(node.right)) +1;
    }

    public boolean isBalancedTree(Node node){
        if(node == null) return true;
            int leftHeight = heightOfTree(node.left);
            int rightHeight = heightOfTree(node.right);
            int heightDiff = leftHeight - rightHeight;
            if(Math.abs(heightDiff) > 1)
                return false;
            else
                return isBalancedTree(node.left) && isBalancedTree(node.right);
    }

    public Node minimalTree(int[] arr, int start, int end){
        if(end < start){
            return null;
        }
        int mid = (start + end)/2;
        Node n = new Node(arr[mid]);
        n.left = minimalTree(arr, start, mid-1);
        n.right = minimalTree(arr, mid +1, end);
        return n;
    }

    public static void main(String args[]){
        int[] arr = {1,2,3,4,5};
        IsBalanceBinaryTree bt = new IsBalanceBinaryTree();
        Node n = bt.minimalTree(arr, 0, 4);
        System.out.println(bt.isBalancedTree(n));
    }

}
