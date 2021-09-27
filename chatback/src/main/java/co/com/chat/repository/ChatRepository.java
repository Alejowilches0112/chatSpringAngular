package co.com.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.chat.model.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje, String> {

    public List<Mensaje> findFirst10ByOrderTimestampDesc();

}
