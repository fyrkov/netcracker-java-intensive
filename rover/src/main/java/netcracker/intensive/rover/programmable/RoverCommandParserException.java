package netcracker.intensive.rover.programmable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Это исключение должно быть непроверяемым - оно происходит, когда возникает ошибка чтения файла с программой для ровера.
 * Дальнейшее выполнение программы становится невозможным. Исключение должно содержать информации о породившем его исключении.
 */
public class RoverCommandParserException extends RuntimeException {

    private static Logger LOG = LoggerFactory.getLogger(RoverCommandParserException.class);

    public RoverCommandParserException(Exception e) {
        super();
        LOG.error("RoverCommandParserException caused by " + e.toString());
    }
}
