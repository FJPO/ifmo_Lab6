package Lab6.CommandsM;

import Lab6.CommandsM.General.ExecutableWithInput;
import Lab6.Source.LabWork;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, осуществлящий удаление элемента по его id
 */
public class RemoveById implements ExecutableWithInput {

    private int id = -1;
    private String answer;
    private boolean success = true;

    @Override
    public String getName() {
        return "REMOVE_BY_ID";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        Object[] tempo = list.stream().filter(element -> element.getId() == id).toArray();
        if (tempo.length != 0) {
            list.remove(tempo[0]);
            answer = String.format("Элемент с id %d удален", id );
        } else answer = "Элемент с таким Id не найден";
    }

    @Override
    public void collectData(String arg) {
        recordData(arg);
        while(!isDataCorrect()){
            System.out.println("Введите id элемента:");
            collectData(new Scanner(System.in).nextLine());
        }

    }

    @Override
    public void recordData(String arg) {
        try {
            id = Integer.parseInt(arg);
            success = true;
        }catch(NumberFormatException e){
            success = false;
        }
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }

    @Override
    public boolean isDataCorrect() {
        return success;
    }
}
