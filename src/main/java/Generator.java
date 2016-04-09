import java.util.Random;

/**
 * Created by artemiibezguzikov on 06.04.16.
 */
class Generator {
    boolean[] visitedVillages;
    Village[] villages;
    int numberOfVillages;
    Village currentVillage;

    public Generator(Village[] villages,int numberOfVillages) {
        this.numberOfVillages = numberOfVillages;
        visitedVillages = new boolean[numberOfVillages];
        for (int i = 0; i < numberOfVillages; ++i) {
            visitedVillages[i] = false;
        }
        this.villages = villages;
    }

    void getNextVillage() {
        final Random random = new Random();
        int next = 0;
        do {
            next = random.nextInt(numberOfVillages);
        } while(visitedVillages[next]);
        currentVillage = villages[next];
        visitedVillages[next] = true;
    }
}