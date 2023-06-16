import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Character {
    String name;
    String job;
    String item_level;
    ArrayList<Engrave> engraves;

    private static final String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDAxNDAzNjcifQ.PVEVLv3NFUuwd9TqBM-kCNpy2tbRFp3lI249UiD-cg7UyUbsj8S6OIKcEuOdGKVdZGDf0Cvge22QwH8JRCfT9uKWf9FP4iBJuFrIX7CuuQe-mrs2j2ViTYCY5QMJKddpcxCp9MLYKl5yBpGb1bhOMiNmeUSbVsQdDftAgALKsz26-myeuxBFuSgaFOGQ1aLy3BGjXJb6wM0p89G_jRSEnAYI7uEeBFywXhPZJ-3yh8ukUn1jdQGax2Lce71HMAj5aamyVmTBTfD0fVK7WEhPM5l5DuGvxy_CruV15sf4Pnr97JkaH89RuJKTlqPC9u4bbkW2WWvzL7qClPDN73S2yQ";


    public Character(String name){
        engraves = new ArrayList<>();
        try {
            String encoded_character_name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());

            URL characters_url = new URL("https://developer-lostark.game.onstove.com/characters/" + encoded_character_name + "/siblings");

            HttpURLConnection character_con = (HttpURLConnection) characters_url.openConnection();

            character_con.setRequestMethod("GET");
            character_con.setRequestProperty("accept", "application/json");
            character_con.setRequestProperty("authorization", "bearer " + apiKey);

            int responseCode = 0;
            //인터넷 연결이 없을 경우 여기서 UnknownHostException발생

            try{
                responseCode = character_con.getResponseCode();
            } catch (IOException e){
                character_con.disconnect();
                JOptionPane.showMessageDialog(null,
                        "HTTP 연결을 맺을 수 없습니다. 인터넷 연결을 확인해주세요.",
                        "HTTP API ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(character_con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONParser parser = new JSONParser();
                JSONArray character_array = (JSONArray) parser.parse(response.toString());

                if(character_array == null){
                    this.name = null;
                    return;
                }
                for (Object obj : character_array) {
                    JSONObject character = (JSONObject) obj;
                    String result = (String) character.get("CharacterName");
                    if (result.equals(name)) {
                        this.name = name;
                        this.job = (String) character.get("CharacterClassName");
                        this.item_level = (String) character.get("ItemAvgLevel");
                    }
                }
            }
            else{
                System.out.println("character GET request failed");
            }

            URL engraving_url = new URL("https://developer-lostark.game.onstove.com/armories/characters/" + encoded_character_name + "/engravings");

            HttpURLConnection engraving_con = (HttpURLConnection) engraving_url.openConnection();

            engraving_con.setRequestMethod("GET");
            engraving_con.setRequestProperty("accept", "application/json");
            engraving_con.setRequestProperty("authorization", "bearer " + apiKey);

            responseCode = engraving_con.getResponseCode();


            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(engraving_con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONParser parser = new JSONParser();
                //JSONArray jsonArray = (JSONArray) parser.parse(response.toString());

                JSONObject result = (JSONObject) parser.parse(response.toString());
                JSONArray name_descriptions = (JSONArray) result.get("Effects");

                for(Object obj : name_descriptions){
                    JSONObject effect = (JSONObject) obj;
                    String engraving_name = (String) effect.get("Name");
                    String engraving_description = (String) effect.get("Description");
                    Engrave engrave = new Engrave();
                    engrave.setName(engraving_name);
                    engrave.setDescription(engraving_description);
                    engraves.add(engrave);
                }
            }
            else{
                System.out.println("engrave GET request failed");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getItem_level() {
        return item_level;
    }

    public ArrayList<Engrave> getEngraves() {
        return engraves;
    }
}
class Engrave{
    String name;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
