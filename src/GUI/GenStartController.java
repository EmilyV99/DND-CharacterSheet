package GUI;

import DND.CharGen;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class GenStartController
{
	@FXML
	public ChoiceBox<String> typechoice;
	
	@FXML
	private Button nextbtn;
	
	@FXML
	public void updateTypeChoice(Event e)
	{
		nextbtn.setDisable(typechoice.getValue() == null || typechoice.getValue().equals(""));
	}
	
	private void setStatStyle()
	{
		String s = typechoice.getValue();
		if (s == null || s.equals(""))
		{
			Main_Gui.gen.statStyle = CharGen.StatStyle.NULL;
		}
		else if (s.equals("Array"))
		{
			Main_Gui.gen.statStyle = CharGen.StatStyle.ARRAY;
		}
		else if (s.equals("Roll (Assign)"))
		{
			Main_Gui.gen.statStyle = CharGen.StatStyle.ROLL_ASSIGN;
		}
		else if (s.equals("Roll (Random)"))
		{
			Main_Gui.gen.statStyle = CharGen.StatStyle.ROLL_RANDOM;
		}
		else if (s.equals("Point Buy"))
		{
			Main_Gui.gen.statStyle = CharGen.StatStyle.POINT_BUY;
		}
		else if (s.equals("Manual"))
		{
			Main_Gui.gen.statStyle = CharGen.StatStyle.MANUAL;
		}
	}
	
	@FXML
	public void onClose(ActionEvent e)
	{
		setStatStyle();
		Main_Gui.stage.setScene(Main_Gui.menu);
	}
	
	@FXML
	public void onNext(ActionEvent e)
	{
		setStatStyle();
		if (Main_Gui.gen.confirm())
		{
			switch (Main_Gui.gen.statStyle)
			{
				case POINT_BUY:
					Main_Gui.statspoint_controller.updateText();
					Main_Gui.stage.setScene(Main_Gui.gen_stats_pointbuy);
					break;
				case ROLL_RANDOM:
					Main_Gui.statsrand_controller.updateText();
					Main_Gui.stage.setScene(Main_Gui.gen_stats_rand);
					break;
				case ARRAY:
				case ROLL_ASSIGN:
					Main_Gui.statsarr_controller.updateText();
					Main_Gui.statsarr_controller.resetButtons();
					Main_Gui.stage.setScene(Main_Gui.gen_stats_array);
					break;
				case MANUAL:
					Main_Gui.statsmanual_controller.init();
					Main_Gui.statsmanual_controller.clear();
					Main_Gui.stage.setScene(Main_Gui.gen_stats_manual);
					break;
			}
		}
	}
}
