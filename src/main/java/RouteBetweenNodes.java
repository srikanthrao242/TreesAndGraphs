import java.util.ArrayList;
import java.util.LinkedList;

public class RouteBetweenNodes {

    static class Graph<nodes> {
        public ArrayList<Node> nodes;
    }

    static class Node{
        public String name;
        public ArrayList<Node> children;
        public boolean isVisited;
        public Node(String name){
            this.name = name;
            this.children = new ArrayList<>();
            this.isVisited = false;
        }
    }

    static boolean Search(Node start, Node end){
        if(start == null || end == null) return true;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(start);
        start.isVisited = true;
        while (!queue.isEmpty()){
            Node r = queue.removeFirst();
            for (Node n: r.children){
                if(!n.isVisited){
                    if(n == end){
                        return true;
                    }else{
                        n.isVisited = true;
                        queue.add(n);
                    }
                }
            }
        }
        return false;
    }


    public static void main(String[] args){

        Graph g = new Graph();
        Node v1 =  new Node("v1");
        Node v2 = new Node("v2");
        Node v3 = new Node("v3");
        Node v4 = new Node("v4");
        Node v5 = new Node("v5");
        Node v6 = new Node("v6");
        v1.children.add(v2);
        v1.children.add(v3);
        v2.children.add(v3);
        v2.children.add(v4);
        v3.children.add(v4);
        v3.children.add(v5);
        v4.children.add(v5);
        v4.children.add(v6);
        v5.children.add(v3);
        v5.children.add(v4);
        v5.children.add(v6);
        v6.children.add(v4);
        v6.children.add(v5);

        System.out.println(Search(v1, v6));


    }

}
