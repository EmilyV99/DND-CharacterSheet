package GUI;

import DND.CharGen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuController
{
	@FXML
	public void onNewChar(ActionEvent e)
	{
		Main_Gui.gen = CharGen.init();
		Main_Gui.stage.setScene(Main_Gui.gen_start);
	}
	@FXML
	public void onLoadChar(ActionEvent e)
	{
		System.err.println("Loading is not yet implemented!");
	}
}
