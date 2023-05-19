package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
	@SuppressWarnings("rawtypes")
	public boolean find(List x, List y) {
		// Comprobamos si alguna lista es nula
	    if (x == null || y == null) {
	        throw new IllegalArgumentException("Las listas no pueden ser nulas");
	    }

	    // Si la lista x est� vac�a, se considera una subsecuencia v�lida
	    if (x.isEmpty()) {
	        return true;
	    }

	    // �ndice auxiliares para recorrer las listas
	    int i = 0;
	    int j = 0;

	    while (j < y.size()) {
	        if (x.get(i).equals(y.get(j))) {
	            i++;
	            if (i == x.size()) {
	                return true; // Se encontr� una subsecuencia completa
	            }
	        }
	        j++;
	    }

	    // No se encontr� una subsecuencia completa
	    return false;
	}



}
