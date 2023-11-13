package marcus.example.APITest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    private String name = "Marcus";

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/setName/{value}")
    public String setName(@PathVariable String value) {
        name = value;
        return "Saved name: " + name;
    }

    @GetMapping("/getName")
    public String getName() {
        return name;
    }

    @PostMapping("/saveValue")
    public String saveValue(@RequestBody String value) {
        name = value;
        return "Value saved: " + name;
    }

    @PostMapping("/saveJsonValue")
    public String saveJsonValue(@RequestBody String value) throws ParseException {
        JSONObject jsonData = (JSONObject) new JSONParser().parse(value);

        name = jsonData.get("name").toString();
        return "Value saved: " + name;
    }
}
