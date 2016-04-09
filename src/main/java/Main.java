/**
 * Created by artemiibezguzikov on 06.04.16.
 */
public class Main {

    static String alignment_line(int number, String string){
        while(string.length() < number){
            string = " " + string;
        }
        return string;
    }

    public static void main(String[] args) {
        XMLReader reader = new XMLReader("/Users/artemiibezguzikov/Downloads/qreal-web-master 2/History/src/main/resources/A3.xlsx", 4);
       // int lengthOfXLS =
        String[][] mainAr = reader.getRes();
        Village[] villages = new Village[mainAr.length - 1];
        for (int i = 0; i < mainAr.length - 1; ++i) {
            for (int j = 0; j < mainAr[i].length; ++j) {
            if (mainAr[i][j] != null)
                System.out.print(alignment_line(15, mainAr[i][j]));
            }
            System.out.println(" " + i);
        }
        // -1 just becouse
        double aver = 0;
        for (int i = 0; i < villages.length - 1; ++i) {
            villages[i] = new Village(0, Double.valueOf(mainAr[i + 1][3]).intValue(), Double.valueOf(mainAr[i + 1][5]));
            aver += Double.valueOf(mainAr[i + 1][4]);
        }
        System.out.println(aver / villages.length);

        // step 1
        int numberOfOriginalVillages = villages.length -1;
        Village[] original = villages.clone();
        for (int i = 0; i < numberOfOriginalVillages; ++i) {
        //    original[i] = new Village();
            original[i].print();
        }

        Statistic myStatistic = new Statistic(original, numberOfOriginalVillages);
        Generator myGenerator = new Generator(original, numberOfOriginalVillages);
        Algorithm alg = new Algorithm(original, numberOfOriginalVillages, myGenerator, myStatistic);
        alg.prepareSelection(100);
        alg.makeSelection();

        PairP mainPair = alg.checkSelection();
        System.out.println("\n\n");
        System.out.println("интервал: " + mainPair.a.toString());
        System.out.println("ошибка: " + mainPair.b);
        System.out.println("генеральная средняя: " + myStatistic.averageBefore);
    }
}
