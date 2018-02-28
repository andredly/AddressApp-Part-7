package ch.makery.address.view;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.ServerData;
import ch.makery.address.model.TypeEnvironment;
import ch.makery.address.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;

import java.io.File;

public class ServerTabOverviewController {

	@FXML
	private ListView<ServerData> listServer;

	@FXML
	private TextField serverPath;

	@FXML
	private TextField host;

	@FXML
	private Button path;

	@FXML
	private TextField port;

	@FXML
	private ComboBox<TypeEnvironment> typeEnvironment;

	@FXML
	private RadioButton full;

	@FXML
	private RadioButton content;

	@FXML
	private RadioButton bundle;

	@FXML
	private ComboBox<String> bundleNames;

	@FXML
	private RadioButton skipTest;

	@FXML
	private Button deploy;

	@FXML
	private Button saveChange;

	@FXML
	private CheckBox installPackage;

	@FXML
	private CheckBox installLocal;

	private final ToggleGroup group = new ToggleGroup();



	@FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public ServerTabOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
		listServer.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		listServer.getFocusModel().focus(0);

        showSeverDetails(null);

		listServer.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showSeverDetails(newValue));
		full.setToggleGroup(group);
		content.setToggleGroup(group);
		bundle.setToggleGroup(group);
    }

	@FXML
	private void handleAddServer() {
    	ServerData serverData = new ServerData();
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Dialogs.create()
					.title("No Selection")
					.masthead("No Person Selected")
					.message("Please select a person in the table.")
					.showWarning();
		}
	}

	@FXML
	private void handleOpen() {

		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("AEM Projects");
//		File defaultDirectory = new File("c:/dev/javafx");
//		chooser.setInitialDirectory(defaultDirectory);
		File selectedDirectory = chooser.showDialog(new Stage());
	}

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
//        personTable.setItems(mainApp.getPersonData());
        listServer.setItems(mainApp.getServerDataList());


		showSeverDetails(mainApp.getServerDataList().get(0));
    }


	private void showSeverDetails(ServerData server) {
		if (server != null) {
			// Fill the labels with info from the person object.
			serverPath.setText(server.getProjectPath());
			host.setText(server.getHost());
			port.setText(Integer.toString(server.getPort()));
			host.setText(server.getHost());
			full.setSelected(true);
			typeEnvironment.setItems(FXCollections.observableArrayList(TypeEnvironment.values()));
			bundleNames.setItems(FXCollections.observableArrayList(server.getBundleNames()));
			skipTest.setSelected(true);
			installPackage.setSelected(true);
			installLocal.setSelected(true);
		} else {
			// Person is null, remove all the text.
//			firstNameLabel.setText("");
//			lastNameLabel.setText("");
//			streetLabel.setText("");
//			postalCodeLabel.setText("");
//			cityLabel.setText("");
//			birthdayLabel.setText("");
		}
	}
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
    	if (person != null) {
    		// Fill the labels with info from the person object.
    		firstNameLabel.setText(person.getFirstName());
    		lastNameLabel.setText(person.getLastName());
    		streetLabel.setText(person.getStreet());
    		postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
    		cityLabel.setText(person.getCity());
    		birthdayLabel.setText(DateUtil.format(person.getBirthday()));
    	} else {
    		// Person is null, remove all the text.
    		firstNameLabel.setText("");
    		lastNameLabel.setText("");
    		streetLabel.setText("");
    		postalCodeLabel.setText("");
    		cityLabel.setText("");
    		birthdayLabel.setText("");
    	}
    }

	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nothing selected.
			Dialogs.create()
		        .title("No Selection")
		        .masthead("No Person Selected")
		        .message("Please select a person in the table.")
		        .showWarning();
		}
	}
	
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Nothing selected.
			Dialogs.create()
				.title("No Selection")
				.masthead("No Person Selected")
				.message("Please select a person in the table.")
				.showWarning();
		}
	}
}