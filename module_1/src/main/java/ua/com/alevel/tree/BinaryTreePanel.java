package ua.com.alevel.tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class BinaryTreePanel extends JPanel implements ActionListener {
    public static JFrame BinaryTreeFrame;
    private final BinaryTree tree;
    private final HashMap<Node, Rectangle> nodeLocations;
    private final HashMap<Node, Dimension> subtreeSizes;
    private boolean dirty = true;
    private final int parentToChild = 10, childToChild = 20;
    private final Dimension empty = new Dimension(0, 0);
    private FontMetrics fontMetric = null;

    public BinaryTreePanel(BinaryTree tree) {
        this.tree = tree;
        nodeLocations = new HashMap<>();
        subtreeSizes = new HashMap<>();
        registerKeyboardAction(this, "add", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(this, "search", KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(this, "delete", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(this, "help", KeyStroke.getKeyStroke(KeyEvent.VK_H, 0), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(this, "depth", KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), WHEN_IN_FOCUSED_WINDOW);
    }

    public void actionPerformed(ActionEvent e) {
        String input;
        int inputNumber;
        if (e.getActionCommand().equals("add")) {
            input = JOptionPane.showInputDialog("Add an integer number:");
            try {
                inputNumber = Integer.parseInt(input);
                tree.addNode(inputNumber);
                dirty = true;
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(BinaryTreeFrame, "Please, write an integer number");
            }
        }
        if (e.getActionCommand().equals("delete")) {
            input = JOptionPane.showInputDialog("Delete an integer number:");
            try {
                inputNumber = Integer.parseInt(input);
                tree.deleteNode(inputNumber, tree.getRoot());
                dirty = true;
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(BinaryTreeFrame, "Please, write an integer number");
            }
        }
        if (e.getActionCommand().equals("search")) {
            input = JOptionPane.showInputDialog("Search an integer number:");
            try {
                inputNumber = Integer.parseInt(input);
                Node aux = tree.searchNode(inputNumber, tree.getRoot());
                if (aux == null)
                    JOptionPane.showMessageDialog(BinaryTreeFrame, "The number " + inputNumber + " was not found");
                else
                    JOptionPane.showMessageDialog(BinaryTreeFrame, "The number " + inputNumber + " was found");
                dirty = true;
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(BinaryTreeFrame, "Please, write an integer number");
            }
        }

        if (e.getActionCommand().equals("help")) {
            JOptionPane.showMessageDialog(BinaryTreeFrame, """
                    The operations you can use are:
                     a --- Add an integer number
                     s --- Search an integer number
                     d --- Delete an integer number
                     h --- Help (if you forgot this)
                     g --- Get max depth""");
        }

        if (e.getActionCommand().equals("depth")) {
            try {
                int depth = tree.getMaxTreeDepth();
                JOptionPane.showMessageDialog(BinaryTreeFrame, "Maximum tree depth: " + depth);
                dirty = true;
                repaint();
            } catch (NumberFormatException z) {
                JOptionPane.showMessageDialog(BinaryTreeFrame, "Error. Tree not found");
            }
        }
    }

    private void calculateLocations() {
        nodeLocations.clear();
        subtreeSizes.clear();
        Node root = tree.getRoot();
        if (root != null) {
            calculateSubtreeSize(root);
            calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    private Dimension calculateSubtreeSize(Node node) {
        if (node == null) return new Dimension(0, 0);
        String content = Integer.toString(node.getKey());
        Dimension leftDimension = calculateSubtreeSize(node.getLeft());
        Dimension rightDimension = calculateSubtreeSize(node.getRight());
        int height = fontMetric.getHeight() + parentToChild + Math.max(leftDimension.height, rightDimension.height);
        int width = leftDimension.width + childToChild + rightDimension.width;
        Dimension dimension = new Dimension(width, height);
        subtreeSizes.put(node, dimension);
        return dimension;
    }

    private void calculateLocation(Node node, int left, int right, int top) {
        if (node == null) return;
        Dimension leftDimension = subtreeSizes.get(node.getLeft());
        if (leftDimension == null) leftDimension = empty;
        Dimension rightDimension = subtreeSizes.get(node.getRight());
        if (rightDimension == null) rightDimension = empty;
        int center = 0;
        if (right != Integer.MAX_VALUE)
            center = right - rightDimension.width - childToChild / 2;
        else if (left != Integer.MAX_VALUE)
            center = left + leftDimension.width + childToChild / 2;
        int width = fontMetric.stringWidth(Integer.toString(node.getKey()));
        Rectangle rectangle = new Rectangle(center - width / 2 - 3, top, width + 6, fontMetric.getHeight());
        nodeLocations.put(node, rectangle);
        calculateLocation(node.getLeft(), Integer.MAX_VALUE, center - childToChild / 2, top + fontMetric.getHeight() + parentToChild);
        calculateLocation(node.getRight(), center + childToChild / 2, Integer.MAX_VALUE, top + fontMetric.getHeight() + parentToChild);
    }

    private void drawTree(Graphics2D graphics2D, Node node, int px, int py, int yoffs) {
        if (node == null) return;
        Rectangle rectangle = nodeLocations.get(node);
        graphics2D.draw(rectangle);
        graphics2D.drawString(Integer.toString(node.getKey()), rectangle.x + 3, rectangle.y + yoffs);
        if (px != Integer.MAX_VALUE)
            graphics2D.drawLine(px, py, rectangle.x + rectangle.width / 2, rectangle.y);
        drawTree(graphics2D, node.getLeft(), rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height, yoffs);
        drawTree(graphics2D, node.getRight(), rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height, yoffs);
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        fontMetric = graphics.getFontMetrics();
        if (dirty) {
            calculateLocations();
            dirty = false;
        }
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.translate(getWidth() / 2, parentToChild);
        drawTree(graphics2D, tree.getRoot(), Integer.MAX_VALUE, Integer.MAX_VALUE, fontMetric.getLeading() + fontMetric.getAscent());
        fontMetric = null;
    }
}
