/*
 * Nicholas Marthinuss
 * k-d visual GUI
 * 12/31/2021
 */

package kdTree.kdTreeGUI;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import kdTree.kdTree;
import kdTree.kdTreeFactory;

public class kdVisual {
	JFrame frame;
	kdPanel panel;

	public kdVisual(kdTree<Integer> tree) {
		frame = new JFrame("kd tree visualization");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 700));
		// add insert/delete/search buttons? -- if I do this I need to add visual steps
		panel = new kdPanel(tree);
		frame.setContentPane(panel);

		frame.setVisible(true);
		frame.pack();

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			kdTree<Integer> tree = kdTreeFactory.createRandIntegerTree(10);
			System.out.println("tree:");
			tree.dump();
			new kdVisual(tree);
		});
	}
}
