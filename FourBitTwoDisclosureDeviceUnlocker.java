/**
 * Solution development for 4-bit/2-disclosure device.
 *
 * @author Dr. Jody Paul - API
 * @author Marco Nicoleti
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
    @Override
    public static boolean unlock(final Device dev) {
        // TODO
        return false;
    }

    // Note that log and showTrace are already implemented in DeviceUnlocker
}
