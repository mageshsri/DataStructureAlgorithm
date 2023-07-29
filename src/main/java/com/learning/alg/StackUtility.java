package com.learning.alg;

import com.learning.ds.MyStackUsingLinkedList;

public class StackUtility {

	public boolean checkIfParenthesisBalanced(String str) {
		
		MyStackUsingLinkedList<String> stack= new MyStackUsingLinkedList<>();
		
		for(int i=0;i<str.length();i++) {
			String selChar=String.valueOf(str.charAt(i));
			if(isOpeningParenthesis(selChar))
					stack.push(selChar);
			else if(isClosingParenthesis(selChar)) {
				if(stack.isEmpty())
					return false;
				String lastOpenedParenthsys=stack.pop();
				if(!isParenthesisMatching(lastOpenedParenthsys,selChar))
					return false;
			}
		}
		return stack.isEmpty();
	}
	
	public Integer evaluatePostFixExpression(String str)
	{
       MyStackUsingLinkedList<Integer> stack= new MyStackUsingLinkedList<>(); 
		
		for(int i=0;i<str.length();i++) {
			String selChar=String.valueOf(str.charAt(i));
			if(isOperand(selChar)) {
				stack.push(Integer.parseInt(selChar));
			}else if(isOperator(selChar)) {
				Integer operand2=stack.pop();
				Integer operand1=stack.pop();
				Integer result=performOperation(operand1,operand2,selChar);
				stack.push(result);
			}
		}	
		return stack.pop();
	}
	
	public Integer evaluatePreFixExpression(String str)
	{
       MyStackUsingLinkedList<Integer> stack= new MyStackUsingLinkedList<>(); 
		
		for(int i=str.length()-1;i>=0;i--) {
			String selChar=String.valueOf(str.charAt(i));
			if(isOperand(selChar)) {
				stack.push(Integer.parseInt(selChar));
			}else if(isOperator(selChar)) {
				Integer operand1=stack.pop();
				Integer operand2=stack.pop();
				Integer result=performOperation(operand1,operand2,selChar);
				stack.push(result);
			}
		}	
		return stack.pop();
	}
	
	public String convertToPostFixExpression(String infixExpr) {
		MyStackUsingLinkedList<String> stack= new MyStackUsingLinkedList<>(); 
		StringBuffer postFixExpr= new StringBuffer();
		for(int i=0;i<infixExpr.length();i++) {
			String selChar=String.valueOf(infixExpr.charAt(i));
			if(isOperand(selChar)) {
				postFixExpr.append(selChar);
			}else if(isOpeningParenthesis(selChar)) {
				stack.push(selChar);
			}else if(isClosingParenthesis(selChar)) {
				while(!stack.isEmpty() && !isOpeningParenthesis(stack.peek())){
					postFixExpr.append(stack.pop());
				}
				stack.pop();//To pop up the opening parenthesis
			}
			else if(isOperator(selChar)) {
				while(!stack.isEmpty() && isOperator(stack.peek()) && stackHasHigherPrecedence(stack.peek(),selChar))
					postFixExpr.append(stack.pop());
				stack.push(selChar);
			}
		}
		while(!stack.isEmpty())
			postFixExpr.append(stack.pop());
		return postFixExpr.toString();
	}
	
	public String convertToPreFixExpression(String infixExpr) {
		MyStackUsingLinkedList<String> stack= new MyStackUsingLinkedList<>(); 
		String preFixExpr= "";
		for(int i=infixExpr.length()-1;i>=0;i--) {
			String selChar=String.valueOf(infixExpr.charAt(i));
			if(isOperand(selChar)) {
				preFixExpr=selChar+preFixExpr;
			}else if(isClosingParenthesis(selChar)) {
				stack.push(selChar);
			}else if(isOpeningParenthesis(selChar)) {
				while(!stack.isEmpty() && !isClosingParenthesis(stack.peek())){
					preFixExpr=stack.pop()+preFixExpr;
				}
				stack.pop();//To pop up the opening parenthesis
			}
			else if(isOperator(selChar)) {
				while(!stack.isEmpty() && isOperator(stack.peek()) && stackHasHigherPrecedence(stack.peek(),selChar))
					preFixExpr=stack.pop()+preFixExpr;
				stack.push(selChar);
			}
		}
		while(!stack.isEmpty())
			preFixExpr=stack.pop()+preFixExpr;
		return preFixExpr;
	}
	
