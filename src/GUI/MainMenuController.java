package GUI;

import DND.CharGen;
import DND.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainMenuController
{
	@FXML
	ListView<String> charlist;
	public void refreshChars()
	{
		charlist.getItems().removeAll(charlist.getItems());
		for(Character c : Character.loaded)
		{
			charlist.getItems().add(c.name);
		}
	}
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
