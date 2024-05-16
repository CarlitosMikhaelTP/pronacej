package com.carlitos.Pronacej.Utils;

public class Apis {
    public static final String URL_001 = "http://192.168.18.76:8080";

    public static SabanaService getSabanaService(){
        return Client.getClient(URL_001).create(SabanaService.class);
    }

}
