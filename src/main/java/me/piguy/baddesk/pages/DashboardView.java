package me.piguy.baddesk.pages;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardView implements TabPaneViewController {
    @FXML
    private PieChart overduePieChart;
    @FXML
    private PieChart unresolvedPieChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<PieChart.Data> overdue = new ArrayList<PieChart.Data>();
        overdue.add(new PieChart.Data("Overdue", 1));
        overduePieChart.setData(FXCollections.observableArrayList(overdue));

        ArrayList<PieChart.Data> unresolved = new ArrayList<PieChart.Data>();
        unresolved.add(new PieChart.Data("Unresolved", 7));
        unresolved.add(new PieChart.Data("Resolved", 8));
        unresolvedPieChart.setData(FXCollections.observableArrayList(unresolved));
    }

    @FXML
    private void viewUnresolvedIncidents(ActionEvent ignored) {
    }
}
