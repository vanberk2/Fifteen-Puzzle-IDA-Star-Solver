package hw7;
import java.util.Timer;

public class hw7MD {
    public static void main (String[] args) {
        // variables used for statistics
        Timer timer = new Timer();
        int timeOut = 30000;
        timer.schedule(new TimeoutTask(), timeOut);
        double startTime;
        double finishTime;
        double elapsedTime;
        double startMemUse = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        double finishMemUse;
        double totalMemUse;

        int puzzle[] = {
                5,2,4,8,
                10,0,3,14,
                13,6,11,12,
                1,15,9,7,
        };

        System.out.print("> ");
        for (int i = 0 ; i < puzzle.length ; i++) {
            System.out.print(puzzle[i] + " ");
        }
        System.out.println();

        System.out.println("Manhattan distance IDA* search: ");
        Node root = new Node(puzzle);

        startTime = System.currentTimeMillis();
        IDAStarSearchManhattan search = new IDAStarSearchManhattan();
        search.do_ManhattanSearch(root, timer);

        finishTime = System.currentTimeMillis();
        elapsedTime = (finishTime - startTime);

        System.out.print("Time Taken: " + elapsedTime + "ms");
        System.out.println();

        finishMemUse = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        totalMemUse = finishMemUse - startMemUse;
        System.out.print("Memory Used: " + Math.round(totalMemUse/1024) + "kb");
    }
}
