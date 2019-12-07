package controllers.layouts;

import components.cells.QuestionCell;
import components.cells.TeamCell;
import controllers.modals.AddTeamModal;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import manager.QuestionManager;
import manager.TeamManager;
import model.Team;

public class TeamsController {

    @FXML
    private ListView<Team> teamListView;

    @FXML
    private void initialize(){

        teamListView.setCellFactory( callback -> {
            TeamCell teamCell = new TeamCell();
            teamCell.onRemove(data -> {
                TeamManager.getInstance().remove(data);
            });

            return teamCell;
        });

        teamListView.setItems(TeamManager.getInstance().getTeamList());
    }

    @FXML
    private void showModal(){

        AddTeamModal modal = new AddTeamModal("Add Team");

        modal.onData( data -> {
            if(TeamManager.getInstance().contains(data)){

                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);

                    alert.setTitle("Team Already Exists");
                    alert.setHeaderText("A team with this name already exists.");
                    alert.showAndWait();
                });

                return;
            }

            TeamManager.getInstance().add(data);
        });

        modal.show();
    }
}
