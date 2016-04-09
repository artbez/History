import java.util.Random;

/**
 * Created by artemiibezguzikov on 06.04.16.
 */
class Village
{
    int id = 0;
    int provinceId = 0;
    int numberOfFamilies = 0;
    double numberOfEarthBefore = 0;

    public Village() {
        final Random random = new Random();
        provinceId = random.nextInt(1);
        numberOfFamilies = random.nextInt(300) + 5;
        numberOfEarthBefore = random.nextInt(43 * numberOfFamilies) + 7 * numberOfFamilies;
    }

    public Village(int provinceId, int numberOfFamilies, double numberOfEarthBefore) {
        this.provinceId = provinceId;
        this.numberOfFamilies = numberOfFamilies;
        this.numberOfEarthBefore = numberOfEarthBefore;
    }

    public void print() {
        System.out.println("губерния:" + provinceId + ", число семей: " + numberOfFamilies +
                ", количество земли: " + numberOfEarthBefore);
    }
}