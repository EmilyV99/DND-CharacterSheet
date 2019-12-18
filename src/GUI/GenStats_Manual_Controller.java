package GUI;

import DND.Character;
import GUI.Helpers.GenericGuiHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GenStats_Manual_Controller
{
	@FXML
	TextField strength, dexterity, constitution, intelligence, wisdom, charisma;
	@FXML
	Label error, error2;
	private boolean initialized;
	
	public void init()
	{
		if (initialized)
			return;
		initialized = true;
		GenericGuiHelper.filterIntegerTextField(strength, 0, 127, 3);
		GenericGuiHelper.filterIntegerTextField(dexterity, 0, 127, 3);
		GenericGuiHelper.filterIntegerTextField(constitution,0, 127, 3);
		GenericGuiHelper.filterIntegerTextField(intelligence, 0, 127, 3);
		GenericGuiHelper.filterIntegerTextField(wisdom, 0, 127, 3);
		GenericGuiHelper.filterIntegerTextField(charisma, 0, 127, 3);
	}
	
	@SuppressWarnings("DuplicatedCode")
	@FXML
	public void updateText(Event e)
	{
		byte str = 0, dex = 0, con = 0, intel = 0, wis = 0, cha = 0;
		if (!strength.getText().equals(""))
		{
			str = Byte.parseByte(strength.getText());
		}
		if (!dexterity.getText().equals(""))
		{
			dex = Byte.parseByte(dexterity.getText());
		}
		if (!constitution.getText().equals(""))
		{
			con = Byte.parseByte(constitution.getText());
		}
		if (!intelligence.getText().equals(""))
		{
			intel = Byte.parseByte(intelligence.getText());
		}
		if (!wisdom.getText().equals(""))
		{
			wis = Byte.parseByte(wisdom.getText());
		}
		if (!charisma.getText().equals(""))
		{
			cha = Byte.parseByte(charisma.getText());
		}
		if (str <= 0 || dex <= 0 || con <= 0 || intel <= 0 || wis <= 0 || cha <= 0)
		{
			error2.setText("All stats must be at least 1!");
		}
		else
		{
			error2.setText("");
		}
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
			updateText(null);
			Character c = Main_Gui.gen.c;
			c.str = Byte.parseByte(strength.getText());
			c.dex = Byte.parseByte(dexterity.getText());
			c.con = Byte.parseByte(constitution.getText());
			c.intel = Byte.parseByte(intelligence.getText());
			c.wis = Byte.parseByte(wisdom.getText());
			c.cha = Byte.parseByte(charisma.getText());
			if (Main_Gui.gen.confirm())
			{
				Main_Gui.charsetup_controller.init();
				Main_Gui.charsetup_controller.clear();
				Main_Gui.stage.setScene(Main_Gui.gen_char_setup);
			}
		}
		catch (Exception ignored)
		{
			
		}
	}
}
