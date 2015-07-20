package niit.summer.rover;

import org.junit.Test;

import static niit.summer.rover.constants.CellState.FREE;
import static niit.summer.rover.constants.CellState.OCCUPIED;

public class GroundTest extends AbstractRoverTest {

    @Test
    public void testInitializeSquareGround() throws OutOfGroundException {
        assertSquareGround(SQUARE_GROUND);
    }

    @Test
    public void testInitializeOnExcessiveParametersCount() throws OutOfGroundException {
        Ground ground = new Ground(2, 2);

        ground.initialize(
                new GroundCell(FREE), new GroundCell(FREE),
                new GroundCell(OCCUPIED), new GroundCell(OCCUPIED),
                new GroundCell(FREE)
        );

        assertSquareGround(ground);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializeFailsOnInsufficientParameters() {
        Ground ground = new Ground(2, 2);

        ground.initialize(
                new GroundCell(FREE), new GroundCell(FREE),
                new GroundCell(OCCUPIED)
        );
    }

    @Test(expected = OutOfGroundException.class)
    public void testGetCellThrowsExceptionOnExcessiveWidth() throws Exception {
        SQUARE_GROUND.getCell(5, 1);
    }

    @Test(expected = OutOfGroundException.class)
    public void testGetCellThrowsExceptionOnExcessiveLength() throws Exception {
        SQUARE_GROUND.getCell(1, 5);
    }

    @Test(expected = OutOfGroundException.class)
    public void testGetCellThrowsExceptionOnNegativeX() throws Exception {
        SQUARE_GROUND.getCell(-5, 1);
    }

    @Test(expected = OutOfGroundException.class)
    public void testGetCellThrowsExceptionOnNegativeY() throws Exception {
        SQUARE_GROUND.getCell(1, -5);
    }
}