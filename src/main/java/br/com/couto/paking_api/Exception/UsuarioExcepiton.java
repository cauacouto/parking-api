package br.com.couto.paking_api.Exception;

public class UsuarioExcepiton extends RuntimeException {

    public UsuarioExcepiton() {
        super("usuario não encontrodo");
    }

    public UsuarioExcepiton(String message) {
        super(message);
    }
}
