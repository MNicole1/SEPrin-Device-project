public class Main {

    public static void main(String[] args) {
        boolean allPass = true;

        System.out.println("Testing unlock");
        for (int i = 0; i < 10000; i++) {
            Device dev = new Device(4, 2);
            if (!FourBitTwoDisclosureDeviceUnlocker.unlock(dev)) {
                System.out.println("Failure on try #"+ i);
                allPass = false;
            }
            System.out.println(i);
        }

        if (allPass) {
            System.out.println("All 10000 tests passed!");
        }
    }
}
