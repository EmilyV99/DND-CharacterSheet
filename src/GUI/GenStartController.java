package GUI;

import DND.CharGen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class GenStartController
{
	@FXML
	private ChoiceBox<String> typechoice;
	
	@FXML
	public void onClose(ActionEvent e)
	{
		Main_Gui.stage.setScene(Main_Gui.menu);
	}
	
	@FXML
	public void onNext(ActionEvent e)
	{
		String s = typechoice.getValue();
		if(s == null || s.equals(""))
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
		if (Main_Gui.gen.confirm())
			Main_Gui.stage.setScene(Main_Gui.gen_stats);
	}
}
