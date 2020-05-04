package Lab6.CommandsM.ServerSpecialCommands;

import Lab6.CommandsM.General.Executable;
import Lab6.ModuleServer.FileInteraction.FileInteractor;
import Lab6.Source.LabWork;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Команда для сохранения коллекции
 */
public class Save implements Executable {
    @Override
    public String getName() {
        return "SAVE";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        try {
            FileInteractor.writeData(list, FileInteractor.getCurrentFile());
        }catch(IOException e){
            System.out.println("Не удалось сохранить файл");
        }
    }

    @Override
    public void printAnswer() {
        System.out.println("Коллекция сохранена");
    }
}
