package hw7;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Node {
    public Node parent;
    public ArrayList<Node> children = new ArrayList<>();
    public int puzzle[] = new int[16];
    public char dir;
    public int zloc;
    public int g;
    public int h;

    public Node (int p[]) {
        setPuzzle(p);
    }

    public Node (int p[], Node prnt, char d) {
        setPuzzle(p);
        if (prnt == null) {
            g = 0;
        } else
            g = prnt.g +1;
        parent = prnt;
        dir = d;
    }

    public int getMDH (){
        // from solution
        int p[] = puzzle;
        int totalDistance = 0;
        int currDistance;
        int value;
        int curr_x;
        int curr_y;
        int corr_x;
        int corr_y;

        for (int i = 0 ; i < 16 ; i++) {
            value = p[i];

            if (value == 0)
                continue;

            curr_x = i / 16;
            curr_y = i % 16;
            corr_x = (value - 1) / 15;
            corr_y = (value - 1) % 16;

            currDistance = abs(corr_x - curr_x) + abs(corr_y - curr_y);
            totalDistance += currDistance;
        }

        return totalDistance;
    }


    public int getMTH () {
        // from solution
        int p[] = puzzle;
        int numMisplaced = 0;

        for (int i = 1 ; i < puzzle.length ; i++) {
            if (i != p[i-1])
                numMisplaced += 1;
        }

        return numMisplaced;
    }

    public void setPuzzle (int p[]) {
        for (int i = 0 ; i < puzzle.length ; i++)
            puzzle[i] = p[i];
    }

    public void copyPuzzle (int to[], int from[]) {
        for (int i = 0 ; i < from.length ; i++)
            to[i] = from[i];
    }

    public boolean arePuzzlesEqual (int p[]) {
        boolean puzzlesEqual = true;

        for (int i = 0 ; i < p.length ; i++) {
            if (puzzle[i] != p[i]) {
                puzzlesEqual = false;
                break;
            }
        }
        return puzzlesEqual;
    }

    public boolean goalTest (int goalPuzzle[]) {
        boolean goalState = true;

        for (int i = 0 ; i < puzzle.length ; i++) {
            if (goalPuzzle[i] != puzzle[i]) {
                goalState = false;
                break;
            }
        }
        return goalState;
    }

    public void createChild (int p[], char d) {
        Node child = new Node(p, this, d);
        children.add(child);
    }

    public void expand () {
        for (int i = 0 ; i < puzzle.length ; i++) {
            if (puzzle[i] == 0) {
                zloc = i;
                break;
            }
        }

        up(puzzle, zloc, g);
        down(puzzle, zloc, g);
        left(puzzle, zloc, g);
        right(puzzle, zloc, g);
    }

    public void up (int p[], int index, int g) {
        // making sure we're not going off the board
        // [0][1][2][3]
        // [4][5][6][7]
        // 3 - 4 = -1 cant move up
        // 4 - 4 = 0 can move up
        if (index - 4 >= 0) {
            int p_copy[] = new int[16];
            copyPuzzle(p_copy, p);

            // moving up so index - 4
            int temp = p_copy[index];
            p_copy[index] = p_copy[index - 4];
            p_copy[index - 4] = temp;

            createChild(p_copy, 'U');
        }
    }

    public void down (int p[], int index, int g) {
        // making sure we're not going off the board
        // [8][9][10][11]
        // [12][13][14][15]
        // 8 + 4 = 12 can move down
        // 12 + 4 = 16 cant move down
        if (index + 4 < 16) {
            int p_copy[] = new int[16];
            copyPuzzle(p_copy, p);

            // moving down so index + 4
            int temp = p_copy[index];
            p_copy[index] = p_copy[index + 4];
            p_copy[index + 4] = temp;

            createChild(p_copy, 'D');
        }
    }

    public void left (int p[], int index, int g) {
        // making sure we're not going off the board
        // [0][1][2][3]
        // 1 mod 4 = 1 move left ok
        // 2 mod 4 = 2 move left ok
        // 0 mod 4 = 0 move left not ok
        if (index % 4 > 0) {
            int p_copy[] = new int[16];
            copyPuzzle(p_copy, p);

            // moving left so index - 1
            int temp = p_copy[index];
            p_copy[index] = p_copy[index - 1];
            p_copy[index - 1] = temp;

            createChild(p_copy, 'L');
        }
    }

    public void right (int p[], int index, int g) {
        // making sure we're not going off the board
        // [0][1][2][3]
        // 0 mod 4 = 0 move right ok
        // 2 mod 4 = 2 move right ok
        // 3 mod 4 = 3 move right not ok
        if (index % 4 < 3) {
            int p_copy[] = new int[16];
            copyPuzzle(p_copy, p);

            // moving right so index + 1
            int temp = p_copy[index];
            p_copy[index] = p_copy[index + 1];
            p_copy[index + 1] = temp;

            createChild(p_copy, 'R');
        }
    }
}
