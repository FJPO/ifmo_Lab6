package Lab6.CommandsM;

import Lab6.Source.Difficulty;
import Lab6.Source.LabWork;

import java.util.ArrayList;

/**
 * Класс, осуществляющий добавление в коллекцию при условии, что доавляемый элемент максимальный по сложности
 */
public class AddIfMax extends AddElement {

    @Override
    public String getName() {
        return "ADD_IF_MAX";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        Difficulty difficulty = getToAdd().getDifficulty();
        if (list.stream().noneMatch(element -> element.getDifficulty().compareTo(difficulty) > 0))
            super.execute(list);
        else
            setAnswer("Элемент не максимальный по сложности, он не добавлен");

    }
}
