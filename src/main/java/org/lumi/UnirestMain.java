package org.lumi;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by John Tsantilis (A.K.A lumi) on 30/8/2016.
 */

public class UnirestMain {
    public static void main(String[] args) {
        String[] osMetrics = new String[]{"/os/memory/total",
                "/os/memory/free",
                "/os/date",
                "/os/uptime",
                "/os/threads",
                "/os/cpu_load",
                "/network/ifconfig",
                "/hardware/hypervisor",
                "/hardware/processor/count"};
        String port = "9001";
        String VmEndPoint = "http://192.168.7.230" + ":" + port;
        String URI = "/os/memory/total";
        String metricVale = "";
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = Unirest.get(VmEndPoint + URI).asString();
            if(httpResponse.getStatus() != 200){
                System.out.printf("Could not get metric: " + URI + " for VM with IP: 192.168.7.230");

            }
            metricVale = httpResponse.getBody();
            System.out.println(metricVale);

        }
        catch (UnirestException e) {
            e.printStackTrace();

        }

    }

}
