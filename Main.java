public class Main {

    public static void main(String[] args) {
        boolean allPass = true;

        System.out.println("Testing unlock");

        int numTests = 1000000;
        long startNanos = System.nanoTime();

        for (int i = 0; i < numTests; i++) {
            Device dev = new Device(4, 2);
            if (!FourBitTwoDisclosureDeviceUnlocker.unlock(dev)) {
                System.out.println("Failure on try #"+ i);
                allPass = false;
            }
        }

        double durationMicros = (System.nanoTime() - startNanos) / 1000.0;

        if (allPass) {
            System.out.printf("All %d tests passed!", numTests);
            System.out.printf("On average it took %.03f microseconds for each" +
                    " unlock%n", durationMicros / numTests);
        }

        System.out.println("Mathematica copy-paste:\n");

        // Note: This requires an extra array to be added to your unlocker
        //  This array should be as large as the number of tries before halting
        //  At successful unlock you should do spins[spinCount]++; such that at
        //  the end of all of trials spins[i] represents the number of trials
        //  took i spins to unlock.
        int[] spins = FourBitTwoDisclosureDeviceUnlocker.spins;
        int lastIndex;
        for (lastIndex = spins.length - 1; lastIndex >= 0; lastIndex--) {
            if (spins[lastIndex] != 0) {
                break;
            }
        }
        System.out.print("frequencydata={{0," + spins[0] + "}");
        for (int i = 1; i <= lastIndex; i++) {
            System.out.printf(",{%d,%d}", i, spins[i]);
        }
        System.out.println("}");
        System.out.println("bars=BarChart[frequencydata[[All,2]], " +
                "ImageSize->Large, ChartLabels->frequencydata[[All,1]]]");
        System.out.println("barsLog=BarChart[frequencydata[[All,2]], " +
                "ImageSize->Large, ChartLabels->frequencydata[[All,1]], " +
                "ScalingFunctions->\"Log\"]");
        // sometimes useless statistical data
        System.out.println("flatlist=Flatten[Table[#1, {#2}] & @@@ " +
                "frequencydata]");
        System.out.println("distro=FindDistribution[flatlist]");
        System.out.println("pdfplot=Plot[PDF[distro,x],{x,0," + lastIndex +
                "}]");
        System.out.println("hist=Histogram[flatlist, Automatic, " +
                "\"Probability\"]");
        System.out.println("shown=Show[hist,pdfplot]");
        System.out.println("p_loss=PDF[distro,100]");
        // Note: You'll have to change these paths here
        System.out.println("Export[\"/full/path/to/destination/directory/bars" +
                ".png\", bars]");
        System.out.println("Export[\"/full/path/to/destination/directory" +
                "/barsLog.png\", barsLog]");
        System.out.println("Export[\"/full/path/to/destination/directory" +
                "/stats.png\", shown]");
        // Note: Further suggestions are to look at the barsLog image. If
        // that suggests you have a logarithmic decay after some max you
        // might be able to solve for that logarithmic equation from the max
        // onwards and use that to roughly estimate the "probability" of
        // reaching your stopping number of iterations.
    }
}
