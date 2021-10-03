public class pathsWithSum {
    public class Node{
        Node left;
        Node right;
        int data;
        public Node(int data){
            this.data = data;
        }
    }

    int numberOfPathsWithSum(Node root, int targetSum){
        if(root == null) return 0;
        int rootSum = sumOfPaths(root,targetSum, 0);
        int leftSum = numberOfPathsWithSum(root.left, targetSum);
        int rightSum = numberOfPathsWithSum(root.right, targetSum);
        return rootSum + leftSum + rightSum;
    }

    int sumOfPaths(Node root, int targetSum, int sum){
        if(root == null){
            return 0;
        }
        sum += sum+ root.data;
        int totalPaths = 0;
        if(targetSum == sum){
            totalPaths++;
        }
        totalPaths += sumOfPaths(root.left, targetSum, sum);
        totalPaths += sumOfPaths(root.right, targetSum, sum);
        return totalPaths;
    }

}
