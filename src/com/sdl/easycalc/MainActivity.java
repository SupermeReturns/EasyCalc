package com.sdl.easycalc;

import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ImageButton;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements OnClickListener{
	private TextView inputTextView, resultTextView;
	private ImageButton buttonClear, buttonLeftBrace, buttonRightBrace,buttonDelete,
              		          buttonDigit7, buttonDigit8, buttonDigit9, buttonDivide,
              		          buttonDigit4, buttonDigit5, buttonDigit6, buttonMultiply,
              		          buttonDigit1, buttonDigit2, buttonDigit3, buttonSubtract,
              		          buttonDigit0,buttonPoint, buttonEqual, buttonPlus;
              private String userInput = new String("");
	private static final int EXIT_ID = Menu.FIRST +1;
	private static final int AUTHOR_ID = Menu.FIRST+2;
	protected static final String ACTIVITY_TAG="MyAndroid";
	private AlertDialog.Builder authorInfo;
	private boolean newSession = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/* 作者信息显示对话框 */
		authorInfo = new AlertDialog.Builder(this);

		// find all the views by id
		inputTextView = (TextView)findViewById(R.id.input_TextView);
		resultTextView = (TextView)findViewById(R.id.result_TextView);

		buttonClear = (ImageButton)findViewById(R.id.button_clear);
		buttonLeftBrace = (ImageButton)findViewById(R.id.button_left_brace);
		buttonRightBrace = (ImageButton)findViewById(R.id.button_right_brace);
		buttonDelete = (ImageButton)findViewById(R.id.button_delete);

		buttonDigit7 = (ImageButton)findViewById(R.id.button_digit_7);
		buttonDigit8 = (ImageButton)findViewById(R.id.button_digit_8);
		buttonDigit9 = (ImageButton)findViewById(R.id.button_digit_9);
		buttonDivide = (ImageButton)findViewById(R.id.button_divide);

		buttonDigit4 = (ImageButton)findViewById(R.id.button_digit_4);
		buttonDigit5 = (ImageButton)findViewById(R.id.button_digit_5);
		buttonDigit6 = (ImageButton)findViewById(R.id.button_digit_6);
		buttonMultiply = (ImageButton)findViewById(R.id.button_multiply);

		buttonDigit1 = (ImageButton)findViewById(R.id.button_digit_1);
		buttonDigit2 = (ImageButton)findViewById(R.id.button_digit_2);
		buttonDigit3 = (ImageButton)findViewById(R.id.button_digit_3);
		buttonSubtract = (ImageButton)findViewById(R.id.button_subtract);

		buttonDigit0 = (ImageButton)findViewById(R.id.button_digit_0);
		buttonPoint = (ImageButton)findViewById(R.id.button_point);
		buttonEqual = (ImageButton)findViewById(R.id.button_equal);
		buttonPlus = (ImageButton)findViewById(R.id.button_plus);

		// bind listener to image buttons
		buttonClear.setOnClickListener(this);
		buttonLeftBrace.setOnClickListener(this);
		buttonRightBrace.setOnClickListener(this);
		buttonDelete.setOnClickListener(this);

              	buttonDigit7.setOnClickListener(this);
              	buttonDigit8.setOnClickListener(this);
              	buttonDigit9.setOnClickListener(this);
              	buttonDivide.setOnClickListener(this);

              	buttonDigit4.setOnClickListener(this);
              	buttonDigit5.setOnClickListener(this);
              	buttonDigit6.setOnClickListener(this);
              	buttonMultiply.setOnClickListener(this);

              	buttonDigit1.setOnClickListener(this);
              	buttonDigit2.setOnClickListener(this);
              	buttonDigit3.setOnClickListener(this);
              	buttonSubtract.setOnClickListener(this);

              	buttonDigit0.setOnClickListener(this);
              	buttonPoint.setOnClickListener(this);
              	buttonEqual.setOnClickListener(this);
              	buttonPlus.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Log.d(ACTIVITY_TAG, "Received A Click");
		if (!newSession) {
			userInput = new String("");
			inputTextView.setText(userInput);
			resultTextView.setText("");
			newSession = true;
		}
		switch (v.getId()) {
			case R.id.button_clear :
				userInput = new String("");
				resultTextView.setText("");
				break;
			case R.id.button_left_brace :
				userInput += new String("(");
				break;
			case R.id.button_right_brace :
				userInput += new String(")");
				break;
			case R.id.button_delete :
				if (userInput.length()!=0) {
					userInput = userInput.substring(0, userInput.length()-1);
				}
				break;
			case R.id.button_digit_7 :
				userInput += new String("7");
				break;
			case R.id.button_digit_8 :
				userInput += new String("8");
				break;
			case R.id.button_digit_9 :
				userInput += new String("9");
				break;
			case R.id.button_divide :
				userInput += new String("/");
				break;
			case R.id.button_digit_4 :
				userInput += new String("4");
				break;
			case R.id.button_digit_5 :
				userInput += new String("5");
				break;
			case R.id.button_digit_6 :
				userInput += new String("6");
				break;
			case R.id.button_multiply :
				userInput += new String("*");
				break;
			case R.id.button_digit_1 :
				userInput += new String("1");
				break;
			case R.id.button_digit_2 :
				userInput += new String("2");
				break;
			case R.id.button_digit_3 :
				userInput += new String("3");
				break;
			case R.id.button_subtract :
				userInput += new String("-");
				break;
			case R.id.button_digit_0 :
				userInput += new String("0");
				break;
			case R.id.button_point :
				userInput += new String(".");
				break;
			case R.id.button_equal :
				Log.d(ACTIVITY_TAG, "Equals clicked,content:" + userInput);
				try {
					resultTextView.setText( "=" + String.valueOf(calculate(userInput)));
					newSession = false;
				} catch (Exception e) {
					resultTextView.setText(R.string.errorInfo);					
				}
				break;
			case R.id.button_plus :
				userInput += new String("+");
				break;	
		}
		inputTextView.setText(userInput);
	}

	/**
	* calculate the reslut from string formula
	*/
	private float calculate(String formula) {
		Log.d(ACTIVITY_TAG, "start of calculate");    
		List<Float> operands = new ArrayList<Float>();
		List<Character>  operators =  new ArrayList<Character>(); 

		// extract operands and operators from the string
		for (int i = 0; i < formula.length(); i++) {
			if (formula.charAt(i) == '(') {
				int end = findMatch(i, formula);
				operands.add(Float.valueOf(calculate(formula.substring(i+1, end))));
				i = end + 1;
			} else {
				int start = i;
				while (true) {
					i++;
					if (i == formula.length()) {
						float newOperands = Float.parseFloat(formula.substring(start, i));
						operands.add(Float.valueOf(newOperands));
						break;
					}
					boolean foundEnd = false;
					switch (formula.charAt(i)) {
						case '+' :
						case '-' :
						case '*' :
						case '/' :
							foundEnd = true;
							float newOperands = Float.parseFloat(formula.substring(start, i));
							operands.add(Float.valueOf(newOperands));
							break;
					}
					if (foundEnd) {
						break;
					}
				}
			}
			if (i == formula.length() ) {
				break;
			}
			operators.add( Character.valueOf(formula.charAt(i)));
		}
		Log.d(ACTIVITY_TAG, "operands array" + operands.toString()); 
		Log.d(ACTIVITY_TAG, "operators array" + operators.toString()); 
		// calculate the result with the two arraylists of operands and operators
		while (!operators.isEmpty()) {
			// find the "greatest" operator
			int indexOfOperator = 0;
			for (int i =0; i< operators.size(); i++) {
				char o = operators.get(i).charValue();
				if ((o == '*')|| (o == '/')) {
					indexOfOperator = i;
					break;
				} 
			}

			// take two operands and do the math
			float operand1 = operands.get(indexOfOperator).floatValue();
			float operand2 = operands.get(indexOfOperator+1).floatValue();
			char operator = operators.get(indexOfOperator).charValue();
			float result = simpleCalc(operand1, operand2, operator);
			// store the result back into operands array
			operands.set(indexOfOperator, Float.valueOf(result));
			operands.remove(indexOfOperator+1);
			operators.remove(indexOfOperator);
		}
		Log.d(ACTIVITY_TAG, "end of calculate");    
		return operands.get(0).floatValue();
	}

	/**
	* find the index of matched brace in the string formula
	*/
	private int findMatch(int index, String formula) {
		int stackOfBraces = 0;
		for (int i = index+1; i < formula.length();i++) {
			if (formula.charAt(i) == ')') {
				if (stackOfBraces == 0) {
					return i;
				}
				stackOfBraces--;
			} else if (formula.charAt(i) == '('){
				stackOfBraces++;
			}
		}
		return -1;
	}

	/**
	* simple calculation , with two operands and one operator
	*/
	private float simpleCalc(float operand1, float operand2, char operator) {
		float result;
		switch (operator) {
			case '+':
				result = operand1 + operand2;
				break;
			case '-' :
				result = operand1 - operand2;
				break;
			case '*':
				result = operand1 * operand2;
				break;
			case '/' :
				result = operand1 / operand2;
				break;
			default:
				result = -1;
				break;
		}
		return result;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/*添加退出菜单*/
		menu.add(Menu.NONE, AUTHOR_ID, Menu.NONE, "Author Info");
		menu.add(Menu.NONE, EXIT_ID, Menu.NONE, "Exit");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case AUTHOR_ID:
				authorInfo.setTitle(R.string.authorInfoTitle);
				authorInfo.setPositiveButton(R.string.about_dialog_ok, null);
				authorInfo.setMessage(R.string.authorInfoMain).show();
				break;
			case EXIT_ID:
				/*结束Activity*/
				finish();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}