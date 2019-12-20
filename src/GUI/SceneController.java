package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;

import java.util.ArrayList;

public abstract class SceneController
{
	protected static ArrayList<SceneController> controllers = new ArrayList<>();
	protected boolean initialized = false;
	private Scene scene;
	
	public SceneController()
	{
		controllers.add(this);
	}
	
	public static SceneController getController(Scene scene)
	{
		for (SceneController sc : controllers)
			if (sc.scene == scene)
				return sc;
		return null;
	}
	
	public Scene getScene()
	{
		return scene;
	}
	
	protected void setScene(Scene s)
	{
		scene = s;
	}
	
	@FXML
	public abstract void updateText(Event e);
	public void updateText()
	{
		updateText(null);
	}
	
	@FXML
	public abstract void onNext(ActionEvent e);
	
	@FXML
	public abstract void onClose(ActionEvent e);
	
	public boolean init()
	{
		if(initialized) return true;
		initialized = true;
		return false;
	}
	
	@FXML
	public void onReset(ActionEvent e)
	{
		reset();
	}
	
	public void switchTo()
	{
		updateText();
	}
	
	public void reset()
	{
		updateText(null);
	}
}
