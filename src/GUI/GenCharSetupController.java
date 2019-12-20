package GUI;

import DND.Character;
import GUI.Helpers.GenericGuiHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GenCharSetupController extends SceneController
{
	@FXML
	TextField name, age;
	@FXML
	Label race_label, class_label;
	@FXML
	Label nameReq, ageReq;
	@FXML
	Button confirm, cancel;
	
	public boolean init()
	{
		if (super.init())
			return true;
		initialized = true;
		GenericGuiHelper.filterTextField(name, "[A-Za-z \\'\\-]*", 64, ".+", nameReq); //Filters: Alphanumeric + ' ' + '-' + '\'', min 1 char
		GenericGuiHelper.filterIntegerTextField(age, 0, Integer.MAX_VALUE, 10, "[1-9][0-9]*", ageReq); //Filters: Value of at least 1, no leading 0s
		return false;
	}
	
	@FXML
	public void updateText(Event e)
	{
		Character c = Main_Gui.gen.c;
		race_label.setText(c.race == null ? "Required" : c.race.toString());
		race_label.setTextFill(c.race == null ? GuiColor.ERROR_RED : GuiColor.BLACK);
		class_label.setText(c.classes.size() == 0 ? "Required" : c.classes.get(0).toString());
		class_label.setTextFill(c.classes.size() == 0 ? GuiColor.ERROR_RED : GuiColor.BLACK);
		confirm.setDisable(!isValid());
	}
	
	@SuppressWarnings("RedundantIfStatement")
	private boolean isValid()
	{
		Character c = Main_Gui.gen.c;
		try
		{
			if (name.getText() == null || name.getText().equals(""))
				return false;
			if (age.getText() == null || Integer.parseInt(age.getText()) < 1)
				return false;
			if(c.race == null) return false;
			//if(c.classes.size() == 0) return false;
			return true;
		}
		catch (Exception ignored)
		{
			return false;
		}
	}
	
	@Override
	public void reset()
	{
		name.setText(Main_Gui.gen.c.name);
		age.setText(Main_Gui.gen.c.age > 0 ? "" + Main_Gui.gen.c.age : "");
		updateText();
	}
	
	@FXML
	public void onRace()
	{
		Main_Gui.race_controller.setup(Main_Gui.gen_char_setup, Main_Gui.gen.c.race, Main_Gui.gen.c);
		Main_Gui.setScene(Main_Gui.edit_race);
	}
	
	@FXML
	public void onClose(ActionEvent e)
	{
		try
		{
			Main_Gui.gen.c.name = name.getText();
			Main_Gui.gen.c.age = age.getText().equals("") ? 0 : Integer.parseInt(age.getText());
			if (Main_Gui.gen.back())
			{
				Scene sc;
				switch (Main_Gui.gen.statStyle)
				{
					case ARRAY:
					case ROLL_ASSIGN:
						sc = Main_Gui.gen_stats_array;
						break;
					case POINT_BUY:
						sc = Main_Gui.gen_stats_pointbuy;
						break;
					case ROLL_RANDOM:
						sc = Main_Gui.gen_stats_rand;
						break;
					case MANUAL:
						sc = Main_Gui.gen_stats_manual;
						break;
					default:
						return;
				}
				Main_Gui.setScene(sc);
			}
		}
		catch (Exception ignored)
		{
		}
	}
	
	@FXML
	public void onNext(ActionEvent e)
	{
		try
		{
			Main_Gui.gen.c.name = name.getText();
			Main_Gui.gen.c.age = Integer.parseInt(age.getText());
			if (Main_Gui.gen.confirm())
			{
				Main_Gui.gen.end();
			}
		}
		catch (Exception ignored)
		{
		}
	}
}
