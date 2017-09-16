public class Main {

    public static void main(String[] args) {
        boolean allPass = true;

        System.out.println("Testing unlock");
        int numTests = 1000000000;
        for (int i = 0; i < numTests; i++) {
            Device dev = new Device(4, 2);
            if (!FourBitTwoDisclosureDeviceUnlocker.unlock(dev)) {
                System.out.println("Failure on try #"+ i);
                allPass = false;
            }
        }

        if (allPass) {
            System.out.println("All " + numTests + " tests passed!");
        }

        int[] spins = FourBitTwoDisclosureDeviceUnlocker.spins;
        System.out.print("frequencydata={{0," + spins[0] + "}");
        for (int i = 1; i < spins.length; i++) {
            System.out.printf(",{%d,%d}", i, spins[i]);
        }
        System.out.println("}");
    }
}
