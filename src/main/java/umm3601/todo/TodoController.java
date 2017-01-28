package umm3601.todo;

/**
 * Created by cookx876 on 1/28/17.
 */
import com.google.gson.Gson;

        import java.io.FileReader;
        import java.io.IOException;
        import java.util.Arrays;
        import java.util.Map;

public class TodoController {

    private Todo[] Todos;

    public TodoController() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/data/Todos.json");
        Todos = gson.fromJson(reader, Todo[].class);
    }

    // List Todos
    public Todo[] listTodos(Map<String, String[]> queryParams) {
        Todo[] filteredTodos = Todos;

        // Filter age if defined
//        if(queryParams.containsKey("age")) {
//            int age = Integer.parseInt(queryParams.get("age")[0]);
//            filteredTodos = filterTodosByAge(filteredTodos, age);
//        }

        return filteredTodos;
    }

//    // Filter Todos by age
//    public Todo[] filterTodosByAge(Todo[] filteredTodos, int age) {
//        return Arrays.stream(filteredTodos).filter(x -> x.age == age).toArray(Todo[]::new);
//    }

    // Get a single Todo
    public Todo getTodo(String id) {
        return Arrays.stream(Todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }

}
