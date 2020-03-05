package hw7;
import java.util.Timer;
import java.util.TimerTask;

public class hw7MT {
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
                1,3,4,8,
                5,2,0,6,
                9,10,7,11,
                13,14,15,12,
        };

        System.out.print("> ");
        for (int i = 0 ; i < puzzle.length ; i++) {
            System.out.print(puzzle[i] + " ");
        }
        System.out.println();

        System.out.println("Misplaced tiles IDA* search: ");
        Node root = new Node(puzzle);

        startTime = System.currentTimeMillis();
        IDAStarSearchMisplacedT search = new IDAStarSearchMisplacedT();
        search.do_MisplacedTSearch(root, timer);

        finishTime = System.currentTimeMillis();
        elapsedTime = (finishTime - startTime);

        System.out.print("Time Taken: " + elapsedTime + "ms");
        System.out.println();

        finishMemUse = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        totalMemUse = finishMemUse - startMemUse;
        System.out.print("Memory Used: " + Math.round(totalMemUse/1024) + "kb");

    }
}

class TimeoutTask extends TimerTask {
    public void run() {
        System.out.print("Solution not found");
        System.exit(0);
    }
}
