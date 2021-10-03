public class CheckSubTree {
    class Node{
        Node left;
        Node right;
        int data;
        public Node(int data){
            this.data = data;
        }
    }

    boolean isSubTreeWithTraverse(Node r1, Node r2){
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        getInorderTravers(r1, sb1);
        getInorderTravers(r2, sb2);
        return sb1.indexOf(sb2.toString()) != -1;
    }

    void getInorderTravers(Node root, StringBuilder sb){
        if(root == null){
            sb.append("X");
            return;
        }
        getInorderTravers(root.left,sb);
        sb.append(root.data);
        getInorderTravers(root.right,sb);
    }

    boolean isSubTreeByTreeCompare(Node r1, Node r2){
        if(r2 == null) return true;
        return isSubTree(r1,r2);
    }

    boolean isSubTree(Node r1, Node r2){
        if(r1 == null || r2 == null){
            return false;
        }else return r1.data == r2.data && matchTree(r1, r2);
    }

    boolean matchTree(Node r1, Node r2){
        if(r1 == null && r2 == null){
            return true;
        }else if(r1 == null || r2 == null){
            return false;
        }else if(r1.data != r2.data){
            return false;
        }else{
            return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
        }
    }

}
