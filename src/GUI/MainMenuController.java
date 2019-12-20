package GUI;

import DND.CharGen;
import DND.Character;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainMenuController extends SceneController
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
		Main_Gui.setScene(Main_Gui.gen_start);
	}
	@FXML
	public void onLoadChar(ActionEvent e)
	{
		System.err.println("Loading is not yet implemented!");
	}
	
	@Override
	public void updateText(Event e)
	{
	
	}
	
	@Override
	public void onNext(ActionEvent e)
	{
	
	}
	
	@Override
	public void onClose(ActionEvent e)
	{
	
	}
}
