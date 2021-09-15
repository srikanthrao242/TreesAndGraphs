
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

public class BuildOrder<T> {
    HashMap<T,Node> map = new HashMap<>();

    class Node{
        T data;
        ArrayList<Node> children;
        String status;
        public Node(T data){
            this.data = data;
            this.children = new ArrayList<>();
            status = "blank";
        }
    }

    public Node addNode(T data){
        if(!map.containsKey(data)){
            Node n = new Node(data);
            map.put(data, n);
            return n;
        }else{
            return map.get(data);
        }
    }

    public void addChildren(T data, Node child){
        if(map.containsKey(data)){
            map.get(data).children.add(child);
        }
    }

    public void buildGraph(T[][] dependencies){
        for (T[] str :dependencies) {
            Node n = addNode(str[0]);
            Node c = addNode(str[1]);
            addChildren(n.data, c);
        }
    }

    public boolean doDFS(Node n, Stack<T> stack){
        if(Objects.equals(n.status, "partial")){
            return false;
        }
        if(Objects.equals(n.status, "blank")){
            n.status = "partial";
            for (Node child: n.children) {
                if(!doDFS(child, stack)){
                    return false;
                }
            }
            n.status = "complete";
            stack.push(n.data);
        }
        return true;
    }

    public Stack<T> buildOrder(T[] input){
        Stack<T> stack = new Stack<>();
        for (T d: input  ) {
            Node n = addNode(d);
            if(!doDFS(n, stack)){
                return null;
            }
        }
        return stack;
    }

    public static void main(String[] args){
        BuildOrder<String> bo = new BuildOrder<>();
        String[][] arr = {{"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}};
        bo.buildGraph(arr);
        String[] inputs = {"a","b", "c", "d", "e", "f"};
        Stack<String> response = bo.buildOrder(inputs);
        response.forEach(System.out::println);
    }


}
