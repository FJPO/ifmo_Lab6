package Lab6.ModuleServer;

import Lab6.CommandsM.Exit;
import Lab6.CommandsM.General.CommandKeeper;
import Lab6.CommandsM.General.Executable;
import Lab6.CommandsM.ServerSpecialCommands.Save;
import Lab6.Source.LabWork;
import Lab6.ModuleServer.FileInteraction.FileInteractor;

import java.io.*;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Основной класс сервера
 */
public class ServerSide {


    private ArrayList<LabWork> list;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringBuilder input = new StringBuilder();

    /**
     * Метод отправляет объект клиетну
     * @param obj Объект, который должен быть отправлен
     * @param channel Канал для связи с клиентом
     */
    private void sendObject(Object obj, SocketChannel channel) throws IOException{
        if(channel == null){
            System.err.println("Соединене не создано");
            return;
        }
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(byteArray);
        stream.writeObject(obj);
        channel.write(ByteBuffer.wrap(byteArray.toByteArray()));
    }

    /**
     * Метод получает объект
     * @param channel Канал для связи с клиентом
     * @return Объект, полученный от сервера
     */
    private Object receiveObject(SocketChannel channel) throws IOException, ClassNotFoundException{
        if(channel == null){
            System.err.println("Соединене не создано");
            return null;
        }
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        channel.read(buffer);

        try {
            ObjectInputStream objStream = new ObjectInputStream(new ByteArrayInputStream(buffer.array()));
            return objStream.readObject();
        }catch(Exception e){
            return null;
        }
    }


    /**
     * Метод, отвечающий за общую работу сервера
     * @param path Путь к файлу с коллекцией
     * @param port Номер порта сервера
     */
    public void loop(String[] path, int port){


        while(true){
            try{
                list = FileInteractor.readData(path[0]);
                break;
            } catch(Exception e){
                System.out.println("Не удалось открыть файл\nВведите название файла");
                path = new String[]{new Scanner(System.in).nextLine()};
            }
        }
        System.out.println("Данные загружены из файла");
        System.out.printf("Адрес %s; порт %d\n", "127.0.0.1", port);
        System.out.println("Доступна специальная команда save, сохраняющая все изменения в файл");

        try(Selector selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open().bind(new InetSocketAddress(port), 7)
        ) {
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                consoleHandler();
                selector.selectNow();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (key.isAcceptable()) {
                        SocketChannel channel = serverChannel.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Принято новое соединение");
                    }

                    if (key.isReadable()) {
                        if(key.channel() instanceof SocketChannel) {
                            Object tempo = receiveObject(((SocketChannel) key.channel()));
                            if (!(tempo instanceof Executable)) continue;
                            Executable command = ((Executable) tempo);
                            command.execute(list);
                            System.out.println("Выполнена команда " + command.getName());
                            sendObject(command, ((SocketChannel) key.channel()));
                            if (command instanceof Exit) key.channel().close();
                        }else{
                            InputStream stream = Channels.newInputStream((ReadableByteChannel) key.channel());
                            DataInputStream inputStream = new DataInputStream(stream);
                            System.out.println(inputStream.readUTF());
                        }
                    }

                    iterator.remove();
                }

            }
        }
        catch(BindException e){
            System.out.println("Порт уже используется");
        }
        catch (IOException e) {
                e.printStackTrace();
                loop(path, port);
            }
        catch(ClassNotFoundException e){
                e.printStackTrace();
            }
    }

    private void consoleHandler(){
        try {
            if (!reader.ready()) return;
            if (reader.lines().anyMatch(one -> one.toUpperCase().equals("SAVE"))) {
                Executable save = new Save();
                save.execute(list);
                save.printAnswer();
                reader = new BufferedReader(new InputStreamReader(System.in));
            }
        }catch (IOException e) {return;}
    }
}
