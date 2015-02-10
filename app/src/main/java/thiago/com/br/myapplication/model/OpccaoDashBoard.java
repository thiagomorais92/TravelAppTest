package thiago.com.br.myapplication.model;

/**
 * Created by Samsung on 10/02/2015.
 */
public class OpccaoDashBoard {
    String title;
    int imagen;

    public OpccaoDashBoard(String title, int imagen) {
        this.title = title;
        this.imagen = imagen;
    }

    public String getTitle() {
        return title;
    }

    public int getImagen() {
        return imagen;
    }
}
