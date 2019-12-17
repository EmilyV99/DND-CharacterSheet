package GUI;

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
	Label error;
	
	@FXML
	public boolean updateText(ActionEvent e)
	{
		boolean ret = true;
		String a = age.getText();
		a = a.replaceAll("[^0-9]", "");
		if (!a.equals(age.getText()))
			ret = false;
		age.setText(a);
		error.setText("");
		return ret;
	}
	
	public void clear()
	{
		name.setText(Main_Gui.gen.c.name);
		age.setText("" + Main_Gui.gen.c.age);
		updateText(null);
	}
	
	@FXML
	public void onClose(ActionEvent e)
	{
		try
		{
			if (!updateText(null))
			{
				error.setText("Invalid field data! Fields have been fixed to rules, check that they are still correct" + ".");
				return;
			}
			Main_Gui.gen.c.name = name.getText();
			Main_Gui.gen.c.age = Integer.parseInt(age.getText());
			if (Main_Gui.gen.back())
			{
				//Main_Gui.genstart_controller.onNext(null);
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
			if (!updateText(null))
			{
				error.setText("Invalid field data! Fields have been fixed to rules, check that they are still correct" + ".");
				return;
			}
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
