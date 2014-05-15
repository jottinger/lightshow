package com.redhat.osas.lightshow;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import org.testng.annotations.Test;

import javax.sound.midi.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestDevice {
    @Test

   public void testGPIO() {
        //GpioController controller=GpioFactory.getInstance();
    }

    @Test
    public void testDeviceLocation() throws InterruptedException {
        List<MidiDevice.Info> infoList=MidiCommon.getDevices(false, true);
        List<MidiDevice> devices=new ArrayList<>();
        Receiver receiver=new DumpReceiver(System.out);
        infoList.stream().forEach(info->{
            try {
                System.out.println("opening "+info.getName());
                MidiDevice device = MidiSystem.getMidiDevice(info);
                devices.add(device);
                device.open();
                Transmitter transmitter=device.getTransmitter();
                transmitter.setReceiver(receiver);
                System.out.println("running");
            } catch(MidiUnavailableException ignored) {
            }
        });
        System.out.println("Watching for input");
        Thread.sleep(10000);
        System.out.println("Stopping");
        devices.stream().forEach(device->{
            System.out.println("closing "+device.getDeviceInfo().getName());
            if(device.isOpen()) { device.close();}
        });
    }
}
