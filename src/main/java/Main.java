import controller.UserController;
import utils.Tables;

public class Main {

    public static void main(final String[] args) {
        final Tables tables = new Tables();
        tables.createTables();
        final UserController controller = new UserController();
        controller.saveUsers();
    }
}
