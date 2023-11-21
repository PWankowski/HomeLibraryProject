package booksProject.configuration.cache.serializers;

import booksProject.customer.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehcache.spi.serialization.Serializer;
import org.ehcache.spi.serialization.SerializerException;

import java.io.*;
import java.nio.ByteBuffer;

public class CustomerDtoSerializer implements Serializer<CustomerDto> {
    @Override
    public ByteBuffer serialize(CustomerDto customerDto) throws SerializerException {
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);

            String json = new ObjectMapper()
                    .writer()
                    .withDefaultPrettyPrinter()
                    .writeValueAsString(customerDto);
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
    public CustomerDto read(ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {

        try{
            byte[] bytes = new byte[byteBuffer.remaining()];
            byteBuffer.get(bytes, 0, bytes.length);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object object = ois.readObject();
            bais.close();
            ois.close();
            return new ObjectMapper().reader().forType(CustomerDto.class).readValue(object.toString());
        } catch (IOException e) {
            throw new SerializerException(e);
        }
    }

    @Override
    public boolean equals(CustomerDto customerDto, ByteBuffer byteBuffer) throws ClassNotFoundException, SerializerException {
        return customerDto.equals(read(byteBuffer));
    }

}
