package org.lumi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hyperic.sigar.Sigar;
import org.lumi.util.OSvModels;

import java.util.logging.Logger;

/**
 * Created by John Tsantilis (A.K.A lumi) on 30/8/2016.
 */

public class OSMetricsHandler {
    public static void main(String[] args) {
        //

    }

    private Sigar sigar;
    private OSvModels osvmodels = new OSvModels();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

}
