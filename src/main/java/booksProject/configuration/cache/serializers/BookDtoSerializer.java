package booksProject.configuration.cache.serializers;

import booksProject.books.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import java.io.*;
import java.nio.ByteBuffer;

public class BookDtoSerializer implements Serializer<BookDto>{

    @Override
    public ByteBuffer serialize(BookDto bookDto) throws SerializerException {
        try{ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);

            String json = new ObjectMapper()
                    .writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(bookDto);
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
    public BookDto read(ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {

        try{
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes, 0, bytes.length);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            bais.close();
            ois.close();
            return new ObjectMapper().reader().forType(BookDto.class).readValue(object.toString());
        } catch (IOException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public boolean equals(BookDto bookDto, ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {
        return bookDto.equals(read(byteBuffer));
    }
}
