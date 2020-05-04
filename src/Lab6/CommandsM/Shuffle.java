package Lab6.CommandsM;

import Lab6.CommandsM.General.Executable;
import Lab6.Source.LabWork;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс, осуществляющий перемещивание элементов в случайном порядке
 */
public class Shuffle implements Executable {
    @Override
    public String getName() {
        return "SHUFFLE";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        Collections.shuffle(list);
    }

    @Override
    public void printAnswer() {
        System.out.println("Элементы коллекции перемешаны");
    }
}
