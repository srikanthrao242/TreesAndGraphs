import java.util.ArrayList;

public class ValidateBST {

    public static class Node{
        Node left;
        Node right;
        int data;
        public Node(int data){
            this.data = data;
        }
    }

    public Node minimalTree(int[] arr, int start, int end){
        if(end < start) return  null;
        int mid = (start + end)/2;
        Node n = new Node(arr[mid]);
        n.left = minimalTree(arr, start, mid-1);
        n.right = minimalTree(arr, mid+1, end);
        return n;
    }

    public void inorderTraversal(Node root, ArrayList<Integer> arr){
        if(root != null) {
            inorderTraversal(root.left, arr);
            arr.add(root.data);
            inorderTraversal(root.right, arr);
        }
    }

    public boolean isBST(ArrayList<Integer> input){
        for(int i =0;i< input.size()-1;i++){
            if(input.get(i) > input.get(i+1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        int[] arr = {2,4,12,8,10,20};//false case
        //int[] arr = {2,4,6,8,10,20};//true case
        ValidateBST bst = new ValidateBST();
        Node root = bst.minimalTree(arr,0, arr.length-1);
        ArrayList<Integer> input = new ArrayList<>();
        bst.inorderTraversal(root, input);

        System.out.println(bst.isBST(input));

    }


}
