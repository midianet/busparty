package midianet.busparty.domain.exception;

public class NotFoundException extends BussinesException {

    public NotFoundException() {
        super("Not Found");
    }

}