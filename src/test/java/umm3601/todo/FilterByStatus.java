package umm3601.todo;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
/**
 * Created by cookx876 on 2/7/17.
 */
public class FilterByStatus {
    @Test
    public void GetTrue() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] trues = todoController.returnstatuses(true, allTodos);
        assertEquals(trues.length, 143);

    }

    @Test
    public void GetFalse() throws IOException{

        TodoController todoController = new TodoController();
        Todo[] allTodos = todoController.listTodos(new HashMap<>());
        Todo[] falses = todoController.returnstatuses(false, allTodos);
        assertEquals(falses.length, 157);

    }

}
