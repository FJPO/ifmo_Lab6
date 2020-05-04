package Lab6.CommandsM;

import Lab6.CommandsM.General.ExecutableWithInput;
import Lab6.Source.Difficulty;
import Lab6.Source.LabWork;

import java.util.ArrayList;
import java.util.Scanner;

//Мог быть наследником addElement

/**
 * Класс, осуществлящщий фильтрацию элементов коллекции по заданной сложности
 */
public class FilterGreaterThanDifficulty implements ExecutableWithInput {

    private Difficulty limit;
    private boolean success;
    private StringBuilder answer;

    @Override
    public String getName() {
        return "FILTER_GREATER_THAN_DIFFICULTY";
    }

    @Override
    public void recordData(String arg) {
        try {
            limit = Difficulty.valueOf(arg.toUpperCase());
            success = true;
        }catch (Exception e){
            success = false;
        }
    }

    @Override
    public void collectData(String arg) {
        recordData(arg);
        while(!success) {
            System.out.println("Доступные сложности(ввод на английском):");
            for (Difficulty dif : Difficulty.values()) System.out.println(dif);
            collectData(new Scanner(System.in).nextLine());
        }
    }

    @Override
    public boolean isDataCorrect() {
        return success;
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        answer = new StringBuilder();
        final Integer[] num = {1};
        answer.append("Коллекция представлена ниже:\n");
        list.stream().filter(element -> element.getDifficulty().compareTo(limit) > 0).
                forEach(element -> answer.append("\nЭлемент № ").append(num[0]++).append("\n").append(element.toString()).append("\n\n"));
        if(list.isEmpty())answer.append("Элементы с такой сложностью не найдены\n");
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }
}
