package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
	public String evaluate(String statement) {
	    try {
	        if (statement == null || statement.isEmpty()) {
	            return null; // La declaraci�n es nula o vac�a
	        }

	        statement = statement.replaceAll("\\s+", ""); // Eliminar espacios en blanco

	        if (!isValidStatement(statement)) {
	            return null; // La declaraci�n no es v�lida
	        }

	        Stack<Double> values = new Stack<>();
	        Stack<Character> operators = new Stack<>();

	        for (int i = 0; i < statement.length(); i++) {
	            char ch = statement.charAt(i);

	            if (ch == '(') {
	                operators.push(ch);
	            } else if (ch == ')') {
	                while (!operators.isEmpty() && operators.peek() != '(') {
	                    double result = applyOperator(values, operators);
	                    values.push(result);
	                }
	                operators.pop();
	            } else if (isOperator(ch)) {
	                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
	                    double result = applyOperator(values, operators);
	                    values.push(result);
	                }
	                operators.push(ch);
	            } else {
	                StringBuilder sb = new StringBuilder();
	                while (i < statement.length() && (Character.isDigit(statement.charAt(i)) || statement.charAt(i) == '.')) {
	                    sb.append(statement.charAt(i));
	                    i++;
	                }
	                i--;
	                double value = Double.parseDouble(sb.toString());
	                values.push(value);
	            }
	        }

	        while (!operators.isEmpty()) {
	            double result = applyOperator(values, operators);
	            values.push(result);
	        }

	        if (!values.isEmpty()) {
	            double result = values.pop();
	            if (Double.isInfinite(result) || Double.isNaN(result)) {
	                return null; // Divisi�n entre cero
	            } else if (result == (long) result) {
	                return String.valueOf((long) result); // Eliminar decimales si el resultado es un n�mero entero
	            } else {
	                return String.valueOf(result);
	            }
	        }
	    } catch (Exception e) {
	        return null; // Error en la evaluaci�n de la declaraci�n
	    }

	    return null; // Resultado nulo
	}

	/**
	 * Verifica si un car�cter es un operador matem�tico.
	 * @param ch el car�cter a verificar
	 * @return true si el car�cter es un operador, false de lo contrario
	 */
	private boolean isOperator(char ch) {
	    return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	/**
	 * Devuelve la precedencia de un operador.
	 * @param operator el operador a evaluar
	 * @return el nivel de precedencia del operador (1 para + y -, 2 para * y /, 0 para otros)
	 */
	private int precedence(char operator) {
	    if (operator == '+' || operator == '-')
	        return 1;
	    else if (operator == '*' || operator == '/')
	        return 2;
	    else
	        return 0;
	}

	/**
	 * Aplica un operador a los valores de la pila de valores.
	 * @param values    la pila de valores
	 * @param operators la pila de operadores
	 * @return el resultado de la operaci�n
	 */
	private double applyOperator(Stack<Double> values, Stack<Character> operators) {
	    double b = values.pop();
	    double a = values.pop();
	    char operator = operators.pop();

	    switch (operator) {
	        case '+':
	            return a + b;
	        case '-':
	            return a - b;
	        case '*':
	            return a * b;
	        case '/':
	            if (b == 0) {
	                return Double.POSITIVE_INFINITY; // Divisi�n entre cero
	            } else {
	                return a / b;
	            }
	        default:
	            return 0.0;
	    }
	}

	/**
	 * Verifica si una declaraci�n matem�tica es v�lida.
	 * @param statement la declaraci�n matem�tica a verificar
	 * @return true si la declaraci�n es v�lida, false de lo contrario
	 */
	private boolean isValidStatement(String statement) {
	    int parenthesesCount = 0;
	    char prev = '\0';

	    for (char ch : statement.toCharArray()) {
	        if (!Character.isDigit(ch) && ch != '.' && ch != '(' && ch != ')' && !isOperator(ch)) {
	            return false; // Car�cter no v�lido en la declaraci�n
	        }

	        if (ch == '(') {
	            parenthesesCount++;
	        } else if (ch == ')') {
	            parenthesesCount--;
	            if (parenthesesCount < 0) {
	                return false; // Par�ntesis desequilibrados
	            }
	        }

	        if (isOperator(ch) && isOperator(prev)) {
	            return false; // Dos operadores consecutivos
	        }

	        prev = ch;
	    }

	    return parenthesesCount == 0; // Par�ntesis equilibrados
	}
}
