/**
 * Created by artemiibezguzikov on 06.04.16.
 */
class Statistic {
    int allFamilies;
    double averageBefore = 0;
    double disp = 0;

    int[] numberOfVillagesInProvince = new int[60];
    int[] numberOfFamiliesInProvince = new int[60];
    double[] numberOfEarthBeforeInProvince = new double[60];

    public Statistic(Village[] villages, int number) {
        for (int i = 0; i < number; ++i) {
            numberOfVillagesInProvince[villages[i].provinceId]++;
            numberOfFamiliesInProvince[villages[i].provinceId]+= villages[i].numberOfFamilies;
            numberOfEarthBeforeInProvince[villages[i].provinceId]+= villages[i].numberOfEarthBefore;

            averageBefore += ((double) villages[i].numberOfEarthBefore);
        }

        for (int i = 0; i < 60; ++i) {
            allFamilies += numberOfFamiliesInProvince[i];
        }

        averageBefore /= allFamilies;

        for (int i = 0; i < number; ++i) {
            disp += (villages[i].numberOfEarthBefore / villages[i].numberOfFamilies - averageBefore)
                    * (villages[i].numberOfEarthBefore / villages[i].numberOfFamilies - averageBefore)
                    * villages[i].numberOfFamilies;
        }

        disp /= allFamilies;

    }

    void print() {
        for (int i = 0; i < 60; ++i) {
            System.out.println("губерния: " + i + ", число деревень: " + numberOfVillagesInProvince[i]);
            System.out.print("число семей: " + numberOfFamiliesInProvince[i]);
            System.out.print(", количество земли: " + numberOfEarthBeforeInProvince[i]);
            System.out.println();
        }
    }
}