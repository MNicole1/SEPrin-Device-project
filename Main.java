public class Main {

    public static void main(String[] args) {
        System.out.println("Testing unlock");
        Device dev = new Device(4, 2);
        System.out.println(FourBitTwoDisclosureDeviceUnlocker.unlock(dev));
    }
}
