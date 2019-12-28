import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author John·Louis
 * @date create in 2019/12/26
 * description:
 */
@Data
@Builder
@AllArgsConstructor
public class Article {
    private long id;
    private String content;
    private String title;
}