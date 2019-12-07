package controllers.modals;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Judge;
import utils.CodeGenerator;

public class AddJudgeModal extends Modal<Judge> {

    private Label nameFieldError = new Label();

    private TextField judgeNameField = new TextField();

    private boolean nameIsValid = false;

    public AddJudgeModal(String title) {
        super(title);
        VBox vbox = new VBox(
                new Label("Judge name:"),
                judgeNameField,
                nameFieldError
        );

        setContent(vbox);
    }


    @Override
    protected Judge getData() {
        return new Judge(judgeNameField.getText(), CodeGenerator.generate(4));
    }


    @Override
    protected boolean validate() {

        nameIsValid = !(judgeNameField.getText() == null || judgeNameField.getText().isEmpty());

        return nameIsValid;
    }

    @Override
    protected void showError() {
        if(!nameIsValid) nameFieldError.setText("Name can't be empty.");
    }
}
