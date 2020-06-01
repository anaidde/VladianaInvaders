package ro.uaic.info.game.objects.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInputListener implements KeyListener {

    @Override
    public String toString() {
        return "PlayerInputListener{" +
                "up=" + up +
                ", down=" + down +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public boolean pressedUp() {
        return up;
    }

    public boolean pressedDown() {
        return down;
    }

    public boolean pressedLeft() {
        return left;
    }

    public boolean pressedRight() {
        return right;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w' :
                this.up = true;
                break;
            case 's' :
                this.down = true;
                break;
            case 'd' :
                this.right = true;
                break;
            case 'a' :
                this.left = true;
                break;
        }

        switch (e.getKeyCode()){
            case 37 :
                this.left = true;
                break;
            case 38 :
                this.up = true;
                break;
            case 39 :
                this.right = true;
                break;
            case 40 :
                this.down = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'w' :
                this.up = false;
                break;
            case 's' :
                this.down = false;
                break;
            case 'd' :
                this.right = false;
                break;
            case 'a' :
                this.left = false;
                break;
        }

        switch (e.getKeyCode()){
            case 37 :
                this.left = false;
                break;
            case 38 :
                this.up = false;
                break;
            case 39 :
                this.right = false;
                break;
            case 40 :
                this.down = false;
                break;
        }
    }
}
