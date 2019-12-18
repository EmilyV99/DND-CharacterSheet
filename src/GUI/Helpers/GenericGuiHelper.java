package GUI.Helpers;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;

@SuppressWarnings("DuplicatedCode")
public abstract class GenericGuiHelper
{
	public static void filterIntegerTextField(final TextField tf, int minValue, int maxValue)
	{
		final boolean limitRange = minValue < maxValue;
		final String regex = minValue < 0 ? "-?[0-9]*" : "[0-9]*";
		final String regex_leading0 = "(-?)0+([0-9]+)";
		final String replace_leading0 = "$1$2";
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if(text.equals("")) return change;
				if ((text.matches(regex)))
				{
					int val = Integer.parseInt(text);
					if(text.matches("-0+"))
					{
						tf.setText("0");
						change.setText("");
					}
					else if (limitRange && val > maxValue)
					{
						tf.setText("" + maxValue);
						change.setText("");
					}
					else if(limitRange && val < minValue)
					{
						tf.setText("" + minValue);
						change.setText("");
					}
					else if (text.matches(regex_leading0))
					{
						tf.setText(text.replaceAll(regex_leading0, replace_leading0));
						change.setText("");
					}
					return change;
				}
			}
			catch (Exception ignored)
			{
			}
			return null;
		};
		tf.setTextFormatter(new TextFormatter<TextFormatter.Change>(filter));
	}
	public static void filterIntegerTextField(final TextField tf, int minValue, int maxValue, int maxChars)
	{
		final boolean limitRange = minValue < maxValue;
		final String regex = minValue < 0 ? "-?[0-9]*" : "[0-9]*";
		final String regex_leading0 = "(-?)0+([0-9]+)";
		final String replace_leading0 = "$1$2";
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if(text.equals("")) return change;
				if(maxChars > 0 && text.length() > maxChars) return null;
				if ((text.matches(regex)))
				{
					int val = Integer.parseInt(text);
					if(text.matches("-0+"))
					{
						tf.setText("0");
						change.setText("");
					}
					else if (limitRange && val > maxValue)
					{
						tf.setText("" + maxValue);
						change.setText("");
					}
					else if(limitRange && val < minValue)
					{
						tf.setText("" + minValue);
						change.setText("");
					}
					else if (text.matches(regex_leading0))
					{
						tf.setText(text.replaceAll(regex_leading0, replace_leading0));
						change.setText("");
					}
					return change;
				}
			}
			catch (NumberFormatException e)
			{
				try
				{
					tf.setText("" + (change.getControlNewText().charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE));
					change.setText("");
					return change;
				}
				catch (Exception ignored)
				{
				
				}
			}
			catch (Exception ignored)
			{
			}
			return null;
		};
		tf.setTextFormatter(new TextFormatter<TextFormatter.Change>(filter));
	}
	public static void filterTextField(final TextField tf, TextFieldType type, int maxChars)
	{
		String regex;
		switch (type)
		{
			case ALPHABETIC:
				regex = "[a-zA-z]*";
				break;
			case NUMERIC_NEG:
				regex = "-?[0-9]*";
				break;
			case NUMERIC:
				regex = "[0-9]*";
				break;
			case ALPHANUMERIC:
			default:
				regex = "[a-zA-Z0-9]*";
				break;
		}
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if ((text.matches(regex) || text.equals("")) && (maxChars == 0 || text.length() <= maxChars))
				{
					return change;
				}
			}
			catch (Exception ignored)
			{
			}
			return null;
		};
		tf.setTextFormatter(new TextFormatter<TextFormatter.Change>(filter));
	}
	
	public enum TextFieldType
	{
		ALPHABETIC, ALPHANUMERIC, NUMERIC_NEG, NUMERIC
	}
}
