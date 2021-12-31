package kdTree.kdTreeGUI;

import java.awt.Graphics;

import javax.swing.JPanel;
// 

import kdTree.kdTree;

public class kdPanel extends JPanel {

	// right now, the visual part will only support integers
	// because generic arithmetic is a hassle and must go through
	// the BigDecimal class
	kdTree<Integer> tree;

	Integer maxValueX;
	Integer maxValueY;

	Integer xRange;
	Integer yRange;

	public kdPanel(kdTree<Integer> tree) {
		this.tree = tree;

		maxValueX = findMaxX();
		maxValueY = findMaxY();

		xRange = findRangeX();
		yRange = findRangeY();

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

		g.fillRect(40, 50, 20, 100);
	}

}
