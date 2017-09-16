/**
 * Solution development for 4-bit/2-disclosure device.
 *
 * @author Dr. Jody Paul - API
 * @author Marco Nicoletti
 * @author Anthony Ales
 * @author Michael Brown
 * @author Daniel Collier
 * @author Alex Jones
 * @version 1.1.5
 * @see Device
 * @see <a href="../projectDescription.html">Project Description</a>
 */
public class FourBitTwoDisclosureDeviceUnlocker extends DeviceUnlocker {
    /**
     * Unlocks a resource controlled by a 4-bit/2-disclosure device.
     * Behavior is unspecified if parameter is not a reference to a valid
     * 4-bit/2-disclosure device.
     * @param dev the device controlling the resource to unlock;
     *        must be a 4-bit device with 2 peek/poke bits.
     * @return true if the resource is unlocked (all bits in the
     *         device are now identical); false otherwise
     */
    public static boolean unlock(final Device dev) {
        log(null); // Clear trace
        boolean unlocked = false; // DC: Could use some javadoc comments for this variable
        for (int i = 0; i < 100; i++) {
            log("Cycle #" + i);
            log("spin()");
            unlocked = dev.spin();
            if (unlocked) {
                log("Unlocked after " + i + " tries.");
                break;
            }
            log("peek(\"??  \")");
            CharSequence peekResult = dev.peek("??  ");
            log("peek result: \"" + peekResult + "\"");
            log("poke(\"TT  \")");
            dev.poke("TT  ");
        }

        return unlocked;
    }

    // Note that log and showTrace are already implemented in DeviceUnlocker
}