	private boolean stackHasHigherPrecedence(String stackOpr, String selopr) {
		Integer stackOprRank=0;
		Integer selOprRank=0;
		if((OPERATOR.DIVISION.getName().equals(stackOpr)) || (OPERATOR.MULTIPLICATION.getName().equals(stackOpr))) {
			stackOprRank=2;
		}else if((OPERATOR.ADDITION.getName().equals(stackOpr)) || (OPERATOR.SUBTRACTION.getName().equals(stackOpr))) {
			stackOprRank=1;
		}
		
		if((OPERATOR.DIVISION.getName().equals(selopr)) || (OPERATOR.MULTIPLICATION.getName().equals(selopr))) {
			selOprRank=2;
		}else if((OPERATOR.ADDITION.getName().equals(selopr)) || (OPERATOR.SUBTRACTION.getName().equals(selopr))) {
			selOprRank=1;
		}
		
		return stackOprRank>=selOprRank;
	}

	private Integer performOperation(Integer operand1, Integer operand2, String selChar) {
		if(OPERATOR.ADDITION.getName().equals(selChar))
			return operand1+operand2;
		if(OPERATOR.SUBTRACTION.getName().equals(selChar))
			return operand1-operand2;
		else if(OPERATOR.MULTIPLICATION.getName().equals(selChar))
			return operand1*operand2;
		else if(OPERATOR.DIVISION.getName().equals(selChar))
			return operand1/operand2;
		return null;
	}

	private boolean isOperator(String selChar) {
		return OPERATOR.ADDITION.getName().equals(selChar) || OPERATOR.SUBTRACTION.getName().equals(selChar)
				|| OPERATOR.MULTIPLICATION.getName().equals(selChar) || OPERATOR.DIVISION.getName().equals(selChar);
	}

	private boolean isOperand(String selChar) {
		try {
			Integer.parseInt(selChar);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}

	private boolean isParenthesisMatching(String lastOpenedParenthsys, String closingParaenthsys) {
		return ((PARENTHESIS.CURLY.getOpening().equalsIgnoreCase(lastOpenedParenthsys) &&
			PARENTHESIS.CURLY.getClosing().equalsIgnoreCase(closingParaenthsys)) 
		|| (PARENTHESIS.SQUARE.getOpening().equalsIgnoreCase(lastOpenedParenthsys) &&
				PARENTHESIS.SQUARE.getClosing().equalsIgnoreCase(closingParaenthsys))		
		|| (PARENTHESIS.ROUND.getOpening().equalsIgnoreCase(lastOpenedParenthsys) &&
				PARENTHESIS.ROUND.getClosing().equalsIgnoreCase(closingParaenthsys)));		
	}


	private boolean isOpeningParenthesis(String selChar) {
		return PARENTHESIS.CURLY.getOpening().equalsIgnoreCase(selChar) ||
				PARENTHESIS.SQUARE.getOpening().equalsIgnoreCase(selChar) || 
				PARENTHESIS.ROUND.getOpening().equalsIgnoreCase(selChar);
	}
	
	private boolean isClosingParenthesis(String selChar) {
		return PARENTHESIS.CURLY.getClosing().equalsIgnoreCase(selChar) ||
				PARENTHESIS.SQUARE.getClosing().equalsIgnoreCase(selChar) || 
				PARENTHESIS.ROUND.getClosing().equalsIgnoreCase(selChar);
	}

	enum PARENTHESIS{
		
		CURLY("{","}"),SQUARE("[","]"),ROUND("(",")");
		
		public String opening;
		public String closing;
		private PARENTHESIS(String opening, String closing) {
			this.opening = opening;
			this.closing = closing;
		}
		public String getOpening() {
			return opening;
		}
		public String getClosing() {
			return closing;
		}
		
	}
	
   enum OPERATOR{	
		ADDITION("+"),SUBTRACTION("-"),MULTIPLICATION("*"),DIVISION("/");
		
		public String name;

		private OPERATOR(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
	}
	
	public static void main(String[] args) {
		StackUtility stackUtility= new StackUtility();
		System.out.println(" Paraenthesis Check "+stackUtility.checkIfParenthesisBalanced("{{}}"));
		System.out.println(" PostFix  "+stackUtility.evaluatePostFixExpression("34+7*95-*"));//3+4*9-5
		System.out.println(" PreFix  "+stackUtility.evaluatePreFixExpression("**+347-95"));//3+4*9-5'
		
		System.out.println(" PostFix conversion "+stackUtility.convertToPostFixExpression("5*4+6"));
		System.out.println(" PreFix conversion "+stackUtility.convertToPreFixExpression("((3+4)*7)*(9-5)"));
	}

}
