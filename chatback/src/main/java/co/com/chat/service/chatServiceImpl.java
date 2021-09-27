package co.com.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.chat.model.Mensaje;
import co.com.chat.repository.ChatRepository;

public class chatServiceImpl implements chatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Mensaje> findLast10Message() {
        // TODO Auto-generated method stub
        return chatRepository.findFirst10ByOrderTimestampDesc();
    }

    @Override
    public Mensaje guardar(Mensaje m) {
        // TODO Auto-generated method stub
        return chatRepository.save(m);
    }

}
