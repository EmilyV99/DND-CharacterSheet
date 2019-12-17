package GUI;

import DND.CharGen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main_Gui extends Application
{
	protected static Stage stage;
	protected static Scene menu, charsheet, inventory, gen_start, gen_stats;
	protected static CharGen gen;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		Main_Gui.stage = stage;
		try
		{
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/MainMenu.fxml");
				loader.setLocation(u);
				menu = new Scene(loader.load());
				System.out.println("Loaded Menu");
				System.out.println(menu);
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/Gen_Start.fxml");
				loader.setLocation(u);
				gen_start = new Scene(loader.load());
				System.out.println("Loaded gen_start");
				System.out.println(gen_start);
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/Gen_Stats.fxml");
				loader.setLocation(u);
				gen_stats = new Scene(loader.load());
				System.out.println("Loaded gen_stats");
				System.out.println(gen_stats);
			}
			
			stage.setTitle("D&D Digital DND.Character Sheet");
			stage.setWidth(600);
			stage.setHeight(400);
			stage.setMaxWidth(1200);
			stage.setMaxHeight(800);
			stage.setResizable(true);
			stage.setScene(menu);
			stage.setOnCloseRequest(windowEvent ->
			                        {
				                        System.out.println("Closing!");
				                        System.exit(0);
			                        });
			stage.show();
		}
		catch (IOException e)
		{
			System.err.println("Error in main Start method!");
			e.printStackTrace();
			System.exit(1);
		}
	}
}