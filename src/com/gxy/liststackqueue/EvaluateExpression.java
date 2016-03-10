package com.gxy.liststackqueue;

import java.util.StringTokenizer;

/**
 * 计算表达式
 * 
 * 阶段一： 扫描表达式
 * 程序从左到右扫描表达式，提取操作数、运算符和括号
 * 	   1.1   如果提取的条目是一个操作数，将它压入operandStack
 * 	   1.2   如果提取的条目是一个+或-的运算符， 处理在operatorStack栈顶的所有运算符。将运算符压入operatorStack
 * 	   1.3   如果提取的条目是一个*或/运算符，处理在oepratorStack栈顶的所有运算符*和/，将提取出的运算符压入operatorStack
 *     1.4 如果提取的条目是一个"("符号，将它压入operatorStack
 *     1.5 如果提取的条目是一个")"符号，重复处理来自operatorStack栈顶的运算符，直到看到栈顶的")"符号
 * 阶段二： 清除栈
 * 	重复处理来自operatorStack栈顶的运算符，直到operatorStack为空为止。 
 * 
 * 
 * @author gxy
 * @date 2016年2月4日下午5:15:19
 */
public class EvaluateExpression {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java EvaluateExpression expression");
			System.exit(0);
		}
		String expression = "";
		for (int i = 0; i < args.length; i++) {
			expression += args[i];
		}
		
		try {
			System.out.println(evaluateExpression(expression));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("wroing expression");
		}
	}
	
	/**
	 * evaluate an expression
	 * @param expression
	 * @return
	 */
	public static int evaluateExpression(String expression) {
		//create operandStack to store operands
		GenericStack<Integer> operandStack = new GenericStack<Integer>();
		
		//create operandStack to store operators
		GenericStack<Character> operatorStack = new GenericStack<Character>();
		
		//extract operands and operators
		StringTokenizer tokens = new StringTokenizer(expression, "()+-/*", true);
		
		//Phase 1: Scan tokens
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken().trim();//Extract a token
			System.out.println(token);
			if (token.length() == 0) {//blank space
				continue; //Back to the while loop to extract the next token
			} else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
				//Process all +, -, * , /in the top of the operator stack
				while (!operatorStack.isEmpty() && 
						(operatorStack.peek() == '+' ||
						 operatorStack.peek() == '-' ||
						 operatorStack.peek() == '*' ||
						 operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}
				
				//push the + or - operator into the operator stack
				operatorStack.push(token.charAt(0));
			} else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
				//process all *, / in the top of the operator stack
				while (!operatorStack.isEmpty() &&
					(operatorStack.peek() == '*' ||
					 operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}
				
				// push the * or / operator into the operator stack
				operatorStack.push(token.charAt(0));
			} else if (token.trim().charAt(0) == '(') {
				//push "(" to stack
				operatorStack.push(token.charAt(0));
			} else if (token.trim().charAt(0) == ')') {
				//process all the operators in the stack until seeing
				while (operatorStack.peek() != '(') {
					processAnOperator(operandStack, operatorStack);
				}
				
				operatorStack.pop(); //pop the '(' symbol from the stack
			} else {//an operator scanned
				//push an operand to the stack
				operandStack.push(new Integer(token));
			}
		}
		
		//phase 2 : process all the remaining operators in the stack
		while (!operatorStack.isEmpty()) {
			processAnOperator(operandStack, operatorStack);
		}
		
		//return the result
		return operandStack.pop();
	}

	/**
	 * process one operator: take an operator from operatorStack and
	 *  apply it on the operands in the operandStack
	 * @param operandStack
	 * @param operatorStack
	 */
	public static void processAnOperator(GenericStack<Integer> operandStack, GenericStack<Character> operatorStack) {
		char op = operatorStack.pop();
		int op1 = operandStack.pop();
		int op2 = operandStack.pop();
		if (op == '+')
			operandStack.push(op2 + op1);
	    else if (op == '-')
			operandStack.push(op2 - op1);
		else if (op == '*')
			operandStack.push(op2 * op1);
		else if (op == '/') {
			operandStack.push(op2 / op1);
		}
	}
	
}
