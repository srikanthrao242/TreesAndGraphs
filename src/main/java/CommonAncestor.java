import java.util.ArrayList;

public class CommonAncestor<T> {
    public class Node{
        T data;
        Node parent;
        Node left;
        Node right;
        public Node(T data) {
            this.data = data;
        }
    }

    public Node minimalSpanningTree(T[] input, int start, int end){
        if(end < start) return null;
        int mid = (start+end)/2;
        Node n = new Node(input[mid]);
        n.left = minimalSpanningTree(input, start, mid-1);
        if(n.left != null)
        n.left.parent = n;
        n.right = minimalSpanningTree(input, mid+1, end);
        if(n.right != null)
        n.right.parent = n;
        return n;
    }

    public ArrayList<T> pathToParents(Node n){
        ArrayList<T> output = new ArrayList<>();
        while (n != null){
            output.add(n.data);
            n = n.parent;
        }
        return output;
    }

    public T commonAncestor(Node n1, Node n2){
        if(n1 == n2) return n1.data;
        if(n1 != null && n2 == null) return n1.data;
        if(n1 == null && n2 != null) return n2.data;
        if(n1 == null && n2 == null) return null;
        ArrayList<T> n1ParentPaths = pathToParents(n1);
        ArrayList<T> n2ParentPaths = pathToParents(n2);
        if(n1ParentPaths.size() > n2ParentPaths.size()){
            for (T v: n2ParentPaths) {
                if(n1ParentPaths.contains(v)){
                    return v;
                }
            }
        }else{
            for (T v: n1ParentPaths) {
                if(n2ParentPaths.contains(v)){
                    return v;
                }
            }
        }
        return null;
    }

    public Node getNode(Node root, T data){
        if(root == null) return null;
        if(root.data == data) return root;
        else {
            Node l = getNode(root.left, data);
            if(l != null && l.data == data)
                return l;
            Node r = getNode(root.right, data);
            if(r != null && r.data == data)
                return r;
            return null;
        }
    }

    public Node commonAncestor(Node root, Node p, Node q){
        if(root == null) return null;
        if(root == p && root == q) return root;
        Node x = commonAncestor(root.left, p, q);
        if(x != null && x != p && x != q){
            return x;
        }
        Node y = commonAncestor(root.right, p,q);
        if(y != null && y != p & y != q){
            return y;
        }
        if(x != null && y != null){
            return root;
        }else if(root == p || root == q){
            return root;
        }else {
            return x == null ? y:x;
        }
    }

    public static void main(String[] args){
        String[] arr = {"H", "D",  "I", "B", "E", "J", "K", "A", "F", "L", "M", "C","N", "G", "O"};
        CommonAncestor<String> cm = new CommonAncestor<>();
        CommonAncestor<String>.Node root = cm.minimalSpanningTree(arr, 0 ,14);
        CommonAncestor<String>.Node hNode = cm.getNode(root, "H");
        CommonAncestor<String>.Node oNode = cm.getNode(root, "O");
        System.out.println(cm.commonAncestor(root, hNode, oNode).data);
    }
}
