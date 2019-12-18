package GUI;

import GUI.Helpers.GenericGuiHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GenCharSetupController
{
	@FXML
	TextField name, age;
	@FXML
	Label race_label, class_label;
	
	private static boolean initialized;
	public void init()
	{
		if(initialized) return;
		initialized = true;
		GenericGuiHelper.filterIntegerTextField(age, 0, Integer.MAX_VALUE, 10);
	}
	
	@FXML
	public void updateText(ActionEvent e)
	{
	}
	
	public void clear()
	{
		name.setText(Main_Gui.gen.c.name);
		age.setText(Main_Gui.gen.c.age > 0 ? "" + Main_Gui.gen.c.age : "");
		updateText(null);
	}
	
	@FXML
	public void onClose(ActionEvent e)
	{
		try
		{
			Main_Gui.gen.c.name = name.getText();
			Main_Gui.gen.c.age =  age.getText().equals("") ? 0 : Integer.parseInt(age.getText());
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
				Main_Gui.stage.setScene(sc);
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
