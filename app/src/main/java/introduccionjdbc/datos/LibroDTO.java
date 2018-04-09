package introduccionjdbc.datos;

import java.io.Serializable;

/**
 * Created by Usuario on 04/04/2018.
 */

public class LibroDTO implements Serializable {

    private int id;
    private String isbn;
    private String titulo;
    private String autor;

    public LibroDTO() {
    }

    public LibroDTO(int id, String isbn, String titulo, String autor) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibroDTO libroDTO = (LibroDTO) o;

        if (id != libroDTO.id) return false;
        if (isbn != null ? !isbn.equals(libroDTO.isbn) : libroDTO.isbn != null) return false;
        if (titulo != null ? !titulo.equals(libroDTO.titulo) : libroDTO.titulo != null)
            return false;
        return autor != null ? autor.equals(libroDTO.autor) : libroDTO.autor == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\''
                ;
    }
}
