import scala.Int;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTSequence {

    static class Node{
        Node left;
        Node right;
        int data;
        public Node(int data){
            this.data = data;
        }
    }

    public void bstSequenceLR(Node root, ArrayList<Integer> arr){
        if(root != null){
            arr.add(root.data);
            bstSequenceLR(root.left, arr);
            bstSequenceLR(root.right, arr);
        }
    }

    public void bstSequenceRL(Node root, ArrayList<Integer> arr){
        if(root != null){
            arr.add(root.data);
            bstSequenceLR(root.right, arr);
            bstSequenceLR(root.left, arr);
        }
    }

    public void bstSequence(Node root, ArrayList<ArrayList<Integer>> res){
        ArrayList<Integer> re = new ArrayList<>();
        if(root != null) {
            bstSequenceLR(root.left, re);
            bstSequenceRL(root.right, re);
            res.add(re);
            bstSequence(root.left, res);
            bstSequence(root.right, res);
        }
    }

    public Node minimalBST(int[] arr, int start, int end){
        if(start > end){
            return null;
        }
        int mid = (start + end)/2;
        Node root = new Node(arr[mid]);
        root.left = minimalBST(arr, start, mid-1);
        root.right = minimalBST(arr, mid+1, end);
        return root;
    }

    public ArrayList<LinkedList<Integer>> allSequences(Node node){
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        if(node == null){
            result.add(new LinkedList<>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        for(LinkedList<Integer> left: leftSeq){
            for(LinkedList<Integer> right: rightSeq){
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveList(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return  result;

    }

    void weaveList(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix){
        if(first.isEmpty() || second.isEmpty()){
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headFirst = first.removeFirst();
        prefix.addFirst(headFirst);
        weaveList(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveList(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headFirst);
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6};
        BSTSequence bstSeq = new BSTSequence();
        Node root = bstSeq.minimalBST(arr, 0, 5);
        System.out.println(root.data);
        ArrayList<LinkedList<Integer>> res = bstSeq.allSequences(root);
        //bstSeq.bstSequenceLR(root, res.get(0));
        res.get(0).forEach(v -> System.out.print(v + " "));
        System.out.println("");
        //bstSeq.bstSequenceRL(root, res.get(1));
        res.get(1).forEach(v -> System.out.print(v+ " "));
    }
}
