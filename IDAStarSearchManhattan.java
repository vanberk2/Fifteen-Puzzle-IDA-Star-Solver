package hw7;
import java.util.*;

public class IDAStarSearchManhattan {
    public boolean goalState = false;
    ArrayList<Node> solution = new ArrayList<>();
    int b;
    int min = 0;
    int nodesExpanded = 0;

    public int goalPuzzle[] = {
            1,2,3,4,
            5,6,7,8,
            9,10,11,12,
            13,14,15,0,
    };


    public void do_ManhattanSearch (Node root, Timer timer) {
        int bound = root.getMDH();
        Stack<Node> explored = new Stack<>();
        explored.push(root);

        while (true) {
            b = r_aStarSearchManhattan(timer, 0, bound, explored);
            if (b == -1)
                break;
            bound = b;
        }
    }

    public int r_aStarSearchManhattan (Timer timer, int g, int bound, Stack<Node> explored) {
        Node curr = explored.peek();

        int f = g + curr.getMDH();

        if (f > bound)
            return f;
        if (curr.goalTest(goalPuzzle)) {
            goalState = true;
            getPath(solution, curr);

            Collections.reverse(solution);
            if (!solution.isEmpty()) {
                for (int i = 0 ; i < solution.size() ; i++) {
                    System.out.print(solution.get(i).dir + " ");
                }
                System.out.println();
                System.out.println("Nodes expanded: " + nodesExpanded);
                timer.cancel();
            }
            return -1;
        }

        min = 99999;

        curr.expand();

        for (int i = 0; i < curr.children.size(); i++) {
            Node currChild = curr.children.get(i);

            if (!hasChild(currChild, explored)) {
                explored.push(currChild);
                nodesExpanded++;
                b = r_aStarSearchManhattan(timer,  g + 1, bound, explored);
                if (b == -1)
                    return b;
                if (b < min)
                    min = b;
                explored.pop();
            }
        }

        return min;
    }

    public boolean hasChild (Node child, Stack<Node> nodes) {
        boolean hasChild = false;

        for (int i = 0; i < nodes.size() ; i++) {
            if (nodes.get(i).arePuzzlesEqual(child.puzzle)) {
                hasChild = true;
            }
        }
        return hasChild;
    }

    public void getPath (ArrayList<Node> path, Node a) {
        Node curr = a;

        if (curr == null)
            return;
        path.add(curr);
        getPath(path, curr.parent);
    }
}
