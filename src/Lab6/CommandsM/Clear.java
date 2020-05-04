package Lab6.CommandsM;

import Lab6.CommandsM.General.Executable;
import Lab6.Source.LabWork;

import java.util.ArrayList;

/**
 * Класс, осуществляющий очищение коллекции
 */
public class Clear implements Executable {

    @Override
    public String getName() {
        return "CLEAR";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        list.clear();
    }

    @Override
    public void printAnswer() {
        System.out.println("Коллекция очищена");
    }
}
