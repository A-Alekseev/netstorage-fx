module ru.gb.netstoragefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires io.netty.transport;
    requires io.netty.codec;
    requires io.netty.buffer;

    opens ru.gb.netstoragefx to javafx.fxml;
    exports ru.gb.netstoragefx;
}