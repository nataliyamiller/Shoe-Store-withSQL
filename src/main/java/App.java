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
    ModelAndView view = new ModelAndView(model, layout);
    VelocityTemplateEngine engine = new VelocityTemplateEngine();

    get("/", (request, response) -> {
      model.put("template", "templates/index.vtl");
      return view;
    }, engine);

    get("/stores", (request, response) -> {
      model.put("stores", Store.all());
      model.put("template", "templates/stores.vtl");
      return view;
    }, engine);

    post("/stores", (request, response) -> {
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      String phoneNumber = request.queryParams("phone_number");
      Store newStore = new Store(name, address, phoneNumber);
      newStore.save();
      response.redirect("/stores");
      return null;
    }, engine);

    get("/brands", (request, response) -> {
      model.put("brands", Brand.all());
      model.put("template", "templates/brands.vtl");
      return view;
    }, engine);

    post("/brands", (request, response) -> {
      String brandName = request.queryParams("brand_name");
      String style = request.queryParams("style");
      String type = request.queryParams("type");
      String color = request.queryParams("color");
      Brand newBrand = new Brand(brandName, style, type, color);
      newBrand.save();
      response.redirect("/brands");
      return null;
    }, engine);

    get("/stores/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      Store savedStore = Store.find(id);
      model.put("store", savedStore);
      model.put("brands", Brand.all());
      model.put("template", "templates/store.vtl");
      return view;
    }, engine);

    post("stores/:id/addbrands", (request, response) -> {
      int brandId = Integer.parseInt(request.queryParams("brand_id"));
      int storeId = Integer.parseInt(request.queryParams("store_id"));
      Store store = Store.find(storeId);
      Brand brand = Brand.find(brandId);
      store.addBrand(brand);
      response.redirect("/stores/" + storeId);
      return null;
    });

    post("stores/:id/update", (request, response) -> {
      Store store = Store.find(Integer.parseInt(request.params(":id")));
      String name = request.queryParams("name");
      String address = request.queryParams("address");
      String phoneNumber = request.queryParams("phone_number");

      if (!(name == null)) {
        store.updateName(name);
      } else if (!(address == null)) {
        store.updateAddress(address);
      } else if (!(phoneNumber == null)) {
        store.updatePhoneNumber(phoneNumber);
      }
      response.redirect("/stores/" + store.getId());
      return null;
    });

    post("/stores/:id/delete", (request, response) -> {
      Store store = Store.find(Integer.parseInt(request.params(":id")));
      store.delete();
      response.redirect("/stores");
      return null;
    });

    get("/brands/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      Brand savedBrand = Brand.find(id);
      model.put("brand", savedBrand);
      model.put("stores", savedBrand.getStores());
      model.put("template", "templates/brand.vtl");
      return view;
    }, engine);

    post("brands/:id/addstores", (request, response) -> {
      int brandId = Integer.parseInt(request.queryParams("brand_id"));
      int storeId = Integer.parseInt(request.queryParams("store_id"));
      Store store = Store.find(storeId);
      Brand brand = Brand.find(brandId);
      brand.addStore(store);
      response.redirect("/brands/" + brandId);
      return null;
    });

    post("brands/:id/update", (request, response) -> {
      Brand brand = Brand.find(Integer.parseInt(request.params(":id")));
      String brandName = request.queryParams("brand_name");
      String style = request.queryParams("style");
      String type = request.queryParams("type");
      String color = request.queryParams("color");

      if (!(brandName == null)) {
        brand.updateBrandName(brandName);
      } else if (!(style == null)) {
        brand.updateStyle(style);
      } else if (!(type == null)) {
        brand.updateType(type);
      } else if (!(color == null)) {
        brand.updateColor(color);
      }
      response.redirect("/brands/" + brand.getId());
      return null;
    });
    post("/stores/:id/delete", (request, response) -> {
      Brand brand = Brand.find(Integer.parseInt(request.params(":id")));
      brand.delete();
      response.redirect("/brands");
      return null;
    });




  }
}
