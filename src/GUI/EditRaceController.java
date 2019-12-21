package GUI;

import DND.Character;
import DND.Race;
import GUI.Helpers.GenericGuiHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;

public class EditRaceController extends SceneController
{
	@FXML
	Label nameReq, strReq, dexReq, conReq, wisReq, intelReq, chaReq, assignableReq, assignableLabel,
			descriptionReq, description_physicalReq, description_ageReq, description_societyReq, trait_ageReq, trait_alignmentReq, trait_sizeReq, trait_speedReq, languageReq;
	@FXML
	TextField name, str, dex, con, wis, intel, cha, assignable;
	@FXML
	TextArea description, description_physical, description_age, description_society, trait_age, trait_alignment, trait_size, trait_speed, language, traitEdit, traitViewBox;
	@FXML
	Label mode;
	@FXML
	Button confirm, cancel, editAll, editCopy, delete, newRace,
		addTrait, editTrait, deleteTrait, saveTrait, cancelTrait;
	@FXML
	ChoiceBox<Race> raceChoice;
	@FXML
	ListView<String> traitList;
	@FXML
	AnchorPane traitEditPane;
	private Scene returnScene;
	private Race loadedRace;
	private int loadedRaceIndex = -1;
	private Race cancelToRace;
	private boolean editing = false;
	private int focusedTrait, editingTrait;
	private Node[] disable_fields;
	public Character currChar;
	
	public boolean init()
	{
		if (super.init()) return true;
		disable_fields = new Node[]{name, description, description_physical, description_age, description_society, trait_age, trait_alignment, trait_size,
		                            trait_speed, language, str, dex, con, intel, wis, cha, assignable, addTrait};
		raceChoice.setOnAction(foo -> loadRace(raceChoice.getValue()));
		GenericGuiHelper.filterTextField(name, GenericGuiHelper.NAMES, 25, "[^\n\t]+", nameReq);
		GenericGuiHelper.filterTextArea(description, "[^\n]*", 0, "[^\n]+", descriptionReq);
		GenericGuiHelper.filterTextArea(description_physical, "[^\n]*", 0, "[^\n]+", description_physicalReq);
		GenericGuiHelper.filterTextArea(description_age, "[^\n]*", 0, "[^\n]+", description_ageReq);
		GenericGuiHelper.filterTextArea(description_society, "[^\n]*", 0, "[^\n]+", description_societyReq);
		GenericGuiHelper.filterTextArea(trait_age, "[^\n]*", 0, "[^\n]+", trait_ageReq);
		GenericGuiHelper.filterTextArea(trait_alignment, "[^\n]*", 0, "[^\n]+", trait_alignmentReq);
		GenericGuiHelper.filterTextArea(trait_size, "[^\n]*", 0, "[^\n]+", trait_sizeReq);
		GenericGuiHelper.filterTextArea(trait_speed, "[^\n]*", 0, "[^\n]+", trait_speedReq);
		GenericGuiHelper.filterTextArea(language, "[^\n]*", 0, "[^\n]+", languageReq);
		GenericGuiHelper.filterIntegerTextField(str, 0, 20, 0, ".+", strReq);
		GenericGuiHelper.filterIntegerTextField(dex, 0, 20, 0, ".+", dexReq);
		GenericGuiHelper.filterIntegerTextField(con, 0, 20, 0, ".+", conReq);
		GenericGuiHelper.filterIntegerTextField(intel, 0, 20, 0, ".+", intelReq);
		GenericGuiHelper.filterIntegerTextField(wis, 0, 20, 0, ".+", wisReq);
		GenericGuiHelper.filterIntegerTextField(cha, 0, 20, 0, ".+", chaReq);
		GenericGuiHelper.filterIntegerTextField(assignable, 0, 20, 0, ".+", assignableReq);
		editAll.setTooltip(GenericGuiHelper.instaTT("Edits this Race, affecting ALL loaded characters using this race."));
		editCopy.setTooltip(GenericGuiHelper.instaTT("Edits a copy of this Race, which can be assigned to characters."));
		delete.setTooltip(GenericGuiHelper.instaTT("Deletes this race. Only works if no loaded characters use this Race."));
		assignableLabel.setTooltip(GenericGuiHelper.instaTT("This many stats of the player's choice get +1 for this Race."));
		return false;
	}
	
	public void setup(Scene returnto, Race race, Character c)
	{
		returnScene = returnto;
		currChar = c;
		resetRaces(race);
	}
	
	public void resetRaces(Race race)
	{
		raceChoice.getItems().clear();
		raceChoice.getItems().addAll(Race.packRaces);
		raceChoice.getItems().addAll(Race.customRaces);
		loadRace(race);
		raceChoice.setValue(loadedRace);
		updateText();
	}
	
