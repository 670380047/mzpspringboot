package util.lombok.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/26 16:17
 * @File : UserInfo
 * @Software: IntelliJ IDEA 2019.3.15
 */
@AllArgsConstructor  //全参构造方法
@NoArgsConstructor      //无参构造方法
@Data    //自动为所有字段添加@ToString, @EqualsAndHashCode, @Getter方法，为非final字段添加@Setter,和@RequiredArgsConstructor!
public class UserInfo {
        private String name;
        private int age;
        private String sex;
}
