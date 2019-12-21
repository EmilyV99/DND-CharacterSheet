package GUI.Helpers;

import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.function.UnaryOperator;

@SuppressWarnings("DuplicatedCode")
public abstract class GenericGuiHelper
{
	public static void filterIntegerTextField(final TextField tf, final int minValue, final int maxValue)
	{
		filterIntegerTextField(tf, minValue, maxValue, 0);
	}
	public static void filterIntegerTextField(final TextField tf, final int minValue, final int maxValue, final int maxChars)
	{
		filterIntegerTextField(tf, minValue, maxValue, maxChars, null, null);
	}
	public static void filterIntegerTextField(final TextField tf, final int minValue, final int maxValue, final int maxChars, final String reqRegex, final Label reqLabel)
	{
		final boolean req = reqLabel != null && reqRegex != null;
		final boolean limitRange = minValue < maxValue;
		final String regex = minValue < 0 ? "-?[0-9]*" : "[0-9]*";
		final String regex_leading0 = "(-?)0+([0-9]+)";
		final String replace_leading0 = "$1$2";
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if(req) reqLabel.setVisible(!text.matches(reqRegex));
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
	public static void filterTextField(final TextField tf, final String regex, final int maxChars)
	{
		filterTextField(tf, regex, maxChars, null, null);
	}
	public static void filterTextField(final TextField tf, final String regex, final int maxChars, final String reqRegex, final Label reqLabel)
	{
		final boolean req = reqLabel != null && reqRegex != null;
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if(req) reqLabel.setVisible(!text.matches(reqRegex));
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
	public static void filterTextArea(final TextArea ta, final String regex, final int maxChars)
	{
		filterTextArea(ta, regex, maxChars, null, null);
	}
	public static void filterTextArea(final TextArea ta, final String regex, final int maxChars, final String reqRegex, final Label reqLabel)
	{
		final boolean req = reqLabel != null && reqRegex != null;
		UnaryOperator<TextFormatter.Change> filter = change ->
		{
			try
			{
				String text = change.getControlNewText();
				if(text.contains("\n")) return null; //Cancel newlines, they break TextArea!
				if(req) reqLabel.setVisible(!text.matches(reqRegex));
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
		ta.setTextFormatter(new TextFormatter<TextFormatter.Change>(filter));
	}
	
	public static Tooltip instaTT(String str)
	{
		Tooltip t = new Tooltip(str);
		t.setShowDelay(Duration.ZERO);
		t.setHideDelay(Duration.ZERO);
		return t;
	}
	
	public static final String ALPHABETIC = "[a-zA-Z]*", ALPHANUMERIC = "[a-zA-Z0-9]*", ALPHABETIC_SPACE = "[a-zA-Z ]*", ALPHANUMERIC_SPACE = "[a-zA-Z0-9 ]*",
	NUMERIC = "[0-9]*", NUMERIC_NEG = "-?[0-9]*", ANY = ".*", NAMES = "[A-Za-z '-()\"!]+";
}
