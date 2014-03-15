package com.z2.calculator;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * given expression
 * like 7-12/6
 * computer return value.
 * support precedence.
 * 
 * @author zhouzhou
 *
 */
public class Calculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String input = "7 - 12 / 6";
		System.out.println(evaluate(input));
		System.out.println(postFixEvaluate(input));
		
		input = "1000 - 5 * 6 / 3 * 2  + 1";
		System.out.println(evaluate(input));
		System.out.println(recursiveEval(input));
		System.out.println(postFixEvaluate(input));
		System.out.println(1000 - 5.0 * 6.0 / 3.0 * 2.0  + 1);
		
		input = "1000 - 5 ^ 6 / 3 * 2  + 1";
		System.out.println(evaluate(input));
		
		System.out.println(1000 - Math.pow(5, 6) / 3 * 2  + 1);
		
		input = "( 1000 - 5 * 6 ) / 3 * 2  + 1";
		System.out.println(evaluate(input));
		System.out.println(recursiveEval(input));
		System.out.println(postFixEvaluate(input));
		System.out.println((1000 - 5.0 * 6.0) / 3.0 * 2.0  + 1);
		
		
		input = "-3 - 4.3 + 5.05 * ( 2 / ( 4 - 4.10 ) / 5 ) + 3";
		System.out.println(evaluate(input));
		System.out.println(recursiveEval(input));
		System.out.println(postFixEvaluate(input));
		System.out.println(-3 - 4.3 + 5.05 * ( 2 / ( 4 - 4.10 ) / 5 ) + 3);
		
	}
	
	public static int getPrecedence(Character c){
		switch(c){
		case '+':
		case '-':
			return 3;
		case '*':
		case '/':
			return 4;
		case '^':
			return 5;
		case '$':
			return 0;
		case '(':
			return 1;//left parenthese 's precedence is lower than right.
			//so it won't be force evaluated. but pop off automatically.
		case ')':
			return 2;
		default:
			return 0;
		}
	}
	
	public static Pattern opPattern = Pattern.compile("[-+*/^\\(\\)\\$]");
	/**
	 * use Dijakastra's two stack algorithm.
	 * assume the expression has space as token separator.
	 * add end symbol to trigger final evaluation.
	 * @return
	 */
	public static double evaluate(String input){
		Stack<Double> operands = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();
		
		Scanner scanner = new Scanner(input + " $");
		while(true){
			if(scanner.hasNextDouble()){
				operands.push(scanner.nextDouble());
			}else if(scanner.hasNext(opPattern)){
			
				char opChar = scanner.next(opPattern).charAt(0);
				while(!operators.isEmpty() && opChar != '(' && 
						getPrecedence(opChar) <= getPrecedence(operators.peek())){//as long as the precedence is bigger or equal.
					char op = operators.pop();
					double b = operands.pop();
					double a = operands.pop();
					operands.push(eval(op, a, b));
				}
				if(opChar == '$'){
					break; //done here.
				}
				if(opChar == ')'){
					char op = operators.pop();
					assert( op == '(');
					continue;
				}
				operators.push(opChar);
			}else{
				System.out.println("["+scanner.next() + "]");
			}
		}
		assert(operators.isEmpty());
		return operands.pop();
		
	}
	
	public static double eval(Character op, double a, double b){
		switch(op){
		case '-':
			return a - b;
		case '+':
			return a + b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		case '^':
			return Math.pow(a, b);
		default:
			throw new IllegalArgumentException("invalid operator");
		}
	}
	
	/**
	 * recursive descend parser.
	 * top down parer build from a set of mutally recursive procedure each procedure the implements one of the production rules of the grammar.
	 * structure of the program missor the grammar.
	 * predictive parser is a recursive descent parser that doesn't require backtracking.
	 * is possible for the class of LL(K) grammars. which are context-free programmer for which there exisits some posibve k allow rdp to decide wihch production urle to use 
	 * by examining next k token of input.
	 * CFG context free grammar
	 * grammer is considered as context free when production rules can be apply regardless of the context of the an non-terminal.
	 * another name BNF.
	 * V- > W V nonterminal symbol.
	 * terminal symbos and non terminal proeuction rules.
	 * terminal are quoted. | alternative
	 * finaihed by semicolon.
	 * 
	 * W a string of terminals or and or non terminals.
	 * summand separated by =-
	 * factor separated by * or /
	 * and finally atom the number.
	 * @param input
	 * @return
	 */
	
	public static double recursiveEval(String input){
		Scanner scan = new Scanner(input);
		return add(scan);
	}
	
	public static double add(Scanner scan) {
		double res = mul(scan);
		while (scan.hasNext("[+-]")) {
			if (scan.next().charAt(0) == '+') {
				res += mul(scan);
			} else {
				res -= mul(scan);
			}
		}
		return res;
	}

	public static double mul(Scanner scan) {
		double res = num(scan);
		while (scan.hasNext("[*/]")) {
			if (scan.next().charAt(0) == '*') {
				res *= num(scan);
			} else {
				res /= num(scan);
			}
		}
		return res;
	}
	
	private static int bracketCount = 0;
	public static double num(Scanner scan){
		if(scan.hasNextDouble()){
			return scan.nextDouble();
		}else if(scan.hasNext("[\\(\\)]")){
			if(scan.next().charAt(0) == '('){
				bracketCount ++ ;
				double val =  add(scan);
				if(scan.next().charAt(0) == ')'){
					bracketCount--;
				}else{
					throw new IllegalArgumentException("invalid input, missing bracket!");
				}
				return val;
			}
		}
		throw new IllegalArgumentException("invalid input");
	}
	
	/**
	 * convert infix to post polish expression
	 * operators follows operands.
	 * shunting (branching) yard algorithm by Dijkastra 
	 * http://en.wikipedia.org/wiki/Shunting-yard_algorithm
	 * @param input
	 * @return
	 */
	public static String infixToPostfix(String input){
		Scanner scan = new Scanner(input + " $");
		Stack<Character> operators = new Stack<Character>();
		StringBuilder sb = new StringBuilder();
		while(true){
			if(scan.hasNextDouble()){
				sb.append(scan.nextDouble() + " ");
			}else if(scan.hasNext("[+-/*\\(\\)\\$]")){
				char op = scan.next().charAt(0);
				while(!operators.isEmpty() && op != '(' && getPrecedence(op) <= getPrecedence(operators.peek())){
					sb.append(operators.pop() + " ");
				}//since ')' is lower than most of operators in terms of precedence.
				//here we guarantee all the operators enclosed in parentheses are output to the buffer.
				if(op == '$'){
					break;
				}
				if(op == ')'){
					operators.pop();
					continue;
				}
				operators.push(op);
			}
		}
		
		while(!operators.isEmpty()){
			sb.append(operators.pop() + " " );
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	public static double postFixEvaluate(String input){
		String input1 = infixToPostfix(input);
		System.out.println("Post fix expression: " + input1);
		
		
		Stack<Double> comp = new Stack<Double>(); //computation stack.
		Scanner scan = new Scanner(input1 + " $");
		while(true){
			if(scan.hasNextDouble()){
				comp.push(scan.nextDouble());
			}else if(scan.hasNext(opPattern)){
				char op = scan.next().charAt(0);
				if(op == '$'){
					break;
				}
				if(comp.size() < 2){
					throw new RuntimeException("invalid expression evaluation");
				}
				double b = comp.pop();
				double a = comp.pop();
				comp.push(eval(op, a, b));
			}
			
		}
		if(comp.size() == 1)
			return comp.pop();
		else
			throw new RuntimeException("invalid expression evaluation");
	}

}
