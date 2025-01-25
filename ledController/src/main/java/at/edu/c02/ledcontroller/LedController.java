package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public interface LedController {
    void demo() throws IOException;
    JSONArray getGroupLeds() throws IOException;
    void getOneLed(int id) throws IOException;
    void putLight(int id, String color, boolean state) throws IOException;
}

