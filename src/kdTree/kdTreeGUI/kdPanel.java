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

		// g.fillRect(CORNER_PADDING, getHeight() - CORNER_PADDING, absoluteMax, 20);

		g.setColor(Color.RED);
		for (Node<Integer> node : tree) {
			// g.fillOval(node.getX(), node.getY(), POINT_DIAMETER, POINT_DIAMETER);
			g.drawString(node.toString(), node.getX() * 2, node.getY() * 2);

			// there is a strong sub-tree relation
			// consider the simplest case: a node with two children
			// the left tree is left of the root's partition
			// the right tree is right of the root's partition
			// both children will create partitions in the opposite dimension to the root

			// one consideration is that these partitions must also be drawn keeping in mind
			// a larger "superpartition" that the entire tree may be a part of
			// to achieve this, a max/min x and y must be retained for each subtree
			// the first "superpartition" is the entire plane with respective bounds
		}

		// g.fillRect(40, 50, 20, 100);
	}

}
