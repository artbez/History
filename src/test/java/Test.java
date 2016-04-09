/**
 * Created by artemiibezguzikov on 06.04.16.
 */
class Test {

    int numberOk = 0;
    int numberNot = 0;

    public Test(int number) {
        for (int k = 0; k < number; ++k) {
            int numberOfOriginalVillages = 500;
            Village[] original = new Village[numberOfOriginalVillages];
            for (int i = 0; i < numberOfOriginalVillages; ++i) {
                original[i] = new Village();
            }

            Statistic myStatistic = new Statistic(original, numberOfOriginalVillages);
            Generator myGenerator = new Generator(original, numberOfOriginalVillages);
            Algorithm alg = new Algorithm(original, numberOfOriginalVillages, myGenerator, myStatistic);
            alg.prepareSelection(20);
            alg.makeSelection();
            PairP mainPair = alg.checkSelection();
            if (mainPair.a.include(myStatistic.averageBefore))
            {
                System.out.println("ОК");
                numberOk++;
            }
            else
            {
                System.out.println("NOT");
                numberNot++;
            }
        }
    }
}