package com.redhat.osas.lightshow;

import org.testng.annotations.Test;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Transmitter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class TestDevice {
    @Test
    public void detectDevice() throws MidiUnavailableException, InterruptedException, IOException {
        /*
        MidiDevice.Info[] devices = MidiSystem.getMidiDeviceInfo();
        Stream.of(devices).forEach(i ->
                System.out.printf("%s [%s] %s %s%n", i.getDescription(), i.getName(), i.getVendor(), i.getVersion()));
        MidiDevice device = MidiSystem.getMidiDevice(devices[1]);
        System.out.println(device);
        List<Transmitter> transmitters = device.getTransmitters();
        System.out.println(transmitters);
        Stream.of(transmitters).forEach(System.out::println);

        Transmitter transmitter = device.getTransmitter();
        System.out.println(transmitter);
        transmitter.setReceiver(new DumpReceiver(System.out));

        Thread.sleep(2000);
        */
        MidiCommon.listDevicesAndExit(true, false, true);
        MidiDevice.Info input=MidiCommon.getMidiDeviceInfo("O61", false);
        System.out.println("got device");
        System.out.println(input);
        MidiDevice device=MidiSystem.getMidiDevice(input);
        System.out.println(device);
        device.open();
        Transmitter transmitter=device.getTransmitter();
        transmitter.setReceiver(new DumpReceiver(System.out));
        Thread.sleep(10000);
        device.close();
    }
}
