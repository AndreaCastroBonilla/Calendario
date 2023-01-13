package dad.custom.components;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CustomComponent extends GridPane implements Initializable {

	private static final DateFormat FORMATTER = new SimpleDateFormat("MMMM");

	// model
	private IntegerProperty monthProperty = new SimpleIntegerProperty();
	private IntegerProperty yearProperty = new SimpleIntegerProperty();

	// view
	@FXML
	private Label monthLabel;

	@FXML
	private List<Label> daysLabelList;

	public CustomComponent() {
		super();
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CustomComponent.fxml"));
			loader.setController(this);
//			loader.setRoot(this);
			loader.load();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.monthProperty.addListener((o, ov, nv) -> onDateChanged(o, ov, nv));
		this.yearProperty.addListener((o, ov, nv) -> onDateChanged(o, ov, nv));

	}

	private void onDateChanged(ObservableValue<? extends Number> o, Number ov, Number nv) {

		int first = DateUtils.firstDay(yearProperty.get(), monthProperty.get()) - 1;
		int last = DateUtils.lastDay(yearProperty.get(), monthProperty.get());
		for (int i = 0; i < first; i++) {
			daysLabelList.get(i).setText("");
			daysLabelList.get(i).getStyleClass().remove("today");
		}
		for (int i = first, j = 1; i < first + last; i++, j++) {
			daysLabelList.get(i).setText("" + j);
			if (LocalDate.of(yearProperty.get(), monthProperty.get(), j).equals(LocalDate.now())) {
				daysLabelList.get(i).getStyleClass().add("today");
			} else {
				daysLabelList.get(i).getStyleClass().remove("today");
			}
		}
		for (int i = first + last; i < daysLabelList.size(); i++) {
			daysLabelList.get(i).setText("");
			daysLabelList.get(i).getStyleClass().remove("today");
		}
		Date day = DateUtils.newDate(yearProperty.get(), monthProperty.get(), 1);
		monthLabel.setText(FORMATTER.format(day));

	}

	public final IntegerProperty monthPropertyProperty() {
		return this.monthProperty;
	}

	public final int getMonthProperty() {
		return this.monthPropertyProperty().get();
	}

	public final void setMonthProperty(final int monthProperty) {
		this.monthPropertyProperty().set(monthProperty);
	}

	public final IntegerProperty yearPropertyProperty() {
		return this.yearProperty;
	}

	public final int getYearProperty() {
		return this.yearPropertyProperty().get();
	}

	public final void setYearProperty(final int yearProperty) {
		this.yearPropertyProperty().set(yearProperty);
	}

}
