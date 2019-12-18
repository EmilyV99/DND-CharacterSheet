package GUI;

import DND.Character;
import GUI.Helpers.GenericGuiHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class GenStats_Manual_Controller
{
	@FXML
	TextField strength, dexterity, constitution, intelligence, wisdom, charisma;
	@FXML
	Label error, error2;
	
	public void init()
	{
		if (initialized)
			return;
		initialized = true;
		GenericGuiHelper.makeTextFieldLimited(strength, GenericGuiHelper.TextFieldType.POSITIVE_INTEGER_VALUE, 3, 127);
		GenericGuiHelper.makeTextFieldLimited(dexterity, GenericGuiHelper.TextFieldType.POSITIVE_INTEGER_VALUE, 3, 127);
		GenericGuiHelper.makeTextFieldLimited(constitution, GenericGuiHelper.TextFieldType.POSITIVE_INTEGER_VALUE, 3, 127);
		GenericGuiHelper.makeTextFieldLimited(intelligence, GenericGuiHelper.TextFieldType.POSITIVE_INTEGER_VALUE, 3, 127);
		GenericGuiHelper.makeTextFieldLimited(wisdom, GenericGuiHelper.TextFieldType.POSITIVE_INTEGER_VALUE, 3, 127);
		GenericGuiHelper.makeTextFieldLimited(charisma, GenericGuiHelper.TextFieldType.POSITIVE_INTEGER_VALUE, 3, 127);
	}
	
	private boolean initialized;
	
	@FXML
	public boolean updateText(Event e)
	{
		boolean ret = true;
		/*{
			String pattern1 = "[^0-9]";
			String pattern2 = "0*([0-9]+)";
			String replace2 = "$1";
			String str = strength.getText();
			str = str.replaceAll(pattern1, "");
			if (!str.equals(strength.getText()))
				ret = false;
			if (str.length() > 3)
				str = str.substring(str.length() - 4, str.length() - 1);
			str = str.replaceAll(pattern2, replace2);
			strength.setText(str);
			String dex = dexterity.getText();
			dex = dex.replaceAll(pattern1, "");
			if (!dex.equals(dexterity.getText()))
				ret = false;
			if (dex.length() > 3)
				dex = dex.substring(dex.length() - 4, dex.length() - 1);
			dex = dex.replaceAll(pattern2, replace2);
			dexterity.setText(dex);
			String con = constitution.getText();
			con = con.replaceAll(pattern1, "");
			if (!con.equals(constitution.getText()))
				ret = false;
			if (con.length() > 3)
				con = con.substring(con.length() - 4, con.length() - 1);
			con = con.replaceAll(pattern2, replace2);
			constitution.setText(con);
			String intel = intelligence.getText();
			intel = intel.replaceAll(pattern1, "");
			if (!intel.equals(intelligence.getText()))
				ret = false;
			if (intel.length() > 3)
				intel = intel.substring(intel.length() - 4, intel.length() - 1);
			intel = intel.replaceAll(pattern2, replace2);
			intelligence.setText(intel);
			String wis = wisdom.getText();
			wis = wis.replaceAll(pattern1, "");
			if (!wis.equals(wisdom.getText()))
				ret = false;
			if (wis.length() > 3)
				wis = wis.substring(wis.length() - 4, wis.length() - 1);
			wis = wis.replaceAll(pattern2, replace2);
			wisdom.setText(wis);
			String cha = charisma.getText();
			cha = cha.replaceAll(pattern1, "");
			if (!cha.equals(charisma.getText()))
				ret = false;
			if (cha.length() > 3)
				cha = cha.substring(cha.length() - 4, cha.length() - 1);
			cha = cha.replaceAll(pattern2, replace2);
			charisma.setText(cha);
		}*/
		byte str = 0, dex = 0, con = 0, intel = 0, wis = 0, cha = 0;
		{
			String tooLargeError = "The highest functional value is 127.";
			if (!strength.getText().equals(""))
			{
				if (Integer.parseInt(strength.getText()) > 127)
				{
					error2.setText(tooLargeError);
					strength.setText("127");
					str = 127;
				}
				else
				{
					str = Byte.parseByte(strength.getText());
				}
			}
			if (!dexterity.getText().equals(""))
			{
				if (Integer.parseInt(dexterity.getText()) > 127)
				{
					error2.setText(tooLargeError);
					dexterity.setText("127");
					dex = 127;
				}
				else
				{
					dex = Byte.parseByte(dexterity.getText());
				}
			}
			if (!constitution.getText().equals(""))
			{
				if (Integer.parseInt(constitution.getText()) > 127)
				{
					error2.setText(tooLargeError);
					constitution.setText("127");
					con = 127;
				}
				else
				{
					con = Byte.parseByte(constitution.getText());
				}
			}
			if (!intelligence.getText().equals(""))
			{
				if (Integer.parseInt(intelligence.getText()) > 127)
				{
					error2.setText(tooLargeError);
					intelligence.setText("127");
					intel = 127;
				}
				else
				{
					intel = Byte.parseByte(intelligence.getText());
				}
			}
			if (!wisdom.getText().equals(""))
			{
				if (Integer.parseInt(wisdom.getText()) > 127)
				{
					error2.setText(tooLargeError);
					wisdom.setText("127");
					wis = 127;
				}
				else
				{
					wis = Byte.parseByte(wisdom.getText());
				}
			}
			if (!charisma.getText().equals(""))
			{
				if (Integer.parseInt(charisma.getText()) > 127)
				{
					error2.setText(tooLargeError);
					charisma.setText("127");
					cha = 127;
				}
				else
				{
					cha = Byte.parseByte(charisma.getText());
				}
			}
		}
		if (str <= 0 || dex <= 0 || con <= 0 || intel <= 0 || wis <= 0 || cha <= 0)
		{
			if (error2.getText().equals(""))
				error2.setText("All stats must be at least 1!");
		}
		else
		{
			error2.setText("");
		}
		
		return ret;
	}
	
	public void clear()
	{
		strength.setText("0");
		dexterity.setText("0");
		constitution.setText("0");
		intelligence.setText("0");
		wisdom.setText("0");
		charisma.setText("0");
		error.setText("");
		error2.setText("");
	}
	
	@FXML
	public void onReset(ActionEvent e)
	{
		Main_Gui.gen.initStats();
		clear();
	}
	
	@FXML
	public void onClose(ActionEvent e)
	{
		if (Main_Gui.gen.back())
			Main_Gui.stage.setScene(Main_Gui.gen_start);
	}
	
	@FXML
	public void onNext(ActionEvent e)
	{
		try
		{
			error.setText("");
			error2.setText("");
			if (!updateText(null))
			{
				error.setText("Invalid field data! Fields have been fixed to rules, check that they are still correct.");
				return;
			}
			Character c = Main_Gui.gen.c;
			c.str = Byte.parseByte(strength.getText());
			c.dex = Byte.parseByte(dexterity.getText());
			c.con = Byte.parseByte(constitution.getText());
			c.intel = Byte.parseByte(intelligence.getText());
			c.wis = Byte.parseByte(wisdom.getText());
			c.cha = Byte.parseByte(charisma.getText());
			if (Main_Gui.gen.confirm())
			{
				Main_Gui.charsetup_controller.clear();
				Main_Gui.stage.setScene(Main_Gui.gen_char_setup);
			}
		}
		catch (Exception ignored)
		{
			
		}
	}
}
