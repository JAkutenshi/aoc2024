import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        int answer = readMaze("day6-input.txt").solve();

        System.out.println(answer);
    }

    private static Maze readMaze(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename));

        char[][] maze = new char[130][130];

        int yCoord = 0;

        Maze.Coords startCoords = null;

        while (input.hasNextLine()) {
            char[] lineChars = input.nextLine().toCharArray();

            for (int xCoord = 0; xCoord < lineChars.length; xCoord++) {
                if (lineChars[xCoord] == '^') {
                    startCoords = new Maze.Coords(xCoord, yCoord);
                }
            }

            assert lineChars.length == 130;

            maze[yCoord++] = lineChars;
        }

        assert yCoord == 130;
        assert startCoords != null;

        return new Maze(maze, startCoords);
    }

    static class Maze {
        static class Coords {
            public Coords(int x, int y) {
                this.x = x;
                this.y = y;
            }

            int x;
            int y;
        }

        enum Direction { NORTH, EAST, SOUTH, WEST }

        enum CellType { EMPTY, OBSTACLE, QUIT }

        char[][] maze;
        Coords currentCoords;
        Direction direction;

        public Maze(char[][] maze, Coords startCoords) {
            this.maze = maze;
            this.currentCoords = startCoords;
            this.direction = Direction.NORTH;
        }

        int solve() {
            final int maxSteps = 130 * 130;

            int stepsCount = 0;

            while (true) {
                switch (forwardCellType()) {
                    case OBSTACLE:
                        turnRight();
                        continue;
                    case QUIT:
                        return isCurrentCellVisited() ? stepsCount : stepsCount + 1;
                    case EMPTY:
                        stepsCount += doStep() ? 0 : 1;
                        break;
                }

                assert stepsCount < maxSteps;
            }
        }

        private boolean doStep() {
            boolean isAlreadyVisited = isCurrentCellVisited();

            if (!isAlreadyVisited) {
                maze[currentCoords.y][currentCoords.x] = 'X';
            }

            currentCoords = getForwardCell();

            return isAlreadyVisited;
        }

        private boolean isCurrentCellVisited() {
            return maze[currentCoords.y][currentCoords.x] == 'X';
        }

        private void turnRight() {
            direction = switch (direction) {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };
        }

        private CellType forwardCellType() {
            Coords forwardCellCoords = getForwardCell();

            if (forwardCellCoords.x < 0 || forwardCellCoords.x >= 130 ||
                forwardCellCoords.y < 0 || forwardCellCoords.y >= 130) {
                return CellType.QUIT;
            }

            if (maze[forwardCellCoords.y][forwardCellCoords.x] == '#') {
                return CellType.OBSTACLE;
            }

            return CellType.EMPTY;
        }

        private Coords getForwardCell() {
            return switch (direction) {
                case NORTH -> new Coords(currentCoords.x, currentCoords.y - 1);
                case EAST -> new Coords(currentCoords.x + 1, currentCoords.y);
                case SOUTH -> new Coords(currentCoords.x, currentCoords.y + 1);
                case WEST -> new Coords(currentCoords.x - 1, currentCoords.y);
            };
        }

        @Override
        public String toString() {
            StringBuilder out = new StringBuilder();
            for (int yCoord = 0; yCoord < 130; yCoord++) {
                out.append(maze[yCoord]).append('\n');
            }
            return out.toString();
        }
    }
}