	public void onEditAll(ActionEvent e)
	{
		setEditing(true);
	}
	public void onEditCopy(ActionEvent e)
	{
		cancelToRace = loadedRace;
		loadedRace = loadedRace.copy();
		loadedRaceIndex = -1;
		setEditing(true);
	}
	public void onDelete(ActionEvent e)
	{
		Race.customRaces.remove(loadedRace);
		if(currChar!=null && currChar.race == loadedRace) currChar.race = null;
		resetRaces(Race.getRace(loadedRaceIndex-1));
	}
	public void onNew(ActionEvent e)
	{
		cancelToRace = loadedRace;
		loadedRace = new Race();
		loadedRaceIndex = -1;
		clear();
		setEditing(true);
	}
	
	private void clear()
	{
		name.setText("");
		description.setText("");
		description_physical.setText("");
		description_age.setText("");
		description_society.setText("");
		trait_age.setText("");
		trait_alignment.setText("");
		trait_size.setText("");
		trait_speed.setText("");
		language.setText("");
		str.setText("0");
		dex.setText("0");
		con.setText("0");
		intel.setText("0");
		wis.setText("0");
		cha.setText("0");
		assignable.setText("0");
		traitList.getItems().clear();
	}
	
	public void loadRace(Race race)
	{
		if (race == null)
		{
			loadedRaceIndex = -1;
			loadedRace = null;
		}
		else
		{
			loadedRaceIndex = Race.getIndex(race);
			if (loadedRaceIndex == -1)
			{
				Race.customRaces.add(race);
				loadedRaceIndex = Race.getIndex(race);
				loadedRace = Race.getRace(loadedRaceIndex);
			}
			else
			{
				loadedRace = race;
			}
			traitList.getItems().clear();
			traitList.getItems().addAll(loadedRace.traits);
		}
		updateText();
	}
	
	@SuppressWarnings("DuplicatedCode")
	@FXML
	public void updateText(Event e)
	{
		if(editing)
		{
			disable(delete, true);
			disable(editAll, true);
			disable(editCopy, true);
			disable(newRace, true);
			disable(confirm, isInvalid());
			disable(cancel, false);
		}
		else
		{
			disable(cancel, currChar!=null && currChar.race == null);
			disable(newRace, false);
			if (loadedRace == null)
			{
				clear();
				disable(delete, true);
				disable(editAll, true);
				disable(editCopy, true);
			}
			else
			{
				name.setText(loadedRace.name);
				description.setText(loadedRace.description);
				description_physical.setText(loadedRace.description_physical);
				description_age.setText(loadedRace.description_age);
				description_society.setText(loadedRace.description_society);
				trait_age.setText(loadedRace.trait_age);
				trait_alignment.setText(loadedRace.trait_alignment);
				trait_size.setText(loadedRace.trait_size);
				trait_speed.setText(loadedRace.trait_speed);
				language.setText(loadedRace.languages);
				str.setText(""+loadedRace.str);
				dex.setText(""+loadedRace.dex);
				con.setText(""+loadedRace.con);
				intel.setText(""+loadedRace.intel);
				wis.setText(""+loadedRace.wis);
				cha.setText(""+loadedRace.cha);
				assignable.setText(""+loadedRace.assignable_points);
				disable(editCopy, false);
				boolean doDisable = false;
				if(Arrays.asList(Race.packRaces).contains(loadedRace))
					doDisable = true;
				disable(editAll, doDisable); //Don't allow editing packed races directly!
				if(!doDisable)
				{
					for (Character c : Character.loaded)
					{
						if (c.race == loadedRace)
						{
							doDisable = true;
							break;
						}
					}
				}
				disable(delete, doDisable);
			}
		}
		updateFocus(null);
	}
	@FXML
	public void updateList(Event e)
	{
		focusedTrait = traitList.getFocusModel().getFocusedIndex();
		if(traitList.getItems().size() > focusedTrait)
			traitList.getFocusModel().focus(focusedTrait);
		if(editing)
		{
			disable(editTrait, false);
			disable(deleteTrait, false);
		}
		else
		{
			disable(addTrait, true);
			disable(editTrait, true);
			disable(deleteTrait, true);
		}
		traitViewBox.setText(traitList.getItems().get(focusedTrait));
	}
	@FXML
	public void onAddTrait(Event e)
	{
		traitEditPane.setVisible(true);
		traitEdit.setText("");
		editingTrait = -1;
	}
	@FXML
	public void onEditTrait(Event e)
	{
		traitEditPane.setVisible(true);
		traitEdit.setText(traitList.getItems().get(focusedTrait));
		editingTrait = focusedTrait;
	}
	@FXML
	public void onSaveTrait(Event e)
	{
		if(editingTrait < 0)
		{
			traitList.getItems().add(traitEdit.getText());
			traitList.getFocusModel().focus(traitList.getItems().size()-1);
			traitEditPane.setVisible(false);
			updateList(null);
		}
		else
		{
			traitList.getItems().set(editingTrait, traitEdit.getText());
			traitEditPane.setVisible(false);
		}
	}
	@FXML
	public void onCancelTrait(Event e)
	{
		traitEditPane.setVisible(false);
	}
	@FXML
	public void onDeleteTrait(Event e)
	{
		traitList.getFocusModel().focusPrevious();
		traitList.getItems().remove(focusedTrait);
		updateList(null);
	}
	@Override
	public void switchTo() //ensure editing is refreshed upon entry
	{
		traitEditPane.setVisible(false);
		editing = true;
		setEditing(false);
	}
	
