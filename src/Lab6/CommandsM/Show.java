package Lab6.CommandsM;

import Lab6.CommandsM.General.Executable;
import Lab6.Source.LabWork;

import java.util.ArrayList;

/**
 * Класс, осуществлящий вывод всех элементов коллекции
 */
public class Show implements Executable {

    private StringBuilder answer;

    @Override
    public String getName() {
        return "SHOW";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        answer = new StringBuilder();
        final Integer[] num = {1};
        answer.append("Коллекция представлена ниже:\n");
        list.forEach(element -> answer.append("\nЭлемент № ").append(num[0]++).append("\n").append(element.toString()).append("\n\n"));
        if(list.isEmpty())answer.append("Коолекция пуста\n");
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }
}
