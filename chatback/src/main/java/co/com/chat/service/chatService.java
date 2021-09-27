package co.com.chat.service;

import java.util.List;

import co.com.chat.model.Mensaje;

public interface chatService {
    public List<Mensaje> findLast10Message();

    public Mensaje guardar(Mensaje m);
}
