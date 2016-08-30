package org.lumi.util;

/**
 * Created by John Tsantilis (A.K.A lumi) on 30/8/2016.
 */

public class OSvModels {
    public NetworkConfig NetworkConfig(){
        return new NetworkConfig();

    }

    public NetworkData NetworkData(){
        return new NetworkData();

    }

    public IfConfig IfConfig(){
        return new IfConfig();

    }

    public class NetworkConfig{
        public String getAddr() {
            return addr;

        }

        public void setAddr(String addr) {
            this.addr = addr;

        }

        public String getName() {
            return name;

        }

        public void setName(String name) {
            this.name = name;

        }

        public String getMtu() {
            return mtu;

        }

        public void setMtu(String mtu) {
            this.mtu = mtu;

        }

        String addr;
        String name;
        String mtu;

    }


    public class NetworkData{
        public long getIfi_ipackets() {
            return ifi_ipackets;

        }

        public void setIfi_ipackets(long ifi_ipackets) {
            this.ifi_ipackets = ifi_ipackets;

        }

        public long getIfi_opackets() {
            return ifi_opackets;

        }

        public void setIfi_opackets(long ifi_opackets) {
            this.ifi_opackets = ifi_opackets;

        }

        public long getIfi_ibytes() {
            return ifi_ibytes;

        }

        public void setIfi_ibytes(long ifi_ibytes) {
            this.ifi_ibytes = ifi_ibytes;

        }

        public long getIfi_obytes() {
            return ifi_obytes;

        }

        public void setIfi_obytes(long ifi_obytes) {
            this.ifi_obytes = ifi_obytes;

        }

        public long getIfi_ierrors() {
            return ifi_ierrors;

        }

        public void setIfi_ierrors(long ifi_ierrors) {
            this.ifi_ierrors = ifi_ierrors;

        }

        public long getIfi_oerrors() {
            return ifi_oerrors;

        }

        public void setIfi_oerrors(long ifi_oerrors) {
            this.ifi_oerrors = ifi_oerrors;

        }

        long ifi_ipackets;
        long ifi_opackets;
        long ifi_ibytes;
        long ifi_obytes;
        long ifi_ierrors;
        long ifi_oerrors;

    }

    public class IfConfig{
        public NetworkConfig getConfig() {
            return config;

        }

        public void setConfig(NetworkConfig config) {
            this.config = config;

        }

        public NetworkData getData() {
            return data;

        }

        public void setData(NetworkData data) {
            this.data = data;

        }

        NetworkConfig config;
        NetworkData data;

    }

}
