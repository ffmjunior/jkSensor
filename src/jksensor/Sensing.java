/*
 * Copyright (C) 2015 junior
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jksensor;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Uses host's ip and ipv6 and a dummy temperature to create a messade.
 *
 * @author junior
 */
public class Sensing {

    String ipv6;
    String ip;
    int data;

    public Sensing() {
        ipv6 = (String) netAddress().get(0);
        ip = (String) netAddress().get(1);
        data = randomValuesGeneration(0, 100);

    }

    private int randomValuesGeneration(int lowerLimit, int upperLimit) {
        Double data = Math.random() * 100;
        return data.intValue();
    }

    public void newData(int lowerLimit, int upperLimit) {
        Double data = Math.random() * 100;
        if (data < lowerLimit | data > upperLimit) {
            newData(lowerLimit, upperLimit);
        } else {
            this.data = data.intValue();
        }
    }

    private Vector netAddress() {
        Enumeration interfaces;
        Vector addresses = new Vector();
        try {
            interfaces = NetworkInterface.getNetworkInterfaces();
            NetworkInterface ni = (NetworkInterface) interfaces.nextElement();
            List add = ni.getInterfaceAddresses();
            for (Object a : add) {
                InterfaceAddress ia = (InterfaceAddress) a;
                addresses.add(ia.getAddress().getHostAddress());
            }
        } catch (SocketException ex) {
        }
        return addresses;
    }

    @Override
    public String toString() {
        return "IPv6 " + ipv6 + " IP " + ip + " data " + data;
    }
    
    public void test(){
        
    }

}
