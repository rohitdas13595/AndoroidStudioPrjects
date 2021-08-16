package com.example.asynchronous;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);

    }

    public void parsexml(View view){
        textView.setText("");
        try {
            InputStream inputStream = getAssets().open("weather.xml");
            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(inputStream);

            Element element=document.getDocumentElement();
            element.normalize();

            NodeList nodeList=document.getElementsByTagName("weather");

            for (int i=0; i<nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element Element2 = (Element) node;
                    String city = Element2.getElementsByTagName("city").item(0).getTextContent();
                    String lat = Element2.getElementsByTagName("lat").item(0).getTextContent();
                    String longi = Element2.getElementsByTagName("longi").item(0).getTextContent();
                    String temp = Element2.getElementsByTagName("temp").item(0).getTextContent();
                    String humidity = Element2.getElementsByTagName("humidity").item(0).getTextContent();

                    textView.setText(textView.getText()+"\nCity : " + city +"\n");
                    textView.setText(textView.getText()+"Latitude : " + lat +"\n");
                    textView.setText(textView.getText()+"Longitude : " + longi +"\n");
                    textView.setText(textView.getText()+"Temparature : " + temp+"\n");
                    textView.setText(textView.getText()+"Humidity : " + humidity +"\n");
                    textView.setText(textView.getText()+"-----------------------");
                }
            }
        }
        catch (Exception e){

        }
    }

    public void parseJson(View view) {
        textView.setText("");
        String Json;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("weather.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            Json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(Json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                textView.setText(textView.getText() + "\nCity : " + jsonObject.getString("cityName") + "\n");
                textView.setText(textView.getText() + "Temperature : " + jsonObject.getString("temperature") + "\n");
                textView.setText(textView.getText() + "WindDirection : " + jsonObject.getString("windDirection") + "\n");
                textView.setText(textView.getText() + "-----------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}