import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

// Define a Block with specific Unit and image
public class Block extends JPanel implements MouseListener, MouseMotionListener {
    static final int X_AXIS = 0, Y_AXIS = 1; // Define direction for moving
    int movingDirection = -1;
    Point startingPoint = new Point();
    Point previousPoint = new Point();
    BufferedImage image = null;
    int w, h; //* CHANGED
    private String color;

    public Block(int x, int y, int w, int h, String type, String color) {
        this.w = w; //* CHANGED
        this.h = h; //* CHANGED
        this.color = color;
        JPanel panel = this;
        panel.setLayout(null);
        image = getImg(type, color);
        image = toBufferedImage(image.getScaledInstance(w * Unit.W, h * Unit.H, Image.SCALE_SMOOTH));
        JLabel picLabel = new JLabel(new ImageIcon(image));
        panel.add(picLabel);
        Insets insets = panel.getInsets();
        picLabel.setBounds(insets.left, insets.top, w * Unit.W, h * Unit.H);
        panel.setBorder(BorderFactory.createRaisedBevelBorder());
        panel.setBounds(x * Unit.W, y * Unit.H, w * Unit.W, h * Unit.H);

        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }

    private BufferedImage flipImg(BufferedImage img) {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getTranslateInstance(img.getHeight()/2, img.getWidth()/2));
        at.concatenate(AffineTransform.getRotateInstance(Math.PI/2));
        at.concatenate(AffineTransform.getTranslateInstance(-img.getWidth()/2, -img.getHeight()/2));
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -img.getHeight()));
        BufferedImage newImage = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return newImage;
    }

    // Get image for specific block
    private BufferedImage getImg(String type, String color) {
        String img = null;
        BufferedImage myImage = null;
        boolean flip = false;
        switch (color) {
            case "LightBlue":
                switch (type) {
                    case "1by2":
                        img = "blue.jpg";
                        flip = true;
                        break;
                    case "2by1":
                        img = "blue.jpg";
                        break;
                }
                break;
            case "DarkBlue":
                switch (type) {
                    case "1by3":
                        img = "blue3.jpg";
                        break;
                    case "3by1":
                        img = "blue3.jpg";
                        flip = true;
                        break;
                }
                break;
            case "Green":
                switch (type) {
                    case "1by2":
                        img = "green2.jpg";
                        flip = true;
                        break;
                    case "2by1":
                        img = "green2.jpg";
                        break;
                }
                break;
            case "Teal":
                switch (type) {
                    case "1by3":
                        img = "teal3.jpg";
                        flip = true;
                        break;
                    case "3by1":
                        img = "teal3.jpg";
                        break;
                }
                break;
            case "Orange":
                switch (type) {
                    case "1by2":
                        img = "orange2.jpg";
                        break;
                    case "2by1":
                        img = "orange2.jpg";
                        flip = true;
                        break;
                }
                break;
            case "Red":
                switch (type) {
                    case "1by2":
                        img = "red2.jpg";
                        flip = true;
                        break;
                    case "2by1":
                        img = "red2.jpg";
                        break;
                }
                break;
            case "DarkGreen":
                switch (type) {
                    case "1by2":
                        img = "darkgreen2.jpg";
                        break;
                    case "2by1":
                        img = "darkgreen2.jpg";
                        flip = true;
                        break;
                }
                break;
            case "Yellow":
                switch (type) {
                    case "1by3":
                        img = "yellow3.jpg";
                        break;
                    case "3by1":
                        img = "yellow3.jpg";
                        flip = true;
                        break;
                }
                break;
            case "Purple":
                switch (type) {
                    case "1by3":
                        img = "purple3.jpg";
                        break;
                    case "3by1":
                        img = "purple3.jpg";
                        flip = true;
                        break;
                }
                break;
            case "DarkPurple":
                switch (type) {
                    case "1by2":
                        img = "darkpurple2.jpg";
                        break;
                    case "2by1":
                        img = "darkpurplr2.jpg";
                        flip = true;
                        break;
                }
                break;
            case "Pink":
                switch (type) {
                    case "1by2":
                        img = "pink2.jpg";
                        break;
                    case "2by1":
                        img = "pink2.jpg";
                        flip = true;
                        break;
                }
                break;
            case "Black":
                switch (type) {
                    case "1by2":
                        img = "black2.jpg";
                        flip = true;
                        break;
                    case "2by1":
                        img = "black2.jpg";
                        break;
                }
                break;

        }
        if (img == null) {
            switch (type) {
                case "1by3":
                    img = "blue3.jpg";
                    break;
                case "1by2":
                    img = "blue.jpg";
                    flip = true;
                    break;
                case "3by1":
                    img = "blue3.jpg";
                    flip = true;
                    break;
                case "2by1":
                    img = "blue.jpg";
                    break;
                default:
                    System.out.println("type: \"" + type + "\"" + ", color: \"" + color + "\"");
                    break;
            }
        }
        System.out.println("img: \"" + img + "\"");
        try {
            myImage = ImageIO.read(Klotski.class.getResourceAsStream(img));
            if (flip) {
                myImage = flipImg(myImage);
            }
        } catch (IOException ex) {
            System.out.println("Could not find the file!");
        }
        return myImage;
    }

    // Convert Image type to BufferedImage type
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage
                (img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }

    @Override // MouseListener
    public void mouseExited(MouseEvent e) {
        // Do nothing
    }

    @Override // MouseListener
    public void mouseEntered(MouseEvent e) {
        // Do nothing
    }

    @Override // MouseListener
    public void mouseReleased(MouseEvent e) {
        int x = this.getX();
        int y = this.getY();
        double xRound = Math.round(x / (double) (Unit.W));
        double yRound = Math.round(y / (double) (Unit.H));
        x = (int) (xRound * Unit.W);
        y = (int) (yRound * Unit.H);

        this.setLocation(x, y);
        BlockList.rebuildOccupancy();

        // Win condition check
        if (this.color.equals("Red") &&
                this.getWidth() == Unit.W * 2 && this.getHeight() == Unit.H
                && this.getX() == Unit.W * 4) {
            for (Block block : BlockList.blocks) {
                block.removeMouseListener(block);
                block.removeMouseMotionListener(block);
            }
            String message = "Congratulations ! Puzzle solved !";
            javax.swing.JOptionPane.showMessageDialog(this, message);
        }

    }

    @Override // MouseListener
    public void mousePressed(MouseEvent e) {
        startingPoint = e.getPoint();
        previousPoint = e.getPoint();
        movingDirection = -1;

        // Remove selected block from the occupancy array
        java.awt.Rectangle rect = this.getBounds();
        int ulx = rect.x / Unit.W;
        int uly = rect.y / Unit.H;
        int w = rect.width / Unit.W;
        int h = rect.height / Unit.H;
        for (int i = ulx; i < ulx + w; i++)
            for (int j = uly; j < uly + h; j++)
                BlockList.occupied[i][j] = false;

    }

    @Override // MouseListener
    public void mouseClicked(MouseEvent e) {
        // Do nothing
    }

    @Override // MouseMotionListener
    public void mouseMoved(MouseEvent e) {
        // Do nothing
    }

    @Override // MouseMotionListener
    public void mouseDragged(MouseEvent e) {
        int dx = e.getX() - previousPoint.x;
        int dy = e.getX() - previousPoint.y;
        previousPoint = e.getPoint();

        dx = e.getX() - startingPoint.x;
        dy = e.getY() - startingPoint.y;

        int x = this.getX();
        int y = this.getY();

        // When movement is > 5 pixels then determining the moving axis
        if (Math.abs(dx) < 5 && Math.abs(dy) < 5) return;

        // Restrict the movement on the same axis until the mouse is released
        if (Math.abs(dx) > Math.abs(dy) && movingDirection != Y_AXIS) {
            for (int i = 0; i < Math.abs(dx); i++)
                if (movable(dx / Math.abs(dx), 0)) {
                    x += dx / Math.abs(dx);
                    movingDirection = X_AXIS;
                    if (w > h) //* CHANGED THIS LINE
                        this.setLocation(x, y);
                }
        }
        if (Math.abs(dy) > Math.abs(dx) && movingDirection != X_AXIS) {
            for (int i = 0; i < Math.abs(dy); i++)
                if (movable(0, dy / Math.abs(dy))) {
                    y += dy / Math.abs(dy);
                    movingDirection = Y_AXIS;
                    if (h > w) //* CHANGED THIS LINE
                        this.setLocation(x, y);
                }
        }

        // Allow to switch between X_AXIS and Y_AXIS direction
        if (this.getX() % Unit.W == 0 && this.getY() % Unit.H == 0) {
            movingDirection = -1;
        }

    }

    boolean movable(int dx, int dy) {
        int x = this.getX();
        int y = this.getY();
        int row = this.getHeight() / Unit.H;
        int column = this.getWidth() / Unit.W;

        x += dx;
        y += dy;

        double xRound = Math.round(x / (double) (Unit.W));
        double yRound = Math.round(y / (double) (Unit.H));
        double xFloor = Math.floor(x / (double) (Unit.W));
        double yFloor = Math.floor(y / (double) (Unit.H));
        double xCeil = Math.ceil(x / (double) (Unit.W));
        double yCeil = Math.ceil(y / (double) (Unit.H));

        // Adjust the position with the floor or the ceiling function
        int newLocationX = (int) xRound;
        int newLocationY = (int) yRound;
        if (dx < 0) newLocationX = (int) xFloor;
        if (dx > 0) newLocationX = (int) xCeil;
        if (dy < 0) newLocationY = (int) yFloor;
        if (dy > 0) newLocationY = (int) yCeil;

        // Not allow block move over board
        if (newLocationX < 0 || newLocationY < 0) return false;
        if (newLocationX + column - 1 >= Board.W || newLocationY + row - 1 >= Board.H) return false;

        // Check its position is not already occupied
        for (int i = 0; i < column; i++)
            for (int j = 0; j < row; j++)
                if (BlockList.occupied[newLocationX + i][newLocationY + j]) return false;

        return true;
    }
}
