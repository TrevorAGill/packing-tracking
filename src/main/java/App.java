import models.Item;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

/**
 * Created by Guest on 8/9/17.
 */
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Item> items= Item.getAllItems();
            model.put("items", items);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new post form
        get("/item/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //add new post to index
        post("/item/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("itemName");
            Double price = Double.parseDouble(req.queryParams("itemPrice"));
            Double weight = Double.parseDouble(req.queryParams("itemWeight"));
            boolean purchased = Boolean.parseBoolean(req.queryParams("purchased"));
            boolean packed = Boolean.parseBoolean(req.queryParams("packed"));
            Item newItem = new Item(name, price, weight, purchased, packed);
            model.put("item",newItem);
            res.redirect("/");
            return null;
        });

        //this is just showing an individual item's detail
        get("/item/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Item foundItem = Item.findById(Integer.parseInt(req.params("id")));
            model.put("item", foundItem);
            return new ModelAndView(model, "item-detail.hbs");
        }, new HandlebarsTemplateEngine());


        //go to form to edit existing item
        get("/item/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Item foundItem = Item.findById(Integer.parseInt(req.params("id")));
            model.put("editItem", foundItem);
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());

        //post edited item
        post("/item/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String newName = request.queryParams("itemName");
            Double newPrice = Double.parseDouble(request.queryParams("itemPrice"));
            Double newWeight = Double.parseDouble(request.queryParams("itemWeight"));
            boolean newPurchased = Boolean.parseBoolean(request.queryParams("purchased"));
            boolean newPacked = Boolean.parseBoolean(request.queryParams("packed"));
            Item editItem = Item.findById(Integer.parseInt(request.params("id")));
            editItem.editItem(newName, newPrice, newWeight, newPurchased, newPacked);
            model.put("item", editItem);
            response.redirect("/");
            return null;
        });

        //delete
        get("/item/:id/delete", (request,response)-> {
            int idOfPostToDelete = Integer.parseInt(request.params("id")); //pull id - must match route segment
            Item.deleteItem(idOfPostToDelete);
            response.redirect("/");
            return null;
        });


    }


}
