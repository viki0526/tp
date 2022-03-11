package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import javafx.scene.paint.Color;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane educations;
    @FXML
    private FlowPane internships;
    @FXML
    private FlowPane modules;
    @FXML
    private FlowPane ccas;
    @FXML
    private Label l;
    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        educations.setHgap(8);
        educations.setVgap(8);
        internships.setHgap(8);
        internships.setVgap(8);
        modules.setHgap(8);
        modules.setVgap(8);
        ccas.setHgap(8);
        ccas.setVgap(8);
        person.getEducations().stream()
                .sorted(Comparator.comparing(edu -> edu.tagName))
                .forEach(edu -> educations.getChildren().add(new TagLabel(edu).getTagLabel()));
        person.getInternships().stream()
                .sorted(Comparator.comparing(intern -> intern.tagName))
                .forEach(intern -> internships.getChildren().add(new TagLabel(intern).getTagLabel()));
        person.getModules().stream()
                .sorted(Comparator.comparing(module -> module.tagName))
                .forEach(module -> modules.getChildren().add(new TagLabel(module).getTagLabel()));
        person.getCcas().stream()
                .sorted(Comparator.comparing(cca -> cca.tagName))
                .forEach(cca -> ccas.getChildren().add(new TagLabel(cca).getTagLabel()));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
