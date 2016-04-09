/**
 * Created by artemiibezguzikov on 06.04.16.
 */
class Pair {
    double a;
    double b;

    public Pair(double a, double b) {
        this.a = a;
        this.b = b;
    }

    boolean include(double c) {
        return (a <= c) && (c <= b);
    }

    public String toString() {
        return ("(" + a + "," + b +")");
    }
}

class PairP {
    Pair a;
    double b;

    public PairP(Pair a, double b) {
        this.a = a;
        this.b = b;
    }

    boolean include(double c, double d) {
        return a.include(c);
    }
}


class Algorithm	{
    Village[] villages;
    int numberOfVillages;
    Generator generator;
    Statistic statistic;
    Selection[] firstSelection;
    int allNumberOfOperations;
    int numberOfOperations;

    public Algorithm(Village[] villages, int number, Generator generator, Statistic statistic) {
        this.villages = villages;
        numberOfVillages = number;
        this.generator = generator;
        this.statistic = statistic;
        firstSelection = new Selection[60];
        numberOfOperations = 0;
        allNumberOfOperations = 0;
    }

    void prepareSelection(int procent) {
        for (int i = 0; i < 60; ++i) {
            firstSelection[i] = new Selection(statistic.numberOfVillagesInProvince[i] * procent / 100);
        }
    }

    void makeSelection() {
        boolean flag = true;
        do
        {
            flag = true;
            generator.getNextVillage();
            Village nextV = generator.currentVillage;
            numberOfOperations++;
            if (firstSelection[nextV.provinceId].put(nextV))
                allNumberOfOperations++;

            for (int i = 0; i < 60; ++i) {
                if (!firstSelection[0].ready)
                {
                    //	System.out.println(firstSelection[i].ready);
                    flag = false;
                    break;
                }
            }

        } while(!flag);
    }

    PairP checkSelection() {
        firstSelection[0].calculate();

        double exceptionBefore = 0;
        double averageBefore = 0;

	    averageBefore = firstSelection[0].averageBefore;

        System.out.println("! " + firstSelection[0].standartDeviationBefore + " "
                + (double) firstSelection[0].currentFamilies + " " + Math.sqrt((double) statistic.disp) +
                " " + firstSelection[0].averageBefore);
        exceptionBefore = firstSelection[0].standartDeviationBefore /
                Math.sqrt((double) firstSelection[0].currentVillages) * Math.sqrt(1 - ((double) firstSelection[0].currentVillages) / ((double) statistic.numberOfVillagesInProvince[0]));
    Pair pairBefore = new Pair(averageBefore - 2 * exceptionBefore, averageBefore + 2 * exceptionBefore);
        return new PairP(pairBefore, exceptionBefore);
    }
}
