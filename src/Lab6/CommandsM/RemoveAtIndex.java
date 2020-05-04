package Lab6.CommandsM;

import Lab6.CommandsM.General.ExecutableWithInput;
import Lab6.Source.LabWork;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, осуществлящий удаление элемента по его индексу
 */
public class RemoveAtIndex implements ExecutableWithInput {

    private int index = -1;
    private String answer;


    @Override
    public String getName() {
        return "REMOVE_AT";
    }

    @Override
    public void execute(ArrayList<LabWork> list) {
        try {
            list.remove(index-1);
            answer = String.format("Элемент под индексом %d удален\n", index);
        }catch (Exception e){
            answer = "Элемент с таким индексом не найден";
        }
    }

    @Override
    public void printAnswer() {
        System.out.println(answer);
    }

    @Override
    public void collectData(String arg) {
        recordData(arg);
        while(!isDataCorrect()){
            System.out.println("Введите индекс(считая с 1):");
            collectData(new Scanner(System.in).nextLine());
        }
    }

    @Override
    public void recordData(String arg) {
        try{
            index = Integer.parseInt(arg);
        }catch(Exception ignored){}
    }

    @Override
    public boolean isDataCorrect() {
        return index > 0;
    }
}
