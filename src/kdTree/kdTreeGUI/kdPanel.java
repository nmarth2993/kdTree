/*
 * Nicholas Marthinuss
 * k-d tree panel
 * 12/31/2021
 */

package kdTree.kdTreeGUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import kdTree.Node;
import kdTree.kdTree;

public class kdPanel extends JPanel {

	public static final int CORNER_PADDING = 50;

	public static final int POINT_DIAMETER = 5;

	// right now, the visual part will only support integers
	// because generic arithmetic is a hassle and must go through
	// the BigDecimal class
	kdTree<Integer> tree;

	Integer maxValueX;
	Integer maxValueY;

	Integer absoluteMax;

	Integer xRange;
	Integer yRange;

	public kdPanel(kdTree<Integer> tree) {
		this.tree = tree;

		maxValueX = findMaxX();
		maxValueY = findMaxY();

		xRange = findRangeX();
		yRange = findRangeY();

		// keep track of the absolute max to save the calculation in repaint
		absoluteMax = maxValueX > maxValueY ? maxValueX : maxValueY;

		System.out.println(
				"found maxX: " + maxValueX + "; maxY: " + maxValueY + "; xRange: " + xRange + "; yRange: " + yRange);

	}

	private Integer findMaxX() {
		return tree.findMaximum(tree.getHead(), true).getX();
	}

	private Integer findMaxY() {
		return tree.findMaximum(tree.getHead(), false).getY();
	}

	private Integer findRangeX() {
		return maxValueX - tree.findMinimum(tree.getHead(), true).getX();
	}

	private Integer findRangeY() {
		return maxValueY - tree.findMinimum(tree.getHead(), false).getY();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.fillRect(CORNER_PADDING, getHeight() - CORNER_PADDING, absoluteMax, 20);

		g.setColor(Color.RED);
		for (Node<Integer> node : tree) {
			g.fillOval(node.getX(), node.getY(), POINT_DIAMETER, POINT_DIAMETER);
		}

		// g.fillRect(40, 50, 20, 100);
	}

}
