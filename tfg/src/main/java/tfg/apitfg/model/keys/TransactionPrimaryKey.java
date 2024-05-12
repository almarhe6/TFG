package tfg.apitfg.model.keys;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfg.apitfg.model.entity.Fund;
import tfg.apitfg.model.entity.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPrimaryKey {
    private User user;
    private Fund fund;
    private LocalDateTime effectDateTime;
}
