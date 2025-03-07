package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;


import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void testGetGroupLeds() throws IOException {

        LedController ledController = new LedControllerImpl(new ApiServiceImpl());
        JSONArray temp = ledController.getGroupLeds();
        int count = 28;
        for (int i = 0; i < temp.length(); i++) {
            JSONObject light = temp.getJSONObject(i);
            assertEquals(light.getInt("id"), count);
            count++;
        }
    }

    @Test
    public void testGetGroupLedsMock() throws IOException {
        ApiService apiServiceMock = Mockito.mock(ApiService.class);
        JSONArray sampleResponse = new JSONArray();
        for (int i = 28; i < 30; i++) {
            JSONObject light = new JSONObject();
            light.put("id", i);
            JSONObject groupByGroup = new JSONObject();
            groupByGroup.put("name", "I");
            light.put("groupByGroup", groupByGroup);
            sampleResponse.put(light);
        }

        JSONObject apiResponse = new JSONObject();
        apiResponse.put("lights", sampleResponse);
        when(apiServiceMock.getLights()).thenReturn(apiResponse);
        LedController ledController = new LedControllerImpl(apiServiceMock);
        JSONArray result = ledController.getGroupLeds();
        int count = 28;
        for (int i = 0; i < result.length(); i++) {
            JSONObject light = result.getJSONObject(i);
            assertEquals(light.getInt("id"), count);
            count++;
        }
    }

    @Test
    public void testOneLED() throws IOException {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());
        ledController.putLight(33, "#fff", true);

        ApiService apiService = new ApiServiceImpl();

        JSONObject response = apiService.getLight(33);
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);

        assertEquals(firstLight.getInt("id"), 33);
        assertTrue(firstLight.getString("color").equalsIgnoreCase("#fff"));

//        System.out.println("light id is: " + firstLight.getInt("id"));
//        System.out.println("light color is: " + firstLight.getString("color"));

    }


}
