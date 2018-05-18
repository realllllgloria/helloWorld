import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

class BlockList {
    static Block[] blocks = new Block[]{
            new Block(1, 0, 2, 1, "2by1", "red"),
            new Block(0, 0, 1, 2, "1by2", "red"),
            new Block(0, 2, 1, 2, "1by2", "red"),
            new Block(3, 0, 1, 2, "1by2", "red"),
            new Block(1, 2, 3, 1, "3by1", "red"),
            new Block(1, 3, 1, 3, "1by3", "red")
    };

    static boolean[][] occupied = new boolean[Board.W][Board.H];

    static void loadBoard(String filename) {
        ArrayList<Block> blks = new ArrayList<>();
        Scanner scanner = new Scanner(BlockList.class.getResourceAsStream(filename));
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] cols = line.split(", ");
            if (cols.length < 4) continue;
            String color = cols[0],
                    startPosition = cols[1],
                    endPosition = cols[2],
                    direction = cols[3];
            int spRow = Integer.valueOf(startPosition.split("-")[1]);
            int spCol = Integer.valueOf(startPosition.split("-")[0]);
            int epRow = Integer.valueOf(endPosition.split("-")[1]);
            int epCol = Integer.valueOf(endPosition.split("-")[0]);
            int len = 1;
            boolean vertical = direction.equals("UPDOWN");
            if (vertical) len = epRow - spRow; // length could be negative, that's ok.
            else len = epCol - spCol;

            if (len < 0) {
                int t;
                t = spRow;
                spRow = epRow;
                epRow = t;
                t = spCol;
                spCol = epCol;
                epCol = t;
                len = -len;
            }
            len++;

            int w = vertical ? 1 : len, h = vertical ? len : 1;

            blks.add(new Block(spCol, spRow, w, h, "" + w + "by" + h, color));
        }

        blocks = blks.toArray(blocks);
    }

    static void rebuildOccupancy() {
        // Initial empty board
        for (int i = 0; i < Board.W; i++) {
            for (int j = 0; j < Board.H; j++) {
                occupied[i][j] = false;
            }
        }
        // Build occupancy array
        for (Block block : blocks) {
            Rectangle rect = block.getBounds();
            int ulx = rect.x / Unit.W;
            int uly = rect.y / Unit.H;
            int w = rect.width / Unit.W;
            int h = rect.height / Unit.H;
            for (int i = ulx; i < ulx + w; i++) {
                for (int j = uly; j < uly + h; j++) {
                    occupied[i][j] = true;
                }
            }
        }
    }
}
