package Lab6.CommandsM;

import Lab6.CommandsM.General.Executable;
import Lab6.Source.LabWork;

import java.util.ArrayList;

/**
 * Класс, осущеествляющий вывод информацию о коллекции
 */
public class Info implements Executable {

    private String answer;

    @Override
    public String getName() {
        return "INFO";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {

        if(list == null || list.size()==0){
            answer = "Пустая коллекция";
        }else {
            java.time.LocalDateTime minDate = list.get(0).getCreationDate();
            for (LabWork labWork : list)
                if (labWork.getCreationDate().compareTo(minDate) < 0) minDate = labWork.getCreationDate();

            answer = "Коллекция из элементов LabWork\nСоздана " + minDate + "\nРазмер: " + list.size() + " элементов";
        }
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }
}
