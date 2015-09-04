import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;
import java.util.Arrays;
import java.util.Set;

 public class App {
    public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    HashMap<String, Object> model = new HashMap<String, Object>();
    VelocityTemplateEngine engine = new VelocityTemplateEngine();

    get("/", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, engine);

    get("/stores", (request, response) -> {
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return new ModelAndView(model, layout);
    }, engine);

  }
}
