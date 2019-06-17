package com.ddc.server.mapper;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCAuth;
import com.ddc.server.entity.DDCRoleAuth;
import com.ddc.server.shiro.PasswordUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class DDCAdminMapperTest extends BaseTest {
    @Resource
    private DDCAdminMapper ddcAdminMapper;
    @Resource
    private DDCAuthMapper authMapper;
    @Resource
    private DDCRoleMapper roleMapper;
    @Resource
    private DDCRoleAuthMapper roleAuthMapper;

    @Test
    public void hello() {
//        authMapper.insert(new DDCAuth("资讯管理","ZXGL",0L,1));
//        authMapper.insert(new DDCAuth("评论管理","PLGL",0L,1));
//        authMapper.insert(new DDCAuth("会员管理","HYGL",0L,1));
//        authMapper.insert(new DDCAuth("管理员管理","GLYGL",0L,1));
//        authMapper.insert(new DDCAuth("数据统计","SJTJ",0L,1));
//        authMapper.insert(new DDCAuth("系统管理","XTGL",0L,1));
        authMapper.insert(new DDCAuth("资讯列表", "ZXGL-ZXLB", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("图片管理", "ZXGL-TPGL", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("栏目管理", "ZXGL-LMGL", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("分类管理", "ZXGL-FLGL", 1139936537677512705L, 2));
        authMapper.insert(new DDCAuth("意见反馈", "PLGL-YJFK", 1139936538944192514L, 2));
        authMapper.insert(new DDCAuth("会员列表", "PLGL-HYLB", 1139936539090993153L, 2));
        authMapper.insert(new DDCAuth("浏览记录", "PLGL-LLJL", 1139936539090993153L, 2));
        authMapper.insert(new DDCAuth("管理员列表", "GLYGL-GLYLB", 1139936539376205826L, 2));
        authMapper.insert(new DDCAuth("角色列表", "GLYGL-JSLB", 1139936539376205826L, 2));
        authMapper.insert(new DDCAuth("权限列表", "GLYGL-QXLB", 1139936539376205826L, 2));
        authMapper.insert(new DDCAuth("活跃用户统计", "SJTJ-HYYH", 1139936539653029890L, 2));
        authMapper.insert(new DDCAuth("浏览记录统计", "SJTJ-LJL", 1139936539653029890L, 2));
        authMapper.insert(new DDCAuth("系统日志", "XTGL-XTRZ", 1139936539791441922L, 2));
    }

    @Test
    public void hello1() {
        List<DDCAuth> auths = authMapper.selectList(new EntityWrapper<>());
        List<DDCRoleAuth> list = new ArrayList<>(auths.size());
        for (DDCAuth auth : auths) {
            roleAuthMapper.insert(new DDCRoleAuth(null, 1L, auth.getId()));
        }

    }

    @Test
    public void hello2() {
        DDCAdmin admin = new DDCAdmin("root", "123456", 0,
                "13812341234", "hello@qq.com", 1L);
        PasswordUtils.entryptPassword(admin);
        ddcAdminMapper.insert(admin);
    }
}
