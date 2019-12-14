package pa2;

import api.Tuple;
import com.sun.org.apache.xalan.internal.xsltc.dom.ArrayNodeListIterator;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.*;

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
		if( M.length == 0 || M[0].length == 0){
			return null;
		}
		data[][] costs = new data[M.length][M[0].length];
		
		for(int i = 0; i < M.length; i++){
			for(int j = 0; j<M[i].length; j++){
				
				if(i == 0){
					
					costs[i][j] = new data(null, M[i][j], i, j);
					continue;
					
				}
				
				data[] row = costs[min(max(0, i - 1), M.length-1)];
				
				costs[i][j] = row[min(max(0,j-1),M[0].length-1)].val<((row[j].val< row[min(max(0,j+1),M[0].length-1)].val)? row[min(max(0,j),M[0].length-1)].val: row[min(max(0,j+1),M[0].length-1)].val)?(new data(row[min(max(0,j-1),M[0].length-1)], row[min(max(0,j-1),M[0].length-1)].val+M[i][j], i, j)):((row[j].val< row[min(max(0,j+1),M[0].length-1)].val)?(new data(row[j], row[j].val+M[i][j],i ,j)):(new data(row[min(max(0,j+1),M[0].length-1)], row[min(max(0,j+1),M[0].length-1)].val+M[i][j], i, j)));
			}
		}
		
		data lowest = costs[costs.length-1][costs[costs.length-1].length-1];
		
		for(int i = 1; i<M.length; i++){
			lowest = lowest.val<=costs[costs.length-1][i].val?lowest:costs[costs.length-1][i];
		}
		
		ArrayList<Tuple> out = new ArrayList<Tuple>();
		
		int cost = lowest.val;
		
		while(lowest != null){
			out.add(lowest.pos);
			lowest=lowest.previous;
		}
		
		out.add(new Tuple(cost, -1));
		
		Collections.reverse(out);
		
		return out;
	}
	
	/**
	 * Find the min cost stitch cut
	 * @param M Matrix onto which to apply stitch cut
	 * @return An array list of tuples, the first of which being in the form <x, -1> with x representing the cost. The following tuples are the row and column of the path of the stitch cut
	 */
	static ArrayList<Tuple> stitchCut(int [][] M){
		if( M.length == 0 || M[0].length == 0){
			return null;
		}
		data[][] costs = new data[M.length][M[0].length];
		
		for(int i = 0; i < M.length; i++){
			done:
			for(int j = 0; j<M[i].length; j++){
				
				if(i == 0){
					
					costs[i][j] = new data(null, M[i][j], i, j);
					continue;
					
				}
				
				if(j == 0){
					
					costs[i][j] = new data(costs[i-1][j],costs[i-1][j].val+M[i][j], i, j);
					continue done;
					
				}
				
				costs[i][j] = (costs[i][max(j-1,0)].val<(costs[max(i-1, 0)][max(j-1, 0)].val<costs[max(i-1,0)][j].val?costs[max(i-1, 0)][max(j-1, 0)].val:costs[max(i-1,0)][j].val))?(new data(costs[i][max(j-1,0)], costs[i][max(j-1,0)].val + M[i][j], i, j)):((costs[max(i-1, 0)][max(j-1, 0)].val<costs[max(i-1,0)][j].val)?new data(costs[max(i-1, 0)][max(j-1, 0)],costs[max(i-1, 0)][min(j-1, 0)].val + M[i][j], i, j):new data(costs[max(i-1,0)][j], costs[max(i-1,0)][j].val + M[i][j], i, j));
				
				}
		}
		
		data lowest = costs[costs.length-1][costs[costs.length-1].length-1];
		
		for(int i = 1; i<M.length; i++){
			lowest = lowest.val<=costs[costs.length-1][i].val?lowest:costs[costs.length-1][i];
		}
		
		ArrayList<Tuple> out = new ArrayList<Tuple>();
		
		int cost = lowest.val;
		
		while(lowest != null){
			out.add(lowest.pos);
			lowest=lowest.previous;
		}
		
		out.add(new Tuple(cost, -1));
		
		Collections.reverse(out);
		
		return out;
	}
	
}

class data{
	
	public data previous;
	Tuple pos;
	public int val;
	
	public data(data d, int val, int x, int y){
		previous = d;
		this.val = val;
		pos = new Tuple(x, y);
	}
	
}
