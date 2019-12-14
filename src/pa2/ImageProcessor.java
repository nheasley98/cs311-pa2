package PA2;

import api.Tuple;
import api.Picture;
import api.ImageStitch;


/**
 * @author Mark Schmidt-Dannert and Noah Heasley
**/
public class ImageProcessor {

	/**
	 * Your  method must use  the  algorithm  described  earlier to  reduce  the  image  width.
	 * And  your method must use the static methods from the
	 * class MatrixCuts to compute the width cut and reduce the width.  Otherwise, you will receive zero credit.
	 *
	 * @param x
	 * @param inputImage is the nameof image whose width will be reduced.
	 * @return Picture with width W - param x
	 */

	/**
	 * Now,  coming  back  to  our  problem  of  reducing  the  image  width,  we  will  apply  the  following
	 * procedure to reduce width from W to W-1.  Given an image whose pixel matrix isM,•Compute a Matrix named I,
	 * whereI[i, j] =Importance(M[i, j]).•Compute Min-Cost width cut ofI.  Let it be〈0, y0〉,〈1, y1〉,···,〈H−1, yH−1〉.
	 * For every i, remove the pixelM[i, yi] from the image.
	 * Now the width of the image isW−1.To reduce the width fromWtoW−k, repeat the above procedurektimes.
	 */
	static Picture reduceWidth(int x, String inputImage) {
		Picture pic = new Picture(inputImage);

		Color[][] colors = new Color[pic.height()][pic.width()];
		for (int i = 0; i < pic.height(); i++) {
			for (int j = 0; j < pic.width(); j++) {
				colors[i][j] = pic.get(j, i);
			}
		}

		for(int iteration = 0; iteration < x; iteration++) {
			int height = colors.length;
			int width = colors[0].length;

			int[][] importance = new int[height][width];

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (j == 0) {
						importance[i][j] = dist(colors[i][j], colors[i][j + 1]);
					} else if (j == colors[0].length - 1) {
						//mportance(M[i, j]) =Dist(M[i, j]M[i, j−1]).
						importance[i][j] = dist(colors[i][j], colors[i][j - 1]);
					} else {
						//importance(M[i, j]) =Dist(M[i, j−1], M[i, j+ 1]),
						importance[i][j] = dist(colors[i][j - 1], colors[i][j + 1]);
					}

				}

			}

			ArrayList<Tuple> tuples = MatrixCuts.widthCut(importance);

			Color[][] newColors = new Color[height][width - 1];
			for (int i = 0; i < tuples.size(); i++) {
				for (int j = 0; j < width; j++) {
					if(j < tuples.get(i).getY()) {
						newColors[i][j] = colors[i][j];
					} if (j > tuples.get(i).getY()) {
						newColors[i][j] = colors[i][j - 1];
					}
				}
			}

			colors = newColors;
		}


		Picture result = Picture(pic.width() - x, pic.height());
		//For everyi, remove the pixelM[i, yi] from the image.  Now the width of the image isW−1.
		for (int i = 0; i < result.height(); i++) {
			for (int j = 0; j < result.width(); j++) {
				result.set(j, i, colors[i, j]);
			}
		}

		return result;
	}
	private static int dist(Color x, Color y) {
		return Math.pow(x.getRed() - y.getRed(), 2) + Math.pow(x.getGreen() - y.getGreen(), 2) + Math.pow(x.getBlue() - y.getBlue(), 2);
	}
}