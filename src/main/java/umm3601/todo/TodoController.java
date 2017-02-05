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

    private Todo[] todos;

    public TodoController() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/data/todos.json");
        todos = gson.fromJson(reader, Todo[].class);
    }

    // List Todos
    public Todo[] listTodos(Map<String, String[]> queryParams) {
        Todo[] filteredTodos = todos;

        // page limit
        if(queryParams.containsKey("limit")){
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            filteredTodos = getPageLimit(limit);
        }

        if(queryParams.containsKey("status")){
            boolean state;
            if ("complete".equals(queryParams.get("status")[0])) {
                state = true;
            } else {
                state =false;
            }

            filteredTodos = returnstatuses(state);
        }

        return filteredTodos;
    }



    // Get a single Todo_
    public Todo getTodo(String id) {
        return Arrays.stream(todos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
    }

    //Get todo page limit
    public Todo[] getPageLimit(int limit){
        Todo[] toReturn = new Todo[limit];
        int i = 0;
        while(i < limit){
            toReturn[i] = todos[i];
            i++;
        }
        return toReturn;
    }
    //Return All todos of a status.
    public Todo[] returnstatuses(boolean state) {
        Todo[] holding = new Todo[todos.length];
        int j = 0;
        int i;
        for(i = 0; i < todos.length; i++){
            if (todos[i].status.equals(state)) {
                holding[j] = todos[i];
                j++;
            }
        }
        Todo[] toReturn = new Todo[j];
        for (i=0; i<toReturn.length; i++) {
            toReturn[i] = holding[i];
        }
        return toReturn;
    }
}
