package gui;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import model.entities.Department;
import model.services.DepartmentService;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class DepartmentFormController implements Initializable{
    
    private Department department;
    
    private DepartmentService service;
    
    private final List<DataChangeListener> dataChangeListener = new ArrayList<>();
            
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtName;
    
    @FXML
    private Label labelErrorName;
    
    @FXML
    private Button btSave;
    
    @FXML
    private Button btCancel;  

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }
    
    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListener.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent event) {
        if (department == null) {
            throw new IllegalStateException("Entity was null");
        }
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {   
            department = getFormData();
            service.saveOrUpdate(department);
            notifyDataChangeListeners();
            Utils.currentStage(event).close();
        } 
        catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    public void onBtCancelAction(ActionEvent event) {
         Utils.currentStage(event).close();
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    } 
    
    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
    
    public void updateFormData() {
        if (department == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(department.getId()));
        txtName.setText(department.getName());
    }

    private Department getFormData() {
        Department obj = new Department();
        
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        obj.setName(txtName.getText());
        
        return obj;
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listeners: dataChangeListener) {
            listeners.onDataChanged();
        }
    }
}
