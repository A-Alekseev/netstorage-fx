module ru.gb.netstoragefx {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.netstoragefx to javafx.fxml;
    exports ru.gb.netstoragefx;
}