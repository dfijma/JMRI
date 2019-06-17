package jmri.jmrix;

import java.util.Vector;
import org.slf4j.Logger;
import purejavacomm.PortInUseException;

/**
 * Enable basic setup of a serial interface for a jmrix implementation.
 *
 * @author Bob Jacobsen Copyright (C) 2001, 2003, 2008
 * @see jmri.jmrix.SerialConfigException
 */
public interface SerialPortAdapter extends PortAdapter {

    /**
     * Provide a vector of valid port names, each a String.
     */
    public Vector<String> getPortNames();

    /**
     * Open a specified port.
     *
     * @param portName name tu use for this port
     * @param appName provided to the underlying OS during startup so
     *                that it can show on status displays, etc.
     */
    public String openPort(String portName, String appName);

    /**
     * Configure all of the other jmrix widgets needed to work with this adapter
     */
    @Override
    public void configure();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean status();

    /**
     * Remember the associated port name.
     *
     * @param s name of the port
     */
    public void setPort(String s);

    @Override
    public String getCurrentPortName();

    /**
     * Get an array of valid baud rates; used to display valid options in Connections Preferences.
     *
     * @return array of I18N display strings of port speed settings valid for this serial adapter,
     * must match order and values from {@link #validBaudNumbers()}
     */
    public String[] validBaudRates();

    /**
     * Get an array of valid baud rate numbers; used to store/load adapter speed option.
     *
     * @return integer array of speeds, must match order and values from {@link #validBaudRates()}
     */
    public int[] validBaudNumbers();

    /**
     * Set the baud rate. Only to be used after construction, but before the
     * openPort call.
     */
    public void configureBaudRate(String rate);

    /**
     * Set the baud rate by index from ValidBaudRates[].
     * <p>
     * Only to be used after construction, but before the openPort call.
     */
    public void configureBaudNumber(String index);

    public void configureBaudIndex(int index);

    public String getCurrentBaudRate();

    public String getCurrentBaudNumber();

    public int getCurrentBaudIndex();

    /**
     * Set the first port option. Only to be used after construction, but before
     * the openPort call
     */
    @Override
    public void configureOption1(String value);

    /**
     * Set the second port option. Only to be used after construction, but
     * before the openPort call
     */
    @Override
    public void configureOption2(String value);

    /**
     * Set the third port option. Only to be used after construction, but before
     * the openPort call
     */
    @Override
    public void configureOption3(String value);

    /**
     * Set the fourth port option. Only to be used after construction, but
     * before the openPort call
     */
    @Override
    public void configureOption4(String value);

    /**
     * Error handling for busy port at open.
     *
     * @see jmri.jmrix.AbstractSerialPortController
     */
    public String handlePortBusy(PortInUseException p, String portName, Logger log);

    /**
     * Return the System Manufacturers Name
     */
    @Override
    public String getManufacturer();

    /**
     * Set the System Manufacturers Name
     */
    @Override
    public void setManufacturer(String Manufacturer);

}
