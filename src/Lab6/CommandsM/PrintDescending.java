package Lab6.CommandsM;

import Lab6.Source.LabWork;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Класс, осуществлящщий фильтрацию элементов коллекции по заданной возрастанию
 */
public class PrintDescending extends Show{

    static class DescendingComportaor implements Comparator<LabWork> {
        @Override
        public int compare(LabWork lab, LabWork lab2) {
            return lab2.getName().compareTo(lab.getName()) ;
        }
    }

    @Override
    public String getName() {
        return "PRINT_DESCENDING";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        ArrayList<LabWork> tempo = new ArrayList<>(list);
        tempo.sort(new DescendingComportaor());
        super.execute(tempo);    }

    @Override
    public void printAnswer() {
        System.out.println("Коллекция отсортирована по убыванию.(Я-А)");
        super.printAnswer();
    }
}
