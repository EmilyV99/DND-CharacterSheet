import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class Main_Gui extends Application
{
	Scene menu, charsheet, inventory, gen_start, gen_stats;

	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		FXMLLoader loader = new FXMLLoader();
		try
		{
			URL mainMenuURL = getClass().getResource("/MainMenu.fxml");
			loader.setLocation(mainMenuURL);
			menu = new Scene(loader.load());
			
			
			stage.setTitle("D&D Digital Character Sheet");
			stage.setWidth(400);
			stage.setHeight(400);
			stage.setMaxWidth(800);
			stage.setMaxHeight(800);
			stage.setResizable(true);
			stage.setScene(menu);
			stage.show();
		}
		catch(IOException e)
		{
			System.err.println("Error in main Start method!");
			e.printStackTrace();
		}
	}
}
