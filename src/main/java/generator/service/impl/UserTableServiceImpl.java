package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccut.main.bean.UserTable;
import generator.service.UserTableService;
import generator.mapper.UserTableMapper;
import org.springframework.stereotype.Service;

/**
* @author o'k
* @description 针对表【usertable(用户表)】的数据库操作Service实现
* @createDate 2022-07-07 09:59:08
*/
@Service
public class UserTableServiceImpl extends ServiceImpl<UserTableMapper, UserTable>
    implements UserTableService{

}




