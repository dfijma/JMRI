package jmri.jmrix.easydcc;

import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Paul Bender Copyright (C) 2017	
 */
public class EasyDccThrottleManagerTest extends jmri.managers.AbstractThrottleManagerTestBase {

    @Test
    public void testCTor() {
        // infrastructure objects
        Assert.assertNotNull("exists",tm);
    }

    // The minimal setup for log4J
    @Before
    public void setUp() {
        JUnitUtil.setUp();
        EasyDccTrafficControlScaffold tc = new EasyDccTrafficControlScaffold(null);
        EasyDccSystemConnectionMemo memo = new EasyDccSystemConnectionMemo(tc);
        tm = new EasyDccThrottleManager(memo);
    }

    @After
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(EasyDccThrottleManagerTest.class);

}
