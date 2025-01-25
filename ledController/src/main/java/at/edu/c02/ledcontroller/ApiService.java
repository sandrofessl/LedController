package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.IOException;

public interface ApiService {
    JSONObject getLights() throws IOException;
    JSONObject getLight(int id) throws IOException;
    JSONObject putLight(int id, String color, boolean state) throws IOException;

}

