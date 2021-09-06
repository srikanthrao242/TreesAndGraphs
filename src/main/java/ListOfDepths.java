import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListOfDepths<T> {
    static class TreeNode<T>{
        TreeNode<T> left;
        TreeNode<T> right;
        T data;
        public TreeNode(T data){
            this.data = data;
        }
    }

    public TreeNode<T> minimalTree(T[] arr, int start, int end){
        if(end < start) return null;
        int mid = (start + end)/ 2;
        TreeNode<T> n = new TreeNode<T>(arr[mid]);
        n.left = minimalTree(arr, start, mid-1);
        n.right = minimalTree(arr, mid+1, end);
        return n;
    }

    public void listOfDepths(TreeNode<T> root, List<LinkedList<TreeNode<T>>> list, int level){
        if(root == null) return ;
        LinkedList<TreeNode<T>> ll = null;
        if(list.size() == level){
            ll = new LinkedList<TreeNode<T>>();
            list.add(ll);
        }else{
            ll = list.get(level);
        }
        ll.add(root);
        listOfDepths(root.left, list, level+1);
        listOfDepths(root.right, list, level+1);
    }

    public static void main(String args[]){

        Integer[] arr = {1,2,3,4,5,6,7,8};
        ListOfDepths<Integer> lod = new ListOfDepths<Integer>();
        ArrayList<LinkedList<TreeNode<Integer>>> list = new ArrayList<LinkedList<TreeNode<Integer>>>();
        lod.listOfDepths(lod.minimalTree(arr, 0, 7), list, 0);
        list.stream().forEach(v -> {
            System.out.println("level");
            v.forEach(n-> System.out.println(n.data));
        });

    }
}
