package Lab6.CommandsM;

import Lab6.CommandsM.General.Executable;
import Lab6.CommandsM.ServerSpecialCommands.Save;
import Lab6.Source.LabWork;

import java.util.ArrayList;

/**
 * Класс, осуществляющий завршение работы клиента
 */
public class Exit implements Executable {
    @Override
    public String getName() {
        return "EXIT";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        Executable save = new Save();
        save.execute(list);
        save.printAnswer();
        System.out.println("Клиент отключился, изменения сохранены");
    }

    @Override
    public void printAnswer() {
        System.out.println("Команда выполняется");
        System.exit(0);
    }
}
