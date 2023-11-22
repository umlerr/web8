package mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MySampleApplication implements EntryPoint {
    private final MySampleApplicationServiceAsync myService =
            MySampleApplicationService.App.getInstance();

    @SuppressWarnings("deprecation")
    final ListBox managersListBox = new ListBox(false);


    public void onModuleLoad() {
        managersListBox.setFocus(true);
        refreshManagersList();

        //создание и заполнение таблицы
        final CellTable<Manager> mainTable = createCellTable();
        final ListDataProvider<Manager> mainDataProvider = new ListDataProvider<>();
        mainDataProvider.addDataDisplay(mainTable);
        RootPanel.get("PanelContainer").add(mainTable);
        myService.getManagersList(
                new AsyncCallback<List<Manager>>() {
                    @Override
                    public void onFailure(Throwable caught) {

                    }

                    @Override
                    public void onSuccess(List<Manager> result) {
                        mainDataProvider.setList(result);
                    }
                }
        );

        final VerticalPanel seniorityPanel = new VerticalPanel();
        seniorityPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
        seniorityPanel.setVisible(true);
        final Label seniorityLabel = new Label("Введите минимальный стаж");
        final Label errorLabel = new Label("Неверно введенный стаж");
        final Button button = new Button("Search");
        final TextBox seniorityField = new TextBox();
        seniorityField.getElement().setPropertyString("placeholder", "Стаж");
        errorLabel.setVisible(false);
        seniorityPanel.add(seniorityLabel);
        seniorityPanel.add(errorLabel);
        seniorityPanel.add(seniorityField);
        seniorityPanel.add(button);

        button.addClickHandler(event -> {
            int seniority;
            try{
                seniority = Integer.parseInt(seniorityField.getText());
                List<Manager> tempList = new ArrayList<>(mainDataProvider.getList());
                tempList.removeIf(boy -> boy.getSeniority() < seniority);
                mainDataProvider.setList(tempList);
                mainDataProvider.refresh();
                refreshManagersList();
                seniorityField.setText("");
                errorLabel.setVisible(false);
            }
            catch (Exception e){
                errorLabel.setVisible(true);
            }
        });

        RootPanel.get("seniorityForm").add(seniorityPanel);
    }

    private CellTable<Manager> createCellTable(){
        final CellTable<Manager> table = new CellTable<>();
        //без этой строчки ничего не будет видно
        table.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);

        TextColumn<Manager> nameColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager object) {
                return object.getName();
            }
        };
        table.addColumn(nameColumn, "Имя");//колонка, ее название

        TextColumn<Manager> cityColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager object) {
                return object.getSurname();
            }
        };
        table.addColumn(cityColumn, "Фамилия");

        TextColumn<Manager> specColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager object) {
                return String.valueOf(object.getId());
            }
        };
        table.addColumn(specColumn, "Номер");


        TextColumn<Manager> seniorityColumn = new TextColumn<Manager>() {
            @Override
            public String getValue(Manager object) {
                return String.valueOf(object.getSeniority());
            }
        };
        table.addColumn(seniorityColumn, "Стаж");

        return table;
    }

    private void refreshManagersList(){
        myService.getManagersList(new AsyncCallback<List<Manager>>() {
            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(List<Manager> result) {
                managersListBox.clear();
                for (Manager boy : result)
                    managersListBox.addItem(boy.getName());
            }
        });
    }
}
