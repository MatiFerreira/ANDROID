package com.example.idsfv10.DATAGYM;

public class ListExercise {
    private String nameExercise;
    private int imageExersice;
    private String DataExercise;

    public ListExercise() {

    }

    public ListExercise(String nombre, int imagen, String DataExercise) {
        this.imageExersice = imagen;
        this.nameExercise = nombre;
        this.DataExercise = DataExercise;
    }

    public void setNameExercise(String nameExercise) {
        this.nameExercise = nameExercise;
    }

    public void setImageExersice(int imageExersice) {
        this.imageExersice = imageExersice;
    }

    public void setDataExercise(String dataExercise) {
        DataExercise = dataExercise;
    }

    public String getNameExercise() {
        return nameExercise;
    }

    public int getImageExersice() {
        return imageExersice;
    }

    public String getDataExercise() {
        return DataExercise;
    }
}
