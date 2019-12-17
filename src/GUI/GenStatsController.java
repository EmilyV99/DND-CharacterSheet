package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GenStatsController
{
	@FXML
	public void onClose(ActionEvent e)
	{
		if(Main_Gui.gen.back())
			Main_Gui.stage.setScene(Main_Gui.gen_start);
	}
	@FXML
	public void onNext(ActionEvent e)
	{
	
	}
}
