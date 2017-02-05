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
      //  Todo[] filteredTodos = todos;

        // page limit
        if(queryParams.containsKey("limit")){
            int limit = Integer.parseInt(queryParams.get("limit")[0]);
            todos = getPageLimit(limit);
        }

        if(queryParams.containsKey("status")){
            boolean state;
            if ("complete".equals(queryParams.get("status")[0])) {
                state = true;
            } else {
                state =false;
            }

            todos = returnstatuses(state);
        }

        if(queryParams.containsKey("contains")){
            String search = queryParams.get("contains")[0];
            todos = searchBody(search);
        }

        if(queryParams.containsKey("owner")){
            String owner = queryParams.get("owner")[0];
            todos = filterOwner(owner);
        }
        if(queryParams.containsKey("category")){
            String category = queryParams.get("category")[0];
            todos = filterCategory(category);
        }
        if(queryParams.containsKey("orderBy")){
            String param = queryParams.get("orderBy")[0];
            if(param.equals("owner")){
                todos = orderByOwner(param);
            } else if (param.equals("category")){
                todos = orderByCategory(param);
            } else if (param.equals("status")){
                todos = orderByStatus(param);
            } else if (param.equals("body")){
                todos = orderByBody(param);
            } else {
                return null;
            }
        }

        return todos;
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

    // returns all todos with search in body
    public Todo[] searchBody(String key){
        Todo[] holding = new Todo[todos.length];
        int j = 0;
        int i;
        for(i = 0; i < todos.length; i++){
            if (todos[i].body.contains(key)) {
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
   public Todo[] filterOwner(String owner) {
       Todo[] holding = new Todo[todos.length];
       int j = 0;
       int i;
       for(i = 0; i < todos.length; i++){
           if (todos[i].owner.equals(owner)) {
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

    public Todo[] filterCategory(String category) {
        Todo[] holding = new Todo[todos.length];
        int j = 0;
        int i;
        for(i = 0; i < todos.length; i++){
            if (todos[i].category.equals(category)) {
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

    //From rosettacode.org :)
    public Todo[] orderByOwner (String owner){
        Todo[] tmp = new Todo[todos.length];
        int i =1; int j=0;
        tmp[0] = todos[0];
        while(i<todos.length){
            String thing = todos[i].owner;
            while(thing.compareTo(tmp[j].owner) < 0){
                j++;
            }
            tmp[j].owner = thing;
        }
//        for (i =1; i<tmp.length;i++) {
//            for (j = 0; j<i; j++) {
//                if (tmp[i].owner.compareTo(tmp[j].owner) < 0) {
//                    Todo foo = tmp[i];
//                    tmp[i] = tmp[j];
//                    tmp[j] = foo;
//                }
//            }
//            i++;
//        }
        return tmp;
    }

    public Todo[] orderByCategory(String owner){
        return null;
    }

    public Todo[] orderByBody(String owner){
        return null;
    }

    public Todo[] orderByStatus(String owner){
        return null;
    }
}
