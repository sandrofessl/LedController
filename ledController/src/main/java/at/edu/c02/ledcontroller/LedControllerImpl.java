package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));

    }

    @Override
    public JSONArray getGroupLeds() throws IOException {
        JSONObject response = apiService.getLights();
        JSONArray lights = response.getJSONArray("lights");

        JSONArray groupLight = new JSONArray();
        for (int i = 0; i < lights.length(); i++) {
            JSONObject light = lights.getJSONObject(i);

            if (light.getJSONObject("groupByGroup").getString("name").equals("I")) {
                System.out.println("Light id: " + light.getInt("id"));
                groupLight.put(light);
            }
        }
        return groupLight;

    }

    @Override
    public void getOneLed(int id) throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLight(id);
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("light id is: " + firstLight.getInt("id"));
        System.out.println("light color is: " + firstLight.getString("color"));
    }


    public void putLight(int id, String color, boolean state) throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        apiService.putLight(id, color,state);
        // get the "lights" array from the response
        //JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
      //
    }
    public void turnOffAll() throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        for (int i = 28; i < 36; i++) {
            JSONArray tempArray = getGroupLeds();
            for (int j = 0; j < tempArray.length(); j++) {
                JSONObject temp = tempArray.getJSONObject(j);
                int id = temp.getInt("id");
                apiService.putLight(id, "#FF5733", false);
            }
        }
    }
}
