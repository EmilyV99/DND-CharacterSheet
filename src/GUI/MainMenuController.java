package GUI;

import DND.CharGen;
import DND.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainMenuController
{
	@FXML
	ListView<Character> charlist;
	public void refreshChars()
	{
		charlist.getItems().removeAll(charlist.getItems());
		charlist.getItems().addAll(Character.loaded);
	}
	@FXML
	public void onNewChar(ActionEvent e)
	{
		Main_Gui.gen = CharGen.init();
		Main_Gui.genstart_controller.typechoice.setValue(CharGen.getStatStyleString(Main_Gui.gen.statStyle));
		Main_Gui.stage.setScene(Main_Gui.gen_start);
	}
	@FXML
	public void onLoadChar(ActionEvent e)
	{
		System.err.println("Loading is not yet implemented!");
	}
}
