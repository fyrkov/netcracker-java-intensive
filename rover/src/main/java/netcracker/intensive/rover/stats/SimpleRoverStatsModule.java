package netcracker.intensive.rover.stats;

import netcracker.intensive.rover.Point;

import java.util.Collection;
import java.util.HashSet;

public class SimpleRoverStatsModule implements RoverStatsModule {

    private Collection<Point> visitedPoints;

    public SimpleRoverStatsModule() {
        visitedPoints = new HashSet<>();
    }

    @Override
    public void registerPosition(Point position) {
        visitedPoints.add(position);
    }

    @Override
    public boolean isVisited(Point point) {
        return visitedPoints.contains(point);
    }

    @Override
    public Collection<Point> getVisitedPoints() {
        return visitedPoints;
    }
}
