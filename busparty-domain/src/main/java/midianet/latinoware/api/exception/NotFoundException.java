package midianet.latinoware.api.exception;

public class NotFoundException extends BussinesException {

    public NotFoundException() {
        super("Registro Não encontrado");
    }

}