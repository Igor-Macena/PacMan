package pacman;

import java.awt.Graphics;

/**
 *
 * @author Igor
 */
public abstract class PieceShape {
    public int width;
    public int height;
    public abstract void draw(Graphics g);
}
