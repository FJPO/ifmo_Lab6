package Lab6.Source;



import Lab6.ModuleServer.FileInteraction.XmlTools;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LabWork implements Xml_Interchangeable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private int tunedInWorks;
    private Double averagePoint; //Поле может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле не может быть null



    public LabWork(){}

    public LabWork(String name, Coordinates coordinates, Double minimalPoint, int turnedInWorks, Double averagePoint,
                   Difficulty difficulty, Person author) {
        if (name == null || name.equals("") || coordinates == null || minimalPoint == null || author == null ||
                    minimalPoint < 0)
            throw new IllegalArgumentException();

        if (averagePoint != null)
            if(averagePoint < 0)
                throw new IllegalArgumentException();

        id = (int)(Math.random() * 1000000);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = turnedInWorks;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.author = author;
    }

    public LabWork(int id, String name, Coordinates coordinates, LocalDateTime creationDate,
                   Double minimalPoint, int turnedInWorks, Double averagePoint,
                   Difficulty difficulty, Person author) {

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.tunedInWorks = turnedInWorks;
        this.averagePoint = averagePoint;
        this.difficulty = difficulty;
        this.author = author;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public int getTunedInWorks() {
        return tunedInWorks;
    }

    public Double getAveragePoint() {
        return averagePoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public void setAveragePoint(Double averagePoint) {
        this.averagePoint = averagePoint;
    }

    public void setMinimalPoint(Double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setTunedInWorks(int tunedInWorks) {
        this.tunedInWorks = tunedInWorks;
    }

    @Override
    public String toString() {
        return "\n***\nЛабораторная работа:\n\n" + "id: " + id + "\nНазване: " + name + "\n" + coordinates +
                "\nВремя создания: " + creationDate
                + "\nПроходной балл: " + minimalPoint + "\nЕще какой-то параметер: " + tunedInWorks +
                "\nСредний Балл: " + averagePoint + "\n" + difficulty + "\n" + author + "\n*****";
    }

    /**здесь заюзан StringBuilder*/
    @Override
    public String createXml() {
        StringBuilder line = new StringBuilder("<LabWork>\n");
        line.append(XmlTools.createXmlLine("id", String.valueOf(id)));
        line.append(XmlTools.createXmlLine("name", name));
        line.append(coordinates.createXml());
        //line.append(creationDate);

        line.append(XmlTools.createXmlLine("creationDate", creationDate.toString()  ));

        //end of creation date creation
        line.append(XmlTools.createXmlLine("minimalPoint", String.valueOf(minimalPoint)));
        line.append(XmlTools.createXmlLine("tunedInWorks", String.valueOf(tunedInWorks)));
        line.append(XmlTools.createXmlLine("averagePoint", String.valueOf(averagePoint)));
        line.append(difficulty.createXml());
        line.append(author.createXml());
        return line.append("</LabWork>\n").toString();
    }


}
