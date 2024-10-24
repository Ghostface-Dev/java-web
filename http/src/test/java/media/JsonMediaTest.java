package media;

import com.google.gson.JsonElement;
import ghostface.dev.media.MediaType;
import ghostface.dev.media.json.JsonMediaType;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public final class JsonMediaTest {

   private final @NotNull String @NotNull [] valids = new String[] {
           "{\"nome\":\"João Silva\",\"email\":\"joao.silva@email.com\",\"telefone\":\"+55 11 91234-5678\",\"endereco\":{\"rua\":\"Rua das Flores\",\"numero\":123,\"cidade\":\"São Paulo\",\"estado\":\"SP\",\"cep\":\"12345-678\"}}",
            "{\"produtoId\":987654,\"nome\":\"Laptop\",\"marca\":\"TechBrand\",\"preco\":2999.99,\"emEstoque\":true,\"especificacoes\":{\"processador\":\"Intel i7\",\"ram\":\"16GB\",\"armazenamento\":\"512GB SSD\"}}"
   };

   @Test
   @DisplayName("Simple Json parser test")
   public void test() throws Throwable {
       @NotNull ByteArrayInputStream stream = new ByteArrayInputStream(valids[0].getBytes());
       @NotNull ByteArrayInputStream stream2 = new ByteArrayInputStream(valids[1].getBytes());

       @NotNull JsonElement json = JsonMediaType.parser.deserialize(stream);
       @NotNull JsonElement json2 = JsonMediaType.parser.deserialize(stream2);

       @NotNull InputStream inputStream = JsonMediaType.parser.serialize(json, MediaType.Parameter.UTF_8);
       @NotNull InputStream inputStream2 = JsonMediaType.parser.serialize(json2);

       Assertions.assertEquals(json.toString(), valids[0]);
       Assertions.assertEquals(json2.toString(), valids[1]);

       inputStream.close();
       inputStream2.close();
   }
}
