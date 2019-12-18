package GUI;

import DND.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GenStats_PointBuy_Controller
{
	@FXML
	Text strength, dexterity, constitution, intelligence, wisdom, charisma, points;
	
	@FXML
	Button nextbtn, b_str_up, b_dex_up, b_con_up, b_int_up, b_wis_up, b_cha_up, b_str_down, b_dex_down, b_con_down, b_int_down, b_wis_down, b_cha_down;
	
	public void updateText()
	{
		Character c = Main_Gui.gen.c;
		strength.setText("" + c.str);
		dexterity.setText("" + c.dex);
		constitution.setText("" + c.con);
		intelligence.setText("" + c.intel);
		wisdom.setText("" + c.wis);
		charisma.setText("" + c.cha);
		points.setText("Points: " + Main_Gui.gen.points);
		nextbtn.setDisable(Main_Gui.gen.points > 0);
	}
	
	@FXML
	public void onReset(ActionEvent e)
	{
		Main_Gui.gen.initStats();
		updateText();
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
		if (Main_Gui.gen.confirm())
		{
			Main_Gui.charsetup_controller.clear();
			Main_Gui.stage.setScene(Main_Gui.gen_char_setup);
		}
	}
	
	@FXML
	public void onPlus(ActionEvent e)
	{
		if (e.getTarget() instanceof Button)
		{
			Button b = (Button) e.getTarget();
			Character c = Main_Gui.gen.c;
			if (b == b_str_up)
			{
				if ((c.str < 13 ? 1 : 2) <= Main_Gui.gen.points && c.str < 15)
				{
					Main_Gui.gen.points -= (c.str < 13 ? 1 : 2);
					++c.str;
				}
			}
			else if (b == b_dex_up)
			{
				if ((c.dex < 13 ? 1 : 2) <= Main_Gui.gen.points && c.dex < 15)
				{
					Main_Gui.gen.points -= (c.dex < 13 ? 1 : 2);
					++c.dex;
				}
			}
			else if (b == b_con_up)
			{
				if ((c.con < 13 ? 1 : 2) <= Main_Gui.gen.points && c.con < 15)
				{
					Main_Gui.gen.points -= (c.con < 13 ? 1 : 2);
					++c.con;
				}
			}
			else if (b == b_int_up)
			{
				if ((c.intel < 13 ? 1 : 2) <= Main_Gui.gen.points && c.intel < 15)
				{
					Main_Gui.gen.points -= (c.intel < 13 ? 1 : 2);
					++c.intel;
				}
			}
			else if (b == b_wis_up)
			{
				if ((c.wis < 13 ? 1 : 2) <= Main_Gui.gen.points && c.wis < 15)
				{
					Main_Gui.gen.points -= (c.wis < 13 ? 1 : 2);
					++c.wis;
				}
			}
			else if (b == b_cha_up)
			{
				if ((c.cha < 13 ? 1 : 2) <= Main_Gui.gen.points && c.cha < 15)
				{
					Main_Gui.gen.points -= (c.cha < 13 ? 1 : 2);
					++c.cha;
				}
			}
		}
		updateText();
	}
	
	@FXML
	public void onMinus(ActionEvent e)
	{
		if (e.getTarget() instanceof Button)
		{
			Button b = (Button) e.getTarget();
			Character c = Main_Gui.gen.c;
			if (b == b_str_down)
			{
				if (c.str > 8)
				{
					--c.str;
					Main_Gui.gen.points += (c.str < 13 ? 1 : 2);
				}
			}
			else if (b == b_dex_down)
			{
				if (c.dex > 8)
				{
					--c.dex;
					Main_Gui.gen.points += (c.dex < 13 ? 1 : 2);
				}
			}
			else if (b == b_con_down)
			{
				if (c.con > 8)
				{
					--c.con;
					Main_Gui.gen.points += (c.con < 13 ? 1 : 2);
				}
			}
			else if (b == b_int_down)
			{
				if (c.intel > 8)
				{
					--c.intel;
					Main_Gui.gen.points += (c.intel < 13 ? 1 : 2);
				}
			}
			else if (b == b_wis_down)
			{
				if (c.wis > 8)
				{
					--c.wis;
					Main_Gui.gen.points += (c.wis < 13 ? 1 : 2);
				}
			}
			else if (b == b_cha_down)
			{
				if (c.cha > 8)
				{
					--c.cha;
					Main_Gui.gen.points += (c.cha < 13 ? 1 : 2);
				}
			}
		}
		updateText();
	}
}
