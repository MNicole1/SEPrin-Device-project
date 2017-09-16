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

            String peekPattern = shufflePattern('?', '?', '-', '-');
            log("peek(\"" + peekPattern + "\")");
            CharSequence peekResult = dev.peek(peekPattern);
            log("peek result: \"" + peekResult + "\"");

            String pokePattern = peekPattern.replace('?', 'T');
            log("poke(\"" + pokePattern + "\")");
            dev.poke(pokePattern);
        }

        // By now unlocked is true if we were successful in unlocking
        // the device and false if we reached 100 tries without success.
        return unlocked;
    }

    /**
     * Takes an array of characters and returns a random permutation of them
     * as a string.
     * @param chars an arbitrary length array of Character objects.
     * @return a string representing a permutation of the input characters.
     */
    private static String shufflePattern (Character... chars) {
        List<Character> baseList = Arrays.asList(chars);

        Collections.shuffle(baseList);

        StringBuilder returnValue = new StringBuilder();
        for (Character c : baseList) {
            returnValue.append(c);
        }
        return returnValue.toString();
    }

    // Note that log and showTrace are already implemented in DeviceUnlocker
}
