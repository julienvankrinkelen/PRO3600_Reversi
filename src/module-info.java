module pro3600_reversi {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	opens application to javafx.graphics, javafx.fxml;
}