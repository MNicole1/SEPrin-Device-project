public class Main {

    public static void main(String[] args) {
        boolean allPass = true;

        System.out.println("Testing unlock");
        for (int i = 0; i < 1000; i++) {
            Device dev = new Device(4, 2);
            if (!FourBitTwoDisclosureDeviceUnlocker.unlock(dev)) {
                System.out.println("Failure on try #"+ i);
                allPass = false;
            }
            String trace = FourBitTwoDisclosureDeviceUnlocker.showTrace();
            System.out.println(trace);
        }

        if (allPass) {
            System.out.println("All 1000 tests passed!");
        }
    }
}
