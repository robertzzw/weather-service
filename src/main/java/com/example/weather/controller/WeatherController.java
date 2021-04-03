package com.example.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {
    @Value("${weatherAPI.appid}")
    private String appid;
    @Value("${weatherAPI.apiURL}")
    private String apiURL;
    @Value("${city.value}")
    private String city;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/get/{city}")
    @ResponseBody
    public String getWeather(@PathVariable("city") String city){
//        String apiURL = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
        System.out.println(apiURL);
        String apiURL2 = apiURL + city + "&appid=" + appid;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiURL2, String.class);

        if(200==responseEntity.getStatusCodeValue()){
            String result = responseEntity.getBody();
            return result;
        }else{
            return "error with code : " + responseEntity.getStatusCodeValue();
        }
    }
    @GetMapping(value = "/get-city")
    @ResponseBody
    public String[] getCity(){
        //城市信息可以从配置文件获取，或从数据库读取
        String[] split = city.trim().split(",");
        return split;
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
