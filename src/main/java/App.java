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

        post("/", (req,res)->{
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

        get("item/new", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "form.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
