package jmri.jmrix;

import java.util.ResourceBundle;
import javax.swing.JMenu;
import jmri.jmrix.jmriclient.json.swing.JsonClientMenu;
import jmri.jmrix.swing.ComponentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provide a "Systems" menu containing the Jmri system-specific tools in
 * submenus.
 * <p>
 * This contains all compiled systems, whether active or not. For the set of
 * currently-active system-specific tools, see {@link ActiveSystemsMenu}.
 * To be updated when a connection type is migrated to support multiple connections.
 *
 * @see ActiveSystemsMenu
 * @author Bob Jacobsen Copyright 2003
 * @author Egbert Broerse 2017
 */
public class SystemsMenu extends JMenu {

    public SystemsMenu(String name) {
        this();
        setText(name);
    }

    public SystemsMenu() {

        super();

        ResourceBundle rb = ResourceBundle.getBundle("jmri.jmrix.JmrixSystemsBundle");

        setText(rb.getString("MenuSystems"));

        // Put configured menus at top
        // get ComponentFactory object(s) and create menus
        java.util.List<ComponentFactory> list
                = jmri.InstanceManager.getList(ComponentFactory.class);

        for (ComponentFactory memo : list) {
            JMenu menu = memo.getMenu();
            if (menu != null) {
                add(menu);
            }
        }

        add(new javax.swing.JSeparator());

        // Acela is migrated
        add(new jmri.jmrix.acela.AcelaMenu(null));
        // Bachrus is migrated
        add(new jmri.jmrix.bachrus.SpeedoMenu(null));
        // CAN is migrated
        add(new jmri.jmrix.can.swing.CanMenu(null));
        // Merg CBUS is migrated
        add(new jmri.jmrix.can.cbus.swing.CbusMenu(null));
        // C/MRI is migrated
        add(new jmri.jmrix.cmri.CMRIMenu(null));
        // DCC++ is migrated
        add(new jmri.jmrix.dccpp.swing.DCCppMenu(null));
        // EasyDCC is migrated
        add(new jmri.jmrix.easydcc.EasyDccMenu(null));

        addMenu("jmri.jmrix.grapevine.GrapevineMenu");

        // LocoNet is migrated
        add(new jmri.jmrix.loconet.swing.LocoNetMenu(null));
        // NCE is migrated
        add(new jmri.jmrix.nce.swing.NceMenu(null));
        // OpenLCB is migrated
        add(new jmri.jmrix.openlcb.OpenLcbMenu(null));

        addMenu("jmri.jmrix.oaktree.OakTreeMenu");

        // Powerline is migrated
        add(new jmri.jmrix.powerline.swing.PowerlineMenu(null));
        addMenu("jmri.jmrix.pricom.PricomMenu"); // special type of connection, not to be migrated
        // QSI is migrated
        add(new jmri.jmrix.qsi.QSIMenu(null));

        addMenu("jmri.jmrix.rps.RpsMenu");
        addMenu("jmri.jmrix.secsi.SecsiMenu");
        // SPROG is migrated
        add(new jmri.jmrix.sprog.SPROGMenu(null));
        // SRCP is migrated
        add(new jmri.jmrix.srcp.swing.SystemMenu(null));

        addMenu("jmri.jmrix.tmcc.TMCCMenu");
        // Wangrow is migrated
        add(new jmri.jmrix.wangrow.WangrowMenu(null));
        // XpressNet is migrated
        add(new jmri.jmrix.lenz.swing.XNetMenu(null));
        // XPA is migrated
        add(new jmri.jmrix.xpa.swing.XpaMenu(null));
        // Zimo is migrated
        add(new jmri.jmrix.zimo.swing.Mx1Menu(null));

        add(new javax.swing.JSeparator());

        addMenu("jmri.jmrix.direct.DirectMenu");

        // NmraNet is migrated
        add(new jmri.jmrix.can.nmranet.swing.NmraNetMenu(null));
        // Ecos is migrated
        add(new jmri.jmrix.ecos.swing.EcosMenu(null));

        addMenu("jmri.jmrix.maple.MapleMenu");

        // JMRI Network Client is migrated
        add(new jmri.jmrix.jmriclient.swing.JMRIClientMenu(null));
        add(new JsonClientMenu(null));
        add(new jmri.jmrix.rfid.swing.RfidMenu(null));
        add(new jmri.jmrix.ieee802154.swing.IEEE802154Menu(null));
        add(new jmri.jmrix.ieee802154.xbee.swing.XBeeMenu(null));
    }

    void addMenu(String className) {
        JMenu j = null;
        try {
            j = (JMenu) Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.warn("Could not make menu from class " + className + "; " + e);
        }
        if (j != null) {
            add(j);
        }
    }

    private final static Logger log = LoggerFactory.getLogger(SystemsMenu.class);

}
