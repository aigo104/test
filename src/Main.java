import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button p = new Button("Hello World");
        BorderPane root = new BorderPane(p);
        p.setOnAction(e ->{

        });
        primaryStage.setScene(new Scene(root,800, 600));
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Stopping Application");
    }

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Starting Application");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}