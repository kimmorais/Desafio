package exception;

public class EmailJaUtilizadoException extends GenericApiDesafioException {
    private static final long serialVersionUID = 1L;
    public EmailJaUtilizadoException(String mensagemEmailJaFoiUtilizado) {
        super(mensagemEmailJaFoiUtilizado);
    }
}
