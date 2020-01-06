package news.blog.com.exception;

public class NotValidDataException extends Exception
{
    public NotValidDataException() {}

    public NotValidDataException(String message)
    {
        super(message);
    }
}
