import java.io.File;

public class Books
{
    private int id;
    private String name;
    private String release_year;
    private File file;

    public void Books(int id,String name,String release_year)
    {
        this.id = id;
        this.name = name;
        this.release_year = release_year;
    }
}
