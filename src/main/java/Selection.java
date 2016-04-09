import java.util.ArrayList;

/**
 * Created by artemiibezguzikov on 06.04.16.
 */
class Selection {
    ArrayList<Village> villages;
    int maxVillages;
    int currentFamilies = 0;
    int currentVillages = 0;
    boolean ready = false;
    double averageBefore = 0;
    double standartDeviationBefore = 0;

    public Selection(int maxVillages) {
        this.maxVillages = maxVillages;
        villages = new ArrayList<Village>();
    }

    public boolean put(Village village) {
        if (ready)
            return false;

        villages.add(village);
        currentFamilies += village.numberOfFamilies;
        currentVillages++;
        if (currentVillages >= maxVillages)
            ready = true;

        return true;
    }

    public void calculate() {

        double helperBefore = 0;

        for (Village item : villages) {
            helperBefore = 0;
            helperBefore += ((double) item.numberOfEarthBefore);
            averageBefore += helperBefore;
        }
        averageBefore /= currentFamilies;

        for (Village item : villages) {
            helperBefore = 0;
            double ev;
            helperBefore += ((double) item.numberOfEarthBefore) / item.numberOfFamilies;
            standartDeviationBefore += (helperBefore - averageBefore) * (helperBefore - averageBefore) * item.numberOfFamilies;
        }
        standartDeviationBefore /= currentFamilies;
        standartDeviationBefore = Math.sqrt(standartDeviationBefore);
    }
}
