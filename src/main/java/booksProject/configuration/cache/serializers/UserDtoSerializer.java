package booksProject.configuration.cache.serializers;

import booksProject.user.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import java.io.*;
import java.nio.ByteBuffer;

public class UserDtoSerializer implements Serializer<UserDto> {
    @Override
    public ByteBuffer serialize(UserDto userDto) throws SerializerException {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);

            String json = new ObjectMapper()
                    .writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(userDto);
            oout.writeObject(json);
            oout.flush();
            oout.close();
            baos.close();
            return ByteBuffer.wrap(baos.toByteArray());
        } catch (IOException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public UserDto read(ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {

        try{
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes, 0, bytes.length);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            bais.close();
            ois.close();
            return new ObjectMapper().reader().forType(UserDto.class).readValue(object.toString());
        } catch (IOException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public boolean equals(UserDto userDto, ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {
        return userDto.equals(read(byteBuffer));
    }

}
