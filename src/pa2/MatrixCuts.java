package pa2;

import api.Tuple;
import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;

import java.util.ArrayList;

/**
 * @author Noah Heasley, Mark Schmidt-Dannert
 */
public class MatrixCuts {
	
	/**
	 * Find the min cost width cut and its cost
	 *
	 * @param M The matrix for which to find the min cost width cut
	 * @return An array list of tuples, the first of which being in the form <x, -1> with x representing the cost. The following tuples are the row and column of the path of the width cut
	 */
	static ArrayList<Tuple> widthCut(int[][] M) {
		//TODO
	}
	
	/**
	 * Find the min cost stitch cut
	 * @param M Matrix onto which to apply stitch cut
	 * @return An array list of tuples, the first of which being in the form <x, -1> with x representing the cost. The following tuples are the row and column of the path of the stitch cut
	 */
	static ArrayList<Tuple> stitchCut(int [][] M){
		//TODO
	}
	
}
