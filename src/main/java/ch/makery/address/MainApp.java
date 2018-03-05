package ch.makery.address;

import ch.makery.address.model.*;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.RootWindowController;
import ch.makery.address.view.ServerTabOverviewController;
import ch.makery.address.view.TabOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.prefs.Preferences;

public class MainApp extends Application {

	private Stage primaryStage;
	private VBox vBox;
	private TabPane tabOverview;

	/**
	 * The data as an observable list of Persons.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	private ObservableList<ServerData> serverDataList = FXCollections.observableArrayList();

	/**
	 * Constructor
	 */
	public MainApp() {

		// Add some sample data
		ServerData serverData = new ServerData();
		serverData.setProjectPath("");
		serverData.setServerName("name");
		serverData.setHost("host");
		serverData.setBundleName("");
		serverData.setTypeDeployRadioButton(TypeDeployRadioButton.FULL);
//		serverData.setTypeEnvironment(TypeEnvironment.PUBLISH);
		ServerData serverData1 = new ServerData();
		serverData1.setProjectPath("");
		serverData1.setServerName("name1");
		serverData1.setHost("host1");
		serverData1.setBundleName("");
		serverData1.setTypeDeployRadioButton(TypeDeployRadioButton.FULL);
		serverData1.setTypeEnvironment(TypeEnvironment.AUTHOR);
		serverDataList.add(serverData);
		serverDataList.add(serverData1);


		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Martin", "Mueller"));
	}

	@Override
	public void start(Stage primaryStage) throws ParserConfigurationException, SAXException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Server operations");

		// Set the application icon.
		this.primaryStage.getIcons().add(
				new Image("file:resources/images/address_book_32.png"));

		initRootLayout();

		showTabOverview();
		showServerTabOverview();
	}

	/**
	 * Initializes the root layout and tries to load the last opened
	 * person file.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/RootWindow.fxml"));
			vBox = (VBox) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(vBox);
			primaryStage.setScene(scene);

			// Give the controller access to the main app.
			RootWindowController controller = loader.getController();
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Try to load last opened person file.
		File file = getPersonFilePath();
		if (file != null) {
			loadPersonDataFromFile(file);
		}
	}

	/**
	 * Shows the person overview inside the root layout.
	 */
	public void showTabOverview() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/TabOverview.fxml"));
			tabOverview = (TabPane) loader.load();

			// Set person overview into the center of root layout.
			vBox.getChildren().addAll(tabOverview);

			// Give the controller access to the main app.
			TabOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void showServerTabOverview() throws ParserConfigurationException, SAXException {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/ServerTabOverview.fxml"));
			AnchorPane serverTabOverview = (AnchorPane) loader.load();

			// Set person overview into the center of root layout.
			tabOverview.getTabs().get(0).setContent(serverTabOverview);

			// Give the controller access to the main app.
			ServerTabOverviewController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person
	 *            the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showPersonEditDialog(Person person) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("/PersonEditDialog.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Person");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			PersonEditDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setPerson(person);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Opens a dialog to show birthday statistics.
	 */
//	public void showBirthdayStatistics() {
//		try {
//			// Load the fxml file and create a new stage for the popup.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
//			AnchorPane page = (AnchorPane) loader.load();
//			Stage dialogStage = new Stage();
//			dialogStage.setTitle("Birthday Statistics");
//			dialogStage.initModality(Modality.WINDOW_MODAL);
//			dialogStage.initOwner(primaryStage);
//			dialogStage.getIcons().add(new Image("file:resources/images/calendar.png"));
//			Scene scene = new Scene(page);
//			dialogStage.setScene(scene);
//
//			// Set the persons into the controller.
//			BirthdayStatisticsController controller = loader.getController();
//			controller.setPersonData(personData);
//
//			dialogStage.show();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Returns the person file preference, i.e. the file that was last opened.
	 * The preference is read from the OS specific registry. If no such
	 * preference can be found, null is returned.
	 * 
	 * @return
	 */
	public File getPersonFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	/**
	 * Sets the file path of the currently loaded file. The path is persisted in
	 * the OS specific registry.
	 * 
	 * @param file the file or null to remove the path
	 */
	public void setPersonFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());

			// Update the stage title.
			primaryStage.setTitle("AddressApp - " + file.getName());
		} else {
			prefs.remove("filePath");

			// Update the stage title.
			primaryStage.setTitle("AddressApp");
		}
	}

	/**
	 * Loads person data from the specified file. The current person data will
	 * be replaced.
	 * 
	 * @param file
	 */
	public void loadPersonDataFromFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			// Reading XML from the file and unmarshalling.
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

			personData.clear();
			personData.addAll(wrapper.getPersons());

			// Save the file path to the registry.
			setPersonFilePath(file);

		} catch (Exception e) { // catches ANY exception

		}
	}

	/**
	 * Saves the current person data to the specified file.
	 * 
	 * @param file
	 */
	public void savePersonDataToFile(File file) {
		try {
			JAXBContext context = JAXBContext
					.newInstance(PersonListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Wrapping our person data.
			PersonListWrapper wrapper = new PersonListWrapper();
			wrapper.setPersons(personData);

			// Marshalling and saving XML to the file.
			m.marshal(wrapper, file);
			
			// Save the file path to the registry.
			setPersonFilePath(file);
		} catch (Exception e) { // catches ANY exception

		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * Returns the data as an observable list of Persons.
	 * 
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
		return personData;
	}

	public ObservableList<ServerData> getServerDataList() {
		return serverDataList;
	}

	public static void main(String[] args) {
		launch(args);
	}
}