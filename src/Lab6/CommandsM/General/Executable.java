package Lab6.CommandsM.General;

import Lab6.Source.LabWork;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Интерфейс для реализации команд
 */
public interface Executable extends Serializable {

    /**
     * Возвращает название команды
     */
    String getName();

    /**
     * Выполняет команду (предполагается, что на сервере)
     * @param list Коллекция LabWork
     */
    void execute(ArrayList<LabWork> list);

    /**
     * Пишет в консоль ответ, записанный во время выполнения команды execute на сервере
     */
    void printAnswer();

}