	public void setEditing(boolean edit)
	{
		if (editing == edit)
		{
			updateText();
			return;
		}
		editing = edit;
		disable(editTrait, true);
		disable(deleteTrait, true);
		if (edit)
		{
			mode.setText("Edit Mode");
			confirm.setText("Save");
			for (Node n : disable_fields)
			{
				disable(n, false);
			}
			disable(raceChoice, true);
		}
		else
		{
			mode.setText("View Mode");
			if (returnScene == Main_Gui.gen_char_setup)
			{
				confirm.setText("Select");
			}
			else
			{
				confirm.setText("Exit");
			}
			for (Node n : disable_fields)
			{
				disable(n, true);
			}
			disable(raceChoice, false);
		}
		updateText();
	}
	
	private void disable(Node n, boolean state)
	{
		if(n instanceof TextArea)
		{
			((TextArea) n).setEditable(!state);
			n.setFocusTraversable(!state);
		}
		else if(n instanceof TextField)
		{
			((TextField) n).setEditable(!state);
			n.setFocusTraversable(!state);
		}
		else
		{
			n.setDisable(state);
			n.setFocusTraversable(!state);
		}
	}
	
	@FXML
	public void updateFocus(Event e)
	{
		if(!traitList.isFocused() && !traitViewBox.isFocused())
		{
			focusedTrait = -1;
			disable(editTrait, true);
			disable(deleteTrait, true);
			traitViewBox.setText("");
		}
		else updateList(null);
		
	}
	
	@FXML
	@Override
	public void onClose(ActionEvent e)
	{
		if (editing)
		{
			if(cancelToRace!=null)
			{
				loadedRaceIndex = Race.getIndex(cancelToRace);
			}
			loadRace(Race.getRace(loadedRaceIndex));
			setEditing(false);
		}
		else
		{
			Main_Gui.setScene(returnScene);
			returnScene = null;
		}
	}
	
	@SuppressWarnings({"DuplicatedCode"})
	private boolean isInvalid()
	{
		TextInputControl[] text = new TextInputControl[]{name, description, description_physical, description_age, description_society, trait_age, trait_alignment, trait_size, trait_speed, language, str, dex, con, intel, wis, cha, assignable};
		for(TextInputControl t : text)
		{
			if(t.getText()==null || t.getText().equals("")) return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("DuplicatedCode")
	private void setLoadedRace()
	{
		if(isInvalid()) return;
		loadedRace.name = name.getText();
		loadedRace.description = description.getText();
		loadedRace.description_physical = description_physical.getText();
		loadedRace.description_age = description_age.getText();
		loadedRace.description_society = description_society.getText();
		loadedRace.trait_age = trait_age.getText();
		loadedRace.trait_alignment = trait_alignment.getText();
		loadedRace.trait_size = trait_size.getText();
		loadedRace.trait_speed = trait_speed.getText();
		loadedRace.languages = language.getText();
		loadedRace.str = Byte.parseByte(str.getText());
		loadedRace.dex = Byte.parseByte(dex.getText());
		loadedRace.con = Byte.parseByte(con.getText());
		loadedRace.intel = Byte.parseByte(intel.getText());
		loadedRace.wis = Byte.parseByte(wis.getText());
		loadedRace.cha = Byte.parseByte(cha.getText());
		loadedRace.assignable_points = Byte.parseByte(assignable.getText());
		loadedRace.traits.clear();
		loadedRace.traits.addAll(traitList.getItems());
	}
	
	@FXML
	@Override
	public void onNext(ActionEvent e)
	{
		if (editing)
		{
			cancelToRace = null;
			setLoadedRace();
			if (loadedRaceIndex == -1)
			{
				Race.customRaces.add(loadedRace);
			}
			else
			{
				Race.setIndex(loadedRaceIndex, loadedRace);
			}
			resetRaces(loadedRace);
			setEditing(false);
		}
		else
		{
			try
			{
				if (currChar != null)
				{
					currChar.race = loadedRace;
				}
				Main_Gui.setScene(returnScene);
			}
			catch (Exception ignored)
			{
			}
		}
	}
}
