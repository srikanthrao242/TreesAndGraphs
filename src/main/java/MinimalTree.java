public class MinimalTree {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int data;
        public TreeNode(int data){
            this.data = data;
        }
    }

    static TreeNode minimalTree(int[] arr, int start, int end){
        if(end < start) return  null;
        int mid = (start + end)/2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = minimalTree(arr, start, mid -1);
        node.right = minimalTree(arr, mid+1, end);
        return node;
    }

    static void printTree(TreeNode node){
        if(node != null){
            System.out.println(node.data);
            printTree(node.left);
            printTree(node.right);
        }
    }

    public static void main(String[] args){

        int[] inputArr = {1,2,3,4,5,6,7,8};

        TreeNode node = minimalTree(inputArr, 0, 7);
        printTree(node);
    }
}
