package br.com.couto.paking_api.Exception;

public class VeiculoExcepiton extends RuntimeException {

    public VeiculoExcepiton() {
        super("veiculos não encontrodo");
    }

    public VeiculoExcepiton(String message) {
        super(message);
    }
}
