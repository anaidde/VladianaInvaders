package ro.uaic.info.game.objects.player;

import ro.uaic.info.game.objects.ship.Ship;

import java.awt.*;

public class Player {
    private Ship ship;
    private Rectangle mesh;
    private Point coordinates;
    private Dimension dimension;

    public void update() {

    }

    public void draw(Graphics graphics) {

    }

    public static class PlayerBuilder {
        private Ship ship ;
        private Rectangle mesh;
        private Point coordinates = new Point(0, 0); // instantiate default for all
        private Dimension dimension = new Dimension(0,0);

        public PlayerBuilder withShip(Ship ship) {
            this.ship = ship;
            return this;
        }

        public PlayerBuilder withMesh(Rectangle mesh) {
            this.mesh = mesh;
            return this;
        }

        public PlayerBuilder withCoordinates(Point coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public PlayerBuilder withDimension(Dimension dimension) {
            this.dimension = dimension;
            return this;
        }

        public Player build() {
            Player player = new Player();

            player.ship = this.ship;
            player.mesh = this.mesh;
            player.coordinates = this.coordinates;
            player.dimension = this.dimension;

            return player;
        }


    }

    public Ship getShip() {
        return ship;
    }

    public Rectangle getMesh() {
        return mesh;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
