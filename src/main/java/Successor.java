public class Successor {
    public class Node{
        Node left;
        Node right;
        Node parent;
        int data;
        public Node(int data){
            this.data = data;
        }
    }

    public Node minimalTree(int[] arr, int start, int end, Node parent){
        if(end < start) return null;
        int mid = (start + end)/2;
        Node n = new Node(arr[mid]);
        n.parent = parent;
        n.left = minimalTree(arr, start, mid-1, n);
        n.right = minimalTree(arr, mid+1, end, n);
        return n;
    }

    public Node leftMostNode(Node n){
        while (n.left != null){
            n = n.left;
        }
        return n;
    }

    public Node successor(Node n){
        if(n == null) return null;
        if(n.right != null){
            return leftMostNode(n.right);
        }else{
            Node q = n;
            Node x = q.parent;
            while (x != null && x.left != q){
                x = x.parent;
            }
            return x;
        }
    }

    public static void main(String[] args){
        int[] arr = {1,2,4,6,8,10,20};
        Successor succ = new Successor();
        Node n = succ.minimalTree(arr, 0, 6, null);
        System.out.println(succ.successor(n).data);
    }
}
