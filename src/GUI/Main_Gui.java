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
	public static Stage stage;
	public static Scene menu, charsheet, inventory, gen_start, gen_stats_rand, gen_stats_array, gen_stats_pointbuy, gen_char_setup;
	protected static GenStats_Array_Controller statsarr_controller;
	protected static GenStats_PointBuy_Controller statspoint_controller;
	protected static GenStats_Random_Controller statsrand_controller;
	public static GenStartController genstart_controller;
	public static MainMenuController main_controller;
	protected static GenCharSetupController charsetup_controller;
	public static CharGen gen;
	
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
				main_controller = loader.getController();
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/Gen_Start.fxml");
				loader.setLocation(u);
				gen_start = new Scene(loader.load());
				genstart_controller = loader.getController();
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/Gen_Stats_Array.fxml");
				loader.setLocation(u);
				gen_stats_array = new Scene(loader.load());
				statsarr_controller = loader.getController();
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/Gen_Stats_PointBuy.fxml");
				loader.setLocation(u);
				gen_stats_pointbuy = new Scene(loader.load());
				statspoint_controller = loader.getController();
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/Gen_Stats_Random.fxml");
				loader.setLocation(u);
				gen_stats_rand = new Scene(loader.load());
				statsrand_controller = loader.getController();
			}
			
			{
				FXMLLoader loader = new FXMLLoader();
				URL u = getClass().getResource("/GUI/GenCharSetup.fxml");
				loader.setLocation(u);
				gen_char_setup = new Scene(loader.load());
				charsetup_controller = loader.getController();
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