package apps.startup;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 *
 * @author Randall Wood
 * @deprecated since 4.21.1; use {@link jmri.util.startup.PerformScriptModelFactory} instead
 */
@Deprecated
@SuppressFBWarnings(value = "NM_SAME_SIMPLE_NAME_AS_SUPERCLASS", justification = "Deprecated by refactoring; retaining unchanged until removal")
public class PerformScriptModelFactory extends jmri.util.startup.PerformScriptModelFactory {

    public PerformScriptModelFactory() {
        super();
    }
}
