import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Solution development for 4-bit/2-disclosure device.
 *
 * @author Dr. Jody Paul - API
 * @author Anthony Ales
 * @author Michael Brown
 * @author Daniel Collier
 * @author Alex Jones
 * @author Marco Nicoletti
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
    public static boolean unlock (final Device dev) {
        log(null); // Clear trace
        boolean unlocked = false;

        for (int i = 0; i < 100; i++) {
            log("Try #" + i);
            log("spin()");

            // Set unlocked to true if all bits are identical
            unlocked = dev.spin();
            if (unlocked) {
                log("Unlocked after " + i + " tries.");
                break;
            }

            String peekPattern = randomPeekPattern('?', '?','-','-');
            log("peek(\"" + peekPattern + "\")");
            CharSequence peekResult = dev.peek(peekPattern);
            log("peek result: \"" + peekResult + "\"");

            String pokePattern = peekPattern.replace('?', 'T');
            log("poke(\"" + pokePattern + "\")");
            dev.poke(pokePattern);
        }

        // By now unlocked is true if we were successful in unlocking the device
        // and false if we reached 100 tries without success.
        return unlocked;
    }

    private static String randomPeekPattern (Character... chars) {
        List<Character> baseList = Arrays.asList(chars);
        Collections.shuffle(baseList);
        StringBuilder temp = new StringBuilder();
        for (Character c : baseList) {
            temp.append(c);
        }
        return temp.toString();
    }

    // Note that log and showTrace are already implemented in DeviceUnlocker
}
