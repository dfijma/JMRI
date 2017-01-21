// ConfigurationManager.java
package jmri.jmrix.can;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Does configuration for various CAN-based communications implementations.
 * <p>
 * It would be good to replace this with properties-based method for redirecting
 * to classes in particular subpackages.
 *
 * @author	Bob Jacobsen Copyright (C) 2009
 */
abstract public class ConfigurationManager {

    final public static String MERGCBUS = "MERG CBUS";
    final public static String OPENLCB = "OpenLCB";
    final public static String RAWCAN = "Raw CAN";
    final public static String TEST = "Test - do not use";

    private static String[] options = new String[]{MERGCBUS, OPENLCB, RAWCAN, TEST};

    /**
     * Provide the current set of "Option1" values
     */
    @SuppressFBWarnings({"EI_EXPOSE_REP", "MS_EXPOSE_REP"}) // OK until Java 1.6 allows return of cheap array copy
    static public String[] getSystemOptions() {
        return options;
    }

    /**
     * Set the list of protocols to start with OpenLCB
     */
    static public void setOpenLCB() {
        log.debug("setOpenLCB");
        options = new String[]{OPENLCB, MERGCBUS, RAWCAN, TEST};
    }

    /**
     * Set the list of protocols to start with MERG
     */
    static public void setMERG() {
        log.debug("setMERG");
        options = new String[]{MERGCBUS, OPENLCB, RAWCAN, TEST};
    }

    public ConfigurationManager(CanSystemConnectionMemo memo) {
        adapterMemo = memo;
    }

    protected CanSystemConnectionMemo adapterMemo;

    abstract public void configureManagers();

    /**
     * Tells which managers this provides by class
     */
    abstract public boolean provides(Class<?> type);

    @SuppressWarnings("unchecked")
    abstract public <T> T get(Class<?> T);

    abstract public void dispose();

    abstract protected ResourceBundle getActionModelResourceBundle();

    private final static Logger log = LoggerFactory.getLogger(ConfigurationManager.class.getName());
}

/* @(#)ConfigurationManager.java */
