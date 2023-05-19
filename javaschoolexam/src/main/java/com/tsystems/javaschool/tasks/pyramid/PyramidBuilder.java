package com.tsystems.javaschool.tasks.pyramid;

import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
	public int[][] buildPyramid(List<Integer> inputNumbers) throws CannotBuildPyramidException {
        // Verificar si el número de elementos es válido para construir una pirámide
	    if (inputNumbers.contains(null)) {
	        throw new CannotBuildPyramidException("Input contains null values, cannot build pyramid."); // Si no lo es, lanza la excepción creada
	    }

	    int size = inputNumbers.size();
	    int levels = calculateLevels(size);

	    if (levels * (levels + 1) / 2 != size) {
	        throw new CannotBuildPyramidException("Input size does not form a valid pyramid shape, cannot build pyramid.");
	    }

	    // Ordenar los números de entrada
	    Collections.sort(inputNumbers);

	    int[][] pyramid = new int[levels][2 * levels - 1];
	    int currentIndex = 0;

	    for (int i = 0; i < levels; i++) {
	        int startColumn = levels - i - 1;
	        int endColumn = levels + i - 1;

	        for (int j = startColumn; j <= endColumn; j += 2) {
	            pyramid[i][j] = inputNumbers.get(currentIndex++);
	        }
	    }

	    return pyramid;
	}

	private int calculateLevels(int size) {
	    int levels = 0;
	    int count = 0;

	    while (count < size) {
	        levels++;
	        count += levels;
	    }

	    return levels;
	}



}
