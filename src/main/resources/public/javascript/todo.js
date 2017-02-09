/**
 * Created by cookx876 on 1/28/17.
 */
/**
 * We use this to make sure we're not trying to do stuff with the
 * elements on the page before the page is even loaded.
 *
 *
 */
window.onload = function() {
    console.log("The page is loaded now!");

    var element = document.getElementById('getAll');
    element.addEventListener("click", getToDo, true);
}


/**
 * Function to get all the users!
 */
var getToDo = function() {
    var httpClient = new HttpClient();
    // var thing = makeURL();
    // document.write(thing);
    // httpClient.get("/api/todo", function(returned_json){
    //     document.getElementById('jsonDump').innerHTML = returned_json;
    // });
     var url = makeURL();
    // document.getElementById('urlDump').innerHTML = makeURL().URL;
    httpClient.get(url,function(results) {
       document.getElementById('jsonDump').innerHTML = results;
    });

}
/**
 * Makes url from input data
 */
function  makeURL(){

    var URL = "/api/todo";
    var cate = null;
    var owne = null;
    var stat = null;
    var keyw = null;
    var ID = null;
    var sear = null;
    var limit = null;

// search by ID
    ID = document.getElementById('_ID').value;
    if(ID != ""){
         URL = URL.concat("/").concat(ID);
         return URL;
     }


// Category checking
    if(document.getElementById('category1').checked){
        cate="groceries";
    } else if(document.getElementById('category2').checked){
        cate="homework";
    } else if(document.getElementById('category3').checked){
        cate="software+design";
    } else if(document.getElementById('category4').checked){
        cate="video+games";
    }
    if(cate !== null){
        URL = URL.concat("?category=").concat(cate);
    }

// Owner checking
    if(document.getElementById('owner1').checked){
        owne = "Barry";
    }else if(document.getElementById('owner2').checked){
        owne = "Blanche";
    }else if(document.getElementById('owner3').checked){
        owne = "Dawn";
    }else if(document.getElementById('owner4').checked){
        owne = "Fry";
    }else if(document.getElementById('owner5').checked){
        owne = "Roberta";
    }else if(document.getElementById('owner6').checked){
        owne = "Workman";
    }

    if(owne !== null){
        if(cate !== null){
            URL = URL.concat("&owner=").concat(owne);
        }else {
            URL = URL.concat("?owner=").concat(owne);
        }
    }

// check status
    if(document.getElementById('complete').checked){
        stat="complete"
    } else if(document.getElementById('incomplete').checked){
        stat="incomplete"
    }
    if(stat !== null){
        if(cate !== null || owne !== null){
        URL = URL.concat("&status=").concat(stat);
    } else{
            URL = URL.concat("?status=").concat(stat);
        }
    }

// Search by Keyword
    sear = document.getElementById('search').value;
    if(sear != ""){
        if(cate !== null || owne !== null || stat !== null){
            URL = URL.concat("&contains=").concat(sear)
        }else {
            URL = URL.concat("?contains=").concat(sear);
        }
    }

// Order by keyword
    if(document.getElementById('keyword1').checked){
        keyw = "body";
    } else if(document.getElementById('keyword2').checked){
        keyw = "category";
    } else if(document.getElementById('keyword3').checked){
        keyw = "owner";
    } else if(document.getElementById('keyword4').checked){
        keyw = "staus";
    }
    if(keyw !== null){
        if(cate !== null || owne !== null || (stat !== null || sear != "")){
            URL = URL.concat("&orderBy=").concat(keyw);
        } else{
            URL = URL.concat("?orderBy=").concat(keyw);
        }
    }

// page limit

    limit = document.getElementById("limit").value;
    if(limit != ""){
        if(cate !== null || owne !== null || stat !== null || sear != "" || keyw !== null){
            URL = URL.concat("&limit=").concat(limit);
        } else{
            URL = URL.concat("?limit=").concat(limit);
        }
    }

    return URL;
}


/**
 * p much copied from users.js
 * @constructor
 */
function HttpClient() {
    // We'll take a URL string, and a callback function.
    this.get = function(aUrl, aCallback){
        var anHttpRequest = new XMLHttpRequest();

        // Set a callback to be called when the ready state of our request changes.
        anHttpRequest.onreadystatechange = function(){

            /**
             * Only call our 'aCallback' function if the ready state is 'DONE' and
             * the request status is 200 ('OK')
             *
             * See https://httpstatuses.com/ for HTTP status codes
             * See https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState
             *  for XMLHttpRequest ready state documentation.
             *
             */
            if (anHttpRequest.readyState == 4 && anHttpRequest.status == 200)
                aCallback(anHttpRequest.responseText);
        }

        anHttpRequest.open("GET", aUrl, true);
        anHttpRequest.send(null);
    }

}