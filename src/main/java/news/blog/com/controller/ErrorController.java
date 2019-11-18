package news.blog.com.controller;

import news.blog.com.exception.BadRequestException;
import news.blog.com.exception.ForbiddenException;
import news.blog.com.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static news.blog.com.util.Constants.EXCEPTION_TITLE;


@ControllerAdvice
public class ErrorController
{
    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity forbiddenException(final ForbiddenException throwable)
    {
        logger.error(EXCEPTION_TITLE, throwable);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(throwable.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity badRequestException(final BadRequestException throwable)
    {
        logger.error(EXCEPTION_TITLE, throwable);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(throwable.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity notFoundException(final NotFoundException throwable)
    {
        logger.error(EXCEPTION_TITLE, throwable);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(throwable.getMessage());
    }
}
