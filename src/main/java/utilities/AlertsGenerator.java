package utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class AlertsGenerator {

    public static Alert createConfirmationDialog() {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setContentText("");
        alertDialog.setTitle("Confirmation");
        alertDialog.setHeaderText("Are you sure from exiting ?");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);


        alertDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);

        return alertDialog;
    }

    public static Alert createWarningDialog() {
        Alert alertDialog = new Alert(Alert.AlertType.WARNING);
        alertDialog.setContentText("");
        alertDialog.setTitle("Warning");
        alertDialog.setHeaderText("Cannot connect to the server.....");
        return alertDialog;
    }

    public static Alert createGameInvitationDialog(String opponentName) {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setContentText("");
        alertDialog.setTitle("Confirmation");
        alertDialog.setHeaderText(opponentName + " is challenging you");

        ButtonType buttonTypeYes = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alertDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        return alertDialog;

    }

    public static Alert createSaveGameInvitationDialog(String opponentName) {
        Alert alertDialog = new Alert(Alert.AlertType.CONFIRMATION);
        alertDialog.setContentText("");
        alertDialog.setTitle("Confirmation");
        alertDialog.setHeaderText(opponentName + " is request to record the game");
        ButtonType buttonTypeYes = new ButtonType("Accept", ButtonBar.ButtonData.OK_DONE);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alertDialog.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        return alertDialog;
    }
}
