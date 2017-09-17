public class Main {

    public static void main(String[] args) {
        boolean allPass = true;

        System.out.println("Testing unlock");
        int numTests = 100;
        for (int i = 0; i < numTests; i++) {
            Device dev = new Device(4, 2);
            if (!FourBitTwoDisclosureDeviceUnlocker.unlock(dev)) {
                System.out.println("Failure on try #"+ i);
                allPass = false;
            }
            String trace = FourBitTwoDisclosureDeviceUnlocker.showTrace();
            System.out.println(trace);
        }

        if (allPass) {
            System.out.println("All " + numTests + " tests passed!");
        }
    }
}
