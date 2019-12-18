package GUI.Helpers;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

public abstract class GenericGuiHelper
{
	public static void makeTextFieldLimited(final TextField tf, TextFieldType type, int maxChars, int maxValue)
	{
		String regex;
		switch (type)
		{
			case ALPHABETIC:
				regex = "[a-zA-z]*";
				break;
			case INTEGER_VALUE:
				regex = "\\-?[0-9]*";
				break;
			case POSITIVE_INTEGER_VALUE:
				regex = "[0-9]*";
				break;
			case ALPHANUMERIC:
			default:
				regex = "[a-zA-Z0-9]*";
				break;
		}
		final boolean isInt = (type == TextFieldType.POSITIVE_INTEGER_VALUE || type == TextFieldType.INTEGER_VALUE);
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if (text.matches(regex) && (maxChars == 0 || text.length() < maxChars))
				{
					if (isInt)
					{
						if (Integer.parseInt(text) > maxValue)
						{
							change.setText("" + maxValue);
							/*
							tf.setText("" + maxValue);
							return null;
							 */
						}
					}
					return change;
				}
			}
			catch (Exception ignored)
			{}
			return null;
		};
		tf.setTextFormatter(new TextFormatter<TextFormatter.Change>(filter));
	}
	
	public enum TextFieldType
	{
		ALPHABETIC, ALPHANUMERIC, INTEGER_VALUE, POSITIVE_INTEGER_VALUE
	}
}
