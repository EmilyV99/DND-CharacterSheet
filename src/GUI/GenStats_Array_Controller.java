package GUI;

import DND.CharGen;
import DND.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class GenStats_Array_Controller
{
	@FXML
	Text strength, dexterity, constitution, intelligence, wisdom, charisma, instr;
	@FXML
	Button nextbtn, reset, reroll, b_str, b_dex, b_con, b_int, b_wis, b_cha, b_0, b_1, b_2, b_3, b_4, b_5;
	byte pressedIndex = -1, numChosen = 0;
	
	public void resetButtons()
	{
		b_str.setDisable(false);
		b_dex.setDisable(false);
		b_con.setDisable(false);
		b_int.setDisable(false);
		b_wis.setDisable(false);
		b_cha.setDisable(false);
		b_0.setDisable(false);
		b_1.setDisable(false);
		b_2.setDisable(false);
		b_3.setDisable(false);
		b_4.setDisable(false);
		b_5.setDisable(false);
		b_0.setMinWidth(29.6);
		b_1.setMinWidth(29.6);
		b_2.setMinWidth(29.6);
		b_3.setMinWidth(29.6);
		b_4.setMinWidth(29.6);
		b_5.setMinWidth(29.6);
		numChosen = 0;
		nextbtn.setDisable(true);
		pressedIndex = -1;
		reroll.setVisible(Main_Gui.gen.statStyle != CharGen.StatStyle.ARRAY);
	}
	
	public void updateText()
	{
		Character c = Main_Gui.gen.c;
		strength.setText("" + c.str);
		dexterity.setText("" + c.dex);
		constitution.setText("" + c.con);
		intelligence.setText("" + c.intel);
		wisdom.setText("" + c.wis);
		charisma.setText("" + c.cha);
		b_0.setText("" + Main_Gui.gen.statsarr[0]);
		b_1.setText("" + Main_Gui.gen.statsarr[1]);
		b_2.setText("" + Main_Gui.gen.statsarr[2]);
		b_3.setText("" + Main_Gui.gen.statsarr[3]);
		b_4.setText("" + Main_Gui.gen.statsarr[4]);
		b_5.setText("" + Main_Gui.gen.statsarr[5]);
		if (numChosen == 6)
			instr.setText("All stats assigned. Please continue, " + (Main_Gui.gen.statStyle == CharGen.StatStyle.ARRAY ? "or reset." : "reset, or re-roll."));
		else if (pressedIndex == -1)
			instr.setText("Please select a number value, to assign to a stat.");
		else
			instr.setText("Please select a stat to place '" + Main_Gui.gen.statsarr[pressedIndex] + "' in.");
	}
	
	@FXML
	public void onReset(ActionEvent e)
	{
		Character c = Main_Gui.gen.c;
		c.str = 0;
		c.dex = 0;
		c.con = 0;
		c.intel = 0;
		c.wis = 0;
		c.cha = 0;
		resetButtons();
		updateText();
	}
	
	@FXML
	public void onReroll(ActionEvent e)
	{
		Main_Gui.gen.initStats();
		resetButtons();
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
			Main_Gui.charsetup_controller.init();
			Main_Gui.charsetup_controller.clear();
			Main_Gui.stage.setScene(Main_Gui.gen_char_setup);
		}
	}
	
	@FXML
	public void onStatNum(ActionEvent e)
	{
		if (pressedIndex > -1)
			return;
		if (e.getTarget() instanceof Button)
		{
			Button b = (Button) e.getTarget();
			b.setDisable(true);
			if (b == b_0)
				pressedIndex = 0;
			else if (b == b_1)
				pressedIndex = 1;
			else if (b == b_2)
				pressedIndex = 2;
			else if (b == b_3)
				pressedIndex = 3;
			else if (b == b_4)
				pressedIndex = 4;
			else if (b == b_5)
				pressedIndex = 5;
			updateText();
		}
	}
	
	@FXML
	public void onStatType(ActionEvent e)
	{
		if (pressedIndex == -1)
			return;
		if (e.getTarget() instanceof Button)
		{
			Button b = (Button) e.getTarget();
			b.setDisable(true);
			Character c = Main_Gui.gen.c;
			if (b == b_str)
				c.str = Main_Gui.gen.statsarr[pressedIndex];
			else if (b == b_dex)
				c.dex = Main_Gui.gen.statsarr[pressedIndex];
			else if (b == b_con)
				c.con = Main_Gui.gen.statsarr[pressedIndex];
			else if (b == b_int)
				c.intel = Main_Gui.gen.statsarr[pressedIndex];
			else if (b == b_wis)
				c.wis = Main_Gui.gen.statsarr[pressedIndex];
			else if (b == b_cha)
				c.cha = Main_Gui.gen.statsarr[pressedIndex];
			pressedIndex = -1;
			++numChosen;
			if (numChosen == 5)
				chooseFinal();
			updateText();
		}
	}
	
	private void chooseFinal()
	{
		if(numChosen != 5) return;
		byte ind = -1;
		if (!b_0.isDisabled())
		{
			ind = 0;
			b_0.setDisable(true);
		}
		else if (!b_1.isDisabled())
		{
			ind = 1;
			b_1.setDisable(true);
		}
		else if (!b_2.isDisabled())
		{
			ind = 2;
			b_2.setDisable(true);
		}
		else if (!b_3.isDisabled())
		{
			ind = 3;
			b_3.setDisable(true);
		}
		else if (!b_4.isDisabled())
		{
			ind = 4;
			b_4.setDisable(true);
		}
		else if (!b_5.isDisabled())
		{
			ind = 5;
			b_5.setDisable(true);
		}
		Character c = Main_Gui.gen.c;
		if (!b_str.isDisabled())
		{
			c.str = Main_Gui.gen.statsarr[ind];
			b_str.setDisable(true);
		}
		else if (!b_dex.isDisabled())
		{
			c.dex = Main_Gui.gen.statsarr[ind];
			b_dex.setDisable(true);
		}
		else if (!b_con.isDisabled())
		{
			c.con = Main_Gui.gen.statsarr[ind];
			b_con.setDisable(true);
		}
		else if (!b_int.isDisabled())
		{
			c.intel = Main_Gui.gen.statsarr[ind];
			b_int.setDisable(true);
		}
		else if (!b_wis.isDisabled())
		{
			c.wis = Main_Gui.gen.statsarr[ind];
			b_wis.setDisable(true);
		}
		else if (!b_cha.isDisabled())
		{
			c.cha = Main_Gui.gen.statsarr[ind];
			b_cha.setDisable(true);
		}
		++numChosen;
		nextbtn.setDisable(false);
	}
}
