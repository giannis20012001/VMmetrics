package org.lumi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.lumi.util.OSvModels;
import org.lumi.util.SigarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by John Tsantilis (A.K.A lumi) on 30/8/2016.
 */

public class OSMetricsHandler {
    public static void main(String[] args) {
        //Step One
        initializeSigar();

    }

    //@PostConstruct
    public static void initializeSigar(){
        SigarHelper.loadLibrary("libsigar-amd64-linux.so");
        sigar = new Sigar();

    }

    //@RequestMapping(value = "/os/memory/total", method = RequestMethod.GET)
    public static long getTotalMem() throws SigarException {
        Mem mem = sigar.getMem();
        return mem.getTotal();

    }

    //@RequestMapping(value = "/os/memory/free", method = RequestMethod.GET)
    public static long getFreeMem() throws SigarException {
        Mem mem = sigar.getMem();
        return mem.getActualFree();

    }

    //@RequestMapping(value = "/os/date", method = RequestMethod.GET)
    public static String getDate()  {
        return  new java.util.Date()+"";

    }

    //@RequestMapping(value = "/os/uptime", method = RequestMethod.GET)
    public static long getUptime() throws SigarException {
        return (long)sigar.getUptime().getUptime();

    }

    //@RequestMapping(value = "/hardware/hypervisor", method = RequestMethod.GET)
    public static String getHypervisor()  {
        return  "";

    }

    //@RequestMapping(value = "/hardware/processor/count", method = RequestMethod.GET)
    public static long getCpuCount() throws SigarException {
        CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
        return (long)cpuInfoList.length;

    }

    //@RequestMapping(value = "/os/threads", method = RequestMethod.GET)
    public static String getThreads() throws SigarException {
        return "";

    }


    //@RequestMapping(value = "/os/cpu_load", method = RequestMethod.GET)
    public static String getCpuLoad() throws SigarException {
        CpuPerc res;
        Map<String, Double> cpuPercResult = new HashMap<String, Double>();
        res = sigar.getCpuPerc();
        cpuPercResult.put("IdlePercentage", res.getIdle());
        cpuPercResult.put("CombinedPercentage", res.getCombined());
        return gson.toJson(cpuPercResult);

    }

    //@RequestMapping(value = "/network/ifconfig", method = RequestMethod.GET)
    public static String getIfConfig() throws SigarException {
        List<OSvModels.IfConfig> ifConfigList = new ArrayList<>();

        String[] netInterfaceList = sigar.getNetInterfaceList();
        for (String interfaceName : netInterfaceList) {

            //Network Interface Config
            NetInterfaceStat interfaceStat = sigar.getNetInterfaceStat(interfaceName);
            OSvModels.NetworkConfig networkConfig = osvmodels.NetworkConfig();
            NetInterfaceConfig interfaceConfig = sigar.getNetInterfaceConfig(interfaceName);
            networkConfig.setName(interfaceConfig.getName());
            networkConfig.setAddr(interfaceConfig.getAddress());
            networkConfig.setMtu( Long.toString(interfaceConfig.getMtu()));

            //Network Interface Data
            OSvModels.NetworkData networkData = osvmodels.NetworkData();
            //long speed = interfaceStat.getSpeed();
            long receivedBytes = interfaceStat.getRxBytes();
            long sentBytes = interfaceStat.getTxBytes();
            long sentPackets = interfaceStat.getTxPackets();
            long receivedPackets = interfaceStat.getRxPackets();
            long ierrors = interfaceStat.getRxErrors();
            long oerrors = interfaceStat.getTxErrors();
            networkData.setIfi_ibytes(receivedBytes);
            networkData.setIfi_obytes(sentBytes);
            networkData.setIfi_ipackets(receivedPackets);
            networkData.setIfi_opackets(sentPackets);
            networkData.setIfi_ierrors(ierrors);
            networkData.setIfi_oerrors(oerrors);

            //Set transfer Object
            OSvModels.IfConfig ifConfig = osvmodels.IfConfig();
            ifConfig.setConfig(networkConfig);
            ifConfig.setData(networkData);
            ifConfigList.add(ifConfig);

        }

        return gson.toJson(ifConfigList);

    }

    private static Sigar sigar;
    private static OSvModels osvmodels = new OSvModels();
    private  static  Gson gson = new GsonBuilder().setPrettyPrinting().create();

}
